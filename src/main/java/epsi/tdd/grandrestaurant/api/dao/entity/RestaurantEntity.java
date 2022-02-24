package epsi.tdd.grandrestaurant.api.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany( targetEntity=TableEntity.class, mappedBy="restaurant" )
    private List<TableEntity> tables = new ArrayList<>();

    @OneToMany( targetEntity=ServeurEntity.class, mappedBy="restaurant" )
    private List<ServeurEntity> serveurs = new ArrayList<>();

    private boolean serviceEnCours = false;
    private boolean isFiliale;

    public RestaurantEntity() {
    }

    public RestaurantEntity(Long id, List<TableEntity> tables, List<ServeurEntity> serveurs, boolean serviceEnCours, boolean isFiliale) {
        this.id = id;
        this.tables = tables;
        this.serveurs = serveurs;
        this.serviceEnCours = serviceEnCours;
        this.isFiliale = isFiliale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TableEntity> getTables() {
        return tables;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }

    public List<ServeurEntity> getServeurs() {
        return serveurs;
    }

    public void setServeurs(List<ServeurEntity> serveurs) {
        this.serveurs = serveurs;
    }

    public boolean isServiceEnCours() {
        return serviceEnCours;
    }

    public void setServiceEnCours(boolean serviceEnCours) {
        this.serviceEnCours = serviceEnCours;
    }

    public boolean isFiliale() {
        return isFiliale;
    }

    public void setFiliale(boolean filiale) {
        isFiliale = filiale;
    }

}
