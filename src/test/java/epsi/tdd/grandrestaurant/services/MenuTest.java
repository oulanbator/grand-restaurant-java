package epsi.tdd.grandrestaurant.services;

import org.junit.jupiter.api.Test;

import epsi.tdd.grandrestaurant.services.builders.PlatBuilder;

import static org.assertj.core.api.Assertions.*;

class MenuTest {

    /**
     * ÉTANT DONNE un restaurant ayant le statut de filiale d'une franchise
     * ET une franchise définissant un menu ayant un plat
     * QUAND la franchise modifie le prix du plat
     * ALORS le prix du plat dans le menu du restaurant est celui défini par la
     * franchise
     */
    @Test
    public void prixDuPlatFiliale() {
        // ÉTANT DONNE un restaurant ayant le statut de filiale d'une franchise
        Franchise franchise = new Franchise();
        Restaurant restaurant = franchise.newFiliale();

        // ET une franchise définissant un menu ayant un plat
        Plat plat = new Plat("Burger", 10.0);
        franchise.addPlatToMenu(plat);

        // QUAND la franchise modifie le prix du plat
        double newPrix = Math.random() * 40;
        franchise.modifiePrixDuPlat(plat, newPrix);

        // ALORS le prix du plat dans le menu du restaurant est celui défini par la
        // franchise
        double prixPlatRestaurant = restaurant.getPlat(plat).getPrix();
        double prixPlatFranchise = franchise.getPlat(plat).getPrix();

        assertThat(prixPlatRestaurant).isEqualTo(prixPlatFranchise);
    }

    /**
     * ÉTANT DONNE un restaurant appartenant à une franchise et définissant un menu
     * ayant un plat
     * ET une franchise définissant un menu ayant le même plat
     * QUAND la franchise modifie le prix du plat
     * ALORS le prix du plat dans le menu du restaurant reste inchangé
     */
    @Test
    public void prixDuPlatFranchise() {
        // ÉTANT DONNE un restaurant appartenant à une franchise et définissant 
        // un menu ayant un plat
        Franchise franchise = new Franchise();
        Restaurant restaurant = franchise.newRestaurant();

        double prixInitial = 10.90;
        Plat plat = new PlatBuilder()
            .withName("Burger")
            .withPrixInitial(prixInitial)
            .build();

        restaurant.addPlatToMenu(plat);

        // ET une franchise définissant un menu ayant le même plat
        franchise.addPlatToMenu(plat);

        // QUAND la franchise modifie le prix du plat
        franchise.modifiePrixDuPlat(plat, 15.50);

        // ALORS le prix du plat dans le menu du restaurant reste inchangé
        double prixFinal = restaurant.getPlat(plat).getPrix();
        assertThat(prixInitial).isEqualTo(prixFinal);
    }

    /**
     * ÉTANT DONNE un restaurant appartenant à une franchise et définissant un menu
     * ayant un plat
     * QUAND la franchise ajoute un nouveau plat
     * ALORS la carte du restaurant propose le premier plat au prix du restaurant et
     * le second au prix de la franchise
     */
    @Test
    public void prixDesPlatsFranchiseNouveauPlat() {
        // ÉTANT DONNE un restaurant appartenant à une franchise et définissant un menu
        // ayant un plat
        Franchise franchise = new Franchise();
        Restaurant restaurant = franchise.newRestaurant();
        
        double prixPlatRestaurant = 10.90;
        Plat plat1 = new PlatBuilder()
            .withName("Burger")
            .withPrixInitial(prixPlatRestaurant)
            .build();

        restaurant.addPlatToMenu(plat1);

        // QUAND la franchise ajoute un nouveau plat
        double prixPlatFranchise = 12.50;
        Plat plat2 = new PlatBuilder()
            .withName("Pizza")
            .withPrixInitial(prixPlatFranchise)
            .build();

        franchise.addPlatToMenu(plat2);

        // ALORS la carte du restaurant propose le premier plat au prix du restaurant et
        // le second au prix de la franchise
        double prixPlat1 = restaurant.getPlat(plat1).getPrix();
        double prixPlat2 = restaurant.getPlat(plat2).getPrix();

        assertThat(prixPlat1 == prixPlatRestaurant
                && prixPlat2 == prixPlatFranchise).isTrue();
    }
}
