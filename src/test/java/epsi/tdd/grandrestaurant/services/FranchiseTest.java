package epsi.tdd.grandrestaurant.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import epsi.tdd.grandrestaurant.services.builders.CommandeBuilder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FranchiseTest {

    // TODO : Tester les modifications des variables du CA avec frameWork
    // d'assertion

    /**
     * ÉTANT DONNÉ un restaurant ayant X serveurs
     * QUAND tous les serveurs prennent une commande d'un montant Y
     * ALORS le chiffre d'affaires de la franchise est X * Y
     * CAS(X = 0; X = 1; X = 2; X = 100)
     * CAS(Y = 1.0)
     */
    @ParameterizedTest(name = "CA : {0} x 1 devrait être égal à {0}")
    @ValueSource(ints = {0, 1, 2, 1000})
    public void chiffreAffaireFranchise(int X) {
        // ÉTANT DONNÉ un restaurant ayant X serveurs
        Franchise franchise = new Franchise();
        Restaurant restaurant = franchise.newRestaurant();
        for (int i = 0; i < X; i++) {
            restaurant.addNewServeur();
        }

        // QUAND tous les serveurs prennent une commande d'un montant Y (toujours égal à 1)
        double Y = 1;
        Commande commande = new CommandeBuilder()
                .withMontant(Y)
                .build();

        for (Serveur serveur : restaurant.getServeurs()) {
            serveur.prendreCommande(commande, mock(Table.class));
        }
        // ALORS le chiffre d'affaires de la franchise est X * Y
        double result = franchise.getChiffreAffaire();
        double expected = X * Y;

        assertThat(result).isEqualTo(expected);
    }

    /**
     * ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
     * QUAND tous les serveurs prennent une commande d'un montant Z
     * ALORS le chiffre d'affaires de la franchise est X * Y * Z
     * CAS(X = 0; X = 1; X = 2; X = 100)
     * CAS(Y = 0; Y = 1; Y = 2; Y = 100)
     * CAS(Z = 1.0)
     */
    @ParameterizedTest(name = "CA Franchise : {0} Restaurants et {1} Serveurs")
    @CsvSource({"0,0", "0,1", "0,2", "0,100",
                "1,0", "1,1", "1,2", "1,100",
                "2,0", "2,1", "2,2", "2,100",
                "100,0", "100,1", "100,2", "100,100"})
    public void chiffreAffaireGlobalFranchise(int X, int Y) {
        // ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
        Franchise franchise = new Franchise();
        for (int i = 0; i < X; i++) {
            franchise.newRestaurant();
        }

        franchise.getRestaurants().forEach(restaurant -> {
            for (int i = 0; i < Y; i++) {
                restaurant.addNewServeur();
            }
        });

        // QUAND tous les serveurs prennent une commande d'un montant Z
        double Z = 1;
        Commande commande = new CommandeBuilder()
                .withMontant(Z)
                .build();
                
        franchise.getRestaurants().forEach(restaurant -> {
            restaurant.getServeurs().forEach(serveur -> {
                serveur.prendreCommande(commande, mock(Table.class));
            });
        });

        // ALORS le chiffre d'affaires de la franchise est X * Y * Z
        double CA = franchise.getChiffreAffaire();
        assertThat(CA).isEqualTo(X * Y * Z);
    }
}
