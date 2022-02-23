package epsi.tdd.grandrestaurant.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;
import epsi.tdd.grandrestaurant.model.TypeCommande;

public class ServeurTest {

    /**
     * ÉTANT DONNE un serveur dans un restaurant
     * QUAND il prend une commande de nourriture
     * ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce
     * restaurant
     */
    @Test
    public void commandeNourriture() {
        // ÉTANT DONNE un serveur dans un restaurant
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();

        // QUAND il prend une commande de nourriture
        Commande commande = new Commande(TypeCommande.NOURRITURE);
        serveur.prendreCommande(commande, mock(Table.class));

        // ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce
        // restaurant
        List<ICommande> tachesCuisine = restaurant.getTachesCuisine();
        assertThat(tachesCuisine)
                .as("cette commande apparaît dans la liste de tâches de la cuisine de ce restaurant")
                .contains(commande);
    }

    /**
     * ÉTANT DONNE un serveur dans un restaurant
     * QUAND il prend une commande de boissons
     * ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de
     * ce restaurant
     */
    @Test
    public void commandeBoisson() {
        // ÉTANT DONNE un serveur dans un restaurant
        Restaurant restaurant = new Restaurant();
        Serveur serveur = restaurant.addNewServeur();

        // QUAND il prend une commande de boissons
        Commande commande = new Commande(TypeCommande.BOISSONS);
        serveur.prendreCommande(commande, mock(Table.class));

        // ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de
        // ce restaurant
        List<ICommande> tachesCuisine = restaurant.getTachesCuisine();
        assertThat(commande)
                .as("cette commande n'apparaît pas dans la liste de tâches de la cuisine de ce restaurant")
                .isNotIn(tachesCuisine);
    }

    /**
     * ÉTANT DONNÉ un nouveau serveur
     * QUAND on récupére son chiffre d'affaires
     * ALORS celui-ci est à 0
     */
    @Test
    public void chiffreAffairesNouveauServeur() {
        // ÉTANT DONNÉ un nouveau serveur
        Serveur serveur = new Serveur();

        // QUAND on récupére son chiffre d'affaires
        double result = serveur.getChiffreAffaires();

        // ALORS celui-ci est à 0
        double expected = 0F;
        assertThat(result).isEqualTo(expected);
    }

    /**
     * ÉTANT DONNÉ un nouveau serveur
     * QUAND il prend une commande
     * ALORS son chiffre d'affaires est le montant de celle-ci
     */
    @Test
    public void chiffreAffairesApresCommande() {
        // ÉTANT DONNÉ un nouveau serveur
        Serveur serveur = new Serveur();
        serveur.setRestaurant(mock(Restaurant.class));

        // QUAND il prend une commande
        Commande commande = new Commande();
        commande.setMontant(Math.random() * 100); // set montant aléatoire entre 0 et 100
        serveur.prendreCommande(commande, mock(Table.class));

        // ALORS son chiffre d'affaires est le montant de celle-ci
        double result = serveur.getChiffreAffaires();
        double expected = commande.getMontant();
        assertThat(result).isEqualTo(expected);
    }

    /**
     * ÉTANT DONNÉ un serveur ayant déjà pris une commande
     * QUAND il prend une nouvelle commande
     * ALORS son chiffre d'affaires est la somme des deux commandes
     */
    @Test
    public void chiffreAffaireCommandeSuivante() {
        // ÉTANT DONNÉ un serveur ayant déjà pris une commande
        Serveur serveur = new Serveur();
        serveur.setRestaurant(mock(Restaurant.class));
        Commande commande = new Commande();
        commande.setMontant(Math.random() * 100); // set montant aléatoire entre 0 et 100
        serveur.prendreCommande(commande, mock(Table.class));

        // QUAND il prend une nouvelle commande
        Commande commande2 = new Commande();
        commande2.setMontant(Math.random() * 100); // set montant aléatoire entre 0 et 100
        serveur.prendreCommande(commande2, mock(Table.class));

        // ALORS son chiffre d'affaires est la somme des deux commandes
        double result = serveur.getChiffreAffaires();
        double expected = commande.getMontant() + commande2.getMontant();

        assertThat(result).isEqualTo(expected);
    }
}
