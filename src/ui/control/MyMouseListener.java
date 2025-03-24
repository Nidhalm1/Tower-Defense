package ui.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ui.control.states.GameStates;
import ui.view.GameFrame;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private GameFrame gamePanel;

	public MyMouseListener(GameFrame gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			gamePanel.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			gamePanel.getPlaying().mouseMoved(e.getX(), e.getY());
			break;
		case SETTINGS:
			gamePanel.getSettings().mouseMoved(e.getX(), e.getY());
			break;
		case SECONDMENU:
			gamePanel.getSecondMenu().mouseMoved(e.getX(), e.getY());
			break;
		case GAMEOVER:
			gamePanel.getGameOver().mouseMoved(e.getX(), e.getY());	
		default:
			break;

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {

			switch (GameStates.gameState) {
			case MENU:
				gamePanel.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
				gamePanel.getPlaying().mouseClicked(e.getX(), e.getY());
				break;
			case SETTINGS:
				gamePanel.getSettings().mouseClicked(e.getX(), e.getY());
				break;
			case SECONDMENU:
				gamePanel.getSecondMenu().mouseClicked(e.getX(), e.getY());
			break;
			case GAMEOVER:
				gamePanel.getGameOver().mouseClicked(e.getX(), e.getY());		
				break;		
			default:
				break;

			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			gamePanel.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PLAYING:
			gamePanel.getPlaying().mousePressed(e.getX(), e.getY());
			break;
		case SETTINGS:
			gamePanel.getSettings().mousePressed(e.getX(), e.getY());
			break;
		case SECONDMENU:
			gamePanel.getSecondMenu().mousePressed(e.getX(), e.getY());
			break;
		case GAMEOVER:
			gamePanel.getGameOver().mousePressed(e.getX(), e.getY());		
			break;			
		default:
			break;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU:
			gamePanel.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			gamePanel.getPlaying().mouseReleased(e.getX(), e.getY());
			break;
		case SETTINGS:
			gamePanel.getSettings().mouseReleased(e.getX(), e.getY());
			break;
		case SECONDMENU:
			gamePanel.getSecondMenu().mouseReleased(e.getX(), e.getY());
			break;

		case GAMEOVER:
			gamePanel.getGameOver().mouseReleased(e.getX(), e.getY());		
			break;			
		default:
			break;

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}


