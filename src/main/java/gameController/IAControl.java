package gameController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;
import org.neuroph.util.TransferFunctionType;

import application.Properties;
import elements.Eagle;
import elements.Enemy;
import elements.Player;
import elements.Stage;
import elements.StageElement;
import elements.Wall;

public class IAControl {
	
	private NeuralNetwork<?> neuralNetworkMultiLayer;
	private int inputNeuronsCount;
	private int outputNeuronsCount;
	
	public IAControl() {
		
		inputNeuronsCount = 4;
		outputNeuronsCount = 3;
		
		neuralNetworkMultiLayer = NeuralNetwork.createFromFile("enemy_IA.nnet");
		//training("enemy_IA.nnet", "trainingData3.txt", "testData2.txt");
	}
	
	private void training(String nameToSave, String trainingData, String testData) {
		System.out.println("Time start training:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
		
		neuralNetworkMultiLayer = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, inputNeuronsCount, 50, outputNeuronsCount);
		
		DataSet trainingSet = createDataSet(inputNeuronsCount, outputNeuronsCount, trainingData);
		DataSet testSet = createDataSet(inputNeuronsCount, outputNeuronsCount, testData);
		
        ((LMS) neuralNetworkMultiLayer.getLearningRule()).setMaxError(0.001);//0-1
        ((LMS) neuralNetworkMultiLayer.getLearningRule()).setLearningRate(0.7);//0-1
        //((LMS) neuralNetworkMultiLayer.getLearningRule()).setMaxIterations(10000);
        
        System.out.println(trainingSet.toString());
        
		neuralNetworkMultiLayer.learn(trainingSet);
		neuralNetworkMultiLayer.save(nameToSave);
		
		testNeuralNetwork(neuralNetworkMultiLayer, testSet);
		System.out.println(Arrays.toString(neuralNetworkMultiLayer.getWeights()));
        System.out.println("Time stop training:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
	}
	
	private DataSet createDataSet(int inputNeuronsCount, int outputNeuronsCount, String file) {
		DataSet dataSet = new DataSet(inputNeuronsCount, outputNeuronsCount);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
			String line; 
			while((line = reader.readLine()) != null){
				if (line.equalsIgnoreCase("-----------U-----------") ||
						line.equalsIgnoreCase("-----------D-----------") ||
						line.equalsIgnoreCase("-----------L-----------") ||
						line.equalsIgnoreCase("-----------R-----------")) {
					System.out.println(line);
				} else {
					String[] data = line.split("-");
					String[] in = data[0].split(",");
					String[] out = data[1].split(",");
					
					double[] input = new double[in.length];
					for (int i = 0; i < input.length; i++) {
						input[i] = Double.parseDouble(in[i]);
					}
					
					double[] output = new double[out.length];
					for (int i = 0; i < output.length; i++) {
						output[i] = Double.parseDouble(out[i]);
					}
					
					dataSet.addRow(new DataSetRow(input, output));
				}
			}
			reader.close(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		return dataSet;
	}
	
	private void testNeuralNetwork(NeuralNetwork<?> nnet, DataSet testSet) {
		for(DataSetRow dataRow : testSet.getRows()) {
			double[] output = dataRow.getDesiredOutput();
			System.out.println("\nOutput label : " + Arrays.toString(output));
			generateAction(output);

			nnet.setInput(dataRow.getInput());
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
			
			System.out.println("Input: " + Arrays.toString(dataRow.getInput()));
			System.out.println("Output Normalize: " + Arrays.toString(networkOutputNormalize));
			
			generateAction(networkOutputNormalize);
		}

		Layer l2 = nnet.getLayerAt(1);
		Layer l3 = nnet.getLayerAt(2);

		System.out.println("\nNetwork layers: " + nnet.getLayersCount());
		System.out.println("Layer hidden neurons: " + l2.getNeuronsCount());
		System.out.println("Layer output neurons: " + l3.getNeuronsCount());
	}
	
	private void generateAction(double[] networkOutputNormalize) {
		for (int i = 0; i < networkOutputNormalize.length; i = i+2) {
			if (i < 2) {
				if (networkOutputNormalize[i] < 1 
						&& networkOutputNormalize[i+1] < 1) {
					System.out.print("Arriba (U)");
				} else if (networkOutputNormalize[i] < 1 
						&& networkOutputNormalize[i+1] > 0) {
					System.out.print("Abajo (D)");
				} else if (networkOutputNormalize[i] > 0 
						&& networkOutputNormalize[i+1] < 1) {
					System.out.print("Izquierda (L)");
				} else if (networkOutputNormalize[i] > 0 
						&& networkOutputNormalize[i+1] > 0) {
					System.out.print("Derecha (R)");
				}
			} else {
				if (networkOutputNormalize[i] < 1) {
					System.out.println(" -- No disparar (NS)");
				} else if (networkOutputNormalize[i] > 0) {
					System.out.println(" -- Si disparar (S)");
				}
			}
		}
	}

// ------------------------------------------------------------------------------- //
// ---------------------------- GAME IMPLEMENTATION ------------------------------ //
// ------------------------------------------------------------------------------- //
	
	public int[] getDir_Shoot(double posX, double posY, Stage stage) {
		double[] inputIA = {0,0,0,0};
		
		LinkedList<StageElement> elementsList = stage.getElements(null);
		Player player = stage.getPlayer();
		elementsList.add(player);
		
		StageElement element, temElementUP1 = null, temElementDOWN1 = null, 
				temElementLEFT1 = null, temElementRIGHT1 = null;
		double elementX, elementY;
		double delta = Properties.SIZE_SQUARE/2;
		double delta2 = delta/2;
		double nearUP1 = -10000;
		double nearDOWN1 = 10000;
		double nearLEFT1 = -10000;
		double nearRIGHT1 = 10000;
		
		for (int i = 0; i < elementsList.size(); i++) {
			element = elementsList.get(i);
			elementX = element.getPosX() + delta;
			elementY = element.getPosY() + delta;
			
			if (element.getClass().equals(Enemy.class)
					&& element.getType() == 3) {
				elementX = element.getPosX() + delta;
				elementY = element.getPosY() + delta2;
			} else if (element.getClass().equals(Enemy.class)
					&& element.getType() == 4) {
    			elementX = element.getPosX() + delta2;
    			elementY = element.getPosY() + delta;
			} else if (element.getClass().equals(Enemy.class) 
					&& (element.getType() == 1 || element.getType() == 2)) {
    			elementX = element.getPosX() + delta2;
    			elementY = element.getPosY() + delta2;
			}
			
			if (elementX == posX) {
				
				if (elementY < posY-18 && nearUP1 < elementY) {
					nearUP1 = elementY;
					temElementUP1 = element; // UP
				} else if (elementY > posY+18 && nearDOWN1 > elementY) {
					nearDOWN1 = elementY;
					temElementDOWN1 = element; // DOWN
				}
				
			} 
			
			else if (elementY == posY) {
				
				if (elementX < posX-18 && nearLEFT1 < elementX) {
					nearLEFT1 = elementX;
					temElementLEFT1 = element; // LEFT
				} else if (elementX > posX+18 && nearRIGHT1 > elementX) {
					nearRIGHT1 = elementX;
					temElementRIGHT1 = element; // RIGHT
				}
				
			}
		}
		
		inputIA[0] = generateInputValue(temElementUP1, posX, posY);
		
		inputIA[1] = generateInputValue(temElementDOWN1, posX, posY);
		
		inputIA[2] = generateInputValue(temElementLEFT1, posX, posY);
		
		inputIA[3] = generateInputValue(temElementRIGHT1, posX, posY);
		
		return calculateIA(neuralNetworkMultiLayer, inputIA);
	}
	
	public int[] calculateIA(NeuralNetwork<?> nnet, double[] input) {
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
	
	public double generateInputValue(StageElement element, double posX, double posY) {
		double value = 0;
		
		if (element != null) {
			
			if (element.getClass().equals(Wall.class)
					&& element.getType() != 1) {
				value = 1;
			}
			
			else if (element.getClass().equals(Wall.class)
					&& element.getType() == 1) {
				value = 2;
			}
			
			else if (element.getClass().equals(Player.class)) {
				value = 3;
			}
			
			else if (element.getClass().equals(Eagle.class)) {
				value = 4;
			}
		} /*else {
			if (posX == Properties.X_INIT_STAGE 
					|| posX == (Properties.X_FINAL_STAGE - Properties.SIZE_SQUARE)
					|| posY == Properties.Y_INIT_STAGE
					|| posY == (Properties.Y_FINAL_STAGE - Properties.SIZE_SQUARE)) {
				value = 5;
			}
		}*/
		
		return value;
	}
}