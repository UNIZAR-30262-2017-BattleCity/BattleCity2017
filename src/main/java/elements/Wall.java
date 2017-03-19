package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import application.Properties;
import gameController.PhysicsContol;
import gameController.SpriteSheetControl;

public class Wall extends GameElement implements StageElement{

	private double posX;
	private double posY;
	private BufferedImage img;
	private boolean eagleWall;
	private int maxTimeItemEfect;
	private LinkedList<Wall> listEagleBrick;
	
	public Wall(double row, double col, int type, Stage stage, SpriteSheetControl ssc, boolean eagleWall) {
		super(stage);
		this.type = type;
		this.eagleWall = eagleWall;
		initWall(ssc);
		setInitPos(row+1,col+1);
		maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;
	}
	
	public void initWall(SpriteSheetControl ssc){
		switch (type) {
		case 1:
			img = Properties.SSCTANK.getImgBrick();
			break;
		case 2:
			img = Properties.SSCTANK.getImgHalfBrickH();
			break;
		case 3:
			img = Properties.SSCTANK.getImgIron();
			break;
		case 4:
			img = Properties.SSCTANK.getImgHalfIronH();
			break;
		case 5:
			img = Properties.SSCTANK.getImgHalfBrickV();
			break;
		case 6:
			img = Properties.SSCTANK.getImgHalfIronV();
			break;
		}	

		if (type == 2 || type == 4)
			setHeigth(heigth/2);
		if (type == 5 || type == 6) 
			setWidth(width/2);		

	}
	
	public void setInitPos( double row, double col){
    	
		//if (type == 1)
			
		setPosX(xI+(col*k)-k);
    	setPosY(yI+(row*k)-k);
    	
    	if (eagleWall)
    	listEagleBrick =  PhysicsContol.collisionEagleWall(this, stage.getElements(this));
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
