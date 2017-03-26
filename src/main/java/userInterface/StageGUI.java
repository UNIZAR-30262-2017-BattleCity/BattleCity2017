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
        		(int) (Properties.WIDTH+(Properties.WIDTH*0.05)), 
        		Properties.HEIGHT);
        
        g.setFont( new Font("Arial",Font.PLAIN, 7*Properties.SCALE));
        g.setColor(Color.black);
        
        g.drawImage(ImageControl.getIP(), 
        		(int) (Properties.X_INIT_INFO+(Properties.WIDTH*0.06)), 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.344)), 
        		(int) (Properties.WIDTH_ELEMENT_STAGE+(Properties.WIDTH*0.0147)), 
        		(int) (Properties.HEIGH_ELEMENT_STAGE/2+(Properties.HEIGHT*0.0344)), null);
        g.drawImage(ImageControl.getPlayer1UP(), Properties.X_INIT_INFO, 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.344)), 
        		Properties.WIDTH_ELEMENT_STAGE, 
        		(int) (Properties.HEIGH_ELEMENT_STAGE/2+(Properties.HEIGHT*0.0344)), null);
        g.drawString("Lifes:", Properties.X_INIT_INFO, 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.4468)));
        g.drawString("Score:", Properties.X_INIT_INFO, 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.4812)));
        
        g.drawImage(ImageControl.getIIP(), 
        		(int) (Properties.X_INIT_INFO+(Properties.WIDTH*0.0588)), 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.5155)), 
        		(int) (Properties.WIDTH_ELEMENT_STAGE+(Properties.WIDTH*0.0147)), 
        		(int) (Properties.HEIGH_ELEMENT_STAGE/2+(Properties.HEIGHT*0.0344)), null);
        g.drawImage(ImageControl.getPlayer2UP(), 
        		Properties.X_INIT_INFO, 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.509)), 
        		Properties.WIDTH_ELEMENT_STAGE, 
        		(int) (Properties.HEIGH_ELEMENT_STAGE/2+(Properties.HEIGHT*0.0344)), null);
        g.drawString("Lifes:", Properties.X_INIT_INFO, 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.619)));
        g.drawString("Score:", Properties.X_INIT_INFO, 
        		(int) (Properties.Y_INIT_INFO+(Properties.HEIGHT*0.653)));
        
        g.setFont( FC.getFont(Font.PLAIN, 17*Properties.SCALE));
        g.drawString(""+level, Properties.X_INIT_INFO, 
        		(int) (Properties.Y_FINAL_INFO+(Properties.HEIGHT*0.06)));
        g.drawImage(ImageControl.getFlag(), 
        		(int) (Properties.X_INIT_INFO+(Properties.WIDTH*0.08)), 
        		(int) (Properties.Y_FINAL_INFO-(Properties.HEIGHT*0.016)), 
        		(int) (Properties.WIDTH_ELEMENT_STAGE+(Properties.WIDTH*0.0147)), 
        		(int) (Properties.HEIGH_ELEMENT_STAGE+(Properties.HEIGHT*0.017)), null);

	}
	
}
