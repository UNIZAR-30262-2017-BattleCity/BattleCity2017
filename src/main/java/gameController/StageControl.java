package gameController;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import application.Properties;
import elements.Bullet;
import elements.Eagle;
import elements.Effect;
import elements.Enemy;
import elements.Item;
import elements.Forest;
import elements.Ice;
import elements.Player;
import elements.StageElement;
import elements.Wall;
import elements.Water;
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
    private LinkedList<Effect> stageEffects;
    private LinkedList<MiniEnemies> miniEnemies;
    private LinkedList<Water> stageWater;
    private LinkedList<Ice> stageIce;
    
    private MazeControl mazeControl;
    public static Player[] players;
    private Eagle eagle;
    private int pos,col,row,typeItem, typeEnemy;
    private int timerE, timerIt;
    private Random random;
    private static final int ROW_SPAWN_ENEMY = 1;
    private static final int[] COL_SPAWN_ENEMY = {1,7,13};
        
    private static int yMiniE; 
	private static final int x = (int) (Properties.X_INIT_INFO+(Properties.WIDTH*0.037));
	private static final int xB = x + MiniEnemies.delta;
	
	private boolean updteBricks;
	private boolean initDraw;
	private boolean rePaintStageGUI;
	private boolean updateMiniEnemies;
	
	private IAControl ia;
	private GameControl gC;
	
	private BufferedImage[] imgBackStage = new BufferedImage[2];
	private boolean eagleWallEfect;
	private int nEnemies1, nEnemies2, nEnemies3, nEnemies4;
	private int cantEnemies1, cantEnemies2, cantEnemies3, cantEnemies4;
	private int sizeMazeItemsCol,sizeMazeItemsRow;
            
    public StageControl(GameControl gC) {
    	initValues(gC.getDifficulty());
    	this.gC= gC;
    	this.ia = gC.getIa();
    }
    
    public void initValues(int dif){
				
		players = new Player[2];
		yMiniE = Properties.Y_INIT_INFO;
		random = new Random();			
    	
    	//dif: difficulty     	// 1 easy / 2 normal  3 hard
		maxEnemySimul = Properties.MIN_ENEMY_SIMUL*dif;					
		timeBetweenSpawnE = Properties.TIME_BETWEEN_SPAWN_E - (dif*50);
				
    	cantItemsLevel = Properties.MAX_ITEMS_LEVEL - (dif*5);
    	timeBetweenSpawnIt = Properties.MIN_TIME_BETWEEN_SPAWN_IT * dif;
    	    	
    	items = new LinkedList<>();
    	stageIce = new LinkedList<>();
    	enemies = new LinkedList<>();
    	bullets = new LinkedList<>();    	
    	stageWalls = new LinkedList<>();    	
    	miniEnemies = new LinkedList<>();
    	stageEffects = new LinkedList<>();
    	stageForest = new LinkedList<>();
    	stageWater = new LinkedList<>();
    	mazeControl = new MazeControl(this);
    }    
    
    public void resetVariables(){  	
		
		pos = 1;		
    	nItems= 0;
    	timerE = 0;
		timerIt = 0;
		nEnemies = 0;
		nItemsSimul = 0;
		nEnemiesSimul = 0;
		enemiesKilled = 0;
		maxTimeItemEfect = 0;
		
		items.clear();
    	enemies.clear();
    	bullets.clear(); 	
    	stageWalls.clear();	
    	miniEnemies.clear();
    	stageForest.clear();
    	stageWater.clear();
    	stageIce.clear();
    	
		updateMiniEnemies = true;
		initDraw = true;
    }
        
    public void loadLevel(int level, boolean isPlayer1, boolean isPlayer2){
    	
    	if (isPlayer1) {
			if (players[0]!=null) {
				isPlayer1 = false;
				players[0].resetPlayer();				
			}
		}
    	
    	if (isPlayer2) {
			if (players[1]!=null) {
				isPlayer2 = false;
				players[1].resetPlayer();
			}
		}
    	
    	int[] nEnemies =  mazeControl.loadNumEnemies(level);
    	nEnemies1 = nEnemies[0];
    	nEnemies2 = nEnemies[1];
    	nEnemies3 = nEnemies[2];
    	nEnemies4 = nEnemies[3];
    	resetVariables();
    	
    	mazeControl.loadMaze(level,isPlayer1,isPlayer2);
    	eagleBricks = mazeControl.loadEagleWall(); 
    	imgBackStage = mazeControl.loadBackground(level);
    	sizeMazeItemsCol = mazeControl.getMazeItemsCol().size();
    	sizeMazeItemsRow = mazeControl.getMazeItemsRow().size();
    	loadMiniEnemies();
    	 
    }
    
	public void spawnBullets(Bullet e) {
    	bullets.add(e);
	}
	
	public void spawnEffects(Effect ef) {
    	stageEffects.add(ef);
	}
    
    public void spawnWater(Water e) {
    	stageWater.add(e);
	}
    
    public void spawnIce(Ice e) {
    	stageIce.add(e);
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
					switch (typeEnemy) {
					case 1:
						if (cantEnemies1<nEnemies1) {
							setPosEnemies(1);
							cantEnemies1++;
						}
						break;
					case 2:
						if (cantEnemies2<nEnemies2) {
							setPosEnemies(2);
							cantEnemies2++;
						}
						break;
					case 3:
						if (cantEnemies3<nEnemies3) {
							setPosEnemies(3);
							cantEnemies3++;
						}
						break;
					case 4:
						if (cantEnemies4<nEnemies4) {
							setPosEnemies(4);
							cantEnemies4 ++;
						}
						break;
					}
				
					
			}
		}
	}
    
    public void setPosEnemies(int typeEnemy){
    	pos = random.nextInt(3);
    	enemies.add(new Enemy(ROW_SPAWN_ENEMY, COL_SPAWN_ENEMY[pos], typeEnemy, this));
    	timerE=0;
    	nEnemies++;
		nEnemiesSimul++;
    }
    
    public void spawnItems() {
		if (nItems<cantItemsLevel) {
			if (nItemsSimul<Properties.MAX_ITEMS_SIMUL) {
				typeItem = random.nextInt(sizeMazeItemsCol);
				col =  mazeControl.getMazeItemsCol().get(typeItem)+1;
				typeItem = random.nextInt(sizeMazeItemsRow);
				row =  mazeControl.getMazeItemsCol().get(typeItem)+1;
				typeItem = random.nextInt(5)+1;
				items.add(new Item(col, row, typeItem, this));
				SoundControl.playSound("itemSpawn");
				nItemsSimul++;
				nItems++;
			}
		}		
	}
    
    public void spawnGas(Item it) {
    	items.add(it);		
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
    				if (dir[1] == 1 && !tmpEnemy.isShoot()) {
    					tmpEnemy.shoot(new Bullet(tmpEnemy.getPosX(),tmpEnemy.getPosY(),
    							tmpEnemy.getDir(),tmpEnemy.getTier(),tmpEnemy.getVelBullet(),this,tmpEnemy));
    				}
    			}    			
    			tmpEnemy.updateDraw();
    		}
    		else deleteEnemy(null,tmpEnemy);
    	}

    	if (gC.isPlayer1() && players[0].isActive()) players[0].updateDraw();
    	if (gC.isPlayer2() && players[1].isActive()) players[1].updateDraw();
    	
    	
    	for(int i=0;i<items.size();i++) {    		
    		if (items.get(i).isActive()) items.get(i).updateDraw();
    		else items.remove(items.get(i));
    	}
    	
    	for(int i=0;i<bullets.size();i++) {
    		tmpElement = bullets.get(i);
    		if (tmpElement.isActive()){
    			tmpElement.updateDraw();
    		}else deleteBullet((Bullet)tmpElement);
    	}
    	
    	if (eagleWallEfect) {
			for (int i = 0; i < stageWalls.size(); i++) {
				stageWalls.get(i).updateDraw();
			}
		}
    	
    	
		for (int i = 0; i < stageEffects.size(); i++) {
				stageEffects.get(i).updateDraw();
		}		
    	
    	if (updteBricks) {
    		for (int i = 0; i < eagleBricks.size(); i++) {
    			tmpWall = eagleBricks.get(i);
    			if (!tmpWall.isActive()) eagleBricks.remove(tmpWall);
    		}
    		for (int i = 0; i < stageWalls.size(); i++) {
    			tmpWall = stageWalls.get(i);
    			if (!tmpWall.isActive()) deleteWall(tmpWall);
    		}
    		updteBricks = false;
    	}
    	
    }
    
    public void draw(Graphics g){    	
    	
        if (rePaintStageGUI) {
			rePaintStageGUI(g);
			rePaintStageGUI = false;
		}
    	
    	if (initDraw) {      	
        	g.drawImage(imgBackStage[0], Properties.X_INIT_STAGE-1,Properties.Y_INIT_STAGE-2, Properties.WIDTH_STAGE+2, Properties.HEIGHT_STAGE+2, null);
			initDraw = false;
		}
        
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
    	
		if (gC.isPlayer1() && players[0].isActive()) players[0].draw(g);
		if (gC.isPlayer2() && players[1].isActive()) players[1].draw(g);
    	
    	for (Forest obs : stageForest) {
			obs.draw(g);
		}
    	    	
    	for (Effect ef : stageEffects) {
			ef.draw(g);
		}	
    	
    	for (Item item : items) {
			item.draw(g);
		}
    	
    	if (updateMiniEnemies) {
    		updateMiniEnemies(g);
		}
    	
    	if (!eagle.isActive()) {
			eagle.draw(g);
		}
    	
    }
    
    private void rePaintStageGUI(Graphics g) {
    	gC.getStageGUI().draw(gC, g);
		if (gC.isPlayer1() && players[0].isActive()) {
			players[0].setUpdateLifes(true);
			players[0].setUpdateScore(true);
			players[0].setUpdateGas(true);
		}
		if (gC.isPlayer2() && players[1].isActive()){
			players[1].setUpdateLifes(true);
			players[1].setUpdateScore(true);
			players[1].setUpdateGas(true);
		}
		updateMiniEnemies = true;
		initDraw = true;
		
	}

	public void deleteBullet(Bullet e){
        stageEffects.add(new Effect(e.getPosX(), e.getPosY(), 2,this));
    	bullets.remove(e);
    } 
    
    public void deleteEffect(Effect ef){
    	stageEffects.remove(ef);
    }
    
    public void deleteWall(Wall w){
    	SoundControl.playSound("explotionWallBrick");
        stageWalls.remove(w);
    }
    
    public void deleteEnemy(Player p,Enemy e){
    	SoundControl.playSound("explotionTank");
    	if (p!=null) {
			p.addScore(e.getType());
			p.getEnemyType().add(e.getType());
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
			eagleWallEfect = true;
			for (Wall wall : eagleBricks) {
				wall.setType(2);
				wall.setPaintBrick(false);
			}		
		}else{
			for (Wall wall : eagleBricks) {
				wall.setType(1);
				wall.setPaintBrick(true);
			}
			boolean d;
			for (Wall wall : stageWalls) {
				d = wall.isEagleBrick();
				if (d) {
					return;
				}						
			}
			eagleWallEfect = false;
			
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
			player.shieldEfect();	
			break;
		case 2://clock
			itemTaked = true;
			player.addScore(1);
			setClockEfect(true);
			break;
		case 3://shovel
			player.addScore(1);
			eagleIronWallEfect(true);
			break;
		case 4://star
			player.starEfect();
			break;
		case 5://bomb
			bombEfect(player);
			break;
		case 6://tank
			player.tankEfect();
			break;
		case 7://gas
			player.gasEfect();
			break;
		}
		deleteItem(it);
    }
	
	public void bombEfect(Player p) {
		for (int i = 0; i < enemies.size();i++) {
			Enemy e = enemies.get(i);			
			p.addScore(e.getType());
			p.getEnemyType().add(e.getType());
			e.setActive(false);
		}
	}
	
	public LinkedList<StageElement> getElements_Enemy() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		clone.addAll(enemies);
		clone.addAll(stageWalls);
		clone.addAll(stageWater);
		clone.add(eagle);
		if (gC.isPlayer1() && players[0].isActive()) clone.add(players[0]);
		if (gC.isPlayer2() && players[1].isActive()) clone.add(players[1]);
		return clone;
	}
	
	public LinkedList<StageElement> getElements_Player() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		clone.addAll(enemies);
		clone.addAll(stageWalls);
		clone.addAll(stageWater);
		clone.addAll(stageIce);
		clone.addAll(items);
		clone.add(eagle);
		if (gC.isPlayer1() && players[0].isActive()) clone.add(players[0]);
		if (gC.isPlayer2() && players[1].isActive()) clone.add(players[1]);
		return clone;
	}

	public LinkedList<StageElement> getMaze_Enemies() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		clone.addAll(enemies);
		if (gC.isPlayer1() && players[0].isActive()) clone.add(players[0]);
		if (gC.isPlayer2() && players[1].isActive()) clone.add(players[1]);
		clone.add(eagle);
		clone.addAll(stageWalls);
		clone.addAll(stageWater);
		return clone;
	}
	
	public LinkedList<StageElement> getMaze_Players() {
		LinkedList<StageElement> clone = new LinkedList<>();
		clone.addAll(eagleBricks);
		if (gC.isPlayer1() && players[0].isActive()) clone.add(players[0]);
		if (gC.isPlayer2() && players[1].isActive()) clone.add(players[1]);
		clone.add(eagle);
		clone.addAll(stageWalls);
		clone.addAll(stageWater);
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

	public boolean isRePaintStageGUI() {
		return rePaintStageGUI;
	}

	public void setRePaintStageGUI(boolean rePaintStageGUI) {
		this.rePaintStageGUI = rePaintStageGUI;
	}
		
}
