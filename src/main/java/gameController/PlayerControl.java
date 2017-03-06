package gameController;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlayerControl extends TankControl{

	private int userName;
	private int lifes;
	private int score;
	private BufferedImage img;
    
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
    private int shieldTime = 0;
	
    public PlayerControl(int x, int y, int lifes, SpriteSheetControl ssc) {
		this.setLifes(lifes);
		super.setTypeTank(0);
		super.setPosX(x);
		super.setPosY(y);
		img = ssc.getSprite(1, 1, 16, 16);
	}
    
    public void draw(Graphics g) {
		g.drawImage(img,getPosX(), getPosY(), null);

	}
    
    public void updateDraw(){    	
    	
    }
    
    public void addScore(int sco){
		score = score + sco;
	}
	
	public void reduceLifes(){
    	lifes = lifes - 1;
    }

	public void shieldControl() {	
		if(shieldActivate){
			if(0<shieldTime){
				shieldTime--;
			}else{
				shieldActivate = false;
			}
		}
	}

	public void efectItemShield(int time) {
		shieldActivate = true;
		shieldTime = time;
	}

	@Override
	public void moveControl() {
		// TODO Auto-generated method stub
		
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

	
}
