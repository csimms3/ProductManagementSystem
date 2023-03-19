package ui;

import javax.swing.*;

//GUI for searching for products
public class ProductSearchUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField searchBox = new JTextField(3);
    JLabel instructions = new JLabel("Enter 4-digit Product ID:");
    JLabel exampleLabel = new JLabel("Example: 0000");

    public ProductSearchUI() {
        setupFrame();
        setupElements();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    private void setupFrame() {
        frame.setTitle("Search");
        frame.setSize(250,100);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    private void setupElements() {
        panel.setLayout(null);

        instructions.setBounds(20,5,200,25);
        searchBox.setBounds(180,5,50,25);

        exampleLabel.setBounds(75,30, 100, 25);


        panel.add(instructions);
        panel.add(exampleLabel);
        panel.add(searchBox);
    }
}
