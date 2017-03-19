package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.SpriteSheetControl;

public class Menu {
	
	private static final SpriteSheetControl sscMenu = new SpriteSheetControl(Properties.PATH_SS_MENU);
	private static final BufferedImage imgMenu = sscMenu.loadImage();

	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(imgMenu, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
    }

}
