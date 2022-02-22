package epsi.tdd.grandrestaurant.builders;

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

    public Restaurant build() {
        return this.restaurant;
    }
}
