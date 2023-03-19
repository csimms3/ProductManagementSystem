package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// GUI for productmanagementsystem
public class ProductSystemUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    //EFFECTS: initializes the main GUI window, at the menu screen
    public ProductSystemUI() {
        setupFrame();
        panel.setLayout(null);

        JLabel label = new JLabel("Product Count: 0, Total Stock: 0");
        label.setBounds(95,15,225,25);
        panel.add(label);

        setupButtons();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: sets up the main menu screen
    private void setupFrame() {
        frame.setTitle("Product Management System");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

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
                System.out.println("Search Action");
                new ProductSearchUI();
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
                System.out.println("New Product Action");
                new ProductCreationUI();
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
                System.out.println("View Action");
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
                System.out.println("Load Action");
            }
        });
        panel.add(loadSystemButton);
    }

    //TODO: add WindowListener for SAVE functionality


    //EFFECTS: initializes a new GUI system
    public static void main(String[] args) {
        new ProductSystemUI();
    }
}
