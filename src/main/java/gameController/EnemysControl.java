package gameController;

public class EnemysControl extends TankControl{

	private int id;
	private int maxEnemysSimul;
    private int maxEnemysForLevel;
    
	public EnemysControl(int id, int maxEnemysSimul, int maxEnemysForLevel) {
		this.maxEnemysSimul = maxEnemysSimul;
		this.maxEnemysForLevel = maxEnemysForLevel;
		this.id = id;
	}
	
	@Override
	public void moveControl() {
		// TODO Auto-generated method stub
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMaxEnemysSimul() {
		return maxEnemysSimul;
	}

	public void setMaxEnemysSimul(int maxEnemysSimul) {
		this.maxEnemysSimul = maxEnemysSimul;
	}

	public int getMaxEnemysForLevel() {
		return maxEnemysForLevel;
	}

	public void setMaxEnemysForLevel(int maxEnemysForLevel) {
		this.maxEnemysForLevel = maxEnemysForLevel;
	}
	
}
