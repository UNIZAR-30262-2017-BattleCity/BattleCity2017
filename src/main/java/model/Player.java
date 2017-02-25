package model;

public class Player extends Tank {

	private int lifes;
	private int score = 0;
	
	//escudo
    //private int shieldStatus = 0;
    private boolean shieldActivate = false;
    private int shieldTime = 0;
	
    //items/poderes
    private int timeItem = 0;
    private int maxTimeItem = 10;
    private boolean useItem = false; //item en uso
    
    
    public Player(int lifes) {
		this.setLifes(lifes);
	}

	public void Item(){
    	useItem = true;
        if(timeItem<maxTimeItem){
            timeItem++;
        }else{
            timeItem = 1;
            useItem = !useItem;
        }
    }
    
    public void reduceLifes(){
    	setLifes(getLifes() - 1);
    }

	@Override
	public void moveControl() {
		// TODO Auto-generated method stub
		
	}

	public void addScore(int sco){
	        score = score + sco;
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

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

}
