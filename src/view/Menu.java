package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import control.MyButton;
import main1.GamePanel;
import static main1.GameStates.*;

public class Menu   implements SceneMethods {

    private MyButton bPlaying, bSettings, bQuit;
	private GamePanel gamePanel;
	private Image img ;




    public Menu(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
        initButtons();
		loadImage("res/menu.png");
        
    }


    private void initButtons() {

		int w = 220;
		int h = w / 3;
		int x = 975 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("", x, y, w, h,"res/jouer.png");
		bSettings = new MyButton("", x, y + yOffset, w, h,"res/settings.png");
		bQuit = new MyButton("", x, y + yOffset * 2, w, h,"res/quitter.png");

	}
	private void loadImage (String imagePath){
		ImageIcon icon = new ImageIcon(imagePath);
		img = icon.getImage();
	}
   
    @Override
    public void render(Graphics g) {
        // Dessiner l'image de fond
		if (img != null) {
			g.drawImage(img, 0, 0, gamePanel.getWidth(), gamePanel.getHeight()+25, null);
		}

		// Ensuite, dessiner les boutons
		drawButtons(g);

		
    }
	
    private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);

	}
    @Override
	public void mouseClicked(int x, int y) {

		if (bPlaying.getBoundsRect().contains(x, y)) {
			SetGamesState(SECONDMENU);
		} else if (bSettings.getBoundsRect().contains(x, y)) {
			SetGamesState(SETTINGS);
		} else if (bQuit.getBoundsRect().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBoundsRect().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bSettings.getBoundsRect().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBoundsRect().contains(x, y)) {
			bQuit.setMouseOver(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBoundsRect().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bSettings.getBoundsRect().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBoundsRect().contains(x, y)) {
			bQuit.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}
    
}
