package model;

import java.util.ArrayList;


// Represents a sale having a date, total price and list of items sold
public class Sale {

    private ArrayList<Product> saleItems;


    public Sale(ArrayList saleItems) {
        this.saleItems = saleItems;
    }
}
