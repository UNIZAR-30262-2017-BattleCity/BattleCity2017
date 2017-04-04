package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import application.Properties;
import gameController.PhysicsContol;
import gameController.StageControl;
import gameController.ImageControl;

public class Player extends Tank implements StageElement{
	
	private final BufferedImage[] imgPlayer1Up = new BufferedImage[2];
	private final BufferedImage[] imgPlayer1Dowm = new BufferedImage[2];
	private final BufferedImage[] imgPlayer1Left = new BufferedImage[2];
	private final BufferedImage[] imgPlayer1Right = new BufferedImage[2];
	private final BufferedImage[] imgPlayer2Up = new BufferedImage[2];
	private final BufferedImage[] imgPlayer2Dowm = new BufferedImage[2];
	private final BufferedImage[] imgPlayer2Left = new BufferedImage[2];
	private final BufferedImage[] imgPlayer2Right = new BufferedImage[2];
	private static Effect shieldEffect;
	private final int initGas = (int) (Properties.WIDTH*5.8738);
    private int userName;
	private int lifes;
	private int score;
	private int player;
	private boolean isGas;
	private boolean updateLifes;
	private boolean updateScore;
	private boolean updateGas;
	private boolean gameOver;
	private int lifesForScore;
	private ArrayList<Integer> enemyType;
    private boolean shieldActivate;
	private boolean freezed;
	private boolean isIce;
	private int gas;		
	
    public Player(int col, int row, int lifes, int player, StageControl stageControl) {
		super(col,row,stageControl);
    	this.lifes = lifes;
		setTypeTank(0);
		this.player = player;
		initPlayer();
		isGas = true;
		updateLifes = true;
		updateScore = true;
		updateGas = true;
		score = 0;
		tier = 1;
		gas = initGas;
		shieldActivate = true;
		shieldEffect = new Effect(posX, posY, 1,stageControl);
		velBullet = Properties.VEL_BULLET;
		timeToNext = 0;
		lifesForScore = 10000;
		this.shieldLevel = 1;
    	enemyType = new ArrayList<>();
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
    	
    	if (isBorn) {
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
        	
        	if (shieldActivate) {
        		shieldEffect.draw(g);
    		}
        	
        	if (gameOver) {
        		updateLifes = false;
        		updateScore = false;
        		if (player==1) {
    				paintGameOver(g, Properties.Y_IP_LIFES);
    			}else{
    				paintGameOver(g, Properties.Y_IIP_LIFES);
    			}
    		}        	
        	
		}else{
			birthEffect.draw(g);
		}
    	
    	if (updateLifes) {
    		if (player==1) {
				updateLifes(g, Properties.Y_IP_LIFES);
			}else{
				updateLifes(g, Properties.Y_IIP_LIFES);
			}
		}
    	
    	if (updateScore) {
    		if (player==1) {
				updateScore(g, Properties.Y_IP_SCORE);
			}else{
				updateScore(g, Properties.Y_IIP_SCORE);
			}
		}
    	
    	if (updateGas) {
    		if (player==1) {
				updateGas(g, Properties.Y_IP_GAS);
			}else{
				updateGas(g, Properties.Y_IIP_GAS);
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
    	
    	if (isBorn) {

    		anim();
    		move();
    		collision();

        	if(shieldActivate){
        		shieldEffect.setPosX(posX);
        		shieldEffect.setPosY(posY);
        		shieldEffect.updateDraw();
    			if (next(Properties.MAX_TIME_ITEM_EFECT)) {
    				shieldActivate = false;
    			}
    		}
		}else{
			birthEffect.setPosX(posX);
			birthEffect.setPosY(posY);
			birthEffect.updateDraw();
			if (next(120)) {
				isBorn = true;
			}
		}
    	
    }

	private void move() {
    	
    	if (freezed) {
    		if (next(240)) {
				freezed = false;
			}
		}else{
			if (isGas) {				
				gas -= vel;
				
				if (gas<1) {
					isGas=false;
				}else updateGas = true;
				
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
		
		if (score>=lifesForScore) {
			tankEfect();
			lifesForScore = lifesForScore +10000;
		}
		
	}
    
    public void updateScore(Graphics g, int y){
    	g.setColor(Color.darkGray);
        g.fillRect(Properties.X_SCORE, (int) (y-Properties.DELTA_SCORE_LIFE), 
        		Properties.SCORE_LIFE_BACKGROUND_WIDTH, 
        		Properties.SCORE_LIFE_BACKGROUND_HEIGHT);
        g.setColor(Color.black);
		g.setFont( Properties.ARIAL);
		g.drawString(""+score, Properties.X_SCORE, y);
		updateScore = false;
    }
    
    public void updateLifes(Graphics g, int y){
    	g.setColor(Color.darkGray);
        g.fillRect(Properties.X_LIFES, (int) (y-Properties.DELTA_SCORE_LIFE), 
        		Properties.SCORE_LIFE_BACKGROUND_WIDTH, 
        		Properties.SCORE_LIFE_BACKGROUND_HEIGHT);
        g.setColor(Color.black);
		g.setFont( Properties.ARIAL);
		g.drawString(""+lifes, Properties.X_LIFES, y);
		updateLifes = false;
    }
	
    public void updateGas(Graphics g, int y){
    	g.setColor(Color.black);
    	g.fillRect(Properties.X_INIT_INFO, y, (int) (Properties.WIDTH*.147), 
    			(int) (Properties.HEIGHT*.03451));
    	g.setColor(Color.green);
        g.fillRect(Properties.X_INIT_INFO, y, gas/Properties.UPDATE_GAS,
        		(int) (Properties.HEIGHT*.03451));
		updateGas = false;
    }
    
	public void reduceLifes(){
		if (!shieldActivate) {
			lifes--;
			updateLifes = true;
			if (lifes<=0) {
				gameOver = true;
			}else{
				resetPos();
				shieldActivate=true;
			}
		}    	
    }
	
	public void paintGameOver(Graphics g, int y){
    	g.setColor(Color.darkGray);
        g.fillRect(Properties.X_INIT_INFO, (int) (y-Properties.DELTA_SCORE_LIFE), 
        		Properties.SCORE_LIFE_BACKGROUND_WIDTH, 
        		Properties.SCORE_LIFE_BACKGROUND_HEIGHT);
        g.setColor(Color.red);
		g.setFont( Properties.ARIAL);
		g.drawString("GAME OVER", Properties.X_INIT_INFO, y);
		isActive = false;
		stageControl.getgC().resultStage(2);
	}
	
    public void resetPlayer(){
    	resetPos();
		setVel(0);
		setDir(1);
		setShoot(false);
		setActive(true);
		enemyType.clear();		
		isBorn = false;
		isGas = true;
		updateGas = true;
		updateLifes = true;
		updateScore = true;
		shieldActivate=true;
		gas += initGas/2;
		if (gas>initGas) gas = initGas;
    }
    
    public void resetPlayerGameOver(){
    	resetPlayer();
    	tier = 1;
    	gas = initGas;
    	score = 0;
    	lifes = Properties.INIT_LIFES;
    	gameOver = false;    	
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
		if (tier<4) {
			tier++;
		}
	}
	
	public void tankEfect(){
		addScore(1);
		lifes++;
		updateLifes = true;
	}
	
	public void gasEfect(){
		addScore(1);
		gas += initGas/2;
		if (gas>initGas) gas = initGas;
	}
	
	public void shieldEfect() {
		addScore(1);
		shieldActivate = true;
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

	public ArrayList<Integer> getEnemyType() {
		return enemyType;
	}

	public void setEnemyType(ArrayList<Integer> enemyType) {
		this.enemyType = enemyType;
	}

	public boolean isFreezed() {
		return freezed;
	}

	public void setFreezed(boolean freezed) {
		this.freezed = freezed;
	}

	public boolean isUpdateGas() {
		return updateGas;
	}

	public void setUpdateGas(boolean updateGas) {
		this.updateGas = updateGas;
	}

	public boolean isIce() {
		return isIce;
	}

	public void setIce(boolean isIce) {
		this.isIce = isIce;
	}

}
