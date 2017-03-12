package elements;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import application.Properties;
import gameController.SpriteSheetControl;

public class Stage {	
		
	//items/poderes
	private int maxItems; 
	private int maxItemsSimul;  
    private int nItems;
    private int nItemsSimul;	
    private int timeItem;
    private int maxTimeItem;
    private int timeBetweenSpawnIt;
    
    private LinkedList<StageElement> elements;
    private StageElement tmpElement;
    
    private int cantEnemiesForLevel;
    private int maxEnemySimul;
    private int nEnemies;
    private int nEnemiesSimul;
    private int timeBetweenSpawnE;
    	
    private boolean clockEfect;
    
    private Maze maze;
    private SpriteSheetControl ssc;
    private int k,pos,x1,x2,x3, y1,y2,y3;
    private long timerE, timerIt;
    private Random r;
            
    public Stage(int level, int dif, SpriteSheetControl ssc) {
    	initValues();
    	this.ssc = ssc;
    	elements = new LinkedList<>();		
    	maze = new Maze(this, ssc);
    	loadLevel(level, dif);
    }
    
    public void initValues(){
    	k = 1;
    	pos = 1;
    	nItems= 0;
		nEnemies = 0;
		timeItem = 0;
		nItemsSimul = 0;
		nEnemiesSimul = 0;
		r = new Random();
		timerE = System.currentTimeMillis();
		timerIt = timerE;
		x1 = Properties.POS1_SPAWN_ENEMY[0];
		x2 = Properties.POS2_SPAWN_ENEMY[0];
		x3 = Properties.POS3_SPAWN_ENEMY[0];
		y1 = Properties.POS1_SPAWN_ENEMY[1];
		y2 = Properties.POS2_SPAWN_ENEMY[1];
		y3 = Properties.POS3_SPAWN_ENEMY[1];
		maxItems = Properties.MAX_ITEMS_LEVEL;
		maxTimeItem = Properties.MAX_TIME_ITEM;		
		maxItemsSimul = Properties.MAX_ITEMS_SIMUL;
    }
    
    public void loadLevel(int level, int dif){
    	//dif: difficulty
    	switch (level) {
		case 1:			
			maxEnemySimul = Properties.MAX_ENEMY_SIMUL + dif;
			cantEnemiesForLevel = Properties.CANT_ENEMIES_LEVEL_123;						
			timeBetweenSpawnE = Properties.TIME_BETWEEN_SPAWN_E - (dif*1000);
			
			timeBetweenSpawnIt = Properties.TIME_BETWEEN_SPAWN_IT - dif;
			break;
		case 2:
			maxEnemySimul = Properties.MAX_ENEMY_SIMUL + k + dif;
			cantEnemiesForLevel = Properties.CANT_ENEMIES_LEVEL_123;
			timeBetweenSpawnE = Properties.TIME_BETWEEN_SPAWN_E;
			
			timeBetweenSpawnIt = Properties.TIME_BETWEEN_SPAWN_IT - dif;
			break;
		}
    	 maze.loadMaze(level);
    	
    }
            
    public void spawnElements(StageElement e) {
    	elements.add(e);
	}
    
    public void spawnEnemys() {	
		if (nEnemies<cantEnemiesForLevel) {			
			if (nEnemiesSimul<maxEnemySimul) {
				if (pos == 1) elements.add(new Enemy(x1, y1, 1, ssc));
				if (pos == 2) elements.add(new Enemy(x2, y2, 1, ssc));
				if (pos == 3) elements.add(new Enemy(x3, y3, 1, ssc));
				if (pos == 3) pos = 0;
				pos++;
				nEnemies++;
				nEnemiesSimul++;
			}
		}
	}
    
    public void spawnItems() {
		if (nItems<maxItems) {
			if (nItemsSimul<maxItemsSimul) {
				int col = r.nextInt(Properties.COL_STAGE)+1;
				int row = r.nextInt(Properties.ROW_STAGE)+1;
				int id = r.nextInt(7)+1;
				elements.add(new Item(col, row, id, ssc));
				nItemsSimul++;
				nItems++;
			}
		}		
	}

    public void updateDraw(){
    	    	
    	if (System.currentTimeMillis() - timerE > timeBetweenSpawnE) {
			timerE += timeBetweenSpawnE;
			spawnEnemys();
		}
    	
    	if (System.currentTimeMillis() - timerIt > timeBetweenSpawnIt) {
			timerIt += timeBetweenSpawnIt;
			spawnItems();
		}   	
    	
    	if (clockEfect) {
    		clockEfect();
    	}else{
    		for(int i=0;i<elements.size();i++) {
    			tmpElement = elements.get(i);	
    			if (tmpElement.isActive()) tmpElement.updateDraw();
    			else deleteElement(tmpElement);
    		}
    	}

    }
    
    public void draw(Graphics g){
    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);
    		if (tmpElement.isActive()) tmpElement.draw(g);
			else deleteElement(tmpElement);
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
   

	public void eagleIronWallEfect() {
		maze.loadIronWall();
		
	}
	
	public void clockEfect() {
		if(timeItem<maxTimeItem){
			timeItem++;
		}else{
			timeItem = 0;
			clockEfect = false;
		}
		
	}
	
	public void bombEfect() {
		for (int i = 0; i < elements.size();i++) {
			if (elements.get(i).getClass().equals(Enemy.class)) {
				deleteElement(elements.get(i));
			}	
		}	
	}
	
	public LinkedList<StageElement> getElements() {
		return elements;
	}

	public void setElements(LinkedList<StageElement> elements) {
		this.elements = elements;
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

	public boolean isClockEfect() {
		return clockEfect;
	}

	public void setClockEfect(boolean relojEfect) {
		this.clockEfect = relojEfect;
	}
	
			
}
