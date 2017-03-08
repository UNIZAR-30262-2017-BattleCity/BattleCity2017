package gameController;

import java.awt.Graphics;
import java.util.LinkedList;

import elements.Bullet;

public abstract class TankControl {
	
	//tank
	protected int typeTank;  //tipo de tanque 0 = player, 1 = enemy
	protected double velX;
	protected double velY;
	protected int direction; //direccion hacia donde esta mirando 0=arriba, 1=abajo, 2=izquierda, 3=derecha
	protected double posX;
	protected double posY;
	
	//balas	
	protected int maxBulletsInProgres;
	protected LinkedList<Bullet> bulletsInProgres;
	protected Bullet tmpBullet;
	
	
	public void shoot(Bullet b) {
		if (bulletsInProgres.size()<maxBulletsInProgres) {
			bulletsInProgres.add(b);
		}		
	}
	
	public void updateDrawBullet() {
		for(int i=0;i<bulletsInProgres.size();i++) {
			
			tmpBullet = bulletsInProgres.get(i);
			
			if(tmpBullet.getPosX()<Properties.xInitStage) deleteBullet(tmpBullet);
	    	if(tmpBullet.getPosX()>Properties.xFinalStage) deleteBullet(tmpBullet);
	    	if(tmpBullet.getPosY()<Properties.yInitStage) deleteBullet(tmpBullet);
	    	if(tmpBullet.getPosY()>Properties.yFinalStage) deleteBullet(tmpBullet);
	    	tmpBullet.updateDraw();
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
		
	public abstract void updateDraw();
	
	public abstract void draw(Graphics g);
			
	public LinkedList<Bullet> getBulletsInProgres() {
		return bulletsInProgres;
	}

	public void setBulletsInProgres(LinkedList<Bullet> bulletsInProgres) {
		this.bulletsInProgres = bulletsInProgres;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
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

	
}
