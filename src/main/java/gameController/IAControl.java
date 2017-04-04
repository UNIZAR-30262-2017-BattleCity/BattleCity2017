package gameController;

import java.util.LinkedList;
import java.util.Random;

import org.neuroph.core.NeuralNetwork;

import application.Properties;
import elements.Eagle;
import elements.Enemy;
import elements.Player;
import elements.StageElement;
import elements.Wall;

public class IAControl {
	
	private NeuralNetwork<?> neuralNetworkMultiLayer;
	
	private static final double delta2 = Properties.DELTA/2;
	
	public IAControl() {			
		neuralNetworkMultiLayer = NeuralNetwork.createFromFile("enemy_IA.nnet");
	}
// ------------------------------------------------------------------------------- //
// ---------------------------- GAME IMPLEMENTATION ------------------------------ //
// ------------------------------------------------------------------------------- //
	
	public int[] getDir_Shoot(Enemy e, StageControl stageControl) {
		
		double posX = e.getPosX()+Properties.DELTA;
		double posY = e.getPosY()+Properties.DELTA;
		
		double[] inputIA = {0,0,0,0};
		int[] action = {0,0};
		
		LinkedList<StageElement> elementsList = stageControl.getElements_Enemy();
		
		StageElement element, temElementUP1 = null, temElementDOWN1 = null, 
				temElementLEFT1 = null, temElementRIGHT1 = null;
		double elementX, elementY;
		double columnInit, columnFin, rowInit, rowFin;
		double nearUP1 = -10;
		double nearDOWN1 = Properties.HEIGHT;
		double nearLEFT1 = -10;
		double nearRIGHT1 = Properties.WIDTH;
		
		for (int i = 0; i < elementsList.size(); i++) {
			element = elementsList.get(i);
			elementX = element.getPosX() + Properties.DELTA;
			elementY = element.getPosY() + Properties.DELTA;
			
			columnInit = element.getPosX();
			columnFin = elementX + Properties.DELTA;
			
			rowInit = element.getPosY();
			rowFin = elementY + Properties.DELTA;
			
			if (element.getClass().equals(Wall.class)) {				
				if (element.getType() == 3) {
					elementY = element.getPosY() + delta2;				
					rowFin = elementY + delta2;
				}else if (element.getType() == 4) {
					elementX = element.getPosX() + delta2;
    			
					columnInit = elementX - delta2;
					columnFin = elementX + delta2;    			
				}else if (element.getType() == 1) {
					elementX = element.getPosX() + delta2;
					elementY = element.getPosY() + delta2;
    			
					columnFin = elementX + delta2;    			
					rowFin = elementY + delta2;
				}				
			}else if (element.getClass().equals(Eagle.class)) {
				columnInit = elementX - delta2;
				columnFin = elementX + delta2;
				
				rowInit = elementY - delta2;
				rowFin = elementY + delta2;
			}
			
			if (posX > columnInit && posX < columnFin) {
				
				if (elementY < posY && nearUP1 < elementY) {
					nearUP1 = elementY;
					temElementUP1 = element; // UP
				} else if (elementY > posY && nearDOWN1 > elementY) {
					nearDOWN1 = elementY;
					temElementDOWN1 = element; // DOWN
				}
				
			} 
			
			else if (posY > rowInit && posY < rowFin) {
				
				if (elementX < posX && nearLEFT1 < elementX) {
					nearLEFT1 = elementX;
					temElementLEFT1 = element; // LEFT
				} else if (elementX > posX && nearRIGHT1 > elementX) {
					nearRIGHT1 = elementX;
					temElementRIGHT1 = element; // RIGHT
				}
				
			}
		}
		
		inputIA[0] = generateInputValue(temElementUP1);
		
		inputIA[1] = generateInputValue(temElementDOWN1);
		
		inputIA[2] = generateInputValue(temElementLEFT1);
		
		inputIA[3] = generateInputValue(temElementRIGHT1);
		
		if (inputIA[0] == 0 && inputIA[1] == 0
				&& inputIA[2] == 0 && inputIA[3] == 0) {					
			if (e.isUpdateIARandom()) {				
				e.updateIARamdon();
				e.updateCollision();
				action[0] = e.getDir();
			}else{
				action = randomMove(e);
			}			
			
		} else {
			if (e.isUpdateIA()) {				
				e.updateIA();
				e.updateCollision();
				action[0] = e.getDir();
			}else{
				e.setUpdateIARandom(false);
				e.setTimeUpdateIARandom(Properties.TIME_UPDATE_IA_RANDOM);
				action = calculateIA(neuralNetworkMultiLayer, inputIA, e);
			}
		}
		return action;
		//return borderController(action, posX-Properties.DELTA, posY-Properties.DELTA);
	}
	
	public int[] calculateIA(NeuralNetwork<?> nnet, double[] input, Enemy e) {
		int[] action = {0,0};
		
		nnet.setInput(input);
		nnet.calculate();
		double[] networkOutput = nnet.getOutput();
		double[] networkOutputNormalize =  new double[nnet.getOutput().length];
		
		for (int i = 0; i < networkOutput.length; i++) {
			if (networkOutput[i] > 0.5) {
				networkOutputNormalize[i] = 1;
			} else {
				networkOutputNormalize[i] = 0;
			}
		}
		
		for (int i = 0; i < networkOutputNormalize.length; i = i+2) {
			if (i < 2) {
				if (networkOutputNormalize[i] < 1 
						&& networkOutputNormalize[i+1] < 1) {
					action[0] = 1; // Up
				} else if (networkOutputNormalize[i] < 1 
						&& networkOutputNormalize[i+1] > 0) {
					action[0] = -1; // Down
				} else if (networkOutputNormalize[i] > 0 
						&& networkOutputNormalize[i+1] < 1) {
					action[0] = -2; // Left
				} else if (networkOutputNormalize[i] > 0 
						&& networkOutputNormalize[i+1] > 0) {
					action[0] = 2; // Right
				}
			} else {
				if (networkOutputNormalize[i] < 1) {
					action[1] = 0; // No shoot
				} else if (networkOutputNormalize[i] > 0) {
					action[1] = 1; // Shoot
				}
			}
		}

		return action;
	}
	
	public double generateInputValue(StageElement element) {
		double value = 0;

		if (element != null) {

			if (element.getClass().equals(Wall.class)){
				if(element.getType() == 1) value = 2;
			}
			else if (element.getClass().equals(Player.class)) {
				value = 3;
			}

			else if (element.getClass().equals(Eagle.class)) {
				value = 4;
			}
		}

		return value;
	}

	public int[] randomMove(Enemy e) {
		int[] move = {-1,0};
		
		Random random = new Random();
		int valor = -1; 
		int direction = random.nextInt(3);
		
		switch (direction) {
			case 0:
				valor = -1;
				break;
			case 1:
				valor = 2;
				break;
			case 2:
				valor = 2*(-1);
				break;
		}
		
		move[0] = valor;
		e.setUpdateIARandom(true);
		return move;
	}
}