package epsi.tdd.grandrestaurant.services.builders;

import epsi.tdd.grandrestaurant.services.Client;
import epsi.tdd.grandrestaurant.services.Commande;
import epsi.tdd.grandrestaurant.services.Restaurant;

public class RestaurantBuilder {
    private Restaurant restaurant;

    public RestaurantBuilder() {
        this.restaurant = new Restaurant();
    }

    public RestaurantBuilder withTables(int nbTables) {
        this.restaurant.createTables(nbTables);
        return this;
    }

    public RestaurantBuilder withServeurs(int nbServeurs) {
        for (int i = 0 ; i < nbServeurs ; i++) {
            this.restaurant.addNewServeur();
        }
        return this;
    }

    public RestaurantBuilder withClient(Client client) {
        this.restaurant.entreeClient(client);
        return this;
    }

    public RestaurantBuilder withCommandeATransmettre(Commande commande) {
        this.restaurant.addCommandeTransmettre(commande);
        return this;
    }

    public RestaurantBuilder withServiceStarted() {
        this.restaurant.startService();
        return this;
    }

    public RestaurantBuilder withFilialeStatus(boolean isFiliale) {
        if (isFiliale) {
            this.restaurant.setFiliale(true);
        }
        return this;
    }

    public Restaurant build() {
        return this.restaurant;
    }
}
