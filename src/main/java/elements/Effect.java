package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;
import gameController.StageControl;

public class Effect extends GameElement implements StageElement{
	
	private static final BufferedImage[] IMG_SHIELD =  {ImageControl.getImgShieldA(),
			ImageControl.getImgShieldB()};
	private static final BufferedImage[] IMG_BULLET_EXPL = { 
			ImageControl.getEffectBulletA(),ImageControl.getEffectBulletB(),ImageControl.getEffectBulletC()};
	private static final BufferedImage[] imgEffectBirth = {ImageControl.getEffectBirdthA(),
			ImageControl.getEffectBirdthB(),ImageControl.getEffectBirdthC(),ImageControl.getEffectBirdthD()};
	private static final BufferedImage[] imgEffectTank =  {ImageControl.getEffectTankA(),
			ImageControl.getEffectTankB()};
	private static final int delta = Properties.SIZE_SQUARE/2;
	private int anim1, anim2,anim3,anim4;
	private int maxTimeEffect;

	public Effect(double x, double y, int type, StageControl stageControl) {
		super(stageControl);
		this.type = type;
		initEffect(x,y);
		maxTimeEffect = 20;
	}

	public void initEffect(double x, double y){
		switch (type) {
		case 1://shield
			anim1=0;
			setPosX(x);
			setPosY(y);
			break;
		case 2://bullet bomb 
			anim2=0;
			setPosX(x-delta);
			setPosY(y-delta);
			break;
		case 3:// birth
			anim3=0;
			setPosX(x);
			setPosY(y);
			break;
		case 4:// tank bomb
			anim4=0;
			setPosX(x);
			setPosY(y);
			break;
		}
	}
	
	public void updateDraw() {
		switch (type) {
		case 1:
			anim1 = anim(anim1,1);
			break;
		case 2:
			anim2 = anim(anim2,2);
			break;
		case 3:
			anim3 = anim(anim3,3);
			break;
		case 4:
			anim4 = anim(anim4,1);
			break;
		}
		
		if(maxTimeEffect>0){
			maxTimeEffect--;
		}else{
			isActive = false;
			if (type==2||type == 4) {
				double xR = posX - Properties.X_INIT_STAGE;
				double xL = posX - Properties.X_FINAL_STAGE;
				double yUP = posY - Properties.Y_INIT_STAGE;
				double yDown = posY - Properties.Y_FINAL_STAGE;
				if (xR<Properties.SIZE_SQUARE || xL>(-Properties.SIZE_SQUARE) ||
						yUP<Properties.SIZE_SQUARE || yDown>(-Properties.SIZE_SQUARE)) {
					stageControl.setRePaintStageGUI(true);
				}				
			}
			stageControl.deleteEffect(this);
		}
		
	}

	public void draw(Graphics g) {
		
		switch (type) {
		case 1:
			g.drawImage(IMG_SHIELD[anim1], (int) posX,(int) posY, width, heigth, null);
			break;
		case 2:
			g.drawImage(IMG_BULLET_EXPL[anim2], (int) posX,(int) posY, width, heigth, null);
			break;
		case 3:
			g.drawImage(imgEffectBirth[anim3], (int) posX,(int) posY, width, heigth, null);
			break;
		case 4:
			g.drawImage(imgEffectTank[anim4], (int) posX,(int) posY, width, heigth, null);
			break;
		default:
			break;
		}
			
	}
		
	public int anim(int anim, int length){
		if(timeAnim>0){
			timeAnim--;
		}else{
			timeAnim = Properties.TIME_ANIM;
			if (anim<length) {
				anim++;
			}else{
				anim = 0;
			}
		}
		return anim;
	}	
	
}
