package main1;

import java.awt.Graphics;


public class Render {

	private GamePanel gamePanel;
	

	public Render(GamePanel gamePanel) {
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

		}

	}
	

}
