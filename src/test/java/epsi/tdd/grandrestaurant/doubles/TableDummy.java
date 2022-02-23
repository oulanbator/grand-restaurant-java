package epsi.tdd.grandrestaurant.doubles;

import epsi.tdd.grandrestaurant.services.IClient;
import epsi.tdd.grandrestaurant.services.ICommande;
import epsi.tdd.grandrestaurant.services.ITable;

import java.util.List;

public class TableDummy implements ITable {
    @Override
    public void affecterClient(IClient client) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void passeCommande(ICommande commande) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ICommande> getAddition() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
