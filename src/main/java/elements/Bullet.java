package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.ImageControl;

public class Bullet extends GameElement implements StageElement{
	
	private double posX;
    private double posY;
    private double velBullet;
    private int direction;
    private int type;	//tipo A=0 , tipo B=1
    private BufferedImage imgBulletUp, imgBulletDowm, imgBulletLeft, imgBulletRight;
    private ImageControl ssc;
    private Stage stage;
    private boolean isActive;
    
	public Bullet(double posX, double posY, int direction, int type, ImageControl ssc, Stage stage) {
		super(stage);
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.type = type;
		this.ssc = ssc;
		this.stage = stage;
		this.velBullet = Properties.VEL_BULLET;
		isActive = true;
		initBullet();
	}
	
	public void initBullet(){
		switch (type) {
		case 0://regular bullet
			imgBulletUp = ssc.getBulletUp();
			imgBulletDowm = ssc.getBulletDowm();
			imgBulletLeft = ssc.getBulletLeft();
			imgBulletRight = ssc.getBulletRight();
			break;
		case 1://hard bullet
			imgBulletUp = ssc.getBulletUp();
			imgBulletDowm = ssc.getBulletDowm();
			imgBulletLeft = ssc.getBulletLeft();
			imgBulletRight = ssc.getBulletRight();
			break;
		default:
			imgBulletUp = ssc.getBulletUp();
			imgBulletDowm = ssc.getBulletDowm();
			imgBulletLeft = ssc.getBulletLeft();
			imgBulletRight = ssc.getBulletRight();
			break;
		}
	}	
	
    public void draw(Graphics g) {
		
    	switch (this.direction) {
		case 1:
			g.drawImage(imgBulletUp, (int) getPosX(), (int)getPosY(), null);
			break;
		case -1:
			g.drawImage(imgBulletDowm, (int) getPosX(), (int)getPosY(), null);
			break;
		case -2:
			g.drawImage(imgBulletLeft, (int) getPosX(), (int)getPosY(), null);
			break;
		case 2:
			g.drawImage(imgBulletRight, (int) getPosX(), (int)getPosY(), null);
			break;
		}

	}
    
    public void updateDraw(){	
    	switch (direction) {
		case 1://up
			posY -= velBullet;
			break;
		case -1://down
			posY += velBullet;
			break;
		case -2://left
			posX -= velBullet;
			break;
		case 2://rigth
			posX += velBullet;
			break;
		}
    	    	 
    	PhysicsContol.collisionBullet(this, stage.getElements(this));
    	
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

	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
      
    
    
}
