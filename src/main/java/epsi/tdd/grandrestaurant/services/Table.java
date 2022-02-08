package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Client> clients = new ArrayList<>();
    private Serveur serveurAffecte;
    private List<Commande> addition = new ArrayList<>();

    public void affecterClient(Client client) {
        this.clients.add(client);
    }

    public void passeCommande(Commande commande) {
        getServeur().prendreCommande(commande, this);
    }

    // GETTERS AND SETTERS
    public Serveur getServeur() {
        return serveurAffecte;
    }

    public void setServeur(Serveur serveur) {
        this.serveurAffecte = serveur;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void liberer() {
        this.clients.clear();
    }

    public List<Commande> getAddition() {
        return addition;
    }
}
