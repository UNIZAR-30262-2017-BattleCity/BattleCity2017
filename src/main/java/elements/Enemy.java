package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.ImageControl;

public class Enemy extends Tank implements StageElement{

    private BufferedImage imgEnemyUp[] = new BufferedImage[2];
    private BufferedImage imgEnemyDowm[] = new BufferedImage[2]; 
    private BufferedImage imgEnemyLeft[] = new BufferedImage[2]; 
    private BufferedImage imgEnemyRight[] = new BufferedImage[2];
    
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
		case 1://basic
			imgEnemyUp[0] = ImageControl.getEnemy1Up();
			imgEnemyUp[1] = ImageControl.getEnemy1UpB();
			imgEnemyDowm[0] = ImageControl.getEnemy1Dowm();
			imgEnemyDowm[1] = ImageControl.getEnemy1DowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy1Left();
			imgEnemyLeft[1] = ImageControl.getEnemy1LeftB();
			imgEnemyRight[0] = ImageControl.getEnemy1Right();
			imgEnemyRight[1] = ImageControl.getEnemy1RightB();
			
			this.shieldLevel = 1;
			vel = Properties.VEL_ENEMY;			
			break;
		case 2://fast
			imgEnemyUp[0] = ImageControl.getEnemy2Up();
			imgEnemyUp[1] = ImageControl.getEnemy2UpB();
			imgEnemyDowm[0] = ImageControl.getEnemy2Dowm();
			imgEnemyDowm[1] = ImageControl.getEnemy2DowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy2Left();
			imgEnemyLeft[1] = ImageControl.getEnemy2LeftB();
			imgEnemyRight[0] = ImageControl.getEnemy2Right();
			imgEnemyRight[1] = ImageControl.getEnemy2RightB();
			
			this.shieldLevel = 1;
			vel = Properties.VEL_ENEMY_FAST;
			break;
			
		case 3://power tank
			imgEnemyUp[0] = ImageControl.getEnemy3Up();
			imgEnemyUp[1] = ImageControl.getEnemy3UpB();
			imgEnemyDowm[0] = ImageControl.getEnemy3Dowm();
			imgEnemyDowm[1] = ImageControl.getEnemy3DowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy3Left();
			imgEnemyLeft[1] = ImageControl.getEnemy3LeftB();
			imgEnemyRight[0] = ImageControl.getEnemy3Right();
			imgEnemyRight[1] = ImageControl.getEnemy3RightB();
			
			this.shieldLevel = 3;
			vel = Properties.VEL_ENEMY;
			break;
			
		case 4://armor tank
			imgEnemyUp[0] = ImageControl.getEnemy4Up();
			imgEnemyUp[1] = ImageControl.getEnemy4UpB();
			imgEnemyDowm[0] = ImageControl.getEnemy4Dowm();
			imgEnemyDowm[1] = ImageControl.getEnemy4DowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy4Left();
			imgEnemyLeft[1] = ImageControl.getEnemy4LeftB();
			imgEnemyRight[0] = ImageControl.getEnemy4Right();
			imgEnemyRight[1] = ImageControl.getEnemy4RightB();
			
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
    	
    	anim();
    	updateDrawBullet();
		
	}
	
	

	@Override
	public void draw(Graphics g) {
		
		switch (this.dir) {
		case 1:
			g.drawImage(imgEnemyUp[anim], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case -1:
			g.drawImage(imgEnemyDowm[anim], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case -2:
			g.drawImage(imgEnemyLeft[anim], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		case 2:
			g.drawImage(imgEnemyRight[anim], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
			break;
		}
		
		drawBullet(g);
		
	}
		
}
