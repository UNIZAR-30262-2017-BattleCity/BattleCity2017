package gameController;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import elements.Bullet;


public class GameControl extends Canvas implements Runnable, KeyListener{

	
	private static final long serialVersionUID = 1L;
	private BufferedImage background = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
	private boolean running;
	private Thread thread;
	private SpriteSheetControl sscTank;
	private PlayerControl player;
	
	public GameControl(JFrame jf){
		requestFocus();
		sscTank = new SpriteSheetControl("/Sprites/SpriteSheet.png", 16, 16);
		player = new PlayerControl(100, 100, 5, sscTank);
		jf.addKeyListener(this);	
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
		double amountIstant = 60.0;
		double ns = 1000000000/amountIstant;
		double delta=0;
		double frames=0;
		double updates=0;
		long timer = System.currentTimeMillis();
		int s=1000;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastime)/ns;
			lastime = now;
			if (delta >= 1) {
				updateDraw();
				updates++;
				delta--;				
			}			
			render();
			frames++;			
			if (System.currentTimeMillis() - timer > s) {
				timer += s;
				System.out.println("Updates: "+ updates + " FPS: " + frames);
				updates=0;
				frames=0;				
			}			
		}
		stop();		
	}
	
	public void updateDraw(){
		player.updateDraw();
		player.updateDrawBullet();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}		
		Graphics g = bs.getDrawGraphics();	
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		player.draw(g);
		player.drawBullet(g);
		g.dispose();
		bs.show();
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		player.setVelX(0);
		player.setVelY(0);
	}	
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			player.setVelY(-1);
			player.setVelX(0);
			player.setDirection(0);
		}
		if (key == KeyEvent.VK_DOWN) {
			player.setVelY(1);
			player.setVelX(0);
			player.setDirection(1);
		}
		if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(1);
			player.setVelY(0);
			player.setDirection(3);
		}
		if (key == KeyEvent.VK_LEFT) {
			player.setVelX(-1);
			player.setVelY(0);
			player.setDirection(2);
		}
		if (key == KeyEvent.VK_SPACE) {
			player.shoot(new Bullet(player.getPosX(),player.getPosY(),player.getDirection(),0,sscTank));
		}
		
	}
	

}
