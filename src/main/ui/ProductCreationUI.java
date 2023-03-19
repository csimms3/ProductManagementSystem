package ui;

import javax.swing.*;

//GUI for product creation
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

    //EFFECTS: initializes new product creation window
    public ProductCreationUI() {
        setupFrame();
        setupPanel();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up GUI elements
    private void setupFrame() {
        frame.setTitle("Create New Product");
        frame.setSize(350,160);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up panel and associated elements
    private void setupPanel() {
        panel.setLayout(null);
        setupNameField();
        setupIdField();
        setupPriceField();

        createProduct.setBounds(110, 100, 140, 25);
        panel.add(createProduct);
    }

    //MODIFIES: this
    //EFFECTS: sets up name field and label
    private void setupNameField() {
        nameLabel.setBounds(20,5,160,25);
        nameField.setBounds(250,5,80,25);

        panel.add(nameLabel);
        panel.add(nameField);
    }

    //MODIFIES: this
    //EFFECTS: sets up id field and label
    private void setupIdField() {
        idLabel.setBounds(20,35,160,25);
        idField.setBounds(250,35,80,25);

        panel.add(idLabel);
        panel.add(idField);
    }

    //MODIFIES: this
    //EFFECTS: sets up price field and label
    private void setupPriceField() {
        priceLabel.setBounds(20,65,160,25);
        priceField.setBounds(250,65,80,25);

        panel.add(priceLabel);
        panel.add(priceField);
    }
}
