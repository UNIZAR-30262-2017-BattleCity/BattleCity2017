package elements;

import java.awt.Graphics;
import java.util.LinkedList;

import application.Properties;

public class Stage {	
		
	//items/poderes
	private int maxItems;    
    private int nItems;
    private int maxItemsSimul;
    private int nItemsSimul;	
    private int timeItem;
    private int maxTimeItem;
    
    private LinkedList<StageElement> elements;
    private StageElement tmpElement;
    
    private int cantEnemies;
    private int maxEnemySimul;
    private int nEnemies;
    private int nEnemiesSimul;
    private int timeBetweenBirth;
	
    private boolean relojEfect;
            
    public Stage(int level) {		
		level(level);
		elements = new LinkedList<>();
		nEnemies = 0;
		timeItem = 0;
		maxTimeItem = Properties.MAX_TIME_ITEM;
		nEnemiesSimul = 0;
		nItems= 0;
	    maxItemsSimul = 4;
	    maxItems=10;
	}
    
    public void level(int level){
    	switch (level) {
		case 1:			
			maxEnemySimul = Properties.MAX_ENEMY_SIMUL;
			cantEnemies = Properties.CANT_ENEMIES_LEVEL;						
			timeBetweenBirth = Properties.TIME_BETWEEN_BIRTH;
			
			maxItems = Properties.MAX_ITEMS_LEVEL;
			maxItemsSimul = Properties.MAX_ITEMS_SIMUL;			
			break;
		case 2:			
			maxEnemySimul = Properties.MAX_ENEMY_SIMUL;
			cantEnemies = Properties.CANT_ENEMIES_LEVEL;
			timeBetweenBirth = Properties.TIME_BETWEEN_BIRTH;
			
			maxItems = Properties.MAX_ITEMS_LEVEL;
			maxItemsSimul = Properties.MAX_ITEMS_SIMUL;
			break;
		}
    }
    
    public void spawnElements(StageElement e) {
				
	}
    
    public void spawnEnemys(Enemy e) {
		if (nEnemies<cantEnemies) {
			nEnemies++;
			if (nEnemiesSimul<maxEnemySimul) {
				elements.add(e);
				nEnemiesSimul++;
			}
		}		
	}    
    
    public void spawnItems(Item it) {
		if (nItems<maxItems) {
			nItems++;
			if (nItemsSimul<maxItemsSimul) {
				elements.add(it);
				nItemsSimul++;
			}
		}		
	}

    public void updateDraw(){
    	
    	if (relojEfect) {
    		if(timeItem<maxTimeItem){
				timeItem++;
			}else{
				timeItem = 0;
				relojEfect = false;
			}
    	}else{
    		for(int i=0;i<elements.size();i++) {
    			tmpElement = elements.get(i);
    			tmpElement.updateDraw();

    		}
    	}

    }
    
    public void draw(Graphics g){
    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);
    		tmpElement.draw(g);
    	}
    }
    
    public void deleteElement(StageElement e){
        elements.remove(e);
    }
    
    public void deleteEnemy(Enemy e){
        elements.remove(e);
        nEnemiesSimul--;
    }
    
    public void deleteItem(Item it){
    	elements.remove(it);
    	nItemsSimul--;
    }
   

	public void eagleWallEfect() {
		// TODO Auto-generated method stub
		
	}
	
	public void relojEfect() {
		relojEfect = true;	
		
	}
	
	public void bombEfect() {
		for (int i = 0; i < elements.size();i++) {
			if (elements.get(i).getClass().equals(Enemy.class)) {
				deleteElement(elements.get(i));
			}	
		}	
	}
	
	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	public int getMaxEnemySimul() {
		return maxEnemySimul;
	}

	public void setMaxEnemySimul(int maxEnemySimul) {
		this.maxEnemySimul = maxEnemySimul;
	}

	public int getTimeBetweenBirth() {
		return timeBetweenBirth;
	}

	public void setTimeBetweenBirth(int timeBetweenBirth) {
		this.timeBetweenBirth = timeBetweenBirth;
	}

	public boolean isRelojEfect() {
		return relojEfect;
	}

	public void setRelojEfect(boolean relojEfect) {
		this.relojEfect = relojEfect;
	}
	
			
}
