package epsi.tdd.grandrestaurant.api.dao.entity;

import javax.persistence.*;

@Entity
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="idRestaurant")
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name="idServeur")
    private ServeurEntity serveurAffecte;

    private boolean isLibre;

    public TableEntity() {
    }

    public TableEntity(Long id, RestaurantEntity restaurant, ServeurEntity serveurAffecte, boolean isLibre) {
        this.id = id;
        this.restaurant = restaurant;
        this.serveurAffecte = serveurAffecte;
        this.isLibre = isLibre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public ServeurEntity getServeurAffecte() {
        return serveurAffecte;
    }

    public void setServeurAffecte(ServeurEntity serveurAffecte) {
        this.serveurAffecte = serveurAffecte;
    }

    public boolean isLibre() {
        return isLibre;
    }

    public void setLibre(boolean libre) {
        isLibre = libre;
    }
}
