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

    public void affecterClient(Client client) {
        this.clients.add(client);
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void liberer() {
        this.clients.clear();
    }
    
}
