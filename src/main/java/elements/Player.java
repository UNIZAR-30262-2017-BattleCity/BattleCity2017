package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.PhysicsContol;
import gameController.StageControl;
import gameController.FontControl;
import gameController.ImageControl;

public class Player extends Tank implements StageElement{
	
	private final static BufferedImage[] imgPlayerUp = {ImageControl.getPlayer1UP(),ImageControl.getPlayer1UPB()};
	private final static BufferedImage[] imgPlayerDowm = {ImageControl.getPlayer1Dowm(),ImageControl.getPlayer1DowmB()};
	private final static BufferedImage[] imgPlayerLeft = {ImageControl.getPlayer1Left(),ImageControl.getPlayer1LeftB()};
	private final static BufferedImage[] imgPlayerRight = {ImageControl.getPlayer1Right(),ImageControl.getPlayer1RightB()};
    private int userName;
	private int lifes;
	private int score;
    private int maxTimeItemEfect;
	private boolean itemTaked;
	private boolean gunEfectActivate;
	private boolean updateLifes;
	private boolean updateScore;
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
	
	
    public Player(int col, int row, int lifes, StageControl stageControl) {
		super(stageControl);
    	this.setLifes(lifes);
		this.stageControl = stageControl;
		setTypeTank(0);		
		setInitPos(col, row);
		itemTaked= false;
		gunEfectActivate = false;
		updateLifes = true;
		updateScore = true;
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

    	drawBullet(g);
    	if (updateLifes) {
    		g.setColor(Color.darkGray);
	        g.fillRect(Properties.X_INIT_INFO+60, Properties.Y_IP_LIFES-15, 40, 20);
	        g.setColor(Color.black);
    		g.setFont( FontControl.ARIAL);
    		g.drawString(""+lifes, Properties.X_INIT_INFO+60, Properties.Y_IP_LIFES );
    		updateLifes = false;
		}
    	if (updateScore) {
    		g.setColor(Color.darkGray);
	        g.fillRect(Properties.X_INIT_INFO+60, Properties.Y_IP_LIFES, 40, 20);
	        g.setColor(Color.black);
    		g.setFont( FontControl.ARIAL);
    		g.drawString(""+score, Properties.X_INIT_INFO+60, Properties.Y_IP_SCORE );
    		updateScore = false;
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
    	
    	Point p = PhysicsContol.collisionPlayer(this, stageControl.getElements());
    	
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
		updateDrawBullet();
        
    	
    }
        
    public void addScore(int type){		
		updateScore = true;
		switch (type) {
		case 1:
			score = score + 100;
			break;
		case 2:
			score = score + 200;
			break;
		case 3:
			score = score + 300;
			break;
		case 4:
			score = score + 400;
			break;
		case 5:
			score = score + 500;
			break;
		}
	}
    
	
	public void reduceLifes(){
    	lifes--;
    	updateLifes = true;
    	if (lifes<0) {
			
		}else{
			setInitPos(Properties.POS_INIT_PLAYER[0], Properties.POS_INIT_PLAYER[1]);
			shieldEfect();
		}
    	
    }
	
	public void starEfect(){		
		addScore(5);
		updateScore = true;
	}
	
	public void tankEfect(){		
		lifes++;
		updateLifes = true;
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
			stageControl.setItemTaked(false);
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
