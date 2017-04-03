package elements;

import java.awt.Rectangle;

import application.Properties;
import gameController.StageControl;

public abstract class GameElement {

	protected double posX;
	protected double posY;
	protected static final int xI = Properties.X_INIT_STAGE;
	protected static final int yI = Properties.Y_INIT_STAGE;
	protected static final int xF = Properties.X_FINAL_STAGE;
	protected static final int yF = Properties.Y_FINAL_STAGE;
	protected static final int k = Properties.SIZE_SQUARE;
	protected int width;
	protected int heigth;
	protected int type;
	protected boolean isActive;
	protected StageControl stageControl;
	protected int timeAnim, anim;
	
	public GameElement(StageControl stageControl){
		this.stageControl = stageControl;
		width = k;
		heigth = k;
		isActive=true;
		timeAnim = Properties.TIME_ANIM;
		anim = 0;
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

	public StageControl getStage() {
		return stageControl;
	}

	public void setStage(StageControl stageControl) {
		this.stageControl = stageControl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
					
}
