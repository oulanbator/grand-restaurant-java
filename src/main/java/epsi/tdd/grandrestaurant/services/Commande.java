package epsi.tdd.grandrestaurant.services;

import epsi.tdd.grandrestaurant.model.TypeCommande;

public class Commande {
    TypeCommande typeCommande;
    Table table;
    
    public Commande() {
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

    
}
