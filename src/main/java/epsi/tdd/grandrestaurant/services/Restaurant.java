package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Restaurant {
    private Serveur maitreHotel;
    private List<Table> tables = new ArrayList<>();
    private List<Serveur> serveurs = new ArrayList<>();
    private List<Commande> commandesPrises = new ArrayList<>();
    private List<Commande> tachesCuisine = new ArrayList<>();
    private List<Commande> aTansmettre = new ArrayList<>();
    private List<Plat> menu = new ArrayList<>();
    private boolean serviceEnCours = false;
    private boolean isFiliale;

    public Restaurant() {
        creerMaitreHotel();
    }

    public Restaurant(List<Plat> menu) {
        creerMaitreHotel();
        for (Plat plat : menu) {
            this.menu.add(plat);
        }
    }

    public void addPlatToMenu(Plat plat) {
        this.menu.add(plat);
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
        serviceEnCours = true;
    }

    public void stopService() {
        //
        serviceEnCours = false;
    }

    public Serveur addNewServeur() {
        Serveur serveur = new Serveur();
        serveur.setRestaurant(this);
        this.serveurs.add(serveur);
        return serveur;
    }

    // public void creerServeurs(int nbServeurs) {
    // }

    public List<Table> getTablesLibres() {
        List<Table> tablesLibres = new ArrayList<>();
        for (Table table : this.tables) {
            if (table.getClients().size() == 0) {
                tablesLibres.add(table);
            }
        }
        return tablesLibres;
    }

    // Affecte un serveur à la table
    public boolean affecterServeurTable(int indexTable, Serveur serveur) {
        Table table = tables.get(indexTable);
        if (serviceEnCours) {
            return false;
        } else {
            table.setServeur(serveur);
            return true;
        }
    }

    public void listerCommandesATransmettreGendarmerie() {
        for (Commande commande : commandesPrises) {
            if (commande.isEpinglee()) {
                long dateEpinglage = commande.getDateEpinglage().getTime();
                long calendar = Calendar.getInstance().getTime().getTime();
                long diff = calendar - dateEpinglage;
                float res = (diff / (1000 * 60 * 60 * 24));
                if (res >= 15) {
                    commande.setVersGendarmerie(true);
                    aTansmettre.add(commande);
                }
            }
        }
    }

    public void transmettreCommandesGendarmerie() {
        List<Commande> commandesATransmettre = new ArrayList<Commande>(aTansmettre);
        for (Commande commande : commandesATransmettre) {
            commande.setbTransmise(true);
            aTansmettre.remove(commande);
        }
    }

    // GETTERS & SETTERS
    public List<Commande> getTachesCuisine() {
        return this.tachesCuisine;
    }

    public void addCommandeNourriture(Commande commande) {
        this.tachesCuisine.add(commande);
    }

    public void addCommande(Commande commande) {
        this.commandesPrises.add(commande);
    }

    public void addCommandeTransmettre(Commande commande) {
        // commande.setVersGendarmerie(true);
        this.aTansmettre.add(commande);
    }

    public List<Table> getTables() {
        return this.tables;
    }

    public List<Commande> getaTansmettre() {
        return aTansmettre;
    }

    public List<Serveur> getServeurs() {
        return this.serveurs;
    }

    public Serveur getMaitreHotel() {
        return this.maitreHotel;
    }

    public List<Commande> getCommandesPrises() {
        return commandesPrises;
    }

    public boolean isFiliale() {
        return isFiliale;
    }

    public void setFiliale(boolean filiale) {
        isFiliale = filiale;
    }

    public Plat getPlat(Plat plat) {
        for (Plat platDuMenu : this.menu) {
            if (platDuMenu.getId() == plat.getId()) {
                return  platDuMenu;
            }
        }
        return null;
    }
}
