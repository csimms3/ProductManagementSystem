package model;

// a product with a name, a price (in $), and a number in stock
public class Product {

    private String name;
    private double price;
    private int stock;
    private final int id;


    //REQUIRES: price > 0, id is 4 digits
    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
        stock = 0;

    }

    //REQUIRES: amount > 0
    //MODIFIES: this
    //EFFECTS: increases product stock by given amount
    public void increaseStock(int amount) {
        this.stock += amount;
    }

    //REQUIRES: amount > 0 and amount <= stock on hand
    //MODIFIES: this
    //EFFECTS: reduces product stock by given amount
    public void reduceStock(int amount) {
        this.stock -= amount;
    }




    
    // EFFECTS: returns printable representation of the product
    public String toString() {
        return ""; // STUB
    }








    // GETTERS

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public int getId() {
        return this.id;
    }

}
