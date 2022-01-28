package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ServeurTest {
    
    /**
     * ÉTANT DONNE un serveur dans un restaurant
     * QUAND il prend une commande de nourriture
     * ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce restaurant
     */
    @Test
    public void commandeNourriture() {
        // ÉTANT DONNE un serveur dans un restaurant
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();

        // QUAND il prend une commande de nourriture
        Commande commande = serveur.prendreCommandeNourriture();

        // ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce restaurant
        List<Commande> tachesCuisine = restaurant.getTachesCuisine();
        assertTrue(tachesCuisine.contains(commande));
    }

    /**
     * ÉTANT DONNE un serveur dans un restaurant
     * QUAND il prend une commande de boissons
     * ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de ce restaurant
     */
    @Test
    public void commandeBoisson() {
        // ÉTANT DONNE un serveur dans un restaurant
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();

        // QUAND il prend une commande de boissons
        Commande commande = serveur.prendreCommandeBoissons();
        
        // ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de ce restaurant
        List<Commande> tachesCuisine = restaurant.getTachesCuisine();
        assertFalse(tachesCuisine.contains(commande));
    }

    /**
     * ÉTANT DONNÉ un nouveau serveur
     * QUAND on récupére son chiffre d'affaires
     * ALORS celui-ci est à 0
     */
    @Test
    public void chiffreAffairesNouveauServeur() {
        // ÉTANT DONNÉ un nouveau serveur
        Serveur serveur = new Serveur();

    	// QUAND on récupére son chiffre d'affaires
        float result = serveur.getChiffreAffaires();

    	// ALORS celui-ci est à 0
        float expected = 0F;
        assertEquals(expected, result);
    }
}