package ui.view;

import java.awt.Graphics;

import ui.control.states.GameStates;


public class Render {

	private GameFrame gamePanel;
	

	public Render(GameFrame gamePanel) {
		this.gamePanel = gamePanel;
		
	}

	public void render(Graphics g) {

		switch (GameStates.gameState) {

		case MENU:
		gamePanel.getMenu().render(g);

			break;
		case PLAYING:
			
		gamePanel.getPlaying().render(g);

			break;
		case SETTINGS:
			
		gamePanel.getSettings().render(g);

			break;
		case SECONDMENU:
			
		gamePanel.getSecondMenu().render(g);
			break;

		case GAMEOVER:
			
			gamePanel.getGameOver().render(g);
			

		}

	}
	

}
