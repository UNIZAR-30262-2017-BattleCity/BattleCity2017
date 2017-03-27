package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Controls {
	private static ImageControl sscControls = new ImageControl(Properties.PATH_SS_CONTROLS);
	private static BufferedImage imgControls = sscControls.loadImage(Properties.PATH_SS_CONTROLS);
	
	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(imgControls, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
    }
}
