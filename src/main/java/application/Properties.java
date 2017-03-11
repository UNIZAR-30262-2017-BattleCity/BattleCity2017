package application;

public final class Properties {

	//Frame
	public final static int WIDTH = 720;
	public final static int HEIGHT = 480;
	public final static int SCALE = 2;
			
	//Tank
	public final static int WIDTH_TANK = 16;
	public final static int HEIGH_TANK = 16;
	
	//Player
	public final static int INIT_LIVES = 3;
	
	//Bullets
	public final static int VEL_BULLET = 3;
	public final static int MAX_BULLETS_TANK = 3;
	
	//Enemy
	public final static int VEL_ENEMY = 1;
	public final static int VEL_ENEMY_FAST = 2;
	public final static int MAX_ENEMY_SIMUL = 5;
	public final static int CANT_ENEMIES_LEVEL = 10;
	public final static int MAX_ENEMY_LEVEL = 4;
	public final static int TIME_BETWEEN_BIRTH = 10;
	
	//Spawn Enemy Points
	public final static int[] POS1_SPAWN_ENEMY= {1, 1};
	public final static int[] POS2_SPAWN_ENEMY= {13, 1};
	public final static int[] POS3_SPAWN_ENEMY= {7, 1};
	
	//_STAGE
	public final static int SIZE_SQUARE_STAGE = 16;
	public final static int SIZE_STAGE = SIZE_SQUARE_STAGE * 13;
	public final static int WIDTH_STAGE = SIZE_STAGE;
	public final static int HEIGHT_STAGE = SIZE_STAGE;
	public final static int X_INIT_STAGE = 20;
	public final static int Y_INIT_STAGE = 20;
	public final static int X_FINAL_STAGE = X_INIT_STAGE+WIDTH_STAGE - SIZE_SQUARE_STAGE;
	public final static int Y_FINAL_STAGE = Y_INIT_STAGE+HEIGHT_STAGE - SIZE_SQUARE_STAGE;	
	
	public final static int COL_STAGE = SIZE_SQUARE_STAGE;
	public final static int ROW_STAGE = SIZE_SQUARE_STAGE;
	
	//Elements on stage
	public final static int[] POS_INIT_PLAYER = {13, 5};
	public final static int[] POS_INIT_EAGLE = {13, 7};
	
	//Textures
	public final static int WIDTH_ELEMENT_STAGE = WIDTH_STAGE/COL_STAGE;
	public final static int HEIGH_ELEMENT_STAGE =  HEIGHT_STAGE/ROW_STAGE;
	
	//Sprite Sheets
	public final static String PATH_SS_TANK = "/Sprites/SpriteSheet.png";
			
	//Items
	public final static int MAX_ITEMS_LEVEL = 3;
	public final static int MAX_ITEMS_SIMUL = 3;
	public final static int MAX_TIME_ITEM = 8;
	
}
