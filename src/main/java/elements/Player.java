package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import application.Properties;
import gameController.SpriteSheetControl;

public class Player extends Tank{

	private int userName;
	private int lifes;
	private int score;	
    private int timeItem;
    private int maxTimeItem;
	private boolean itemTaked;
	private BufferedImage imgPlayerUp, imgPlayerDowm, imgPlayerLeft, imgPlayerRight;
    
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
	
    public Player(double x, double y, int lifes, SpriteSheetControl ssc) {
		this.setLifes(lifes);
		setTypeTank(0);
		setPosX(x);
		setPosY(y);
		itemTaked= false;
		timeItem = 0;
		maxTimeItem = Properties.MAX_TIME_ITEM;
		imgPlayerUp = ssc.getPlayerUP();
		imgPlayerDowm = ssc.getPlayerDowm();
		imgPlayerLeft = ssc.getPlayerLeft();
		imgPlayerRight = ssc.getPlayerRight();
		this.direction = 0;		
		this.maxBulletsInProgres = Properties.MAX_BULLETS_PLAYER;
		this.bulletsInProgres = new LinkedList<Bullet>();
	}
    
    @Override
    public void draw(Graphics g) {
    	switch (this.direction) {
		case 0:
			g.drawImage(imgPlayerUp, (int) getPosX(), (int)getPosY(), null);
			break;
		case 1:
			g.drawImage(imgPlayerDowm, (int) getPosX(), (int)getPosY(), null);
			break;
		case 2:
			g.drawImage(imgPlayerLeft, (int) getPosX(), (int)getPosY(), null);
			break;
		case 3:
			g.drawImage(imgPlayerRight, (int) getPosX(), (int)getPosY(), null);
			break;
		}
		
	}
    
    @Override
    public void updateDraw(){    	
    	setPosX(getPosX()+velX);
    	setPosY(getPosY()+velY);
    	
    	if(getPosX()<Properties.X_INIT_STAGE) setPosX(Properties.X_INIT_STAGE);
    	if(getPosX()>Properties.X_FINAL_STAGE) setPosX(Properties.X_FINAL_STAGE);
    	if(getPosY()<Properties.Y_INIT_STAGE) setPosY(Properties.Y_INIT_STAGE);
    	if(getPosY()>Properties.Y_FINAL_STAGE) setPosY(Properties.Y_FINAL_STAGE);
    	
    	
    	if (itemTaked) {
			if(timeItem<maxTimeItem){
				timeItem++;
			}else{
				timeItem = 0;
				itemTaked = false;
			}
		}
        
    	
    }
        
    public void addScore(int sco){
		score = score + sco;
	}
	
	public void reduceLifes(){
    	lifes = lifes - 1;
    }

	public void starEfect(){
		
	}
	
	public void shieldEfect() {
		if(shieldActivate){
			
		}
	}
    	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getUserName() {
		return userName;
	}

	public void setUserName(int userName) {
		this.userName = userName;
	}

	public boolean isItemTaked() {
		return itemTaked;
	}

	public void setItemTaked(boolean itemTaked) {
		this.itemTaked = itemTaked;
	}

	public void cascoEfect() {
		// TODO Auto-generated method stub
		
	}

	
	
}
