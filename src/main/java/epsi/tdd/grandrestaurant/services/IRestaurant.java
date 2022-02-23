package epsi.tdd.grandrestaurant.services;

import java.util.List;

public interface IRestaurant {
    void addPlatToMenu(Plat plat);
    void createTables(int nbTables);
    void startService();
    void stopService();
    Serveur addNewServeur();
    List<Table> getTablesLibres();
    boolean affecterServeurTable(int indexTable, IServeur serveur);
    void listerCommandesATransmettreGendarmerie();
    void transmettreCommandesGendarmerie();
    void addCommande(ICommande commande);
    void addCommandeNourriture(ICommande commande);

}
