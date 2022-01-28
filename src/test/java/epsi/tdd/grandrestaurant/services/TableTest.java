package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class TableTest {

    /**
     * ÉTANT DONNE une table dans un restaurant ayant débuté son service
     * QUAND un client est affecté à une table
     * ALORS cette table n'est plus sur la liste des tables libres du restaurant
     */
    @Test
    public void tableOccupee() {
        // ÉTANT DONNE une table dans un restaurant ayant débuté son service
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(2);

        // QUAND un client est affecté à une table
        Client clientDummy = mock(Client.class);
        Table tableOccupee = restaurant.getTables().get(0);
        tableOccupee.affecteClient(clientDummy);

        // ALORS cette table n'est plus sur la liste des tables libres du restaurant
        boolean tableEstDansTablesLibres = restaurant.getTablesLibres().contains(tableOccupee);
        assertFalse(tableEstDansTablesLibres);
    }
}
