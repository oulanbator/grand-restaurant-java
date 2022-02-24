package epsi.tdd.grandrestaurant.services.builders;

import epsi.tdd.grandrestaurant.model.TypeCommande;
import epsi.tdd.grandrestaurant.services.Commande;

public class CommandeBuilder {
    private Commande commande;

    public CommandeBuilder() {
        this.commande = new Commande();
    }

    public epsi.tdd.grandrestaurant.services.builders.CommandeBuilder withType(TypeCommande type) {
        this.commande.setTypeCommande(type);
        return this;
    }

    public epsi.tdd.grandrestaurant.services.builders.CommandeBuilder withMontant(double montant) {
        this.commande.setMontant(montant);
        return this;
    }

    public Commande build() {
        return this.commande;
    }   
}
