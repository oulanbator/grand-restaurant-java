package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
            // assertTrue(table.getServeur().isMaitreHotel());
            // assertThat(table.getServeur().isMaitreHotel())
            assertThat(table.getServeur().isMaitreHotel()).isTrue();
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
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(3);

        Serveur serveur = restaurant.addNewServeur();
        int indexTable = 0;
        Table table = restaurant.getTables().get(indexTable);
        table.setServeur(serveur);

        restaurant.startService();

        for (int i = 0; i < restaurant.getTables().size(); i++) {
            if (i == indexTable) {
                assertThat(restaurant.getTables().get(i).getServeur()).isEqualTo(serveur);
            } else {
                assertThat(restaurant.getTables().get(i).getServeur()).isEqualTo(restaurant.getMaitre());
            }
        }
    }

    /**
     * ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
     * QUAND le service débute
     * ALORS il n'est pas possible de modifier le serveur affecté à la table
     */
    //@Test

}
