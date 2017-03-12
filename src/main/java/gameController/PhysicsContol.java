package gameController;

import java.util.LinkedList;

import elements.GameElement;
import elements.StageElement;

public class PhysicsContol {

	public static boolean collision(GameElement gE, LinkedList<StageElement> lSE){
		
		for (int i = 0; i < lSE.size(); i++) {
			if (gE.getBounds(gE.getWidth(),gE.getHeigth()).intersects(lSE.get(i).getBounds(gE.getWidth(),gE.getHeigth()))) {
				System.out.println("collision");
				System.out.println(gE.getClass() + " collision with " + lSE.get(i).getClass());
				return true;
			}
		}
		
		return false;
	}
	
}
