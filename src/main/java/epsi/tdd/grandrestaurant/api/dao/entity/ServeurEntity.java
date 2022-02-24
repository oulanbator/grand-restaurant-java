package epsi.tdd.grandrestaurant.api.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ServeurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="idRestaurant")
    private RestaurantEntity restaurant;

    @OneToMany( targetEntity=TableEntity.class, mappedBy="serveurAffecte" )
    private List<TableEntity> tables;

    private boolean isMaitreHotel;
    private double chiffreAffaires;

    public ServeurEntity() {
    }

    public ServeurEntity(Long id, RestaurantEntity restaurant, List<TableEntity> tables, boolean isMaitreHotel, double chiffreAffaires) {
        this.id = id;
        this.restaurant = restaurant;
        this.tables = tables;
        this.isMaitreHotel = isMaitreHotel;
        this.chiffreAffaires = chiffreAffaires;
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

    public List<TableEntity> getTables() {
        return tables;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }

    public boolean isMaitreHotel() {
        return isMaitreHotel;
    }

    public void setMaitreHotel(boolean maitreHotel) {
        isMaitreHotel = maitreHotel;
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(double chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }
}
