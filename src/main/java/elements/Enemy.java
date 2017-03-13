package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.SpriteSheetControl;

public class Enemy extends Tank implements StageElement{

	private int type;
    private BufferedImage imgEnemyUp, imgEnemyDowm, imgEnemyLeft, imgEnemyRight;
    private SpriteSheetControl ssc;
    private Stage stage;
    
	public Enemy(int col, int row, int type, Stage stage, SpriteSheetControl ssc) {
		super(stage);
		setInitPos(col, row);
		setTypeTank(1);
		this.ssc = ssc;
		this.type = type;
		this.stage = stage;
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
			vel = Properties.VEL_ENEMY;			
			break;
		case 2://fast
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();
			
			this.shieldLevel = 1;
			vel = Properties.VEL_ENEMY_FAST;
			break;
			
		case 3://hard
			imgEnemyUp =  ssc.getEnemyUp();
			imgEnemyDowm = ssc.getEnemyDowm();
			imgEnemyLeft = ssc.getEnemyLeft();
			imgEnemyRight = ssc.getEnemyRight();
			
			this.shieldLevel = 3;
			vel = Properties.VEL_ENEMY;
			break;
		}
	}
	
	@Override
	public void updateDraw() {
		switch (direction) {
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
		
		if (PhysicsContol.collision(this, stage.getElements(this))) {
			setDirection(getDirection()*-1);
		}
		
		
		if(getPosX()<xI) setDirection(2);
    	if(getPosX()>xF) setDirection(-2);
    	if(getPosY()<yI) setDirection(-1);
    	if(getPosY()>yF) setDirection(1);
		
	}

	@Override
	public void draw(Graphics g) {
		
		switch (this.direction) {
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
		
	}
	
	public int getType() {
		return type;
	}
	public void setType(int id) {
		this.type = id;
	}
		
}
