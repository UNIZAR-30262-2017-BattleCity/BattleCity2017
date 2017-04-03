package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import application.Properties;

public final class ImageControl {

	private static final int k = Properties.SIZE_SQUARE_SSC;
	private static final int w = k;
	private static final int h = k;
	
	public ImageControl() {

	}

	public static BufferedImage loadImage(String path){
		URL url = ImageControl.class.getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static BufferedImage getSprite(BufferedImage spriteSheet, int row, int col, int width, int heigth){
		BufferedImage sprite = spriteSheet.getSubimage((col*k)-k, (row*k)-k, width, heigth);
		return sprite;
	}
	
	public static BufferedImage getPaused(BufferedImage spriteSheet){
		return spriteSheet.getSubimage(304, 64, 40, 8);
	}
	
	public static BufferedImage getEnemyMini(BufferedImage spriteSheet){
		return spriteSheet.getSubimage(352, 32, 8, 8);
	}
	
	public static BufferedImage getGameOver(BufferedImage spriteSheet){
		return spriteSheet.getSubimage(304, 72, 34, 16);
	}
	
	public static BufferedImage getFlag(){
		return getSprite(Properties.SSTANK,2, 25,  w, h);
	}

	public static BufferedImage getIP(){
		return getSprite(Properties.SSTANK,2, 23,  w, h/2);
	}
	
	public static BufferedImage getIIP(){
		return getSprite(Properties.SSTANK,2, 24,  w, h/2);
	}

	public static BufferedImage getBulletUp() {
		return getSprite(Properties.SSTANK,1, 22, w/4-1, h/4);
	}

	public static BufferedImage getBulletDowm() {
		return getSprite(Properties.SSTANK,1, 23,  w/4-1, h/4);
	}
	
	public static BufferedImage getBulletLeft() {
		return getSprite(Properties.SSTANK,1, 25,  w/4, h/4-1);
	}

	public static BufferedImage getBulletRight() {
		return getSprite(Properties.SSTANK,1, 24,  w/4, h/4-1);
	}

	public static BufferedImage getPlayer1UP() {
		return getSprite(Properties.SSTANK,1, 1, w, h);
	}
	
	public static BufferedImage getPlayer1UPB() {
		return getSprite(Properties.SSTANK,1, 2, w, h);
	}

	public static BufferedImage getPlayer1Dowm() {
		return getSprite(Properties.SSTANK,1, 5, w, h);
	}
	
	public static BufferedImage getPlayer1DowmB() {
		return getSprite(Properties.SSTANK,1, 6, w, h);
	}

	public static BufferedImage getPlayer1Left() {
		return getSprite(Properties.SSTANK,1, 3, w, h);
	}
	
	public static BufferedImage getPlayer1LeftB() {
		return getSprite(Properties.SSTANK,1, 4, w, h);
	}
	
	public static BufferedImage getPlayer1Right() {
		return getSprite(Properties.SSTANK,1, 7, w, h);
	}
	
	public static BufferedImage getPlayer1RightB() {
		return getSprite(Properties.SSTANK,1, 8, w, h);
	}

	public static BufferedImage getPlayer2UP() {
		return getSprite(Properties.SSTANK,9, 1, w, h);
	}
	
	public static BufferedImage getPlayer2UPB() {
		return getSprite(Properties.SSTANK,9, 2, w, h);
	}

	public static BufferedImage getPlayer2Dowm() {
		return getSprite(Properties.SSTANK,9, 5, w, h);
	}
	
	public static BufferedImage getPlayer2DowmB() {
		return getSprite(Properties.SSTANK,9, 6, w, h);
	}

	public static BufferedImage getPlayer2Left() {
		return getSprite(Properties.SSTANK,9, 3, w, h);
	}
	
	public static BufferedImage getPlayer2LeftB() {
		return getSprite(Properties.SSTANK,9, 4, w, h);
	}
	
	public static BufferedImage getPlayer2Right() {
		return getSprite(Properties.SSTANK,9, 7, w, h);
	}
	
	public static BufferedImage getPlayer2RightB() {
		return getSprite(Properties.SSTANK,9, 8, w, h);
	}
	
	public static BufferedImage getEnemy1Up() {
		return getSprite(Properties.SSTANK,1, 9, w, h);
	}
	
	public static BufferedImage getEnemy1UpB() {
		return getSprite(Properties.SSTANK,1, 10, w, h);
	}

	public static BufferedImage getEnemy1Dowm() {
		return getSprite(Properties.SSTANK,1, 13, w, h);
	}
	
	public static BufferedImage getEnemy1DowmB() {
		return getSprite(Properties.SSTANK,1, 14, w, h);
	}

	public static BufferedImage getEnemy1Left() {
		return getSprite(Properties.SSTANK,1, 11, w, h);
	}
	
	public static BufferedImage getEnemy1LeftB() {
		return getSprite(Properties.SSTANK,1, 12, w, h);
	}

	public static BufferedImage getEnemy1Right() {
		return getSprite(Properties.SSTANK,1, 15, w, h);
	}
	
	public static BufferedImage getEnemy1RightB() {
		return getSprite(Properties.SSTANK,1, 16, w, h);
	}
	
	public static BufferedImage getEnemy2Up() {
		return getSprite(Properties.SSTANK,6, 9, w, h);
	}
	
	public static BufferedImage getEnemy2UpB() {
		return getSprite(Properties.SSTANK,6, 10, w, h);
	}

	public static BufferedImage getEnemy2Dowm() {
		return getSprite(Properties.SSTANK,6, 13, w, h);
	}
	
	public static BufferedImage getEnemy2DowmB() {
		return getSprite(Properties.SSTANK,6, 14, w, h);
	}

	public static BufferedImage getEnemy2Left() {
		return getSprite(Properties.SSTANK,6, 11, w, h);
	}
	
	public static BufferedImage getEnemy2LeftB() {
		return getSprite(Properties.SSTANK,6, 12, w, h);
	}

	public static BufferedImage getEnemy2Right() {
		return getSprite(Properties.SSTANK,6, 15, w, h);
	}
	
	public static BufferedImage getEnemy2RightB() {
		return getSprite(Properties.SSTANK,6, 16, w, h);
	}
	
	public static BufferedImage getEnemy3Up() {
		return getSprite(Properties.SSTANK,7, 9, w, h);
	}
	
	public static BufferedImage getEnemy3UpB() {
		return getSprite(Properties.SSTANK,7, 10, w, h);
	}

	public static BufferedImage getEnemy3Dowm() {
		return getSprite(Properties.SSTANK,7, 13, w, h);
	}
	
	public static BufferedImage getEnemy3DowmB() {
		return getSprite(Properties.SSTANK,7, 14, w, h);
	}

	public static BufferedImage getEnemy3Left() {
		return getSprite(Properties.SSTANK,7, 11, w, h);
	}
	
	public static BufferedImage getEnemy3LeftB() {
		return getSprite(Properties.SSTANK,7, 12, w, h);
	}

	public static BufferedImage getEnemy3Right() {
		return getSprite(Properties.SSTANK,7, 15, w, h);
	}
	
	public static BufferedImage getEnemy3RightB() {
		return getSprite(Properties.SSTANK,7, 16, w, h);
	}

	public static BufferedImage getEnemy4RedUp() {
		return getSprite(Properties.SSTANK,12, 9, w, h);
	}
	
	public static BufferedImage getEnemy4RedUpB() {
		return getSprite(Properties.SSTANK,12, 10, w, h);
	}

	public static BufferedImage getEnemy4RedDowm() {
		return getSprite(Properties.SSTANK,12, 13, w, h);
	}
	
	public static BufferedImage getEnemy4RedDowmB() {
		return getSprite(Properties.SSTANK,12, 14, w, h);
	}

	public static BufferedImage getEnemy4RedLeft() {
		return getSprite(Properties.SSTANK,12, 11, w, h);
	}
	
	public static BufferedImage getEnemy4RedLeftB() {
		return getSprite(Properties.SSTANK,12, 12, w, h);
	}

	public static BufferedImage getEnemy4RedRight() {
		return getSprite(Properties.SSTANK,12, 15, w, h);
	}
	
	public static BufferedImage getEnemy4RedRightB() {
		return getSprite(Properties.SSTANK,12, 16, w, h);
	}
		
	public static BufferedImage getEnemy4GreenUp() {
		return getSprite(Properties.SSTANK,12, 1, w, h);
	}
	
	public static BufferedImage getEnemy4GreenUpB() {
		return getSprite(Properties.SSTANK,12, 2, w, h);
	}

	public static BufferedImage getEnemy4GreenDowm() {
		return getSprite(Properties.SSTANK,12, 5, w, h);
	}
	
	public static BufferedImage getEnemy4GreenDowmB() {
		return getSprite(Properties.SSTANK,12, 6, w, h);
	}

	public static BufferedImage getEnemy4GreenLeft() {
		return getSprite(Properties.SSTANK,12, 3, w, h);
	}
	
	public static BufferedImage getEnemy4GreenLeftB() {
		return getSprite(Properties.SSTANK,12, 4, w, h);
	}

	public static BufferedImage getEnemy4GreenRight() {
		return getSprite(Properties.SSTANK,12, 7, w, h);
	}
	
	public static BufferedImage getEnemy4GreenRightB() {
		return getSprite(Properties.SSTANK,12, 8, w, h);
	}	
	
	public static BufferedImage getEnemy4GoldUp() {
		return getSprite(Properties.SSTANK,4, 1, w, h);
	}
	
	public static BufferedImage getEnemy4GoldUpB() {
		return getSprite(Properties.SSTANK,4, 2, w, h);
	}

	public static BufferedImage getEnemy4GoldDowm() {
		return getSprite(Properties.SSTANK,4, 5, w, h);
	}
	
	public static BufferedImage getEnemy4GoldDowmB() {
		return getSprite(Properties.SSTANK,4, 6, w, h);
	}

	public static BufferedImage getEnemy4GoldLeft() {
		return getSprite(Properties.SSTANK,4, 3, w, h);
	}
	
	public static BufferedImage getEnemy4GoldLeftB() {
		return getSprite(Properties.SSTANK,4, 4, w, h);
	}

	public static BufferedImage getEnemy4GoldRight() {
		return getSprite(Properties.SSTANK,4, 7, w, h);
	}
	
	public static BufferedImage getEnemy4GoldRightB() {
		return getSprite(Properties.SSTANK,4, 8, w, h);
	}
		
	public static BufferedImage getEnemy4GrayUp() {
		return getSprite(Properties.SSTANK,4, 9, w, h);
	}
	
	public static BufferedImage getEnemy4GrayUpB() {
		return getSprite(Properties.SSTANK,4, 10, w, h);
	}

	public static BufferedImage getEnemy4GrayDowm() {
		return getSprite(Properties.SSTANK,4, 13, w, h);
	}
	
	public static BufferedImage getEnemy4GrayDowmB() {
		return getSprite(Properties.SSTANK,4, 14, w, h);
	}

	public static BufferedImage getEnemy4GrayLeft() {
		return getSprite(Properties.SSTANK,4, 11, w, h);
	}
	
	public static BufferedImage getEnemy4GrayLeftB() {
		return getSprite(Properties.SSTANK,4, 12, w, h);
	}

	public static BufferedImage getEnemy4GrayRight() {
		return getSprite(Properties.SSTANK,4, 15, w, h);
	}
	
	public static BufferedImage getEnemy4GrayRightB() {
		return getSprite(Properties.SSTANK,4, 16, w, h);
	}	
	
	public static BufferedImage getImgEagle() {
		return getSprite(Properties.SSTANK,3, 20, w, h);
	}
	
	public static BufferedImage getImgEagleDead() {
		return getSprite(Properties.SSTANK,3, 21, w, h);
	}
	
	public static BufferedImage getImgShieldA() {
		return getSprite(Properties.SSTANK,2, 22, w, h);
	}
	
	public static BufferedImage getImgShieldB() {
		return getSprite(Properties.SSTANK,3, 22, w, h);
	}
	
	public static BufferedImage getImgForest() {
		return getSprite(Properties.SSTANK,3, 18, w, h);
	}

	public static BufferedImage getImgWater() {
		return getSprite(Properties.SSTANK,3, 17, w, h);
	}

	public static BufferedImage getImgBrick() {
		return getSprite(Properties.SSTANK,1, 17, w, h);
	}

	public static BufferedImage getImgIron() {
		return getSprite(Properties.SSTANK,2, 17, w, h);
	}

	public static BufferedImage getImgHalfBrickH() {
		return getSprite(Properties.SSTANK,1, 21, w, h/2);
	}

	public static BufferedImage getImgHalfIronH() {
		return getSprite(Properties.SSTANK,2, 21, w, h/2);
	}

	public static BufferedImage getItemShield() {
		return getSprite(Properties.SSTANK,8, 17, w, h);
	}

	public static BufferedImage getItemClock() {
		return getSprite(Properties.SSTANK,8, 18, w, h);
	}

	public static BufferedImage getItemShovel() {
		return getSprite(Properties.SSTANK,8, 19, w, h);
	}

	public static BufferedImage getItemStar() {
		return getSprite(Properties.SSTANK,8, 20, w, h);
	}
	
	public static BufferedImage getItemGranade() {
		return getSprite(Properties.SSTANK,8, 21, w, h);
	}
	
	public static BufferedImage getItemTank() {
		return getSprite(Properties.SSTANK,8, 22, w, h);
	}
	
	public static BufferedImage getItemGas() {
		return getSprite(Properties.SSTANK,8, 23, w, h);
	}

	public static BufferedImage getImgHalfBrickV() {
		return getSprite(Properties.SSTANK,1, 20, w/2, h);
	}

	public static BufferedImage getImgHalfIronV() {
		return getSprite(Properties.SSTANK,2, 20, w/2, h);
	}
	
	public static BufferedImage getEffectBulletA() {
		return getSprite(Properties.SSTANK,9, 17, w, h);
	}
	
	public static BufferedImage getEffectBulletB() {
		return getSprite(Properties.SSTANK,9, 18, w, h);
	}
	
	public static BufferedImage getEffectBulletC() {
		return getSprite(Properties.SSTANK,9, 19, w, h);
	}
	
	public static BufferedImage getEffectBirdthA() {
		return getSprite(Properties.SSTANK,7, 17, w, h);
	}
	
	public static BufferedImage getEffectBirdthB() {
		return getSprite(Properties.SSTANK,7, 18, w, h);
	}
	
	public static BufferedImage getEffectBirdthC() {
		return getSprite(Properties.SSTANK,7, 19, w, h);
	}
	
	public static BufferedImage getEffectBirdthD() {
		return getSprite(Properties.SSTANK,7, 20, w, h);
	}
	
	public static BufferedImage getEffectTankA() {
		return getSprite(Properties.SSTANK,9, 20, w*2, h*2);
	}
	
	public static BufferedImage getEffectTankB() {
		return getSprite(Properties.SSTANK,9, 22, w*2, h*2);
	}
}
