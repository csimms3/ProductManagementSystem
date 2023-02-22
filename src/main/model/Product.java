package model;

import org.json.JSONObject;
import persistence.Writable;

// a product with an id value, a name, a price (in $), and a number in stock
// id must be unique
public class Product implements Writable {

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
    public void addStock(int amount) {
        this.stock += amount;
    }

    //REQUIRES: amount > 0 and amount <= stock on hand
    //MODIFIES: this
    //EFFECTS: reduces product stock by given amount
    public void reduceStock(int amount) {
        this.stock -= amount;
    }

    
    // EFFECTS: returns printable representation of the product
    @Override
    public String toString() {
        return "| #" + String.format("%04d", this.id) + " | " + this.name
                + " | Price: $" + String.format("%.2f", this.price) + " | "
                + this.stock + " in stock |\n";
    }


    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES: price > 0
    public void setPrice(double price) {
        this.price = price;
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


    //EFFECTS: returns a JSON representation of product
    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("name", this.name);
        jsonObj.put("price", this.price);
        jsonObj.put("stock", this.stock);
        jsonObj.put("id", this.id);

        return jsonObj;
    }

}
