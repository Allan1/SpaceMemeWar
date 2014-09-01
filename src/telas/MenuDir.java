package telas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

import objetos.BalaDeConfiguracao;
import objetos.Nave;


public class MenuDir extends Menu {

	private static final long serialVersionUID = 1L;

	private String imagePlayer;

	public MenuDir(String imagePlayer){
		super();
		this.imagePlayer = imagePlayer;
		addKeyListener(this);
		setJogador1(new Nave(10,200,30,90,50,imagePlayer));
		jogador2 = new Nave(300,100,122,90,2,"images/trocarNave1.png");
		jogador3 = new Nave(300,300,84,90,2,"images/jogar1.png");
		setIconImage(getImage("images/nave.png"));
	}
	
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
	public void paint(Graphics g){
		image = createImage(getWidth(),getHeight());
		image2 = Toolkit.getDefaultToolkit().createImage("images/space2.jpg");
		graphics = image.getGraphics();
		updateGame();
		paintComponent(graphics);
		g.drawImage(image, 0, 0, null);
		repaint();
	}
	public void updateGame(){
		jogador1.update();
		jogador2.update();
		jogador3.update();
		for(BalaDeConfiguracao bala : balas)
			bala.update(this);
	}
	
	public void paintComponent(Graphics g){
		
		Dimension dDesktop = this.getSize();  		  
        double width = dDesktop.getWidth();  
        double height = dDesktop.getHeight();  
        Image background = new ImageIcon(this.image2.getScaledInstance((int) width, (int) height, 1)).getImage();  
        g.drawImage(background, 0, 0, this);
        
		if(jogador1.getHealth()>0 && jogador2.getHealth()>0 && jogador3.getHealth()>0){
			for(BalaDeConfiguracao bala : balas)
				bala.draw(g);
		
		} else if (jogador2.getHealth() == 0){
			if(!isGameOn()){
				setGameOn(true);
				setVisible(false);
				new MenuDirNave(this.imagePlayer);
			}
		}else if (jogador3.getHealth() == 0){
			if(!isGameOn()){
				setGameOn(true);
				setVisible(false);
				new MenuEsq(this.imagePlayer,"images/jogador2.png");	
			}
		}
		jogador1.draw(g);
		jogador2.draw(g);
		jogador3.draw(g);
	}


	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		} else if (e.getKeyCode() == KeyEvent.VK_W){
			jogador1.setUp(true);
		} else if (e.getKeyCode() == KeyEvent.VK_S){
			jogador1.setDown(true);
		} else if (e.getKeyCode() == KeyEvent.VK_X){
				BalaDeConfiguracao jogador1Tiro = new BalaDeConfiguracao(jogador2,jogador3,1, jogador1.getxPos()+20, jogador1.getyPos() + 45,4, 4,"images/bala1.gif");
				balas.add(jogador1Tiro);
		}
	}


	public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W){
			jogador1.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S){
			jogador1.setDown(false);
		} 
	}

	public void keyTyped(KeyEvent arg0) {
		;
		
	}
	
//	public static void main(String[] args){
//		new MenuDir("images/jogador1.png");
//	}
	

}
