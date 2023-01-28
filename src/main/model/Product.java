package model;

// a product with a name, a price (in $), and a number in stock
public class Product {

    private String name;
    private float price;
    private int stock;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
        stock = 0;
    }








    // GETTERS

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }
}
