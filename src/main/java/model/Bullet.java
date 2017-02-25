package model;

public class Bullet {
	
	private int positionX;
    private int positionY;
    private int direction;
    private int type;	//tipo A=0 , tipo B=1
    
	public Bullet(int positionX, int positionY, int direction, int type) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.direction = direction;
		this.type = type;
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
