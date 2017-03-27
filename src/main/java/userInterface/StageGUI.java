package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import application.Properties;
import gameController.FontControl;
import gameController.ImageControl;

public class StageGUI {
	
	private static final FontControl FC= new FontControl(Properties.FONT_PIXEL);

	
	public StageGUI(int level){
		
	}

	public void draw(int level, Graphics g) {
		g.setColor(Color.darkGray);
        g.fillRect(0, 0, 
        		Properties.INFO_BACKGROUND_WIDTH, 
        		Properties.HEIGHT);
        
        g.setFont(FontControl.ARIAL);
        g.setColor(Color.black);
        
        g.drawImage(ImageControl.getIP(), 
        		Properties.X_ICON_IP, 
        		Properties.Y_ICON_IP, 
        		Properties.ICON_WIDTH, 
        		Properties.ICON_HEIGHT, null);
        g.drawImage(ImageControl.getPlayer1UP(), 
        		Properties.X_INIT_INFO, 
        		Properties.Y_ICON_IP, 
        		Properties.WIDTH_ELEMENT_STAGE, 
        		Properties.ICON_HEIGHT, null);
        g.drawString("Lifes:", 
        		Properties.X_INIT_INFO, 
        		Properties.Y_LABEL_LIFE_IP );
        g.drawString("Score:", Properties.X_INIT_INFO, 
        		Properties.Y_LABEL_SCORE_IP);
        
        g.drawImage(ImageControl.getIIP(), 
        		Properties.X_ICON_IIP, 
        		Properties.Y_ICON_IIP, 
        		Properties.ICON_WIDTH, 
        		Properties.ICON_HEIGHT, null);
        g.drawImage(ImageControl.getPlayer2UP(), 
        		Properties.X_INIT_INFO, 
        		Properties.Y_ICON_IIP, 
        		Properties.WIDTH_ELEMENT_STAGE, 
        		Properties.ICON_HEIGHT, null);
        g.drawString("Lifes:", Properties.X_INIT_INFO, 
        		Properties.Y_LABEL_LIFE_IIP);
        g.drawString("Score:", Properties.X_INIT_INFO, 
        		Properties.Y_LABEL_SCORE_IIP);
        
        g.setFont( FC.getFont(Font.PLAIN, Properties.FONT_LEVEL_SIZE));
        g.drawString(""+level, Properties.X_INIT_INFO, 
        		Properties.Y_LEVEL);
        g.drawImage(ImageControl.getFlag(), 
        		Properties.X_FLAG, 
        		Properties.Y_FLAG, 
        		Properties.FLAG_WIDTH, 
        		Properties.FLAG_HEIGHT, null);

	}
	
}
