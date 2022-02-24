package epsi.tdd.grandrestaurant.api.dao.repository;

import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServeurRepository extends PagingAndSortingRepository<ServeurEntity, Long> {

}
