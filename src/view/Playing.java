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
import static main1.GameStates.*;

public class Playing extends GameScene implements SceneMethods {
    private BufferedImage img;
	private Random random;
    private MyButton bMenu;


    
    public Playing(GamePanel game){
        super(game);
        importImg();
        random = new Random();
		initButtons();
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
        //g.drawImage(img, 0, 0, null);

			for (int i = 0; i < 20; i++) {// si on fait moins que< 32 camarche
				g.setColor(getRandomColor());//changer la coleur dudessin
				g.fillRect(i*32, 0, 32, 32);
			}
			for (int y = 0; y < 20; y++) {
				g.setColor(getRandomColor());//changer la coleur dudessin
				g.fillRect(0, y*32, 32, 32);
			}
        drawButtons(g);


    }
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);

	}

    
    public Color getRandomColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

	private void importImg() {

		InputStream is = getClass().getResourceAsStream("/res/mainBG.png");

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
