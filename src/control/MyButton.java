package control;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class MyButton {

	private int x, y, width, height;
	private String text;
	private Rectangle boundsRect;
	private boolean mouseOver, mousePressed,isSelectedMap=false;
	private Image buttonImage;

	public MyButton(String text, int x, int y, int width, int height, String imagePath) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		initBoundsRect();
		loadImage(imagePath);
	}
	//charger les images des boutons
	private void loadImage (String imagePath){
		ImageIcon icon = new ImageIcon(imagePath);
		buttonImage = icon.getImage();
	}

	private void initBoundsRect() {
		this.boundsRect = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		// Body
		drawBody(g);

		// Border
		drawBorder(g);

		// Text
		drawText(g);
	}
	// pour le choix de map (si le bouton selectionné est dans Settings) (on l'appele depuis Settings)
	// ca sert pour garder un effet pour le bouton selectionné et pas l'autre
	public void SetIsSelected(boolean isSelected){ 
		this.isSelectedMap = isSelected;
	}

	private void drawBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		drawShadow(g2d);
        
		g.drawRect(x, y, width, height);
		if (mousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}
	}
	private void drawShadow(Graphics2D g2d) {
		if (isSelectedMap) {
			g2d.setColor(new Color(0, 0, 0, 50)); // Couleur d'ombre semi-transparente
			g2d.fillRect(x + 5, y + 5, width, height); // Position et taille de l'ombre
		}
	}

	private void drawBody(Graphics g) {
		if (buttonImage != null) {
			g.drawImage(buttonImage, x, y, width, height, null);
		} else {//si une image n'est pas chargée
			if (mouseOver)
				g.setColor(Color.gray);
			else
				g.setColor(Color.WHITE);
				//g.fillRect(x, y, width, height);
		}
		
    }

	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);

	}

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
  
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public Rectangle getBoundsRect() {
		return boundsRect;
	}

}

