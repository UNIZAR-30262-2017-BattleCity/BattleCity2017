package elements;

import java.awt.Graphics;

import gameController.StageControl;

public class Ice extends GameElement implements StageElement{

	public Ice(int row, int col, StageControl stageControl) {
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
