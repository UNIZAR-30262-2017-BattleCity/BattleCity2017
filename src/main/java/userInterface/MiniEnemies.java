package userInterface;

import java.awt.Graphics;

import application.Properties;
import gameController.ImageControl;

public class MiniEnemies {
	
	public static final int size = Properties.WIDTH_ELEMENT_STAGE/2;
	public static final int delta = (int) (Properties.WIDTH*0.03);
	private int x,y;
	
	public MiniEnemies(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g){
		g.drawImage(ImageControl.getEnemyMini(Properties.SSTANK), x, y, size, size, null);
	}
	
}
