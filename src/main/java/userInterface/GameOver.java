package userInterface;

import java.awt.Graphics;

import application.Properties;
import gameController.ImageControl;

public class GameOver {

	private double y = Properties.Y_GO;
	
	public GameOver(){
	}
	
	public void updateDraw(){
		
		if (y<Properties.HEIGHT/2) {
			//TODO continue to score
		}else{
			y--;
		}
	}

	public void draw(Graphics g){
		g.drawImage(ImageControl.getGameOver(), 
				Properties.X_GO, (int) y, 
				Properties.GO_WIDTH, 
				Properties.GO_HEIGHT, null);
	}
}
