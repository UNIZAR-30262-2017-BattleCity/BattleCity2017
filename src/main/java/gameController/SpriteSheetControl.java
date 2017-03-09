package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import application.Properties;

public class SpriteSheetControl {

	private BufferedImage spriteSheet, imgEagle, imgEagleDead;
	private BufferedImage item1, item2;
	private BufferedImage bulletUp, bulletDowm, bulletLeft, bulletRight;
	private BufferedImage playerUp, playerDowm, playerLeft, playerRight;
	private BufferedImage enemyUp, enemyDowm, enemyLeft, enemyRight;
	private String path;
	private int k;
	
	public SpriteSheetControl(String path, int k) {
		this.path = path;
		this.k = k;
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
	
	public BufferedImage getSprite(int col, int row, int width, int heigth){
		BufferedImage sprite = spriteSheet.getSubimage((col*k)-k, (row*k)-k, width, heigth);
		return sprite;
	}
	
	public void generateTextures(){
		
		imgEagle = getSprite(21, 3, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		imgEagleDead = getSprite(22, 3, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		playerUp = getSprite(1, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		playerDowm = getSprite(5, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		playerLeft = getSprite(3, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		playerRight = getSprite(7, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		enemyUp = getSprite(9, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		enemyDowm = getSprite(5, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		enemyLeft = getSprite(3, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		enemyRight = getSprite(7, 1, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		bulletUp = getSprite(21, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		bulletLeft = getSprite(22, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		bulletDowm = getSprite(23, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		bulletRight = getSprite(24, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		
		item1 = getSprite(24, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
		item2 = getSprite(24, 7, Properties.WIDTH_TANK, Properties.HEIGH_TANK);
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
					
}
