package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.SpriteSheetControl;

public class Enemy extends Tank implements StageElement{

	private int type;
    private BufferedImage imgEnemyUp, imgEnemyDowm, imgEnemyLeft, imgEnemyRight;
    private double velEnemy;
    private SpriteSheetControl ssc;
    
	public Enemy(int col, int row, int type, SpriteSheetControl ssc) {
		setInitPos(col, row);
		setTypeTank(1);
		this.ssc = ssc;
		this.type = type;
		initEnemy();
	}
	
	public void initEnemy(){
		switch (type) {
		case 1://regular
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();			
			
			this.shieldLevel = 1;
			velEnemy = Properties.VEL_ENEMY;			
			break;
		case 2://fast
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();
			
			this.shieldLevel = 1;
			velEnemy = Properties.VEL_ENEMY_FAST;
			break;
			
		case 3://hard
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();
			
			this.shieldLevel = 3;
			velEnemy = Properties.VEL_ENEMY;
			break;
		}
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
		
		if(getPosX()<xI) setDirection(3);
    	if(getPosX()>xF) setDirection(2);
    	if(getPosY()<yI) setDirection(1);
    	if(getPosY()>yF) setDirection(0);
		
	}

	@Override
	public void draw(Graphics g) {
		
		switch (this.direction) {
		case 0:
			g.drawImage(imgEnemyUp, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case 1:
			g.drawImage(imgEnemyDowm, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case 2:
			g.drawImage(imgEnemyLeft, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case 3:
			g.drawImage(imgEnemyRight, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
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
