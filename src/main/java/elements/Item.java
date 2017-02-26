package elements;

public class Item {
	
	private String name;
	private boolean status;
	private int id;
	private int positionX;
	private int positionY;
	
	public Item(String name, boolean status, int id, int positionX, int positionY) {
		super();
		this.name = name;
		this.status = status;
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	

}
