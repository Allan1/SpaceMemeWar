package telas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

import objetos.BalaDeConfiguracao;
import objetos.Nave;


public class MenuEsqNave extends Menu{
	
	private static final long serialVersionUID = 1L;
	private String imageThisPlayer2;
	private final String imageChanged;
	private final String imagePlayer1;

	public MenuEsqNave(String imagePlayer,String thisImagePlayer){
		super();
		this.imagePlayer1 = imagePlayer;
		this.imageThisPlayer2 = thisImagePlayer;	
		setIconImage(getImage("images/nave.png"));
		addKeyListener(this);
		jogador2 = new Nave(660,200,30,90,2,imageThisPlayer2);
		String tempImage;
		if(imageThisPlayer2.equals("images/jogador2.png")){
			tempImage = "images/jogador2nova.png";
			imageChanged = "images/jogador2nova.png";
		}else{
			tempImage = "images/jogador2.png";
			imageChanged = "images/jogador2.png";
		}
		jogador1 = new Nave(320,100,30,90,2,tempImage);
		jogador3 = new Nave(267,300,83,90,2,"images/voltar2.png");
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
	
	@Override
	public void updateGame() {
		for(BalaDeConfiguracao bala : balas){
			bala.update(this);
		}
		jogador3.update();
		jogador1.update();
		jogador2.update();
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
			}
			
		} else if (jogador1.getHealth() == 0){
			if(!isGameOn()){
				setGameOn(true);
				setVisible(false);
				new MenuEsqNave(this.imagePlayer1,this.imageChanged);
			}
		}else if (jogador3.getHealth() == 0){
			if(!isGameOn()){
				setGameOn(true);
				setVisible(false);
				new MenuEsq(this.imagePlayer1,this.imageThisPlayer2);
			}
		}
		jogador1.draw(g);
		jogador2.draw(g);
		jogador3.draw(g);

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		} else if (e.getKeyCode() == KeyEvent.VK_UP){
			jogador2.setUp(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			jogador2.setDown(true);
		} else if (e.getKeyCode() == KeyEvent.VK_K){
				BalaDeConfiguracao jogador2Tiro = new BalaDeConfiguracao(jogador1,jogador3,-1, jogador2.getxPos()-4, jogador2.getyPos() + 45,4, 4,"images/bala1.gif");
				balas.add(jogador2Tiro);
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			jogador2.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			jogador2.setDown(false);
		} 
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		;
		
	}

	

}
