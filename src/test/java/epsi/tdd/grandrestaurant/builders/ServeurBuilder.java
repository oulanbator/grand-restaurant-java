package epsi.tdd.grandrestaurant.builders;

import epsi.tdd.grandrestaurant.doubles.TableDummy;
import epsi.tdd.grandrestaurant.services.*;

import static org.mockito.Mockito.mock;

public class ServeurBuilder {
    private Serveur serveur;

    public ServeurBuilder() {
        this.serveur = new Serveur();
    }

    public ServeurBuilder withRestaurant(Restaurant restaurant) {
        this.serveur.setRestaurant(restaurant);
        return this;
    }

    public ServeurBuilder withCommande(Commande commande, ITable table) {
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
