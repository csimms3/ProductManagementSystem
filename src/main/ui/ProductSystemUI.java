package ui;

import model.Event;
import model.EventLog;
import model.Product;
import model.ProductManagementSystem;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;


// GUI for productmanagementsystem
//citation: https://www.youtube.com/watch?v=iE8tZ0hn2Ws
//main frame/panel structure based off above source
public class ProductSystemUI {

    private static final String JSON_FILE = "./data/productmanagementsystem.json";

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    ProductManagementSystem productSystem = new ProductManagementSystem();
    JsonReader jsonReader = new JsonReader(JSON_FILE);
    JsonWriter jsonWriter = new JsonWriter(JSON_FILE);

    //EFFECTS: initializes the main GUI window, at the menu screen
    public ProductSystemUI() {
        setupGUI();



    }

    //MODIFIES: this
    //EFFECTS: sets up main menu GUI
    private void setupGUI() {
        setupFrame();
        panel.setLayout(null);

        setupButtons();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: sets up the main menu screen
    private void setupFrame() {
        frame.setTitle("Product Management System");
        frame.setSize(400,300);
        frame.add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        saveOnExitListener();

    }

    //citation: https://stackoverflow.com/questions/5911565
    //adapted code from source for handling multiple event listeners in single panel
    //MODIFIES: this
    //EFFECTS: sets up the main menu buttons
    private void setupButtons() {
        setupSearchButton();
        setupNewProductButton();
        setupViewProductsButton();
        setupLoadButton();
    }

    //MODIFIES: this
    //EFFECTS: sets up the search button and associated on-click event
    private void setupSearchButton() {
        JButton searchButton = new JButton("Search by ID");
        searchButton.setBounds(115, 55, 165, 25);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductSearchUI(productSystem);
            }
        });
        panel.add(searchButton);
    }

    //MODIFIES: this
    //EFFECTS: sets up the new product button and associated on-click event
    private void setupNewProductButton() {
        JButton newProductButton = new JButton("Create New Product");
        newProductButton.setBounds(115, 95, 165, 25);
        newProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductCreationUI(productSystem);
            }
        });
        panel.add(newProductButton);
    }

    //MODIFIES: this
    //EFFECTS: sets up the view products button and associated on-click event
    private void setupViewProductsButton() {
        JButton viewProductsButton = new JButton("View All Products");
        viewProductsButton.setBounds(115, 135, 165, 25);
        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCatalogueUI(productSystem);
            }
        });
        panel.add(viewProductsButton);
    }

    //MODIFIES: this
    //EFFECTS: sets up the load system button and associated on-click event
    private void setupLoadButton() {
        JButton loadSystemButton = new JButton("Load from File");
        loadSystemButton.setBounds(115, 175, 165, 25);
        loadSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productSystem = loadSystemFromFile();
                JOptionPane.showMessageDialog(frame, "Catalogue loaded from: " + JSON_FILE,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(loadSystemButton);
    }

    //citation: https://stackoverflow.com/questions/9093448
    //JOptionPane and windowlistener functionality adapted from source above
    //MODIFIES: this
    //EFFECTS: shows save prompt on program exit
    private void saveOnExitListener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Do you want to save your changes before quitting?", "Save Changes",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    saveCatalogue();
                }
                for (Event e: EventLog.getInstance()) {
                    System.out.println(e.toString());
                }

            }
        });
    }

    //EFFECTS: saves the current state of the catalogue to file
    private void saveCatalogue() {
        saveSystemToFile(productSystem);
    }

    //EFFECTS: loads system state from save file
    private ProductManagementSystem loadSystemFromFile() {
        try {
            productSystem = jsonReader.read();
        } catch (IOException e) {
            System.out.println("File read error when reading from: " + JSON_FILE);
        }
        return productSystem;
    }

    //EFFECTS: saves current state of system to save file
    private void saveSystemToFile(ProductManagementSystem productSystem) {
        try {
            jsonWriter.openFile();
            jsonWriter.write(productSystem);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File write error for: " + JSON_FILE);
        }
    }

    //EFFECTS: returns current state of catalogue
    public ProductManagementSystem getCatalogue() {
        return productSystem;
    }

    //EFFECTS: initializes a new GUI system
    public static void main(String[] args) {

        new LoadingScreen(1500);
        new ProductSystemUI();
    }
}
