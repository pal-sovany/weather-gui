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

        frame.add(lblText);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        // JFrame f=new JFrame();//creating instance of JFrame  
          
        // JButton b=new JButton("click");//creating instance of JButton  
        // b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
                
        // f.add(b);//adding button in JFrame  
                
        // f.setSize(400,500);//400 width and 500 height  
        // f.setLayout(null);//using no layout managers  
        // f.setVisible(true);//making the frame visible  
    }
}
