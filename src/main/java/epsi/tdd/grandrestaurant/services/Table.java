package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Client> clients = new ArrayList<>();
    private Serveur serveurAffecte;

    public Serveur getServeur() {
        return serveurAffecte;
    }

    public void setServeur(Serveur serveur) {
        this.serveurAffecte = serveur;
    }

    public void affecteClient(Client client) {
        this.clients.add(client);
    }

    public List<Client> getClients() {
        return this.clients;
    }
    
}
