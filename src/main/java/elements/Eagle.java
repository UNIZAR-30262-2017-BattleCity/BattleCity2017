package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.ImageControl;
import gameController.StageControl;

public class Eagle extends GameElement implements StageElement{
		
	private static final BufferedImage imgEagle = ImageControl.getImgEagle();
	private static final BufferedImage imgEagleDead = ImageControl.getImgEagleDead();
	
	public Eagle(int row, int col, StageControl stageControl) {
		super(stageControl);
		setInitPos(row+1,col+1);
	}

	public void updateDraw() {
	}
	
	public void draw(Graphics g) {
		
		if (isActive) {
			g.drawImage(imgEagle, (int) posX,(int) posY,  width, heigth, null);
		}else{
			g.drawImage(imgEagleDead, (int) posX,(int) posY,  width, heigth, null);
		}
		
	}
		
}
