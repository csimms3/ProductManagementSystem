package ui;

import javax.swing.*;

//GUI for searching for products
public class ProductSearchUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField searchBox = new JTextField(3);
    JLabel instructions = new JLabel("Enter 4-digit Product ID:");
    JLabel exampleLabel = new JLabel("Example: 0000");
    JButton searchButton = new JButton("Search");

    //EFFECTS: initializes new search window
    public ProductSearchUI() {
        setupFrame();
        setupElements();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle("Search");
        frame.setSize(250,125);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up gui elements
    private void setupElements() {
        panel.setLayout(null);

        instructions.setBounds(20,5,200,25);
        searchBox.setBounds(180,5,50,25);
        exampleLabel.setBounds(75,35, 100, 25);
        searchButton.setBounds(85, 65, 80, 25);

        panel.add(instructions);
        panel.add(exampleLabel);
        panel.add(searchBox);
        panel.add(searchButton);

    }
}
