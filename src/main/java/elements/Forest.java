package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.ImageControl;
import gameController.StageControl;

public class Forest extends GameElement implements StageElement{

	private BufferedImage img;
	
	public Forest(int row, int col, StageControl stageControl) {
		super(stageControl);
		initObstacle();
		setInitPos(row+1,col+1);
	}
	
	public void initObstacle(){
		img = ImageControl.getImgForest();
	}
		

	public void updateDraw() {
		
	}
	
	public void draw(Graphics g) {

		g.drawImage(img, (int) posX,(int) posY, width, heigth, null);

		
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
