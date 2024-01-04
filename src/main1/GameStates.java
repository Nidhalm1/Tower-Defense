package main1;
public enum GameStates {
    PLAYING,
    MENU,SECONDMENU,
    SETTINGS;

	public static GameStates gameState = MENU;
    public static void SetGamesState(GameStates state) {
        gameState= state;
    }


}
