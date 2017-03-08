package gameController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheetControl {

	private BufferedImage spriteSheet;
	private BufferedImage bulletUp, bulletDowm, bulletLeft, bulletRight;
	private BufferedImage playerUp, playerDowm, playerLeft, playerRight;
	private BufferedImage enemyUp, enemyDowm, enemyLeft, enemyRight;
	private String path;
	private int kX;
	private int kY;
	
	public SpriteSheetControl(String path, int kX, int kY) {
		this.path = path;
		this.kX = kX;
		this.kY = kY;
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
		BufferedImage sprite = spriteSheet.getSubimage((col*kX)-kX, (row*kY)-kY, width, heigth);
		return sprite;
	}
	
	public void generateTextures(){
		playerUp = getSprite(1, 1, Properties.widthTank, Properties.heighTank);
		playerDowm = getSprite(5, 1, Properties.widthTank, Properties.heighTank);
		playerLeft = getSprite(3, 1, Properties.widthTank, Properties.heighTank);
		playerRight = getSprite(7, 1, Properties.widthTank, Properties.heighTank);
		
		enemyUp = getSprite(9, 1, Properties.widthTank, Properties.heighTank);
		enemyDowm = getSprite(5, 1, Properties.widthTank, Properties.heighTank);
		enemyLeft = getSprite(3, 1, Properties.widthTank, Properties.heighTank);
		enemyRight = getSprite(7, 1, Properties.widthTank, Properties.heighTank);
		
		bulletUp = getSprite(21, 7, Properties.widthTank, Properties.heighTank);
		bulletLeft = getSprite(22, 7, Properties.widthTank, Properties.heighTank);
		bulletDowm = getSprite(23, 7, Properties.widthTank, Properties.heighTank);
		bulletRight = getSprite(24, 7, Properties.widthTank, Properties.heighTank);
		
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
			
}
