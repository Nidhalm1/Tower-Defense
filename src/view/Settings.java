package view;

import java.awt.Color;
import java.awt.Graphics;

import main1.GamePanel;
import control.MyButton;

import static main1.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private MyButton bMenu;

	public Settings(GamePanel gamePanel) {
		super(gamePanel);
		initButtons();

	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 640);

		drawButtons(g);
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
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}

}
