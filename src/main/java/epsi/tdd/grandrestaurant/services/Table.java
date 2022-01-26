package epsi.tdd.grandrestaurant.services;

public class Table {
    private Serveur serveurAffecte;

    public Serveur getServeur() {
        return serveurAffecte;
    }

    public void setServeur(Serveur serveur) {
        this.serveurAffecte = serveur;
    }
    
}
