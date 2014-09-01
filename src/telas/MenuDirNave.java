package telas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import objetos.BalaDeConfiguracao;
import objetos.Nave;


public class MenuDirNave extends Menu{
	
	private static final long serialVersionUID = 1L;

	private String imagePlayer1;
	private String imageChanged1;

	public MenuDirNave(String imagePlayer1){
		super();
		this.imagePlayer1 = imagePlayer1;
		setIconImage(getImage("images/nave.png"));
		addKeyListener(this);
		jogador1 = new Nave(10,200,30,90,50,imagePlayer1);
		String tempImage;
		if(imagePlayer1.equals("images/jogador1nova.png")){
			this.imageChanged1 = "images/jogador1.png";
			tempImage = "images/jogador1.png";
		}else{
			this.imageChanged1 = "images/jogador1nova.png";
			tempImage = "images/jogador1nova.png";
		}
		jogador2 = new Nave(350,100,30,90,2,tempImage);
		jogador3 = new Nave(350,300,83,90,2,"images/voltar1.png");
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
	public void paintComponent(Graphics g){
		
		Dimension dDesktop = this.getSize();  		  
        double width = dDesktop.getWidth();  
        double height = dDesktop.getHeight();  
        Image background = new ImageIcon(this.image2.getScaledInstance((int) width, (int) height, 1)).getImage();  
        g.drawImage(background, 0, 0, this);
		if(jogador1.getHealth()>0 && jogador2.getHealth()>0 && jogador3.getHealth()>0){
			for(BalaDeConfiguracao bala : balas){
				bala.draw(g);
				bala.update(this);
			}
		} else if (jogador2.getHealth() == 0){
			if(!isGameOn()){
				setGameOn(true);
				setVisible(false);
				new MenuDirNave(imageChanged1);	
			}
		}else if (jogador3.getHealth() == 0){
			if(!isGameOn()){
				setGameOn(true);
				setVisible(false);
				new MenuDir(imagePlayer1);
			}
		}
		
		jogador1.draw(g);
		jogador2.draw(g);
		jogador3.draw(g);
	}
	
	@Override
	public void updateGame() {
		for(BalaDeConfiguracao bala : balas){
			bala.update(this);
		}
		jogador1.update();
		jogador2.update();
		jogador3.update();
	}

	@Override
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


	@Override
	public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W){
			jogador1.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S){
			jogador1.setDown(false);
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
