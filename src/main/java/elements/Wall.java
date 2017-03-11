package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.SpriteSheetControl;

public class Wall implements StageElement{

	private double posX;
	private double posY;
	private BufferedImage img;
	private boolean isActive;
	private int type;
	
	public Wall(int row, int col, int type, SpriteSheetControl ssc) {
		this.type = type;
		isActive = true;
		initWall(ssc);
		setInitPos(row+1,col+1);
	}
	
	public void initWall(SpriteSheetControl ssc){
		if (type==1) {
			img = ssc.getImgBrick();
		}else{
			img = ssc.getImgIron();
		}
	}
	
	public void setInitPos( int row, int col){
    	setPosX(x+(col*k)-k);
    	setPosY(y+(row*k)-k);
    }

	public void updateDraw() {
		
	}
	
	public void draw(Graphics g) {
		
		if (isActive) {
			g.drawImage(img, (int) posX,(int) posY, null);
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
