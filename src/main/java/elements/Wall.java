package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;
import gameController.StageControl;

public class Wall extends GameElement implements StageElement{

	private BufferedImage img;
	private boolean eagleWall, eagleBrick;
	private int maxTimeItemEfect;
	private static final int k2 = k/2;
	
	public Wall(double row, double col, int key,int type, StageControl stageControl, boolean eagleWall) {
		super(stageControl);
		this.type = type;
		this.eagleWall = eagleWall;
		initWall();
		setInitPos(row+1,col+1,key);
		maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;
		eagleBrick = true;
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
    	
    }

	public void updateDraw() {
		if (eagleWall){
			item();			
		}
	}
	
	public void draw(Graphics g) {
		if (eagleBrick) {
			g.drawImage(img, (int) posX,(int) posY, width, heigth, null);
		}		
	}
	
	public void item(){		
		if(maxTimeItemEfect>0){
			maxTimeItemEfect--;
		}else{
			
			stageControl.deleteWall(this);
			stageControl.setItemTaked(false);
			stageControl.eagleIronWallEfect(false);
		}
	}

	public boolean isEagleBrick() {
		return eagleBrick;
	}

	public void setEagleBrick(boolean eagleBrick) {
		this.eagleBrick = eagleBrick;
	}
	
}
