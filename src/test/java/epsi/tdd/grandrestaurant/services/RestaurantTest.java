package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class RestaurantTest {

    /**
     * ÉTANT DONNE un restaurant ayant 3 tables
     * QUAND le service commence
     * ALORS elles sont toutes affectées au Maître d'Hôtel
     */
    @Test
    public void affecteTablesMaitreHotel() {
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(3);
        restaurant.startService();

        for (Table table : restaurant.getTables()) {
            assertTrue(table.getServeur().isMaitreHotel());
        }
    }

    /**
     * ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
     * QUAND le service débute
     * ALORS la table éditée est affectée au serveur et les deux autres au maître
     * d'hôtel
     */
    @Test
    public void affecteTableServeur() {
        // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(3);
        // creer serveur et affecte table
        Serveur serveur = restaurant.addNewServeur();
        int indexTableServeur = 0;
        Table table = restaurant.getTables().get(indexTableServeur);
        table.setServeur(serveur);

        // QUAND le service débute
        restaurant.startService();

        // ALORS la table éditée est affectée au serveur et les deux autres au maître
        // Boucle sur les tables
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            // Recupère le serveur de la table
            Serveur result = restaurant.getTables().get(i).getServeur();
            if (i == indexTableServeur) {
                Serveur expected = serveur;
                assertEquals(expected, result);
            } else {
                Serveur expected = restaurant.getMaitreHotel();
                assertEquals(expected, result);
            }
        }
    }

    /**
     * ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
     * QUAND le service débute
     * ALORS il n'est pas possible de modifier le serveur affecté à la table
     */
    @Test
    public void modifieAffectation() {
        // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(3);
        Serveur serveur = restaurant.addNewServeur();
        int indexTable = 0;
        Table table = restaurant.getTables().get(indexTable);
        table.setServeur(serveur);

        // QUAND le service débute
        restaurant.startService();

        // ALORS il n'est pas possible de modifier le serveur affecté à la table
        assertFalse(Restaurant.retirerTableServeur(table, serveur));
        // Cannot make a static reference to the non-static method retirerTableServeur(Table, Serveur) from the type Restaurant
        // Je ne comprends pas
    }

}
