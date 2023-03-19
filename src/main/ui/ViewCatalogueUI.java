package ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//GUI for viewing product catalogue
public class ViewCatalogueUI {

    String[] testList = {"Product1", "Product 2"};

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JList displayList = new JList(testList);
    JLabel displayLabel = new JLabel("Products:");


    //EFFECTS: initializes new view catalogue ui
    public ViewCatalogueUI() {
        setupFrame();
        panel.setLayout(null);

        displayLabel.setBounds(115, 5, 80, 25);
        displayList.setBounds(115, 35, 80, 100);

        panel.add(displayLabel);
        panel.add(displayList);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle("Product Catalogue");
        frame.setSize(300,500);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }
}
