package gameController;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import application.Properties;
import elements.Bullet;
import elements.Eagle;
import elements.Enemy;
import elements.Item;
import elements.Forest;
import elements.Player;
import elements.StageElement;
import elements.Wall;
import userInterface.MiniEnemies;

public class StageControl {	
		
	//Items
    private int nItems;
    private int nItemsSimul;
    private int cantItemsLevel;
    private int timeBetweenSpawnIt;
    private static int maxTimeItemEfect;
    private boolean clockEfect, itemTaked;
    
    //Enemies
    private int nEnemies;
    private int nEnemiesSimul;
    private int enemiesKilled;
    private int maxEnemySimul;
    private int timeBetweenSpawnE;    
    
    private Wall tmpWall;
    private Enemy tmpEnemy;
    private LinkedList<Item> items;
    private StageElement tmpElement;
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemies;
    private LinkedList<Wall> stageWalls;
    private LinkedList<Wall> eagleBricks;
    private LinkedList<Forest> stageForest;
    private LinkedList<MiniEnemies> miniEnemies;
    private LinkedList<StageElement> staticElements;
    
    private MazeControl mazeControl;
    public static Player[] players;
    private Eagle eagle;
    private int pos,col,row,typeItem,typeEnemy;
    private int timerE, timerIt;
    private Random random;
    private static final int x1 = Properties.POS1_SPAWN_ENEMY[0];
    private static final int x2 = Properties.POS2_SPAWN_ENEMY[0];
    private static final int x3 = Properties.POS3_SPAWN_ENEMY[0];
    private static final int y1 = Properties.POS1_SPAWN_ENEMY[1];
    private static final int y2 = Properties.POS2_SPAWN_ENEMY[1];
    private static final int y3 = Properties.POS3_SPAWN_ENEMY[1];
        
    private static int yMiniE; 
	private static final int x = (int) (Properties.X_INIT_INFO+(Properties.WIDTH*0.037));
	private static final int xB = x + MiniEnemies.delta;
	
	private boolean updteBricks;
	private boolean initDraw;
	private boolean updateMiniEnemies;
	
	private IAControl ia;
	private GameControl gC;
	
	private BufferedImage[] imgBackStage = new BufferedImage[2];
            
    public StageControl(GameControl gC) {
    	initValues(gC.getDifficulty());
    	this.gC= gC;
    	this.ia = gC.getIa();
    	loadLevel(gC.getLevel(),gC.isPlayer1(),gC.isPlayer2());
    }
    
    public void initValues(int dif){
    	pos = 1;    	
    	nItems= 0;
    	timerE = 0;
		timerIt = 0;		
		maxTimeItemEfect = 0;
				
		players = new Player[2];
		yMiniE = Properties.Y_INIT_INFO;
		random = new Random();
		updateMiniEnemies = true;
		initDraw = true;			
    	
    	//dif: difficulty     	// 1 easy / 2 normal  3 hard
		maxEnemySimul = Properties.MIN_ENEMY_SIMUL*dif;					
		timeBetweenSpawnE = Properties.TIME_BETWEEN_SPAWN_E - (dif*50);
				
    	cantItemsLevel = Properties.MAX_ITEMS_LEVEL - (dif*5);
    	timeBetweenSpawnIt = Properties.MIN_TIME_BETWEEN_SPAWN_IT * dif;
    	
    	items = new LinkedList<>();
    	enemies = new LinkedList<>();
    	bullets = new LinkedList<>();    	
    	stageWalls = new LinkedList<>();    	
    	miniEnemies = new LinkedList<>();
    	stageForest = new LinkedList<>();	
    	staticElements = new LinkedList<>();
    	mazeControl = new MazeControl(this);
    }
    
    
    public void resetVariables(){    	
		nEnemies = 0;
		nItemsSimul = 0;
		nEnemiesSimul = 0;
		enemiesKilled = 0;
    }
    
    
    public void loadLevel(int level, boolean isPlayer1, boolean isPlayer2){

    	switch (level) {
		case 1:			
			
			break;
		case 2:

			break;
		}


    	resetVariables();
    	
    	 mazeControl.loadMaze(level,isPlayer1,isPlayer2);
    	 eagleBricks = mazeControl.loadEagleWall(); 
    	 imgBackStage = mazeControl.loadBackground(level);
    	 loadMiniEnemies();
    	 
    }
            
    public void spawnBullets(Bullet e) {
    	bullets.add(e);
	}
    
    public void spawnStaticElements(StageElement e) {
    	staticElements.add(e);
	}
    
    public void spawnWalls(Wall w) {
    	stageWalls.add(w);
	}
    
    public void spawnForest(Forest o) {
    	stageForest.add(o);
	}
    
    public void spawnEnemys() {	
    	
		if (nEnemies<Properties.CANT_ENEMIES_LEVEL) {	
			if (nEnemiesSimul<maxEnemySimul) {
				typeEnemy = random.nextInt(3)+1;
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
		if (nItems<cantItemsLevel) {
			if (nItemsSimul<Properties.MAX_ITEMS_SIMUL) {
				col = random.nextInt(Properties.COL_STAGE)+1;
				row = random.nextInt(Properties.ROW_STAGE)+1;
				typeItem = random.nextInt(7)+1;
				items.add(new Item(col, row, typeItem, this));
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

    	if (timerE < timeBetweenSpawnE) {
    		timerE++;
    	}else{
    		timerE=0;
    		spawnEnemys();
    	}

    	for (int i = 0; i < enemies.size(); i++) {
    		tmpEnemy = enemies.get(i);	
    		if (tmpEnemy.isActive()){

    			if (clockEfect) {
    				tmpEnemy.setClockEfect(clockEfect);
    			}else{
    				tmpEnemy.setClockEfect(clockEfect);
    				int dir[] = ia.getDir_Shoot(tmpEnemy, this);
    				tmpEnemy.setDir(dir[0]);
    				if (dir[1] == 1) {
    					tmpEnemy.shoot(new Bullet(tmpEnemy.getPosX(),tmpEnemy.getPosY(),tmpEnemy.getDir(),0,this,tmpEnemy));
    				}
    			}
    			
    			tmpEnemy.updateDraw();
    		}
    		else deleteEnemy(null,tmpEnemy);

    	}

    	if (gC.isPlayer1()) players[0].updateDraw();
    	if (gC.isPlayer2()) players[1].updateDraw();
    	
    	
    	for(int i=0;i<items.size();i++) {
    		
    		if (!items.get(i).isActive()) 
    			deleteItem(items.get(i));		 
    	}
    	
    	for(int i=0;i<bullets.size();i++) {
    		tmpElement = bullets.get(i);
    		if (tmpElement.isActive()){
    			tmpElement.updateDraw();
    		}else deleteBullet(tmpElement);
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
    	
        if (initDraw) {
    		//g.setColor(Color.black);
            //g.fillRect(Properties.X_INIT_STAGE-1,Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2);
			
        	//for (StageElement s : staticElements) {
				//s.draw(g);
			//}        	
        	g.drawImage(imgBackStage[0], Properties.X_INIT_STAGE-1,Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2, null);
			initDraw = false;
		}
        ///g.setColor(Color.black);
        //g.fillRect(Properties.X_INIT_STAGE-1,Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2);
		
    	g.drawImage(imgBackStage[1], Properties.X_INIT_STAGE-1,Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2, null);
    	
    	for (Wall wall : eagleBricks) {
			wall.draw(g);
		}
    	
    	for (Wall wall : stageWalls) {
			wall.draw(g);
		}
    	
    	for (Enemy e : enemies) {
			e.draw(g);
		}
    	
    	for(int i=0;i<bullets.size();i++) {
    		tmpElement = bullets.get(i);
    		if (tmpElement.isActive()) tmpElement.draw(g);
		}
    	
    	if (gC.isPlayer1()) players[0].draw(g);
    	if (gC.isPlayer2()) players[1].draw(g);
    	
    	for (Forest obs : stageForest) {
			obs.draw(g);
		}
    	
    	for (Item item : items) {
			item.draw(g);
		}
    	
    	if (updateMiniEnemies) {
    		updateMiniEnemies(g);
		}    	
    	
    }
    
    public void deleteBullet(StageElement e){
        bullets.remove(e);
    }
    
    public void deleteWall(Wall w){
        stageWalls.remove(w);
    }
    
    public void deleteEnemy(Player p,Enemy e){
    	if (p!=null) {
			p.addScore(e.getType());
		} 	
        enemies.remove(e);
        nEnemiesSimul--;
        enemiesKilled++;
        miniEnemies.removeLast();
        updateMiniEnemies = true;
        if (enemiesKilled==Properties.CANT_ENEMIES_LEVEL) {
			gC.resultStage(1);
			
		}
    }
    
    public void deleteItem(Item it){
    	items.remove(it);
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
		if(maxTimeItemEfect<Properties.MAX_TIME_ITEM_EFECT){
			maxTimeItemEfect++;
		}else{
			maxTimeItemEfect = 0;
			clockEfect = false;
			itemTaked = false;
		}
	}

	public void ItemTaked(Player player, Item it){		
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
		deleteBullet(it);
    }
	
	public void bombEfect() {
		for (int i = 0; i < enemies.size();i++) {
			enemies.get(i).setActive(false);
		}
	}
	
	public LinkedList<StageElement> getElements() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(bullets);
		clone.addAll(eagleBricks);
		clone.addAll(enemies);
		clone.addAll(stageWalls);
		clone.addAll(staticElements);
		clone.addAll(items);
		clone.add(eagle);
		return clone;
	}

	public LinkedList<StageElement> getMaze_Enemies() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		clone.addAll(enemies);
		if (gC.isPlayer1()) clone.add(players[0]);
		if (gC.isPlayer2()) clone.add(players[1]);
		clone.addLast(eagle);
		clone.addAll(staticElements);
		clone.addAll(stageWalls);
		return clone;
	}
	
	public LinkedList<StageElement> getMaze_Players() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		if (gC.isPlayer1()) clone.add(players[0]);
		if (gC.isPlayer2()) clone.add(players[1]);
		clone.add(eagle);
		clone.addAll(staticElements);
		clone.addAll(stageWalls);
		return clone;
	}

	public void loadMiniEnemies(){
		yMiniE = (int) (Properties.Y_INIT_INFO-(Properties.HEIGHT*0.035));
    	
 		for (int i = 0; i < Properties.CANT_ENEMIES_LEVEL; i++) {
 			if (i%2==0) {
 				miniEnemies.add(new MiniEnemies(x, yMiniE));
 			}else{
 				miniEnemies.add(new MiniEnemies(xB, yMiniE));
 				yMiniE = yMiniE+ MiniEnemies.delta;
 			}
 		}
	}
	
	public void updateMiniEnemies(Graphics g){
		g.setColor(Color.darkGray);
        g.fillRect(Properties.X_INIT_INFO, 
        		Properties.Y_MINI_ENEMIES, 
        		Properties.X_FINAL_INFO, Properties.MINI_ENEMIES_BACKGROUND_HEIGHT);
	    
		for(int i=0;i<miniEnemies.size();i++) {
			miniEnemies.get(i).draw(g);
		}
        updateMiniEnemies = false;
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

	public int getMaxEnemySimul() {
		return maxEnemySimul;
	}

	public boolean isClockEfect() {
		return clockEfect;
	}

	public void setClockEfect(boolean relojEfect) {
		this.clockEfect = relojEfect;
	}	
	
	public static Player[] getPlayers() {
		return players;
	}

	public static void setPlayers(Player[] players) {
		StageControl.players = players;
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

	public Eagle getEagle() {
		return eagle;
	}

	public void setEagle(Eagle eagle) {
		this.eagle = eagle;
	}

	public GameControl getgC() {
		return gC;
	}

	public void setgC(GameControl gC) {
		this.gC = gC;
	}

	public boolean isInitDraw() {
		return initDraw;
	}

	public void setInitDraw(boolean initDraw) {
		this.initDraw = initDraw;
	}	
		
}
