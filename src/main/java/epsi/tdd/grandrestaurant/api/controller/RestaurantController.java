package epsi.tdd.grandrestaurant.api.controller;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.api.service.ApiRestaurantService;
import epsi.tdd.grandrestaurant.api.service.ApiServeurService;
import epsi.tdd.grandrestaurant.api.service.ApiTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    private ApiRestaurantService apiRestaurantService;
    @Autowired
    private ApiTableService apiTableService;
    @Autowired
    private ApiServeurService apiServeurService;

    @GetMapping("serveurs")
    public Iterable<ServeurEntity> getServeurs() {
        return apiServeurService.getAllServeurs();
    }

    @GetMapping("restaurants")
    public Iterable<RestaurantEntity> getRestaurants() {
        return apiRestaurantService.getAllRestaurants();
    }

    @GetMapping("create-restaurants")
    public String createRestaurants() {
        apiRestaurantService.createRestaurantPool();
        return "Succès !";
    }

    @GetMapping("tables")
    public Iterable<TableEntity> getTables() {
        return apiTableService.getAllTables();
    }

    @GetMapping("restaurant/tables")
    public Iterable<TableEntity> getTablesRestaurant(@RequestParam int restauId) {
        return apiRestaurantService.getTablesRestaurant(restauId);
    }
    @GetMapping("restaurant/serveurs")
    public Iterable<ServeurEntity> getServeursRestaurant(@RequestParam int restauId) {
        return apiRestaurantService.getServeursRestaurant(restauId);
    }
}
