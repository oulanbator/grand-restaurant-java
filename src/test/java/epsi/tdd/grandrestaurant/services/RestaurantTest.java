package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
