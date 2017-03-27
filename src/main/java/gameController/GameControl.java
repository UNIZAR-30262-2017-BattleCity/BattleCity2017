package gameController;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import application.Properties;
import elements.Bullet;
import elements.Player;
import userInterface.Configuration;
import userInterface.Controls;
import userInterface.Cursor;
import userInterface.GameOver;
import userInterface.Menu;
import userInterface.Screen;
import userInterface.StageGUI;


public class GameControl extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private boolean running;
	private Thread thread;
	private Player[] players;
	private boolean isPlayer1,isPlayer2;
	private StageControl stageControl;
	private Screen screen;
	private Menu menu;
	private GameOver gameOver;
	private StageGUI stageGUI;
	private Configuration config;
	private Controls controls;
	private Cursor cursor;
	private IAControl ia;
	private int level;
	private int difficulty;
	private int opc;
	
	public GameControl(JFrame jf){
		requestFocus();
		jf.addKeyListener(this);
		screen = Screen.MENU;
		level = 4;
		difficulty = 0;
		opc = 1;
		menu = new Menu();
		cursor = new Cursor();
		ia = new IAControl();
	}
	
	public void initStage(){				
		stageControl = new StageControl(this);
		players = StageControl.getPlayers();
		level++;
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
		
	public void updateDraw(){
		if (screen.equals(Screen.STAGE_PLAY)) {
			stageControl.updateDraw();
		}else if (screen.equals(Screen.GAMEOVER)) {
			stageControl.updateDraw();
			gameOver.updateDraw();
		}
		
	}
		
	public void resultStage(int result){
		switch (result) {
		case 1:
			gameOver = new GameOver();
			screen = Screen.SCORE_STAGE;
			break;
		case 2:
			gameOver = new GameOver();
			screen = Screen.GAMEOVER;
			break;
		}
		
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
		case MENU:
			menu.draw(g);
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
			g.setColor(Color.darkGray);
	        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
	        //TODO 
			break;
		case INIT_STAGE:
	        stageGUI = new StageGUI(level);
	        stageGUI.draw(level,g);
	        initStage();
	        screen = Screen.STAGE_PLAY;
			break;
		case STAGE_PLAY:
			stageControl.draw(g);	
			break;
		case STAGE_PAUSED:
			g.drawImage(ImageControl.getPaused(Properties.SSTANK), 252, 298, 100, 25, null);
			break;
		case SCORE_STAGE:
			
			break;
		case GAMEOVER:
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
			if (isPlayer1) {
				switch (key) {
				case KeyEvent.VK_UP:
					players[0].setVel(0);
					break;
				case KeyEvent.VK_DOWN:
					players[0].setVel(0);
					break;
				case KeyEvent.VK_RIGHT:
					players[0].setVel(0);
					break;
				case KeyEvent.VK_LEFT:
					players[0].setVel(0);
					break;
				}
			}
			if (isPlayer2) {
				switch (key) {
				case KeyEvent.VK_W:
					players[1].setVel(0);
					break;
				case KeyEvent.VK_S:
					players[1].setVel(0);
					break;
				case KeyEvent.VK_D:
					players[1].setVel(0);
					break;
				case KeyEvent.VK_A:
					players[1].setVel(0);
					break;
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
			if (key == KeyEvent.VK_UP) {
				cursorMove(-1,Properties.N_OPC_CONFIG);				
			}
			if (key == KeyEvent.VK_DOWN) {
				cursorMove(1,Properties.N_OPC_CONFIG);
			}
			if (key == KeyEvent.VK_RIGHT) {
				
			}
			if (key == KeyEvent.VK_LEFT) {
				
			}
			if (key == KeyEvent.VK_ENTER) {				
				configOptions();
			}
			break;
		case CONTROLS:
			if (key == KeyEvent.VK_UP) {
				cursorMove(-1,Properties.N_OPC_CONTROLS);				
			}
			if (key == KeyEvent.VK_DOWN) {
				cursorMove(1,Properties.N_OPC_CONTROLS);
			}
			if (key == KeyEvent.VK_RIGHT) {
				
			}
			if (key == KeyEvent.VK_LEFT) {
				
			}
			if (key == KeyEvent.VK_ENTER) {				
				controlsOptions();
			}
			break;
		case STAGE_PLAY:
			playerMove(key);
		case STAGE_PAUSED:
			if (key == KeyEvent.VK_ENTER ||
					key == KeyEvent.VK_G) {		
				screen = Screen.STAGE_PLAY;
			}
			break;
		case SCORE_STAGE:
			
			break;
		default:
			break;
		}		
		
	}
	
	public void cursorMove(int move, int max){
		cursor.setY(cursor.getY()+ Properties.DELTA_CURSOR*move);
		opc = opc + move;
		if (opc<1) {
			opc=max;
		}
		if (opc>max) {
			opc=1;
		}
		
	}
	
	public void menuOptions(){
		switch (opc) {
		case 1:
			screen = Screen.INIT_STAGE;
			isPlayer1 = true;
			break;
		case 2:
			isPlayer1 = true;
			isPlayer2 = true;
			screen = Screen.INIT_STAGE;
			break;
		case 3:
			config = new Configuration();
			cursor.cursorConfig();
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
			cursor.cursorControls();
			opc = 1;
			screen = Screen.CONTROLS;			
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
			break;		
		case 7:
			cursor.cursorConfig();
			opc = 1;
			screen = Screen.CONFIG;
			break;
		}
	}
	
	public void playerMove(int key){

		if (isPlayer1) {
			switch (key) {
			case KeyEvent.VK_UP:
				players[0].setDir(1);
				players[0].setVel(1);
				break;

			case KeyEvent.VK_DOWN:
				players[0].setDir(-1);
				players[0].setVel(1);
				break;
			case KeyEvent.VK_RIGHT:
				players[0].setDir(2);
				players[0].setVel(1);
				break;
			case KeyEvent.VK_LEFT:
				players[0].setDir(-2);
				players[0].setVel(1);
				break;
			case KeyEvent.VK_SPACE :
				players[0].shoot(new Bullet(players[0].getPosX(),players[0].getPosY(),players[0].getDir(),0,stageControl,players[0]));
				break;
			case KeyEvent.VK_ENTER:
				screen = Screen.STAGE_PAUSED;
				break;
			}
		}
		if (isPlayer2) {
			switch (key) {
			case KeyEvent.VK_W:
				players[1].setDir(1);
				players[1].setVel(1);
				break;

			case KeyEvent.VK_S:
				players[1].setDir(-1);
				players[1].setVel(1);
				break;
			case KeyEvent.VK_D:
				players[1].setDir(2);
				players[1].setVel(1);
				break;
			case KeyEvent.VK_A:
				players[1].setDir(-2);
				players[1].setVel(1);
				break;
			case KeyEvent.VK_F :
				players[1].shoot(new Bullet(players[1].getPosX(),players[1].getPosY(),players[1].getDir(),0,stageControl,players[1]));
				break;
			case KeyEvent.VK_G:
				screen = Screen.STAGE_PAUSED;
				break;
			}
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
	
}
