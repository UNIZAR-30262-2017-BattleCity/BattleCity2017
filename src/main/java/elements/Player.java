package elements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.SpriteSheetControl;

public class Player extends Tank implements StageElement{

	private int userName;
	private int lifes;
	private int score;
    private int maxTimeItemEfect;
	private boolean itemTaked;
	private boolean gunEfectActivate;
	private Stage stage;
	private BufferedImage imgPlayerUp, imgPlayerDowm, imgPlayerLeft, imgPlayerRight;
    
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
	
    public Player(int col, int row, int lifes, Stage stage, SpriteSheetControl ssc) {
		super(stage);
    	this.setLifes(lifes);
		this.stage = stage;
		setTypeTank(0);		
		setInitPos(col, row);
		itemTaked= false;
		gunEfectActivate = false;
		score = 0;
		maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;
		imgPlayerUp = ssc.getPlayerUP();
		imgPlayerDowm = ssc.getPlayerDowm();
		imgPlayerLeft = ssc.getPlayerLeft();
		imgPlayerRight = ssc.getPlayerRight();
		this.shieldLevel = 1;
	}
        
    @Override
    public void draw(Graphics g) {
    	switch (this.direction) {
		case 1:
			g.drawImage(imgPlayerUp, (int) getPosX(), (int)getPosY(), width, heigth, null);
			break;
		case -1:
			g.drawImage(imgPlayerDowm, (int) getPosX(), (int)getPosY(), width, heigth, null);
			break;
		case -2:
			g.drawImage(imgPlayerLeft, (int) getPosX(), (int)getPosY(), width, heigth, null);
			break;
		case 2:
			g.drawImage(imgPlayerRight, (int) getPosX(), (int)getPosY(),width, heigth, null);
			break;
		}
		
	}
    
    @Override
    public void updateDraw(){
    	
    	switch (direction) {
		case 1://up
			posY -= vel;
			break;
		case -1://down
			posY += vel;
			break;
		case -2://left
			posX -= vel;
			break;
		case 2://right
			posX += vel;
			break;
		}
    	
    	Point p = PhysicsContol.collisionPlayer(this, stage.getElements(this));
    	
    	if (p!=null) {
    		
    		switch (direction) {
    		case 1://up
    			setPosY(p.getY());
    			break;
    		case -1://down
    			setPosY(p.getY()-heigth);
    			break;
    		case -2://left
    			setPosX(p.getX());
    			break;
    		case 2://right
    			setPosX(p.getX()-width);
    			break;
    		}
		}
    	
    	
    	if(getPosX()<xI) setPosX(xI);
    	if(getPosX()>xF) setPosX(xF);
    	if(getPosY()<yI) setPosY(yI);
    	if(getPosY()>yF) setPosY(yF);
    	
    	
    	if (itemTaked) {
			item();
		}
    	
    	if(shieldActivate){
			
		}else{
			
		}
        
    	
    }
        
    public void addScore(int sco){
		score = score + sco;
	}
	
	public void reduceLifes(){
    	lifes = lifes - 1;
    }

	
	public void starEfect(boolean star){		
		if (star) {
			setVel(getVel()+2);
		}else{
			setVel(getVel()-2);
		}
	}
	
	public void gunEfect(){
		if (gunEfectActivate) {
			setMaxBulletsInProgres(getMaxBulletsInProgres()+3);
		}else{
			setMaxBulletsInProgres(getMaxBulletsInProgres()-3);
		}		
	}
	
	public void shieldEfect() {
		shieldActivate = true;
	}
	
	public void item(){		
		if(maxTimeItemEfect>0){
			maxTimeItemEfect--;
		}else{
			maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;
			shieldActivate = false;
			gunEfectActivate = false;
			this.itemTaked = false;
			stage.setItemTaked(false);
			gunEfect();
			starEfect(false);
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

	public boolean isGunEfectActivate() {
		return gunEfectActivate;
	}

	public void setGunEfectActivate(boolean gunEfectActivate) {
		this.gunEfectActivate = gunEfectActivate;
	}

	public boolean isShieldActivate() {
		return shieldActivate;
	}

	public void setShieldActivate(boolean shieldActivate) {
		this.shieldActivate = shieldActivate;
	}
	
	
}
