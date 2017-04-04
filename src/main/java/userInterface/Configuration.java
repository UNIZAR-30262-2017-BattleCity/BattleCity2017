package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Configuration {

	private final BufferedImage IMG_CONFIG = ImageControl.loadImage("/resources/images/Config.png");
	private final String[] DIFICULTY = {"FACIL", "NORMAL", "DIFICIL"};
	private final String[] RESOLUTION = {"PEQUENA", "MEDIANA", "GRANDE"};
	private final String[] SOUND = {"NO", "SI"};
	
	private int opcDificulty;
	private int opcResolution;
	private int opcSound;
	
	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(IMG_CONFIG, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
        
        g.setColor(Color.white);
		g.setFont(Properties.FC_PIXEL.getFont(Font.PLAIN, 6*Properties.SCALE));
        g.drawString(DIFICULTY[opcDificulty], Properties.X_CONFIG, Properties.Y_CONFIG);
        

        g.drawString(RESOLUTION[opcResolution], Properties.X_CONFIG, Properties.Y_CONFIG + Properties.DELTA_CONFIG);
        
        
        g.drawString(SOUND[opcSound], Properties.X_CONFIG, Properties.Y_CONFIG + Properties.DELTA_CONFIG*2);
    }

	public int getOpcDificulty() {
		return opcDificulty;
	}

	public void setOpcDificulty(int opcDificulty) {
		this.opcDificulty = opcDificulty;
	}

	public int getOpcResolution() {
		return opcResolution;
	}

	public void setOpcResolution(int opcResolution) {
		this.opcResolution = opcResolution;
	}

	public int getOpcSound() {
		return opcSound;
	}

	public void setOpcSound(int opcSound) {
		this.opcSound = opcSound;
	}
}
