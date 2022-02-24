package epsi.tdd.grandrestaurant.api.service;

import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.entity.ServeurEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.ServeurRepository;
import epsi.tdd.grandrestaurant.services.IServeur;
import epsi.tdd.grandrestaurant.services.Serveur;
import epsi.tdd.grandrestaurant.services.Table;
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

    @Transactional
    public ServeurEntity buildServeurEntity(IServeur serveur) {
        ServeurEntity eServeur = new ServeurEntity();
        eServeur.setMaitreHotel(serveur.isMaitreHotel());
        eServeur.setChiffreAffaires(serveur.getChiffreAffaires());
        if (serveur.getId() != null) {
            eServeur.setId(serveur.getId());
        }
//        // Restaurant
//        Optional<RestaurantEntity> optRestaurant = apiRestaurantService.getRestaurantById(serveur.getRestaurant().getId());
//        if (optRestaurant.isPresent()) {
//            eServeur.setRestaurant(optRestaurant.get());
//        } else {
//            RestaurantEntity eRestaurant = apiRestaurantService.buildRestaurantEntity(serveur.getRestaurant());
//            eRestaurant.getServeurs().add(eServeur);
//            eServeur.setRestaurant(eRestaurant);
//        }
        return eServeur;
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
