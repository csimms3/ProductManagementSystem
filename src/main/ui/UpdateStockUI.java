package ui;

import model.Product;
import model.ProductManagementSystem;

import javax.swing.*;

//abstract ui for updating stock
public abstract class UpdateStockUI {

    protected static final int ELEMENT_HEIGHT = 25;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField inputField = new JTextField(3);

    Product product;

    //EFFECTS: initializes new stock update window
    public UpdateStockUI(Product p) {
        this.product = p;

        setupFrame();
        setupElements();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setSize(150,100);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up gui elements
    abstract void setupElements();
}
