package ui.control.states;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.awt.Graphics2D;

import static ui.control.states.GameStates.*;

import java.awt.BorderLayout;
import javax.imageio.ImageIO;



import ui.control.MyButton;
import ui.view.GameFrame;
import ui.view.GameScene;
import ui.view.SceneMethods;

public class GameOver extends GameScene implements SceneMethods {
    private BufferedImage img;
    private MyButton bMenu;

    public GameOver(GameFrame gameFrame){
        super(gameFrame);
        importImg();
        initButtons();

    }

    private void initButtons() {
		bMenu = new MyButton("Menu", 427, 600, 100, 30);
	}

    private void importImg() {

		InputStream is = getClass().getResourceAsStream("/res/gameover.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int newWidth = 975;  // Nouvelle largeur souhaitée
        int newHeight = 650;
        if (img != null) {
            g2d.drawImage(img, 0, 0, newWidth, newHeight, null);
        }
            drawButtons(g);
        
    }
    private void drawButtons(Graphics g) {
		bMenu.draw(g);

	}

    @Override
    public void mouseClicked(int x, int y) {
        System.out.println("clicked");
        if (bMenu.getBoundsRect().contains(x, y)){
			changeGameState(MENU);
		}
      
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
		if (bMenu.getBoundsRect().contains(x, y))
			bMenu.setMouseOver(true);
   
    }

    @Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBoundsRect().contains(x, y))
			bMenu.setMousePressed(true);


	}

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    
}
}