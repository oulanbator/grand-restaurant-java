package epsi.tdd.grandrestaurant.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    /**
     * ÉTANT DONNE un restaurant ayant le statut de filiale d'une franchise
     * ET une franchise définissant un menu ayant un plat
     * QUAND la franchise modifie le prix du plat
     * ALORS le prix du plat dans le menu du restaurant est celui défini par la franchise
     */
    @Test
    public void prixDuPlatFiliale() {
        // ÉTANT DONNE un restaurant ayant le statut de filiale d'une franchise
        Franchise franchise = new Franchise();
        Restaurant restaurant = franchise.newFiliale();

        // ET une franchise définissant un menu ayant un plat
        Plat plat = new Plat(01, 10.0);
        franchise.addPlatToMenu(plat);

        // QUAND la franchise modifie le prix du plat
        double newPrix = Math.random() * 40;
        franchise.modifiePrixDuPlat(plat, newPrix);

        //ALORS le prix du plat dans le menu du restaurant est celui défini par la franchise
        double prixPlatRestaurant = restaurant.getPlat(plat).getPrix();
        double prixPlatFranchise = franchise.getPlat(plat).getPrix();


        // TODO : ça passe... mais je pense que ya anguille sous roche... ça devrait pas vraiment passer..
        //  ça devrait être plus clair quand on va faire le test suivant dans ce scope...
        assertEquals(prixPlatRestaurant, prixPlatFranchise);
    }
}
