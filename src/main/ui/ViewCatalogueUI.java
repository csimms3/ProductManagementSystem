package ui;

import model.ProductManagementSystem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//GUI for viewing product catalogue
public class ViewCatalogueUI {

    String[] testList = {"Product1", "Product 2"};

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JList displayList;
    JLabel displayLabel = new JLabel("Products:");
    JScrollPane scrollPane;


    //EFFECTS: initializes new view catalogue ui
    public ViewCatalogueUI(ProductManagementSystem productSystem) {
        setupFrame();
        panel.setLayout(null);

        setupElements(productSystem);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up visual elements of catalogue viewer
    private void setupElements(ProductManagementSystem productSystem) {
        displayList = new JList(convertCatalogue(productSystem));
        scrollPane = new JScrollPane(displayList);

        displayLabel.setBounds(20, 5, 80, 25);
        scrollPane.setBounds(20, 35, 560, 200);

        panel.add(displayLabel);
        panel.add(scrollPane);
    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle("Product Catalogue");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //EFFECTS: converts given ProductManagementSystem into array of string to be displayed
    private String[] convertCatalogue(ProductManagementSystem productSystem) {
        String[] convertedCatalogue = new String[productSystem.getCatalogueSize()];

        for (int p = 0; p < productSystem.getCatalogueSize(); p++) {
            convertedCatalogue[p] = productSystem.getStringOfProductAtPosition(p);
        }

        return convertedCatalogue;
    }
}
