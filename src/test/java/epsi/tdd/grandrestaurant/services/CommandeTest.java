package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

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
        assertTrue(commande.isEpinglee());
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
        assertTrue(commande.isVersGendarmerie() == true);
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
        assertTrue(listeCommandes.contains(commandeATransmettre));
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
        assertFalse(chezGaston.getaTansmettre().contains(commande));

    }
}
