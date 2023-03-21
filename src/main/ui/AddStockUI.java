package ui;

import model.Product;

import javax.swing.*;

//TODO
//ui for adding stock to product
public class AddStockUI extends UpdateStockUI {

    public AddStockUI(Product p) {
        super(p);
    }

    @Override
    void setupElements() {
        JLabel promptLabel = new JLabel("Qty to add: ");
        panel.setLayout(null);
        promptLabel.setBounds(10,10,120,ELEMENT_HEIGHT);
        panel.add(promptLabel);
    }
}
