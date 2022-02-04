package epsi.tdd.grandrestaurant.services;

import java.util.Calendar;
import epsi.tdd.grandrestaurant.model.TypeCommande;

public class Serveur {
    private Restaurant restaurant;
    private boolean isMaitreHotel;
    private double chiffreAffaires;

    public Serveur() {
        this.isMaitreHotel = false;
        this.chiffreAffaires = 0;
    }

    public void prendreCommande(Commande commande) {
        // Associe la table à la commande ???

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

    private void prendreCommandeNourriture(Commande commande) {
        restaurant.addCommandeNourriture(commande);
    }

    private void prendreCommandeBoissons(Commande commande) {
    }

    // GETTERS & SETTERS

    public boolean isMaitreHotel() {
        return isMaitreHotel;
    }

    public void setMaitreHotel(boolean isMaitreHotel) {
        this.isMaitreHotel = isMaitreHotel;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getChiffreAffaires() {
        return this.chiffreAffaires;
    }

    public void commandeIsPaid(Commande commande, boolean b) {

        boolean isEpinglee = b ? false : true;
        // Epinglage de la commande (ou non)
        commande.setEpinglee(isEpinglee);
        if (isEpinglee) {
            Calendar calendar = Calendar.getInstance();
            commande.setDateEpinglage(calendar.getTime());
        }
    }

}
