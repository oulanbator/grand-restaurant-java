package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.RestaurantRepository;
import epsi.tdd.grandrestaurant.services.Restaurant;
import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;
import epsi.tdd.grandrestaurant.services.builders.RestaurantBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ApiRestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ApiServeurService apiServeurService;
    @Autowired
    private ApiTableService apiTableService;

    public RestaurantEntity buildRestaurantEntity(Restaurant restaurant) {
        RestaurantEntity eRestaurant = new RestaurantEntity();
        eRestaurant.setFiliale(restaurant.isFiliale());
        eRestaurant.setServiceEnCours(restaurant.isServiceEnCours());
        return eRestaurant;
    }

    public Optional<RestaurantEntity> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Iterable<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Transactional
    public RestaurantEntity saveRestaurant(RestaurantEntity restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @Transactional
    public void createRestaurantPool() {
        createRestaurant(3, 1, false, true);
        createRestaurant(10, 4, true, false);
        createRestaurant(5, 2, false, false);
        createRestaurant(12, 3, true, true);
    }

    @Transactional
    public void createRestaurant(int tables, int serveurs, boolean isFiliale, boolean isServiceEnCours) {
        Restaurant restaurant = new RestaurantBuilder()
                .withTables(tables)
                .withServeurs(serveurs)
                .withServiceStarted()
                .withFilialeStatus(isFiliale)
                .build();
        RestaurantEntity eRestaurant = saveRestaurant(buildRestaurantEntity(restaurant));
        addServeurs(eRestaurant, restaurant.getServeurs());
        addTables(eRestaurant, restaurant.getTables());
    }

    @Transactional
    public void addServeurs(RestaurantEntity restaurant, List<Serveur> serveurs) {
        for (Serveur serveur : serveurs) {
            ServeurEntity eServeur = apiServeurService.buildServeurEntity(serveur);
            eServeur.setRestaurant(restaurant);
            apiServeurService.saveServeur(eServeur);
        }
    }

    @Transactional
    public void addTables(RestaurantEntity restaurant, List<Table> tables) {
        for (Table table : tables) {
            TableEntity eTable = apiTableService.buildTableEntity(table);
            eTable.setRestaurant(restaurant);
            apiTableService.saveTable(eTable);
        }
    }

    public Iterable<TableEntity> getTablesRestaurant(int restauId) {
        Optional<RestaurantEntity> restaurant = getRestaurantById((long) restauId);
        if (restaurant.isPresent()) {
            return restaurant.get().getTables();
        }
        return null;
    }

    public Iterable<ServeurEntity> getServeursRestaurant(int restauId) {
        Optional<RestaurantEntity> restaurant = getRestaurantById((long) restauId);
        if (restaurant.isPresent()) {
            return restaurant.get().getServeurs();
        }
        return null;
    }
}
