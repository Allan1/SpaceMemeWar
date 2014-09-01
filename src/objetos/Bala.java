package objetos;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import telas.Partida;



public class Bala extends ObjetoGeometrico{
	private static int numeroBalas;
	protected Nave alvo;
	private int deltaX;
	
	public Bala(final Nave alvo, final int deltaX, final int xPos, final int yPos,final int width,final int height, final String img){
			super(xPos,yPos,width,height);
			this.setAlvo(alvo);
			this.deltaX = deltaX;
			this.img = getImage(img);
			numeroBalas++;
	}
	public static String numeroDeBalas(){                     //METODO ESTATICO 
		return "O numero de balas gastas foram: "+numeroBalas;
	}
	public void draw(Graphics g){
		g.drawImage(img, xPos, yPos, width, height, null);
	}
	
	void update(){    
		incrementaXPos(6*getDeltaX());
		incrementaXRect(6*getDeltaX());
	}
	
	public void update(Partida partida) {         //SOBRECARGA
		if(rect.intersects(partida.getEscudo1())&&(partida.getBlock1())){
			partida.getMunicao().remove(this);
		}
		if(rect.intersects(partida.getEscudo2())&&(partida.getBlock2())){
				partida.getMunicao().remove(this);
		} 
		if(getRect().intersects(alvo.getRect())){
				alvo.setHealth(alvo.getHealth() - 2);
				partida.getMunicao().remove(this);
			} else if(xPos < 5 || xPos>695){
				partida.getMunicao().remove(this);
			} else {
				this.update(); 			//POLIMORFISMO		// aqui em tempo de execucao acontecera
			}									// a amarracao tardia 
	}											// e sera chamada o método da classe BalaDiagonal ou da classe Bala 

	
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	
	public int getDeltaX() {
		return deltaX;
	}
	
	public void setAlvo(Nave alvo) {
		this.alvo = alvo;
	}

	
	public Nave getAlvo() {
		return alvo;
	}
	
}