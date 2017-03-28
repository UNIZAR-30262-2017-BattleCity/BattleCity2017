package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Configuration {

	
	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(ImageControl.loadImage(Properties.PATH_SS_CONFIG), 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
    }
}
