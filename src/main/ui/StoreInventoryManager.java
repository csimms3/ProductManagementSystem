package ui;

import model.Sale;
import model.Shipment;


// Main inventory object, will be used to store products and their data
public class StoreInventoryManager {

    public StoreInventoryManager() {
        runApplication();
    }

    //EFFECTS: initializes and runs inventory manager app
    public void runApplication() {
        boolean runManager = true;

        while (runManager) {

        }
    }


    //REQUIRES: stock of each item in sale >= num sold
    //MODIFIES: this
    //EFFECTS: updates inventory according to sale
    public void processSale(Sale sale) {
        // stub
    }

    //MODIFIES: this
    //EFFECTS: adds all items from shipment to stock
    public void receiveShipment(Shipment shipment) {
        // stub
    }
}
