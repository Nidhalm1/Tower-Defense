package view;


import main1.GamePanel;

public class GameScene {

	private GamePanel gamePanel;

	public GameScene(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public GamePanel getGame() {
		return gamePanel;
	}
}
