package objetos;

import telas.Menu;

public class BalaDeConfiguracao extends Bala{
	
	private Nave segundoAlvo2;
	
	public BalaDeConfiguracao(final Nave alvo,final Nave alvo2, final int deltaX, final int xPos, final int yPos,final int width,final int height, final String img){
			super(alvo,deltaX,xPos,yPos,width,height,img);
			this.setJogador2(alvo2);
	}
	
	public void update(Menu menu) {
		 if(getRect().intersects(alvo.getRect())){
				alvo.setHealth(alvo.getHealth() - 2);
				menu.getBalas().remove(this);
			} else if(getRect().intersects(segundoAlvo2.getRect())){
				segundoAlvo2.setHealth(segundoAlvo2.getHealth() - 2);
				menu.getBalas().remove(this);
			}  
			else if(xPos < 5 || xPos>695){
				menu.getBalas().remove(this);
			} else {
				update();
			}
	}
	
	public void setJogador2(Nave alvo) {
		this.segundoAlvo2 = alvo;
	}
	public Nave getJogador2() {
		return segundoAlvo2;
	}
}