package elements;

import gameController.SpriteSheetControl;

public class Maze {
	
	private SpriteSheetControl ssc;
	private Stage stage;
			
	public Maze(Stage stage, SpriteSheetControl ssc) {	
		this.stage = stage;
		this.ssc = ssc;
	}
		
	public int[][] createMaze(int level){
		
		int[][] matriz = null;
		
		switch (level) {//1 brick 2 half brick 3 iron 4 half iron 
		case 1:	//5 forest 6 water 7 eagle 8 block half brick & half Iron 9 brick begin before 10 & 11 eagle wall
			int[][] m1= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,3,1,0,1,0,1,0},
					{0,1,0,1,0,2,0,2,0,1,0,1,0},
					{0,2,0,2,0,0,0,0,0,2,0,2,0},
					{0,0,0,0,0,9,0,9,0,0,0,0,0},
					{8,0,9,9,0,0,0,0,0,9,9,0,8},
					{0,1,0,0,0,9,1,9,0,0,0,1,0},
					{0,1,0,1,0,9,0,9,0,1,0,1,0},
					{0,1,0,1,0,9,0,9,0,1,0,1,0},
					{0,1,0,1,0,11,0,0,0,1,0,1,0},
					{0,0,0,0,0,10,7,0,0,0,0,0,0},
					};			
			matriz = m1;			
			break;

		case 2:
			int[][] m2= {
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
    	stage.spawnElements(new Wall(11.5,5.5,4,stage,ssc,true));
    	stage.spawnElements(new Wall(11.5,6.5,4,stage,ssc,true));
    	stage.spawnElements(new Wall(12,5.5,6,stage,ssc,true));
    	stage.spawnElements(new Wall(12,7,6,stage,ssc,true));
	}
	
	public void loadMaze(int level){
    	int[][] m = createMaze(level);
    	
    	for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
					stage.spawnElements(new Wall(i,j,1,stage,ssc, false));
				}
                if (m[i][j] == 2) {
					stage.spawnElements(new Wall(i,j,2,stage,ssc, false));
				}
                if (m[i][j] == 3) {
					stage.spawnElements(new Wall(i,j,3,stage,ssc, false));
				}
                if (m[i][j] == 4) {
					stage.spawnElements(new Wall(i,j,4,stage,ssc, false));
				}
                if (m[i][j] == 5) {
					stage.spawnElements(new Obstacle(i,j,1,stage,ssc));
				}
                if (m[i][j] == 6) {
					stage.spawnElements(new Obstacle(i,j,2,stage,ssc));
				}
                if (m[i][j] == 7) {
					stage.spawnElements(new Eagle(i,j,stage,ssc));
				}
                if (m[i][j] == 8) {
					stage.spawnElements(new Wall(i-0.5,j,2,stage,ssc, false));
		    		stage.spawnElements(new Wall(i,j,4,stage,ssc, false));
				}
                if (m[i][j] == 9) {
					stage.spawnElements(new Wall(i-0.5,j,1,stage,ssc, false));
				}
                if (m[i][j] == 10) {
                	stage.spawnElements(new Wall(i,j+.5,5,stage,ssc, false));
                	stage.spawnElements(new Wall(i,j+2,5,stage,ssc, false));
				}
                if (m[i][j] == 11) {                	
                	stage.spawnElements(new Wall(i+.5,j+.5,2,stage,ssc, false));
                	stage.spawnElements(new Wall(i+.5,j+1.5,2,stage,ssc, false));
				}
            }
        }
    	
    }
	
}
