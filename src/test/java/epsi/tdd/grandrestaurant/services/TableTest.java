package epsi.tdd.grandrestaurant.services;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import epsi.tdd.grandrestaurant.services.builders.RestaurantBuilder;
import epsi.tdd.grandrestaurant.doubles.ClientDummy;

public class TableTest {

    /**
     * ÉTANT DONNE une table dans un restaurant ayant débuté son service
     * QUAND un client est affecté à une table
     * ALORS cette table n'est plus sur la liste des tables libres du restaurant
     */
    @Test
    public void tableOccupee() {
        // ÉTANT DONNE une table dans un restaurant ayant débuté son service
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(2)
                .withServiceStarted()
                .build();

        // QUAND un client est affecté à une table
        Table tableCiblee = restaurant.getTables().get(0);
        tableCiblee.affecterClient(new ClientDummy());

        // ALORS cette table n'est plus sur la liste des tables libres du restaurant
        List<Table> tablesLibres = restaurant.getTablesLibres();
        assertThat(tableCiblee).isNotIn(tablesLibres);
    }

    /**
     * ÉTANT DONNE une table occupée par un client
     * QUAND la table est libérée
     * ALORS cette table apparaît sur la liste des tables libres du restaurant
     */
    @Test
    public void tableLiberee() {
        // ÉTANT DONNE une table dans un restaurant *ayant débuté son service*, occupée
        // par un client
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(2)
                .withServiceStarted()
                .build();

        Table tableCiblee = restaurant.getTables().get(0);
        tableCiblee.affecterClient(new ClientDummy());

        // QUAND la table est libérée
        tableCiblee.liberer();

        // ALORS cette table apparaît sur la liste des tables libres du restaurant
        List<Table> tablesLibres = restaurant.getTablesLibres();
        // assertTrue(tablesLibres.contains(tableCiblee));
        assertThat(tablesLibres).contains(tableCiblee);
    }

    /**
     * ETANT DONNE un restaurant
     * QUAND un client entre
     * ALORS il est installé à une table libre par le maitre d'hôtel
     */
    @Test
    public void entreeClient() {
        // ETANT DONNE un restaurant
        int tablesLibresInitiales = 3;
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(tablesLibresInitiales)
                .withServiceStarted()
                .build();

        // QUAND un client entre
        Client client = new Client("Vladimir Poutine");
        restaurant.entreeClient(client);

        // ALORS il est installé à une table libre par le maitre d'hôtel
        // TODO : utiliser un spy ici ?
        int nbTablesLibres = restaurant.getTablesLibres().size();
        Table tableOccupee = restaurant.getTablesOccupees().get(0);

        assertThat(nbTablesLibres == (tablesLibresInitiales - 1) 
                && tableOccupee.getClients().get(0) == client).isTrue();
    }
}
