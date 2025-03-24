package ui.control.states;

import ui.view.GameScreen;

public enum GameStates {
    PLAYING,
    MENU,
    SECONDMENU,
    SETTINGS,
    GAMEOVER;

	public static GameStates gameState = MENU;
    public static void SetGamesState(GameStates state) {
        gameState= state;
        
    }

    public static void changeGameState(GameStates newState) {
        gameState = newState;

        if (gameState == GameStates.PLAYING) {
            GameScreen.getPlayingPanel().setVisible(true);
        } else {
            GameScreen.getPlayingPanel().setVisible(false);
        }
    }
}



