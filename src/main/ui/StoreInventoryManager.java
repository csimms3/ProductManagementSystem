package ui;

import model.Product;
import model.ProductManagementSystem;

import java.util.Scanner;


// Product Management interface
public class StoreInventoryManager {

    private ProductManagementSystem productSystem;
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

    //MODIFIES: productSystem
    //EFFECTS: sets up product system
    public void initializeSystem() {
        Product apple = new Product("Apple", 0.99, 0001);
        Product ball = new Product("Ball", 4.00, 0015);
        Product textbook = new Product("210-textbook", 119.99, 1205);
        productSystem = new ProductManagementSystem();

        productSystem.addProduct(apple);
        productSystem.addProduct(ball);
        productSystem.addProduct(textbook);

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
            handleCreateProduct();

        } else if (cmd.equals("e")) {
            managerRunning = false;

        } else {
            System.out.println("Invalid Input.");
        }
    }

    //EFFECTS: displays command options
    public void showOptions() {
        System.out.println("Options:");
        bufferLine();
        System.out.println("View Catalogue (vc)");
        System.out.println("Find Product (fp)");
        System.out.println("Create Product (cp)");
        System.out.println("Exit (e)");
        bufferLine();
        System.out.print("> ");

    }

    //EFFECTS: prints current product catalogue
    public void handleViewCatalogue() {
        bufferLine();
        System.out.print(productSystem.displayCatalogue());
        bufferLine();
        stallLoop();
    }

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

    //REQUIRES: id is int of length 4
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

    //MODIFIES: productSystem
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

    //MODIFIES: productSystem
    //EFFECTS: handles user changing product name
    private void handleChangeProductName(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter a new name to change product name, or (e) to exit");
            System.out.print("> ");
            cmd = input.next().toLowerCase();
            if (cmd.equals("e")) {
                queryInput = false;
            } else {
                p.setName(cmd);
                System.out.print("Product name successfully changed:");
                System.out.println(p.toString());
                stallLoop();
            }
        }
    }

    //MODIFIES: productSystem
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
                System.out.println(p.toString());
                stallLoop();

            } catch (NumberFormatException e) {
                System.out.println("Invalid Price.");
            }
        }
    }

    //MODIFIES: productSystem
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
                System.out.println(p.toString());
                stallLoop();

            } catch (NumberFormatException e) {
                System.out.println("Invalid Stock.");
            }
        }
    }

    //MODIFIES: productSystem
    //EFFECTS: handles user reducing product stock
    private void handleRemoveStock(Product p) {
        boolean queryInput = true;
        String cmd;

        while (queryInput) {
            System.out.println("Enter number of items to remove, or (e) to exit");
            System.out.println("Current stock (cannot remove more than this): " + p.getStock());
            System.out.print("> ");
            cmd = input.next().toLowerCase();

            try {
                int newStock = Integer.parseInt(cmd);
                if (newStock <= p.getStock()) {
                    p.reduceStock(newStock);
                    queryInput = false;
                    System.out.print("Product stock successfully changed:");
                    System.out.println(p.toString());
                    stallLoop();
                } else {
                    System.out.println("Invalid Stock. Cannot remove more than current stock");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Stock.");
            }
        }
    }


    //MODIFIES: productSystem
    //EFFECTS: Allows user to create product and add to catalogue
    public void handleCreateProduct() {
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
            }
        }
    }

    //EFFECTS: prompts user for name and returns it
    public String handleCreateProductName() {
        bufferLine();
        System.out.println("Enter a name for new product:");
        System.out.print("> ");
        return input.next();
    }

    //EFFECTS: prompts user for price, validates and returns it
    public double handleCreateProductPrice() {
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
    public int handleCreateProductId() {
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

    //MODIFIES: productSystem
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
