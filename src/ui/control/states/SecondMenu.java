package ui.control.states;

import static ui.control.states.GameStates.*;

import java.awt.Graphics;

import ui.control.MyButton;
import ui.view.GameFrame;
import ui.view.GameScene;
import ui.view.SceneMethods;

public class SecondMenu extends GameScene implements SceneMethods {

    private MyButton bMarathon, BNormal, bRevenir;
	private GameFrame gameFrame;


    public SecondMenu(GameFrame gameFrame) {
        super(gameFrame);
		this.gameFrame = gameFrame;
        initButtons();

        
    }
    private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 975 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bMarathon = new MyButton("Mode Marathon", x, y, w, h);
		BNormal = new MyButton("Mode Normal", x, y + yOffset, w, h);
		bRevenir = new MyButton("Revenir", x, y + yOffset * 2, w, h);

	}

   
    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }
    private void drawButtons(Graphics g) {
		bMarathon.draw(g);
		BNormal.draw(g);
		bRevenir.draw(g);

	}
    @Override
	public void mouseClicked(int x, int y) {
		
		if (bMarathon.getBoundsRect().contains(x, y)) {
			gameFrame.getGame().setMarathon(true);
			changeGameState(PLAYING);
			//SetGamesState(PLAYING);
		} else if (BNormal.getBoundsRect().contains(x, y)) {
			gameFrame.getGame().setMarathon(true);
			changeGameState(PLAYING);
			//SetGamesState(PLAYING);
		} else if (bRevenir.getBoundsRect().contains(x, y)){
			changeGameState(MENU);
			//SetGamesState(MENU);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMarathon.setMouseOver(false);
		BNormal.setMouseOver(false);
		bRevenir.setMouseOver(false);

		if (bMarathon.getBoundsRect().contains(x, y)) {
			bMarathon.setMouseOver(true);
		} else if (BNormal.getBoundsRect().contains(x, y)) {
			BNormal.setMouseOver(true);
		} else if (bRevenir.getBoundsRect().contains(x, y)) {
			bRevenir.setMouseOver(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bMarathon.getBoundsRect().contains(x, y)) {
			bMarathon.setMousePressed(true);
		} else if (BNormal.getBoundsRect().contains(x, y)) {
			BNormal.setMousePressed(true);
		} else if (bRevenir.getBoundsRect().contains(x, y)) {
			bRevenir.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMarathon.resetBooleans();
		BNormal.resetBooleans();
		bRevenir.resetBooleans();
	}
    
}
