package ui.control.states;
import java.awt.Color;
import javax.swing.JPanel;

import ui.view.GameFrame;

import javax.swing.JLabel;
import java.awt.Dimension;


public class PlayingPanel extends JPanel {
    private JLabel moneyLabel;

    public PlayingPanel(GameFrame gFrame) {

        setPreferredSize(new Dimension(600,600));
        String moneyText = "Money: " + gFrame.getGame().getPlayer().getMoney() +"$";
        moneyLabel = new JLabel(moneyText);
        moneyLabel.setBounds(480, 28, 100, 40); // Position et taille de l'étiquette
        add(moneyLabel);
        
        
    }

    public void updateMoney(int amount) {
        moneyLabel.setText("Money: "+amount+"$");
    }
}
