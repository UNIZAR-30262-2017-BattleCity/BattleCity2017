package elements;

import java.awt.Graphics;
import java.awt.Rectangle;

import gameController.StageControl;


public interface StageElement {
	
	public void updateDraw();	
	public void draw(Graphics g);
	public Rectangle getBounds(int width, int heigth);
	public boolean isActive();
	public void setActive(boolean a);
	public StageControl getStage();
	public int getWidth();
	public int getHeigth();
	public int getType();
	public void setType(int type);
	public double getPosX();
	public double getPosY();
}
