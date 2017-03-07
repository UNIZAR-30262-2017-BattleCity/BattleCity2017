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
	
	
	public void shoot(Bullet b) {
		if (bulletsInProgres.size()<maxBulletsInProgres) {
			bulletsInProgres.add(b);
		}		
	}
	
	public void updateDrawBullet() {
		for (Bullet bullet : bulletsInProgres) {
			bullet.updateDraw();
			if(bullet.getPosX()<Properties.xInitStage) deleteBullet(bullet);
	    	if(bullet.getPosX()>Properties.xFinalStage) deleteBullet(bullet);
	    	if(bullet.getPosY()<Properties.yInitStage) deleteBullet(bullet);
	    	if(bullet.getPosY()>Properties.yFinalStage) deleteBullet(bullet);
	    	bullet.updateDraw();
		}
	}
	
	public void drawBullet(Graphics g){
		for (Bullet bullet : bulletsInProgres) {
			bullet.draw(g);
		}
	}
	
	public void deleteBullet(Bullet b){
       bulletsInProgres.remove(b);
    }
	
	public abstract void moveControl();
			
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
