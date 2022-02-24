package epsi.tdd.grandrestaurant.services;

public interface IServeur {
    void prendreCommande(ICommande commande, ITable table);
    void commandeIsPaid(Commande commande, boolean estPaye);
    boolean isMaitreHotel();
    Long getId();
    double getChiffreAffaires();
    Restaurant getRestaurant();
}
