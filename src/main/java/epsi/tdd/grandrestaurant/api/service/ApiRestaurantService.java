package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.RestaurantRepository;
import epsi.tdd.grandrestaurant.services.Restaurant;
import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;
import epsi.tdd.grandrestaurant.services.builders.RestaurantBuilder;
import epsi.tdd.grandrestaurant.services.mapper.CycleAvoidingMappingContext;
import epsi.tdd.grandrestaurant.services.mapper.MapperRestaurant;
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

    public RestaurantEntity mapRestaurantToEntity(Restaurant restaurant) {
        return MapperRestaurant.INSTANCE.restaurantToEntity(restaurant, new CycleAvoidingMappingContext());
    }

    public Restaurant mapEntityToRestaurant(RestaurantEntity restaurant) {
        return MapperRestaurant.INSTANCE.entityToRestaurant(restaurant, new CycleAvoidingMappingContext());
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
        RestaurantEntity eRestaurant = mapRestaurantToEntity(restaurant);
        saveRestaurant(eRestaurant);
        addServeurs(eRestaurant, restaurant.getServeurs());
        addTables(eRestaurant, restaurant.getTables());
    }

    @Transactional
    public void addServeurs(RestaurantEntity restaurant, List<Serveur> serveurs) {
        for (Serveur serveur : serveurs) {
            ServeurEntity eServeur = apiServeurService.mapServeurToEntity(serveur);
            eServeur.setRestaurant(restaurant);
            apiServeurService.saveServeur(eServeur);
        }
    }

    @Transactional
    public void addTables(RestaurantEntity restaurant, List<Table> tables) {
        for (Table table : tables) {
            TableEntity eTable = apiTableService.mapTableToEntity(table);
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
