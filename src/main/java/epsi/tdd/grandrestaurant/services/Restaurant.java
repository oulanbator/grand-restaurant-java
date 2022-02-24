package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Restaurant implements IRestaurant {
    private Long id;
    private Serveur maitreHotel;
    private List<Table> tables = new ArrayList<>();
    private List<Serveur> serveurs = new ArrayList<>();
    private List<ICommande> commandesPrises = new ArrayList<>();
    private List<ICommande> tachesCuisine = new ArrayList<>();
    private List<ICommande> aTansmettre = new ArrayList<>();
    private List<Plat> menu = new ArrayList<>();
    private boolean serviceEnCours = false;
    private boolean isFiliale;

    public Restaurant() {
        creerMaitreHotel();
    }

    public Restaurant(List<Plat> menu) {
        creerMaitreHotel();
        for (Plat plat : menu) {
            Plat platRestaurant = new Plat(plat.getId(), plat.getPrix());
            this.menu.add(platRestaurant);
        }
    }

    @Override
    public void addPlatToMenu(Plat plat) {
        Plat platRestaurant = new Plat(plat.getId(), plat.getPrix());
        this.menu.add(platRestaurant);
    }

    private void creerMaitreHotel() {
        // Crée maitre d'hôtel
        Serveur serveur = new Serveur();
        serveur.setMaitreHotel(true);
        serveur.setRestaurant(this);
        this.maitreHotel = serveur;
    }

    @Override
    public void createTables(int nbTables) {
        for (int i = 0; i < nbTables; i++) {
            Table newTable = new Table();
            tables.add(newTable);
        }
    }

    @Override
    public void startService() {
        // Affecte tables au maitre d'hôtel
        for (Table table : tables) {
            if (table.getServeur() == null) {
                table.setServeur(maitreHotel);
            }
        }
        serviceEnCours = true;
    }

    @Override
    public void stopService() {
        //
        serviceEnCours = false;
    }

    @Override
    public Serveur addNewServeur() {
        Serveur serveur = new Serveur();
        serveur.setRestaurant(this);
        this.serveurs.add(serveur);
        return serveur;
    }

    // public void creerServeurs(int nbServeurs) {
    // }

    @Override
    public List<Table> getTablesLibres() {
        List<Table> tablesLibres = new ArrayList<>();
        for (Table table : this.tables) {
            if (table.getClients().size() == 0) {
                tablesLibres.add(table);
            }
        }
        return tablesLibres;
    }


    @Override
    public boolean affecterServeurTable(int indexTable, IServeur serveur) {
        Table table = tables.get(indexTable);
        if (serviceEnCours) {
            return false;
        } else {
            table.setServeur(serveur);
            return true;
        }
    }

    @Override
    public void listerCommandesATransmettreGendarmerie() {
        for (ICommande commande : commandesPrises) {
            System.out.println(commande);
            if (commande.isEpinglee()) {
                System.out.println("épinglée");
                long dateEpinglage = commande.getDateEpinglage().getTime();
                long calendar = Calendar.getInstance().getTime().getTime();
                long diff = calendar - dateEpinglage;
                float res = (diff / (1000 * 60 * 60 * 24));
                if (res >= 15) {
                    System.out.println(">=15");
                    commande.setVersGendarmerie(true);
                    aTansmettre.add(commande);
                }
            }
        }
    }

    @Override
    public void transmettreCommandesGendarmerie() {
        List<ICommande> commandesATransmettre = new ArrayList<>(aTansmettre);
        for (ICommande commande : commandesATransmettre) {
            commande.setbTransmise(true);
            aTansmettre.remove(commande);
        }
    }

    // GETTERS & SETTERS
    public List<ICommande> getTachesCuisine() {
        return this.tachesCuisine;
    }

    @Override
    public void addCommandeNourriture(ICommande commande) {
        this.tachesCuisine.add(commande);
    }

    @Override
    public void addCommande(ICommande commande) {
        this.commandesPrises.add(commande);
    }

    public void addCommandeTransmettre(Commande commande) {
        // commande.setVersGendarmerie(true);
        this.aTansmettre.add(commande);
    }

    public List<Table> getTables() {
        return this.tables;
    }

    public List<ICommande> getaTansmettre() {
        return aTansmettre;
    }

    public List<Serveur> getServeurs() {
        return this.serveurs;
    }

    public Serveur getMaitreHotel() {
        return this.maitreHotel;
    }

    public List<ICommande> getCommandesPrises() {
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
            if (Objects.equals(platDuMenu.getId(), plat.getId())) {
                return  platDuMenu;
            }
        }
        return null;
    }

    public void modifiePrixDuPlat(Plat plat, double newPrix) {
        Plat platDuRestaurant = getPlat(plat);
        platDuRestaurant.setPrix(newPrix);
    }

    public void entreeClient(Client client) {
        Table table = getTablesLibres().get(0);
        table.affecterClient(client);
        client.setTable(table);
    }

    public List<Table> getTablesOccupees() {
        List<Table> tablesOccupees = new ArrayList<>();
        for (Table table : tables) {
            if (!table.isLibre()) {
                tablesOccupees.add(table);
            }
        }
        return tablesOccupees;
    }

    public boolean isServiceEnCours() {
        return serviceEnCours;
    }

    public void setMenu(List<Plat> menu) {
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
