package gameController;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import application.Properties;
import elements.Bullet;
import elements.Eagle;
import elements.Enemy;
import elements.Item;
import elements.Player;
import elements.Stage;


public class GameControl extends Canvas implements Runnable, KeyListener{

	
	private static final long serialVersionUID = 1L;
	private BufferedImage backgroundStage = new BufferedImage(Properties.WIDTH_STAGE, Properties.HEIGHT_STAGE, BufferedImage.TYPE_INT_RGB);
	private boolean running;
	private Thread thread;
	private SpriteSheetControl sscTank;
	private Player player;
	private Stage stage;
	private int level;
	
	public GameControl(JFrame jf){
		requestFocus();		
		initStage();
		jf.addKeyListener(this);		
	}
	
	public void initStage(){
		sscTank = new SpriteSheetControl(Properties.PATH_SS_TANK);
		player = new Player(Properties.POS_INIT_PLAYER[0], Properties.POS_INIT_PLAYER[1], Properties.INIT_LIVES, sscTank);
		level = 1;
		stage = new Stage(level);
		stage.spawnElements(new Eagle(Properties.POS_INIT_EAGLE[0],Properties.POS_INIT_EAGLE[1],sscTank));
		stage.spawnEnemys(new Enemy(Properties.POS1_SPAWN_ENEMY[0], Properties.POS1_SPAWN_ENEMY[1], 1, sscTank));
		stage.spawnEnemys(new Enemy(Properties.POS2_SPAWN_ENEMY[0], Properties.POS1_SPAWN_ENEMY[1], 1, sscTank));
		stage.spawnEnemys(new Enemy(Properties.POS3_SPAWN_ENEMY[0], Properties.POS1_SPAWN_ENEMY[1], 2, sscTank));
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
			render();
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
		player.updateDraw();
		player.updateDrawBullet();
		stage.updateDraw();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}		
		Graphics g = bs.getDrawGraphics();	
		g.drawImage(backgroundStage, Properties.X_INIT_STAGE, Properties.Y_INIT_STAGE, Properties.WIDTH_STAGE, Properties.HEIGHT_STAGE, this);
		player.draw(g);
		player.drawBullet(g);
		stage.draw(g);
		g.dispose();
		bs.show();
	}
	

	public void ItemTaked(Item it){
		player.setItemTaked(true);
		
		switch (it.getId()) {
		case 1://pala
			stage.eagleWallEfect();
			break;
		case 2://estrella
			player.starEfect();
			break;
		case 3://casco
			player.cascoEfect();
			break;
		case 4://bomba
			stage.bombEfect();
			break;
		case 5://reloj
			stage.relojEfect();
			break;
		case 6://tanque
			player.setLifes(player.getLifes()+1);
			break;
		}
    }
	
	@Override
	public void keyReleased(KeyEvent e) {
		//int key = e.getKeyCode();
		
		player.setVelX(0);
		player.setVelY(0);
		
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
		
		if (key == KeyEvent.VK_UP) {
			player.setDirection(0);
			player.setVelY(-1);
			player.setVelX(0);			
		}
		if (key == KeyEvent.VK_DOWN) {
			player.setDirection(1);
			player.setVelY(1);
			player.setVelX(0);			
		}
		if (key == KeyEvent.VK_RIGHT) {
			player.setDirection(3);
			player.setVelX(1);
			player.setVelY(0);	
		}
		if (key == KeyEvent.VK_LEFT) {
			player.setDirection(2);
			player.setVelX(-1);
			player.setVelY(0);
		}
		if (key == KeyEvent.VK_SPACE) {
			player.shoot(new Bullet(player.getPosX(),player.getPosY(),player.getDirection(),0,sscTank));
		}
		if (key == KeyEvent.VK_C) {
		}
		if (key == KeyEvent.VK_V) {
			stage.spawnItems(new Item( 210, 210, 1,sscTank));
		}
		
	}	

}
