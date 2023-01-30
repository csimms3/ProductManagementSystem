package model;

import java.util.ArrayList;

public class ProductManagementSystem {

    private ArrayList<Product> catalogue;

    public ProductManagementSystem() {
        catalogue = new ArrayList<>();
    }

    //REQUIRES: product not already in catalogue
    //MODIFIES: this
    //EFFECTS: adds product to catalogue
    public void addProduct(Product p) {
        this.catalogue.add(p);
    }


    //EFFECTS: returns true if product already in catalogue, otherwise false
    public boolean containsProduct(Product p) {
        return false; //stub
    }

    //REQUIRES: product in catalogue
    //EFFECTS: returns given product
    public Product getProductById(int id) {
        return null;
    }

    //REQUIRES: product in catalogue
    //EFFECTS: returns given product
    public Product getProductByName(String name) {
        return null;
    }


}
