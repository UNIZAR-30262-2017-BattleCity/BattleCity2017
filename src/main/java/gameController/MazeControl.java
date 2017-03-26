package gameController;

import java.util.LinkedList;

import application.Properties;
import elements.Eagle;
import elements.Obstacle;
import elements.Player;
import elements.Wall;

public class MazeControl {

	private static StageControl stageControl;
			
	public MazeControl(StageControl stageControl) {	
		MazeControl.stageControl = stageControl;
	}
		
	public int[][] createMaze(int level){
		
		/*
		 * 1 - Brick wall.
		 * 2 - Half horizontal UP brick wall.
		 * 3 - Half horizontal DOWN brick wall. 
		 * 4 - Half vertical LEFT brick wall.
		 * 5 - Half vertical RIGHT brick wall.
		 * 6 - Iron wall.
		 * 7 - Half horizontal UP iron wall.
		 * 8 - Half horizontal DOWN iron wall.
		 * 9 - Half vertical LEFT iron wall.
		 * 10 - Half vertical RIGHT iron wall.
		 * 11 - Forest.
		 * 12 - Water.
		 * 13 - Eagle.
		 */
		
		int[][] matriz = null;
		
		switch (level) {
		case 1:
			int[][] m1= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,6,1,0,1,0,1,0},
					{0,1,0,1,0,2,0,2,0,1,0,1,0},
					{0,2,0,2,0,3,0,3,0,2,0,2,0},
					{3,0,3,3,0,2,0,2,0,3,3,0,3},
					{7,0,2,2,0,3,0,3,0,2,2,0,7},
					{0,3,0,3,0,1,1,1,0,3,0,3,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,2,0,2,0,1,0,1,0},
					{0,1,0,1,0,0,0,0,0,1,0,1,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};
			matriz = m1;			
			break;

		case 2:
			int[][] m2= {
					{0,0,0,6,0,0,0,6,0,0,0,0,0},
					{0,1,0,6,0,0,0,1,0,1,0,1,0},
					{0,1,0,0,0,0,1,1,0,1,6,1,0},
					{0,0,0,1,0,0,0,0,0,6,0,0,0},
					{11,0,0,1,0,0,6,0,0,1,11,1,6},
					{11,11,0,0,0,1,0,0,6,0,11,0,0},
					{0,1,1,1,11,11,11,6,0,0,11,1,0},
					{0,0,0,6,11,1,0,1,0,1,0,1,0},
					{6,1,0,6,0,1,0,1,0,0,0,1,0},
					{0,1,0,1,0,1,1,1,0,1,6,1,0},
					{0,1,0,1,0,1,1,1,0,0,0,0,0},
					{0,1,0,0,0,0,0,0,0,1,0,1,0},
					{0,1,0,1,15,0,13,0,0,1,1,1,0},
					};			
			matriz = m2;
			break;
		case 3:
			int[][] m3= {
					{0,0,0,0,1,0,0,0,1,0,0,0,0},
					{0,11,11,11,1,0,0,0,0,0,8,8,8},
					{1,11,11,11,0,0,0,0,0,0,0,0,0},
					{11,11,11,11,0,0,0,1,0,1,1,1,4},
					{11,11,11,11,1,1,1,2,0,1,0,5,0},
					{11,11,11,11,0,0,1,0,0,0,0,5,0},
					{0,11,0,0,0,0,6,6,6,0,0,11,0},
					{0,3,0,3,0,0,0,0,0,11,11,11,11},
					{1,4,5,1,4,5,2,2,2,11,11,11,11},
					{0,0,0,0,0,1,0,3,3,11,11,11,11},
					{1,0,0,9,0,0,0,2,2,11,11,11,0},
					{1,1,0,9,0,0,0,0,0,11,11,11,0},
					{6,1,1,0,0,0,13,0,0,1,0,0,0},
					};			
			matriz = m3;
			break;
		case 4:
			int[][] m4= {
					{0,11,11,0,0,0,0,0,0,0,0,11,0},
					{11,11,0,0,3,1,1,3,3,0,0,0,11},
					{11,0,0,5,1,1,1,1,1,1,3,0,7},
					{7,0,0,1,1,1,1,1,1,1,1,4,0},
					{0,0,5,2,0,0,0,2,1,1,0,4,0},
					{12,0,5,0,9,0,9,0,1,4,0,0,0},
					{0,0,1,0,3,3,0,0,1,4,0,12,12},
					{0,0,1,1,1,1,1,1,1,1,0,0,0},
					{0,5,1,1,1,1,1,1,1,1,4,0,0},
					{0,2,2,1,1,1,1,1,1,2,2,0,0},
					{0,1,1,3,2,1,1,2,3,1,1,0,11},
					{11,0,2,2,0,0,0,0,2,2,0,11,11},
					{6,11,0,0,0,0,13,0,0,0,11,11,6},
					};			
			matriz = m4;
			break;
		case 5:
			int[][] m5= {
					{0,0,0,0,1,1,0,0,0,0,0,0,0},
					{8,0,3,0,1,0,0,0,7,7,6,0,0},
					{6,0,1,0,0,0,1,0,0,0,0,0,0},
					{1,0,1,1,1,0,1,1,0,12,12,0,12},
					{2,0,0,0,2,0,0,0,0,12,0,0,0},
					{0,0,3,0,12,12,0,12,12,12,0,1,1},
					{1,1,0,0,12,1,0,1,4,0,0,0,0},
					{0,0,0,0,12,0,0,0,0,0,10,9,0},
					{12,12,12,0,12,0,6,0,1,0,10,0,0},
					{0,0,0,3,3,0,0,0,0,0,10,1,1},
					{0,0,0,0,1,2,2,2,1,3,0,0,0},
					{1,1,2,0,0,0,0,0,0,2,1,0,0},
					{2,0,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m5;
			break;
		case 6:
			int[][] m6= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m6;
			break;
		case 7:
			int[][] m7= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m7;
			break;
		case 8:
			int[][] m8= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m8;
			break;
		case 9:
			int[][] m9= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m9;
			break;
		case 10:
			int[][] m10= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m10;
			break;
		}
		
		return matriz;
	}	
		
	public void loadIronWall(){
    	stageControl.spawnElements(new Wall(11.5,5.5,0,3,stageControl,true));
    	stageControl.spawnElements(new Wall(11.5,6.5,0,3,stageControl,true));
    	stageControl.spawnElements(new Wall(12,5.5,0,4,stageControl,true));
    	stageControl.spawnElements(new Wall(12,7,0,4,stageControl,true));
	}
	
	public LinkedList<Wall> loadEagleWall(){
		
		LinkedList<Wall> listEagleWall = new LinkedList<>();
		
		listEagleWall.add(new Wall(12,5,1,1,stageControl, false));
		
		listEagleWall.add(new Wall(12,5,1,1,stageControl, false));
		listEagleWall.add(new Wall(12,5,3,1,stageControl, false));
		
		listEagleWall.add(new Wall(11,5,3,1,stageControl, false));

		listEagleWall.add(new Wall(11,6,2,1,stageControl, false));
		listEagleWall.add(new Wall(11,6,3,1,stageControl, false));

		listEagleWall.add(new Wall(11,7,2,1,stageControl, false));

		listEagleWall.add(new Wall(12,7,0,1,stageControl, false));
		listEagleWall.add(new Wall(12,7,2,1,stageControl, false));
		
		for (Wall wall : listEagleWall) {
			stageControl.spawnElements(wall);
		}
		
		return listEagleWall;		

	}
	
	public void loadMaze(int level, boolean player1, boolean player2){
    	int[][] m = createMaze(level);
    	
    	if (player1) {
    		m[12][4] = 15;
		}
    	if (player2) {
			m[12][8] = 16;
		}
    	    	
    	for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) {
            	
            	switch (m[i][j]) {
				case 1:
					for (int j2 = 0; j2 < 4; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
					break;
						
				case 2:
					for (int j2 = 0; j2 < 2; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
					break;
					
				case 3:
					for (int j2 = 2; j2 < 4; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
					break;

				case 4:
					for (int j2 = 0; j2 < 4; j2 = j2+2) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
					break;
					
				case 5:
					for (int j2 = 1; j2 < 4; j2 = j2+2) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
					break;

				case 6:
					stageControl.spawnWalls(new Wall(i,j,0,2,stageControl, false));
					break;
					
				case 7:
					stageControl.spawnWalls(new Wall(i,j,0,3,stageControl, false));
					break;

				case 8:
					stageControl.spawnWalls(new Wall(i+.5,j,0,3,stageControl, false));
					break;
					
				case 9:
					stageControl.spawnWalls(new Wall(i,j,0,4,stageControl, false));
					break;

				case 10:
					stageControl.spawnWalls(new Wall(i,j+.5,0,4,stageControl, false));
					break;
					
				case 11:
					stageControl.spawnForest(new Obstacle(i,j,1,stageControl));
					break;

				case 12:
					stageControl.spawnStaticElements(new Obstacle(i,j,2,stageControl));
					break;
					
				case 13:
                	Eagle e = new Eagle(i,j,stageControl);
					stageControl.spawnStaticElements(e);
					stageControl.setEagle(e);
					break;

				case 14:
                	for (int j2 = 0; j2 < 2; j2++) {
						stageControl.spawnWalls(new Wall(i-.5,j,j2,1,stageControl, false)); 
					}
					break;
					
				case 15:			
					StageControl.players[0] = new Player(i+1, j+1, Properties.INIT_LIFES,1, stageControl);
					stageControl.spawnElements(StageControl.players[0]);
					break;

				case 16:
					StageControl.players[1] = new Player(i+1, j+1, Properties.INIT_LIFES,2, stageControl);
					stageControl.spawnElements(StageControl.players[1]);
					break;
				}

            }
        }
    	
    }
	
}
