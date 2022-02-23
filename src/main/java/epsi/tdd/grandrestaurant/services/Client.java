package epsi.tdd.grandrestaurant.services;

public class Client implements IClient {
    private String name;
    private Table table;
    
    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void repasEstFini(){
       for(ICommande commande : table.getAddition()){
           commande.setIsRegle(true);
       }
    }

    void setTable(Table t) {
        this.table = t;
    }

    public Table getTable() {
       return this.table;
    }
}
