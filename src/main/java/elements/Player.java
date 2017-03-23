package elements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.ImageControl;

public class Player extends Tank implements StageElement{
	
	private final static BufferedImage[] imgPlayerUp = {ImageControl.getPlayerUP(),ImageControl.getPlayerUP1()};
	private final static BufferedImage[] imgPlayerDowm = {ImageControl.getPlayerDowm(),ImageControl.getPlayerDowm1()};
	private final static BufferedImage[] imgPlayerLeft = {ImageControl.getPlayerLeft(),ImageControl.getPlayerLeft1()};
	private final static BufferedImage[] imgPlayerRight = {ImageControl.getPlayerRight(),ImageControl.getPlayerRight1()};
    private int userName;
	private int lifes;
	private int score;
    private int maxTimeItemEfect;
	private boolean itemTaked;
	private boolean gunEfectActivate;

	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
	
    public Player(int col, int row, int lifes, Stage stage) {
		super(stage);
    	this.setLifes(lifes);
		this.stage = stage;
		setTypeTank(0);		
		setInitPos(col, row);
		itemTaked= false;
		gunEfectActivate = false;
		score = 0;
		maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;		
		this.shieldLevel = 1;
	}
        
    @Override
    public void draw(Graphics g) {

    	if (vel!=0) {
    		switch (this.dir) {
    		case 1:
				g.drawImage(imgPlayerUp[anim], (int) getPosX(), (int)getPosY(), width, heigth, null);
    			break;
    		case -1:
    			g.drawImage(imgPlayerDowm[anim], (int) getPosX(), (int)getPosY(), width, heigth, null);
    			break;
    		case -2:
    			g.drawImage(imgPlayerLeft[anim], (int) getPosX(), (int)getPosY(), width, heigth, null);
    			break;
    		case 2:
    			g.drawImage(imgPlayerRight[anim], (int) getPosX(), (int)getPosY(),width, heigth, null);
    			break;
    		}
    	}else{
    		switch (this.dir) {
    		case 1:
    			g.drawImage(imgPlayerUp[0], (int) getPosX(), (int)getPosY(), width, heigth, null);
    			break;
    		case -1:
    			g.drawImage(imgPlayerDowm[0], (int) getPosX(), (int)getPosY(), width, heigth, null);
    			break;
    		case -2:
    			g.drawImage(imgPlayerLeft[0], (int) getPosX(), (int)getPosY(), width, heigth, null);
    			break;
    		case 2:
    			g.drawImage(imgPlayerRight[0], (int) getPosX(), (int)getPosY(),width, heigth, null);
    			break;
    		}
    	}



	}
    
    @Override
    public void updateDraw(){
    	
    	switch (dir) {
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
    		
    		switch (dir) {
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
    	
		anim();
		
        
    	
    }
        
    public void addScore(int sco){
		score = score + sco;
	}
	
	public void reduceLifes(){
    	lifes = lifes - 1;
    }
	
	public void starEfect(){		
		addScore(500);
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
