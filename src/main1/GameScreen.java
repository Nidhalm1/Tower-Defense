package main1;



import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;


public class GameScreen extends JPanel {
    private Random random ;
    
    private Long lastTime;
    private int frames;
    private double tempParImage;
    private long lastFrame;


    private Dimension SIZE;

    Game game ;

    public GameScreen(Game game){// au cas ou pr game
        random = new Random();
        setPanelSize();
        lastTime = System.currentTimeMillis(); // Initialisation de lastTime
        tempParImage = 1000000000.0/60.0;
        this.game = game;
    }
    public void setPanelSize(){
        SIZE = new Dimension(640,640);
        setMinimumSize(SIZE);
        setPreferredSize(SIZE);
        setMaximumSize(SIZE);

    }





    public void paintComponent(Graphics g){
        super.paintComponent(g);// obliger de mettre
        
        
        game.getRender().render(g);
    }
    
    
}
