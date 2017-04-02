package userInterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Cursor {
	
	private static final BufferedImage IMG_CURSOR = ImageControl.getPlayer1Right();
	private int x, y, initY, finalY, initX, finalX;
	private boolean moveX;

	public Cursor(){
		cursorMenu();
		moveX = false;
	}
	
	public void cursorMenu(){
		initY = Properties.Y_INIT_CURSOR_M;
		finalY = Properties.Y_FINAL_CURSOR_M;
		x = Properties.X_CURSOR_M;
		y = initY;
	}
	
	public void cursorConfigV(){
		initY = Properties.Y_INIT_CURSOR_C;
		finalY = Properties.Y_FINAL_CURSOR_C;
		x = Properties.X_CURSOR_C;
		y = initY;
	}
	
	public void cursorConfigH(){
		x = Properties.X_INIT_CURSOR_C;
	}
	
	public void cursorControlsV(){
		initY = Properties.Y_INIT_CURSOR_CT;
		finalY = Properties.Y_FINAL_CURSOR_CT;
		x = Properties.X_CURSOR_CT;
		y = initY;
	}
	
	public void cursorControlsH(){
		initX = Properties.X_INIT_CURSOR_CT;
		finalX = Properties.X_FINAL_CURSOR_CT;
		x = initX;
	}
	
	public void updateDraw(){
		if (y>finalY) {
			y = initY;
		}
		if (y<initY) {
			y = finalY;
		}
	}
	
	public void updateDrawH(){
		if (x>finalX) {
			x = initX;
		}
		if (x<initX) {
			x = finalX;
		}
	}
	
	public void draw(Graphics g) {        
        if (moveX) updateDrawH();
        else updateDraw();
        
        g.drawImage(IMG_CURSOR, x, y, Properties.WIDTH_ELEMENT_STAGE, Properties.HEIGH_ELEMENT_STAGE, null);
    }

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isMoveX() {
		return moveX;
	}

	public void setMoveX(boolean moveX) {
		this.moveX = moveX;
	}
	
}
