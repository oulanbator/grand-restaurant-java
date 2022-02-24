package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.RestaurantRepository;
import epsi.tdd.grandrestaurant.services.Restaurant;
import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ApiRestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ApiServeurService apiServeurService;
    @Autowired
    private ApiTableService apiTableService;

    @Transactional
    public RestaurantEntity buildRestaurantEntity(Restaurant restaurant) {
        RestaurantEntity eRestaurant = new RestaurantEntity();
        eRestaurant.setFiliale(restaurant.isFiliale());
        eRestaurant.setServiceEnCours(restaurant.isServiceEnCours());
        // Serveurs
        for (Serveur serveur : restaurant.getServeurs()) {
            Optional<ServeurEntity> optServeur = apiServeurService.getServeurById(serveur.getId());
            if (optServeur.isPresent()) {
                eRestaurant.getServeurs().add(optServeur.get());
            } else {
                ServeurEntity eServeur = apiServeurService.buildServeurEntity(serveur);
                eServeur.setRestaurant(eRestaurant);
                eRestaurant.getServeurs().add(eServeur);
            }
        }
        // Tables
        for (Table table : restaurant.getTables()) {
            Optional<TableEntity> optTable = apiTableService.getTableById(table.getId());
            if (optTable.isPresent()) {
                eRestaurant.getTables().add(optTable.get());
            } else {
                TableEntity eTable = apiTableService.buildTableEntity(table);
                eTable.setRestaurant(eRestaurant);
                eRestaurant.getTables().add(eTable);
            }
        }
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

    public void createRestaurantPool() {
        RestaurantEntity restaurant1 = new RestaurantEntity();
        restaurant1.setFiliale(true);
        restaurant1.setServiceEnCours(false);
        saveRestaurant(restaurant1);

        RestaurantEntity restaurant2 = new RestaurantEntity();
        restaurant2.setFiliale(false);
        restaurant2.setServiceEnCours(true);
        saveRestaurant(restaurant2);

        RestaurantEntity restaurant3 = new RestaurantEntity();
        restaurant3.setFiliale(false);
        restaurant3.setServiceEnCours(false);
        saveRestaurant(restaurant3);
    }
}
