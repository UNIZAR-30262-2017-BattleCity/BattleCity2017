package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import application.Properties;
import gameController.IAControl;

public class Stage {	
		
	//items
    private int nItems;
    private int nItemsSimul;
    private int timeBetweenSpawnIt;
    private static final int maxItems = Properties.MAX_ITEMS_LEVEL;
    private static final int maxItemsSimul = Properties.MAX_ITEMS_SIMUL;
    private static int maxTimeItemEfect;
    
    private LinkedList<StageElement> elements;
    private LinkedList<Enemy> enemies;
    private StageElement tmpElement;
    private Enemy tmpEnemy;
    
    private static int cantEnemiesForLevel;
    private static int maxEnemySimul;
    private static int nEnemies;
    private static int nEnemiesSimul;
    private static int timeBetweenSpawnE;
    	
    private boolean clockEfect, itemTaked;
    
    private Maze maze;
    private Player player;
    private int k,pos;
    private int timerE, timerIt;
    private Random r;    
    private static final int x1 = Properties.POS1_SPAWN_ENEMY[0];
    private static final int x2 = Properties.POS2_SPAWN_ENEMY[0];
    private static final int x3 = Properties.POS3_SPAWN_ENEMY[0];
    private static final int y1 = Properties.POS1_SPAWN_ENEMY[1];
    private static final int y2 = Properties.POS2_SPAWN_ENEMY[1];
    private static final int y3 = Properties.POS3_SPAWN_ENEMY[1];
    
    private IAControl ia;
            
    public Stage(int level, int dif, IAControl ia) {
    	initValues();
    	this.ia = ia;
    	elements = new LinkedList<>();
    	enemies = new LinkedList<>();
    	maze = new Maze(this);
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
    	 
    	 player = new Player(Properties.POS_INIT_PLAYER[0], Properties.POS_INIT_PLAYER[1], Properties.INIT_LIVES, this);
    	 maze.loadMaze(level);
    }
            
    public void spawnElements(StageElement e) {
    	elements.add(e);
	}
    
    public void spawnEnemys() {	
		if (nEnemies<cantEnemiesForLevel) {			
			if (nEnemiesSimul<maxEnemySimul) {
				if (pos == 1) enemies.add(new Enemy(x1, y1, 1, this));
				if (pos == 2) enemies.add(new Enemy(x2, y2, 1, this));
				if (pos == 3) enemies.add(new Enemy(x3, y3, 1, this));
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
				elements.add(new Item(col, row, id, this));
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
    		
    		for (int i = 0; i < enemies.size(); i++) {
    			tmpEnemy = enemies.get(i);	
        		if (tmpEnemy.isActive()){
        			//int dir[] = ia.getDir_Shoot(tmpEnemy.getPosX(),tmpEnemy.getPosY(), this);
    				//tmpEnemy.setDir(dir[0]);
    				//tmpEnemy.setShoot(dir[1]);
        			tmpEnemy.updateDraw();
        		}
        		else enemies.remove(tmpEnemy);
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
        g.fillRect(Properties.X_INIT_STAGE-1, Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2);
		    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);
    		if (tmpElement.isActive() || (tmpElement.getClass().equals(Eagle.class))) tmpElement.draw(g);
			
    	}
    	
    	for (Enemy e : enemies) {		
			e.draw(g);
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
		for (int i = 0; i < enemies.size();i++) {
			if (enemies.get(i).getClass().equals(Enemy.class)) {
				enemies.get(i).setActive(false);
				nEnemiesSimul--;
			}
		}	
	}
	
	public LinkedList<StageElement> getElements(StageElement el) {
		LinkedList<StageElement> clone = new LinkedList<>();
		for (StageElement sE : elements) {
			if (!sE.equals(el)) {
				clone.add(sE);
			}
		}
		for (Enemy e : enemies) {
			if (!e.equals(el))
				clone.add(e);
		}
		return clone;
	}

	public void setElements(LinkedList<StageElement> elements) {
		this.elements = elements;
	}

	public int getMaxEnemySimul() {
		return maxEnemySimul;
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
