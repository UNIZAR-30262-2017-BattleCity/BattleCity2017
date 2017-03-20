package elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Properties;
import gameController.ImageControl;

public class Item extends GameElement implements StageElement{
	
	private int id;
	private double posX;
	private double posY;
	private boolean isTake;
	private BufferedImage imgItem;
    private int maxTimeItemShow;
			
	public Item(int col, int row, int id, Stage stage) {
		super(stage);
		this.id = id;
		isTake = false;
		maxTimeItemShow = Properties.MAX_TIME_ITEM_SHOW;
		setInitPos(col, row);
		initItem();
	}
	
	public void initItem(){
		switch (id) {
		case 1:
			imgItem = ImageControl.getItemShield();
			break;
		case 2:
			imgItem = ImageControl.getItemClock();
			break;
		case 3:
			imgItem = ImageControl.getItemShovel();
			break;
		case 4:
			imgItem = ImageControl.getItemStar();
			break;
		case 5:
			imgItem = ImageControl.getItemGranade();
			break;
		case 6:
			imgItem = ImageControl.getItemTank();
			break;
		case 7:
			imgItem = ImageControl.getItemGun();
			break;
		}
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(imgItem, (int) posX,(int) posY, width, heigth, null);
	}
	
	public void updateDraw(){
		itemSpawned();
	}
	
	public void itemSpawned(){
		
		if(maxTimeItemShow>0){
			maxTimeItemShow--;
		}else{			
			stage.deleteItem(this);
		}
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
