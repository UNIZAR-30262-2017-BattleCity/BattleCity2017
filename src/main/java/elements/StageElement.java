package elements;

import java.awt.Graphics;
import java.awt.Rectangle;


public interface StageElement {
	
	public void updateDraw();	
	public void draw(Graphics g);
	public Rectangle getBounds(int width, int heigth);
	public boolean isActive();
}
