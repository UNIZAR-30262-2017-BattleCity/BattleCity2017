package elements;

import java.awt.Graphics;
import java.util.LinkedList;

import application.Properties;

public abstract class Tank extends GameElement{
	
	//tank
	protected int typeTank;  //tipo de tanque 0 = player, 1 = enemy
	protected double vel;
	protected int dir;
	protected int shieldLevel;
	
	//balas	
	protected int maxBulletsInProgres;
	protected LinkedList<Bullet> bulletsInProgres;
	protected Bullet tmpBullet;
	
	public Tank(Stage stage){
		super(stage);
		dir = 1;
		maxBulletsInProgres = Properties.MAX_BULLETS_TANK;
		this.bulletsInProgres = new LinkedList<Bullet>();
	}
		
	public void shoot(Bullet b) {
		if (bulletsInProgres.size()<maxBulletsInProgres) {
			bulletsInProgres.add(b);
		}		
	}
	
	public void updateDrawBullet() {
		for(int i=0;i<bulletsInProgres.size();i++) {
			
			tmpBullet = bulletsInProgres.get(i);
			
			if(tmpBullet.getPosX()<xI) deleteBullet(tmpBullet);
	    	if(tmpBullet.getPosX()>xF) deleteBullet(tmpBullet);
	    	if(tmpBullet.getPosY()<yI) deleteBullet(tmpBullet);
	    	if(tmpBullet.getPosY()>yF) deleteBullet(tmpBullet);
	    	
	    	if (tmpBullet.isActive()) tmpBullet.updateDraw();
			else deleteBullet(tmpBullet);
		}
	}
	
	public void drawBullet(Graphics g){
		for(int i=0;i<bulletsInProgres.size();i++) {
			tmpBullet = bulletsInProgres.get(i);
			tmpBullet.draw(g);
		}
	}
	
	public void deleteBullet(Bullet b){
       bulletsInProgres.remove(b);
    }
				
	public LinkedList<Bullet> getBulletsInProgres() {
		return bulletsInProgres;
	}

	public void setBulletsInProgres(LinkedList<Bullet> bulletsInProgres) {
		this.bulletsInProgres = bulletsInProgres;
	}

	public double getVel() {
		return vel;
	}

	public void setVel(double vel) {
		this.vel = vel;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int direction) {
		this.dir = direction;
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

	public int getTypeTank() {
		return typeTank;
	}

	public void setTypeTank(int typeTank) {
		this.typeTank = typeTank;
	}

	public int getMaxBulletsInProgres() {
		return maxBulletsInProgres;
	}

	public void setMaxBulletsInProgres(int maxBulletsInProgres) {
		this.maxBulletsInProgres = maxBulletsInProgres;
	}

	public int getShieldLevel() {
		return shieldLevel;
	}

	public void setShieldLevel(int shieldLevel) {
		this.shieldLevel = shieldLevel;
	}

	public int getW() {
		return width;
	}

	public void setW(int w) {
		this.width = w;
	}

	public int getH() {
		return heigth;
	}

	public void setH(int h) {
		this.heigth = h;
	}
			
}
