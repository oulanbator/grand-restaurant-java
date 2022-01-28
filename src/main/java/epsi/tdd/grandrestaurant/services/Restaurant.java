package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Serveur maitreHotel;
    private List<Table> tables = new ArrayList<>();
    private List<Serveur> serveurs = new ArrayList<>();
    private List<Commande> tachesCuisine = new ArrayList<>();

    public Restaurant() {
        creerMaitreHotel();
    }

    private void creerMaitreHotel() {
        // Crée maitre d'hôtel
        Serveur serveur = new Serveur();
        serveur.setMaitreHotel(true);
        this.maitreHotel = serveur;
    }

    public void createTables(int nbTables) {
        for (int i = 0; i < nbTables; i++) {
            Table newTable = new Table();
            tables.add(newTable);
        }
    }

    public void startService() {
        // Affecte tables au maitre d'hôtel
        for (Table table : tables) {
            if (table.getServeur() == null) {
                table.setServeur(maitreHotel);
            }
        }
    }

    public List<Table> getTables() {
        return this.tables;
    }

    public Serveur addNewServeur() {
        Serveur serveur = new Serveur();
        serveur.setRestaurant(this);
        this.serveurs.add(serveur);
        return serveur;
    }

    // public void creerServeurs(int nbServeurs) {
    // }

    public List<Serveur> getServeurs() {
        return this.serveurs;
    }

    public Serveur getMaitreHotel() {
        return this.maitreHotel;
    }

    public List<Table> getTablesLibres() {
        List<Table> tablesLibres = new ArrayList<>();
        for (Table table : this.tables) {
            if (table.getClients().size() == 0) {
                tablesLibres.add(table);
            }
        }
        return tablesLibres;
    }

    public List<Commande> getTachesCuisine() {
        return this.tachesCuisine;
    }

    public void newCommandeNourriture(Commande commande) {
        this.tachesCuisine.add(commande);
    }

}
