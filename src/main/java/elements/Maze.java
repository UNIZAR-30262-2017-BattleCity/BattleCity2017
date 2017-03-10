package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.SpriteSheetControl;

public class Maze implements StageElement{
	
	private double posX;
	private double posY;	
	private BufferedImage imgWall;
	private SpriteSheetControl ssc;
			
	public Maze(int col, int row, SpriteSheetControl ssc) {	
		this.ssc = ssc;
	}
	
	public void setInitPos(int col, int row){
    	setPosX(x+(col*k)-k);
    	setPosY(y+(row*k)-k);
    }
	
	public void updateDraw() {
		
	}
	
	public void draw(Graphics g) {
		
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
