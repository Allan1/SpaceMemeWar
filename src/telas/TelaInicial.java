package telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TelaInicial extends JFrame implements Tela{
	
	private static final long serialVersionUID = 1L;
	protected Image image;
	protected Image image2;
	protected Graphics graphics;
	
	public TelaInicial(){
		addKeyListener(this);
		setTitle("Space Meme War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,500);
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER){
			setVisible(false);
			new MenuDir("images/jogador1.png");
		}
		
	}
	@Override
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		image2 = Toolkit.getDefaultToolkit().createImage("images/space2.jpg");
		graphics = image.getGraphics();
		paintComponent(graphics);
		g.drawImage(image, 0, 0, null);
		repaint();
	}

	public void paintComponent(Graphics g) {
		Dimension dDesktop = this.getSize();  		  
        double width = dDesktop.getWidth();  
        double height = dDesktop.getHeight();  
        Image background = new ImageIcon(this.image2.getScaledInstance((int) width, (int) height, 1)).getImage();  
        g.drawImage(background, 0, 0, this);
        g.drawImage(getImage("images/titulo.png"), 132, 183, null);
        g.setColor(Color.RED);
        g.drawString("Aperte [Enter] para começar", 270, 400);
        g.drawImage(getImage("images/trollface-icon.png"), 590, 450, null);
		
	}
	private Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		;		
	}
	public void updateGame() {
		;
	}
	

}
