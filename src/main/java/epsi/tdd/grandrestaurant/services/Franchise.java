package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;

public class Franchise {
    private List<Restaurant> restaurants = new ArrayList<>();
    // private double chiffreAffaire = 0;

    public Restaurant newRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurants.add(restaurant);
        return restaurant;
    }

    public double getChiffreAffaire() {
        double chiffreAffaire = 0;
        for (Restaurant restaurant : restaurants) {
            for (Serveur serveur : restaurant.getServeurs()) {
                chiffreAffaire += serveur.getChiffreAffaires();
            }
        }
        return chiffreAffaire;
    }
}
