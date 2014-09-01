package objetos;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Nave extends ObjetoGeometrico {
	private boolean Up;
	private boolean Down;
	private int health;
	
	
	public Nave(final int xPos, final int yPos, final int width,final int height, final int health,final String img){
		super(xPos,yPos,width,height);
		this.health = health;
		this.img = getImage(img);
		this.setUp(false);
		this.setDown(false);
	}
	
	public void draw(Graphics g){
		g.drawImage(img, xPos, yPos, width, height, null);
	}

	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
	@Override
	public void update() {
		if(this.getUp()){
			if(getyPos()>=26){
				incrementaYPos(-4);
				incrementaYRect(-4);
			}			
		} else if(this.Down){
			if(getyPos()<=400){
				incrementaYPos(4);
				incrementaYRect(4);
			}
		} 
	}

	public void setDown(boolean jogadorDown) {
		this.Down = jogadorDown;
	}

	public boolean getDown() {
		return Down;
	}

	public void setUp(boolean jogadorUp) {
		this.Up = jogadorUp;
	}

	public boolean getUp() {
		return Up;
	}
	public int getHealth(){
		return this.health;
	}
	public void setHealth(int health){
		this.health = health;
	}
}
