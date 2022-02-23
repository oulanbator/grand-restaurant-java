package epsi.tdd.grandrestaurant.services;

import java.util.ArrayList;
import java.util.List;

public class Table implements ITable {
    private List<IClient> clients = new ArrayList<>();
    private List<ICommande> addition = new ArrayList<>();
    private IServeur serveurAffecte;
    private boolean isLibre;

    // METHODS
    @Override
    public void affecterClient(IClient client) {
        this.clients.add(client);
        setLibre(false);
    }

    @Override
    public void passeCommande(ICommande commande) {
        getServeur().prendreCommande(commande, this);
    }


    // GETTERS AND SETTERS
    public IServeur getServeur() {
        return serveurAffecte;
    }

    public void setServeur(IServeur serveur) {
        this.serveurAffecte = serveur;
    }

    public List<IClient> getClients() {
        return this.clients;
    }

    public void liberer() {
        this.clients.clear();
    }

    @Override
    public List<ICommande> getAddition() {
        return addition;
    }

    public boolean isLibre() {
        return isLibre;
    }

    public void setLibre(boolean libre) {
        isLibre = libre;
    }
}
