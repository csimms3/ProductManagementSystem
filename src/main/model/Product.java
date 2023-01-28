package model;

// a product with a name, a price (in $), and a number in stock
public class Product {

    private String name;
    private double price;
    private int stock;


    //REQUIRES: price > 0
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        stock = 0;
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
}
