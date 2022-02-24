package epsi.tdd.grandrestaurant.services;

import static org.assertj.core.api.Assertions.*;

import epsi.tdd.grandrestaurant.services.builders.RestaurantBuilder;
import epsi.tdd.grandrestaurant.doubles.ServeurDummy;
import org.junit.jupiter.api.Test;

public class RestaurantTest {

    /**
     * ÉTANT DONNE un restaurant ayant 3 tables
     * QUAND le service commence
     * ALORS elles sont toutes affectées au Maître d'Hôtel
     */
    @Test
    public void affecteTablesMaitreHotel() {
        //ÉTANT DONNE un restaurant ayant 3 tables
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(3)
                .build();
        
        //QUAND le service commence
        restaurant.startService();

        //ALORS elles sont toutes affectées au Maître d'Hôtel
        for (Table table : restaurant.getTables()) {
            assertThat(table.getServeur().isMaitreHotel());
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
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(3)
                .withServeurs(1)
                .build();

        int indexTable = 0;
        restaurant.affecterServeurTable(indexTable, restaurant.getServeurs().get(0));

        // QUAND le service débute
        restaurant.startService();

        // ALORS la table éditée est affectée au serveur et les deux autres au maître
        // Boucle sur les tables
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            // Recupère le serveur de la table courante
            IServeur serveurDeLaTable = restaurant.getTables().get(i).getServeur();
            if (i == indexTable) {
                Serveur serveur = restaurant.getServeurs().get(0);
                assertThat(serveurDeLaTable).isEqualTo(serveur);
            } else {
                Serveur maitreHotel = restaurant.getMaitreHotel();
                assertThat(serveurDeLaTable).isEqualTo(maitreHotel);
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
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(3)
                .build();

        int indexTable = 0;
        restaurant.affecterServeurTable(indexTable, new ServeurDummy());

        // QUAND le service débute
        restaurant.startService();

        // ALORS il n'est pas possible de modifier le serveur affecté à la table
        assertThat(restaurant.affecterServeurTable(indexTable, new ServeurDummy())).isFalse();
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
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(3)
                .withServeurs(1)
                .build();
                
        int indexTableServeur = 0;
        Serveur serveur = restaurant.getServeurs().get(0);
        restaurant.affecterServeurTable(indexTableServeur, serveur);

        // ET ayant débuté son service
        restaurant.startService();

        // QUAND le service se termine
        restaurant.stopService();

        // ET qu'une table est affectée à un serveur
        restaurant.affecterServeurTable(indexTableServeur, serveur);

        // ALORS la table éditée est affectée au serveur et les deux autres au maître
        // d'hôtel
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            // Récupère le serveur de la table
            IServeur serveurDeLaTable = restaurant.getTables().get(i).getServeur();
            if (i == indexTableServeur) {
                Serveur leServeur = serveur;
                assertThat(serveurDeLaTable).isEqualTo(leServeur);
            } else {
                Serveur leMaitreHotel = restaurant.getMaitreHotel();
                assertThat(serveurDeLaTable).isEqualTo(leMaitreHotel);
            }
        }
    }
}
