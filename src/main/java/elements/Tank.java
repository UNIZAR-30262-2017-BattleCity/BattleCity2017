package elements;

import application.Properties;
import gameController.StageControl;

public abstract class Tank extends GameElement{
	
	//tank
	protected int typeTank;  //tipo de tanque 0 = player, 1 = enemy
	protected double vel;
	protected int dir;
	protected int shieldLevel;
	protected int tier;
	
	//bullets	
	protected int bulletsInProgres;
	
	public Tank(StageControl stageControl){
		super(stageControl);
		dir = 1;
		bulletsInProgres = 0;
		tier = 1;
	}
		
	public void shoot(Bullet b) {
		if (bulletsInProgres<Properties.MAX_BULLETS_TANK) {
			stageControl.spawnBullets(b);
			bulletsInProgres++;
		}
	}
	
	public void anim(){
		if(timeAnim>0){
			timeAnim--;
			anim = 0;
		}else{
			timeAnim = Properties.TIME_ANIM;
			anim = 1;
		}
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

	public int getBulletsInProgres() {
		return bulletsInProgres;
	}

	public void setBulletsInProgres(int bulletsInProgres) {
		this.bulletsInProgres = bulletsInProgres;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}
	
}
