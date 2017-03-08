package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.Properties;
import gameController.SpriteSheetControl;

public class Enemy extends Tank{

	private int id;
	private int maxEnemysSimul;
    private int maxEnemysForLevel;
    private BufferedImage imgEnemyUp, imgEnemyDowm, imgEnemyLeft, imgEnemyRight;
    private double velEnemy;
    
	public Enemy(double posX, double posY, int id, int maxEnemysSimul, int maxEnemysForLevel, SpriteSheetControl ssc) {
		this.posX = posX;
		this.posY = posY;
		this.maxEnemysSimul = maxEnemysSimul;
		this.maxEnemysForLevel = maxEnemysForLevel;
		this.id = id;
		imgEnemyUp =  ssc.getEnemyUp();
		imgEnemyDowm = ssc.getEnemyDowm();
		imgEnemyLeft = ssc.getEnemyLeft();
		imgEnemyRight = ssc.getEnemyRight();
		velEnemy = Properties.VELENEMY;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMaxEnemysSimul() {
		return maxEnemysSimul;
	}

	public void setMaxEnemysSimul(int maxEnemysSimul) {
		this.maxEnemysSimul = maxEnemysSimul;
	}

	public int getMaxEnemysForLevel() {
		return maxEnemysForLevel;
	}

	public void setMaxEnemysForLevel(int maxEnemysForLevel) {
		this.maxEnemysForLevel = maxEnemysForLevel;
	}


	
}
