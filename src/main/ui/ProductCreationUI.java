package ui;

import model.Product;
import model.ProductManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI for product creation
public class ProductCreationUI {

    ProductManagementSystem productSystem;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel nameLabel = new JLabel("Product Name:");
    JLabel idLabel = new JLabel("Product Id (4 digits):");
    JLabel priceLabel = new JLabel("Price per Unit:");
    JTextField nameField = new JTextField(10);
    JTextField idField = new JTextField(10);
    JTextField priceField = new JTextField(10);


    JFrame confirmationFrame = new JFrame();
    JPanel confirmationPanel = new JPanel();

    //EFFECTS: initializes new product creation window
    public ProductCreationUI(ProductManagementSystem productSystem) {
        this.productSystem = productSystem;
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
        setupCreateProductButton();


    }

    //MODIFIES: this
    //EFFECTS: sets up name field and label
    private void setupNameField() {
        nameLabel.setBounds(20,5,160,25);
        nameField.setBounds(210,5,120,25);

        panel.add(nameLabel);
        panel.add(nameField);
    }

    //MODIFIES: this
    //EFFECTS: sets up id field and label
    private void setupIdField() {
        idLabel.setBounds(20,35,160,25);
        idField.setBounds(210,35,120,25);

        panel.add(idLabel);
        panel.add(idField);
    }

    //MODIFIES: this
    //EFFECTS: sets up price field and label
    private void setupPriceField() {
        priceLabel.setBounds(20,65,160,25);
        priceField.setBounds(210,65,120,25);

        panel.add(priceLabel);
        panel.add(priceField);
    }

    //MODIFIES: this
    //EFFECTS: sets up the search button and associated on-click event
    private void setupCreateProductButton() {
        JButton createProduct = new JButton("Create Product");
        createProduct.setBounds(110, 100, 140, 25);
        createProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CreateProduct Action (ProductCreation Window)");
                if (!(nameField.getText().equals("")
                        || idField.getText().equals("")
                        || priceField.getText().equals(""))) {
                    String newProductName = nameField.getText();
                    int newProductId = Integer.parseInt(idField.getText());
                    double newProductPrice = Double.parseDouble(priceField.getText());
                    frame.dispose();
                    handleProductConfirmation(newProductName,newProductId,newProductPrice);
                }
            }
        });
        panel.add(createProduct);
    }

    //EFFECTS: handles confirmation of product creation
    private void handleProductConfirmation(String name, int id, double price) {
        Product newProduct = new Product(name,price,id);
        setupConfirmationWindow(newProduct);
    }

    //MODIFIES: this
    //EFFECTS: sets up confirmation window
    private void setupConfirmationWindow(Product p) {

        setupConfirmationLabels(p);

        confirmationPanel.setLayout(null);

        confirmationFrame.setTitle("Product Created");
        confirmationFrame.setSize(300,150);
        confirmationFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        confirmationFrame.add(confirmationPanel);
        confirmationFrame.setResizable(false);

        confirmationFrame.setLocationRelativeTo(null);
        confirmationFrame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up labels and buttons for confirmation panel
    private void setupConfirmationLabels(Product p) {
        JLabel nameConfirmationLabel = new JLabel("Name: " + p.getName());
        JLabel idConfirmationLabel = new JLabel("Id: " + p.getId());
        JLabel priceConfirmationLabel = new JLabel("Price: " + p.getPrice());

        nameConfirmationLabel.setBounds(10,10,200,25);
        idConfirmationLabel.setBounds(10,30,200,25);
        priceConfirmationLabel.setBounds(10,50,200,25);

        confirmationPanel.add(nameConfirmationLabel);
        confirmationPanel.add(idConfirmationLabel);
        confirmationPanel.add(priceConfirmationLabel);

        setupAddProductButton(p);
        setupDiscardProductButton();
    }

    //MODIFIES: this
    //EFFECTS: sets up the add product button and associated on-click event
    private void setupAddProductButton(Product p) {
        JButton confirmAdd = new JButton("Add to Catalogue");
        confirmAdd.setBounds(5, 90, 150, 25);
        confirmAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("AddToCatalogue Action (ProductConfirmation Window)");
                confirmationFrame.dispose();
                productSystem.addProduct(p);
                JOptionPane.showMessageDialog(frame, "Product Added!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        confirmationPanel.add(confirmAdd);
    }

    //MODIFIES: this
    //EFFECTS: sets up the disard product button and associated on-click event
    private void setupDiscardProductButton() {
        JButton discard = new JButton("Discard");
        discard.setBounds(215, 90, 80, 25);
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DiscardProduct Action (ProductConfirmation Window)");
                confirmationFrame.dispose();
                JOptionPane.showMessageDialog(frame, "Product Discarded",
                        "Cancelled", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        confirmationPanel.add(discard);
    }
}
