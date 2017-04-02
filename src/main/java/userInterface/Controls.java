package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import application.Properties;
import gameController.FontControl;
import gameController.ImageControl;

public class Controls {
	private static BufferedImage imgControls = ImageControl.loadImage(Properties.PATH_SS_CONTROLS);
	private int[][] buttons;
	private String[][] lavelsButtons;
	
	public Controls() {
		lavelsButtons = new String[2][6];
		buttons = readButtons();
	}

	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(imgControls, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
        
        g.setColor(Color.white);
		g.setFont(FontControl.ARIAL);
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				if (i == 0) {
					g.drawString(lavelsButtons[i][j], Properties.X_BUTTONS_IP, Properties.Y_INIT_BUTTONS 
							+ (Properties.DELTA_BUTTONS*j));
				} else {
					g.drawString(lavelsButtons[i][j], Properties.X_BUTTONS_IIP, Properties.Y_INIT_BUTTONS 
							+ (Properties.DELTA_BUTTONS*j));
				}
			}
		}
    }
	
	public int[][] readButtons() {
		int[] buttonsIP = new int[6];
		int[] buttonsIIP = new int[6];
		int[][] buttons = new int[2][6];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("buttons.txt")); 
			String line; 
			while((line = reader.readLine()) != null){
				
					String[] data = line.split("-");
					String[] p1 = data[0].split(",");
					String[] p2 = data[1].split(",");
					
					for (int i = 0; i < buttonsIP.length; i++) {
						buttonsIP[i] = Integer.parseInt(p1[i]);
					}
					
					for (int i = 0; i < buttonsIIP.length; i++) {
						buttonsIIP[i] = Integer.parseInt(p2[i]);
					}

			}
			reader.close(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		buttons[0] = buttonsIP;
		buttons[1] = buttonsIIP;
		return buttons;
	}
	
	public void loadButtons() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				lavelsButtons[i][j] = KeyEvent.getKeyText(buttons[i][j]);
			}
		}
	}
	
	public void writeBottons() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("buttons.txt"));
			
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
		writeBottons();
	}
}
