package epsi.tdd.grandrestaurant.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

import epsi.tdd.grandrestaurant.services.builders.CommandeBuilder;
import epsi.tdd.grandrestaurant.services.builders.ServeurBuilder;
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
        Commande commande = new CommandeBuilder()
                .withType(TypeCommande.NOURRITURE)
                .build();
                
        serveur.prendreCommande(commande, mock(Table.class));

        // ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce
        // restaurant
        List<ICommande> tachesCuisine = restaurant.getTachesCuisine();
        assertThat(tachesCuisine).contains(commande);
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
        Commande commande = new CommandeBuilder()
                .withType(TypeCommande.BOISSONS)
                .build();

        serveur.prendreCommande(commande, mock(Table.class));

        // ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de
        // ce restaurant
        List<ICommande> tachesCuisine = restaurant.getTachesCuisine();
        assertThat(commande).isNotIn(tachesCuisine);
    }

    /**
     * ÉTANT DONNÉ un nouveau serveur
     * QUAND on récupére son chiffre d'affaires
     * ALORS celui-ci est à 0
     */
    @Test
    public void chiffreAffairesNouveauServeur() {
        // ÉTANT DONNÉ un nouveau serveur
        Serveur serveur = new ServeurBuilder().build();

        // QUAND on récupére son chiffre d'affaires
        double CA = serveur.getChiffreAffaires();

        // ALORS celui-ci est à 0
        assertThat(CA).isEqualTo(0);
    }

    /**
     * ÉTANT DONNÉ un nouveau serveur
     * QUAND il prend une commande
     * ALORS son chiffre d'affaires est le montant de celle-ci
     */
    @Test
    public void chiffreAffairesApresCommande() {
        // ÉTANT DONNÉ un nouveau serveur
        Serveur serveur = new ServeurBuilder()
                .withRestaurant(mock(Restaurant.class))
                .build();

        // QUAND il prend une commande
        Commande commande = new CommandeBuilder()
                .withMontant(Math.random() * 100) // set montant aléatoire entre 0 et 100
                .build();

        serveur.prendreCommande(commande, mock(Table.class));

        // ALORS son chiffre d'affaires est le montant de celle-ci
        double CA = serveur.getChiffreAffaires();
        double montantCommande = commande.getMontant();

        assertThat(CA).isEqualTo(montantCommande);
    }

    /**
     * ÉTANT DONNÉ un serveur ayant déjà pris une commande
     * QUAND il prend une nouvelle commande
     * ALORS son chiffre d'affaires est la somme des deux commandes
     */
    @Test
    public void chiffreAffaireCommandeSuivante() {
        // ÉTANT DONNÉ un serveur ayant déjà pris une commande
        Serveur serveur = new ServeurBuilder()
                .withRestaurant(mock(Restaurant.class))
                .build();

        Commande commande = new CommandeBuilder()
                .withMontant(Math.random() * 100) // set montant aléatoire entre 0 et 100
                .build();

        serveur.prendreCommande(commande, mock(Table.class));

        // QUAND il prend une nouvelle commande
        Commande commande2 = new CommandeBuilder()
                .withMontant(Math.random() * 100) // set montant aléatoire entre 0 et 100
                .build();

        serveur.prendreCommande(commande2, mock(Table.class));

        // ALORS son chiffre d'affaires est la somme des deux commandes
        double CA = serveur.getChiffreAffaires();
        double sommeCommandes = commande.getMontant() + commande2.getMontant();

        assertThat(CA).isEqualTo(sommeCommandes);
    }
}
