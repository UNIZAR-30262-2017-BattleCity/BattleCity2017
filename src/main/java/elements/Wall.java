package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import application.Properties;
import gameController.PhysicsContol;
import gameController.ImageControl;

public class Wall extends GameElement implements StageElement{

	private double posX;
	private double posY;
	private BufferedImage img;
	private boolean eagleWall;
	private int maxTimeItemEfect;
	private LinkedList<Wall> listEagleBrick;
	private static final int k2 = k/2;
	
	public Wall(double row, double col, int key,int type, Stage stage, boolean eagleWall) {
		super(stage);
		this.type = type;
		this.eagleWall = eagleWall;
		initWall();
		setInitPos(row+1,col+1,key);
		maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;
	}
	
	public void initWall(){
		switch (type) {
		case 1:
			img = ImageControl.getImgBrick();
			setHeigth(heigth/2);
			setWidth(width/2);			
			break;
		case 2:
			img = ImageControl.getImgIron();
			break;
		case 3:
			img = ImageControl.getImgHalfIronH();
			setHeigth(heigth/2);
			break;
		case 4:
			img = ImageControl.getImgHalfIronV();
			setWidth(width/2);
			break;
		}		

	}
	
	public void setInitPos(double row, double col, int key){    	
		
		if (type==1||type==2) {
			int col2 = 0;
			int row2 = 0;
			switch (key) {
			case 0:
				col2 = 1;
				row2 = 1;
				break;
			case 1:
				col2 = 2;
				row2 = 1;
				break;
			case 2:
				col2 = 1;
				row2 = 2;
				break;
			case 3:
				col2 = 2;
				row2 = 2;
				break;
			}
			setPosX((xI+(col*k)-k)+((col2*k2)-k2));
			setPosY((yI+(row*k)-k)+((row2*k2)-k2));
		}else{
			setPosX(xI+(col*k)-k);
			setPosY(yI+(row*k)-k);
		}
    	
    	if (eagleWall)
    	listEagleBrick = PhysicsContol.collisionEagleWall(this, stage.getElements(this));
    }

	public void updateDraw() {
		if (eagleWall){
			item();			
		}
	}
	
	public void draw(Graphics g) {
									
		g.drawImage(img, (int) posX,(int) posY, width, heigth, null);
		
	}
	
	public void item(){		
		if(maxTimeItemEfect>0){
			maxTimeItemEfect--;
		}else{
			if (listEagleBrick.size()>0) {				
				for (Wall wall : listEagleBrick) {
					wall.setActive(true);
					stage.spawnElements(wall);
				}
			}
			stage.deleteElement(this);
			stage.setItemTaked(false);
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
