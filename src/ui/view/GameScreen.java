package ui.view;

import javax.swing.JPanel;

import ui.control.KeyboardListener;
import ui.control.MyMouseListener;
import ui.control.states.GameStates;
import ui.control.states.PlayingPanel;

import static ui.control.states.GameStates.gameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;


public class GameScreen extends JPanel {
    
    private MyMouseListener myMouseListener ;
	private KeyboardListener keyboardListener;
    BufferedImage img ;

    

    public static PlayingPanel getPlayingPanel() {
        return playingPanel;
    }

    private Dimension SIZE;

    GameFrame gameFrame ;
    public static PlayingPanel playingPanel;

    public GameScreen(GameFrame gameFrame){// au cas ou pr game
        playingPanel = new PlayingPanel(gameFrame);
        this.gameFrame = gameFrame;
        System.out.println(this.getLayout());
        setPanelSize();
        playingPanel.setPreferredSize(new Dimension(200,40));
        add(playingPanel,0);
        playingPanel.setVisible(false);
        
        



    }

    public void initInputs(){ /// pour gerer interaction pc utilisateur
		myMouseListener=new MyMouseListener(gameFrame);
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
        
        
        gameFrame.getRender().render(g);
        playingPanel.updateMoney(gameFrame.getGame().getPlayer().getMoney());
    }

    
    
}
