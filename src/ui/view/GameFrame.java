
package ui.view;

import static ui.control.states.GameStates.GAMEOVER;
import static ui.control.states.GameStates.PLAYING;

import javax.swing.JFrame;

import model.*;
import ui.control.states.GameOver;
import ui.control.states.GameStates;
import ui.control.states.Menu;
import ui.control.states.Playing;
import ui.control.states.SecondMenu;
import ui.control.states.Settings;
import ui.control.states.GameOver;




public class GameFrame extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	GameStates currentState;
	
	private Render render ;

	private Game game;

	public Game getGame() {
		return game;
	}
	
	
	//classes etats
	private Menu menu;
	private Playing Playing;
	private Settings Settings;
	private SecondMenu secondMenu;
	private GameOver gameOver;
	// version terminal
	private int difficulty; // ENTIER POUVANT ALLER DE 1 à 3 (FACILE,MOYEN,DIFFICILE)
	

	// version graphic
	public GameFrame() {
		

		initClasses();
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		add(gameScreen);
		pack(); // ou setSize(width, height);
		setLocationRelativeTo(null); // Centre la fenêtre après avoir défini la taille.
		setVisible(true); // Rend la fenêtre visible après le positionnement.
		
	}


	public void initClasses(){
		render = new Render(this);
		
		menu = new Menu(this);
		secondMenu = new SecondMenu(this);
		Playing = new Playing(this);
		Settings =new Settings(this);
		game = new Game(true,false);
		gameScreen = new GameScreen(this);// initailiser avec importimg();
		gameOver = new GameOver(this);
		

	}


	
	

	

	public void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private int numberGen = 1;
	private boolean monsterTest = true;
	private void updateGame() {
		
		


		if(GameStates.gameState==PLAYING){
			if(gen){
				System.out.println("ajout monstre");
				for(int i=0;i<numberGen;i++){
					game.genrerateMonster();// genrer  2 ensuite 3 ensuite 4 toute les 4s
				}
				
				
				gen = false;
			}
		//System.out.println("Game Updated!");
			game.getMap().move();
			game.moveProjectils();
			game.fight(shoot,shoot);
			shoot = false;
			
			if(game.gameLost()){
				GameStates.changeGameState(GAMEOVER);
			}
		
		//game.fight();
		}

	}

	private boolean shoot = false;
	private boolean gen = false;

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		long lastFightTime = System.nanoTime();
		long lastGenTime = System.nanoTime();
		long marathonTime = System.nanoTime();

		int frames = 0;
		int updates = 0;

		while (true) {
			long now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {//si le temps passé depasse le temps par frame qu 'il faut pour avoir 60 fps' on repaint
				repaint();//repaint tout les 16 miliseceond pour atteindre 60 fps
				lastFrame = now;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				updateGame(); 
				lastUpdate = now;
				updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				//System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

			if (now - lastFightTime >= 700000000) { 
				shoot = true; // le temps qu 'il faut pour tirer'
				lastFightTime = now;
			}

			if(now - lastGenTime >= 4000000000L) {
				gen = true;// le temps qu 'il faut pour generer un monstre'
				lastGenTime = now;
			}

			if(now - marathonTime >= 24000000000L && numberGen<8){
				numberGen++;
				marathonTime = now;
			}
	
			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

		}

	}
	public Render getRender(){
		return this.render;
	}
	
    
	//getters
	public Menu getMenu(){
		return this.menu;	
		
	}
	public SecondMenu getSecondMenu(){
		return this.secondMenu;	
		
	}

	public GameOver getGameOver(){
		return this.gameOver;
	}
	
	public Playing getPlaying(){
		return this.Playing;	
		
	}
	public Settings getSettings(){
		return this.Settings;	
		
	}
	//getters
	public GameScreen getGameScreen(){
		return this.gameScreen;	
		
	}


}