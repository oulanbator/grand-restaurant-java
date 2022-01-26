package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<Table> tables = new ArrayList<>();
    private Serveur maitreHotel;

    public void createTables(int nbTables) {
        for (int i = 0 ; i < nbTables ; i++) {
            Table newTable = new Table();
            tables.add(newTable);
        }
    }

    public void startService() {
        // Crée maitre d'hôtel
        Serveur serveur = new Serveur();
        serveur.setMaitreHotel(true);
        this.maitreHotel = serveur;

        // Affecte tables au maitre d'hôtel
        for (Table table : tables) {
            table.setServeur(maitreHotel);
        }
    }

    public List<Table> getTables() {
        return this.tables;
    }
    
}
