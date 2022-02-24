package epsi.tdd.grandrestaurant.api.dao.repository;

import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends PagingAndSortingRepository<TableEntity, Long> {
}
