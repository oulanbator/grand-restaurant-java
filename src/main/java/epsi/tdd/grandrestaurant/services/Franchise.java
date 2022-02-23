package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Franchise {
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Plat> menu = new ArrayList<>();
    // private double chiffreAffaire = 0;

    public Restaurant newRestaurant() {
        Restaurant restaurant = new Restaurant(menu);
        restaurants.add(restaurant);
        return restaurant;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setMenu(menu);
        restaurants.add(restaurant);
    }

    public Restaurant newFiliale() {
        Restaurant restaurant = newRestaurant();
        restaurant.setFiliale(true);
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

    public void addPlatToMenu(Plat plat) {
        this.menu.add(plat);
        restaurants.forEach(restaurant -> restaurant.addPlatToMenu(plat));
    }

    public void modifiePrixDuPlat(Plat plat, double newPrix) {
        for (Plat platDuMenu : menu) {
            if (Objects.equals(platDuMenu.getId(), plat.getId())) {
                platDuMenu.setPrix(newPrix);
                restaurants.forEach(restaurant -> {
                    if (restaurant.isFiliale()) {
                        restaurant.modifiePrixDuPlat(plat, newPrix);
                    }
                });
            }
        }
    }

    // GETTERS AND SETTERS

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Plat getPlat(Plat plat) {
        for (Plat platDuMenu : menu) {
            if (Objects.equals(platDuMenu.getId(), plat.getId())) {
                return  platDuMenu;
            }
        }
        return null;
    }
}
