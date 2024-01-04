package main1;

public class GameLauncher {
    public static void main(String[] args) {

		GamePanel gamePanel = new GamePanel();
		gamePanel.getGameScreen().initInputs();
		gamePanel.start();

	}
}
