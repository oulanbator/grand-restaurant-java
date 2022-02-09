package epsi.tdd.grandrestaurant.services;

public class Plat {
    private int id;
    private double prix;

    public Plat() {
    }

    public Plat(int identifiant, double prix) {
        this.id = identifiant;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
