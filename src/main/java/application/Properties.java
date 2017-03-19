package application;

import gameController.SpriteSheetControl;

public final class Properties {

	//Frame
	public final static int WIDTH = 720;
	public final static int HEIGHT = 480;
	public final static int SCALE = 2;
		
	//Menu - Configuration
	public final static int N_OPC_MENU =  4;
	public final static int N_OPC_CONFIG =  5;
	
	//Font
	public final static String FONT_PIXEL = "/resources/fonts/Pixel.ttf";
	
	//Cursor Menu
	public final static int X_CURSOR_M =  275;
	public final static int Y_INIT_CURSOR_M =  264;
	public final static int DELTA_CURSOR =  34;
	public final static int Y_FINAL_CURSOR_M =  Y_INIT_CURSOR_M + DELTA_CURSOR * (N_OPC_MENU-1);
	
	//Cursor Configuration
	public final static int X_CURSOR_C = 90;
	public final static int Y_INIT_CURSOR_C = 192;
	public final static int Y_FINAL_CURSOR_C =  Y_INIT_CURSOR_C + DELTA_CURSOR * (N_OPC_CONFIG-1);
	
	//Player
	public final static int INIT_LIVES = 3;
	
	//Bullets
	public final static int VEL_BULLET = 3;
	public final static int MAX_BULLETS_TANK = 3;
	
	//Enemy
	public final static int VEL_ENEMY = 1;
	public final static int VEL_ENEMY_FAST = 2;
	public final static int MAX_ENEMY_SIMUL = 5;
	public final static int CANT_ENEMIES_LEVEL_123 = 15;
	public final static int MAX_ENEMY_LEVEL = 4;
	public final static int TIME_BETWEEN_SPAWN_E = 300;
	
	//Spawn Enemy Points
	public final static int[] POS1_SPAWN_ENEMY= {1, 1};
	public final static int[] POS2_SPAWN_ENEMY= {1, 13};
	public final static int[] POS3_SPAWN_ENEMY= {1, 7};
	
	//_STAGE
	public final static int SIZE_SQUARE_SSC = 16;
	public final static int SIZE_SQUARE = SIZE_SQUARE_SSC*2;
	public final static int SIZE_STAGE = SIZE_SQUARE * 13;
	public final static int WIDTH_STAGE = SIZE_STAGE;
	public final static int HEIGHT_STAGE = SIZE_STAGE;
	public final static int X_INIT_STAGE = 40;
	public final static int Y_INIT_STAGE = 30;
	public final static int X_FINAL_STAGE = X_INIT_STAGE+WIDTH_STAGE - SIZE_SQUARE;
	public final static int Y_FINAL_STAGE = Y_INIT_STAGE+HEIGHT_STAGE - SIZE_SQUARE;	
	
	public final static int COL_STAGE = 13;
	public final static int ROW_STAGE = 13;
	
	//Elements on stage
	public final static int[] POS_INIT_PLAYER = {13, 5};
	
	//Textures
	public final static int WIDTH_ELEMENT_STAGE = WIDTH_STAGE/COL_STAGE;
	public final static int HEIGH_ELEMENT_STAGE =  HEIGHT_STAGE/ROW_STAGE;
	
	//Sprite Sheets
	public final static String PATH_SS_TANK = "/resources/images/SpriteSheet.png";
	public final static String PATH_SS_MENU = "/resources/images/Menu.png";
	public final static String PATH_SS_CONFIG = "/resources/images/Config.png";
	
	public static final SpriteSheetControl SSCTANK = new SpriteSheetControl(PATH_SS_TANK);
			
	//Items
	public final static int MAX_ITEMS_LEVEL = 10;
	public final static int MAX_ITEMS_SIMUL = 3;
	public final static int MAX_TIME_ITEM_EFECT = 480;
	public final static int MAX_TIME_ITEM_SHOW = 480;
	public final static int TIME_BETWEEN_SPAWN_IT = 700;
	
	/*Mistakes
	birth of items over enemies
	birth of enemies over enemies
	size bricks
	size sprite of bullets
	*/
	
}
