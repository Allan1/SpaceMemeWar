package objetos;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class ObjetoGeometrico {
	
	protected Rectangle rect;
	protected int xPos;
	protected int yPos;
	protected Image img;
	protected int height;
	protected int width;
	
	ObjetoGeometrico(final int xPos, final int yPos,final int width,final int height){
		this.rect = (new Rectangle(xPos,yPos,width,height));
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}
	
	abstract void draw(Graphics g);
	abstract void update();
	abstract Image getImage(String img);

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getxPos() {
		return xPos;
	}


	public int getyPos() {
		return yPos;
	}
	
	public void incrementaXPos(int xPos){
		this.xPos += xPos;
	}
	
	public void incrementaYPos(int yPos){
		this.yPos += yPos;
	}
	public Rectangle getRect() {
		return rect;
	}
	
	public void incrementaYRect(int y){
		this.rect.y += y;
	}
	public void incrementaXRect(int x){
		this.rect.x += (x);
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

}
