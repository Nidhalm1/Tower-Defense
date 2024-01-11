package main1;

import java.awt.Graphics;
import java.util.Set;

import view.Settings;


public class Render {

	private GamePanel gamePanel;
	private Settings settings;
	

	public Render(GamePanel gamePanel, Settings settings) {
		this.gamePanel = gamePanel;
		this.settings = settings;
		
	}

	public void render(Graphics g) {

		switch (GameStates.gameState) {

		case MENU:
		gamePanel.getMenu().render(g);
		settings.slider.setVisible(false);
		settings.difficultyLabel.setVisible(false);// le rendre invisible pour tout les autres etats
		settings.mapLabel.setVisible(false);
		settings.foret.setVisible(false);
		settings.ocean.setVisible(false);


			break;
		case PLAYING:
			
		gamePanel.getPlaying().render(g);
		settings.slider.setVisible(false);
		settings.difficultyLabel.setVisible(false);
		settings.mapLabel.setVisible(false);
		settings.foret.setVisible(false);
		settings.ocean.setVisible(false);


			break;
		case SETTINGS:
			
		gamePanel.getSettings().render(g);

			break;
		case SECONDMENU:
			
		gamePanel.getSecondMenu().render(g);
		settings.slider.setVisible(false);
		settings.difficultyLabel.setVisible(false);
		settings.mapLabel.setVisible(false);
		settings.foret.setVisible(false);
		settings.ocean.setVisible(false);


		}

	}
	

}
