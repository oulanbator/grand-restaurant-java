package epsi.tdd.grandrestaurant.services;

import java.util.Date;

import epsi.tdd.grandrestaurant.model.TypeCommande;

public class Commande {
    TypeCommande typeCommande;
    Table table;
    double montant;
    boolean epinglee = false;
    Date dateEpinglage;
    private boolean versGendarmerie = false;
    boolean bTransmise;

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

    public boolean isVersGendarmerie() {
        return versGendarmerie;
    }

    public void setVersGendarmerie(boolean versGendarmerie) {
        this.versGendarmerie = versGendarmerie;
    }

    public boolean isbTransmise() {
        return bTransmise;
    }

    public void setbTransmise(boolean bTransmise) {
        this.bTransmise = bTransmise;
    }

}
