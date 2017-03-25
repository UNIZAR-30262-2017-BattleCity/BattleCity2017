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
        g.fillRect(0, 0, Properties.WIDTH+20, Properties.HEIGHT);
        
        g.setFont( new Font("Arial",Font.PLAIN, 20));
        g.setColor(Color.black);
        
        g.drawImage(ImageControl.getIP(), Properties.X_INIT_INFO+40, Properties.Y_INIT_INFO+200, Properties.WIDTH_ELEMENT_STAGE+10, Properties.HEIGH_ELEMENT_STAGE/2+20, null);
        g.drawImage(ImageControl.getPlayer1UP(), Properties.X_INIT_INFO, Properties.Y_INIT_INFO+200, Properties.WIDTH_ELEMENT_STAGE, Properties.HEIGH_ELEMENT_STAGE/2+20, null);
        g.drawString("Lifes:", Properties.X_INIT_INFO, Properties.Y_INIT_INFO+260);
        g.drawString("Score:", Properties.X_INIT_INFO, Properties.Y_INIT_INFO+280);
        
        g.drawImage(ImageControl.getIIP(), Properties.X_INIT_INFO+40, Properties.Y_INIT_INFO+300, Properties.WIDTH_ELEMENT_STAGE+10, Properties.HEIGH_ELEMENT_STAGE/2+20, null);
        g.drawImage(ImageControl.getPlayer2UP(), Properties.X_INIT_INFO, Properties.Y_INIT_INFO+296, Properties.WIDTH_ELEMENT_STAGE, Properties.HEIGH_ELEMENT_STAGE/2+20, null);
        g.drawString("Lifes:", Properties.X_INIT_INFO, Properties.Y_INIT_INFO+360);
        g.drawString("Score:", Properties.X_INIT_INFO, Properties.Y_INIT_INFO+380);
        
        g.setFont( FC.getFont(Font.PLAIN, 50));
        g.drawString(""+level, Properties.X_INIT_INFO, Properties.Y_FINAL_INFO+20);
        g.drawImage(ImageControl.getFlag(), Properties.X_INIT_INFO+50, Properties.Y_FINAL_INFO-28, Properties.WIDTH_ELEMENT_STAGE+10, Properties.HEIGH_ELEMENT_STAGE+10, null);

	}
	
}
