package epsi.tdd.grandrestaurant.services.builders;

import epsi.tdd.grandrestaurant.services.*;

public class ServeurBuilder {
    private Serveur serveur;

    public ServeurBuilder() {
        this.serveur = new Serveur();
    }

    public epsi.tdd.grandrestaurant.services.builders.ServeurBuilder withRestaurant(Restaurant restaurant) {
        this.serveur.setRestaurant(restaurant);
        return this;
    }

    public epsi.tdd.grandrestaurant.services.builders.ServeurBuilder withCommande(Commande commande, ITable table) {
        if (this.serveur.getRestaurant() != null) {
            this.serveur.prendreCommande(commande, table);
        } else {
            this.serveur.setRestaurant(new Restaurant());
            this.serveur.prendreCommande(commande, table);
        }
        return this;
    }

    public Serveur build() {
        return this.serveur;
    }
}
