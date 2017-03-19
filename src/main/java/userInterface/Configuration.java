package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.SpriteSheetControl;

public class Configuration {
	
	private static SpriteSheetControl sscConfig = new SpriteSheetControl(Properties.PATH_SS_CONFIG);
	private static BufferedImage imgConfig = sscConfig.loadImage();
	
	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(imgConfig, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
    }
}
