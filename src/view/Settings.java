package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;

import main1.GamePanel;
import control.MyButton;

import static main1.GameStates.*;
import static main1.MapStates.*;


public class Settings  implements SceneMethods {

	private MyButton bMenu;
	public JSlider slider;
	public JLabel difficultyLabel;
	public JLabel mapLabel;
	public JLabel ocean;
	public JLabel foret;

	private GamePanel gamePanel;
	private Image img ;
	private MyButton bMap1,bMap2;



	public Settings(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		initButtons();
		initSlider();
		initlabels();
		jpanelvisibleF();
		addPanelslabels();
		loadImage("res/set.png");


	}
	private void addPanelslabels(){
		gamePanel.add(slider); // Ajouter le curseur par tout
		gamePanel.add(difficultyLabel);
		gamePanel.add(mapLabel);
		gamePanel.add(ocean);
		gamePanel.add(foret);
	}
	private void jpanelvisibleF (){
		slider.setVisible(false); // Rendre le curseur invisible par défaut
		difficultyLabel.setVisible(false);
		mapLabel.setVisible(false);
		ocean.setVisible(false);
		foret.setVisible(false);
		
	}

	private void loadImage (String imagePath){
		ImageIcon icon = new ImageIcon(imagePath);
		img = icon.getImage();
	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30,"res/bmenu.png");
		bMap2 = new MyButton("", 495, 210, 240, 275,"res/bmap2.png");
		bMap1 = new MyButton("", 245, 210, 240, 275,"res/bmap1.png");
	}

	private void initlabels(){
		//pour le mot diffuculté
		difficultyLabel = new JLabel("Difficulté :");// Rendre le texte invisible par défaut
		difficultyLabel.setBounds(325, 15, 100, 50); // x, y, largeur, hauteur
		difficultyLabel.setFont(new Font("Serif", Font.ITALIC, 20));
		difficultyLabel.setForeground(new Color(128, 0, 0)); // Couleur personnalisée
		gamePanel.add(difficultyLabel);

		//pour le mot "choisie ta map"
		mapLabel= new JLabel("CHOISIS TA MAP ");// Rendre le texte invisible par défaut
		mapLabel.setBounds(370, 155, 500, 50); // x, y, largeur, hauteur
		mapLabel.setFont(new Font("Serif", Font.ITALIC, 30));
		mapLabel.setForeground(new Color(128, 0, 0)); // Couleur personnalisée
		gamePanel.add(mapLabel);

		//pour le mot "ocean"
		ocean= new JLabel("OCEAN ");// Rendre le texte invisible par défaut
		ocean.setBounds(550, 480, 500, 50); // x, y, largeur, hauteur
		ocean.setFont(new Font("Serif", Font.ITALIC, 26));
		ocean.setForeground(new Color(128, 0, 0)); // Couleur personnalisée
		gamePanel.add(ocean);

		//pour le mot "foret"
		foret= new JLabel("FORET ");// Rendre le texte invisible par défaut
		foret.setBounds(310, 480, 500, 50); // x, y, largeur, hauteur
		foret.setFont(new Font("Serif", Font.ITALIC, 26));
		foret.setForeground(new Color(128, 0, 0)); // Couleur personnalisée
		gamePanel.add(foret);

	}



	// pour le slider 
	private void initSlider() {
		
		slider = new JSlider(JSlider.HORIZONTAL, 1, 3, 2); // min, max, valeur par défaut
		slider.setBounds(410, 30, 200, 40); // x, y, width, height

		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();// Créer un dictionnaire pour les labels
		labelTable.put(1, createStyledLabel("Facile"));
		labelTable.put(2, createStyledLabel("Moyen"));
		labelTable.put(3,createStyledLabel("Difficile"));


		slider.setLabelTable(labelTable);// Assigner les labels au curseur
		slider.setPaintLabels(true);
		slider.setOpaque(false);// rendre le carré du curseur transparent

	}
	//style du texte du slider
	private JLabel createStyledLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Serif", Font.ITALIC, 14)); // Police et taille
		label.setForeground(new Color(100, 0, 0)); // Couleur du texte
		Font currentFont = label.getFont();//mettre en gras
    	label.setFont(currentFont.deriveFont(Font.BOLD));
		return label;
	}


	@Override
	public void render(Graphics g) {
		// Dessiner l'image de fond
		if (img != null) {
			g.drawImage(img, 0, 0, gamePanel.getWidth(), gamePanel.getHeight()+25, null);
		}
		drawButtons(g);
		jpanelvisibleT();	// rendre les composants visible pendant l'état Settings

		

	}
	// rendre les composants visible pendant l'état Settings
	private void jpanelvisibleT(){
		slider.setVisible(true); // Rendre le curseur visible lorsque on est dans l'état Settings
		difficultyLabel.setVisible(true); // Rendre le texte visible lorsque on est dans l'état Setting
		mapLabel.setVisible(true);
		ocean.setVisible(true);
		foret.setVisible(true);
		
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bMap1.draw(g);
		bMap2.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBoundsRect().contains(x, y))
			SetGamesState(MENU);
			
		
		if (bMap1.getBoundsRect().contains(x, y)){
			SetMapState(MAP1);
    		gamePanel.getPlaying().updateMap();// dans cet ordre pour appeler updatemap sur la bonne map avec la bonne etat selectioné map1 ou map2
			bMap1.SetIsSelected(true);
			bMap2.SetIsSelected(false);
		}
		if (bMap2.getBoundsRect().contains(x, y)) {
			SetMapState(MAP2);
			bMap2.SetIsSelected(true);
			bMap1.SetIsSelected(false);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBoundsRect().contains(x, y))
			bMenu.setMouseOver(true);
		bMap1.setMouseOver(false);
		if (bMap1.getBoundsRect().contains(x, y))
			bMap1.setMouseOver(true);

		

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBoundsRect().contains(x, y))
			bMenu.setMousePressed(true);
		if (bMap1.getBoundsRect().contains(x, y))
			bMap1.setMousePressed(true);
		if (bMap2.getBoundsRect().contains(x, y))
			bMap2.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();
		bMap1.resetBooleans();
		bMap2.resetBooleans();

	}

}
