package epsi.tdd.grandrestaurant.services;

import java.util.Date;

import epsi.tdd.grandrestaurant.model.TypeCommande;

public class Commande {
    TypeCommande typeCommande;
    Table table;
    double montant;
    boolean epinglee;
    Date dateEpinglage;
    boolean setVersGendarmerie;

    public Commande() {
    }

    public Commande(TypeCommande type) {
        this.typeCommande = type;
    }

    // GETTERS & SETTERS
    public Date getDateEpinglage() {
        return dateEpinglage;
    }

    public void setDateEpinglage(Date date) {
        this.dateEpinglage = date;
    }

    public boolean isEpinglee() {
        return epinglee;
    }

    public void setEpinglee(boolean epinglee) {
        this.epinglee = epinglee;
    }

    public TypeCommande getTypeCommande() {
        return typeCommande;
    }

    public void setTypeCommande(TypeCommande typeCommande) {
        this.typeCommande = typeCommande;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public boolean isSetVersGendarmerie() {
        return setVersGendarmerie;
    }

    public void setSetVersGendarmerie(boolean setVersGendarmerie) {
        this.setVersGendarmerie = setVersGendarmerie;
    }

}
