package application;

import gameController.ImageControl;

public final class Properties {

	//Frame
	public final static int WIDTH = 680;
	public final static int HEIGHT = 580;
	public final static int SCALE = 3;
		
	//Menu - Configuration
	public final static int N_OPC_MENU =  4;
	public final static int N_OPC_CONFIG =  5;
	
	//Font
	public final static String FONT_PIXEL = "/resources/fonts/BattleCity.ttf";
	
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
	
	//Enemy
	public final static int VEL_ENEMY = 1;
	public final static int VEL_ENEMY_FAST = 2;
	public final static int MAX_ENEMY_SIMUL = 5;
	public final static int CANT_ENEMIES_LEVEL = 20;
	public final static int MAX_ENEMY_LEVEL = 4;
	public final static int TIME_BETWEEN_SPAWN_E = 300;
	
	//Spawn Enemy Points
	public final static int[] POS1_SPAWN_ENEMY= {1, 1};
	public final static int[] POS2_SPAWN_ENEMY= {1, 13};
	public final static int[] POS3_SPAWN_ENEMY= {1, 7};
	
	//_STAGE
	public final static int SIZE_SQUARE_SSC = 16;
	public final static int SIZE_SQUARE = SIZE_SQUARE_SSC*SCALE-8;
	public final static int SIZE_STAGE = SIZE_SQUARE * 13;
	public final static int WIDTH_STAGE = SIZE_STAGE;
	public final static int HEIGHT_STAGE = SIZE_STAGE;
	public final static int X_INIT_STAGE = 40;
	public final static int Y_INIT_STAGE = 30;
	public final static int X_FINAL_STAGE = X_INIT_STAGE+WIDTH_STAGE - SIZE_SQUARE;
	public final static int Y_FINAL_STAGE = Y_INIT_STAGE+HEIGHT_STAGE - SIZE_SQUARE;	
	
	public final static int COL_STAGE = 13;
	public final static int ROW_STAGE = 13;
	
	//Bullets
	public final static int VEL_BULLET = 3;
	public final static int MAX_BULLETS_TANK = 3;
	public static final int yIB = Y_INIT_STAGE+2;
	public static final int xIB = X_INIT_STAGE+2;
	public static final int yFB = Y_FINAL_STAGE+SIZE_SQUARE-8;
	public static final int xFB = X_FINAL_STAGE+SIZE_SQUARE-8;
	
	//Elements on stage
	public final static int[] POS_INIT_PLAYER = {13, 5};
	
	//Textures
	public final static int WIDTH_ELEMENT_STAGE = WIDTH_STAGE/COL_STAGE;
	public final static int HEIGH_ELEMENT_STAGE =  HEIGHT_STAGE/ROW_STAGE;
	
	//Sprite Sheets
	public final static String PATH_SS_TANK = "/resources/images/SpriteSheet.png";
	public final static String PATH_SS_MENU = "/resources/images/Menu.png";
	public final static String PATH_SS_CONFIG = "/resources/images/Config.png";
	
	public static final ImageControl SSCTANK = new ImageControl(PATH_SS_TANK);
	
	//Items
	public final static int MAX_ITEMS_LEVEL = 10;
	public final static int MAX_ITEMS_SIMUL = 3;
	public final static int MAX_TIME_ITEM_EFECT = 480;
	public final static int MAX_TIME_ITEM_SHOW = 480;
	public final static int TIME_BETWEEN_SPAWN_IT = 700;
		
	
	public final static int TIME_ANIM = 5;
	
	
	//StageGUI
	public static final int X_INIT_INFO = X_FINAL_STAGE+60;
	public static final int Y_INIT_INFO = Y_INIT_STAGE+40;
	public static final int X_FINAL_INFO = WIDTH -5;
	public static final int Y_FINAL_INFO = Y_FINAL_STAGE;
	
	public static final int Y_IP_LIFES = Y_INIT_INFO+260;
	public static final int Y_IP_SCORE = Y_IP_LIFES+20;
	
	public static final int Y_IIP_LIFES = Y_IP_LIFES+100;
	public static final int Y_IIP_SCORE = Y_IIP_LIFES+20;
	/*Mistakes
	birth of items over enemies
	birth of enemies over enemies
	little enemies not destroy when are 5 and item bomb 
	*/
	
}
