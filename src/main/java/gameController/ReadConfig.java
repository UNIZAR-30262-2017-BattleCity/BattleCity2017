package gameController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadConfig {

	private int[][] buttons;
	private int dificulty;
	private int resolution;
	private int sound;
	
	public ReadConfig() {
		buttons = new int[2][6];
		
		dificulty = 2;
		resolution = 3;
		sound = 1;
		
		readConfig();
	}

	public void readConfig() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Configuration.txt")); 
			String line; 
			while((line = reader.readLine()) != null){
				
				if (line.contains("CONTROLS")) {
					int[] buttonsIP = new int[6];
					int[] buttonsIIP = new int[6];
					
					String[] data = line.split("/");
					String[] controls = data[1].split("-");
					String[] p1 = controls[0].split(",");
					String[] p2 = controls[1].split(",");
					
					for (int i = 0; i < buttonsIP.length; i++) {
						buttonsIP[i] = Integer.parseInt(p1[i]);
						buttonsIIP[i] = Integer.parseInt(p2[i]);
					}
					
					buttons[0] = buttonsIP;
					buttons[1] = buttonsIIP;
				} 
				if (line.contains("DIFICULTY")) {
					String[] data = line.split("/");
					String D = data[1];
					
					dificulty = Integer.parseInt(D);
				}
				if (line.contains("RESOLUTION")) {
					String[] data = line.split("/");
					String R = data[1];
					
					resolution = Integer.parseInt(R);
				}
				if (line.contains("SOUND")) {
					String[] data = line.split("/");
					String S = data[1];
					
					sound = Integer.parseInt(S);
				}
			}
			reader.close(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void writeConfig() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Configuration.txt"));
			
			writer.write("CONTROLS/");
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					if (i == 0) {
						writer.write(String.valueOf(buttons[i][j]));
						if (j < buttons[0].length - 1) {
							writer.write(",");
						} else {
							writer.write("-");
						}
					} else {
						writer.write(String.valueOf(buttons[i][j]));
						if (j < buttons[0].length - 1) {
							writer.write(",");
						}
					}
				}
			}
			
			writer.write("\nDIFICULTY/");
			writer.write(String.valueOf(dificulty));
			
			writer.write("\nRESOLUTION/");
			writer.write(String.valueOf(resolution));
			
			writer.write("\nSOUND/");
			writer.write(String.valueOf(sound));
			
			writer.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}

	public int[][] getButtons() {
		return buttons;
	}

	public void setButtons(int[][] buttons) {
		this.buttons = buttons;
	}

	public int getDificulty() {
		return dificulty;
	}

	public void setDificulty(int dificulty) {
		this.dificulty = dificulty;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public int getSound() {
		return sound;
	}

	public void setSound(int sound) {
		this.sound = sound;
	}
}
