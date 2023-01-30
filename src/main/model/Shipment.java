package model;

import java.util.ArrayList;

public class Shipment {

    private ArrayList shipmentItems;

    public Shipment(ArrayList<Product> shipmentItems) {
        this.shipmentItems = shipmentItems;
    }
}
