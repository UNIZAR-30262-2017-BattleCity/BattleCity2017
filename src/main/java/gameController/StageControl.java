package gameController;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import application.Properties;
import elements.Bullet;
import elements.Eagle;
import elements.Enemy;
import elements.Item;
import elements.Player;
import elements.StageElement;
import elements.Wall;
import userInterface.MiniEnemies;

public class StageControl {	
		
	//items
    private int nItems;
    private int nItemsSimul;
    private int timeBetweenSpawnIt;
    private static final int maxItemsSimul = Properties.MAX_ITEMS_SIMUL;
    private static int maxTimeItemEfect;
    
    private LinkedList<StageElement> elements;
    private LinkedList<Enemy> enemies;
    private LinkedList<Wall> eagleBricks;
    private LinkedList<Wall> stageWalls;
    private StageElement tmpElement;
    private Enemy tmpEnemy;
    private Wall tmpWall;
    
    private static int enemiesKilled;
    private static int maxEnemySimul;
    private static int nEnemies;
    private static int nEnemiesSimul;
    private static int timeBetweenSpawnE;
    	
    private boolean clockEfect, itemTaked;
    
    private MazeControl mazeControl;
    private Player player;
    private int k,pos,col,row,typeItem,typeEnemy;
    private int timerE, timerIt;
    private Random r;    
    private static final int x1 = Properties.POS1_SPAWN_ENEMY[0];
    private static final int x2 = Properties.POS2_SPAWN_ENEMY[0];
    private static final int x3 = Properties.POS3_SPAWN_ENEMY[0];
    private static final int y1 = Properties.POS1_SPAWN_ENEMY[1];
    private static final int y2 = Properties.POS2_SPAWN_ENEMY[1];
    private static final int y3 = Properties.POS3_SPAWN_ENEMY[1];
    
    
    private static int yMiniE; 
	private static final int x = Properties.X_INIT_INFO+25;
	private static final int xB = x + MiniEnemies.delta;
	private LinkedList<MiniEnemies> miniEnemies;
	
    private IAControl ia;
	private boolean updateEnemies;
	private boolean updteBricks;
	
	private GameControl gC;
            
    public StageControl(GameControl gC) {
    	initValues();
    	this.gC= gC;
    	this.ia = gC.getIa();
    	elements = new LinkedList<>();
    	stageWalls = new LinkedList<>();
    	enemies = new LinkedList<>();
    	miniEnemies = new LinkedList<>();
    	mazeControl = new MazeControl(this);
    	loadLevel(gC.getLevel(), gC.getDifficulty());
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
		enemiesKilled = 0;
		yMiniE = Properties.Y_INIT_INFO;
		r = new Random();
		updateEnemies = true;
		maxTimeItemEfect  = Properties.MAX_TIME_ITEM_EFECT;
    }
    
    public void loadLevel(int level, int dif){
    	//dif: difficulty
    	switch (level) {
		case 1:			
			maxEnemySimul = Properties.MAX_ENEMY_SIMUL + dif;					
			timeBetweenSpawnE = Properties.TIME_BETWEEN_SPAWN_E - (dif*1000);
			
			timeBetweenSpawnIt = Properties.TIME_BETWEEN_SPAWN_IT - dif;
			break;
		case 2:
			maxEnemySimul = Properties.MAX_ENEMY_SIMUL + k + dif;
			timeBetweenSpawnE = Properties.TIME_BETWEEN_SPAWN_E;
			
			timeBetweenSpawnIt = Properties.TIME_BETWEEN_SPAWN_IT - dif;
			break;
		}
    	 
    	 player = new Player(Properties.POS_INIT_PLAYER[0], Properties.POS_INIT_PLAYER[1], Properties.INIT_LIVES, this);
    	 mazeControl.loadMaze(level);
    	 eagleBricks = mazeControl.loadEagleWall();    	 
    	 elements.add(player);
    	 
    	yMiniE = Properties.Y_INIT_INFO-20;
    	
 		for (int i = 0; i < Properties.CANT_ENEMIES_LEVEL; i++) {
 			if (i%2==0) {
 				miniEnemies.add(new MiniEnemies(x, yMiniE));
 			}else{
 				miniEnemies.add(new MiniEnemies(xB, yMiniE));
 				yMiniE = yMiniE+ MiniEnemies.delta;
 			}
 		}
    	 
    }
            
    public void spawnElements(StageElement e) {
    	elements.add(e);
	}
    
    public void spawnWalls(Wall w) {
    	stageWalls.add(w);
	}
    
    public void spawnEnemys() {	
		if (nEnemies<Properties.CANT_ENEMIES_LEVEL) {	
			if (nEnemiesSimul<maxEnemySimul) {
				typeEnemy = r.nextInt(3)+1;
				if (pos == 1) enemies.add(new Enemy(x1, y1, 4, this));
				if (pos == 2) enemies.add(new Enemy(x2, y2, typeEnemy, this));
				if (pos == 3) enemies.add(new Enemy(x3, y3, typeEnemy, this));
				if (pos == 3) pos = 0;
				pos++;
				nEnemies++;
				nEnemiesSimul++;
			}
		}
	}
    
    public void spawnItems() {
		if (nItems<Properties.MAX_ITEMS_LEVEL) {
			if (nItemsSimul<maxItemsSimul) {
				col = r.nextInt(Properties.COL_STAGE)+1;
				row = r.nextInt(Properties.ROW_STAGE)+1;
				typeItem = r.nextInt(7)+1;
				elements.add(new Item(col, row, typeItem, this));
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
    				int dir[] = ia.getDir_Shoot(tmpEnemy, this);
    				tmpEnemy.setDir(dir[0]);
    				if (dir[1] == 1) {
    					tmpEnemy.shoot(new Bullet(tmpEnemy.getPosX(),tmpEnemy.getPosY(),tmpEnemy.getDir(),0,this,tmpEnemy));
    				}
    				tmpEnemy.shoot(new Bullet(tmpEnemy.getPosX(),tmpEnemy.getPosY(),tmpEnemy.getDir(),0,this,tmpEnemy));
    				tmpEnemy.updateDraw();
    			}
    			else deleteEnemy(tmpEnemy);

    		}  		
    	}
    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);
    		if (tmpElement.isActive()){
    			tmpElement.updateDraw();
    		}else deleteElement(tmpElement);
    	}
    	
    	if (updteBricks) {
    		for (int i = 0; i < eagleBricks.size(); i++) {
    			tmpWall = eagleBricks.get(i);
    			if (!tmpWall.isActive()) eagleBricks.remove(tmpWall);
    		}
    		for (int i = 0; i < stageWalls.size(); i++) {
    			tmpWall = stageWalls.get(i);
    			if (!tmpWall.isActive()) stageWalls.remove(tmpWall);
    		}
    		updteBricks = false;
    	}
    	
    }
    
    public void draw(Graphics g){    	
    	
    	g.setColor(Color.black);
        g.fillRect(Properties.X_INIT_STAGE-1, Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2);
	    	
    	    	
    	for (Wall wall : stageWalls) {
			wall.draw(g);
		}
    	
    	for (Wall wall : eagleBricks) {
			wall.draw(g);
		}
    	
    	for (Enemy e : enemies) {
			e.draw(g);
		}
    	
    	for(int i=0;i<elements.size();i++) {
    		tmpElement = elements.get(i);
    		if (tmpElement.isActive() || (tmpElement.getClass().equals(Eagle.class))) tmpElement.draw(g);
		}
    	
    	if (updateEnemies) {
    		g.setColor(Color.darkGray);
            g.fillRect(Properties.X_INIT_INFO, Properties.Y_INIT_INFO-20, Properties.X_FINAL_INFO, 200);
    	    
    		for(int i=0;i<miniEnemies.size();i++) {
    			miniEnemies.get(i).draw(g);
    		}
	        updateEnemies = false;
		}    	
    	
    }
    
    public void deleteElement(StageElement e){
        elements.remove(e);
    }
    
    public void deleteEnemy(Enemy e){
    	player.addScore(e.getType());
        enemies.remove(e);
        nEnemiesSimul--;
        enemiesKilled++;
        miniEnemies.removeLast();
        updateEnemies = true;
        if (enemiesKilled==Properties.CANT_ENEMIES_LEVEL) {
			gC.resultStage(2);
		}
    }
    
    public void deleteItem(Item it){
    	elements.remove(it);
    	nItemsSimul--;
    	nItems--;
    }
   
	public void eagleIronWallEfect(boolean efect) {
		if (efect) {
			mazeControl.loadIronWall();
			for (Wall wall : eagleBricks) {
				wall.setType(2);
				wall.setEagleBrick(false);
			}		
		}else{
			for (Wall wall : eagleBricks) {
				wall.setType(1);
				wall.setEagleBrick(true);
			}
		}
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
		switch (it.getType()) {
		case 1://shield
			player.setItemTaked(true);
			player.shieldEfect();	
			break;
		case 2://clock
			itemTaked = true;
			setClockEfect(true);
			break;
		case 3://shovel
			eagleIronWallEfect(true);
			break;
		case 4://star
			player.starEfect();
			break;
		case 5://bomb
			bombEfect();
			break;
		case 6://tank
			player.tankEfect();
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
			enemies.get(i).setActive(false);
		}
	}
	
	public LinkedList<StageElement> getElements() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(elements);
		clone.addAll(enemies);
		clone.addAll(stageWalls);
		return clone;
	}
	
	public LinkedList<StageElement> getMaze() {
		return elements;
	}
	
	public LinkedList<StageElement> getMaze_Enemies() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		clone.addAll(enemies);
		clone.add(player);
		//TODO add player 2
		//TODO add eagle create spawn obstacles.
		clone.addAll(stageWalls);
		return clone;
	}
	
	public LinkedList<StageElement> getMaze_Players() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		clone.add(player);
		//TODO add player 2
		clone.addAll(stageWalls);
		return clone;
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public LinkedList<Wall> getEagleBricks() {
		return eagleBricks;
	}

	public LinkedList<Wall> getStageWalls() {
		return stageWalls;
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

	public boolean isUpdteBricks() {
		return updteBricks;
	}

	public void setUpdteBricks(boolean updteBricks) {
		this.updteBricks = updteBricks;
	}
			
}
