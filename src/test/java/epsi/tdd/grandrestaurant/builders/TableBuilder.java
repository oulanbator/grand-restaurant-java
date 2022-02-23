package epsi.tdd.grandrestaurant.builders;

import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;

public class TableBuilder {
    private Table table;

    public TableBuilder() {
        this.table = new Table();
    }

    public TableBuilder withServeur(Serveur serveur) {
        this.table.setServeur(serveur);
        return this;
    }

    public Table build() {
        return this.table;
    }
}
