package epsi.tdd.grandrestaurant.services.builders;

import epsi.tdd.grandrestaurant.services.Plat;

public class PlatBuilder {
    private Plat plat;
    
    public PlatBuilder() {
        this.plat = new Plat();
    }

    public epsi.tdd.grandrestaurant.services.builders.PlatBuilder withName(String name) {
        this.plat.setId(name);
        return this;
    }

    public epsi.tdd.grandrestaurant.services.builders.PlatBuilder withPrixInitial(double prix) {
        this.plat.setPrix(prix);
        return this;
    } 

    public Plat build() {
        return this.plat;
    }
}
