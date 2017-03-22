package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.ImageControl;

public class Bullet extends GameElement implements StageElement{

    private double velBullet;
    private int dir;
    private BufferedImage imgBulletUp, imgBulletDowm, imgBulletLeft, imgBulletRight;
    private GameElement gE;
    
	public Bullet(double posX, double posY, int dir, int type, Stage stage, GameElement gE) {
		super(stage);
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.type = type;
		this.velBullet = Properties.VEL_BULLET;
		isActive = true;
		initBullet();
		this.gE = gE;
	}
	
	public void initBullet(){
		switch (type) {
		case 0://regular bullet
			imgBulletUp = ImageControl.getBulletUp();
			imgBulletDowm = ImageControl.getBulletDowm();
			imgBulletLeft = ImageControl.getBulletLeft();
			imgBulletRight = ImageControl.getBulletRight();
			break;
		case 1://hard bullet
			imgBulletUp = ImageControl.getBulletUp();
			imgBulletDowm = ImageControl.getBulletDowm();
			imgBulletLeft = ImageControl.getBulletLeft();
			imgBulletRight = ImageControl.getBulletRight();
			break;
		default:
			imgBulletUp = ImageControl.getBulletUp();
			imgBulletDowm = ImageControl.getBulletDowm();
			imgBulletLeft = ImageControl.getBulletLeft();
			imgBulletRight = ImageControl.getBulletRight();
			break;
		}
	}	
	
    public void draw(Graphics g) {
		
    	switch (this.dir) {
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
    	    	 
    	PhysicsContol.collisionBullet(this, gE,stage.getElements(this));
    	
    }
    
}
