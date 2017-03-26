package gameController;

import java.util.LinkedList;

import elements.Eagle;
import elements.Obstacle;
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
					{0,1,0,1,0,0,13,0,0,1,1,1,0},
					};			
			matriz = m2;
			break;
		case 3:
			int[][] m3= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,3,1,0,1,0,1,0},
					{0,1,0,1,0,2,0,2,0,1,0,1,0},
					{0,2,0,2,0,0,0,0,0,2,0,2,0},
					{0,0,0,0,0,1,0,1,0,0,0,0,0},
					{2,0,1,1,0,0,0,0,0,1,1,0,2},
					{4,0,0,0,0,1,0,1,0,0,0,0,4},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,0,0,0,0,1,0,1,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					};			
			matriz = m3;
			break;
		case 4:
			int[][] m4= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,3,1,0,1,0,1,0},
					{0,1,0,1,0,2,0,2,0,1,0,1,0},
					{0,2,0,2,0,0,0,0,0,2,0,2,0},
					{0,0,0,0,0,1,0,1,0,0,0,0,0},
					{2,0,1,1,0,0,0,0,0,1,1,0,2},
					{4,0,0,0,0,1,0,1,0,0,0,0,4},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,0,0,0,0,1,0,1,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					};			
			matriz = m4;
			break;
		case 5:
			int[][] m5= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,3,1,0,1,0,1,0},
					{0,1,0,1,0,2,0,2,0,1,0,1,0},
					{0,2,0,2,0,0,0,0,0,2,0,2,0},
					{0,0,0,0,0,1,0,1,0,0,0,0,0},
					{2,0,1,1,0,0,0,0,0,1,1,0,2},
					{4,0,0,0,0,1,0,1,0,0,0,0,4},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,0,0,0,0,1,0,1,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					};			
			matriz = m5;
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
	
	public void loadMaze(int level){
    	int[][] m = createMaze(level);
    	    	
    	for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                	for (int j2 = 0; j2 < 4; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}					
				}
                if (m[i][j] == 2) {
                	for (int j2 = 0; j2 < 2; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
				}
                if (m[i][j] == 3) {
                	for (int j2 = 2; j2 < 4; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
				}
                if (m[i][j] == 4) {
                	for (int j2 = 0; j2 < 4; j2 = j2+2) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
				}
                if (m[i][j] == 5) {
                	for (int j2 = 1; j2 < 4; j2 = j2+2) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
				}
                if (m[i][j] == 6) {
					stageControl.spawnWalls(new Wall(i,j,0,2,stageControl, false));
				}
                if (m[i][j] == 7) {
					stageControl.spawnWalls(new Wall(i,j,0,3,stageControl, false));
				}
                if (m[i][j] == 8) {
		    		stageControl.spawnWalls(new Wall(i,j,2,3,stageControl, false));
				}
                if (m[i][j] == 9) {
		    		stageControl.spawnWalls(new Wall(i,j,0,4,stageControl, false));
				}
                if (m[i][j] == 10) {
		    		stageControl.spawnWalls(new Wall(i,j,1,4,stageControl, false));
                }
                if (m[i][j] == 11) {
					stageControl.spawnElements(new Obstacle(i,j,1,stageControl));
                }
                if (m[i][j] == 12) {
					stageControl.spawnElements(new Obstacle(i,j,2,stageControl));
                }
                if (m[i][j] == 13) {
					stageControl.spawnElements(new Eagle(i,j,stageControl));
                }
                if (m[i][j] == 14) {
                	for (int j2 = 0; j2 < 2; j2++) {
						stageControl.spawnWalls(new Wall(i-.5,j,j2,1,stageControl, false)); 
					}
                }
            }
        }
    	
    }
	
}
