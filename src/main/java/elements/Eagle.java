package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.ImageControl;

public class Eagle extends GameElement implements StageElement{
		
	private BufferedImage imgEagle, imgEagleDead;
	
	public Eagle(int row, int col, Stage stage, ImageControl ssc) {		
		super(stage);
		imgEagle = ssc.getImgEagle();
		imgEagleDead = ssc.getImgEagleDead();
		setInitPos(row+1,col+1);
		isActive = true;
	}
	
	public void setInitPos(int row, int col){
    	setPosX(xI+(col*k)-k);
    	setPosY(yI+(row*k)-k);
    }

	public void updateDraw() {
	}
	
	public void draw(Graphics g) {
		
		if (isActive) {
			g.drawImage(imgEagle, (int) posX,(int) posY,  width, heigth, null);
		}else{
			g.drawImage(imgEagleDead, (int) posX,(int) posY,  width, heigth, null);
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
		
}
