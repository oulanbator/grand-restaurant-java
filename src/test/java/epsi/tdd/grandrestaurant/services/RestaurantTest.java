package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.javalite.test.jspec.JSpec.*;
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
//            assertTrue(table.getServeur().isMaitreHotel());
            $(table.getServeur().isMaitreHotel()).shouldBeTrue();
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
        /*
         * Table table = restaurant.getTables().get(indexTableServeur);
         * table.setServeur(serveur);
         */
        restaurant.affecterServeurTable(indexTableServeur, serveur);
        // QUAND le service débute
        restaurant.startService();

        // ALORS la table éditée est affectée au serveur et les deux autres au maître
        // Boucle sur les tables
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            // Recupère le serveur de la table
            Serveur result = restaurant.getTables().get(i).getServeur();
            if (i == indexTableServeur) {
                Serveur expected = serveur;
                $(expected).shouldBeEqual(result);
            } else {
                Serveur expected = restaurant.getMaitreHotel();
                $(expected).shouldBeEqual(result);
               
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
        /* Serveur serveur = restaurant.addNewServeur(); */
        Serveur serveur = mock(Serveur.class);

        int indexTable = 0;
        restaurant.affecterServeurTable(indexTable, serveur);

        // QUAND le service débute
        restaurant.startService();

        // ALORS il n'est pas possible de modifier le serveur affecté à la table
        Serveur serveurDummy = mock(Serveur.class);

//        assertFalse(restaurant.affecterServeurTable(indexTable, serveurDummy));
        $(restaurant.affecterServeurTable(indexTable, serveurDummy)).shouldBeFalse();
    }

    /**
     * ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
     * ET ayant débuté son service
     * QUAND le service se termine
     * ET qu'une table est affectée à un serveur
     * ALORS la table éditée est affectée au serveur et les deux autres au maître
     * d'hôtel
     */
    @Test
    public void finDeService() {
        // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(3);
        Serveur serveur = restaurant.addNewServeur();
        int indexTableServeur = 0;
        restaurant.affecterServeurTable(indexTableServeur, serveur);

        // ET ayant débuté son service
        restaurant.startService();

        // QUAND le service se termine
        restaurant.stopService();

        // ET qu'une table est affectée à un serveur
        // TODO : Peut être à revoir sur la définition de ce test
        restaurant.affecterServeurTable(indexTableServeur, serveur);

        // ALORS la table éditée est affectée au serveur et les deux autres au maître
        // d'hôtel
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            // Récupère le serveur de la table
            Serveur serveurDeLaTable = restaurant.getTables().get(i).getServeur();
            if (i == indexTableServeur) {
                Serveur serveurNormal = serveur;
                the(serveurDeLaTable).shouldBeEqual(serveurNormal);
            } else {
                Serveur maitreHotel = restaurant.getMaitreHotel();
               the(serveurDeLaTable).shouldBeEqual(maitreHotel);
            }
        }
    }

}
