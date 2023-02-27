package ui;

import model.Product;
import model.ProductManagementSystem;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Product Management interface
public class StoreInventoryManager {

    private static final String JSON_FILE = "./data/productmanagementsystem.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    private ProductManagementSystem productSystem;
    private Scanner input;
    private boolean managerRunning;


    //EFFECTS: initializes the interface
    public StoreInventoryManager() throws FileNotFoundException {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        productSystem = new ProductManagementSystem();
        jsonReader = new JsonReader(JSON_FILE);
        jsonWriter = new JsonWriter(JSON_FILE);

        runApplication();
    }

    //MODIFIES: this
    //EFFECTS: initializes and runs inventory manager app
    private void runApplication() {
        managerRunning = true;
        String cmd;

        while (managerRunning) {
            showOptions();
            cmd = input.next().toLowerCase();
            handleInput(cmd);
        }
        System.out.println("Shutting down.");
    }

    //MODIFIES: this
    //EFFECTS: handles user input
    private void handleInput(String cmd) {
        if (cmd.equals("v")) {
            handleViewCatalogue();
        } else if (cmd.equals("f"))  {
            handleFindProduct();
        } else if (cmd.equals("c"))  {
            handleCreateProduct();
        } else if (cmd.equals("l"))  {
            loadSystemFromFile();
        } else if (cmd.equals("s"))  {
            saveSystemToFile();
        } else if (cmd.equals("e")) {
            managerRunning = false;

        } else {
            System.out.println("Invalid Input.");
        }
    }

    //EFFECTS: displays command options
    private void showOptions() {
        System.out.println("Options:");
        bufferLine();
        System.out.println("View Catalogue (v)");
        System.out.println("Find Product (f)");
        System.out.println("Create Product (c)");
        System.out.println("Load System from File (l)");
        System.out.println("Save System to File (s)");
        System.out.println("Exit (e)");
        bufferLine();
        System.out.print("> ");

    }

    //EFFECTS: prints current product catalogue
    private void handleViewCatalogue() {
        bufferLine();
        System.out.print(productSystem.displayCatalogue());
        bufferLine();
        stallLoop();
    }

    //EFFECTS: take user input and handles related exceptions
    private void handleFindProduct() {
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
                    findProduct(productId);
                    queryInput = false;


                } catch (NumberFormatException e) {
                    System.out.println("Invalid Id.");
                }
            } else {
                System.out.println("Invalid Id.");
            }
        }
    }

    //REQUIRES: id is length 4
    //EFFECTS: tries to find product based on id number
    private void findProduct(int id) {
        boolean queryInput = true;
        String cmd;

        if (productSystem.containsProduct(id)) {
            System.out.print(productSystem.getProductById(id).toString());

            while (queryInput) {
                System.out.println("Enter (a) to view actions, or (e) to exit:");
                System.out.print("> ");
                cmd = input.next().toLowerCase();

                if (cmd.equals("e")) {
                    queryInput = false;
                } else if (cmd.equals("a")) {
                    changeProduct(productSystem.getProductById(id));
                } else {
                    System.out.println("Invalid Id.");
                }
            }
        } else {
            System.out.println("Could not find product.");
        }

    }

    //MODIFIES: this, p
    //EFFECTS: allows user to edit attributes of given product
    private void changeProduct(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            showProductActions();
            System.out.print("> ");
            cmd = input.next().toLowerCase();
            if (cmd.equals("n")) {
                handleChangeProductName(p);
            } else if (cmd.equals("p")) {
                handleChangeProductPrice(p);
            } else if (cmd.equals("a")) {
                handleAddStock(p);
            } else if (cmd.equals("r")) {
                handleRemoveStock(p);
            } else if (cmd.equals("e")) {
                queryInput = false;
            } else {
                System.out.println("Invalid Input.");
            }
        }
    }

    //EFFECTS: displays list of product actions
    private void showProductActions() {
        System.out.println("Actions Available:");
        bufferLine();
        System.out.println("Update name (n)");
        System.out.println("Update price (p)");
        System.out.println("Add stock (a)");
        System.out.println("Remove stock (r)");
        System.out.println("Exit (e)");
        bufferLine();

    }

    //MODIFIES: this, p
    //EFFECTS: handles user changing product name
    private void handleChangeProductName(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter a new name to change product name, or (e) to exit");
            System.out.print("> ");
            cmd = input.next();
            if (cmd.equals("e")) {
                queryInput = false;
            } else {
                p.setName(cmd);
                queryInput = false;
                System.out.print("Product name successfully changed:");
                System.out.println(p);
                stallLoop();
            }
        }
    }

    //MODIFIES: this, p
    //EFFECTS: handles user changing product price
    private void handleChangeProductPrice(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter new price to update product price.");
            System.out.print("> ");
            cmd = input.next().toLowerCase();

            try {
                double newPrice = Double.parseDouble(cmd);
                p.setPrice(newPrice);
                queryInput = false;
                System.out.print("Product price successfully changed:");
                System.out.println(p);
                stallLoop();

            } catch (NumberFormatException e) {
                System.out.println("Invalid Price.");
            }
        }
    }

    //MODIFIES: this, p
    //EFFECTS: handles user adding product stock
    private void handleAddStock(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter number of items to add.");
            System.out.println("Current stock: " + p.getStock());
            System.out.print("> ");
            cmd = input.next().toLowerCase();

            try {
                int newStock = Integer.parseInt(cmd);
                p.addStock(newStock);
                queryInput = false;
                System.out.print("Product stock successfully changed:");
                System.out.println(p);
                stallLoop();

            } catch (NumberFormatException e) {
                System.out.println("Invalid Stock.");
            }
        }
    }

    //MODIFIES: this, p
    //EFFECTS: handles user reducing product stock
    private void handleRemoveStock(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter number of items to remove");
            System.out.println("Current stock (cannot remove more than this): " + p.getStock());
            System.out.print("> ");
            cmd = input.next().toLowerCase();

            try {
                int newStock = Integer.parseInt(cmd);
                if (newStock <= p.getStock()) {
                    p.reduceStock(newStock);
                    queryInput = false;
                    System.out.print("Product stock successfully changed:");
                    System.out.println(p);
                    stallLoop();
                } else {
                    System.out.println("Invalid Stock. Cannot remove more than current stock");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Stock.");
            }
        }
    }

    //MODIFIES: this, productSystem
    //EFFECTS: Allows user to create product and add to catalogue
    private void handleCreateProduct() {
        boolean queryInput = true;

        while (queryInput) {
            String name = handleCreateProductName();
            double price = handleCreateProductPrice();
            int id = handleCreateProductId();

            Product newProduct = new Product(name, price, id);

            handleNewProduct(newProduct);

            System.out.println("Type (c) to create another product, or (e) to exit.");
            System.out.print("> ");
            String cmd = input.next().toLowerCase();
            if (cmd.equals("e")) {
                queryInput = false;
            } else if (cmd.equals("c")) {
                System.out.println("Returning to product creation...");
            } else {
                System.out.println("Invalid Input.");
                queryInput = false;
            }
        }
    }

    //EFFECTS: prompts user for product name and returns it
    private String handleCreateProductName() {
        bufferLine();
        System.out.println("Enter a name for new product:");
        System.out.print("> ");
        return input.next();
    }

    //EFFECTS: prompts user for price, validates and returns it
    private double handleCreateProductPrice() {
        boolean finished = false;
        double price = 0.0;

        while (!finished) {
            bufferLine();
            System.out.println("Enter a price in form XX.XX:");
            System.out.print("> ");
            String priceString = input.next().toLowerCase();

            try {
                price = Double.parseDouble(priceString);
                finished = true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Price.");
            }
        }
        return price;
    }

    //EFFECTS: prompts user for id, validates and returns it
    private int handleCreateProductId() {
        boolean finished = false;
        int id = 0;

        while (!finished) {
            bufferLine();
            System.out.println("Enter product id in form (XXXX):");
            System.out.print("> ");
            String idString = input.next();

            if (idString.length() == 4)  {
                try {
                    id = Integer.parseInt(idString);
                    finished = true;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid Id.");
                }
            } else {
                System.out.println("Invalid Id.");
            }
        }
        return id;
    }

    //MODIFIES: this, productSystem
    //EFFECTS: allows user to add new product to catalogue, or discard it
    public void handleNewProduct(Product p) {
        boolean queryInput = true;

        System.out.println("New Product:");
        System.out.println("Name: " + p.getName());
        System.out.println("Price: $" + String.format("%.2f",p.getPrice()));
        System.out.println("ID: " + String.format("%04d", p.getId()));
        bufferLine();

        while (queryInput) {
            System.out.println("Type (a) to add product to catalogue, or (d) to discard it");
            System.out.print("> ");
            String cmd = input.next().toLowerCase();
            if (cmd.equals("a")) {
                productSystem.addProduct(p);
                System.out.println("Product Added!");
                queryInput = false;
            } else if (cmd.equals("d")) {
                queryInput = false;
            } else {
                System.out.println("Invalid Input.");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: loads system state from save file
    private void loadSystemFromFile() {
        try {
            productSystem = jsonReader.read();
            System.out.println("Loaded ProductSystem from " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("File read error when reading from: " + JSON_FILE);
        }

    }

    //EFFECTS: saves current state of system to save file
    private void saveSystemToFile() {
        try {
            jsonWriter.openFile();
            jsonWriter.write(productSystem);
            jsonWriter.close();
            System.out.println("Successfully saved Product System to: " + JSON_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("File write error for: " + JSON_FILE);
        }
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
