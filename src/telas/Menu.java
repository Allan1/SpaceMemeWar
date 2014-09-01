package telas;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import objetos.BalaDeConfiguracao;
import objetos.Nave;


public abstract class Menu extends JFrame implements Tela {
	
	protected CopyOnWriteArrayList<BalaDeConfiguracao> balas;
	
	protected Image image;
	protected Image image2;
	protected Graphics graphics;
	
	protected Nave jogador1;
	protected Nave jogador2;
	protected Nave jogador3;
	
	private boolean gameOn;
	
	private static final long serialVersionUID = 1L;
	
	public Menu(){
		setTitle("Space Meme War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,500);
		setResizable(false);
		setVisible(true);
		gameOn = false;
		balas = new CopyOnWriteArrayList<BalaDeConfiguracao>();
	}
	
	public boolean isGameOn() {
		return gameOn;
	}

	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
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
	public void setJogador3(Nave jogador3) {
		this.jogador3 = jogador3;
	}
	public Nave getJogador3() {
		return jogador3;
	}
	public CopyOnWriteArrayList<BalaDeConfiguracao> getBalas(){
		return balas;
	}

}
