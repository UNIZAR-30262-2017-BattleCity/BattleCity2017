package elements;

import java.awt.Rectangle;

import application.Properties;

public abstract class GameElement {

	protected double posX;
	protected double posY;
	protected int width;
	protected int heigth;
	protected int xI,yI,xF,yF;
	protected int k;
	protected boolean isActive;
	protected Stage stage;
	
	public GameElement(Stage stage){
		this.stage = stage;
		xI = Properties.X_INIT_STAGE;
		yI = Properties.Y_INIT_STAGE;
		xF = Properties.X_FINAL_STAGE;
		yF = Properties.Y_FINAL_STAGE;
		k = Properties.SIZE_SQUARE;
		width = k;
		heigth = k;
		isActive=true;
	}
	
	public void setInitPos(int row, int col){
    	setPosX(xI+(col*k)-k);
    	setPosY(yI+(row*k)-k);
    }
	
	public Rectangle getBounds(int width, int heigth){
		return new Rectangle((int) getPosX()+2, (int) getPosY()+2, width-2, heigth-2);
	}


	public double getPosX() {
		return posX;
	}


	public void setPosX(double posX) {
		this.posX = posX;
	}


	public double getPosY() {
		return posY;
	}


	public void setPosY(double posY) {
		this.posY = posY;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeigth() {
		return heigth;
	}


	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
			
}