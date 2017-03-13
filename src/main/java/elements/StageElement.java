package elements;

import java.awt.Graphics;
import java.awt.Rectangle;


public interface StageElement {
	
	public void updateDraw();	
	public void draw(Graphics g);
	public Rectangle getBounds(int width, int heigth);
	public boolean isActive();
	public void setActive(boolean a);
	public Stage getStage();
	public int getWidth();
	public int getHeigth();
}
