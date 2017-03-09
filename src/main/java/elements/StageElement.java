package elements;

import java.awt.Graphics;

public interface StageElement {

	public void updateDraw();	
	public void draw(Graphics g);
	
	public double getPosX();
	public double getPosY();
}
