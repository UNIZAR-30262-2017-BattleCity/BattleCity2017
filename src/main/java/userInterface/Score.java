package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import application.Properties;
import gameController.GameControl;
import gameController.ImageControl;
import gameController.StageControl;

public class Score {

	private int tmpScore, paintScore;
	private int yPaintScore;
	private final int xImgEnemies = Properties.WIDTH/2;
	private int numEnemy;
	private ArrayList<Integer> list;
	int[] typeEnemy;
	
	public Score(){
		yPaintScore = 400;
		paintScore = 000;
		numEnemy = 1;
	}
	
	
	public void initDraw(Graphics g,GameControl gC){
		
		g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH+20, Properties.HEIGHT);
        
        g.setColor(Color.gray);
        g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, 22)); 
        
    	g.drawString("Nivel " + gC.getLevel(), (int)(Properties.WIDTH*.4), (int)(Properties.HEIGHT*.2));
		 
        list = null;
		
        if (gC.isPlayer1()) {
			list = StageControl.getPlayers()[0].getEnemyType();
        	g.drawString("Player 1", (int)(Properties.WIDTH*.15), (int)(Properties.HEIGHT*.3));        
        }        
        
        if (gC.isPlayer2()) {
			list = StageControl.getPlayers()[1].getEnemyType();
			g.drawString("Player 2", (int)(Properties.WIDTH*.65), (int)(Properties.HEIGHT*.3));        
        }
	}
	
	public void draw(Graphics g, GameControl gC) {
       
        
		 if (gC.isPlayer1()) {
				typeEnemy = typeEnemy(StageControl.getPlayers()[0].getEnemyType());
	        	g.drawString("" + paintScore, (int)(Properties.WIDTH*.4), yPaintScore);
	        }
        
        g.drawImage(img(numEnemy),xImgEnemies , 350,Properties.SIZE_SQUARE,Properties.SIZE_SQUARE, null);        
	
	}
	
	public void updateDraw(){
		
		tmpScore = typeEnemy[numEnemy-1] * 100;
		
		if (paintScore<tmpScore) {
			paintScore =  paintScore + 100;
		}else{
			paintScore = 000;
		}
	}
	
	public BufferedImage img(int type){
		switch (type) {
		case 1:
			return ImageControl.getEnemy1Up();
		case 2:
			return ImageControl.getEnemy2Up();
		case 3:
			return ImageControl.getEnemy3Up();
		case 4:
			return ImageControl.getEnemy4Up();
		default:
			return ImageControl.getEnemy1Up();
		}
	}
	
	public int[] typeEnemy(ArrayList<Integer> list){
		int[] sco = new int[4];
		
		for (Integer type : list) {        	
			switch (type) {
			case 1:
				sco[0]++;
				break;
			case 2:
				sco[1]++;
				break;
			case 3:
				sco[2]++;
				break;
			case 4:
				sco[3]++; 
				break;
			}
		}		
		return sco;
	}
	
}
