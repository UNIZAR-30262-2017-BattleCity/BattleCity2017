package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Menu {
	
	private static final BufferedImage imgMenu = ImageControl.loadImage(Properties.PATH_SS_MENU);

	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH+20, Properties.HEIGHT);
        g.drawImage(imgMenu, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
    }

}
