package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.StageControl;
import gameController.ImageControl;

public class Bullet extends GameElement implements StageElement{

    private double velBullet;
    private int dir;
    private BufferedImage imgBulletUp, imgBulletDowm, imgBulletLeft, imgBulletRight;
    private Tank t;
    private static final int DELTA = Properties.SIZE_SQUARE/2 -4;
    
	public Bullet(double posX, double posY, int dir, int tier, double velBullet, StageControl stageControl, Tank t) {
		super(stageControl);
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.type = tier;
		this.velBullet = velBullet;
		isActive = true;
		initBullet();
		this.t = t;
	}
	
	public void initBullet(){
		switch (type) {//tier
		case 2://fast bullet
			initImgBullet();
			velBullet = Properties.VEL_BULLET+0.3;
			break;
		case 3://big bullet
			initImgBullet();
			velBullet = Properties.VEL_BULLET+0.5;
			break;
		case 4://big and hard bullet
			initImgBullet();		
			break;
		default:
			initImgBullet();
			break;
		}
		
		switch (dir) {
		case 1:			
			setHeigth(heigth/4);
			setWidth(width/4+1);
			setPosX(getPosX() + DELTA);
			break;
		case -1:
			setHeigth(heigth/4);
			setWidth(width/4+1);
			setPosX(getPosX() + DELTA);
			setPosY(getPosY() + Properties.SIZE_SQUARE);
			break;
		case -2:
			setHeigth(heigth/4+1);
			setWidth(width/4);
			setPosX(getPosX() + 1);
			setPosY(getPosY() + DELTA);
			break;
		case 2:
			setHeigth(heigth/4+1);
			setWidth(width/4);
			setPosX(getPosX() + Properties.SIZE_SQUARE);
			setPosY(getPosY() + DELTA);
			break;
		}		
		
	}	
	
	public void initImgBullet(){
		imgBulletUp = ImageControl.getBulletUp();
		imgBulletDowm = ImageControl.getBulletDowm();
		imgBulletLeft = ImageControl.getBulletLeft();
		imgBulletRight = ImageControl.getBulletRight();
	}
	
	
    public void draw(Graphics g) {
		
    	switch (dir) {
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
    	switch (dir) {
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
    	    	 
    	PhysicsContol.collisionBullet(this, t,stageControl);
    	    	
		if(getPosX()<Properties.xIB) {
			setActive(false); 
			t.setShoot(false);
			return;}
	    if(getPosX()>Properties.xFB) {
	    	setActive(false); 
	    	t.setShoot(false);
	    	return;}
	    if(getPosY()<Properties.yIB) {
	    	setActive(false); 
	    	t.setShoot(false);
	    	return;}
	    if(getPosY()>Properties.yFB) {
	    	setActive(false); 
	    	t.setShoot(false);
	    	return;}		   	
    	
    }

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
    
}
