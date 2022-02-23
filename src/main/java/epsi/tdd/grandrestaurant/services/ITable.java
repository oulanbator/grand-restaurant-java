package epsi.tdd.grandrestaurant.services;

import java.util.List;

public interface ITable {
    void affecterClient(IClient client);
    void passeCommande(ICommande commande);
    List<ICommande> getAddition();
}
