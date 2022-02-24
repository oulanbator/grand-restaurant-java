package epsi.tdd.grandrestaurant.api.dao.repository;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<RestaurantEntity, Long> {

}
