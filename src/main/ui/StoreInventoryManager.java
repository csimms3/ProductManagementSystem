package ui;

import model.Product;
import model.ProductManagementSystem;
import model.Sale;
import model.Shipment;

import java.util.Scanner;


// Product Management interface
public class StoreInventoryManager {

    private ProductManagementSystem sys;
    private Scanner input;
    private boolean managerRunning;




    //EFFECTS: initializes the interface
    public StoreInventoryManager() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runApplication();
    }

    //MODIFIES: this
    //EFFECTS: initializes and runs inventory manager app
    public void runApplication() {
        managerRunning = true;
        String cmd;

        initializeSystem();

        while (managerRunning) {
            showOptions();
            cmd = input.next().toLowerCase();
            handleInput(cmd);
        }
        System.out.println("Shutting down.");
    }

    //MODIFIES: this
    //EFFECTS: sets up product system
    public void initializeSystem() {
        Product apple = new Product("Apple", 0.99, 0001);
        Product ball = new Product("Ball", 4.00, 0015);
        Product textbook = new Product("210-textbook", 119.99, 1205);
        sys = new ProductManagementSystem();

        sys.addProduct(apple);
        sys.addProduct(ball);
        sys.addProduct(textbook);

        apple.addStock(3);
        textbook.addStock(2);

    }


    //MODIFIES: this
    //EFFECTS: handles user input
    public void handleInput(String cmd) {
        if (cmd.equals("vc")) {
            handleViewCatalogue();

        } else if (cmd.equals("fp"))  {
            handleFindProduct();

        } else if (cmd.equals("cp"))  {
            createProduct();

        } else if (cmd.equals("sp")) {
            sellProduct();

        } else if (cmd.equals("e")) {
            managerRunning = false;

        } else {
            System.out.println("Invalid Input.");
        }
    }

    //EFFECTS: displays command options
    public void showOptions() {
        System.out.println("Options:");
        System.out.println("View Catalogue (vc)");
        System.out.println("Find Product (fp)");
        System.out.println("Create Product (cp)");
        System.out.println("Sell Product (sp)");
        System.out.println("Exit (e)");
        System.out.print("> ");

    }

    //EFFECTS: prints current product catalogue
    public void handleViewCatalogue() {
        bufferLine();
        System.out.print(sys.displayCatalogue());
        bufferLine();
        stallLoop();
    }

    //MODIFIES: this
    //EFFECTS: take user input and handles related exceptions
    public void handleFindProduct() {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter product id, or (e) to exit:");
            System.out.print("> ");
            cmd = input.next().toLowerCase();

            if (cmd.equals("e")) {
                queryInput = false;
            } else if (cmd.length() == 4) {
                try {
                    int productId = Integer.parseInt(cmd);
                    queryInput = false;
                    findProduct(productId);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid Id.");
                }
            } else {
                System.out.println("Invalid Id.");
            }
        }
    }

    //EFFECTS: tries to find product based on id number
    private void findProduct(int id) {
        if (sys.containsProduct(id)) {
            System.out.print(sys.getProductById(id).toString());
            stallLoop();
        } else {
            System.out.println("Could not find product.");
        }

    }

    //MODIFIES: ProductManagementSystem
    //EFFECTS: Allows user to create product and add to catalogue
    public void createProduct() {

    }

    //MODIFIES: ProductManagementSystem
    //EFFECTS: Allows for the sale of products
    public void sellProduct() {
        //stub
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




    //EFFECTS: prints a buffer line (for spacing) to screen
    public void bufferLine() {
        System.out.println(("-----------------------------"));
    }

    //EFFECTS: prompts user for input, to temporarily pause system state
    public void stallLoop() {
        System.out.print("Press enter to return.");
        String temp = input.next();
    }
}
