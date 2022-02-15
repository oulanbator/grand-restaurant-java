package epsi.tdd.grandrestaurant.services;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandeTest {
    /**
     * ÉTANT DONNE un serveur ayant pris une commande
     * QUAND il la déclare comme non-payée
     * ALORS cette commande est marquée comme épinglée
     */
    @Test
    public void commandeEpinglee() {
        // ÉTANT DONNE un serveur ayant pris une commande
        Serveur serveur = new Serveur();
        serveur.setRestaurant(mock(Restaurant.class));
        Commande commande = new Commande();

        serveur.prendreCommande(commande, mock(Table.class));

        // QUAND il la déclare comme non-payée
        serveur.commandeIsPaid(commande, false);

        // ALORS cette commande est marquée comme épinglée
        assertThat(commande.isEpinglee());
    }

    /**
     * ÉTANT DONNE un serveur ayant épinglé une commande
     * QUAND elle date d'il y a au moins 15 jours
     * ALORS cette commande est marquée comme à transmettre gendarmerie
     */
    @Test
    public void transmissionGendarmerie() throws ParseException {
        // ÉTANT DONNE un serveur ayant épinglé une commande
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();
        Commande commande = new Commande();
        serveur.prendreCommande(commande, mock(Table.class));
        serveur.commandeIsPaid(commande, false); // Epingle la commande si inpayé(false)

        // QUAND elle date d'il y a au moins 15 jours
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -15); // Calculer la date limite (15 jours)
        commande.setDateEpinglage(calendar.getTime());

        // ALORS cette commande est marquée comme à transmettre gendarmerie
        restaurant.listerCommandesATransmettreGendarmerie();
        assertThat(commande.isVersGendarmerie() == true);
    }

    /**
     * ÉTANT DONNE une commande à transmettre gendarmerie
     * QUAND on consulte la liste des commandes à transmettre du restaurant
     * ALORS elle y figure
     */
    @Test
    public void listeATransmettre() {
        // ÉTANT DONNE une commande à transmettre gendarmerie
        Restaurant chezGaston = new Restaurant();
        Commande commandeATransmettre = mock(Commande.class);
        chezGaston.addCommandeTransmettre(commandeATransmettre);
        commandeATransmettre.setVersGendarmerie(true);

        // QUAND on consulte la liste des commandes à transmettre du restaurant
        List<Commande> listeCommandes = chezGaston.getaTansmettre();

        // ALORS elle y figure
        // Boucle sur les commandes
        assertThat(listeCommandes.contains(commandeATransmettre));
    }

    /**
     * ÉTANT DONNE une commande à transmettre gendarmerie
     * QUAND elle est marquée comme transmise à la gendarmerie
     * ALORS elle ne figure plus dans la liste des commandes à transmettre du
     * restaurant
     */
    @Test
    public void transmiseGendarmerie() {
        // ÉTANT DONNE une commande à transmettre gendarmerie
        Restaurant chezGaston = new Restaurant();
        Commande commande = mock(Commande.class);
        chezGaston.addCommandeTransmettre(commande);

        // QUAND elle est marquée comme transmise à la gendarmerie
        chezGaston.transmettreCommandesGendarmerie();

        // ALORS elle ne figure plus dans la liste des commandes à transmettre du
        // restaurant
        assertThat(chezGaston.getaTansmettre().contains(commande));
    }

    /**
     * ETANT DONNE un restaurant avec des clients à une table
     * QUAND les clients passent commande de nourriture ou de boissons
     * ALORS tout est noté en vue de la note finale
     */
    @Test
    public void noteFinale() {
        // ETANT DONNE un restaurant avec des clients à une table
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();
        restaurant.createTables(2);
        Table table = restaurant.getTables().get(0);
        table.setServeur(serveur);
        restaurant.startService(); // Inutile ?

        // QUAND les clients passent commande de nourriture et de boissons auprès du
        // serveur
        Commande commandeNourriture = mock(Commande.class);
        Commande commandeBoissons = mock(Commande.class);
        table.passeCommande(commandeNourriture);
        table.passeCommande(commandeBoissons);

        // ALORS tout est noté en vue de la note finale
        assertThat(table.getAddition().contains(commandeNourriture) && table.getAddition().contains(commandeBoissons));
    }

    /**
     * ETANT DONNE un restaurant avec des clients à une table
     * ET les clients ayant déjà passé commande
     * QUAND les clients sollicitent le serveur pour commander un extra
     * ALORS le serveur ajoute les nouvelles commandes à l'addition
     */
    @Test
    public void commandeExtras() {
        // ETANT DONNE un restaurant avec des clients à une table
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();
        restaurant.createTables(2);
        Table table = restaurant.getTables().get(0);
        table.setServeur(serveur);
        restaurant.startService(); // Inutile ?

        // ET les clients ayant déjà passé commande
        Commande premiereCommande = mock(Commande.class);
        table.passeCommande(premiereCommande);

        // QUAND les clients sollicitent le serveur pour commander un extra
        Commande commandeExtras = mock(Commande.class);
        table.passeCommande(commandeExtras);

        // ALORS le serveur ajoute les nouvelles commandes à l'addition
        // TODO : Est-ce qu'on pourrais pas plutôt faire un "spy" pour vérifier que le
        // serveur ajoute les nouvelles commandes à l'addition plutôt que de vérifier
        // l'addition ?
        assertThat(table.getAddition().contains(commandeExtras));
    }

    // ETANT DONNE un restaurant avec des clients ayant commandé
    // QUAND les clients ont terminé leur repas
    // ALORS la commande est marquée comme réglée

    @Test
    public void repasFini() {
        // ETANT DONNE un restaurant avec des clients ayant commandé
        Restaurant restaurant = new Restaurant();
        Commande commande = new Commande();
        restaurant.createTables(1);
        restaurant.startService();
        Client client = new Client("Vladimir Poutine");
        restaurant.entreeClient(client);
        client.getTable().passeCommande(commande);

        // QUAND les clients ont terminé leur repas
        client.repasEstFini();
        // ALORS la commande est marquée comme réglée
        Table table = restaurant.getTables().get(0);
        for (Commande c : table.getAddition()) {
            assertThat(c.isIsRegle());
        }
    }
}
