package main1;



import javax.swing.JPanel;

import control.KeyboardListener;
import control.MyMouseListener;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class GameScreen extends JPanel {
    
    private MyMouseListener myMouseListener ;
	private KeyboardListener keyboardListener;


    BufferedImage img ;


    private Dimension SIZE;

    GamePanel gamePanel ;

    public GameScreen(GamePanel gamePanel){// au cas ou pr game
        this.gamePanel = gamePanel;
       
        setPanelSize();
        
        
        
    }
    public void initInputs(){ /// pour gerer interaction pc utilisateur
		myMouseListener=new MyMouseListener(gamePanel);
		keyboardListener = new KeyboardListener();
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);
		requestFocus();
	}

    public void setPanelSize(){
        //img = game.getMenu().getImg();
        SIZE = new Dimension(975,650);
        setMinimumSize(SIZE);
        setPreferredSize(SIZE);
        setMaximumSize(SIZE);

    }
    





    public void paintComponent(Graphics g){
        super.paintComponent(g);// obliger de mettre
        
        
        gamePanel.getRender().render(g);
    }

    
    
}
