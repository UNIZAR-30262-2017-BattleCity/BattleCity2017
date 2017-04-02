package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Configuration {

	private final BufferedImage IMG_CONFIG = ImageControl.loadImage("/resources/images/Config.png");
	
	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(IMG_CONFIG, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
        
        g.setColor(Color.white);
		g.setFont(Properties.FC_PIXEL.getFont(Font.PLAIN, 20));
        g.drawString("Facil", Properties.X_CONFIG, Properties.Y_CONFIG);
        

        g.drawString("Mediana", Properties.X_CONFIG, Properties.Y_CONFIG + Properties.DELTA_CONFIG);
        
        
        g.drawString("Si", Properties.X_CONFIG, Properties.Y_CONFIG + Properties.DELTA_CONFIG*2);
    }
}
