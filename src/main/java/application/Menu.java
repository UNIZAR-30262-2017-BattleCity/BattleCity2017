package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.SpriteSheetControl;

public class Menu {
	
	private BufferedImage imgMenu, imgCursor;
	private SpriteSheetControl sscMenu;

	public Menu(SpriteSheetControl ssc){
		
		sscMenu = new SpriteSheetControl(Properties.PATH_SS_MENU);
		imgMenu = sscMenu.loadImage();
		imgCursor = ssc.getPlayerRight();
	}
	
	public void updateDraw(){
		
	}
	
	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(imgMenu, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
        
        g.drawImage(imgCursor, Properties.WIDTH/2, Properties.HEIGHT/2, Properties.WIDTH_ELEMENT_STAGE, Properties.HEIGH_ELEMENT_STAGE, null);
    }
	
}
