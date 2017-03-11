package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import application.Properties;

public class SpriteSheetControl {

	private BufferedImage spriteSheet, imgForest, imgWater;
	private BufferedImage imgEagle, imgEagleDead;
	private BufferedImage imgBrick, imgIron;
	private BufferedImage item1, item2;
	private BufferedImage bulletUp, bulletDowm, bulletLeft, bulletRight;
	private BufferedImage playerUp, playerDowm, playerLeft, playerRight;
	private BufferedImage enemyUp, enemyDowm, enemyLeft, enemyRight;
	private String path;
	private int k;
	
	public SpriteSheetControl(String path) {
		this.path = path;
		this.k = Properties.SIZE_SQUARE_STAGE;
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
		
		imgEagle = getSprite(3, 20, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		imgEagleDead = getSprite(3, 21, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		imgForest = getSprite(3, 18, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		imgWater = getSprite(3, 17, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		imgBrick = getSprite(1, 17, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		imgIron = getSprite(2, 17, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		playerUp = getSprite(1, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		playerDowm = getSprite(1, 5, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		playerLeft = getSprite(1, 3, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		playerRight = getSprite(1, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		enemyUp = getSprite(1, 9, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		enemyDowm = getSprite(1, 5, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		enemyLeft = getSprite(1, 3, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		enemyRight = getSprite(1, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		bulletUp = getSprite(7, 21, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		bulletLeft = getSprite(7, 21, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		bulletDowm = getSprite(7, 22, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		bulletRight = getSprite(7, 22, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		item1 = getSprite(8, 17, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		item2 = getSprite(8, 18, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
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

	public BufferedImage getItem1() {
		return item1;
	}

	public void setItem1(BufferedImage item1) {
		this.item1 = item1;
	}

	public BufferedImage getItem2() {
		return item2;
	}

	public void setItem2(BufferedImage item2) {
		this.item2 = item2;
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
	
}
