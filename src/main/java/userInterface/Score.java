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

	private int tmpScore, paintScore,paintScoreP1,paintScoreP2;
	private int yPaintScore;
	private final int xImgEnemies = Properties.WIDTH/2;
	private int numEnemy;
	int[] typeEnemyP1, typeEnemyP2;
	private int timeToNext;
	private boolean nextEnemy;
	
	public Score(){
		yPaintScore = (int)(Properties.HEIGHT*.4);
		numEnemy = 1;
		timeToNext = 0;
		paintScore = 0;
	}
	
	
	public void initDraw(Graphics g,GameControl gC){
		
		g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH+20, Properties.HEIGHT);
        
        g.setColor(Color.gray);
        g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, 22)); 
        
    	g.drawString("Nivel " + gC.getLevel(), (int)(Properties.WIDTH*.4), (int)(Properties.HEIGHT*.2));		
		
        if (gC.isPlayer1()) {
			paintScoreP1 = 000;
			typeEnemyP1 = typeEnemy(StageControl.getPlayers()[0].getEnemyType());
        	g.drawString("Player 1", (int)(Properties.WIDTH*.15), (int)(Properties.HEIGHT*.3));        
        }        
        
        if (gC.isPlayer2()) {	
			paintScoreP2 = 000;
			typeEnemyP2 = typeEnemy(StageControl.getPlayers()[1].getEnemyType());
			g.drawString("Player 2", (int)(Properties.WIDTH*.65), (int)(Properties.HEIGHT*.3));        
        }
	}
	
	public void draw(Graphics g, GameControl gC) {       
        
		if (gC.isPlayer1()) {
	        	g.drawString(""+ paintScoreP1, (int)(Properties.WIDTH*.15), yPaintScore);
	    }
		
		if (gC.isPlayer2()) {
        	g.drawString(""+ paintScoreP2, (int)(Properties.WIDTH*.65), yPaintScore);
		}
        
        g.drawImage(img(numEnemy),xImgEnemies , yPaintScore,Properties.SIZE_SQUARE,Properties.SIZE_SQUARE, null);
        
	}
	
	public void updateDraw(GameControl gC){

		if (next(60)) {
			if (gC.isPlayer1()) {
				int s = updateScore(typeEnemyP1);
				if (s!=000) {
					paintScoreP1 = s;
				}
			}

			if (gC.isPlayer2()) {
				int s = updateScore(typeEnemyP2);
				if (s!=000) {
					paintScoreP2 = s;
				}

			}
		}
		
        if (updateScore(typeEnemyP1)==000 && updateScore(typeEnemyP1)==000 ) {
			nextEnemy = true;
		}
        
        if (nextEnemy) {
			numEnemy++;
			yPaintScore = yPaintScore + (int)(Properties.WIDTH*.10);
			nextEnemy=false;
			
		}
		
	}
	
	
	public int updateScore(int[] typeEnemy){
		tmpScore = typeEnemy[numEnemy-1] * 100;//cuantos mato del primero por 100		
		if (paintScore<tmpScore) {
			paintScore =  paintScore + 100;
		}else{
			paintScore = 000;
		}
		
		return paintScore;
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
	
	public int[] typeEnemy(ArrayList<Integer> list){ //devuelve cuantos mato de cada tipo de enemigo
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
	
	public boolean next(int time){
		if(timeToNext<time){
			timeToNext++;
			return false;
		}else{
			timeToNext = 0;
			return true;
		}
	}
	
}
