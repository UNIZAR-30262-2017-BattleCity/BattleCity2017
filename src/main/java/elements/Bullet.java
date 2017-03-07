package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.Properties;
import gameController.SpriteSheetControl;

public class Bullet {
	
	private double posX;
    private double posY;
    private double velBullet;
    private int direction;
    private int type;	//tipo A=0 , tipo B=1
    private BufferedImage img;
    private SpriteSheetControl ssc;
    
	public Bullet(double posX, double posY, int direction, int type, SpriteSheetControl ssc) {
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.type = type;	
		this.ssc = ssc;
		this.velBullet = Properties.velBullet;
	}
	
    
    public void draw(Graphics g) {
		
    	switch (type) {
		case 0://regular bullet
			img = ssc.getSprite(1, 1, 16, 16);
			break;
		case 1://hard bullet
			img = ssc.getSprite(1, 1, 16, 16);
			break;
		default:
			img = ssc.getSprite(1, 1, 16, 16);
			break;
		}
    	
    	g.drawImage(img, (int) posX, (int) posY, null);

	}
    
    public void updateDraw(){	
    	switch (direction) {
		case 0://up
			posY -= velBullet;
			break;
		case 1://down
			posY += velBullet;
			break;
		case 2://left
			posX -= velBullet;
			break;
		case 3://rigth
			posX += velBullet;
			break;
		}
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
      
    
    
}
