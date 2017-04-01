package elements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.StageControl;
import gameController.ImageControl;

public class Enemy extends Tank implements StageElement{

    private BufferedImage imgEnemyUp[] = new BufferedImage[2];
    private BufferedImage imgEnemyDowm[] = new BufferedImage[2]; 
    private BufferedImage imgEnemyLeft[] = new BufferedImage[2]; 
    private BufferedImage imgEnemyRight[] = new BufferedImage[2];
    private boolean clockEfect;
    
	public Enemy(int col, int row, int type, StageControl stageControl) {
		super(stageControl);
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
			
			this.shieldLevel = 1;
			vel = Properties.VEL_NORMAL-.5;
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
			
			this.shieldLevel = 4;
			vel = Properties.VEL_NORMAL-.5;
			break;
		}
	}
	
	@Override
	public void updateDraw() {	

		if (!clockEfect) {
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

			Point p = PhysicsContol.collisionEnemy(this, stageControl.getElements_Enemy());

			if (p!=null) {

				switch (dir) {
				case 1://up
					setPosY(p.getY());
					break;
				case -1://down
					setPosY(p.getY()-heigth);
					break;
				case -2://left
					setPosX(p.getX());
					break;
				case 2://right
					setPosX(p.getX()-width);
					break;
				}
			}

			if(getPosX()<xI) setPosX(xI);
			if(getPosX()>xF) setPosX(xF);
			if(getPosY()<yI) setPosY(yI);
			if(getPosY()>yF) setPosY(yF);

			anim();
		}
	}	

	@Override
	public void draw(Graphics g) {

		if (clockEfect) {
			switch (this.dir) {
			case 1:
				g.drawImage(imgEnemyUp[0], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
				break;
			case -1:
				g.drawImage(imgEnemyDowm[0], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
				break;
			case -2:
				g.drawImage(imgEnemyLeft[0], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
				break;
			case 2:
				g.drawImage(imgEnemyRight[0], (int) getPosX(), (int)getPosY(),  getW(), getH(), null);
				break;
			}
		}else{
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
		}

	}

	public boolean isClockEfect() {
		return clockEfect;
	}

	public void setClockEfect(boolean clockEfect) {
		this.clockEfect = clockEfect;
	}
	
}
