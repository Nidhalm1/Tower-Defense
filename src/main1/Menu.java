package main1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
   
    JPanel buttonPlayPanel = new JPanel();
    JPanel buttonQuitPanel = new JPanel();

    JButton buttonPlay = new JButton("Jouer");
    JButton buttonQuit = new JButton("Quitter");



    JLabel title = new JLabel();
    JPanel titlePanel = new JPanel();


    // Second menu components
    JPanel difficultyPanel = new JPanel();
    JSlider difficultySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
    JCheckBox marathonModeCheckBox = new JCheckBox("Mode Marathon");
    JButton buttonPlayGame = new JButton("Jouer");

    public Menu(){
        this.setTitle("Tower Defense");
        this.setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new GridLayout(3,1));

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        this.title.setFont(titleFont);
        this.title.setText("Tower Defense");

    


        JLabel emptyLabel = new JLabel(" ");
        this.titlePanel.add(emptyLabel);
        this.titlePanel.add(title);


        

        
        this.buttonPlayPanel.add(buttonPlay);
        this.buttonQuitPanel.add(buttonQuit);

        this.getContentPane().add(titlePanel);
        this.getContentPane().add(buttonPlayPanel);
        this.getContentPane().add(buttonQuitPanel);

        buttonQuit.addActionListener(
            (ActionEvent e) -> {
                dispose();
                });

        
}
}
