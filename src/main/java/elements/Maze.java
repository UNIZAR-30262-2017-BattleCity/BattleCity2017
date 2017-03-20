package elements;

public class Maze {

	private Stage stage;
			
	public Maze(Stage stage) {	
		this.stage = stage;
	}
		
	public int[][] createMaze(int level){
		
		int[][] matriz = null;
		
		switch (level) {//1 brick 2 half brick 3 iron 4 half iron 
		case 1:	//5 forest 6 water 7 eagle 8 block half brick & half Iron 9 brick begin before 10,11,12,13 eagle wall
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
					{0,1,0,1,0,11,12,13,0,1,0,1,0},
					{0,0,0,0,0,10,7,14,0,0,0,0,0},
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
					{0,0,0,0,0,9,0,9,0,0,0,0,0},
					{8,0,9,9,0,0,0,0,0,9,9,0,8},
					{0,1,0,0,0,9,1,9,0,0,0,1,0},
					{0,1,0,1,0,9,0,9,0,1,0,1,0},
					{0,1,0,1,0,9,0,9,0,1,0,1,0},
					{0,1,0,1,0,11,12,13,0,1,0,1,0},
					{0,0,0,0,0,10,7,14,0,0,0,0,0},
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
    	stage.spawnElements(new Wall(11.5,5.5,0,3,stage,true));
    	stage.spawnElements(new Wall(11.5,6.5,0,3,stage,true));
    	stage.spawnElements(new Wall(12,5.5,0,4,stage,true));
    	stage.spawnElements(new Wall(12,7,0,4,stage,true));
	}
	
	public void loadMaze(int level){
    	int[][] m = createMaze(level);
    	
    	for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                	for (int j2 = 0; j2 < 4; j2++) {
						stage.spawnElements(new Wall(i,j,j2,1,stage, false)); 
					}					
				}
                if (m[i][j] == 2) {
                	for (int j2 = 0; j2 < 2; j2++) {
						stage.spawnElements(new Wall(i,j,j2,1,stage, false)); 
					}
				}
                if (m[i][j] == 3) {                
					stage.spawnElements(new Wall(i,j,0,2,stage, false)); 
					
				}
                if (m[i][j] == 4) {
					stage.spawnElements(new Wall(i,j,0,3,stage, false));
				}
                if (m[i][j] == 5) {
					stage.spawnElements(new Obstacle(i,j,1,stage));
				}
                if (m[i][j] == 6) {
					stage.spawnElements(new Obstacle(i,j,2,stage));
				}
                if (m[i][j] == 7) {
					stage.spawnElements(new Eagle(i,j,stage));
				}
                if (m[i][j] == 8) {
                	for (int j2 = 0; j2 < 2; j2++) {
						stage.spawnElements(new Wall(i-.5,j,j2,1,stage, false)); 
					}
		    		stage.spawnElements(new Wall(i,j,0,3,stage, false));
				}
                if (m[i][j] == 9) {
                	for (int j2 = 0; j2 < 4; j2++) {
						stage.spawnElements(new Wall(i-0.5,j,j2,1,stage, false));
					}
				}
                if (m[i][j] == 10) {
                	stage.spawnElements(new Wall(i,j,1,1,stage, false));
                	stage.spawnElements(new Wall(i,j,3,1,stage, false));
				}
                if (m[i][j] == 11) {
                	stage.spawnElements(new Wall(i,j,3,1,stage, false));
				}
                if (m[i][j] == 12) {                	
                	stage.spawnElements(new Wall(i,j,2,1,stage, false));
                	stage.spawnElements(new Wall(i,j,3,1,stage, false));
				}
                if (m[i][j] == 13) {             	
                	stage.spawnElements(new Wall(i,j,2,1,stage, false));
				}
                if (m[i][j] == 14) {        	
                	stage.spawnElements(new Wall(i,j,0,1,stage, false));
                	stage.spawnElements(new Wall(i,j,2,1,stage, false));
				}
            }
        }
    	
    }
	
}
