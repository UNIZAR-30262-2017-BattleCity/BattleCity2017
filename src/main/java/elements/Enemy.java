package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.ImageControl;

public class Enemy extends Tank implements StageElement{

    private BufferedImage imgEnemyUp, imgEnemyDowm, imgEnemyLeft, imgEnemyRight;
    
	public Enemy(int col, int row, int type, Stage stage) {
		super(stage);
		setInitPos(col, row);
		setTypeTank(1);
		this.type = type;
		dir=-1;
		initEnemy();
		
	}
	
	public void initEnemy(){
		switch (type) {
		case 1://regular
			imgEnemyUp =  ImageControl.getEnemyUp();
			imgEnemyDowm = ImageControl.getEnemyDowm();
			imgEnemyLeft = ImageControl.getEnemyLeft();
			imgEnemyRight = ImageControl.getEnemyRight();			
			
			this.shieldLevel = 1;
			vel = Properties.VEL_ENEMY;			
			break;
		case 2://fast
			imgEnemyUp =  ImageControl.getEnemyUp();
			imgEnemyDowm = ImageControl.getEnemyDowm();
			imgEnemyLeft = ImageControl.getEnemyLeft();
			imgEnemyRight = ImageControl.getEnemyRight();
			
			this.shieldLevel = 1;
			vel = Properties.VEL_ENEMY_FAST;
			break;
			
		case 3://hard
			imgEnemyUp =  ImageControl.getEnemyUp();
			imgEnemyDowm = ImageControl.getEnemyDowm();
			imgEnemyLeft = ImageControl.getEnemyLeft();
			imgEnemyRight = ImageControl.getEnemyRight();
			
			this.shieldLevel = 3;
			vel = Properties.VEL_ENEMY;
			break;
		}
	}
	
	@Override
	public void updateDraw() {
		switch (dir) {
		case 1://up
			posY -= vel;
			break;
		case -1://down
			posY += vel;
			break;
		case -2://left
			posX -= vel;
			break;
		case 2://rigth
			posX += vel;
			break;
		}
		
		if (PhysicsContol.collisionEnemy(this, stage.getElements(this))) {
			setDir(getDir()*-1);
		}		
		
		if(getPosX()<xI) setDir(2);
    	if(getPosX()>xF) setDir(-2);
    	if(getPosY()<yI) setDir(-1);
    	if(getPosY()>yF) setDir(1);
		
	}
	
	

	@Override
	public void draw(Graphics g) {
		
		switch (this.dir) {
		case 1:
			g.drawImage(imgEnemyUp, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case -1:
			g.drawImage(imgEnemyDowm, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case -2:
			g.drawImage(imgEnemyLeft, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case 2:
			g.drawImage(imgEnemyRight, (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		}
		
		drawBullet(g);
		
	}
		
}
