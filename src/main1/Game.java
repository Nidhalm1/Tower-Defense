
package main1;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import control.KeyboardListener;
import control.MyMouseListener;

import view.Menu; // Remove this line to resolve the import statement collision
import view.Playing;
import view.Settings;




public class Game extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	private MyMouseListener myMouseListener ;
	private KeyboardListener keyboardListener;

	private Render render ;

	//classes etats
	private Menu menu;
	private Playing Playing;
	private Settings Settings;
	// version terminal
	private Player player;
    private Map map;

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    

    public boolean gameLost() { // On verifie si des monstres ce trouvent sur la première colonne
        for (ArrayList<Monster> monstersLane : map.getMonstersLanes()) {
            for (Monster monster : monstersLane) {
                if(monster.getX() <= 0) 
                    return true;
            }
        }
        return false;
    }


    public void defense(int y) {// Defense des tours sur les monstres sur une ligne
        Tower[] towerLane = map.getMap()[y];
        ArrayList<Monster> monsterLane = map.getMonstersLanes().get(y);

        if (!monsterLane.isEmpty()) {
            for (int i = towerLane.length - 1; i >= 0; i--) {
                if (towerLane[i] != null) {
                    ArrayList<Monster> monsterRangeLane = map.inRangeLane(y, towerLane[i]);//liste des monstres pouvant etre attaqué par la toure
                    if(!monsterRangeLane.isEmpty()){
                        Monster monsterToShoot = map.firstInLane(monsterRangeLane);// Le monstre a attaquer 
                        double nLife = monsterToShoot.getLife() - towerLane[i].getPower();
                        if (nLife <= 0) {
                            map.getMonstersLanes().get(y).remove(monsterToShoot);// LE MONSTRE EST MORT on (remove depuis map)
                            player.setMoney(player.getMoney()+20);// ajout d'argent au joueur quand monstre tué
                        } else monsterToShoot.setLife(nLife);
                    }
                }
            }
        }
    }


    //MODIFIER POUR QUE PLUSIEUR ATTAQUE EN MEME TEMPS
    public void consoleAttack(int y){
        ArrayList<Monster> monsterLane = map.getMonstersLanes().get(y);
        for(Monster monster : monsterLane){
            if(map.getMap()[y][(int)monster.getX()-1]!=null){
                double nLife = map.getMap()[y][(int)monster.getX()-1].getLife() - monster.getPower();
                if(nLife<=0){
                    map.destroyTower((int)monster.getX()-1, y);
                }else map.getMap()[y][(int)monster.getX()-1].setLife(nLife);
            }
        }

    }

    public void fight(){
        for(int i=0;i<map.getMap().length;i++){
            consoleAttack(i);
            defense(i);
        }
    }

    public boolean placeWithInput(String place){
        if(place.length()==2){
            if(place.charAt(0)>='A'&&place.charAt(0)<='E'&&((int)(place.charAt(1))-48)>0&&((int)(place.charAt(1))-48)<11){
                if(player.getMoney()>=20){
                    if(map.placeTower((int)(place.charAt(1))-49,(int)(place.charAt(0))-65,new Tower(20,4,20,2) ))
                        player.setMoney(player.getMoney()-20);
                    return true;
                }
                else System.out.println("Pas assez d'argent !"); return false;
            }
            else System.out.println("Coordonées pas sur la map !");return false;
        }
        else System.out.println("Veuiller rentrer des coordonées sous forme LettreChiffre ex:(B3)");return false;
    }

    public boolean response(String input){
        if(!input.equals("NEXT")){
            return placeWithInput(input);
        }
        else return true;
    }

    public void genrerateMonster(Monster monster){
            Random rd = new Random();
            int y = rd.nextInt(map.getMap().length);
            map.addMonster(map.getMap()[0].length-1,y,monster);
        }



    //Fonction de test pour verification
    public void verif(){
        System.out.println();
        for(ArrayList<Monster> lane  : map.getMonstersLanes()){
            for(Monster m : lane){
                System.out.print(" X: "+m.getX()+" ");
            }
        }
        System.out.println();
    }

 
	// version graphic
	public Game() {
		player = new Player();
        map = new Map();

		initClasses();

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
		add(gameScreen);
		//pack();
		setVisible(true);
		initInputs();

	}


	public void initClasses(){
		render = new Render(this);
		gameScreen = new GameScreen(this);// initailiser avec importimg();
		menu = new Menu(this);
		Playing = new Playing(this);
		Settings =new Settings(this);
	}


	private void initInputs(){ /// pour gerer interaction pc utilisateur
		myMouseListener=new MyMouseListener();
		keyboardListener = new KeyboardListener();
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);
		requestFocus();
	}

	

	

	private void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void updateGame() {

		// System.out.println("Game Updated!");
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.start();

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
	public Playing getPlaying(){
		return this.Playing;	
		
	}
	public Settings getSettings(){
		return this.Settings;	
		
	}
}