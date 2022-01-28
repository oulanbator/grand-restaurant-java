package epsi.tdd.grandrestaurant.services;


public class Serveur {
    private Restaurant restaurant;
    private boolean isMaitreHotel;

    public boolean isMaitreHotel() {
        return isMaitreHotel;
    }

    public void setMaitreHotel(boolean isMaitreHotel) {
        this.isMaitreHotel = isMaitreHotel; 
    }

    public Commande prendreCommandeNourriture() {
        Commande commande = new Commande();
        restaurant.newCommandeNourriture(commande);
        return commande;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
}
