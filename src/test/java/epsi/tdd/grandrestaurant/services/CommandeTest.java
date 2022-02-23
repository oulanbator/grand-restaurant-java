package epsi.tdd.grandrestaurant.services;


import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import epsi.tdd.grandrestaurant.builders.RestaurantBuilder;
import epsi.tdd.grandrestaurant.builders.ServeurBuilder;
import epsi.tdd.grandrestaurant.builders.TableBuilder;
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
        Commande commande = new Commande();
        Serveur serveur = new ServeurBuilder()
                .withCommande(commande, mock(Table.class))
                .build();

        // QUAND il la déclare comme non-payée
        serveur.commandeIsPaid(commande, false);

        // ALORS cette commande est marquée comme épinglée
        assertThat(commande.isEpinglee()).isTrue();
    }

    /**
     * ÉTANT DONNE un serveur ayant épinglé une commande
     * QUAND elle date d'il y a au moins 15 jours
     * ALORS cette commande est marquée comme à transmettre gendarmerie
     */
    @Test
    public void transmissionGendarmerie() throws ParseException {
        // ÉTANT DONNE un serveur ayant épinglé une commande
        Commande commande = new Commande();
        Serveur serveur = new ServeurBuilder()
                .withCommande(commande, mock(Table.class))
                .build();

        serveur.commandeIsPaid(commande, false); // Epingle la commande si inpayé(false)

        // QUAND elle date d'il y a au moins 15 jours
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -16); // Calculer la date limite (15 jours)
        commande.setDateEpinglage(calendar.getTime());

        // ET que les commandes à transmettre ont été listées par le serveur
        serveur.getRestaurant().listerCommandesATransmettreGendarmerie();

        // ALORS cette commande est marquée comme à transmettre gendarmerie
        assertThat(commande.isVersGendarmerie()).isTrue();
    }

    /**
     * ÉTANT DONNE une commande à transmettre gendarmerie
     * QUAND on consulte la liste des commandes à transmettre du restaurant
     * ALORS elle y figure
     */
    @Test
    public void listeATransmettre() {
        // ÉTANT DONNE une commande à transmettre gendarmerie
        Commande commandeATransmettre = mock(Commande.class);
        Restaurant restaurant = new RestaurantBuilder()
                .withCommandeATransmettre(commandeATransmettre)
                .build();

        // QUAND on consulte la liste des commandes à transmettre du restaurant
        List<ICommande> listeCommandes = restaurant.getaTansmettre();

        // ALORS elle y figure
        // Boucle sur les commandes
        assertThat(listeCommandes.contains(commandeATransmettre)).isTrue();
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
        Commande commandeATransmettre = mock(Commande.class);
        Restaurant restaurant = new RestaurantBuilder()
                .withCommandeATransmettre(commandeATransmettre)
                .build();

        // QUAND elle est marquée comme transmise à la gendarmerie
        restaurant.transmettreCommandesGendarmerie();

        // ALORS elle ne figure plus dans la liste des commandes à transmettre du restaurant
        assertThat(restaurant.getaTansmettre().contains(commandeATransmettre)).isFalse();
    }

    /**
     * ETANT DONNE un restaurant avec des clients à une table
     * QUAND les clients passent commande de nourriture ou de boissons
     * ALORS tout est noté en vue de la note finale
     */
    @Test
    public void noteFinale() {
        // ETANT DONNE un restaurant avec des clients à une table
        Restaurant restaurant = new RestaurantBuilder()
                .withServeurs(1)
                .withTables(2)
                .build();

        Table table = new TableBuilder()
                .withServeur(restaurant.getServeurs().get(0))
                .build();


        // QUAND les clients passent commande de nourriture et de boissons auprès du serveur
        ICommande commandeNourriture = mock(Commande.class);
        ICommande commandeBoissons = mock(Commande.class);
        table.passeCommande(commandeNourriture);
        table.passeCommande(commandeBoissons);

        // ALORS tout est noté en vue de la note finale
        assertThat(table.getAddition().contains(commandeNourriture)
                && table.getAddition().contains(commandeBoissons)).isTrue();
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
        Restaurant restaurant = new RestaurantBuilder()
                .withServeurs(1)
                .withTables(2)
                .build();

        Table table = new TableBuilder()
                .withServeur(restaurant.getServeurs().get(0))
                .build();

        // ET les clients ayant déjà passé commande
        Commande premiereCommande = mock(Commande.class);
        table.passeCommande(premiereCommande);

        // QUAND les clients sollicitent le serveur pour commander un extra
        Commande commandeExtras = mock(Commande.class);
        table.passeCommande(commandeExtras);

        // ALORS le serveur ajoute les nouvelles commandes à l'addition
        assertThat(table.getAddition().contains(premiereCommande)
                && table.getAddition().contains(commandeExtras)).isTrue();
    }

    // ETANT DONNE un restaurant avec des clients ayant commandé
    // QUAND les clients ont terminé leur repas
    // ALORS la commande est marquée comme réglée

    @Test
    public void repasFini() {
        // ETANT DONNE un restaurant avec des clients ayant commandé
        Client client = new Client();

        Restaurant restaurant = new RestaurantBuilder()
                .withTables(1)
                .withServiceStarted()
                .withClient(client)
                .build();

        client.getTable().passeCommande(new Commande());

        // QUAND les clients ont terminé leur repas
        client.repasEstFini();

        // ALORS la commande est marquée comme réglée
        Table table = client.getTable();
        for (ICommande c : table.getAddition()) {
            assertThat(c.isIsRegle()).isFalse();
        }
    }
}
