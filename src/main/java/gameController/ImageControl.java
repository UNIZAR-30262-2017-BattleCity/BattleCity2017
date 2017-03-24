package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import application.Properties;

public final class ImageControl {

	private static BufferedImage spriteSheet;
	private static final int k = Properties.SIZE_SQUARE_SSC;
	private static final int w = k;
	private static final int h = k;
	
	public ImageControl(String path) {
		spriteSheet = loadImage(path);
	}

	public BufferedImage loadImage(String path){
		URL url = this.getClass().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;		
	}
	
	public static BufferedImage getSprite(int row, int col, int width, int heigth){
		BufferedImage sprite = spriteSheet.getSubimage((col*k)-k, (row*k)-k, width, heigth);
		return sprite;
	}
	
	public static BufferedImage getPaused(){
		return spriteSheet.getSubimage(304, 64, 40, 8);
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public static BufferedImage getBulletUp() {
		return getSprite(1, 22, w/4-1, h/4);
	}

	public static BufferedImage getBulletDowm() {
		return getSprite(1, 23,  w/4-1, h/4);
	}
	
	public static BufferedImage getBulletLeft() {
		return getSprite(1, 25,  w/4, h/4-1);
	}

	public static BufferedImage getBulletRight() {
		return getSprite(1, 24,  w/4, h/4-1);
	}

	public static BufferedImage getPlayerUP() {
		return getSprite(1, 1, w, h);
	}
	
	public static BufferedImage getPlayerUP1() {
		return getSprite(1, 2, w, h);
	}

	public static BufferedImage getPlayerDowm() {
		return getSprite(1, 5, w, h);
	}
	
	public static BufferedImage getPlayerDowm1() {
		return getSprite(1, 6, w, h);
	}

	public static BufferedImage getPlayerLeft() {
		return getSprite(1, 3, w, h);
	}
	
	public static BufferedImage getPlayerLeft1() {
		return getSprite(1, 4, w, h);
	}
	
	public static BufferedImage getPlayerRight() {
		return getSprite(1, 7, w, h);
	}
	
	public static BufferedImage getPlayerRight1() {
		return getSprite(1, 8, w, h);
	}

	public static BufferedImage getEnemy1Up() {
		return getSprite(1, 9, w, h);
	}
	
	public static BufferedImage getEnemy1UpB() {
		return getSprite(1, 10, w, h);
	}

	public static BufferedImage getEnemy1Dowm() {
		return getSprite(1, 13, w, h);
	}
	
	public static BufferedImage getEnemy1DowmB() {
		return getSprite(1, 14, w, h);
	}

	public static BufferedImage getEnemy1Left() {
		return getSprite(1, 11, w, h);
	}
	
	public static BufferedImage getEnemy1LeftB() {
		return getSprite(1, 12, w, h);
	}

	public static BufferedImage getEnemy1Right() {
		return getSprite(1, 15, w, h);
	}
	
	public static BufferedImage getEnemy1RightB() {
		return getSprite(1, 16, w, h);
	}
	
	public static BufferedImage getEnemy2Up() {
		return getSprite(6, 9, w, h);
	}
	
	public static BufferedImage getEnemy2UpB() {
		return getSprite(6, 10, w, h);
	}

	public static BufferedImage getEnemy2Dowm() {
		return getSprite(6, 13, w, h);
	}
	
	public static BufferedImage getEnemy2DowmB() {
		return getSprite(6, 14, w, h);
	}

	public static BufferedImage getEnemy2Left() {
		return getSprite(6, 11, w, h);
	}
	
	public static BufferedImage getEnemy2LeftB() {
		return getSprite(6, 12, w, h);
	}

	public static BufferedImage getEnemy2Right() {
		return getSprite(6, 15, w, h);
	}
	
	public static BufferedImage getEnemy2RightB() {
		return getSprite(6, 16, w, h);
	}
	
	public static BufferedImage getEnemy3Up() {
		return getSprite(5, 9, w, h);
	}
	
	public static BufferedImage getEnemy3UpB() {
		return getSprite(5, 10, w, h);
	}

	public static BufferedImage getEnemy3Dowm() {
		return getSprite(5, 13, w, h);
	}
	
	public static BufferedImage getEnemy3DowmB() {
		return getSprite(5, 14, w, h);
	}

	public static BufferedImage getEnemy3Left() {
		return getSprite(5, 11, w, h);
	}
	
	public static BufferedImage getEnemy3LeftB() {
		return getSprite(5, 12, w, h);
	}

	public static BufferedImage getEnemy3Right() {
		return getSprite(5, 15, w, h);
	}
	
	public static BufferedImage getEnemy3RightB() {
		return getSprite(5, 16, w, h);
	}

	public static BufferedImage getEnemy4Up() {
		return getSprite(8, 9, w, h);
	}
	
	public static BufferedImage getEnemy4UpB() {
		return getSprite(8, 10, w, h);
	}

	public static BufferedImage getEnemy4Dowm() {
		return getSprite(8, 13, w, h);
	}
	
	public static BufferedImage getEnemy4DowmB() {
		return getSprite(8, 14, w, h);
	}

	public static BufferedImage getEnemy4Left() {
		return getSprite(8, 11, w, h);
	}
	
	public static BufferedImage getEnemy4LeftB() {
		return getSprite(8, 12, w, h);
	}

	public static BufferedImage getEnemy4Right() {
		return getSprite(8, 15, w, h);
	}
	
	public static BufferedImage getEnemy4RightB() {
		return getSprite(8, 16, w, h);
	}
	
	public static BufferedImage getImgEagle() {
		return getSprite(3, 20, w, h);
	}
	
	public static BufferedImage getImgEagleDead() {
		return getSprite(3, 21, w, h);
	}
	
	public static BufferedImage getImgForest() {
		return getSprite(3, 18, w, h);
	}

	public static BufferedImage getImgWater() {
		return getSprite(3, 17, w, h);
	}

	public static BufferedImage getImgBrick() {
		return getSprite(1, 17, w, h);
	}

	public static BufferedImage getImgIron() {
		return getSprite(2, 17, w, h);
	}

	public static BufferedImage getImgHalfBrickH() {
		return getSprite(1, 21, w, h/2);
	}

	public static BufferedImage getImgHalfIronH() {
		return getSprite(2, 21, w, h/2);
	}

	public static BufferedImage getItemShield() {
		return getSprite(8, 17, w, h);
	}

	public static BufferedImage getItemClock() {
		return getSprite(8, 18, w, h);
	}

	public static BufferedImage getItemShovel() {
		return getSprite(8, 19, w, h);
	}

	public static BufferedImage getItemStar() {
		return getSprite(8, 20, w, h);
	}
	
	public static BufferedImage getItemGranade() {
		return getSprite(8, 21, w, h);
	}

	public static BufferedImage getItemGun() {
		return getSprite(8, 22, w, h);
	}

	public static BufferedImage getItemTank() {
		return getSprite(8, 23, w, h);
	}

	public static BufferedImage getImgHalfBrickV() {
		return getSprite(1, 20, w/2, h);
	}

	public static BufferedImage getImgHalfIronV() {
		return getSprite(2, 20, w/2, h);
	}
		
}
