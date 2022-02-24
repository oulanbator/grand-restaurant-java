package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.TableEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.TableRepository;
import epsi.tdd.grandrestaurant.services.Table;
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

    @Transactional
    public TableEntity buildTableEntity(Table table) {
        TableEntity eTable = new TableEntity();
        if (table.getId() != null) {
            eTable.setId(table.getId());
        }
        eTable.setLibre(table.isLibre());
//        // Serveur
//        Optional<ServeurEntity> optServeur = apiServeurService.getServeurById(table.getServeur().getId());
//        if (optServeur.isPresent()) {
//            eTable.setServeurAffecte(optServeur.get());
//        } else {
//            ServeurEntity eServeur = apiServeurService.buildServeurEntity(table.getServeur());
//            eServeur.getTables().add(eTable);
//            eTable.setServeurAffecte(eServeur);
//        }
        return eTable;
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
