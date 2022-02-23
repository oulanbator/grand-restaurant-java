package epsi.tdd.grandrestaurant.services;

import java.util.Calendar;
import epsi.tdd.grandrestaurant.model.TypeCommande;

public class Serveur implements IServeur {
    private IRestaurant restaurant;
    private boolean isMaitreHotel = false;
    private double chiffreAffaires;

    public Serveur() {
        this.isMaitreHotel = false;
        this.chiffreAffaires = 0;
    }

    @Override
    public void prendreCommande(ICommande commande, ITable table) {
        restaurant.addCommande(commande);
        table.getAddition().add(commande);

        // traitement de la commande selon le type de commande
        TypeCommande type = commande.getTypeCommande();
        if (type == TypeCommande.NOURRITURE) {
            prendreCommandeNourriture(commande);

        } else if (type == TypeCommande.BOISSONS) {
            prendreCommandeBoissons(commande);
        }
        // Ajout du montant de la commande au chiffre d'affaires
        this.chiffreAffaires += commande.getMontant();
    }

    private void prendreCommandeNourriture(ICommande commande) {
        restaurant.addCommandeNourriture(commande);
    }

    private void prendreCommandeBoissons(ICommande commande) {
    }

    @Override
    public void commandeIsPaid(Commande commande, boolean estPaye) {
        // Epinglage de la commande (ou non)
        if (!estPaye) {
            commande.setEpinglee(true);
            Calendar calendar = Calendar.getInstance();
            commande.setDateEpinglage(calendar.getTime());
        }
    }

    // GETTERS & SETTERS

    @Override
    public boolean isMaitreHotel() {
        return isMaitreHotel;
    }

    public void setMaitreHotel(boolean isMaitreHotel) {
        this.isMaitreHotel = isMaitreHotel;
    }

    public void setRestaurant(IRestaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getChiffreAffaires() {
        return this.chiffreAffaires;
    }

    public Restaurant getRestaurant() {
        return (Restaurant) this.restaurant;
    }
}
