package ui.control.states;

import java.util.ArrayList;

import static ui.control.states.GameStates.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import java.awt.Rectangle;
import model.*;
import model.entity.Monster;
import model.entity.Projectil;
import model.entity.Tower;
import model.entity.TowerType;
import ui.control.MyButton;
import ui.view.GameFrame;
import ui.view.GameScene;
import ui.view.SceneMethods;

public class Playing extends GameScene implements SceneMethods {
    private BufferedImage img;
    private MyButton bMenu;
	private GameFrame gameFrame;

	//IMAGE DES TOWERS
	private BufferedImage towerImage1; // Image pour la Tour 1
	private BufferedImage magicTower;
	private BufferedImage wall;
	//IMAGE PROJECTILS
	private BufferedImage archerProj;
	private ImageIcon magicProj;

	//IMAGE DES MONSTRES
	private BufferedImage fbug;
	private ImageIcon gobelinRun;
	private ImageIcon skeletonWalk;
	private ImageIcon crusherRun;



	//POSITION POUR CHAQUE TOWER

	//ARCHER
	private int archerTowerX = 113;
	private int archerTowerY = 7;
	private final int fArcherTowerX = 113;
	private final int fArcherTowerY = 7;
	private Rectangle archerBounds; 

	//MAGIC
	private int magicTowerX = 193;
	private int magicTowerY = 7;	
	private final int fMagicTowerX = 193;
	private final int fMagicTowerY = 7;	
	private Rectangle magicBounds;

	//WALL
	private int wallX = 273;
	private int wallY = 7;
	private final int fWallX = 273;
	private final int fWallY = 7;
	private Rectangle wallBounds;

    public Playing(GameFrame gameFrame){
        super(gameFrame);
		this.gameFrame = gameFrame;

		movingArcher = false;
		movingMagic = false;
		movingWall = false;

		initButtons();
		importImg();	

		loadTowerImages();
		loadMagicTower();
		loadWall();

		loadFbugImages();
		loadGobelin();
		loadSkeletonWalk();
		loadCrusherRun();


		loadArcherProj();
		loadMagicProj();
		
    }
	

	

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int newWidth = 975;  // Nouvelle largeur souhaitée
        int newHeight = 650; // Nouvelle hauteur souhaitée

		

    // Redimensionner et dessiner l'image²
    if (img != null) {
        g2d.drawImage(img, 0, 0, newWidth, newHeight, null);
    }
		drawButtons(g);// dessiner le bouton menu qui permet de revenirsur l'etat menu
		
	// parcourir la map et afficher les tours sur 
		for(int i=0;i<gameFrame.getGame().getMap().getMap().length;i++){
			for(int j=0;j<gameFrame.getGame().getMap().getMap()[i].length;j++){
				if(gameFrame.getGame().getMap().getMap()[i][j]!=null){
					BufferedImage typeImage = towerImage1;
					switch (gameFrame.getGame().getMap().getMap()[i][j].getType()) {
						case WALL:
							typeImage = wall;
							break;
						case MAGICIAN:
							typeImage = magicTower;	
						default:
							break;
					}
					g.drawImage(typeImage,(int)gameFrame.getGame().getMap().getMapPlacement()[i][j].getX(),(int)//dessiner sur la cellule correspondante avec la pos I et J  
					 gameFrame.getGame().getMap().getMapPlacement()[i][j].getY(), null);// en allant sur le tableant de mapplacement a la pos [i][j]
				}
			}
		}
// verifier le coordonner acteul et afficher les monstres 
		for(ArrayList<Monster> lane : gameFrame.getGame().getMap().getMonstersLanes()){
			for(Monster m : lane){
				//g.drawImage(fbug,(int)m.getOnScreenCoord().getX(),(int)m.getOnScreenCoord().getY(), null);
				switch (m.getType()) {
					case CRUSHER:
						crusherRun.paintIcon(this, g, (int)m.getOnScreenCoord().getX(), (int)m.getOnScreenCoord().getY());
						break;
				
					case SKELETON:
						skeletonWalk.paintIcon(this, g, (int)m.getOnScreenCoord().getX(), (int)m.getOnScreenCoord().getY());
						break;
					case GOBELIN:
						gobelinRun.paintIcon(this, g, (int)m.getOnScreenCoord().getX(), (int)m.getOnScreenCoord().getY());
				}
				
			}
		}

		for(Projectil proj : gameFrame.getGame().getCurrentProjectils()){
			if(proj.getType()==TowerType.ARCHER)
				g.drawImage(archerProj,(int) proj.getOnScreenCoord().getX(), (int) proj.getOnScreenCoord().getY(), null);
			else magicProj.paintIcon(this, g2d, (int) proj.getOnScreenCoord().getX(), (int) proj.getOnScreenCoord().getY());
		}
		g.drawImage(towerImage1, archerTowerX, archerTowerY, null);
		g.drawImage(magicTower, magicTowerX, magicTowerY, null);
		g.drawImage(wall, wallX, wallY,null);

    }

	

	

	private void loadTowerImages() {
       

		InputStream is = getClass().getResourceAsStream("/res/tour1.png");

		try {
			towerImage1 = ImageIO.read(is);
			archerBounds = new Rectangle(archerTowerX, archerTowerY, towerImage1.getWidth(), towerImage1.getHeight());

		} catch (IOException e) {
			e.printStackTrace();
		}

	
    }

	private void loadFbugImages() {
       

		InputStream is = getClass().getResourceAsStream("/res/fbug3.png");

		try {
			fbug = ImageIO.read(is);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	
    }

	private void loadGobelin() {
        gobelinRun = new ImageIcon(getClass().getResource("/res/gobelinRun.gif"));
    }

	private void loadSkeletonWalk(){
		skeletonWalk = new ImageIcon(getClass().getResource("/res/skelwalk.gif"));
	}

	private void loadCrusherRun(){
		crusherRun = new ImageIcon(getClass().getResource("/res/crusherRun.gif"));
	}

	private void loadMagicProj(){
		magicProj = new ImageIcon(getClass().getResource("/res/magicProjectil.gif"));
	}


	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);
	}

	private void importImg() {

		InputStream is = getClass().getResourceAsStream("/res/waterbg.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadMagicTower(){
		InputStream is = getClass().getResourceAsStream("/res/MagicTower.png");

		try {
			magicTower = ImageIO.read(is);
			magicBounds = new Rectangle(magicTowerX, magicTowerY, magicTower.getWidth(), magicTower.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void loadWall(){
		InputStream is = getClass().getResourceAsStream("/res/wall.png");

		try {
			wall = ImageIO.read(is);
			wallBounds = new Rectangle(wallX,wallY, wall.getWidth(), wall.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void loadArcherProj(){
		InputStream is = getClass().getResourceAsStream("/res/proj1.png");

		try {
			archerProj = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

    //getters
    public BufferedImage getImg() {
        return img;
    }

    private void drawButtons(Graphics g) {
		bMenu.draw(g);

	}

	private boolean movingArcher;
	private boolean movingMagic;
	private boolean movingWall;

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBoundsRect().contains(x, y)){
			changeGameState(MENU);
			//SetGamesState(MENU);
		}
		if(movingArcher){// si on est en train de deplacer la tour on la replace a sa position initiale
			movingArcher = false;
			System.out.println("released moving:"+movingArcher);
			archerTowerX = fArcherTowerX;
			archerTowerY = fArcherTowerY;
			if(gameFrame.getGame().getMap().canPlace(x, y)){//si on peut placer une tour
				OnScreenCoord placement = gameFrame.getGame().getMap().wherePlace(x, y);
				gameFrame.getGame().placeTower((int)placement.getX(), (int)placement.getY(), new Tower(TowerType.ARCHER));
			}
		}
		if(movingMagic){
			movingMagic = false;
			System.out.println("released moving:"+movingMagic);
			magicTowerX = fMagicTowerX;
			magicTowerY = fMagicTowerY;
			if(gameFrame.getGame().getMap().canPlace(x, y)){
				OnScreenCoord placement = gameFrame.getGame().getMap().wherePlace(x, y);
				gameFrame.getGame().placeTower((int)placement.getX(), (int)placement.getY(), new Tower(TowerType.MAGICIAN));
			}
		}
		if(movingWall){
			movingWall = false;
			System.out.println("released moving:"+movingWall);
			wallX = fWallX;
			wallY= fWallY;
			if(gameFrame.getGame().getMap().canPlace(x, y)){
				OnScreenCoord placement = gameFrame.getGame().getMap().wherePlace(x, y);
				gameFrame.getGame().placeTower((int)placement.getX(), (int)placement.getY(), new Tower(TowerType.WALL));
			}
		}

		if(archerBounds.getBounds().contains(x, y)&&(!movingArcher)){
			movingArcher = true;
			System.out.print("cliked  moving:"+movingArcher);
		}

		if(magicBounds.getBounds().contains(x, y)&&(!movingMagic)){
			movingMagic = true;
			System.out.print("cliked  moving:"+movingMagic);
		}
		if(wallBounds.getBounds().contains(x, y)&&(!movingWall)){
			movingWall = true;
			System.out.print("cliked  moving:"+movingWall);
		}

		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBoundsRect().contains(x, y))
			bMenu.setMouseOver(true);
		if(movingArcher){
			archerTowerX = x - towerImage1.getWidth() / 2;
			archerTowerY = y - towerImage1.getHeight() / 2;
		}
		if(movingMagic){
			magicTowerX = x -magicTower.getWidth()/2;
			magicTowerY = y -magicTower.getHeight()/2;
		}
		if(movingWall){
			wallX = x -wall.getWidth()/2;
			wallY = y -wall.getHeight()/2;
		}
		//System.out.println("x:"+x+"y:"+y);
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBoundsRect().contains(x, y))
			bMenu.setMousePressed(true);


	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();

	}
	
	

}
