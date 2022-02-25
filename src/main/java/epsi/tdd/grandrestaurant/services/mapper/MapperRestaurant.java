package epsi.tdd.grandrestaurant.services.mapper;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.services.Restaurant;
import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperRestaurant {
    MapperRestaurant INSTANCE = Mappers.getMapper( MapperRestaurant.class );

    Restaurant entityToRestaurant(RestaurantEntity restaurantEntity, @Context CycleAvoidingMappingContext context);
    RestaurantEntity restaurantToEntity(Restaurant restaurant, @Context CycleAvoidingMappingContext context);

    Table entityToTable(TableEntity tableEntity, @Context CycleAvoidingMappingContext context);
    TableEntity tableToEntity(Table Table, @Context CycleAvoidingMappingContext context);

    Serveur entityToServeur(ServeurEntity serveurEntity, @Context CycleAvoidingMappingContext context);
    ServeurEntity serveurToEntity(Serveur serveur, @Context CycleAvoidingMappingContext context);
}
