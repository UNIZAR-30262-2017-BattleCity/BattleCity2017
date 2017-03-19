package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import application.Properties;

public class ImageControl {

	private static BufferedImage spriteSheet, imgForest, imgWater;
	private static BufferedImage imgEagle, imgEagleDead;
	private static BufferedImage imgBrick, imgIron, imgHalfBrickH, imgHalfIronH, imgHalfBrickV, imgHalfIronV;
	private static BufferedImage itemShield, itemClock, itemShovel, itemStar, itemGranade, itemTank, itemGun;
	private static BufferedImage bulletUp, bulletDowm, bulletLeft, bulletRight;
	private static BufferedImage playerRight, playerUp, playerDowm, playerLeft;
	private static BufferedImage enemyUp, enemyDowm, enemyLeft, enemyRight;
	private String path;
	private int k,w,h;
	
	public ImageControl(String path) {
		this.path = path;
		this.k = Properties.SIZE_SQUARE_SSC;
		this.w = k;
		this.h = k;
		spriteSheet = loadImage();
		generateTextures();
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
	
	public BufferedImage getSprite(int row, int col, int width, int heigth){
		BufferedImage sprite = spriteSheet.getSubimage((col*k)-k, (row*k)-k, width, heigth);
		return sprite;
	}
	
	public void generateTextures(){
		
		imgEagle = getSprite(3, 20, w, h);
		imgEagleDead = getSprite(3, 21, w, h);
		
		imgForest = getSprite(3, 18, w, h);
		imgWater = getSprite(3, 17, w, h);
		
		imgBrick = getSprite(1, 17, w, h);
		imgIron = getSprite(2, 17, w, h);
		
		imgHalfBrickH = getSprite(1, 21, w, h/2);
		imgHalfIronH = getSprite(2, 21, w, h/2);
		imgHalfBrickV = getSprite(1, 20, w/2, h);
		imgHalfIronV = getSprite(2, 20, w/2, h);
		
		playerUp = getSprite(1, 1, w, h);
		playerDowm = getSprite(1, 5, w, h);
		playerLeft = getSprite(1, 3, w, h);
		playerRight = getSprite(1, 7, w, h);
		
		enemyUp = getSprite(1, 9, w, h);
		enemyDowm = getSprite(1, 13, w, h);
		enemyLeft = getSprite(1, 11, w, h);
		enemyRight = getSprite(1, 15, w, h);
		
		bulletUp = getSprite(7, 21, w, h);
		bulletLeft = getSprite(7, 21, w, h);
		bulletDowm = getSprite(7, 22, w, h);
		bulletRight = getSprite(7, 22, w, h);
		
		itemShield = getSprite(8, 17, w, h);
		itemClock = getSprite(8, 18, w, h);
		itemShovel = getSprite(8, 19, w, h);
		itemStar = getSprite(8, 20, w, h);
		itemGranade = getSprite(8, 21, w, h);
		itemTank = getSprite(8, 22, w, h);
		itemGun = getSprite(8, 23, w, h);
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public BufferedImage getBulletUp() {
		return bulletUp;
	}

	public BufferedImage getBulletDowm() {
		return bulletDowm;
	}
	
	public BufferedImage getBulletLeft() {
		return bulletLeft;
	}

	public BufferedImage getBulletRight() {
		return bulletRight;
	}

	public BufferedImage getPlayerUP() {
		return playerUp;
	}

	public BufferedImage getPlayerDowm() {
		return playerDowm;
	}

	public BufferedImage getPlayerLeft() {
		return playerLeft;
	}

	public BufferedImage getPlayerRight() {
		return playerRight;
	}

	public BufferedImage getEnemyUp() {
		return enemyUp;
	}

	public  BufferedImage getEnemyDowm() {
		return enemyDowm;
	}

	public BufferedImage getEnemyLeft() {
		return enemyLeft;
	}

	public BufferedImage getEnemyRight() {
		return enemyRight;
	}

	public BufferedImage getImgEagle() {
		return imgEagle;
	}
	
	public BufferedImage getImgEagleDead() {
		return imgEagleDead;
	}
	
	public BufferedImage getImgForest() {
		return imgForest;
	}

	public  BufferedImage getImgWater() {
		return imgWater;
	}

	public BufferedImage getImgBrick() {
		return imgBrick;
	}

	public BufferedImage getImgIron() {
		return imgIron;
	}

	public BufferedImage getImgHalfBrickH() {
		return imgHalfBrickH;
	}

	public BufferedImage getImgHalfIronH() {
		return imgHalfIronH;
	}

	public BufferedImage getItemShield() {
		return itemShield;
	}

	public BufferedImage getItemClock() {
		return itemClock;
	}

	public BufferedImage getItemShovel() {
		return itemShovel;
	}

	public BufferedImage getItemStar() {
		return itemStar;
	}
	
	public BufferedImage getItemGranade() {
		return itemGranade;
	}

	public BufferedImage getItemGun() {
		return itemGun;
	}

	public BufferedImage getItemTank() {
		return itemTank;
	}

	public BufferedImage getImgHalfBrickV() {
		return imgHalfBrickV;
	}

	public BufferedImage getImgHalfIronV() {
		return imgHalfIronV;
	}
		
}
