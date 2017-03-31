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
	
	private BufferedImage[] imgPlayer1Up = new BufferedImage[2];
	private BufferedImage[] imgPlayer1Dowm = new BufferedImage[2];
	private BufferedImage[] imgPlayer1Left = new BufferedImage[2];
	private BufferedImage[] imgPlayer1Right = new BufferedImage[2];
	private BufferedImage[] imgPlayer2Up = new BufferedImage[2];
	private BufferedImage[] imgPlayer2Dowm = new BufferedImage[2];
	private BufferedImage[] imgPlayer2Left = new BufferedImage[2];
	private BufferedImage[] imgPlayer2Right = new BufferedImage[2];
    private int userName;
	private int lifes;
	private int score;
	private int player;
    private int maxTimeItemEfect;
	private boolean itemTaked;
	private boolean gunEfectActivate;
	private boolean updateLifes;
	private boolean updateScore;
	private boolean gameOver;
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
	
	
    public Player(int col, int row, int lifes, int player, StageControl stageControl) {
		super(stageControl);
    	this.setLifes(lifes);
		this.stageControl = stageControl;
		setTypeTank(0);		
		setInitPos(col, row);
		this.player = player;
		initPlayer();
		itemTaked= false;
		gunEfectActivate = false;
		updateLifes = true;
		updateScore = true;
		score = 0;
		maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;		
		this.shieldLevel = 1;
	}
    
    public void initPlayer(){
    	if (player==1) {
    		imgPlayer1Up[0] = ImageControl.getPlayer1UP();
    		imgPlayer1Dowm[0] = ImageControl.getPlayer1Dowm();
    		imgPlayer1Left[0] = ImageControl.getPlayer1Left();
    		imgPlayer1Right[0] = ImageControl.getPlayer1Right();
    		imgPlayer1Up[1] = ImageControl.getPlayer1UPB();
    		imgPlayer1Dowm[1] = ImageControl.getPlayer1DowmB();
    		imgPlayer1Left[1] = ImageControl.getPlayer1LeftB();
    		imgPlayer1Right[1] = ImageControl.getPlayer1RightB();
		}else{
    		imgPlayer2Up[0] = ImageControl.getPlayer2UP();
    		imgPlayer2Dowm[0] = ImageControl.getPlayer2Dowm();
    		imgPlayer2Left[0] = ImageControl.getPlayer2Left();
    		imgPlayer2Right[0] = ImageControl.getPlayer2Right();
    		imgPlayer2Up[1] = ImageControl.getPlayer2UPB();
    		imgPlayer2Dowm[1] = ImageControl.getPlayer2DowmB();
    		imgPlayer2Left[1] = ImageControl.getPlayer2LeftB();
    		imgPlayer2Right[1] = ImageControl.getPlayer2RightB();
		}
    }
    
    @Override
    public void draw(Graphics g) {

    	if (vel!=0) {
    		switch (this.dir) {
    		case 1:
    			drawPlayer(g, imgPlayer1Up[anim],imgPlayer2Up[anim]);
    			break;
    		case -1:
    			drawPlayer(g, imgPlayer1Dowm[anim],imgPlayer2Dowm[anim]);
    			break;
    		case -2:
    			drawPlayer(g, imgPlayer1Left[anim],imgPlayer2Left[anim]);
    			break;
    		case 2:
    			drawPlayer(g, imgPlayer1Right[anim],imgPlayer2Right[anim]);
    			break;
    		}
    	}else{
    		switch (this.dir) {
    		case 1:
    			drawPlayer(g, imgPlayer1Up[0],imgPlayer2Up[0]);
    			break;
    		case -1:
    			drawPlayer(g, imgPlayer1Dowm[0],imgPlayer2Dowm[0]);
    			break;
    		case -2:
    			drawPlayer(g, imgPlayer1Left[0],imgPlayer2Left[0]);
    			break;
    		case 2:
    			drawPlayer(g, imgPlayer1Right[0],imgPlayer2Right[0]);
    			break;
    		}
    	}

    	if (updateLifes) {
    		if (player==1) {
				updateLifes(g, Properties.Y_IP_LIFES);
			}else{
				updateLifes(g, Properties.Y_IIP_LIFES);
			}
		}
    	
    	if (gameOver) {
    		if (player==1) {
				paintGameOver(g, Properties.Y_IP_LIFES);
			}else{
				paintGameOver(g, Properties.Y_IIP_LIFES);
			}
		}
    	
    	if (updateScore) {
    		if (player==1) {
				updateScore(g, Properties.Y_IP_SCORE);
			}else{
				updateScore(g, Properties.Y_IIP_SCORE);
			}
		}

	}
    
    public void drawPlayer(Graphics g, BufferedImage p1, BufferedImage p2){
    	if (player==1) {
    		g.drawImage(p1, (int) getPosX(), (int)getPosY(), width, heigth, null);
		}else{
			g.drawImage(p2, (int) getPosX(), (int)getPosY(), width, heigth, null);
		}
    }
    
    @Override
    public void updateDraw(){
    	
    	anim();
    	move();    	
    	collision();
    	
    	if (itemTaked) {
			item();
		}
    	
    	if(shieldActivate){
			
		}else{
			
		}      
    	
    }
        
    private void move() {
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
		
	}

	private void collision() {
    	Point p = PhysicsContol.collisionPlayer(this, stageControl.getElements_Player());
    	
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
    
    public void updateScore(Graphics g, int y){
    	g.setColor(Color.darkGray);
        g.fillRect(Properties.X_SCORE, (int) (y-Properties.DELTA_SCORE_LIFE), 
        		Properties.SCORE_LIFE_BACKGROUND_WIDTH, 
        		Properties.SCORE_LIFE_BACKGROUND_HEIGHT);
        g.setColor(Color.black);
		g.setFont( FontControl.ARIAL);
		g.drawString(""+score, Properties.X_SCORE, y);
		updateScore = false;
    }
    
    public void updateLifes(Graphics g, int y){
    	g.setColor(Color.darkGray);
        g.fillRect(Properties.X_LIFES, (int) (y-Properties.DELTA_SCORE_LIFE), 
        		Properties.SCORE_LIFE_BACKGROUND_WIDTH, 
        		Properties.SCORE_LIFE_BACKGROUND_HEIGHT);
        g.setColor(Color.black);
		g.setFont( FontControl.ARIAL);
		g.drawString(""+lifes, Properties.X_LIFES, y);
		updateLifes = false;
    }
	
	public void reduceLifes(){
    	lifes--;
    	updateLifes = true;
    	if (lifes<=0) {
    		gameOver = true;
		}else{
			resetPos();
			shieldEfect();
		}
    	
    }
	
	public void paintGameOver(Graphics g, int y){
    	g.setColor(Color.darkGray);
        g.fillRect(Properties.X_INIT_INFO, (int) (y-Properties.DELTA_SCORE_LIFE), 
        		Properties.SCORE_LIFE_BACKGROUND_WIDTH, 
        		Properties.SCORE_LIFE_BACKGROUND_HEIGHT);
        g.setColor(Color.red);
		g.setFont( FontControl.ARIAL);
		g.drawString("GAME OVER", Properties.X_INIT_INFO, y);
		isActive = false;
		stageControl.getgC().resultStage(2);
	}
	
    public void resetPlayer(){
    	resetPos();
    	setUpdateLifes(true);
		setUpdateScore(true);
		setVel(0);
		setDir(1);
		setBulletsInProgres(0);
		setActive(true);
    }
	
	public void resetPos(){
		if (player==1) {
			setInitPos(Properties.POS_INIT_PLAYER1[0], Properties.POS_INIT_PLAYER1[1]);
		}else{
			setInitPos(Properties.POS_INIT_PLAYER2[0], Properties.POS_INIT_PLAYER2[1]);
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
			//TODO
		}else{
			//TODO
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

	public boolean isUpdateLifes() {
		return updateLifes;
	}

	public void setUpdateLifes(boolean updateLifes) {
		this.updateLifes = updateLifes;
	}

	public boolean isUpdateScore() {
		return updateScore;
	}

	public void setUpdateScore(boolean updateScore) {
		this.updateScore = updateScore;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
		
}
