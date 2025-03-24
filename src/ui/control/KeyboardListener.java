package ui.control;

import static ui.control.states.GameStates.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ui.control.states.GameStates;



public class KeyboardListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A)
			GameStates.gameState = MENU;

		else if (e.getKeyCode() == KeyEvent.VK_S)
			GameStates.gameState = PLAYING;

		else if (e.getKeyCode() == KeyEvent.VK_D)
			GameStates.gameState = SETTINGS;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}