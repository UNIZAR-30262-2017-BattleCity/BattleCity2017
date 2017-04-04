package userInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Controls {
	private final BufferedImage imgControls = ImageControl.loadImage("/resources/images/Controls.png");
	private int[][] buttons;
	private String[][] lavelsButtons;
	
	public Controls() {
		lavelsButtons = new String[2][6];
	}

	public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Properties.WIDTH, Properties.HEIGHT);
        g.drawImage(imgControls, 0, 0, Properties.WIDTH, Properties.HEIGHT, null);
        
        g.setColor(Color.white);
		g.setFont(Properties.ARIAL);
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				if (i == 0) {
					g.drawString(lavelsButtons[i][j], Properties.X_BUTTONS_IP, Properties.Y_INIT_BUTTONS 
							+ (Properties.DELTA_BUTTONS*j));
				} else {
					g.drawString(lavelsButtons[i][j], Properties.X_BUTTONS_IIP, Properties.Y_INIT_BUTTONS 
							+ (Properties.DELTA_BUTTONS*j));
				}
			}
		}
    }
	
	public void loadButtons() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				lavelsButtons[i][j] = KeyEvent.getKeyText(buttons[i][j]);
			}
		}
	}

	public int[][] getButtons() {
		return buttons;
	}

	public void setButtons(int[][] buttons) {
		this.buttons = buttons;
	}
}
