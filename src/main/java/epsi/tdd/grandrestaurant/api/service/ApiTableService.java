package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.TableRepository;
import epsi.tdd.grandrestaurant.services.Table;
import epsi.tdd.grandrestaurant.services.mapper.CycleAvoidingMappingContext;
import epsi.tdd.grandrestaurant.services.mapper.MapperRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ApiTableService {
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ApiServeurService apiServeurService;

    public TableEntity mapTableToEntity(Table table) {
        return MapperRestaurant.INSTANCE.tableToEntity(table, new CycleAvoidingMappingContext());
    }

    public Table mapEntityToTable(TableEntity table) {
        return MapperRestaurant.INSTANCE.entityToTable(table, new CycleAvoidingMappingContext());
    }

    public Optional<TableEntity> getTableById(Long id) {
        return this.tableRepository.findById(id);
    }

    public Iterable<TableEntity> getAllTables() {
        return tableRepository.findAll();
    }

    @Transactional
    public TableEntity saveTable(TableEntity table) {
        return this.tableRepository.save(table);
    }
}
