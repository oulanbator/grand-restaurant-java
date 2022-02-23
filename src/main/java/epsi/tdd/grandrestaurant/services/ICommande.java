package epsi.tdd.grandrestaurant.services;

import epsi.tdd.grandrestaurant.model.TypeCommande;

import java.util.Date;

public interface ICommande {
    TypeCommande getTypeCommande();
    double getMontant();
    boolean isEpinglee();
    Date getDateEpinglage();
    void setVersGendarmerie(boolean versGendarmerie);
    void setbTransmise(boolean bTransmise);
    boolean isIsRegle();
    void setIsRegle(boolean isRegle);
}
