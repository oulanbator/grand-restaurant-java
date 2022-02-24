package epsi.tdd.grandrestaurant.services.builders;

import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;

public class TableBuilder {
    private Table table;

    public TableBuilder() {
        this.table = new Table();
    }

    public epsi.tdd.grandrestaurant.services.builders.TableBuilder withServeur(Serveur serveur) {
        this.table.setServeur(serveur);
        return this;
    }

    public Table build() {
        return this.table;
    }
}
