package telas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface Tela extends KeyListener {
	
	abstract void paintComponent(Graphics g);
	abstract void updateGame();
	abstract void paint(Graphics g);
	abstract void keyPressed(KeyEvent e);
	abstract void keyTyped(KeyEvent arg0);
	abstract void keyReleased(KeyEvent e);
	
}
