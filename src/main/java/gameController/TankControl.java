package gameController;

import elements.Bullet;

public abstract class TankControl {
	
	//tank
	protected int typeTank;  //tipo de tanque 0 = player, 1 = enemy
	protected int velocity;
	protected int direction; //direccion hacia donde esta mirando 0=arriba, 1=abajo, 2=izquierda, 3=derecha
	protected int positionX;
	protected int positionY;
	
	//balas
	protected int velocityBullet;	
	protected int numBulletsInProgres;
	protected int maxBulletsInProgres;
	protected Bullet[] bulletsInProgres;
	
	public void init(){
		direction = 0;
		velocity = 1;
		
		velocityBullet = 1; 
		numBulletsInProgres = 0;
  		maxBulletsInProgres = 2;
		bulletsInProgres = new Bullet[maxBulletsInProgres];
		
	}
	
	public void shoot(int type) {
		if (numBulletsInProgres<maxBulletsInProgres) {
			bulletsInProgres[numBulletsInProgres] = new Bullet(positionX, positionY, direction, type);
			numBulletsInProgres++;
		}
	}
	
	public void deleteBullet(int numberBullet){
        if(0<numBulletsInProgres){
            for(int i=numberBullet;i<numBulletsInProgres-1;i++){
            	bulletsInProgres[i]=bulletsInProgres[i+1];
            }
            numBulletsInProgres--;
        }
    }
	
	public abstract void moveControl();
	
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getTypeTank() {
		return typeTank;
	}

	public void setTypeTank(int typeTank) {
		this.typeTank = typeTank;
	}

	public int getVelocityBullet() {
		return velocityBullet;
	}

	public void setVelocityBullet(int velocityBullet) {
		this.velocityBullet = velocityBullet;
	}

	public int getNumBulletsInProgres() {
		return numBulletsInProgres;
	}

	public void setNumBulletsInProgres(int numBulletsInProgres) {
		this.numBulletsInProgres = numBulletsInProgres;
	}

	public int getMaxBulletsInProgres() {
		return maxBulletsInProgres;
	}

	public void setMaxBulletsInProgres(int maxBulletsInProgres) {
		this.maxBulletsInProgres = maxBulletsInProgres;
	}

	public Bullet[] getBulletsInProgres() {
		return bulletsInProgres;
	}

	public void setBulletsInProgres(Bullet[] bulletsInProgres) {
		this.bulletsInProgres = bulletsInProgres;
	}
	
}




