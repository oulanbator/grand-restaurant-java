package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        Serveur serveur = new Serveur();
        Commande commande = new Commande();

        serveur.commandeIsPaid(commande, false); // Epingle la commande si inpayé(false)

        // QUAND elle date d'il y a au moins 15 jours
        Calendar cLimiteCalendar = Calendar.getInstance();

        // Date dEpinglage = commande.getDateEpinglage();
        String dateInString = "10012022"; // Initialisation date de test de la commande
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date dEpinglage = formatter.parse(dateInString);

        cLimiteCalendar.getTime();
        cLimiteCalendar.add(Calendar.DATE, -15); // Calculer la date limite (15 jours)
        Date dLimite = cLimiteCalendar.getTime();

        // ALORS cette commande est marquée comme à transmettre gendarmerie
        assertTrue(commande.setVersGendarmerie = dEpinglage.compareTo(dLimite) < 0 ? true : false);
    }

    /**
     * ÉTANT DONNE une commande à transmettre gendarmerie
     * QUAND on consulte la liste des commandes à transmettre du restaurant
     * ALORS elle y figure
     */
    @Test
    public void listeaTransmettre() {
        // ÉTANT DONNE une commande à transmettre gendarmerie
        Restaurant chezGaston = new Restaurant();
        Commande commandeATransmettre = new Commande();
        chezGaston.addCommandeTransmettre(commandeATransmettre);
        commandeATransmettre.setVersGendarmerie = true;

        // QUAND on consulte la liste des commandes à transmettre du restaurant
        List<Commande> ListeCommande = new ArrayList<>();
        ListeCommande = chezGaston.getaTansmettre();

        // ALORS elle y figure
        // Boucle sur les commandes
        for (int i = 0; i > ListeCommande.size(); i++) {
            // Récupère la commande à transmettre
            Commande result = chezGaston.getaTansmettre().get(i);
            if (result == commandeATransmettre) {
                assertEquals(commandeATransmettre, result);
            }
        }

    }
}
