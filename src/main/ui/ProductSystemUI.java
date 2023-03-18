package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSystemUI extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField field;

    public ProductSystemUI() {
        super("Product System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton btn = new JButton("Change");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);

        JButton btn2 = new JButton("View Product");
        btn2.setActionCommand("hm");
        btn2.addActionListener(this);
        label = new JLabel("flag");
        field = new JTextField(5);
        add(field);
        add(btn);
        add(label);
        add(btn2);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    public static void main(String[] args) {
        new ProductSystemUI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
