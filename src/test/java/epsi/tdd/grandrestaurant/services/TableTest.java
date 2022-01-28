package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

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
        restaurant.startService();

        // QUAND un client est affecté à une table
        Client clientDummy = mock(Client.class);
        Table tableCiblee = restaurant.getTables().get(0);
        tableCiblee.affecterClient(clientDummy);

        // ALORS cette table n'est plus sur la liste des tables libres du restaurant
        List<Table> tablesLibres = restaurant.getTablesLibres();
        assertFalse(tablesLibres.contains(tableCiblee));
    }

    /**
     * ÉTANT DONNE une table occupée par un client
     * QUAND la table est libérée
     * ALORS cette table apparaît sur la liste des tables libres du restaurant
     */
    @Test
    public void tableLiberee() {
        // ÉTANT DONNE une table dans un restaurant *ayant débuté son service*, occupée par un client 
        Restaurant restaurant = new Restaurant();
        restaurant.createTables(2);
        restaurant.startService();

        Client clientDummy = mock(Client.class);
        Table tableCiblee = restaurant.getTables().get(0);
        tableCiblee.affecterClient(clientDummy);

        // QUAND la table est libérée
        tableCiblee.liberer();

        // ALORS cette table apparaît sur la liste des tables libres du restaurant
        List<Table> tablesLibres = restaurant.getTablesLibres();
        assertTrue(tablesLibres.contains(tableCiblee));
    }
}
