package epsi.tdd.grandrestaurant.services;

import epsi.tdd.grandrestaurant.model.TypeCommande;

public class Commande {
    TypeCommande typeCommande;
    Table table;
    double montant;
    
    public Commande() {
    }
    
    public Commande(TypeCommande type) {
        this.typeCommande = type;
    }

    // GETTERS & SETTERS

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

    
}
