package epsi.tdd.grandrestaurant.doubles;

import epsi.tdd.grandrestaurant.services.*;

import java.util.List;

public class RestaurantDummy implements IRestaurant {
    @Override
    public void addPlatToMenu(Plat plat) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTables(int nbTables) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startService() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopService() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Serveur addNewServeur() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Table> getTablesLibres() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean affecterServeurTable(int indexTable, IServeur serveur) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void listerCommandesATransmettreGendarmerie() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transmettreCommandesGendarmerie() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCommande(ICommande commande) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCommandeNourriture(ICommande commande) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
