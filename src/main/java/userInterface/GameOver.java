package userInterface;

import java.awt.Graphics;

import application.Properties;
import gameController.ImageControl;

public class GameOver {

	private double y = Properties.HEIGHT_STAGE-20;
	
	public GameOver(){
	}
	
	public void updateDraw(){
		
		if (y==Properties.HEIGHT/2) {
			//TODO continue to score
		}else{
			y--;
		}
	}

	public void draw(Graphics g){
		g.drawImage(ImageControl.getGameOver(), Properties.WIDTH/2-90, (int) y , 120, 35, null);
	}
}
