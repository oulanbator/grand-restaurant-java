package epsi.tdd.grandrestaurant.doubles;

import epsi.tdd.grandrestaurant.services.*;

public class ServeurDummy implements IServeur {
    @Override
    public void prendreCommande(ICommande commande, ITable table) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commandeIsPaid(Commande commande, boolean estPaye) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isMaitreHotel() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
