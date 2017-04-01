package application;

import java.awt.image.BufferedImage;

import gameController.FontControl;
import gameController.ImageControl;

public final class Properties {

	//Frame
	public final static int SCALE = 3;
	public final static int WIDTH = 227 * SCALE;
	public final static int HEIGHT = 194 * SCALE;
		
	//Menu - Configuration
	public final static int N_OPC_MENU =  4;
	public final static int N_OPC_CONFIG =  5;
	public final static int N_OPC_CONTROLS =  7;
	
	//Font
	public final static String FONT_PIXEL = "/resources/fonts/BattleCity.ttf";
	public static final FontControl FC_PIXEL= new FontControl(Properties.FONT_PIXEL);
	
	//Cursor Menu
	public final static int X_CURSOR_M =  (int) (WIDTH / 2 - (WIDTH * 0.14));;
	public final static int Y_INIT_CURSOR_M = (int) (HEIGHT / 2 + (HEIGHT * 0.045));
	public final static int DELTA_CURSOR =  (int) (WIDTH*0.0632353);
	public final static int Y_FINAL_CURSOR_M =  Y_INIT_CURSOR_M + DELTA_CURSOR * (N_OPC_MENU-1);
	
	//Cursor Configuration
	public final static int X_CURSOR_C = (int) (WIDTH / 2 - (WIDTH * 0.41));
	public final static int Y_INIT_CURSOR_C = (int) (HEIGHT / 2 - (HEIGHT * 0.105));
	public final static int Y_FINAL_CURSOR_C =  Y_INIT_CURSOR_C + DELTA_CURSOR * (N_OPC_CONFIG-1);
	
	//Cursor Controls
	public final static int X_CURSOR_CT = (int) (WIDTH / 2 - (WIDTH * 0.41));
	public final static int Y_INIT_CURSOR_CT = (int) (HEIGHT / 2 - (HEIGHT * 0.21));
	public final static int Y_FINAL_CURSOR_CT =  Y_INIT_CURSOR_CT + DELTA_CURSOR * (N_OPC_CONTROLS-1);
	
	//Player
	public final static int INIT_LIFES = 3;
	
	//Enemy
	public final static double VEL_ENEMY = 0.20*SCALE;
	public final static double VEL_NORMAL = 0.30*SCALE;
	public final static double VEL_ENEMY_FAST = 0.50*SCALE;
	public final static int MIN_ENEMY_SIMUL = 3;
	public final static int CANT_ENEMIES_LEVEL = 1;
	public final static int TIME_BETWEEN_SPAWN_E = 350;
	
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
	public final static int MAX_BULLETS_TANK = 1;
	public static final int yIB = Y_INIT_STAGE+2;
	public static final int xIB = X_INIT_STAGE+2;
	public static final int yFB = Y_FINAL_STAGE+SIZE_SQUARE-8;
	public static final int xFB = X_FINAL_STAGE+SIZE_SQUARE-8;
	
	//Elements on stage
	public final static int[] POS_INIT_PLAYER1 = {13, 5};
	public final static int[] POS_INIT_PLAYER2 = {13, 9};
	
	//Textures
	public final static int WIDTH_ELEMENT_STAGE = WIDTH_STAGE/COL_STAGE;
	public final static int HEIGH_ELEMENT_STAGE =  HEIGHT_STAGE/ROW_STAGE;
	
	//Sprite Sheets
	public final static String PATH_SS_TANK = "/resources/images/SpriteSheet.png";
	public final static String PATH_SS_CONFIG = "/resources/images/Config.png";
	public final static String PATH_SS_SCORE = "/resources/images/Config.png";
	public final static String PATH_SS_CONTROLS = "/resources/images/Controls.png";
	
	public static final BufferedImage SSTANK = ImageControl.loadImage(PATH_SS_TANK);
	
	//Items
	public final static int MAX_ITEMS_LEVEL = 20;
	public final static int MAX_ITEMS_SIMUL = 3;
	public final static int MAX_TIME_ITEM_EFECT = 480;
	public final static int MAX_TIME_ITEM_SHOW = 480;
	public final static int MIN_TIME_BETWEEN_SPAWN_IT = 360;
		
	
	public final static int TIME_ANIM = 5;
	public final static int TIME_TO_INIT_STAGE = 1000;
	public final static int TIME_TO_SCORE = 1000;
	public final static int TIME_TO_MENU = 1000;
	
	public static final double DELTA = SIZE_SQUARE/2;
	public static final int TIME_UPDATE_IA = 30;
	public static final int FONT_SIZE = 7*SCALE;
	
	//StageGUI
	
	public static final int INFO_BACKGROUND_WIDTH = (int) (WIDTH*1.05);
	public static final int X_INIT_INFO = (int) (X_FINAL_STAGE + (WIDTH*0.07));
	public static final int Y_INIT_INFO = (int) (HEIGHT * 0.0875);
	public static final int X_FINAL_INFO = (int) (WIDTH*0.99);
	public static final int Y_FINAL_INFO = Y_FINAL_STAGE;
	
	public static final int Y_MINI_ENEMIES = (int) (Y_INIT_INFO-(HEIGHT*0.035));
	public static final int MINI_ENEMIES_BACKGROUND_HEIGHT = (int) (HEIGHT*0.345);
	
	public static final int X_LIFES = (int) (X_INIT_INFO+(WIDTH*0.1));
	public static final int X_SCORE = X_LIFES;
	public static final int SCORE_LIFE_BACKGROUND_WIDTH = (int) (WIDTH*0.059);
	public static final int SCORE_LIFE_BACKGROUND_HEIGHT = (int) (HEIGHT*0.035);
	public static final double DELTA_SCORE_LIFE = HEIGHT*0.025;

	public static final int ICON_WIDTH = (int) (WIDTH_ELEMENT_STAGE+(WIDTH*0.0147));
	public static final int ICON_HEIGHT = (int) (HEIGH_ELEMENT_STAGE/2+(HEIGHT*0.0344));

	public static final int X_ICON_IP = (int) (X_INIT_INFO+(WIDTH*0.06));
	public static final int Y_ICON_IP = (int) (Y_INIT_INFO+(HEIGHT*0.344));
	public static final int Y_IP_LIFES = (int) (Y_ICON_IP+(HEIGHT*0.1));
	public static final int Y_IP_SCORE = (int) (Y_IP_LIFES+(HEIGHT*0.035));

	public static final int X_ICON_IIP = X_ICON_IP;
	public static final int Y_ICON_IIP = (int) (Y_ICON_IP+(HEIGHT*0.172));
	public static final int Y_IIP_LIFES = (int) (Y_ICON_IIP+(HEIGHT*0.1));
	public static final int Y_IIP_SCORE = (int) (Y_IIP_LIFES+(HEIGHT*0.035));
	
	public static final int FONT_LEVEL_SIZE = 16*SCALE;
	public static final int X_FLAG = (int) (X_INIT_INFO+(WIDTH*0.035));
	public static final int Y_FLAG = (int) (Y_FINAL_INFO-(HEIGHT*0.1));
	public static final int X_LEVEL = X_FLAG;
	public static final int Y_LEVEL = (int) (Y_FINAL_INFO+(HEIGHT*0.065));
	public static final int FLAG_WIDTH = ICON_WIDTH;
	public static final int FLAG_HEIGHT = (int) (HEIGH_ELEMENT_STAGE+(HEIGHT*0.017));
	
	//Game Over
	public static final int X_GO = (int) (WIDTH/2-(WIDTH*0.1325));
	public static final double Y_GO = HEIGHT_STAGE-(Properties.HEIGHT*0.035);
	public static final int GO_WIDTH = (int) (WIDTH*0.1765);
	public static final int GO_HEIGHT = (int) (HEIGHT*0.0605);
	
	/*Mistakes
	birth of items over enemies eagle etc 
	birth of enemies over enemies
	*/
	
}
