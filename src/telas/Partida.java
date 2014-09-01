package telas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import objetos.Bala;
import objetos.BalaDiagonal;
import objetos.Clock;
import objetos.Nave;


public class Partida extends JFrame implements Tela{
	
	private static final long serialVersionUID = 1L;
	//Naves dos respectivos jogadores
	private Nave jogador1;
	private Nave jogador2;
	//Imagens
	private Image image;
	private Image image2;
	private Graphics graphics;
	//variaveis do tempo principal
	private long timebegin;
	private long time;
	private boolean endgame;
	//Relogio para marcacoes de tempo do primeiro jogador
	private Clock clock1;
	private boolean blocked1;
	//Relogio para marcacoes de tempo do segundo jogador
	private Clock clock2;
	private boolean blocked2;
	
	private Clock clock3;
	
	private CopyOnWriteArrayList<Bala> municao;
	private short cont1;
	private short cont2;
	
	//Escudos
	private final Rectangle escudo1;
	private final Rectangle escudo2;
	private boolean block1;
	private boolean block2;
	
	String numB;
	
	public Partida(String imagePlayer1,String imagePlayer2){
		setTitle("Space Meme War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,500);
		setResizable(false);
		setVisible(true);
		setIconImage(getImage("images/nave.png"));
		escudo1 = (new Rectangle(20,200,50,100));
		escudo2 = (new Rectangle(640,200,50,100));
		jogador1 = new Nave(10,200,30,90,50,imagePlayer1);
		block1=false;
		cont1 = 10;
		blocked1 = false;
		jogador2 = new Nave(660,200,30,90,50,imagePlayer2);
		block2=false;
		cont2 = 10;
		blocked2 = false;
		addKeyListener(this);
		timebegin = System.currentTimeMillis()/1000;
		municao = new CopyOnWriteArrayList<Bala>();
		endgame = false;
	}
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	public Nave getJogador1() {
		return jogador1;
	}
	public void setJogador1(Nave jogador1) {
		this.jogador1 = jogador1;
	}
	public Nave getJogador2() {
		return jogador2;
	}
	public void setJogador2(Nave jogador2) {
		this.jogador2 = jogador2;
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
        g.drawImage(getImage("images/a.png"), 50, 40, null);
        g.drawImage(getImage("images/barra.png"), 54, 45, 5*jogador1.getHealth(), 16, null);
        g.drawImage(getImage("images/barra.png"), 382+5*(50-jogador2.getHealth()), 45, 5*jogador2.getHealth(), 16, null);
        
        if (!endgame){
        	time = 90 - (System.currentTimeMillis()/1000 - timebegin);
        	g.setColor(Color.WHITE);
        	g.drawString(""+time, 335, 57);
        	if(block1)
        		g.drawImage(getImage("images/escudo.png"), 20, 200, null);
        	if(block2)
        		g.drawImage(getImage("images/escudo2.png"), 640, 200, null);
        	if(time == 0)
        		endgame=true;
        }else{
        	g.setColor(Color.WHITE);
        	g.drawString(""+time, 335, 57);
        }
        	
		if(jogador1.getHealth()>0 && jogador2.getHealth()>0 && !endgame ){
			for(Bala bala : municao){
				bala.draw(g);
			}
		} else if (jogador1.getHealth() == 0){
			g.setColor(Color.RED);
			g.drawString("Jogador 2 venceu!", 300, 190);
			g.drawImage(getImage("images/LOL2.png"), 520, 150, null);
			g.drawImage(getImage("images/Okay2.png"), 120,150, null);
			endgame=true;
		} else if (jogador2.getHealth() == 0){
			g.setColor(Color.RED);
			g.drawString("Jogador 1 venceu!", 300, 190);
			g.drawImage(getImage("images/LOL.png"), 120, 150, null);
			g.drawImage(getImage("images/Okay.png"), 520,150, null);
			endgame=true;
		}else {
			g.setColor(Color.RED);
			if (jogador1.getHealth()>jogador2.getHealth()){
				g.drawString("Jogador 1 venceu!", 300, 190);
				g.drawImage(getImage("images/LOL.png"), 120, 150, null);
				g.drawImage(getImage("images/Okay.png"), 520,150, null);
			} else if (jogador2.getHealth()>jogador1.getHealth()){
				g.drawString("Jogador 2 venceu!", 300, 190);
				g.drawImage(getImage("images/LOL2.png"), 520, 150, null);
				g.drawImage(getImage("images/Okay2.png"), 120,150, null);
			} else {
				g.drawString("Empate!", 300, 190);
				g.drawImage(getImage("images/trollface-icon.png"), 590, 450, null);
			}
		}
		
		//Clock Player 1
		if(isBlocked1()){
			if(clock1 == null){
				clock1 = new Clock(5);
				new Thread(clock1).start();
			}else {
				int time1 = clock1.getTime();
				if(clock1.getTime()!=0){
					g.setColor(Color.RED);
					g.drawString("Tempo restante:"+time1,120,150);
				}else{setBlocked1(false);cont1 = 10;clock1 = null;}	
			}
		}
		//Clock Player 2
		if(isBlocked2()){
			if(clock2 == null){
			clock2 = new Clock(5);
			new Thread(clock2).start();
			}else{	
				int time2 = clock2.getTime();
				if(clock2.getTime()!=0){
					g.setColor(Color.RED);
					g.drawString("Tempo restante:"+time2,520,150);
				}else{setBlocked2(false);cont2 = 10;clock2 = null;}
			}
		}
		
		if(endgame){
			if(clock3 == null){
			clock3 = new Clock(10);
			new Thread(clock3).start();
			}else{	
				int time3 = clock3.getTime();
				if(clock3.getTime()!=0){
					g.setColor(Color.RED);
					if(numB == null)
						numB = Bala.numeroDeBalas();
					g.drawString(numB,250,350);
					g.drawString("Aperte [ESC] para sair. Voltando para o Menu em: "+time3,200,400);
				}else{	
				setVisible(false);
				new MenuDir("images/jogador1.png");
				}
			}
		}
		
		jogador1.draw(g);
		jogador2.draw(g);
	}
	public void updateGame(){
		if(jogador1.getHealth()>0 && jogador2.getHealth()>0 && (!endgame)){
			for(Bala bala : municao){
				bala.update(this);   //POLIMORFISMO acontece DENTRO desta funcao na chamada da funcao update() sem parametro
			
			}
			jogador1.update();
			jogador2.update();
		}
	}


	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		} else if (e.getKeyCode() == KeyEvent.VK_UP){
			jogador2.setUp(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			jogador2.setDown(true);
		} else if (e.getKeyCode() == KeyEvent.VK_W){
			jogador1.setUp(true);
		} else if (e.getKeyCode() == KeyEvent.VK_S){
			jogador1.setDown(true);
		} else if (e.getKeyCode() == KeyEvent.VK_X){
			if (cont1>0){
				Bala jogador1Tiro = new Bala(jogador2,1, jogador1.getxPos()+20, jogador1.getyPos() + 45,4, 4,"images/bala1.gif");
				municao.add(jogador1Tiro);
				cont1--;
				if(cont1 == 0){setBlocked1(true);}
			}
//		} else if (e.getKeyCode() == KeyEvent.VK_F){
//			Bala jogador1Tiro = new Bala(jogador2,1, jogador1.getxPos()+20, jogador1.getyPos() + 45,4, 4,"images/bala1.gif");
//			municao.add(jogador1Tiro);
//			
//		} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4){
//			Bala jogador2Tiro = new Bala(jogador1,-1, jogador2.getxPos()-4, jogador2.getyPos() + 45,4, 4,"images/bala1.gif");
//			municao.add(jogador2Tiro);
		} else if (e.getKeyCode() == KeyEvent.VK_K){
			if (cont2>0){
				Bala jogador2Tiro = new Bala(jogador1,-1, jogador2.getxPos()-4, jogador2.getyPos() + 45,4, 4,"images/bala1.gif");
				municao.add(jogador2Tiro);
				cont2--;
				if(cont2 == 0){setBlocked2(true);}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_V){
			BalaDiagonal jogador1Tiro = new BalaDiagonal(jogador2,2,1, jogador1.getxPos()+20, jogador1.getyPos() + 5,4, 4,"images/bala1.gif");
			municao.add(jogador1Tiro);
		} else if (e.getKeyCode() == KeyEvent.VK_C){
			BalaDiagonal jogador1Tiro = new BalaDiagonal(jogador2,2,-1, jogador1.getxPos()+20, jogador1.getyPos() + 85,4, 4,"images/bala1.gif");
			municao.add(jogador1Tiro);
		} else if (e.getKeyCode() == KeyEvent.VK_J){
			BalaDiagonal jogador2Tiro = new BalaDiagonal(jogador1,-2,1, jogador2.getxPos()-4, jogador2.getyPos() + 5,4, 4,"images/bala1.gif");
			municao.add(jogador2Tiro);
		} else if (e.getKeyCode() == KeyEvent.VK_L){
			BalaDiagonal jogador2Tiro = new BalaDiagonal(jogador1,-2,-1, jogador2.getxPos()-4, jogador2.getyPos() + 85,4, 4,"images/bala1.gif");
			municao.add(jogador2Tiro);
		} else if (e.getKeyCode() == KeyEvent.VK_A){
			//setBlock1(true);
		} else if (e.getKeyCode() == KeyEvent.VK_I){
			//setBlock2(true);
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			jogador2.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			jogador2.setDown(false);
		} else if (e.getKeyCode() == KeyEvent.VK_W){
			jogador1.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S){
			jogador1.setDown(false);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			setBlock1(false);
		} else if (e.getKeyCode() == KeyEvent.VK_END){
			setBlock2(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		;
	}
	public boolean isBlocked1() {
		return blocked1;
	}
	public void setBlocked1(boolean blocked1) {
		this.blocked1 = blocked1;
	}
	public boolean isBlocked2() {
		return blocked2;
	}
	public void setBlocked2(boolean blocked2) {
		this.blocked2 = blocked2;
	}
	public void setBlock1(boolean block1) {
		this.block1 = block1;
	}
	public boolean getBlock1() {
		return block1;
	}
	public void setBlock2(boolean block2) {
		this.block2 = block2;
	}
	public boolean getBlock2() {
		return block2;
	}
	public CopyOnWriteArrayList<Bala> getMunicao(){
		return municao;
	}
	public Rectangle getEscudo1() {
		return escudo1;
	}
	public Rectangle getEscudo2() {
		return escudo2;
	}


}
