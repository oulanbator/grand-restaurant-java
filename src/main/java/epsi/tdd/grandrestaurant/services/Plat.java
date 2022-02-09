package epsi.tdd.grandrestaurant.services;

public class Plat {
    private String id;
    private double prix;

    public Plat() {
    }

    public Plat(String identifiant, double prix) {
        this.id = identifiant;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
