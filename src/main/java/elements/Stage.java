package elements;

import java.awt.Color;
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
    private int maxTimeItemEfect;
    private int timeBetweenSpawnIt;
    
    private LinkedList<StageElement> elements;
    private StageElement tmpElement;
    
    private int cantEnemiesForLevel;
    private int maxEnemySimul;
    private int nEnemies;
    private int nEnemiesSimul;
    private int timeBetweenSpawnE;
    	
    private boolean clockEfect, itemTaked;
    
    private Maze maze;
    private Player player;
    private SpriteSheetControl ssc;
    private int k,pos,x1,x2,x3, y1,y2,y3;
    private int timerE, timerIt;
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
    	timerE = 0;
		timerIt = 0;
		nEnemies = 0;
		nItemsSimul = 0;
		nEnemiesSimul = 0;
		r = new Random();		
		x1 = Properties.POS1_SPAWN_ENEMY[0];
		x2 = Properties.POS2_SPAWN_ENEMY[0];
		x3 = Properties.POS3_SPAWN_ENEMY[0];
		y1 = Properties.POS1_SPAWN_ENEMY[1];
		y2 = Properties.POS2_SPAWN_ENEMY[1];
		y3 = Properties.POS3_SPAWN_ENEMY[1];
		maxItems = Properties.MAX_ITEMS_LEVEL;
		maxItemsSimul = Properties.MAX_ITEMS_SIMUL;
		maxTimeItemEfect  = Properties.MAX_TIME_ITEM_EFECT;
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
    	 
    	 player = new Player(Properties.POS_INIT_PLAYER[0], Properties.POS_INIT_PLAYER[1], Properties.INIT_LIVES, this, ssc);
    	 maze.loadMaze(level);
    }
            
    public void spawnElements(StageElement e) {
    	elements.add(e);
	}
    
    public void spawnEnemys() {	
		if (nEnemies<cantEnemiesForLevel) {			
			if (nEnemiesSimul<maxEnemySimul) {
				if (pos == 1) elements.add(new Enemy(x1, y1, 1, this,ssc));
				if (pos == 2) elements.add(new Enemy(x2, y2, 1, this,ssc));
				if (pos == 3) elements.add(new Enemy(x3, y3, 1, this,ssc));
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
				elements.add(new Item(col, row, id, this,ssc));
				nItemsSimul++;
				nItems++;
			}
		}		
	}

    public void updateDraw(){

    	if(timerIt<timeBetweenSpawnIt){
			timerIt++;
		}else{
			timerIt = 0;
			spawnItems();
		}
    	
    	if (itemTaked) {
    		item();
    	}

    	if (!clockEfect) {
    		
    		if (timerE < timeBetweenSpawnE) {
    			timerE++;
    		}else{
    			timerE=0;
    			spawnEnemys();
    		}    		
    		for (StageElement stageElement : elements) {
    			if (stageElement.getClass().equals(Enemy.class)) {
					stageElement.updateDraw();
				}
			}
    	}
    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);	
    		if (tmpElement.isActive() || (tmpElement.getClass().equals(Eagle.class))){
    			if(!tmpElement.getClass().equals(Enemy.class)) tmpElement.updateDraw();
    		}else deleteElement(tmpElement);
    	}

    }
    
    public void draw(Graphics g){
    	
    	g.setColor(Color.black);
        g.fillRect(Properties.X_INIT_STAGE, Properties.Y_INIT_STAGE, Properties.WIDTH_STAGE, Properties.HEIGHT_STAGE);
		    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);
    		if (tmpElement.isActive() || (tmpElement.getClass().equals(Eagle.class))) tmpElement.draw(g);
			
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
    	nItems--;
    }
   
	public void eagleIronWallEfect() {
		maze.loadIronWall();
		
	}
		
	public void item(){		
		if(maxTimeItemEfect>0){
			maxTimeItemEfect--;
		}else{
			maxTimeItemEfect = Properties.MAX_TIME_ITEM_EFECT;
			clockEfect = false;
			itemTaked = false;
		}
	}

	public void ItemTaked(Item it){		
		nItemsSimul--;
		switch (it.getId()) {
		case 1://shield
			player.setItemTaked(true);
			player.shieldEfect();		
			break;
		case 2://clock
			itemTaked = true;
			setClockEfect(true);
			break;
		case 3://shovel
			eagleIronWallEfect();
			break;
		case 4://star
			player.setItemTaked(true);
			player.starEfect(true);
			break;
		case 5://bomb
			bombEfect();
			break;
		case 6://tank
			player.setLifes(player.getLifes()+1);
			break;
		case 7://gun
			player.setItemTaked(true);
			player.setGunEfectActivate(true);
			player.gunEfect();
			break;
		}
		deleteElement(it);
    }
	
	public void bombEfect() {
		for (int i = 0; i < elements.size();i++) {
			if (elements.get(i).getClass().equals(Enemy.class)) {
				elements.get(i).setActive(false);
				nEnemiesSimul--;
			}
		}	
	}
	
	public LinkedList<StageElement> getElements(StageElement e) {
		LinkedList<StageElement> clone = new LinkedList<>();
		for (StageElement sE : elements) {
			if (!sE.equals(e)) {
				clone.add(sE);
			}
		}
		return clone;
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isItemTaked() {
		return itemTaked;
	}

	public void setItemTaked(boolean itemTaked) {
		this.itemTaked = itemTaked;
	}
			
}
