package view;

import java.awt.Graphics;
import control.MyButton;
import main1.GamePanel;
import static main1.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

    private MyButton bPlaying, bSettings, bQuit;



    public Menu(GamePanel gamePanel) {
        super(gamePanel);
        initButtons();

        
    }
    private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 975 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("Jouer", x, y, w, h);
		bSettings = new MyButton("Parametre", x, y + yOffset, w, h);
		bQuit = new MyButton("Quitter", x, y + yOffset * 2, w, h);

	}

   
    @Override
    public void render(Graphics g) {
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
