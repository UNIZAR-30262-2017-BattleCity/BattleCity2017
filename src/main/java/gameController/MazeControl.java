package gameController;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import application.Properties;
import elements.Eagle;
import elements.Forest;
import elements.Ice;
import elements.Item;
import elements.Player;
import elements.Wall;
import elements.Water;

public class MazeControl {

	private static StageControl stageControl;
	private BufferedImage[] img = new BufferedImage[2];
	private ArrayList<Integer> mazeItemsRow, mazeItemsCol;

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
		 * 14 - Ice
		 * 15 - Player 1
		 * 16 - Player 2
		 * 17 - Gas
		 */
		
		int[][] matriz = null;
		
		switch (level) {
		case 1:
			int[][] m1= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},{0,1,0,1,0,1,0,1,0,1,0,1,0},{0,1,0,1,0,1,0,1,0,1,0,1,0},
					{0,1,0,1,0,1,6,1,0,1,0,1,0},{0,1,0,1,0,2,0,2,0,1,0,1,0},{0,2,0,2,0,3,0,3,0,2,0,2,0},
					{3,0,3,3,0,2,17,2,0,3,3,0,3},{7,0,2,2,0,3,0,3,0,2,2,0,7},{0,3,0,3,0,1,1,1,0,3,0,3,0},
					{0,1,0,1,0,1,0,1,0,1,0,1,0},{0,1,0,1,0,2,0,2,0,1,0,1,0},{0,1,0,1,0,0,0,0,0,1,0,1,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};
			matriz = m1;			
			break;

		case 2:
			int[][] m2= {
					{0,0,0,6,0,0,0,6,0,0,0,0,0},{0,1,0,6,0,0,0,1,0,1,0,1,0},{0,1,0,0,0,0,1,1,0,1,6,1,0},
					{0,0,0,1,0,17,0,0,0,6,0,0,0},{11,0,0,1,0,0,6,0,0,1,11,1,6},{11,11,0,0,0,1,0,0,6,0,11,0,0},
					{0,1,1,1,11,11,11,6,0,0,11,1,0},{0,0,0,6,11,1,0,1,0,1,0,1,0},{6,1,0,6,0,1,0,1,0,0,0,1,0},
					{0,1,0,1,0,1,1,1,0,1,6,1,0},{0,1,0,1,0,1,1,1,0,0,0,0,0},{0,1,0,0,0,0,0,0,0,1,0,1,0},
					{0,1,0,1,0,0,13,0,0,1,1,1,0},
					};			
			matriz = m2;
			break;
		case 3:
			int[][] m3= {
					{0,17,0,0,1,0,0,0,1,0,0,0,0},{0,11,11,11,1,0,0,0,0,0,8,8,8},{1,11,11,11,0,0,0,0,0,0,0,0,0},
					{11,11,11,11,0,0,0,1,0,1,1,1,4},{11,11,11,11,1,1,1,2,0,1,0,5,0},{11,11,11,11,0,0,1,0,0,0,0,5,0},
					{0,11,0,0,0,0,6,6,6,0,0,11,0},{0,3,0,3,0,0,0,0,0,11,11,11,11},{1,4,5,1,4,5,2,2,2,11,11,11,11},
					{0,0,0,0,0,1,0,3,3,11,11,11,11},{1,0,0,9,0,0,0,2,2,11,11,11,0},{1,1,0,9,0,0,0,0,0,11,11,11,0},
					{6,1,1,0,0,0,13,0,0,1,0,0,0},
					};			
			matriz = m3;
			break;
		case 4:
			int[][] m4= {
					{0,11,11,0,0,0,17,0,0,0,0,11,0},{11,11,0,0,3,1,1,3,3,0,0,0,11},{11,0,0,5,1,1,1,1,1,1,3,0,7},
					{7,0,0,1,1,1,1,1,1,1,1,4,0},{0,0,5,2,0,0,0,2,1,1,0,4,0},{12,0,5,0,9,0,9,0,1,4,0,0,0},
					{0,0,1,0,3,3,0,0,1,4,0,12,12},{0,0,1,1,1,1,1,1,1,1,0,0,0},{0,5,1,1,1,1,1,1,1,1,4,0,0},
					{0,2,2,1,1,1,1,1,1,2,2,0,0},{0,1,1,3,2,1,1,2,3,1,1,0,11},{11,0,2,2,0,0,0,0,2,2,0,11,11},
					{6,11,0,0,0,0,13,0,0,0,11,11,6},
					};			
			matriz = m4;
			break;
		case 5:
			int[][] m5= {
					{0,0,0,0,1,1,0,0,0,0,0,0,0},{8,0,3,0,1,0,0,0,7,7,6,0,0},{6,0,1,0,0,0,1,0,0,0,17,0,0},
					{1,0,1,1,1,0,1,1,0,12,12,0,12},{2,0,0,0,2,0,0,0,0,12,0,0,0},{0,0,3,0,12,12,0,12,12,12,0,1,1},
					{1,1,0,0,12,1,0,1,4,0,0,0,0},{0,0,0,0,12,0,0,0,0,0,10,9,0},{12,12,12,0,12,0,6,0,1,0,10,0,0},
					{0,0,0,3,3,0,0,0,0,0,10,1,1},{0,0,0,0,1,2,2,2,1,3,0,0,0},{1,1,2,0,0,0,0,0,0,2,1,0,0},
					{2,0,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m5;
			break;
		case 6:
			int[][] m6= {
					{0,0,17,0,0,5,0,4,11,11,0,0,0},{0,4,10,0,4,0,0,0,5,11,4,5,11},{0,4,10,0,4,0,1,0,5,11,4,5,11},
					{0,1,0,0,1,0,6,0,1,11,0,1,11},{0,0,0,5,7,0,1,0,2,9,0,11,11},{1,1,4,0,0,11,1,11,0,0,5,1,1},
					{0,0,0,0,5,11,11,11,4,0,0,0,0},{6,1,1,0,2,11,11,11,2,5,1,1,6},{7,7,7,0,3,0,11,0,3,0,7,7,7},
					{0,1,0,0,1,0,0,0,1,0,0,0,0},{0,1,4,0,0,2,0,2,0,0,5,1,11},{0,0,2,0,0,0,0,0,0,0,11,11,11},
					{0,0,3,0,0,0,13,0,0,0,3,11,11},
					};			
			matriz = m6;
			break;
		case 7:
			int[][] m7= {
					{0,0,0,17,0,0,0,7,7,0,0,0,0},{0,0,6,7,7,7,0,0,0,0,6,0,0},{0,0,6,0,0,0,11,0,7,6,6,0,0},
					{0,6,0,0,0,11,6,0,0,0,6,0,0},{0,0,0,0,11,6,6,0,0,0,7,6,0},{0,6,0,11,6,6,6,0,6,0,0,0,0},
					{0,10,0,6,6,0,0,0,6,6,0,0,0},{9,0,0,0,6,0,6,6,6,0,0,10,0},{0,10,6,0,0,0,6,6,11,0,0,6,0},
					{0,6,0,0,0,0,6,11,0,0,6,6,0},{0,7,7,6,0,0,11,0,0,6,0,0,0},{0,0,0,0,0,0,0,0,0,7,0,8,6},
					{8,8,0,0,0,0,13,0,0,0,0,0,0},
					};			
			matriz = m7;
			break;
		case 8:
			int[][] m8= {
					{0,0,1,0,0,1,0,3,0,1,0,0,0},{11,1,1,1,0,1,0,8,0,1,4,0,0},{11,11,11,0,0,2,0,1,0,2,0,5,4},
					{11,12,12,12,12,12,12,12,12,12,12,0,12},{0,1,0,0,0,0,3,3,0,0,17,0,0},{0,0,1,0,0,5,1,1,2,1,2,7,7},
					{1,1,0,1,0,5,1,1,11,1,8,8,1},{0,0,0,6,0,8,0,11,11,11,11,0,0},{12,12,0,12,12,12,12,12,0,12,12,12,12},
					{11,11,0,5,0,0,3,3,0,0,0,0,0},{11,11,1,0,4,0,0,5,0,8,3,1,0},{11,8,1,0,4,0,0,0,0,2,0,1,0},
					{0,0,0,0,0,0,13,0,0,3,0,2,0},
					};			
			matriz = m8;
			break;
		case 9:
			int[][] m9= {
					{0,0,0,1,0,0,0,0,0,8,11,0,0},{1,0,0,17,0,0,8,11,10,6,9,0,1},{0,0,0,8,11,10,6,9,0,7,11,0,0},
					{0,0,10,6,9,0,7,11,0,0,0,0,0},{0,0,0,7,11,0,0,0,0,0,0,0,0},{0,0,0,11,8,11,0,11,8,11,0,0,0},
					{6,1,0,10,6,9,0,10,6,9,0,1,6},{0,0,0,11,7,11,0,11,7,11,0,0,0},{0,0,0,0,8,0,0,0,8,0,0,0,0},
					{1,0,0,10,6,9,0,10,6,9,0,0,1},{1,0,0,11,7,11,0,11,7,11,0,0,1},{0,0,3,0,0,0,0,0,0,0,3,0,0},
					{0,0,1,1,0,0,13,0,0,1,1,0,0},
					};			
			matriz = m9;
			break;
		case 10:
			int[][] m10= {
					{0,0,17,0,0,0,0,0,0,0,0,0,0},{0,5,2,1,0,0,0,0,0,0,1,2,4},{5,2,0,0,1,0,11,11,0,1,0,0,5},
					{1,0,0,0,1,11,11,11,11,1,0,0,5},{1,0,0,5,1,11,6,6,11,1,4,0,1},{5,3,3,1,12,12,12,12,12,12,1,1,1},
					{0,1,1,1,6,6,1,6,6,1,1,1,4},{0,0,1,1,6,0,1,0,6,1,1,4,0},{0,0,1,1,1,1,1,1,1,1,1,4,0},
					{1,11,2,2,2,6,6,2,2,2,2,11,1},{1,11,11,11,11,11,11,11,11,11,11,11,1},{0,0,11,11,11,0,0,0,11,11,11,11,0},
					{0,0,0,4,0,0,13,0,0,0,4,0,0},
					};
			matriz = m10;
			break;
		case 11:
			int[][] m11= {
					{0,0,17,0,0,6,0,1,0,1,1,0,0},{0,5,1,1,1,1,0,1,0,0,0,0,0},{0,0,0,4,0,1,0,1,1,0,11,11,11},
					{0,5,0,0,0,0,0,6,0,11,11,11,11},{0,5,0,1,1,1,6,1,1,11,11,2,6},{0,2,2,2,6,0,0,1,0,11,11,0,5},
					{5,1,1,1,0,6,11,11,11,11,11,0,0},{0,0,0,6,0,0,11,11,11,11,11,1,0},{6,1,0,11,11,11,11,6,11,11,11,1,0},
					{5,1,11,11,11,11,11,0,0,0,0,1,4},{0,1,11,11,0,0,0,0,7,1,1,1,0},{0,0,11,11,0,0,0,0,0,1,0,5,0},
					{0,3,11,11,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m11;
			break;
		case 12:
			int[][] m12= {
					{0,0,0,17,0,0,0,1,1,1,0,0,0},{0,1,1,1,3,0,3,0,0,1,0,0,0},{0,0,0,0,1,0,2,0,0,0,0,1,1},
					{0,12,12,12,12,12,0,1,4,0,0,1,7},{0,0,8,8,8,12,0,1,0,6,9,1,0},{1,0,1,1,1,12,12,12,0,12,1,1,0},
					{0,0,0,0,6,12,0,0,0,12,7,0,0},{12,12,12,0,12,12,1,1,0,12,0,0,0},{0,0,0,0,0,1,7,7,0,12,12,12,0},
					{1,1,1,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,7,7,0,0,0,1,1,0,5},{1,0,0,0,0,0,0,0,0,1,0,0,1},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m12;
			break;
		case 13:
			int[][] m13= {
					{0,0,0,0,3,0,0,0,3,0,0,0,0},{0,1,1,1,1,0,0,0,1,1,1,1,0},{0,1,0,0,0,0,1,0,0,0,0,6,0},
					{0,6,0,1,2,0,0,0,2,1,0,1,1},{0,1,0,4,11,8,6,8,11,5,0,6,1},{0,2,0,0,11,11,11,11,11,0,0,7,1},
					{1,8,0,0,11,11,11,11,11,0,0,3,1},{1,6,0,4,11,7,6,7,11,5,0,1,0},{1,1,0,1,3,0,0,0,3,1,0,6,0},
					{1,6,0,0,0,0,1,0,0,0,0,1,0},{1,1,1,1,1,0,0,0,1,1,1,6,6},{1,1,0,0,2,0,0,0,2,0,0,1,0},
					{1,1,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m13;
			break;
		case 14:
			int[][] m14= {
					{0,0,17,0,0,0,0,0,0,0,0,0,0},{11,11,0,0,3,1,1,1,3,0,0,11,11},{11,0,0,5,1,1,1,1,1,4,0,0,11},
					{0,0,0,1,1,11,1,11,1,1,0,0,0},{0,0,0,1,11,11,1,11,11,1,0,0,0},{11,0,0,1,1,1,1,1,1,1,0,0,11},
					{11,11,0,0,1,11,1,11,1,0,0,11,11},{12,12,12,0,1,1,1,1,1,0,12,12,12},{0,0,0,0,5,5,5,5,5,0,0,0,0},
					{0,0,0,0,4,4,4,4,4,0,0,0,0},{10,10,10,0,0,0,0,0,0,0,9,9,9},{4,4,4,0,0,0,0,0,0,0,5,5,5},
					{9,9,9,10,0,0,13,0,0,9,10,10,10},
					};	
			matriz = m14;
			break;
		case 15:
			int[][] m15= {
					{0,0,17,0,1,1,0,0,1,0,0,0,0},{0,11,11,1,1,0,0,0,1,0,0,0,0},{11,11,11,11,11,11,11,11,1,1,0,0,0},
					{11,7,1,11,1,1,1,11,11,11,11,1,6},{11,11,1,11,11,11,7,11,11,1,9,1,0},{0,11,11,1,8,11,11,11,11,1,0,1,0},
					{0,1,1,1,1,1,11,11,1,1,4,11,11},{10,7,1,1,0,0,0,1,2,0,0,0,11},{0,1,0,1,0,7,3,2,11,11,1,4,11},
					{0,1,0,0,5,1,2,11,11,1,0,0,11},{0,1,1,4,5,2,11,11,3,11,1,11,11},{0,0,1,0,11,0,0,0,1,11,2,11,0},
					{0,0,2,0,0,0,13,0,0,11,11,11,0},
					};	
			matriz = m15;
			break;
		case 16:
			int[][] m16= {
					{0,0,17,0,0,0,0,0,0,0,0,0,0},{0,0,6,11,6,0,0,0,0,0,0,0,0},{0,0,0,11,0,11,8,0,0,0,0,0,0},
					{0,11,0,0,0,0,11,3,0,0,0,0,0},{0,11,11,0,0,11,0,11,8,0,0,0,0},{0,11,0,11,0,11,0,0,11,3,0,0,0},
					{0,11,0,0,11,0,0,0,11,11,8,0,0},{0,0,11,0,0,0,0,11,11,11,11,3,0},{0,0,0,11,0,0,11,0,11,11,11,11,0},
					{1,0,0,0,0,0,11,0,0,11,11,11,6},{1,1,0,0,0,0,0,11,0,11,11,11,11},{6,1,1,0,0,0,0,0,11,0,11,11,11},
					{6,6,1,1,0,0,13,0,11,0,0,11,11},
					};	
			matriz = m16;
			break;
		case 17:
			int[][] m17= {
					{0,0,0,0,3,0,0,0,0,0,3,0,0},{0,1,0,1,1,17,0,14,14,14,1,1,0},{0,1,0,0,1,0,6,14,14,14,14,14,0},
					{14,14,14,9,1,0,0,1,14,14,14,14,0},{14,14,14,14,14,14,1,1,5,4,0,0,0},{0,0,10,14,14,14,14,1,5,4,0,7,7},
					{1,1,1,1,14,14,14,14,14,14,14,1,1},{0,0,0,1,1,14,14,14,14,9,0,0,0},{0,1,1,1,0,14,14,14,1,1,0,1,0},
					{14,14,14,1,14,0,0,0,0,1,0,1,0},{14,14,14,14,14,7,0,7,0,0,3,1,0},{1,14,14,14,14,0,0,0,0,1,0,0,0},
					{1,1,9,0,0,0,13,0,0,1,0,1,0},
					};	
			matriz = m17;
			break;
		case 18:
			int[][] m18= {
					{0,0,17,0,0,0,0,0,6,6,6,11,0},{0,1,0,17,0,0,0,0,6,0,0,6,0},{1,11,1,0,0,0,1,1,1,1,0,6,0},
					{0,1,11,1,0,0,1,0,11,1,6,6,0},{0,0,1,0,11,6,1,11,0,1,0,0,0},{0,0,0,0,6,0,1,6,1,1,0,0,0},
					{0,0,1,1,6,1,0,6,0,0,0,0,0},{0,0,1,0,11,1,6,11,0,0,0,0,0},{6,6,6,11,0,1,0,0,1,1,0,0,0},
					{6,0,1,1,1,1,0,0,1,6,6,0,0},{6,0,0,6,0,0,0,0,0,6,1,1,0},{11,6,6,6,0,0,0,0,0,0,1,6,6},
					{0,0,0,0,0,0,13,0,0,0,0,6,6},
					};	
			matriz = m18;
			break;
		case 19:
			int[][] m19= {
					{0,1,0,1,0,1,0,1,0,1,0,1,0},{0,1,17,1,0,1,0,1,0,1,0,1,0},{0,7,0,7,0,7,0,7,0,7,0,7,0},
					{3,0,3,0,1,0,0,0,1,0,3,0,3},{1,0,1,2,1,0,1,0,1,2,1,0,1},{7,0,7,0,6,0,7,0,6,0,7,0,7},
					{11,11,0,0,1,0,11,0,1,0,0,11,11},{11,11,11,11,1,2,11,2,1,11,11,11,11},{11,11,11,11,11,11,11,11,11,11,11,11,11},
					{3,0,3,0,1,11,11,11,1,0,3,0,3},{0,1,0,1,0,0,11,0,0,1,0,1,0},{0,1,0,1,0,0,0,0,0,1,0,1,0},
					{0,2,0,2,0,0,13,0,0,2,0,2,0},
					};	
			matriz = m19;
			break;
		case 20:
			int[][] m20= {
					{0,0,0,12,0,1,0,0,1,0,1,0,0},{0,0,0,17,0,0,0,0,1,0,6,0,0},{0,0,0,12,0,3,6,0,1,0,1,0,0},
					{7,0,1,12,0,6,0,3,2,0,1,0,0},{0,0,1,12,0,0,0,1,0,0,0,0,0},{1,0,1,12,12,0,12,12,12,12,0,0,1},
					{0,0,0,3,0,0,0,11,0,12,0,7,7},{1,1,5,1,0,6,11,11,11,12,0,3,3},{2,0,5,0,0,1,11,11,11,12,0,1,0},
					{0,8,0,0,0,1,0,11,0,12,0,11,0},{0,1,0,8,0,2,2,2,0,0,11,11,11},{0,1,0,1,0,0,0,0,0,12,11,11,11},
					{0,0,0,0,0,0,13,0,0,12,0,11,0},
					};	
			matriz = m20;
			break;
		case 21:
			int[][] m21= {
					{0,0,0,3,3,3,0,0,3,0,0,0,0},{0,3,1,1,1,1,1,1,1,1,0,17,0},{0,11,11,11,11,11,11,11,11,1,1,0,0},
					{11,11,0,0,0,0,0,0,11,11,1,1,0},{11,0,6,0,0,6,0,0,0,11,11,11,0},{11,0,6,0,0,6,0,0,0,11,11,11,0},
					{11,0,0,11,0,0,0,0,11,11,1,1,4},{11,11,11,11,11,11,11,11,11,1,1,1,4},{1,11,11,1,1,11,11,11,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,0,6},{6,0,1,6,1,1,1,1,1,1,4,0,6},{0,6,1,2,6,0,0,0,1,1,6,6,6},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m21;
			break;
		case 22:
			int[][] m22= {
					{0,0,0,0,0,11,0,0,0,0,0,0,0},{0,0,0,0,11,6,11,0,0,0,17,0,0},{0,0,11,0,0,11,0,0,11,11,0,0,0},
					{0,11,1,11,0,0,0,11,1,1,11,0,0},{0,0,11,1,11,0,0,0,11,11,0,0,11},{11,0,0,11,0,0,11,0,0,0,0,11,6},
					{1,11,0,0,0,11,6,11,0,0,11,0,11},{6,1,11,0,0,0,11,0,0,11,6,11,0},{1,11,0,0,11,0,0,0,11,0,11,0,0},
					{11,0,0,11,1,11,0,11,1,11,0,0,0},{0,0,0,11,1,11,0,0,11,0,0,11,0},{0,11,0,0,11,0,0,0,0,0,11,6,11},
					{11,6,11,0,0,0,13,0,0,11,1,11,0},
					};	
			matriz = m22;
			break;
		case 23:
			int[][] m23= {
					{0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,6,6,0,0,17,0,0,0},{0,0,0,0,0,0,6,0,0,0,0,0,0},
					{0,6,6,11,11,1,6,1,11,11,6,6,0},{0,0,0,6,11,11,6,11,11,6,0,0,0},{11,0,0,0,6,11,11,11,6,0,0,0,11},
					{6,11,0,0,0,11,11,11,0,0,0,11,6},{11,0,0,0,8,7,11,7,8,0,0,0,11},{0,0,0,0,6,0,8,0,6,0,0,0,0},
					{0,0,0,6,0,0,6,0,0,6,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,6,0,0,0,13,0,0,0,6,0,0},
					};	
			matriz = m23;
			break;
		case 24:
			int[][] m24= {
					{0,0,6,0,1,7,0,0,0,0,5,0,0},{0,17,1,0,1,11,0,2,1,1,1,0,0},{0,11,11,0,1,11,5,4,0,0,0,6,6},
					{11,11,11,11,11,11,1,1,1,0,5,1,0},{0,0,11,11,3,3,7,1,0,5,1,2,5},{1,7,0,3,2,2,0,0,0,1,2,0,5},
					{5,0,3,1,14,14,14,14,14,14,14,14,14},{5,0,2,0,14,14,14,14,14,14,14,14,14},{0,0,6,0,14,14,14,14,14,14,14,14,14},
					{1,0,1,0,14,14,14,14,14,14,14,14,14},{5,0,1,0,14,14,14,14,14,14,14,14,14},{5,0,1,0,0,0,0,0,14,14,14,14,14},
					{0,0,2,0,0,0,13,0,0,14,14,14,14},
					};	
			matriz = m24;
			break;
		case 25:
			int[][] m25= {
					{0,0,0,6,0,1,0,1,0,1,0,6,0},{0,1,0,1,0,0,0,0,0,6,0,0,0},{0,1,0,1,0,0,6,0,0,6,0,6,6},
					{0,1,17,0,0,1,0,6,1,0,0,0,6},{0,0,0,0,1,1,0,1,1,0,6,0,0},{0,0,6,0,1,0,0,1,1,0,1,1,0},
					{6,0,6,0,0,1,0,6,0,0,6,1,0},{0,0,1,1,0,1,0,0,0,1,6,0,0},{0,6,1,1,0,1,1,0,1,1,0,0,1},
					{0,1,0,0,0,1,6,0,0,0,0,1,1},{0,0,0,1,0,1,1,6,0,6,0,0,1},{1,0,1,1,0,0,0,0,0,1,6,0,0},
					{1,0,1,0,0,0,13,0,0,1,1,1,0},
					};	
			matriz = m25;
			break;
		case 26:
			int[][] m26= {
					{0,0,12,12,0,0,0,0,0,0,0,0,0},{8,0,0,12,11,0,4,0,0,17,0,0,0},{11,8,0,0,0,0,9,0,4,0,12,12,0},
					{11,11,0,7,3,5,0,0,9,11,12,0,0},{11,11,11,0,0,6,3,5,0,0,0,0,8},{11,11,7,8,0,5,0,6,3,0,0,8,11},
					{11,7,0,0,2,6,0,5,0,7,0,11,11},{0,0,0,0,0,4,2,6,0,0,11,11,11},{0,0,12,11,10,0,0,4,2,8,7,11,11},
					{0,12,12,0,5,0,10,0,0,0,0,0,11},{0,0,0,0,0,0,5,0,11,12,0,0,7},{6,0,0,0,0,0,0,0,0,12,12,0,0},
					{6,6,0,0,0,0,13,0,0,0,0,0,6},
					};	
			matriz = m26;
			break;
		case 27:
			int[][] m27= {
					{0,0,0,0,6,0,0,0,0,0,0,0,0},{6,6,0,0,6,0,17,6,6,0,0,0,0},{0,6,0,0,6,0,0,0,6,0,6,6,11},
					{0,6,0,0,6,6,6,0,11,0,6,0,0},{0,1,0,0,0,0,6,0,6,6,6,0,0},{11,6,6,0,6,1,6,1,1,0,0,0,0},
					{0,0,6,11,6,11,0,0,1,0,0,6,6},{0,0,6,0,0,11,0,0,6,0,0,6,0},{0,0,1,0,0,6,0,0,6,6,1,6,0},
					{11,6,6,6,11,11,1,6,6,0,1,6,0},{0,0,0,1,0,0,0,0,11,11,0,1,0},{0,0,0,6,0,0,0,0,0,11,0,1,0},
					{0,0,0,6,0,0,13,0,0,6,0,1,0},
					};	
			matriz = m27;
			break;
		case 28:
			int[][] m28= {
					{0,0,0,0,0,0,0,0,0,0,10,9,0},{0,0,0,0,0,0,8,0,0,17,6,0,0},{0,0,0,0,0,3,11,3,0,1,4,0,0},
					{0,0,0,0,8,11,11,11,8,1,4,0,0},{0,0,0,3,11,11,14,11,11,1,4,0,0},{0,0,8,11,11,14,14,14,11,11,4,0,0},
					{0,3,11,11,14,14,14,14,14,11,11,3,0},{8,11,11,14,14,14,14,14,14,14,11,11,8},{11,11,14,14,14,14,14,14,14,14,14,11,11},
					{0,11,14,14,14,14,14,14,14,14,14,11,0},{0,11,14,14,14,14,14,14,14,14,14,11,0},{0,11,14,14,14,0,0,0,14,14,14,11,0},
					{0,11,14,14,0,0,13,0,0,14,14,11,0},
					};	
			matriz = m28;
			break;
		case 29:
			int[][] m29= {
					{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,1,12,12,0,6,0,1,0,0,17,0,0},{0,0,12,12,1,11,11,11,12,12,0,6,0},
					{0,0,0,0,0,11,11,11,12,12,1,0,0},{0,6,0,0,12,12,0,11,0,0,0,0,0},{11,11,1,0,12,12,6,0,0,0,0,1,0},
					{11,11,11,0,0,0,0,0,0,6,0,0,6},{0,1,12,12,0,1,0,0,0,0,0,0,0},{6,0,12,12,11,11,12,12,11,11,0,1,0},
					{0,0,0,0,11,0,12,12,11,11,12,12,0},{0,0,0,6,11,0,0,0,11,11,12,12,0},{0,0,1,0,1,0,0,0,0,0,0,0,0},
					{1,0,0,0,0,0,13,0,0,1,6,0,0},
					};	
			matriz = m29;
			break;
		case 30:
			int[][] m30= {
					{0,0,17,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,3,3,0,0,0,8,0,0},{0,8,8,0,3,11,11,8,0,3,11,3,0},
					{3,11,11,3,11,11,11,11,3,11,11,11,3},{11,11,11,11,11,11,11,11,11,11,11,11,11},{6,11,12,11,11,11,11,11,12,11,11,11,11},
					{11,11,12,12,12,11,11,11,12,12,12,11,6},{11,11,11,11,12,11,6,11,11,11,12,11,11},{11,11,11,11,11,11,11,11,11,11,11,11,11},
					{11,11,11,11,11,2,2,11,11,11,11,11,7},{7,11,11,11,2,0,0,2,11,11,11,7,0},{0,7,2,2,0,0,0,0,2,2,2,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m30;
			break;
		case 31:
			int[][] m31= {
					{0,0,0,12,0,0,0,0,12,0,0,0,0},{12,12,0,12,0,12,12,12,12,0,12,12,12},{11,11,1,0,0,1,0,0,12,0,12,11,12},
					{11,12,12,12,12,0,6,0,0,1,11,11,11},{11,11,0,12,0,0,12,0,12,12,12,12,11},{12,12,17,12,0,12,12,0,0,12,0,0,0},
					{0,0,1,11,1,0,1,11,0,12,0,0,12},{0,12,12,11,12,12,12,12,0,11,1,0,12},{1,0,0,1,0,0,0,0,11,11,12,0,12},
					{12,12,0,12,12,0,12,1,12,12,12,0,0},{0,0,1,0,11,11,0,0,11,12,0,0,12},{0,12,12,12,11,0,0,0,0,12,0,12,12},
					{0,12,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m31;
			break;
		case 32:
			int[][] m32= {
					{14,14,14,14,14,14,0,14,14,14,14,14,14},{14,14,14,14,14,14,14,14,14,14,14,14,14},{14,14,14,1,14,14,14,14,14,1,14,14,14},
					{14,1,0,1,0,1,14,1,0,1,0,1,14},{14,2,2,1,0,17,0,0,0,1,2,2,14},{14,14,14,1,3,1,6,1,3,1,14,14,14},
					{6,14,14,14,0,7,0,7,0,14,14,14,6},{14,14,14,14,0,3,0,3,0,14,14,14,14},{14,14,14,14,0,1,0,1,0,14,14,14,14},
					{14,14,14,1,0,0,3,0,0,1,14,14,14},{14,1,14,1,0,7,7,7,0,1,14,1,14},{0,1,3,1,0,0,0,0,0,1,3,1,0},
					{0,2,0,0,0,0,13,0,0,0,0,2,0},
					};	
			matriz = m32;
			break;
		case 33:
			int[][] m33= {
					{0,0,0,0,6,0,0,0,0,6,0,0,0},{0,6,0,0,0,6,0,0,6,11,11,0,0},{0,0,6,0,0,0,0,6,11,8,9,0,0},
					{0,0,0,6,0,11,11,11,11,11,0,10,0},{0,9,0,0,6,11,11,6,0,0,0,6,0},{0,7,9,11,0,6,0,0,6,0,0,10,0},
					{0,0,11,11,11,11,11,0,0,6,0,0,0},{0,8,9,11,0,6,11,0,0,0,6,0,0},{0,11,11,11,6,0,6,0,8,0,0,6,0},
					{11,11,11,6,0,0,0,0,10,0,0,0,0},{0,0,6,0,0,0,0,0,0,0,0,10,6},{0,0,0,0,0,0,0,0,0,8,9,0,0},
					{9,0,8,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m33;
			break;
		case 34:
			int[][] m34= {
					{0,0,0,0,4,5,0,0,0,0,0,0,0},{4,4,4,5,0,4,0,0,4,4,0,0,0},{4,4,4,1,1,0,0,17,4,1,4,0,0},
					{5,5,0,1,4,0,0,5,4,1,1,0,0},{0,4,0,1,5,4,0,4,1,1,1,0,0},{0,4,5,0,0,1,4,1,5,5,1,0,0},
					{0,4,0,0,5,1,1,4,0,4,1,0,0},{0,5,0,0,4,1,1,4,0,4,1,0,0},{0,5,2,2,0,1,1,1,0,4,4,2,1},
					{0,5,0,0,5,4,1,5,4,4,4,5,5},{0,0,4,0,1,5,4,1,1,0,0,5,0},{0,0,4,5,4,0,0,0,1,4,0,4,0},
					{0,0,4,5,0,0,13,0,0,1,1,0,0},
					};	
			matriz = m34;
			break;
		case 35:
			int[][] m35= {
					{0,0,17,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,1,0,0,0,0,0,0},{11,0,0,11,1,11,1,11,0,0,11,0,0},
					{1,11,11,1,1,1,1,1,11,11,1,11,0},{1,1,1,1,6,1,6,1,1,1,1,11,0},{12,12,12,1,1,1,1,1,12,12,12,11,0},
					{12,1,1,1,1,1,1,1,1,1,12,12,11},{1,1,1,12,1,1,1,12,1,1,1,11,11},{1,1,12,12,12,1,12,12,12,1,1,12,12},
					{11,12,12,11,11,11,11,11,12,12,11,12,11},{0,11,11,0,0,0,0,0,11,11,0,11,0},{0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,13,0,0,0,0,0,0},
					};	
			matriz = m35;
			break;
		}
		
		return matriz;
	}	
		
	public void loadIronWall(){
    	stageControl.spawnWalls(new Wall(11.5,5.5,0,3,stageControl,true));
    	stageControl.spawnWalls(new Wall(11.5,6.5,0,3,stageControl,true));
    	stageControl.spawnWalls(new Wall(12,5.5,0,4,stageControl,true));
    	stageControl.spawnWalls(new Wall(12,7,0,4,stageControl,true));
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

		return listEagleWall;		

	}
	
	public BufferedImage[] loadBackground(int level){
		switch (level) {
		case 4:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage04_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage04_B.png");
			break;
		case 5:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage05_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage05_B.png");
			break;
		case 8:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage08_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage08_B.png");
			break;
		case 10:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage10_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage10_B.png");
			break;
		case 12:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage12_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage12_B.png");
			break;
		case 14:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage14_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage14_B.png");
			break;
		case 17:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage17_B.png");
			break;
		case 20:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage20_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage20_B.png");
			break;
		case 24:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage24_B.png");
			break;
		case 26:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage26_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage26_B.png");
			break;
		case 28:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage28_B.png");
			break;
		case 29:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage29_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage29_B.png");
			break;
		case 30:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage30_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage30_B.png");
			break;
		case 31:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage31_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage31_B.png");
			break;
		case 32:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage32_B.png");
			break;
		case 35:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage35_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage35_B.png");
			break;
		default:
			img[0] = ImageControl.loadImage("/resources/images/background/Stage_A.png");
			img[1] = ImageControl.loadImage("/resources/images/background/Stage_B.png");
			break;
		}
		
		return img;
	}
	
	public void loadMaze(int level, boolean player1, boolean player2){
    	int[][] m = createMaze(level);
    	mazeItemsRow = new ArrayList<>();
    	mazeItemsCol = new ArrayList<>();
    	
    	if (player1) {
    		m[12][4] = 15;
		}
    	if (player2) {
			m[12][8] = 16;
		}
    	    	
    	for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) {
            	
            	switch (m[i][j]) {
				case 0:
					mazeItemsRow.add(i); mazeItemsCol.add(j);
					break;
					
				case 1:
					for (int j2 = 0; j2 < 4; j2++) {
						stageControl.spawnWalls(new Wall(i,j,j2,1,stageControl, false)); 
					}
					
					mazeItemsRow.add(i); mazeItemsCol.add(j);
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
					stageControl.spawnForest(new Forest(i,j,stageControl));
					break;

				case 12:
					stageControl.spawnWater(new Water(i,j,stageControl));
					break;
					
				case 13:
					stageControl.setEagle(new Eagle(i,j,stageControl));
					break;

				case 14:
					stageControl.spawnIce(new Ice(i,j,stageControl));
					break;
					
				case 15:
					StageControl.players[0] = new Player(i+1, j+1, Properties.INIT_LIFES,1, stageControl);
					break;

				case 16:
					StageControl.players[1] = new Player(i+1, j+1, Properties.INIT_LIFES,2, stageControl);
					break;
					
				case 17:
					stageControl.spawnGas(new Item(i+1,j+1,7,stageControl));
					break;
				}

            }
        }
    	
    }
		
	public int[] loadNumEnemies(int level) {
		int[] nEnemies = new int[4];  
		
    	switch (level) {
		case 1:			
			nEnemies[0] = 18; nEnemies[1] = 2;	nEnemies[2] = 0; 	nEnemies[3] = 0;
			break;
		case 2:
			nEnemies[0] = 14; nEnemies[1] = 4;	nEnemies[2] = 2; 	nEnemies[3] = 0;
			break;
		case 3:			
			nEnemies[0] = 14; nEnemies[1] = 4;	nEnemies[2] = 2; 	nEnemies[3] = 0;
			break;
		case 4:
			nEnemies[0] = 2; nEnemies[1] = 5;	nEnemies[2] = 10;	nEnemies[3] = 3;
			break;
		case 5:			
			nEnemies[0] = 8; nEnemies[1] = 5;	nEnemies[2] = 5; 	nEnemies[3] = 2;
			break;
		case 6:
			nEnemies[0] = 9; nEnemies[1] = 2;	nEnemies[2] = 7; 	nEnemies[3] = 2;
			break;
		case 7:			
			nEnemies[0] = 3; nEnemies[1] = 4;	nEnemies[2] = 6; 	nEnemies[3] = 7;
			break;
		case 8:
			nEnemies[0] = 7; nEnemies[1] = 4;	nEnemies[2] = 7; 	nEnemies[3] = 2;
			break;
		case 9:			
			nEnemies[0] = 6; nEnemies[1] = 4;	nEnemies[2] = 7; 	nEnemies[3] = 3;
			break;
		case 10:
			nEnemies[0] = 12; nEnemies[1] = 2;	nEnemies[2] = 4; 	nEnemies[3] = 2;
			break;
		case 11:			
			nEnemies[0] = 0; nEnemies[1] = 10;	nEnemies[2] = 4; 	nEnemies[3] = 6;
			break;
		case 12:
			nEnemies[0] = 0; nEnemies[1] = 6;	nEnemies[2] = 8; 	nEnemies[3] = 6;
			break;
		case 13:			
			nEnemies[0] = 0; nEnemies[1] = 8;	nEnemies[2] = 8; 	nEnemies[3] = 4;
			break;
		case 14:
			nEnemies[0] = 0; nEnemies[1] = 4;	nEnemies[2] = 10; nEnemies[3] = 6;
			break;
		case 15:			
			nEnemies[0] = 2; nEnemies[1] = 10;	nEnemies[2] = 0; 	nEnemies[3] = 8;
			break;
		case 16:
			nEnemies[0] = 16; nEnemies[1] = 2;	nEnemies[2] = 0; 	nEnemies[3] = 2;
			break;
		case 17:			
			nEnemies[0] = 8; nEnemies[1] = 2;	nEnemies[2] = 0; 	nEnemies[3] = 10;
			break;
		case 18:
			nEnemies[0] = 2; nEnemies[1] = 8;	nEnemies[2] = 6; 	nEnemies[3] = 4;
			break;
		case 19:			
			nEnemies[0] = 4; nEnemies[1] = 4;	nEnemies[2] = 4; 	nEnemies[3] = 8;
			break;
		case 20:
			nEnemies[0] = 2; nEnemies[1] = 8;	nEnemies[2] = 2; 	nEnemies[3] = 8;
			break;
		case 21:			
			nEnemies[0] = 6; nEnemies[1] = 2;	nEnemies[2] = 8; 	nEnemies[3] = 4;
			break;
		case 22:
			nEnemies[0] = 6; nEnemies[1] = 8;	nEnemies[2] = 2; 	nEnemies[3] = 4;
			break;
		case 23:			
			nEnemies[0] = 0; nEnemies[1] = 10;	nEnemies[2] = 4; 	nEnemies[3] = 6;
			break;
		case 24:
			nEnemies[0] = 10; nEnemies[1] = 4;	nEnemies[2] = 4; 	nEnemies[3] = 2;
			break;
		case 25:			
			nEnemies[0] = 0; nEnemies[1] = 8;	nEnemies[2] = 2; 	nEnemies[3] = 10;
			break;
		case 26:
			nEnemies[0] = 4; nEnemies[1] = 6;	nEnemies[2] = 4; 	nEnemies[3] = 6;
			break;
		case 27:			
			nEnemies[0] = 2; nEnemies[1] = 8;	nEnemies[2] = 2; 	nEnemies[3] = 8;
			break;
		case 28:
			nEnemies[0] = 15; nEnemies[1] = 2;	nEnemies[2] = 2; 	nEnemies[3] = 1;
			break;
		case 29:			
			nEnemies[0] = 0; nEnemies[1] = 4;	nEnemies[2] = 10; nEnemies[3] = 6;
			break;
		case 30:
			nEnemies[0] = 4; nEnemies[1] = 8;	nEnemies[2] = 4; 	nEnemies[3] = 4;
			break;
		case 31:			
			nEnemies[0] = 0; nEnemies[1] = 8;	nEnemies[2] = 6; 	nEnemies[3] = 6;
			break;
		case 32:
			nEnemies[0] = 6; nEnemies[1] = 4;	nEnemies[2] = 2; 	nEnemies[3] = 8;
			break;
		case 33:			
			nEnemies[0] = 0; nEnemies[1] = 8;	nEnemies[2] = 4; 	nEnemies[3] = 8;
			break;
		case 34:
			nEnemies[0] = 0; nEnemies[1] = 10;	nEnemies[2] = 4; 	nEnemies[3] = 6;
			break;
		case 35:			
			nEnemies[0] = 0; nEnemies[1] = 6;	nEnemies[2] = 4; 	nEnemies[3] = 10;
			break;
		}
    	
    	return nEnemies;
	}

	
	public ArrayList<Integer> getMazeItemsRow() {
		return mazeItemsRow;
	}

	public void setMazeItemsRow(ArrayList<Integer> mazeItemsRow) {
		this.mazeItemsRow = mazeItemsRow;
	}

	public ArrayList<Integer> getMazeItemsCol() {
		return mazeItemsCol;
	}

	public void setMazeItemsCol(ArrayList<Integer> mazeItemsCol) {
		this.mazeItemsCol = mazeItemsCol;
	}
	
}
