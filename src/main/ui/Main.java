package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new StoreInventoryManager();
        } catch (FileNotFoundException e) {
            System.out.println("Application Startup Failed - could not find file.");
        }
    }
}
