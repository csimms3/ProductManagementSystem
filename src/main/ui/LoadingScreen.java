package ui;

import model.Product;
import model.ProductManagementSystem;

import javax.swing.*;
import java.io.File;

//ui for displaying an image on screen for a certain time
public class LoadingScreen {

    ImageIcon img = new ImageIcon("./data/img.png");
    JLabel displayImage = new JLabel(img);

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    int delayTime;

    //EFFECTS: initializes new loading screen window
    public LoadingScreen(int delay) {
        delayTime = delay;

        setupElements();
        setupFrame();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            Thread.sleep(delayTime);
            frame.dispose();
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // he he;
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up frame and associated elements
    private void setupFrame() {
        frame.setTitle("Loading...");
        frame.setVisible(false);
        frame.setSize(330,220);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: sets up gui elements
    void setupElements() {

        displayImage.setBounds(5,0, 320, 220);

        panel.add(displayImage);

    }
}
