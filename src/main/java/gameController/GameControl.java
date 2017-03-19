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
import elements.Stage;
import userInterface.Configuration;
import userInterface.Cursor;
import userInterface.Menu;
import userInterface.Screen;


public class GameControl extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private boolean running;
	private Thread thread;
	private SpriteSheetControl ssc;
	private Player player;
	private Stage stage;
	private Screen screen;
	private Menu menu;
	private Configuration config;
	private Cursor cursor;
	private int level;
	private int difficulty;
	private int opc;
	
	public GameControl(JFrame jf){
		requestFocus();		
		jf.addKeyListener(this);
		screen = Screen.MENU;
		level = 1;
		difficulty = 0;
		opc = 1;
		ssc = new SpriteSheetControl(Properties.PATH_SS_TANK);
		menu = new Menu();
		cursor = new Cursor();
	}
	
	public void initStage(){				
		stage = new Stage(level, difficulty, ssc);
		player = stage.getPlayer();
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
		double frames=0;
		double hz=0;
		long timer = System.currentTimeMillis();
		int s=1000;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastime)/ns;
			lastime = now;
			if (delta >= 1) {
				updateDraw();
				hz++;
				delta--;				
			}			
			draw();
			frames++;			
			if (System.currentTimeMillis() - timer > s) {
				timer += s;
				//System.out.println("Hz: "+ hz + " FPS: " + frames);
				hz=0;
				frames=0;			
			}			
		}
		stop();		
	}
		
	public void updateDraw(){
		if (screen.equals(Screen.STAGE_PLAY)) {
			player.updateDraw();
			player.updateDrawBullet();
			stage.updateDraw();
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
		case INIT_STAGE:
			g.setColor(Color.darkGray);
	        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
	        initStage();
	        screen = Screen.STAGE_PLAY;
			break;
		case STAGE_PLAY:
			stage.draw(g);
			player.draw(g);
			player.drawBullet(g);		
			break;
		case STAGE_PAUSED:
			
			break;
		case SCORE_STAGE:
			
			break;
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		//int key = e.getKeyCode();
		if (screen.equals(Screen.STAGE_PLAY)) {
			player.setVel(0);
		}
		
		/*if (key == KeyEvent.VK_UP) {
			player.setVelX(0);
		}
		if (key == KeyEvent.VK_DOWN) {
			player.setVelX(0);
		}
		if (key == KeyEvent.VK_RIGHT) {
			player.setVelY(0);
		}
		if (key == KeyEvent.VK_LEFT) {
			player.setVelY(0);
		}*/
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
		case STAGE_PLAY:
			if (key == KeyEvent.VK_UP) {
				player.setDirection(1);
				player.setVel(1);			
			}
			if (key == KeyEvent.VK_DOWN) {
				player.setDirection(-1);
				player.setVel(1);			
			}
			if (key == KeyEvent.VK_RIGHT) {
				player.setDirection(2);
				player.setVel(1);	
			}
			if (key == KeyEvent.VK_LEFT) {
				player.setDirection(-2);
				player.setVel(1);
			}
			if (key == KeyEvent.VK_SPACE) {
				player.shoot(new Bullet(player.getPosX(),player.getPosY(),player.getDirection(),0,ssc,stage));
			}
			if (key == KeyEvent.VK_ENTER) {				
				//TODO pause
			}
			break;
		case STAGE_PAUSED:
			
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
			break;
		case 2:
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
			screen = Screen.CONTROLS;			
			break;		
		case 5:
			cursor.cursorMenu();
			opc = 1;
			screen = Screen.MENU;
			break;
		}
	}

}
