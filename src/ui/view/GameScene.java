package ui.view;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameScene extends JPanel {

	private GameFrame gamePanel;

	public GameScene(GameFrame gamePanel) {
		this.gamePanel = gamePanel;
	}

	public GameFrame getGame() {
		return gamePanel;
	}
}
