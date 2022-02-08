package epsi.tdd.grandrestaurant.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

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

    /**
     * ETANT DONNE un restaurant avec des clients à une table
     * QUAND les clients passent commande de nourriture ou de boissons
     * ALORS tout est noté en vue de la note finale
     */
    @Test
    public void noteFinale() {
//        ETANT DONNE un restaurant avec des clients à une table
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();
        restaurant.createTables(2);
        Table table = restaurant.getTables().get(0);
        table.setServeur(serveur);
        restaurant.startService();

//        QUAND les clients passent commande de nourriture et de boissons auprès du serveur
        Commande commandeNourriture = mock(Commande.class);
        Commande commandeBoissons = mock(Commande.class);
        table.passeCommande(commandeNourriture);
        table.passeCommande(commandeBoissons);

//        ALORS tout est noté en vue de la note finale
        assertTrue(table.getAddition().contains(commandeNourriture) && table.getAddition().contains(commandeBoissons));

    }
}
