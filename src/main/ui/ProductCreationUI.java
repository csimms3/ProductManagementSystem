package ui;

import javax.swing.*;

public class ProductCreationUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel nameLabel = new JLabel("Product Name:");
    JLabel idLabel = new JLabel("Product Id (4 digits):");
    JLabel priceLabel = new JLabel("Price per Unit:");
    JTextField nameField = new JTextField(10);
    JTextField idField = new JTextField(10);
    JTextField priceField = new JTextField(10);

    JButton createProduct = new JButton("Create Product");

    public ProductCreationUI() {
        setupFrame();
        setupPanel();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame.setTitle("Create New Product");
        frame.setSize(350,160);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    private void setupPanel() {
        panel.setLayout(null);
        setupNameField();
        setupIdField();
        setupPriceField();

        createProduct.setBounds(200, 100, 140, 25);
        panel.add(createProduct);
    }

    private void setupNameField() {
        nameLabel.setBounds(20,10,160,25);
        nameField.setBounds(250,10,80,25);

        panel.add(nameLabel);
        panel.add(nameField);
    }

    private void setupIdField() {
        idLabel.setBounds(20,40,160,25);
        idField.setBounds(250,40,80,25);

        panel.add(idLabel);
        panel.add(idField);
    }

    private void setupPriceField() {
        priceLabel.setBounds(20,70,160,25);
        priceField.setBounds(250,70,80,25);

        panel.add(priceLabel);
        panel.add(priceField);
    }
}
