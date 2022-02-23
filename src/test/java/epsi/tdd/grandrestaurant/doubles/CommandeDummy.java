package epsi.tdd.grandrestaurant.doubles;

import epsi.tdd.grandrestaurant.model.TypeCommande;
import epsi.tdd.grandrestaurant.services.ICommande;

import java.util.Date;

public class CommandeDummy implements ICommande {

    @Override
    public TypeCommande getTypeCommande() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double getMontant() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isEpinglee() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Date getDateEpinglage() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setVersGendarmerie(boolean versGendarmerie) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setbTransmise(boolean bTransmise) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isIsRegle() {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setIsRegle(boolean isRegle) {
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
