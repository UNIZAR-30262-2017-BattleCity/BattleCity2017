package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.SpriteSheetControl;

public class Enemy extends Tank implements StageElement{

	private int type;
    private BufferedImage imgEnemyUp, imgEnemyDowm, imgEnemyLeft, imgEnemyRight;
    private double velEnemy;
    SpriteSheetControl ssc;
    
	public Enemy(double posX, double posY, int type, SpriteSheetControl ssc) {
		this.posX = posX;
		this.posY = posY;
		this.ssc = ssc;
		this.type = type;		
		this.direction = 0;
		velEnemy = Properties.VEL_ENEMY;
	}		

	@Override
	public void updateDraw() {
		switch (direction) {
		case 0://up
			posY -= velEnemy;
			break;
		case 1://down
			posY += velEnemy;
			break;
		case 2://left
			posX -= velEnemy;
			break;
		case 3://rigth
			posX += velEnemy;
			break;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		
		switch (type) {
		case 1:
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();
			break;
		case 2:
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();
			break;
		}
		
		switch (this.direction) {
		case 0:
			g.drawImage(imgEnemyUp, (int) getPosX(), (int)getPosY(), null);
			break;
		case 1:
			g.drawImage(imgEnemyDowm, (int) getPosX(), (int)getPosY(), null);
			break;
		case 2:
			g.drawImage(imgEnemyLeft, (int) getPosX(), (int)getPosY(), null);
			break;
		case 3:
			g.drawImage(imgEnemyRight, (int) getPosX(), (int)getPosY(), null);
			break;
		}
		
	}
	
	public int getType() {
		return type;
	}
	public void setType(int id) {
		this.type = id;
	}
		
}
