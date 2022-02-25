package epsi.tdd.grandrestaurant.integration;

import epsi.tdd.grandrestaurant.GrandRestaurantApplication;
import epsi.tdd.grandrestaurant.api.dao.entity.RestaurantEntity;
import epsi.tdd.grandrestaurant.api.dao.repository.RestaurantRepository;
import epsi.tdd.grandrestaurant.api.service.ApiRestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ApiRestaurantService apiRestaurantService;

    @Test
    public void int_createRestaurantPool() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/create-restaurants"));
        mvc.perform(MockMvcRequestBuilders.get("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        apiRestaurantService.getAllRestaurants().forEach(restaurant -> {
            restaurantEntities.add(restaurant);
        });
        assertThat(restaurantEntities.size() > 0).isTrue();
    }
}
