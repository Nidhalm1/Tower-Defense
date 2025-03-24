package ui.view;

public class GameLauncher {
    public static void main(String[] args) {

		GameFrame gameFrame = new GameFrame();
		gameFrame.getGameScreen().initInputs();
		gameFrame.start();

	}
}
