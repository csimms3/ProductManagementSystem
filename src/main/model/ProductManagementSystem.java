package model;

import java.util.ArrayList;

public class ProductManagementSystem {

    private ArrayList<Product> catalogue;

    public ProductManagementSystem() {
        catalogue = new ArrayList<>();
    }

    //REQUIRES: product with same id not already in catalogue
    //MODIFIES: this
    //EFFECTS: adds product to catalogue
    public void addProduct(Product p) {
        this.catalogue.add(p);
    }


    //EFFECTS: returns true if product with id already in catalogue, otherwise false
    public boolean containsProduct(Product newProd) {
        boolean contains = false;

        for (Product prod:catalogue) {
            if (prod.getId() == newProd.getId()) {
                contains = true;
            }
        }
        return contains;
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

    //GETTERS

    public int getCatalogueSize() {
        return catalogue.size();
    }


}
