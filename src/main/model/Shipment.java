package model;

import java.util.ArrayList;

public class Shipment {

    private ArrayList<Product> shipmentItems;

    public Shipment(ArrayList shipmentItems) {
        this.shipmentItems = shipmentItems;
    }
}
