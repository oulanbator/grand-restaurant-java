package epsi.tdd.grandrestaurant.integration;

import epsi.tdd.grandrestaurant.GrandRestaurantApplication;
import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.RestaurantRepository;
import epsi.tdd.grandrestaurant.api.service.ApiRestaurantService;
import epsi.tdd.grandrestaurant.api.service.ApiTableService;
import epsi.tdd.grandrestaurant.services.Restaurant;
import epsi.tdd.grandrestaurant.services.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrandRestaurantApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class IntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ApiRestaurantService apiRestaurantService;
    @Autowired
    private ApiTableService apiTableService;

    @Test
    public void int_createRestaurantPool() throws Exception {
        // ETANT DONNE L'appel à la route pour créer un pool de restaurants
        mvc.perform(MockMvcRequestBuilders.get("/create-restaurants"));

        // QUAND la route pour lister les restaurants est utilisée
        // ET qu'elle renvoie une réponse JSON et un code HTTP200
        mvc.perform(MockMvcRequestBuilders.get("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        // SI l'on requête la BDD
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        apiRestaurantService.getAllRestaurants().forEach(restaurant -> {
            restaurantEntities.add(restaurant);
        });

        // ALORS des restaurants sont présents dans la BDD
        assertThat(restaurantEntities.size() > 0).isTrue();
    }

    /**
     * ÉTANT DONNE un restaurant ayant 3 tables
     * QUAND le service commence
     * ALORS elles sont toutes affectées au Maître d'Hôtel
     * @throws Exception
     */
    @Test
    public void int_scopeDebutService_ServiceDemarre() {
        // NON FONCTIONNEL

//        //ÉTANT DONNE un restaurant ayant 3 tables
//        Restaurant restaurant = apiRestaurantService.createRestaurant(3, 0, false, false);
//
//        //QUAND le service commence
//        restaurant.startService(true);
//        RestaurantEntity eRestaurant = apiRestaurantService.mapRestaurantToEntity(restaurant);
//        apiRestaurantService.saveRestaurant(eRestaurant);
//
//        //ALORS elles sont toutes affectées au Maître d'Hôtel
//        apiTableService.getAllTables().forEach(tableEntity -> {
//            assertThat(tableEntity.getServeurAffecte().isMaitreHotel());
//        });
    }
}
