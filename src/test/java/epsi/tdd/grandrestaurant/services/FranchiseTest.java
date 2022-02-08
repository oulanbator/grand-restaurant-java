package epsi.tdd.grandrestaurant.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FranchiseTest {

    // TODO : Tester les modifications des variables du CA avec frameWork d'assertion

    /**
     * ÉTANT DONNÉ un restaurant ayant X serveurs
     * QUAND tous les serveurs prennent une commande d'un montant Y
     * ALORS le chiffre d'affaires de la franchise est X * Y
     * CAS(X = 0; X = 1; X = 2; X = 100)
     * CAS(Y = 1.0)
     */
    @Test
    public void chiffreAffaireFranchise() {
        // ÉTANT DONNÉ un restaurant ayant X serveurs
        Franchise franchise = new Franchise();
        Restaurant restaurant = franchise.newRestaurant();
        int X = 2;
        for (int i = 0; i < X; i++) {
            restaurant.addNewServeur();
        }
        System.out.println(restaurant.getServeurs().size());

        // QUAND tous les serveurs prennent une commande d'un montant Y
        // TODO : Faire un constructeur avec montant de la commande
        Commande commande = new Commande();
        double Y = 1;
        commande.setMontant(Y);

        for (Serveur serveur : restaurant.getServeurs()) {
            serveur.prendreCommande(commande, mock(Table.class));
        }
        // ALORS le chiffre d'affaires de la franchise est X * Y
        double result = franchise.getChiffreAffaire();
        double expected = X * Y;

        assertEquals(expected, result);
        // CAS(X = 0; X = 1; X = 2; X = 100)
        // CAS(Y = 1.0)
    }

    /**
     * ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
     * QUAND tous les serveurs prennent une commande d'un montant Z
     * ALORS le chiffre d'affaires de la franchise est X * Y * Z
     * CAS(X = 0; X = 1; X = 2; X = 1000)
     * CAS(Y = 0; Y = 1; Y = 2; Y = 1000)
     * CAS(Z = 1.0)
     */
     @Test
     public void chiffreAffaireGlobalFranchise() {
    //    ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
         int X = 5;
         Franchise franchise = new Franchise();
         for (int i = 0 ; i < X ; i++) {
             Restaurant restaurant = franchise.newRestaurant();
         }

         int Y = 1000;
         franchise.getRestaurants().forEach(restaurant -> {
             for (int i = 0; i < Y; i++) {
                 restaurant.addNewServeur();
             }
         });

         //    QUAND tous les serveurs prennent une commande d'un montant Z
         Commande commande = new Commande();
         double Z = 1;
         commande.setMontant(Z);
         franchise.getRestaurants().forEach(restaurant -> {
             restaurant.getServeurs().forEach(serveur -> {
                 serveur.prendreCommande(commande, mock(Table.class));
             });
         });

         //    ALORS le chiffre d'affaires de la franchise est X * Y * Z
         double result = franchise.getChiffreAffaire();
         double expected = X * Y * Z;

         assertEquals(expected, result);
         //    CAS(X = 0; X = 1; X = 2; X = 1000)
         //    CAS(Y = 0; Y = 1; Y = 2; Y = 1000)
         //    CAS(Z = 1.0)

     }
}
