package com.palsovany;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World Swing Example");
        JLabel lblText = new JLabel("Hello World!");
        lblText.setBounds(130,100,100, 40);

        frame.add(lblText);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
