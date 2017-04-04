package application;

import java.awt.Font;
import java.awt.image.BufferedImage;

import gameController.FontControl;
import gameController.ImageControl;
import gameController.ReadConfig;

public final class Properties {

	
	public final static ReadConfig DATA_CONFIG = new ReadConfig();
	
	//Frame
	public final static int SCALE = DATA_CONFIG.getResolution();
	public final static int WIDTH = 227 * SCALE;
	public final static int HEIGHT = 194 * SCALE;
	
	//Menu - Configuration
	public final static int N_OPC_MENU =  4;
	public final static int N_OPC_CONFIG =  5;
	public final static int N_OPC_CONTROLS_V =  7;
	public final static int N_OPC_CONTROLS_H =  2;
	
	
	//Cursor Menu
	public final static int X_CURSOR_M =  (int) (WIDTH / 2 - (WIDTH * 0.14));;
	public final static int Y_INIT_CURSOR_M = (int) (HEIGHT / 2 + (HEIGHT * 0.045));
	public final static int DELTA_CURSOR =  (int) (WIDTH*0.0632353);
	public final static int Y_FINAL_CURSOR_M =  Y_INIT_CURSOR_M + DELTA_CURSOR * (N_OPC_MENU-1);
	
	//Cursor Configuration
	public final static int X_CURSOR_C = (int) (WIDTH / 2 - (WIDTH * 0.41));
	public final static int X_INIT_CURSOR_C = (int) (WIDTH / 2 + (WIDTH * 0.07));
	public final static int Y_INIT_CURSOR_C = (int) (HEIGHT / 2 - (HEIGHT * 0.105));
	public final static int Y_FINAL_CURSOR_C =  Y_INIT_CURSOR_C + DELTA_CURSOR * (N_OPC_CONFIG-1);
	
	//Cursor Controls
	public final static int X_CURSOR_CT = (int) (WIDTH / 2 - (WIDTH * 0.41));
	public final static int Y_INIT_CURSOR_CT = (int) (HEIGHT / 2 - (HEIGHT * 0.21));
	public final static int Y_FINAL_CURSOR_CT =  Y_INIT_CURSOR_CT + DELTA_CURSOR * (N_OPC_CONTROLS_V-1);
	public final static int DELTA_CURSOR_X =  (int) (WIDTH*0.189);
	public final static int X_INIT_CURSOR_CT = X_INIT_CURSOR_C;
	public final static int X_FINAL_CURSOR_CT =  X_INIT_CURSOR_CT + DELTA_CURSOR_X * (N_OPC_CONTROLS_H-1);
	
	//Controls GUI
	public final static int Y_INIT_BUTTONS = (int) (Properties.HEIGHT*.335);
	public final static int DELTA_BUTTONS = (int) (HEIGHT*.074);
	public final static int X_BUTTONS_IP = (int) (Properties.WIDTH*.64);
	public final static int X_BUTTONS_IIP = (int) (X_BUTTONS_IP + (WIDTH*.187));
	
	//Configuration GUI
	public final static int Y_CONFIG = (int) (Properties.HEIGHT*.518);
	public final static int X_CONFIG = (int) (Properties.WIDTH*.65);
	public final static int DELTA_CONFIG = DELTA_BUTTONS;
	
	//Present Stage 1 Buttons
	public final static int Y_INIT_BUTTONS_PS = (int) (Properties.HEIGHT*.697);
	public final static int DELTA_BUTTONS_PS = (int) (HEIGHT*.04125);
	public final static int X_BUTTONS_IP_PS = (int) (Properties.WIDTH*.345);
	public final static int X_BUTTONS_IIP_PS = (int) (X_BUTTONS_IP + (WIDTH*.177));
	
	//Player
	public final static int INIT_LIFES = 3;
	
	//Enemy
	public final static double VEL_ENEMY = 0.20*SCALE;
	public final static double VEL_NORMAL = 0.30*SCALE;
	public final static double VEL_ENEMY_FAST = 0.50*SCALE;
	public final static int MIN_ENEMY_SIMUL = 3;
	public final static int CANT_ENEMIES_LEVEL = 20;
	public final static int TIME_BETWEEN_SPAWN_E = 500;
	
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
	public final static double VEL_BULLET = 0.90*SCALE;
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
	public static final BufferedImage SSTANK = ImageControl.loadImage("/resources/images/SpriteSheet.png");
	
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
		
	//Font
	public final static String FONT_PIXEL = "/resources/fonts/BattleCity.ttf";
	public static final FontControl FC_PIXEL= new FontControl(Properties.FONT_PIXEL);
	public static final Font ARIAL = new Font("Arial", Font.PLAIN, FONT_SIZE);
	
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
	public static final int SCORE_LIFE_BACKGROUND_WIDTH = (int) (WIDTH*0.090);
	public static final int SCORE_LIFE_BACKGROUND_HEIGHT = (int) (HEIGHT*0.035);
	public static final double DELTA_SCORE_LIFE = HEIGHT*0.025;

	public static final int ICON_WIDTH = (int) (WIDTH_ELEMENT_STAGE+(WIDTH*0.0147));
	public static final int ICON_HEIGHT = (int) (HEIGH_ELEMENT_STAGE/2+(HEIGHT*0.0344));

	public static final int X_ICON_IP = (int) (X_INIT_INFO+(WIDTH*0.06));
	public static final int Y_ICON_IP = (int) (Y_INIT_INFO+(HEIGHT*0.31));
	public static final int Y_IP_LIFES = (int) (Y_ICON_IP+(HEIGHT*0.1));
	public static final int Y_IP_SCORE = (int) (Y_IP_LIFES+(HEIGHT*0.035));
	public static final int Y_IP_GAS = (int) (Y_IP_SCORE+(HEIGHT*0.01));
	
	public static final int X_ICON_IIP = X_ICON_IP;
	public static final int Y_ICON_IIP = (int) (Y_ICON_IP+(HEIGHT*0.19));
	public static final int Y_IIP_LIFES = (int) (Y_ICON_IIP+(HEIGHT*0.1));
	public static final int Y_IIP_SCORE = (int) (Y_IIP_LIFES+(HEIGHT*0.035));
	public static final int Y_IIP_GAS = (int) (Y_IIP_SCORE+(HEIGHT*0.01));
	
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
