package gameController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;
import org.neuroph.util.TransferFunctionType;

public class ArtificialIntelligence {
	
	private NeuralNetwork<?> neuralNetworkMultiLayer;

	public ArtificialIntelligence(int inputNeuronsCount, int outputNeuronsCount) {
		
		//neuralNetworkMultiLayer = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, inputNeuronsCount, 50, outputNeuronsCount);
		neuralNetworkMultiLayer = NeuralNetwork.createFromFile("enemy_IA.nnet");
		
		DataSet trainingSet = createDataSet(inputNeuronsCount, outputNeuronsCount, "trainingData.txt");
		DataSet testSet = createDataSet(inputNeuronsCount, outputNeuronsCount, "testData.txt");
		
		//training(trainingSet);
		
		testNeuralNetwork(neuralNetworkMultiLayer, testSet);
	}
	
	private void training(DataSet trainingSet) {
		System.out.println("Time start training:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
		
        ((LMS) neuralNetworkMultiLayer.getLearningRule()).setMaxError(0.001);//0-1
        ((LMS) neuralNetworkMultiLayer.getLearningRule()).setLearningRate(0.7);//0-1
        ((LMS) neuralNetworkMultiLayer.getLearningRule()).setMaxIterations(1000);
        
		neuralNetworkMultiLayer.learn(trainingSet);
		neuralNetworkMultiLayer.save("enemy_IA.nnet");

        System.out.println("Time stop training:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
	}
	
	private DataSet createDataSet(int inputNeuronsCount, int outputNeuronsCount, String file) {
		DataSet dataSet = new DataSet(inputNeuronsCount, outputNeuronsCount);
		
		return dataSet = readFileDataSet(dataSet, file);
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
	
	private DataSet readFileDataSet(DataSet trainingSet, String file) {
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
					
					trainingSet.addRow(new DataSetRow(input, output));
				}
			}
			reader.close(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return trainingSet;
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
				if (networkOutputNormalize[i] < 1 
						&& networkOutputNormalize[i+1] < 1) {
					System.out.println(" -- No disparar (NS)");
				} else if (networkOutputNormalize[i] < 1 
						&& networkOutputNormalize[i+1] > 0) {
					System.out.println(" -- Talves disparar (MS)");
				} else if (networkOutputNormalize[i] > 0 
						&& networkOutputNormalize[i+1] < 1) {
					System.out.println(" -- Talves disparar (MS)");
				} else if (networkOutputNormalize[i] > 0 
						&& networkOutputNormalize[i+1] > 0) {
					System.out.println(" -- Si disparar (S)");
				}
			}
		}
	}
}