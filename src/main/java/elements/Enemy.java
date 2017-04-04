package elements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

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
    private int timeUpdateIA;
    private int timeUpdateIARandom;
    private int timeUpdateCollision;
	private boolean updateIA;
	private boolean updateIARandom;
	private boolean collision;
    
	public Enemy(int col, int row, int type, StageControl stageControl) {
		super(col,row,stageControl);
		setTypeTank(1);
		this.type = type;
		dir=-1;
		initEnemy();
		timeUpdateIA = Properties.TIME_UPDATE_IA;
		timeUpdateIARandom = Properties.TIME_UPDATE_IA_RANDOM;
		timeUpdateCollision = Properties.TIME_UPDATE_COLLISION;
		updateIARandom = false;
		updateIA = false;
		collision = false;
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
			
			this.tier = 1;
			this.shieldLevel = 1;
			vel = Properties.VEL_NORMAL-.70;
			velBullet = Properties.VEL_BULLET-1;
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
			
			this.tier = 2;
			this.shieldLevel = 1;
			vel = Properties.VEL_ENEMY_FAST;
			velBullet = Properties.VEL_BULLET;
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
			
			this.tier = 3;
			this.shieldLevel = 1;
			vel = Properties.VEL_NORMAL-.5;
			velBullet = Properties.VEL_BULLET+1;
			break;
			
		case 4://armor tank
			imgEnemyUp[0] = ImageControl.getEnemy4RedUp();
			imgEnemyUp[1] = ImageControl.getEnemy4RedUpB();
			imgEnemyDowm[0] = ImageControl.getEnemy4RedDowm();
			imgEnemyDowm[1] = ImageControl.getEnemy4RedDowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy4RedLeft();
			imgEnemyLeft[1] = ImageControl.getEnemy4RedLeftB();
			imgEnemyRight[0] = ImageControl.getEnemy4RedRight();
			imgEnemyRight[1] = ImageControl.getEnemy4RedRightB();
			
			this.tier = 4;
			this.shieldLevel = 4;
			vel = Properties.VEL_NORMAL-.5;
			velBullet = Properties.VEL_BULLET;
			break;
		}
	}
	
	@Override
	public void updateDraw() {	

		if (isBorn) {
			if (!clockEfect) {
				move();
				collision();
				anim();
			}
		}else{
			birthEffect.setPosX(posX);
			birthEffect.setPosY(posY);
			birthEffect.updateDraw();
			if (next(120)) {
				isBorn = true;
			}
		}
	}	
	
	public void move(){
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
	}
	
	public void avoidElements() {
		Random random = new Random();
		int valor = random.nextInt(2); 
		
		switch (dir) {
		case 1://up
			if (valor == 1) setDir(2);
			else setDir(-2);
			break;
		case -1://down
			if (valor == 1) setDir(2);
			else setDir(-2);
			break;
		default://right or left
			setDir(-1);
			break;
		}
		collision = true;
	}
	
	public void updateIA(){		
		if(timeUpdateIA>0){
			timeUpdateIA--;
		}else{
			timeUpdateIA = Properties.TIME_UPDATE_IA;
			updateIA = false;
			collision = false;
		}
	}
	
	public void updateIARamdon(){		
		if(timeUpdateIARandom>0){
			timeUpdateIARandom--;
		}else{
			timeUpdateIARandom = Properties.TIME_UPDATE_IA_RANDOM;
			updateIARandom = false;
		}
	}
	
	public void updateCollision(){		
		if(timeUpdateCollision>0){
			timeUpdateCollision--;
		}else{
			timeUpdateCollision = Properties.TIME_UPDATE_COLLISION;
			updateIARandom = false;
		}
	}
	
	public void collision(){
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

	}

	@Override
	public void draw(Graphics g) {

		if (isBorn) {
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
		}else{
			birthEffect.draw(g);
		}
	}
	
	public void updateColor(){
		switch (shieldLevel) {
		case 1:
			imgEnemyUp[0] = ImageControl.getEnemy4GrayUp();
			imgEnemyUp[1] = ImageControl.getEnemy4GrayUpB();
			imgEnemyDowm[0] = ImageControl.getEnemy4GrayDowm();
			imgEnemyDowm[1] = ImageControl.getEnemy4GrayDowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy4GrayLeft();
			imgEnemyLeft[1] = ImageControl.getEnemy4GrayLeftB();
			imgEnemyRight[0] = ImageControl.getEnemy4GrayRight();
			imgEnemyRight[1] = ImageControl.getEnemy4GrayRightB();
			break;
		case 2:
			imgEnemyUp[0] = ImageControl.getEnemy4GoldUp();
			imgEnemyUp[1] = ImageControl.getEnemy4GoldUpB();
			imgEnemyDowm[0] = ImageControl.getEnemy4GoldDowm();
			imgEnemyDowm[1] = ImageControl.getEnemy4GoldDowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy4GoldLeft();
			imgEnemyLeft[1] = ImageControl.getEnemy4GoldLeftB();
			imgEnemyRight[0] = ImageControl.getEnemy4GoldRight();
			imgEnemyRight[1] = ImageControl.getEnemy4GoldRightB();
			break;
		case 3:
			imgEnemyUp[0] = ImageControl.getEnemy4GreenUp();
			imgEnemyUp[1] = ImageControl.getEnemy4GreenUpB();
			imgEnemyDowm[0] = ImageControl.getEnemy4GreenDowm();
			imgEnemyDowm[1] = ImageControl.getEnemy4GreenDowmB();
			imgEnemyLeft[0] = ImageControl.getEnemy4GreenLeft();
			imgEnemyLeft[1] = ImageControl.getEnemy4GreenLeftB();
			imgEnemyRight[0] = ImageControl.getEnemy4GreenRight();
			imgEnemyRight[1] = ImageControl.getEnemy4GreenRightB();
			break;
		}
	}
	
	public boolean isClockEfect() {
		return clockEfect;
	}

	public void setClockEfect(boolean clockEfect) {
		this.clockEfect = clockEfect;
	}

	public boolean isUpdateIARandom() {
		return updateIARandom;
	}

	public void setUpdateIARandom(boolean updateIA) {
		this.updateIARandom = updateIA;
	}

	public boolean isUpdateIA() {
		return updateIA;
	}

	public void setUpdateIA(boolean updateIA) {
		this.updateIA = updateIA;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getTimeUpdateIA() {
		return timeUpdateIA;
	}

	public void setTimeUpdateIA(int timeUpdateIA) {
		this.timeUpdateIA = timeUpdateIA;
	}

	public int getTimeUpdateIARandom() {
		return timeUpdateIARandom;
	}

	public void setTimeUpdateIARandom(int timeUpdateIARandom) {
		this.timeUpdateIARandom = timeUpdateIARandom;
	}

	public int getTimeUpdateCollision() {
		return timeUpdateCollision;
	}

	public void setTimeUpdateCollision(int timeUpdateCollision) {
		this.timeUpdateCollision = timeUpdateCollision;
	}
	
}
