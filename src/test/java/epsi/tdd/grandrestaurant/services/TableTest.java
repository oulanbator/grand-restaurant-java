package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;
import static org.javalite.test.jspec.JSpec.*;
import epsi.tdd.grandrestaurant.model.TypeCommande;
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
        //assertFalse(tablesLibres.contains(tableCiblee));
         a(tablesLibres.contains(tableCiblee)).shouldBeFalse();
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
       // assertTrue(tablesLibres.contains(tableCiblee));
        a(tablesLibres.contains(tableCiblee)).shouldBeTrue();
    }

    /**
     * ETANT DONNE un restaurant
     * QUAND un client entre
     * ALORS il est installé à une table libre par le maitre d'hôtel
     */
    @Test
    public void entreeClient() {
        //ETANT DONNE un restaurant
        Restaurant restaurant = new Restaurant();
        int tablesLibresInitiales = 3;
        restaurant.createTables(tablesLibresInitiales);
        restaurant.startService();

        //QUAND un client entre
        Client client = new Client("Vladimir Poutine");
        restaurant.entreeClient(client);

        //ALORS il est installé à une table libre par le maitre d'hôtel
        // TODO : utiliser un spy ici ?
        int nbTablesLibres = restaurant.getTablesLibres().size();
        Table tableOccupee = restaurant.getTablesOccupees().get(0);
//        assertTrue(nbTablesLibres == (tablesLibresInitiales - 1) &&
//                    tableOccupee.getClients().get(0) == client);
         $(nbTablesLibres == (tablesLibresInitiales - 1) &&
                    tableOccupee.getClients().get(0) == client).shouldBeTrue();
    }
}
