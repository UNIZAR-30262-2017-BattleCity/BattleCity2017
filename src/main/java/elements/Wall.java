package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.SpriteSheetControl;

public class Wall extends GameElement implements StageElement{

	private double posX;
	private double posY;
	private BufferedImage img;
	private boolean isActive;
	private int type;
	
	public Wall(double row, double col, int type, Stage stage, SpriteSheetControl ssc) {
		super(stage);
		this.type = type;
		isActive = true;
		initWall(ssc);
		setInitPos(row+1,col+1);
	}
	
	public void initWall(SpriteSheetControl ssc){
		switch (type) {
		case 1:
			img = ssc.getImgBrick();
			break;
		case 2:
			img = ssc.getImgHalfBrickH();
			break;
		case 3:
			img = ssc.getImgIron();
			break;
		case 4:
			img = ssc.getImgHalfIronH();
			break;
		case 5:
			img = ssc.getImgHalfBrickV();
			break;
		case 6:
			img = ssc.getImgHalfIronV();
			break;
		}		
			
	}
	
	public void setInitPos( double row, double col){
    	setPosX(xI+(col*k)-k);
    	setPosY(yI+(row*k)-k);
    }

	public void updateDraw() {
		
	}
	
	public void draw(Graphics g) {
						
		if (type == 1 || type == 3) {
			g.drawImage(img, (int) posX,(int) posY, width, heigth, null);
		}else{
			if (type == 5 || type == 6) g.drawImage(img, (int) posX,(int) posY, width/2, heigth, null);
			else g.drawImage(img, (int) posX,(int) posY, width, heigth/2, null);
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
