package gameController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.Weight;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class ArtificialIntelligence {
	
	private NeuralNetwork<?> neuralNetworkMultiLayer;
	private DataSet trainingSet;
	private DataSet testSet;

	public ArtificialIntelligence(int inputNeuronsCount, int outputNeuronsCount) {
		
		//neuralNetworkMultiLayer = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, inputNeuronsCount, 50, outputNeuronsCount);
		neuralNetworkMultiLayer = NeuralNetwork.createFromFile("enemy_IA.nnet");
		trainingSet = new DataSet(inputNeuronsCount, outputNeuronsCount);
		testSet = new DataSet(inputNeuronsCount, outputNeuronsCount);
		
		createDataSet();
		//training();
		
		testNeuralNetwork(neuralNetworkMultiLayer, testSet);
	}
	
	private void training() {
		neuralNetworkMultiLayer.learn(trainingSet);
		neuralNetworkMultiLayer.save("enemy_IA.nnet");
	}
	
	private void createDataSet() {
		
		readFileDataSet();

		testSet.addRow(new DataSetRow(new double[]{2,1,0,0,0,0,0,1}, new double[]{0,0,1,1}));
		testSet.addRow(new DataSetRow(new double[]{2,2,0,0,0,1,0,0}, new double[]{0,0,0,0}));
		testSet.addRow(new DataSetRow(new double[]{0,0,2,1,1,0,0,0}, new double[]{0,1,1,1}));
		testSet.addRow(new DataSetRow(new double[]{0,0,2,2,0,0,1,0}, new double[]{0,1,0,0}));
		testSet.addRow(new DataSetRow(new double[]{0,0,0,0,2,1,1,0}, new double[]{1,0,1,1}));
		testSet.addRow(new DataSetRow(new double[]{0,0,1,0,2,2,0,0}, new double[]{1,0,0,0}));
		testSet.addRow(new DataSetRow(new double[]{0,1,0,0,0,0,2,1}, new double[]{1,1,1,1}));
		testSet.addRow(new DataSetRow(new double[]{0,0,1,0,0,0,2,2}, new double[]{1,1,0,0}));
	}
	
	private void testNeuralNetwork(NeuralNetwork<?> nnet, DataSet testSet) {
		for(DataSetRow dataRow : testSet.getRows()) {
			double[] output = dataRow.getDesiredOutput();
			System.out.println("\nOutput label : " + Arrays.toString(output));

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
			
			for (int i = 0; i < networkOutputNormalize.length; i = i+2) {
				if (i < 2) {
					if (networkOutputNormalize[i] < 1 
							&& networkOutputNormalize[i+1] < 1) {
						System.out.print("Arriba");
					} else if (networkOutputNormalize[i] < 1 
							&& networkOutputNormalize[i+1] > 0) {
						System.out.print("Abajo");
					} else if (networkOutputNormalize[i] > 0 
							&& networkOutputNormalize[i+1] < 1) {
						System.out.print("Izquierda");
					} else if (networkOutputNormalize[i] > 0 
							&& networkOutputNormalize[i+1] > 0) {
						System.out.print("Derecha");
					}
				} else {
					if (networkOutputNormalize[i] < 1 
							&& networkOutputNormalize[i+1] < 1) {
						System.out.println(" -- No disparar");
					} else if (networkOutputNormalize[i] < 1 
							&& networkOutputNormalize[i+1] > 0) {
						System.out.println(" -- Talves disparar");
					} else if (networkOutputNormalize[i] > 0 
							&& networkOutputNormalize[i+1] < 1) {
						System.out.println(" -- Talves disparar");
					} else if (networkOutputNormalize[i] > 0 
							&& networkOutputNormalize[i+1] > 0) {
						System.out.println(" -- Si disparar");
					}
				}
			}
		}

		Layer l2 = nnet.getLayerAt(1);
		Layer l3 = nnet.getLayerAt(2);

		System.out.println("\nNetwork layers: " + nnet.getLayersCount());
		System.out.println("Layer hidden neurons: " + l2.getNeuronsCount());
		System.out.println("Layer output neurons: " + l3.getNeuronsCount());

		Neuron[] neurons2 = l2.getNeurons();
		Neuron[] neurons3 = l3.getNeurons();

		Weight[] w2 = neurons2[1].getWeights();
		Weight[] w3 = neurons3[1].getWeights();
		
		System.out.println(Arrays.toString(w2));
		System.out.println(Arrays.toString(w3));
	}
	
	private void readFileDataSet() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("trainingData.txt")); 
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
	}
}