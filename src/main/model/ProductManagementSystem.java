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

}
