package elements;

import java.awt.Graphics;

import gameController.SpriteSheetControl;

public class Maze implements StageElement{
	
	private double posX;
	private double posY;
	private SpriteSheetControl ssc;
	private Stage stage;
			
	public Maze(Stage stage, int level, SpriteSheetControl ssc) {	
		this.stage = stage;
		this.ssc = ssc;
		loadMaze(level);
	}
		
	public int[][] createMaze(int level){
		
		int[][] matriz = null;
		
		switch (level) {//1 ladrillo 2 medio ladrillo 3 iron 4 medio iron
		case 1:			
			int[][] m= {
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
			matriz = m;
			break;

		case 2:
			stage.spawnElements(new Obstacle(2, 3, 1, ssc));
			stage.spawnElements(new Obstacle(2, 13, 2, ssc));
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		
		
		
		return matriz;
	}
	
	public void loadMaze(int level){
    	int[][] m = createMaze(level);
    	int brick = 1;
    	
    	for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == brick) {
					stage.spawnElements(new Wall(i,j,brick,ssc));
				}
                if (m[i][j] == 3) {
					stage.spawnElements(new Wall(i,j,3,ssc));
				}
                if (m[i][j] == 5) {
					stage.spawnElements(new Obstacle(i,j,1,ssc));
				}
            }
        }    	
    	
    }
	
	public void updateDraw() {
		
	}
	
	public void draw(Graphics g) {
		
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}	
	
	public void setInitPos(int col, int row){
    }

}
