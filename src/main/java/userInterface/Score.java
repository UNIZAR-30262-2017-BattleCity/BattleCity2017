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

	private int finalScoreP1, finalScoreP2;
	private int paintScore,paintScoreP1,paintScoreP2;
	private int yPaintScore;
	private final int xImgEnemies = Properties.WIDTH/2;
	private int numEnemy;
	private int[] typeEnemyP1, typeEnemyP2;
	private int timeToNext;
	private boolean nextEnemy;
	private boolean scoreFinished;
	private boolean nextEnemyP1;
	private boolean nextEnemyP2;
	private boolean stopScoreP1, stopScoreP2;
	private boolean totalScore;
	
	public Score(){
		yPaintScore = (int)(Properties.HEIGHT*.35);
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
			paintScoreP1 = 0;
			typeEnemyP1 = typesEnemy(StageControl.getPlayers()[0].getEnemyType(),1);
			finalScoreP1 = finanScore(typeEnemyP1);
        	g.drawString("Player 1", (int)(Properties.WIDTH*.15), (int)(Properties.HEIGHT*.3));        
        }
        
        if (gC.isPlayer2()) {
			paintScoreP2 = 0;
			typeEnemyP2 = typesEnemy(StageControl.getPlayers()[1].getEnemyType(),2);
			finalScoreP2 = finanScore(typeEnemyP2);
			g.drawString("Player 2", (int)(Properties.WIDTH*.65), (int)(Properties.HEIGHT*.3));        
        }
	}
	
	public void draw(Graphics g, GameControl gC) { 
        
		g.setColor(Color.black);
		g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, 22));        
        
		if (gC.isPlayer1()) {
			g.fillRect(0, yPaintScore, xImgEnemies-30, 30);
			g.setColor(Color.gray);
	        g.drawString(paintScoreP1+"00", (int)(Properties.WIDTH*.31), yPaintScore+26);
            
	        if (totalScore) {
            	g.drawString(finalScoreP1+"00", (int)(Properties.WIDTH*.3), yPaintScore+ (int)(Properties.HEIGHT*.15));
            }
		}
		
		if (gC.isPlayer2()) {
			g.setColor(Color.black);
			g.fillRect((int)(Properties.WIDTH*.65), yPaintScore, Properties.WIDTH, 30);
			g.setColor(Color.gray);
        	g.drawString(paintScoreP2+"00", (int)(Properties.WIDTH*.65), yPaintScore+26);
        	
            if (totalScore) {
            	g.drawString(finalScoreP2+"00", (int)(Properties.WIDTH*.65), yPaintScore+ (int)(Properties.HEIGHT*.15));
            }
		}
		
        g.drawImage(img(numEnemy),xImgEnemies , yPaintScore,Properties.SIZE_SQUARE,Properties.SIZE_SQUARE, null);
        
        if (totalScore) {
			g.drawLine((int)(Properties.WIDTH*.10), yPaintScore + (int)(Properties.HEIGHT*.10),(int)(Properties.WIDTH*.90), yPaintScore + (int)(Properties.HEIGHT*.10));
			g.drawString("Total", (int)(Properties.WIDTH*.10), yPaintScore+ (int)(Properties.HEIGHT*.15));
        }
        
	}
	
	public void updateDraw(GameControl gC){

		if (next(30)) {
			if (gC.isPlayer1()) {
				int s = updateScore(typeEnemyP1[numEnemy-1]);				
				if (s > 0 && !stopScoreP1) {
					paintScoreP1 = s;
				}else{
					nextEnemyP1 = true;
					if (numEnemy<4) {
						paintScoreP1 = 0;
					}
					if (numEnemy==4) {
						stopScoreP1 = true;
					}
				}
			}else{
				nextEnemyP1 = true;
				stopScoreP1 = true;
			}

			if (gC.isPlayer2()) {
				int s = updateScore(typeEnemyP2[numEnemy-1]);
				if (s > 0 && !stopScoreP2) {
					paintScoreP2 = s;			
				}else{
					nextEnemyP2 = true;
					if (numEnemy<4) {
						paintScoreP2 = 0;
					}
					if (numEnemy==4) {
						stopScoreP2 = true;
					}
				}
			}else{
				nextEnemyP2 = true;
				stopScoreP2 = true;
			}

			if (nextEnemyP1 && nextEnemyP2 ) {
				nextEnemy = true;
				nextEnemyP1 = false;
				nextEnemyP2 = false;
			}
			
			if (stopScoreP1 && stopScoreP2 ) {
				totalScore = true;
			}

			if (nextEnemy) {
				if (numEnemy==4) {
					
				}else{
					numEnemy++;
					yPaintScore = yPaintScore + (int)(Properties.HEIGHT*.10);
				}
				nextEnemy=false;
			}
		}		
	}
	
	public int tmpScore(int typeEnemy){
		
		int tmpScore = 0;
		switch (numEnemy) {		
		case 1:
			tmpScore = typeEnemy;
			break;
		case 2:
			tmpScore = typeEnemy * 2;
			break;
		case 3:
			tmpScore = typeEnemy * 3;
			break;
		case 4:
			tmpScore = typeEnemy * 4;
			break;
		}
		
		return tmpScore;
	}
	
	public int updateScore(int typeEnemy){

		int tmpScore = tmpScore(typeEnemy);

		if (paintScore<tmpScore) {
			paintScore++;
		}else{
			paintScore = 0;
		}
		return paintScore;
	}
	
	public int finanScore(int[] typeEnemy){		
		int a = 0;
		for (int i = 0; i < typeEnemy.length; i++) {
			if (typeEnemy[i]!=0) {
				a = a + (typeEnemy[i] * (i+1));
			}
		}
		return a;
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
			return ImageControl.getEnemy4RedUp();
		default:
			return ImageControl.getEnemy1Up();
		}
	}
	
	public int[] typesEnemy(ArrayList<Integer> list, int nplayer){ //devuelve cuantos mato de cada tipo de enemigo
		int[] sco = new int[4];
		for (Integer type : list) {	
			switch (type) {
			case 1:
				sco[0]++;
				System.out.println("Player: "+nplayer + " del primero: "+ sco[0]);
				break;
			case 2:
				sco[1]++;
				System.out.println("Player: "+nplayer + " del dos: "+ sco[1]);
				break;
			case 3:
				sco[2]++;
				System.out.println("Player: "+nplayer + " del tres: "+ sco[2]);
				break;
			case 4:
				sco[3]++;
				System.out.println("Player: "+nplayer + " del cuatro: "+ sco[3]);
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
			if (totalScore) {
				scoreFinished = true;		
			}
			return true;
		}
	}

	public boolean isScoreFinished() {
		return scoreFinished;
	}

	public void setScoreFinished(boolean scoreFinished) {
		this.scoreFinished = scoreFinished;
	}	
	
}
