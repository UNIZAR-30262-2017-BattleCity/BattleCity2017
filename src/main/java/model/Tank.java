package model;

public abstract class Tank {
	
	//tank
	protected int typeTank;  //tipo de tanque (player/enemy)
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
		//tank
		direction = 0;		
		//balas
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
	
}




