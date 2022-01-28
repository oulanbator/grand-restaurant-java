package epsi.tdd.grandrestaurant.services;


public class Serveur {
    private Restaurant restaurant;
    private boolean isMaitreHotel;

    public Commande prendreCommandeNourriture() {
        Commande commande = new Commande();
        restaurant.newCommandeNourriture(commande);
        return commande;
    }

    public Commande prendreCommandeBoissons() {
        Commande commande = new Commande();
        return commande;
    }

    // GETTERS & SETTERS

    public boolean isMaitreHotel() {
        return isMaitreHotel;
    }

    public void setMaitreHotel(boolean isMaitreHotel) {
        this.isMaitreHotel = isMaitreHotel; 
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    } 
    
}
