package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import application.Properties;
import gameController.GameControl;
import gameController.ImageControl;

public class StageGUI {	
	
	public StageGUI(){
		
	}

	public void draw(GameControl gC, Graphics g) {
        g.drawString("proque hptassss", 10,10); 
		g.setColor(Color.darkGray);
        g.fillRect(0, 0, 
        		Properties.INFO_BACKGROUND_WIDTH, 
        		Properties.HEIGHT);
        
        g.setFont(Properties.ARIAL);
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
        		Properties.Y_IP_LIFES );
        g.drawString("Score:", Properties.X_INIT_INFO,
        		Properties.Y_IP_SCORE);        
        
        if (gC.isPlayer2()) {
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
              		Properties.Y_IIP_LIFES);
              g.drawString("Score:", Properties.X_INIT_INFO, 
              		Properties.Y_IIP_SCORE);
		}
          
        g.setFont( Properties.FC_PIXEL.getFont(Font.PLAIN, Properties.FONT_LEVEL_SIZE));
        g.drawString(""+gC.getLevel(), Properties.X_LEVEL, 
        		Properties.Y_LEVEL);
        g.drawImage(ImageControl.getFlag(), 
        		Properties.X_FLAG, 
        		Properties.Y_FLAG, 
        		Properties.FLAG_WIDTH, 
        		Properties.FLAG_HEIGHT, null);
	}
	
}
