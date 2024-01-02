package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import main1.Game;

public class Playing extends GameScene implements SceneMethods {

    private BufferedImage img;
    private Random random ;


    public Playing(Game game) {
        super(game);
        importImg();
        random = new Random();
        
    }
    private void importImg() {

		InputStream is = getClass().getResourceAsStream("/res/menu.jpg");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void callMe() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'callMe'");
    }
    
}
