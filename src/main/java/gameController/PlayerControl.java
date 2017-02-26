package gameController;

public class PlayerControl extends TankControl{

	private int userName;
	private int lifes;
	private int score;		
    
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
    private int shieldTime = 0;
	
    public PlayerControl(int lifes) {
		this.setLifes(lifes);
		super.setTypeTank(0);
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
