package userInterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Cursor {
	
	private static final BufferedImage IMG_CURSOR = Properties.SSCTANK.getPlayer1Right();
	private int x, y, initY, finalY;

	public Cursor(){
		cursorMenu();
	}
	
	public void cursorMenu(){
		initY = Properties.Y_INIT_CURSOR_M;
		finalY = Properties.Y_FINAL_CURSOR_M;
		x = Properties.X_CURSOR_M;
		y = initY;
	}
	
	public void cursorConfig(){
		initY = Properties.Y_INIT_CURSOR_C;
		finalY = Properties.Y_FINAL_CURSOR_C;
		x = Properties.X_CURSOR_C;
		y = initY;
	}
	
	public void cursorControls(){
		initY = Properties.Y_INIT_CURSOR_CT;
		finalY = Properties.Y_FINAL_CURSOR_CT;
		x = Properties.X_CURSOR_CT;
		y = initY;
	}
	
	public void updateDraw(){
		if (y>finalY) {
			y = initY;
		}
		if (y<initY) {
			y = finalY;
		}
	}
	
	public void draw(Graphics g) {        
        updateDraw();
        g.drawImage(IMG_CURSOR, x, y, Properties.WIDTH_ELEMENT_STAGE, Properties.HEIGH_ELEMENT_STAGE, null);
    }

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
