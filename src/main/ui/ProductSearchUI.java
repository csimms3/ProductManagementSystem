package ui;

import model.Product;
import model.ProductManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI for searching for products
public class ProductSearchUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JTextField searchBox = new JTextField(3);

    ProductManagementSystem productSystem;

    //EFFECTS: initializes new search window
    public ProductSearchUI(ProductManagementSystem productSystem) {
        this.productSystem = productSystem;

        setupFrame();
        setupElements();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle("Search");
        frame.setSize(250,125);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up gui elements
    private void setupElements() {

        JLabel instructions = new JLabel("Enter 4-digit Product ID:");
        JLabel exampleLabel = new JLabel("Example: 0000");

        panel.setLayout(null);

        instructions.setBounds(20,5,200,25);
        searchBox.setBounds(180,5,50,25);
        exampleLabel.setBounds(75,35, 100, 25);

        panel.add(instructions);
        panel.add(exampleLabel);
        panel.add(searchBox);

        setupSearchButton();

    }

    //MODIFIES: this
    //EFFECTS: sets up the search button and associated on-click event
    private void setupSearchButton() {
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(85, 65, 80, 25);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!searchBox.getText().equals("")) {
                    if (searchBox.getText().length() != 4 || (!isInteger(searchBox.getText()))) {
                        displayInvalidIdFrame();
                    } else {

                        findProduct(Integer.parseInt(searchBox.getText()));
                    }

                }
            }
        });
        panel.add(searchButton);
    }

    //EFFECTS: displays invalid id error frame
    private void displayInvalidIdFrame() {
        JOptionPane.showMessageDialog(frame, "Invalid Id",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    //EFFECTS: finds product in catalogue with given id
    private void findProduct(int id) {
        Product p = productSystem.getProductById(id);

        if (p == null) {
            JOptionPane.showMessageDialog(frame, "Could not find product with id: " + String.format("%04d", id),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            frame.dispose();
            new ProductViewerUI(productSystem, p);
        }
    }

    //EFFECTS: checks if a given string is numeric
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
