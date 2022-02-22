package epsi.tdd.grandrestaurant.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
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
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(3);
        // creer serveur et affecte table
        Serveur serveur = restaurant.addNewServeur();

        int indexTableServeur = 0;
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
                assertThat(result).isEqualTo(expected);
                assertThat(result).isEqualTo(expected);
            } else {
                Serveur expected = restaurant.getMaitreHotel();
                assertThat(result).isEqualTo(expected);
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
        assertThat(restaurant.affecterServeurTable(indexTable, serveurDummy))
                .as("il n'est pas possible de modifier le serveur affecté à la table").isFalse();
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
        restaurant.affecterServeurTable(indexTableServeur, serveur);

        // ALORS la table éditée est affectée au serveur et les deux autres au maître
        // d'hôtel
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            // Récupère le serveur de la table
            Serveur result = restaurant.getTables().get(i).getServeur();
            if (i == indexTableServeur) {
                Serveur expected = serveur;
                assertThat(result).isEqualTo(expected);
            } else {
                Serveur expected = restaurant.getMaitreHotel();
                assertThat(result).isEqualTo(expected);
            }
        }
    }

}
