package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheetControl {

	private BufferedImage spriteSheet;
	private String path;
	private int kX;
	private int kY;
	
	public SpriteSheetControl(String path, int kX, int kY) {
		this.path = path;
		this.kX = kX;
		this.kY = kY;
		spriteSheet = loadImage();
	}

	public BufferedImage loadImage(){
		URL url = this.getClass().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;		
	}
	
	public BufferedImage getSprite(int col, int row, int width, int heigth){
		BufferedImage sprite = spriteSheet.getSubimage((col*kX)-kX, (row*kY)-kY, width, heigth);
		return sprite;
	}
	
	
}
