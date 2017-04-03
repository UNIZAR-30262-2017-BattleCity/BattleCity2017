package elements;

import application.Properties;
import gameController.SoundControl;
import gameController.StageControl;

public abstract class Tank extends GameElement{
	
	//tank
	protected int typeTank;  //tipo de tanque 0 = player, 1 = enemy
	protected double vel;
	protected double velBullet;
	protected int dir;
	protected int shieldLevel;
	protected int tier;
	protected int timeToNext;
	
	//bullets	
	protected boolean shoot;
	
	public Tank(StageControl stageControl){
		super(stageControl);
		dir = 1;
		width = Properties.SIZE_SQUARE-2;
		heigth = Properties.SIZE_SQUARE-2;
	}
		
	public void shoot(Bullet b) {
		if (!shoot) {
			stageControl.spawnBullets(b);
			SoundControl.playSound("shoot");
			shoot = true;
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
	
	public boolean next(int time){
		if(timeToNext<time){
			timeToNext++;
			return false;
		}else{
			timeToNext = 0;
			return true;
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
	
	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public int getTier() {
		return tier;
	}

	public double getVelBullet() {
		return velBullet;
	}

	public void setVelBullet(double velBullet) {
		this.velBullet = velBullet;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}
	
}
