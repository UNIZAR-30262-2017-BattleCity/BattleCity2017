package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.SpriteSheetControl;

public class Eagle implements StageElement{
	
	private double posX;
	private double posY;
	private BufferedImage imgEagle, imgEagleDead;
	private boolean isAlive;
	
	public Eagle(int row, int col, SpriteSheetControl ssc) {		
		imgEagle = ssc.getImgEagle();
		imgEagleDead = ssc.getImgEagleDead();
		isAlive = true;
		setInitPos(row+1,col+1);
	}
	
	public void setInitPos(int row, int col){
    	setPosX(x+(col*k)-k);
    	setPosY(y+(row*k)-k);
    }

	public void updateDraw() {
		
	}
	
	public void draw(Graphics g) {
		
		if (isAlive) {
			g.drawImage(imgEagle, (int) posX,(int) posY, null);
		}else{
			g.drawImage(imgEagleDead, (int) posX,(int) posY, null);
		}
		
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}	
			
}
