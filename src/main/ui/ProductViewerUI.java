package ui;

import model.Product;
import model.ProductManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//UI for viewing and editing products
public class ProductViewerUI {

    static final int BUTTON_X = 200;
    static final int TEXT_X = 10;
    static final int TEXT_WIDTH = 200;
    static final int ELEMENT_HEIGHT = 25;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    Product product;
    ProductManagementSystem productSystem;

    //EFFECTS: initializes new search window
    public ProductViewerUI(ProductManagementSystem productSystem, Product p) {
        this.productSystem = productSystem;
        this.product = p;

        setupFrame();
        setupElements();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle("#" + String.format("%04d", product.getId()) + " - " + product.getName());
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up gui elements
    private void setupElements() {
        JLabel productName = new JLabel("Name: " + product.getName());
        JLabel productPrice = new JLabel("Price: " + product.getPrice());
        JLabel productStock = new JLabel("Stock: " + product.getStock());
        JLabel productId = new JLabel("Id: " + product.getId());

        panel.setLayout(null);

        productName.setBounds(TEXT_X,5,TEXT_WIDTH,ELEMENT_HEIGHT);
        productPrice.setBounds(TEXT_X,30,TEXT_WIDTH,ELEMENT_HEIGHT);
        productStock.setBounds(TEXT_X,55,TEXT_WIDTH,ELEMENT_HEIGHT);
        productId.setBounds(TEXT_X,80,TEXT_WIDTH,ELEMENT_HEIGHT);

        panel.add(productName);
        panel.add(productPrice);
        panel.add(productStock);
        panel.add(productId);

        setupButtons();
    }

    //MODIFIES: this
    //EFFECTS: sets up buttons to edit product attributes
    private void setupButtons() {
        setupUpdateStockButton();
        setupDeleteProductButton();
    }

    //MODIFIES: this
    //EFFECTS: sets up the update stock button and associated on-click event
    private void setupUpdateStockButton() {
        JButton addStockButton = new JButton("Update Stock");
        addStockButton.setBounds(BUTTON_X, 55, 150, ELEMENT_HEIGHT);
        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStockUI(productSystem, product);
                frame.dispose();
            }
        });
        panel.add(addStockButton);
    }

    //MODIFIES: this, product, productSystem
    //EFFECTS: sets up the "delete product" button and associated on-click event
    private void setupDeleteProductButton() {
        JButton priceChangeButton = new JButton("Delete Product");
        priceChangeButton.setBounds(BUTTON_X, 140, 150, ELEMENT_HEIGHT);
        priceChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this product?",
                        "Confirm Product Deletion", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    productSystem.deleteProduct(product.getId());
                    JOptionPane.showMessageDialog(frame, "Product Deleted", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(priceChangeButton);
    }

}

