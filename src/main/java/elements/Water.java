package elements;

import java.awt.Graphics;
import gameController.StageControl;

public class Water extends GameElement implements StageElement{

	public Water(int row, int col, StageControl stageControl) {
		super(stageControl);
		setInitPos(row+1,col+1);
	}

	@Override
	public void updateDraw() {
		
	}

	@Override
	public void draw(Graphics g) {
		
	}
}
