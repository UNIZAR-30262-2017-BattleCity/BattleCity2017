package elements;

public class StageControl {
	
		
	//items/poderes
	private int maxItems;
    private int timeItem = 0;
    private int maxTimeItem = 10;
    private boolean useItem = false; //item en uso
    
    private int maxEnemySimul = 20;
    private int maxEnemysForLevel = 100;
    private int timeBetweenBirth;
    
    
	
	public void Item(){
    	useItem = true;
        if(timeItem<maxTimeItem){
            timeItem++;
        }else{
            timeItem = 1;
            useItem = false;
        }
    }
	
	public boolean isUseItem() {
		return useItem;
	}

	public void setUseItem(boolean useItem) {
		this.useItem = useItem;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	public int getTimeItem() {
		return timeItem;
	}

	public void setTimeItem(int timeItem) {
		this.timeItem = timeItem;
	}

	public int getMaxTimeItem() {
		return maxTimeItem;
	}

	public void setMaxTimeItem(int maxTimeItem) {
		this.maxTimeItem = maxTimeItem;
	}

	public int getMaxEnemySimul() {
		return maxEnemySimul;
	}

	public void setMaxEnemySimul(int maxEnemySimul) {
		this.maxEnemySimul = maxEnemySimul;
	}

	public int getMaxEnemysForLevel() {
		return maxEnemysForLevel;
	}

	public void setMaxEnemysForLevel(int maxEnemysForLevel) {
		this.maxEnemysForLevel = maxEnemysForLevel;
	}

	public int getTimeBetweenBirth() {
		return timeBetweenBirth;
	}

	public void setTimeBetweenBirth(int timeBetweenBirth) {
		this.timeBetweenBirth = timeBetweenBirth;
	}
			
}
