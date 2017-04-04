package gameController;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.Properties;
import elements.Bullet;
import elements.Player;
import userInterface.Configuration;
import userInterface.Controls;
import userInterface.Cursor;
import userInterface.GameOver;
import userInterface.Score;
import userInterface.Screen;
import userInterface.StageGUI;


public class GameControl extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private final BufferedImage IMG_MENU = ImageControl.loadImage("/resources/images/Menu.png");	
	private boolean running;
	private Thread thread;
	private Player[] players;
	private boolean isPlayer1,isPlayer2;
	private boolean isGameOver;
	private boolean moveX;
	private StageControl stageControl;	
	private Screen screen;
	private Score score;
	private GameOver gameOver;
	private StageGUI stageGUI;
	private Configuration config;
	private Controls controls;
	private Cursor cursor;
	private IAControl ia;
	private ReadConfig dataConfig;
	private int level;
	private int difficulty;
	private int opc, opcH;
	private int timeToNext;
	private int[][] buttons;
	private int isSound;
	private boolean updateConfig;

	private boolean backgroundMenu;
	private boolean backgroundStage;
	private boolean player1OnIce,player2OnIce;
	
	public GameControl(JFrame jf){
		requestFocus();
		//jf.setFocusable(true);
		jf.addKeyListener(this);
		screen = Screen.INIT_MENU;
		level = 1;
		opc = 1;
		opcH = 1;
		moveX = false;
		cursor = new Cursor();
		ia = new IAControl();
		stageGUI = new StageGUI();
		timeToNext = 0;
		dataConfig = new ReadConfig();
		buttons = dataConfig.getButtons();
		difficulty = dataConfig.getDificulty();
		stageControl = new StageControl(this);
		players = StageControl.getPlayers();
		backgroundMenu = true;
		sound();
	}
	
	public void initStage(){
		stageControl.loadLevel(level, isPlayer1, isPlayer2);		
	}

	public synchronized void start(){
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();		
	}
	
	public synchronized void stop(){
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	@Override
	public void run() {
		long lastime = System.nanoTime();
		double frec = 60.0;
		double ns = 1000000000/frec;
		double delta=0;
		//double frames=0;
		//double hz=0;
		long timer = System.currentTimeMillis();
		int s=1000;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastime)/ns;
			lastime = now;
			if (delta >= 1) {
				updateDraw();
				//hz++;
				delta--;				
			}			
			draw();
			//frames++;			
			if (System.currentTimeMillis() - timer > s) {
				timer += s;
				//System.out.println("Hz: "+ hz + " FPS: " + frames);
				//hz=0;
				//frames=0;			
			}			
		}
		stop();		
	}
		
	public void sound(){
		isSound = dataConfig.getSound();
		if (isSound==1) {
			if (SoundControl.isPlay) {
				SoundControl.stopBackgroundSound();
			}
			if (backgroundMenu) {
				SoundControl.playBackgroundSound("presentation");
			}else
				if (backgroundStage) {
					SoundControl.playBackgroundSound("stageBackground");
				}			
			
		}else if (SoundControl.isPlay) {
			SoundControl.stopBackgroundSound();
		}
	}
	
	public void updateDraw(){	
		switch (screen) {
		case INTRO:
			break;
		case INIT_MENU:
			backgroundMenu = true;
			backgroundStage = false;
			sound();
	        screen = Screen.MENU;
			break;
		case MENU:
			break;
		case CONFIG:
			if (updateConfig) {
				sound();
				difficulty = dataConfig.getDificulty();
				updateConfig=false;
			}
			break;
		case CONTROLS:
			break;
		case PRESENT_STAGE:
			break;
		case INIT_STAGE:
			break;
		case STAGE_PLAY:
			onIce();
			stageControl.updateDraw();
			break;
		case STAGE_WIN:
			if (next(180)) screen = Screen.INIT_SCORE;
			break;
		case STAGE_PAUSED:
			break;
		case INIT_SCORE:
			break;
		case SCORE_STAGE:
			score.updateDraw(this);
	        if(score.isScoreFinished()){
	        	if (next(120)) {
	        		if (isGameOver) {
	        			screen = Screen.INIT_MENU;
	        			isGameOver = false;
	        		}else screen = Screen.PRESENT_STAGE;
	        	}
	        }
			break;
		case GAMEOVER:
			gameOver.updateDraw();
			if (next(240)) {
				screen = Screen.INIT_SCORE;				
			}
			break;
		default:
			break;
		}
		
	}
		
	private void onIce() {
		if (player1OnIce) {
			if (next(30)) {
				players[0].setVel(0);
				player1OnIce = false;
				players[0].setIce(false);
			}else{
				players[0].setVel(Properties.VEL_NORMAL);
			}
		}
		if (player2OnIce) {
			if (next(30)) {
				players[1].setVel(0);
				player2OnIce = false;
				players[1].setIce(false);
			}else{
				players[1].setVel(Properties.VEL_NORMAL);
			}				
		}
		
	}

	public void resultStage(int result){
		switch (result) {
		case 1:
			level++;
			screen = Screen.STAGE_WIN;
			break;
		case 2:
			if (!players[0].isActive()&& players[1]==null) {
				gameOver();				
			}else{
				if (players[1]!=null) {
					if (!players[0].isActive()&&!players[1].isActive()) {
						gameOver();
					}
				}
			}
			break;
		case 3:
			gameOver();
			break;
		}
		
	}
	
	public void gameOver(){		
		gameOver = new GameOver();
		isGameOver = true;
		level = 1;
		screen = Screen.GAMEOVER;
	}
	
	public void draw(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}		
		Graphics g = bs.getDrawGraphics();
		draw(g);
		g.dispose();
		bs.show();
	}
	
	public void draw(Graphics g){
		switch (screen) {
		case INTRO:
			
			break;
		case INIT_MENU:

			break;
		case MENU:
			menu(g);
			cursor.draw(g);
			break;
		case CONFIG:
			config.draw(g);
			cursor.draw(g);
			break;
		case CONTROLS:
			controls.draw(g);
			cursor.draw(g);
			break;
		case PRESENT_STAGE:
			presentStage(g);
			break;
		case INIT_STAGE:	        
	        stageGUI.draw(this,g);
	        initStage();
			backgroundMenu = false;
			backgroundStage = true;
			sound();
	        screen = Screen.STAGE_PLAY;
			break;
		case STAGE_PLAY:
			stageControl.draw(g);	
			break;
		case STAGE_WIN:
			stageControl.draw(g);			
			break;
		case STAGE_PAUSED:
			g.drawImage(ImageControl.getPaused(Properties.SSTANK), 252, 298, 100, 25, null);
			break;
		case INIT_SCORE:
			score = new Score();
			score.initDraw(g, this);
			screen = Screen.SCORE_STAGE;
			break;
		case SCORE_STAGE:			
			score.draw(g,this);
			break;
		case GAMEOVER:
			stageControl.setInitDraw(true);
			stageControl.draw(g);
			gameOver.draw(g);			
			break;
		default:
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (screen.equals(Screen.STAGE_PLAY)) {
			if (isPlayer1 && players[0].isActive()) {
				if (key == buttons[0][0] 
						|| key == buttons[0][1]
								|| key == buttons[0][2]
										|| key == buttons[0][3]) {
					if (players[0].isIce()) {
						player1OnIce = true;
					}else{
						players[0].setVel(0);
					}
				}
			}
			if (isPlayer2 && players[1].isActive()) {
				if (key == buttons[1][0] 
						|| key == buttons[1][1]
								|| key == buttons[1][2]
										|| key == buttons[1][3]) {
					if (players[1].isIce()) {
						player2OnIce = true;
					}else{
						players[1].setVel(0);
					}
				}
			}	

		}		

	}	
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (screen) {
		case INTRO:
			
			break;
		case MENU:
			if (key == KeyEvent.VK_UP) {
				cursorMove(-1,Properties.N_OPC_MENU);				
			}
			if (key == KeyEvent.VK_DOWN) {
				cursorMove(1,Properties.N_OPC_MENU);
			}
			if (key == KeyEvent.VK_ENTER) {		
				menuOptions();
			}
			break;
		case CONFIG:
			keyPressedConfig(key);
			break;
		case CONTROLS:
			if (moveX) {
				if (key == KeyEvent.VK_RIGHT) {
					cursorMove(1, Properties.N_OPC_CONTROLS_H);
				}
				if (key == KeyEvent.VK_LEFT) {
					cursorMove(-1, Properties.N_OPC_CONTROLS_H);
				}
				if (key == KeyEvent.VK_ENTER) {				
					screen = Screen.BUTTON_PLAYERS;
				}
			} else {
				if (key == KeyEvent.VK_UP) {
					cursorMove(-1,Properties.N_OPC_CONTROLS_V);				
				}
				if (key == KeyEvent.VK_DOWN) {
					cursorMove(1,Properties.N_OPC_CONTROLS_V);
				}
				if (key == KeyEvent.VK_ENTER) {				
					controlsOptions();
				}
			}
			break;
		case STAGE_PLAY:
			if (isPlayer1 && players[0].isActive()) {
				if (e.getKeyCode() == buttons[0][0]) {
					key = 1001;
				} else if (e.getKeyCode() == buttons[0][1]) {
					key = 1002;
				} else if (e.getKeyCode() == buttons[0][2]) {
					key = 1003;
				} else if (e.getKeyCode() == buttons[0][3]) {
					key = 1004;
				} else if (e.getKeyCode() == buttons[0][4]) {
					key = 1005;
				} else if (e.getKeyCode() == buttons[0][5]) {
					key = 1006;
				}
			}
			if (isPlayer2 && players[1].isActive()) {
				if (e.getKeyCode() == buttons[1][0]) {
					key = 2001;
				} else if (e.getKeyCode() == buttons[1][1]) {
					key = 2002;
				} else if (e.getKeyCode() == buttons[1][2]) {
					key = 2003;
				} else if (e.getKeyCode() == buttons[1][3]) {
					key = 2004;
				} else if (e.getKeyCode() == buttons[1][4]) {
					key = 2005;
				} else if (e.getKeyCode() == buttons[1][5]) {
					key = 2006;
				}
			}
			playerMove(key);
		case STAGE_PAUSED:
			if (isPlayer1) {
				if (key == buttons[0][5]){
					screen = Screen.STAGE_PLAY;
				}
			}			
			if (isPlayer2) {
				if	(key == buttons[1][5]) {	
					screen = Screen.STAGE_PLAY;
				}
			}
			break;
		case SCORE_STAGE:			
			if (key == KeyEvent.VK_ENTER ||
					key == KeyEvent.VK_G) {		
        		if (isGameOver) {
        			screen = Screen.INIT_MENU;
        		}else screen = Screen.PRESENT_STAGE;
			}			
			break;
		case BUTTON_PLAYERS:
			switch (opc) {
			case 1:
				if (opcH == 1) {
					buttons[0][0] = e.getKeyCode();
				} else {
					buttons[1][0] = e.getKeyCode();
				}
				break;
			case 2:
				if (opcH == 1) {
					buttons[0][1] = e.getKeyCode();
				} else {
					buttons[1][1] = e.getKeyCode();
				}
				break;
			case 3:
				if (opcH == 1) {
					buttons[0][3] = e.getKeyCode();
				} else {
					buttons[1][3] = e.getKeyCode();
				}
				break;
			case 4:
				if (opcH == 1) {
					buttons[0][2] = e.getKeyCode();
				} else {
					buttons[1][2] = e.getKeyCode();
				}
				break;
			case 5:
				if (opcH == 1) {
					buttons[0][4] = e.getKeyCode();
				} else {
					buttons[1][4] = e.getKeyCode();
				}
				break;
			case 6:
				if (opcH == 1) {
					buttons[0][5] = e.getKeyCode();
				} else {
					buttons[1][5] = e.getKeyCode();
				}
				break;
			default:
				break;
			}
			
			saveButton(cursor.getY());
			break;
		default:
			break;
		}		
		
	}
	
	private void keyPressedConfig(int key) {
		if (moveX) {
			int option = 0;
			if (key == KeyEvent.VK_RIGHT) {
				SoundControl.playSound("cursor");
				switch (opc) {
				case 2:
					option = config.getOpcDificulty();
					option++;
					if (option > 2) option = 0;
					if (option < 0) option = 2;
					config.setOpcDificulty(option);
					dataConfig.setDificulty(option+1);
					break;
				case 3:
					option = config.getOpcResolution();
					option++;
					if (option > 2) option = 0;
					if (option < 0) option = 2;
					config.setOpcResolution(option);
					dataConfig.setResolution(option+2);
					break;
				case 4:
					option = config.getOpcSound();
					option++;
					if (option > 1) option = 0;
					if (option < 0) option = 1;
					config.setOpcSound(option);
					dataConfig.setSound(option);						
					break;
				default:
					break;
				}
			}
			if (key == KeyEvent.VK_LEFT) {
				SoundControl.playSound("cursor");
				switch (opc) {
				case 2:
					option = config.getOpcDificulty();
					option--;
					if (option > 2) option = 0;
					if (option < 0) option = 2;
					config.setOpcDificulty(option);
					dataConfig.setDificulty(option+1);
					break;
				case 3:
					option = config.getOpcResolution();
					option--;
					if (option > 2) option = 0;
					if (option < 0) option = 2;
					config.setOpcResolution(option);
					dataConfig.setResolution(option+2);
					break;
				case 4:
					option = config.getOpcSound();
					option--;
					if (option > 1) option = 0;
					if (option < 0) option = 1;
					config.setOpcSound(option);
					dataConfig.setSound(option);
					break;
				default:
					break;
				}
			}
			if (key == KeyEvent.VK_ENTER) {	
				moveX = false;
				int y = cursor.getY();
				cursor.cursorConfigV();
				cursor.setY(y);
				dataConfig.writeConfig();
				updateConfig=true;
				JOptionPane.showMessageDialog(this, "Para visualizar cambios en la Resolucion se debe reiniciar la applicacion");
			}
		} else {
			if (key == KeyEvent.VK_UP) {
				cursorMove(-1,Properties.N_OPC_CONFIG);				
			}
			if (key == KeyEvent.VK_DOWN) {
				cursorMove(1,Properties.N_OPC_CONFIG);
			}
			if (key == KeyEvent.VK_ENTER) {				
				configOptions();
			}
		}
		
	}

	public void cursorMove(int move, int max){		
		SoundControl.playSound("cursor");
		if (moveX) {
			cursor.setX(cursor.getX()+ Properties.DELTA_CURSOR_X*move);
			opcH = opcH + move;
			if (opcH<=0) {
				opcH=max;
			}
			if (opcH>max) {
				opcH=1;
			}
			
		} else {
			cursor.setY(cursor.getY()+ Properties.DELTA_CURSOR*move);
			opc = opc + move;
			if (opc<1) {
				opc=max;
			}
			if (opc>max) {
				opc=1;
			}
		}
		
	}
	
	public void menuOptions(){
		switch (opc) {
		case 1:
			if (players[0]==null) {
				isPlayer1 = true;
			}else{
				players[0].resetPlayerGameOver();
			}	
			if (players[1]==null) {
				isPlayer2 = false;
			}else{
				StageControl.players[1] = null;
				isPlayer2 = false;
			}			
			screen = Screen.PRESENT_STAGE;
			break;
		case 2:
			if (players[0]==null) {
				isPlayer1 = true;
			}else{
				players[0].resetPlayerGameOver();
			}	
			if (players[1]==null) {
				isPlayer2 = true;
			}else{
				players[1].resetPlayerGameOver();
			}
			screen = Screen.PRESENT_STAGE;
			break;
		case 3:
			config = new Configuration();
			cursor.cursorConfigV();
			config.setOpcDificulty(difficulty-1);
			config.setOpcResolution(dataConfig.getResolution()-2);
			config.setOpcSound(dataConfig.getSound());
			opc = 1;
			screen = Screen.CONFIG;
			break;
		case 4:
			System.exit(0);			
			break;

		}
	}
	
	public void configOptions(){
		switch (opc) {
		case 1:
			controls = new Controls();
			controls.setButtons(buttons);
			cursor.cursorControlsV();
			controls.loadButtons();
			opc = 1;
			screen = Screen.CONTROLS;			
			break;
		case 2:
			moveX = true;
			cursor.cursorConfigH();
			break;
		case 3:
			moveX = true;
			cursor.cursorConfigH();
			break;
		case 4:
			moveX = true;
			cursor.cursorConfigH();
			break;
		case 5:
			cursor.cursorMenu();
			opc = 1;
			screen = Screen.MENU;
			break;
		}
	}
	
	public void controlsOptions(){
		switch (opc) {
		case 1:
			moveX = true;
			cursor.setMoveX(moveX);
			cursor.cursorControlsH();
			break;
		case 2:
			moveX = true;
			cursor.setMoveX(moveX);
			cursor.cursorControlsH();
			break;	
		case 3:
			moveX = true;
			cursor.setMoveX(moveX);
			cursor.cursorControlsH();
			break;	
		case 4:
			moveX = true;
			cursor.setMoveX(moveX);
			cursor.cursorControlsH();
			break;	
		case 5:
			moveX = true;
			cursor.setMoveX(moveX);
			cursor.cursorControlsH();
			break;	
		case 6:
			moveX = true;
			cursor.setMoveX(moveX);
			cursor.cursorControlsH();
			break;		
		case 7:
			cursor.cursorConfigV();
			opc = 1;
			screen = Screen.CONFIG;
			break;
		}
	}
	
	private void saveButton(int y) {
		opcH = 1;
		moveX = false;
		cursor.setMoveX(moveX);
		cursor.cursorControlsV();
		cursor.setY(y);
		controls.setButtons(buttons);
		controls.loadButtons();
		dataConfig.setButtons(buttons);
		dataConfig.writeConfig();
		screen = Screen.CONTROLS;
	}
	
	public void playerMove(int key){

		if (isPlayer1 && players[0].isActive()) {
			switch (key) {
			case 1001:
				players[0].setDir(1);
				players[0].setVel(Properties.VEL_NORMAL);
				break;
			case 1002:
				players[0].setDir(-1);
				players[0].setVel(Properties.VEL_NORMAL);
				break;
			case 1003:
				players[0].setDir(2);
				players[0].setVel(Properties.VEL_NORMAL);
				break;
			case 1004:
				players[0].setDir(-2);
				players[0].setVel(Properties.VEL_NORMAL);
				break;
			case 1005:
				players[0].shoot(new Bullet(players[0].getPosX(),players[0].getPosY(),players[0].getDir(),
						players[0].getTier(), players[0].getVelBullet(),stageControl,players[0]));
				break;
			case 1006:
				screen = Screen.STAGE_PAUSED;
				break;
			}
		}
		if (isPlayer2 && players[1].isActive()) {
			switch (key) {
			case 2001:
				players[1].setDir(1);
				players[1].setVel(Properties.VEL_NORMAL);
				break;

			case 2002:
				players[1].setDir(-1);
				players[1].setVel(Properties.VEL_NORMAL);
				break;
			case 2003:
				players[1].setDir(2);
				players[1].setVel(Properties.VEL_NORMAL);
				break;
			case 2004:
				players[1].setDir(-2);
				players[1].setVel(Properties.VEL_NORMAL);
				break;
			case 2005:
				players[1].shoot(new Bullet(players[1].getPosX(),players[1].getPosY(),players[1].getDir(),
						players[1].getTier(),players[0].getVelBullet(),stageControl,players[1]));
				break;
			case 2006:
				screen = Screen.STAGE_PAUSED;
				break;
			}
		}
		

	}
	
	private void menu(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH+20, Properties.HEIGHT);
        g.drawImage(IMG_MENU,0, 0, Properties.WIDTH, Properties.HEIGHT, null);
		
	}

	private void presentStage(Graphics g) {
		g.setColor(Color.darkGray);
        g.fillRect(0, 0, Properties.WIDTH+20, Properties.HEIGHT);
        g.setColor(Color.black);
        g.drawString("proque hptassss", 10,10);
        g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, Properties.FONT_LEVEL_SIZE));
        g.drawString("NIVEL "+level, (int) (Properties.WIDTH/2-Properties.WIDTH*.25), Properties.HEIGHT/2);
        if (level == 1) {
            g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, 6*Properties.SCALE));
        	g.drawString("PLAYER 1", (int) (Properties.WIDTH/2-Properties.WIDTH*.235), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.15));
        	g.drawString("PLAYER 2", (int) (Properties.WIDTH/2+Properties.WIDTH*.206), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.15));
        	
        	g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, 4*Properties.SCALE));
        	g.drawString("ARRIBA", (int) (Properties.WIDTH/2-Properties.WIDTH*.414), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.2));
        	g.drawString("ABAJ0", (int) (Properties.WIDTH/2-Properties.WIDTH*.4054), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.24));
        	g.drawString("IZQUIERDA", (int) (Properties.WIDTH/2-Properties.WIDTH*.4406), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.28));
        	g.drawString("DERECHA", (int) (Properties.WIDTH/2-Properties.WIDTH*.423), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.32));
        	g.drawString("DISPARAR", (int) (Properties.WIDTH/2-Properties.WIDTH*.4348), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.36));
        	g.drawString("PAUSAR", (int) (Properties.WIDTH/2-Properties.WIDTH*.414), 
        			(int) (Properties.HEIGHT/2+Properties.HEIGHT*.4));
        	
        	g.setFont(new Font("Arial", Font.PLAIN, 5*Properties.SCALE));
        	g.setColor(Color.white);
        	for (int i = 0; i < buttons.length; i++) {
    			for (int j = 0; j < buttons[0].length; j++) {
    				if (i == 0) {
    					g.drawString(KeyEvent.getKeyText(buttons[i][j]), Properties.X_BUTTONS_IP_PS, 
    							Properties.Y_INIT_BUTTONS_PS + (Properties.DELTA_BUTTONS_PS*j));
    				} else {
    					g.drawString(KeyEvent.getKeyText(buttons[i][j]), Properties.X_BUTTONS_IIP_PS, 
    							Properties.Y_INIT_BUTTONS_PS + (Properties.DELTA_BUTTONS_PS*j));
    				}
    			}
    		}
		}
        if (next(Properties.TIME_TO_INIT_STAGE)) screen = Screen.INIT_STAGE;
		
	}

	public boolean next(int time){
		if(timeToNext<time){
			timeToNext++;
			return false;
		}else{
			timeToNext = 0;
			return true;
		}
	}

	public IAControl getIa() {
		return ia;
	}

	public void setIa(IAControl ia) {
		this.ia = ia;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public boolean isPlayer2() {
		return isPlayer2;
	}

	public void setPlayer2(boolean isPlayer2) {
		this.isPlayer2 = isPlayer2;
	}

	public boolean isPlayer1() {
		return isPlayer1;
	}

	public void setPlayer1(boolean isPlayer1) {
		this.isPlayer1 = isPlayer1;
	}

	public StageControl getStageControl() {
		return stageControl;
	}

	public void setStageControl(StageControl stageControl) {
		this.stageControl = stageControl;
	}

	public StageGUI getStageGUI() {
		return stageGUI;
	}

	public void setStageGUI(StageGUI stageGUI) {
		this.stageGUI = stageGUI;
	}
	
}
