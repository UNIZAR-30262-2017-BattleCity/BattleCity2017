package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import application.Properties;

public class SpriteSheetControl {

	private BufferedImage spriteSheet, imgForest, imgWater;
	private BufferedImage imgEagle, imgEagleDead;
	private BufferedImage imgBrick, imgIron, imgHalfBrick, imgHalfIron;
	private BufferedImage itemShield, itemClock, itemShovel, itemStar, itemGranade, itemTank, itemGun;
	private BufferedImage bulletUp, bulletDowm, bulletLeft, bulletRight;
	private BufferedImage playerUp, playerDowm, playerLeft, playerRight;
	private BufferedImage enemyUp, enemyDowm, enemyLeft, enemyRight;
	private String path;
	private int k,w,h;
	
	public SpriteSheetControl(String path) {
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
		
		imgHalfBrick = getSprite(1, 21, w, h/2);
		imgHalfIron = getSprite(2, 21, w, h/2);
		
		playerUp = getSprite(1, 1, w, h);
		playerDowm = getSprite(1, 5, w, h);
		playerLeft = getSprite(1, 3, w, h);
		playerRight = getSprite(1, 7, w, h);
		
		enemyUp = getSprite(1, 9, w, h);
		enemyDowm = getSprite(1, 5, w, h);
		enemyLeft = getSprite(1, 3, w, h);
		enemyRight = getSprite(1, 7, w, h);
		
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

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public BufferedImage getBulletUp() {
		return bulletUp;
	}

	public void setBulletUp(BufferedImage bulletUp) {
		this.bulletUp = bulletUp;
	}

	public BufferedImage getBulletDowm() {
		return bulletDowm;
	}

	public void setBulletDowm(BufferedImage bulletDowm) {
		this.bulletDowm = bulletDowm;
	}

	public BufferedImage getBulletLeft() {
		return bulletLeft;
	}

	public void setBulletLeft(BufferedImage bulletLeft) {
		this.bulletLeft = bulletLeft;
	}

	public BufferedImage getBulletRight() {
		return bulletRight;
	}

	public void setBulletRight(BufferedImage bulletRight) {
		this.bulletRight = bulletRight;
	}

	public BufferedImage getPlayerUP() {
		return playerUp;
	}

	public void setPlayerUP(BufferedImage playerUP) {
		this.playerUp = playerUP;
	}

	public BufferedImage getPlayerDowm() {
		return playerDowm;
	}

	public void setPlayerDowm(BufferedImage playerDowm) {
		this.playerDowm = playerDowm;
	}

	public BufferedImage getPlayerLeft() {
		return playerLeft;
	}

	public void setPlayerLeft(BufferedImage playerLeft) {
		this.playerLeft = playerLeft;
	}

	public BufferedImage getPlayerRight() {
		return playerRight;
	}

	public void setPlayerRight(BufferedImage playerRight) {
		this.playerRight = playerRight;
	}

	public BufferedImage getEnemyUp() {
		return enemyUp;
	}

	public void setEnemyUp(BufferedImage enemyUP) {
		this.enemyUp = enemyUP;
	}

	public BufferedImage getEnemyDowm() {
		return enemyDowm;
	}

	public void setEnemyDowm(BufferedImage enemyDowm) {
		this.enemyDowm = enemyDowm;
	}

	public BufferedImage getEnemyLeft() {
		return enemyLeft;
	}

	public void setEnemyLeft(BufferedImage enemyLeft) {
		this.enemyLeft = enemyLeft;
	}

	public BufferedImage getEnemyRight() {
		return enemyRight;
	}

	public void setEnemyRight(BufferedImage enemyRight) {
		this.enemyRight = enemyRight;
	}

	public BufferedImage getImgEagle() {
		return imgEagle;
	}

	public void setImgEagle(BufferedImage imgEagle) {
		this.imgEagle = imgEagle;
	}
	
	public BufferedImage getImgEagleDead() {
		return imgEagleDead;
	}
	
	public BufferedImage getImgForest() {
		return imgForest;
	}

	public BufferedImage getImgWater() {
		return imgWater;
	}

	public BufferedImage getImgBrick() {
		return imgBrick;
	}

	public BufferedImage getImgIron() {
		return imgIron;
	}

	public BufferedImage getImgHalfBrick() {
		return imgHalfBrick;
	}

	public BufferedImage getImgHalfIron() {
		return imgHalfIron;
	}

	public BufferedImage getItemShield() {
		return itemShield;
	}

	public void setItemShield(BufferedImage itemShield) {
		this.itemShield = itemShield;
	}

	public BufferedImage getItemClock() {
		return itemClock;
	}

	public void setItemClock(BufferedImage itemClock) {
		this.itemClock = itemClock;
	}

	public BufferedImage getItemShovel() {
		return itemShovel;
	}

	public void setItemShovel(BufferedImage itemShovel) {
		this.itemShovel = itemShovel;
	}

	public BufferedImage getItemStar() {
		return itemStar;
	}

	public void setItemStar(BufferedImage itemStar) {
		this.itemStar = itemStar;
	}

	public BufferedImage getItemGranade() {
		return itemGranade;
	}

	public void setItemGranade(BufferedImage itemGranade) {
		this.itemGranade = itemGranade;
	}

	public BufferedImage getItemGun() {
		return itemGun;
	}

	public void setItemGun(BufferedImage itemGun) {
		this.itemGun = itemGun;
	}

	public BufferedImage getItemTank() {
		return itemTank;
	}

	public void setItemTank(BufferedImage itemTank) {
		this.itemTank = itemTank;
	}
		
}
