package epsi.tdd.grandrestaurant.services;

public class Client {
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
       for(Commande commande : table.getAddition()){
           commande.setIsRegle(true);
       }
    }

    void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
       return this.table; 
    }
}
