package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameController.SpriteSheetControl;

public class Item {
	
	private String name;
	private boolean isTake;
	private int id;
	private double posX;
	private double posY;
	private BufferedImage imgItem;
	private SpriteSheetControl ssc;
			
	public Item(int id, double posX, double posY, SpriteSheetControl ssc) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;	
		this.ssc = ssc;
		isTake = false;
	}

	public void draw(Graphics g) {
		switch (id) {
		case 1:
			imgItem = ssc.getItem1();
			break;
		case 2:
			imgItem = ssc.getItem2();
			break;
		default:
			imgItem = ssc.getItem1();
			break;
		}
		
		g.drawImage(imgItem, (int) posX,(int) posY, null);
	}
	
	public void updateDraw(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isTake() {
		return isTake;
	}
	public void setTake(boolean isTake) {
		this.isTake = isTake;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public BufferedImage getImgItem() {
		return imgItem;
	}

	public void setImgItem(BufferedImage imgItem) {
		this.imgItem = imgItem;
	}
	
	
	
}
