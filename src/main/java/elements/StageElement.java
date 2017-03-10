package elements;

import java.awt.Graphics;

import application.Properties;

public interface StageElement {

	public int k = Properties.SIZE_SQUARE_STAGE;
	public int x = Properties.X_INIT_STAGE;
	public int y = Properties.Y_INIT_STAGE;
	
	public void updateDraw();	
	public void draw(Graphics g);
	
	public double getPosX();
	public double getPosY();
	
	public void setInitPos(int col, int row);
}
