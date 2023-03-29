package ui;

import model.Product;
import model.ProductManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

//abstract ui for updating stock
public class UpdateStockUI {

    protected static final int ELEMENT_HEIGHT = 25;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JLabel currentStock;
    JLabel promptLabel = new JLabel("Qty: ");
    JTextField inputField = new JTextField(3);

    Product product;
    ProductManagementSystem productSystem;

    //EFFECTS: initializes new stock update window
    public UpdateStockUI(ProductManagementSystem psys, Product p) {
        this.product = p;
        this.productSystem = psys;

        setupFrame();
        setupElements();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        restoreProductViewOnExit();
    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle(product.getName());
        frame.setSize(250,150);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up gui elements
    void setupElements() {
        panel.setLayout(null);

        currentStock = new JLabel("Current stock: " + product.getStock());


        currentStock.setBounds(10,5,150,ELEMENT_HEIGHT);
        promptLabel.setBounds(10,30,40,ELEMENT_HEIGHT);
        inputField.setBounds(50,30,40,ELEMENT_HEIGHT);

        panel.add(currentStock);
        panel.add(promptLabel);
        panel.add(inputField);

        setupAddButton();
        setupRemoveButton();
    }

    //MODIFIES: this, product
    //EFFECTS: sets up the add stock button and associated on-click event
    private void setupAddButton() {
        JButton addButton = new JButton("Add");
        addButton.setBounds(10, 90, 110, ELEMENT_HEIGHT);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInteger(inputField.getText())) {
                    product.addStock(Integer.parseInt(inputField.getText()));
                    currentStock.setText("Current stock: " + product.getStock());
                }

            }
        });
        panel.add(addButton);
    }

    //MODIFIES: this, product
    //EFFECTS: sets up the remove stock button and associated on-click event
    private void setupRemoveButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(130, 90, 110, ELEMENT_HEIGHT);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInteger(inputField.getText())) {
                    int numToRemove = Integer.parseInt(inputField.getText());
                    if (product.getStock() <= numToRemove) {
                        product.reduceStock(product.getStock());
                    } else {
                        product.reduceStock(numToRemove);
                    }
                    currentStock.setText("Current stock: " + product.getStock());
                }
            }
        });
        panel.add(removeButton);
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


    //MODIFIES: this
    //EFFECTS: restores product view page on update stock page exit
    private void restoreProductViewOnExit() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new ProductViewerUI(productSystem, product);
            }
        });
    }
}
