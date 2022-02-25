package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.ServeurRepository;
import epsi.tdd.grandrestaurant.services.IServeur;
import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.mapper.CycleAvoidingMappingContext;
import epsi.tdd.grandrestaurant.services.mapper.MapperRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ApiServeurService {
    @Autowired
    private ServeurRepository serveurRepository;
    @Autowired
    private ApiRestaurantService apiRestaurantService;

    public ServeurEntity mapServeurToEntity(IServeur serveur) {
        return MapperRestaurant.INSTANCE.serveurToEntity((Serveur) serveur, new CycleAvoidingMappingContext());
    }

    public Serveur mapEntityToServeur(ServeurEntity serveur) {
        return MapperRestaurant.INSTANCE.entityToServeur(serveur, new CycleAvoidingMappingContext());
    }

    public Optional<ServeurEntity> getServeurById(Long id) {
        return serveurRepository.findById(id);
    }

    public Iterable<ServeurEntity> getAllServeurs() {
        return serveurRepository.findAll();
    }

    @Transactional
    public ServeurEntity saveServeur(ServeurEntity serveur) {
        return serveurRepository.save(serveur);
    }
}
