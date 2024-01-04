
package main1;





import javax.swing.JFrame;



import view.Menu; // Remove this line to resolve the import statement collision
import view.Playing;
import view.SecondMenu;
import view.Settings;




public class GamePanel extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	

	private Render render ;

	//classes etats
	private Menu menu;
	private Playing Playing;
	private Settings Settings;
	private SecondMenu secondMenu;
	// version terminal
	private int difficulty; // ENTIER POUVANT ALLER DE 1 à 3 (FACILE,MOYEN,DIFFICILE)
	

	// version graphic
	public GamePanel() {
		

		initClasses();
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
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

		gameScreen = new GameScreen(this);// initailiser avec importimg();

	}


	
	

	

	public void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void updateGame() {

		// System.out.println("Game Updated!");
	}

	

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		while (true) {
			long now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();//combien de temps pour chaque image pour atteindre 60 fps par exemple
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
				System.out.println("FPS: " + frames + " | UPS: " + updates);
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