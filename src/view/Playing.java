package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import control.MyButton;
import main1.GamePanel;
import main1.MapStates;

import static main1.GameStates.*;
import static main1.MapStates.MAP1;

public class Playing   implements SceneMethods {
    private BufferedImage img;
	private Random random;
    private MyButton bMenu;

	private BufferedImage towerImage1; // Image pour la Tour 1


    
    public Playing(GamePanel game){
        updateMap();
        random = new Random();
		initButtons();
		loadTowerImages();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int newWidth = 975;  // Nouvelle largeur souhaitée
        int newHeight = 650; // Nouvelle hauteur souhaitée

    // Redimensionner et dessiner l'image
    if (img != null) {
        g2d.drawImage(img, 0, 0, newWidth, newHeight, null);
    }
			
        drawButtons(g);
		g.drawImage(towerImage1, 113, 5, null);


    }

	private void loadTowerImages() {
       

		InputStream is = getClass().getResourceAsStream("/res/tour1.png");

		try {
			towerImage1 = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		}

	
    }
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30, "");

	}

    
    public Color getRandomColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
	public void updateMap() {
		importImg();
	}
	
	private void importImg() {

		InputStream is ;
		if (MapStates.mapState==MAP1) {
			is= getClass().getResourceAsStream("/res/map1.png");
		}
		else{
			is= getClass().getResourceAsStream("/res/map2.png");
		}
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    //getters
    public BufferedImage getImg() {
        return img;
    }

    private void drawButtons(Graphics g) {
		bMenu.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBoundsRect().contains(x, y))
			SetGamesState(MENU);

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
