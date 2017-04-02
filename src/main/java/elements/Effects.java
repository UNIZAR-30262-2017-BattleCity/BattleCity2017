package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.ImageControl;
import gameController.StageControl;

public class Effects extends GameElement implements StageElement{
	
	private static final BufferedImage[] imgEffectShield =  {ImageControl.getImgShieldA(),
			ImageControl.getImgShieldB()};
	private static final BufferedImage[] imgEffectBullet = { 
			ImageControl.getEffectBulletA(),ImageControl.getEffectBulletB(),ImageControl.getEffectBulletC()};
	private static final BufferedImage[] imgEffectBirth = {ImageControl.getEffectBirdthA(),
			ImageControl.getEffectBirdthB(),ImageControl.getEffectBirdthC(),ImageControl.getEffectBirdthD()};
	private static final BufferedImage[] imgEffectTank =  {ImageControl.getEffectTankA(),
			ImageControl.getEffectTankB()};

	public Effects(int col, int row,int type, StageControl stageControl) {
		super(stageControl);
		this.type = type;
		setInitPos(col, row);
		initEffect();
	}

	public void initEffect(){
		switch (type) {
		case 1:

			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		default:
			break;
		}
	}
	
	public void updateDraw() {
		
		
	}

	public void draw(Graphics g) {
		
		switch (type) {
		case 1:
			g.drawImage(imgEffectShield[0], (int) posX,(int) posY, width, heigth, null);
			break;
		case 2:
			g.drawImage(imgEffectBullet[0], (int) posX,(int) posY, width, heigth, null);
			break;
		case 3:
			g.drawImage(imgEffectBirth[0], (int) posX,(int) posY, width, heigth, null);
			break;
		case 4:
			g.drawImage(imgEffectTank[0], (int) posX,(int) posY, width, heigth, null);
			break;
		default:
			break;
		}
			
	}	
	
}
