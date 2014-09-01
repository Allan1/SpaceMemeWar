package objetos;


public class BalaDiagonal extends Bala{
	
	private int deltaY;
	
	public BalaDiagonal(final Nave alvo, final int deltaX,final int deltaY, final int xPos, final int yPos,final int width,final int height, final String img){	
		
		super(alvo,deltaX,xPos,yPos,width,height,img);
		
		this.setDeltaY(deltaY);
	}
	
	public void update(){		//SOBREPOSIÇÃO - Sobrepõe o metodo update() da classe pai Bala
		incrementaXPos(6*getDeltaX());
		incrementaXRect(6*getDeltaX());
		incrementaYPos(6*getDeltaY());
		incrementaYRect(6*getDeltaY());
	}
	
	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}
	

}