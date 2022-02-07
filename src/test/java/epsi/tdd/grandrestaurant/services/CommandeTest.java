package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SourceType;
import org.junit.jupiter.api.Test;

import epsi.tdd.grandrestaurant.model.TypeCommande;

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
        Commande commande = new Commande();

        serveur.prendreCommande(commande);

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
        serveur.prendreCommande(commande);
        serveur.commandeIsPaid(commande, false); // Epingle la commande si inpayé(false)

        // QUAND elle date d'il y a au moins 15 jours
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -15); // Calculer la date limite (15 jours)
        commande.setDateEpinglage(calendar.getTime());

        // ALORS cette commande est marquée comme à transmettre gendarmerie
        restaurant.transmettreCommandesGendarmerie();
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
        Commande commande = new Commande();
        chezGaston.addCommandeTransmettre(commande);
        commande.setVersGendarmerie(true);

        // QUAND elle est marquée comme transmise à la gendarmerie
        commande.bTransmise = true;

        // ALORS elle ne figure plus dans la liste des commandes à transmettre du
        // restaurant
        assertFalse(chezGaston.getaTansmettre().contains(commande));
        // List<Commande> ListeCommande = new ArrayList<>();
        // for (int i = 0; i > ListeCommande.size(); i++) {
        //     //
        //     Commande result = chezGaston.getaTansmettre().get(i);
        //     if (result == commande) {
        //         assertEquals(commande, result);
        //     }
        // }

    }
}
