package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.ImageControl;
import gameController.StageControl;

public class Obstacle extends GameElement implements StageElement{

	private BufferedImage img;
	
	public Obstacle(int row, int col, int type, StageControl stageControl) {
		super(stageControl);
		this.type = type;
		isActive = true;
		initObstacle();
		setInitPos(row+1,col+1);
	}
	
	public void initObstacle(){
		if (type==1) {
			img = ImageControl.getImgForest();
		}else{
			img = ImageControl.getImgWater();
		}
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
