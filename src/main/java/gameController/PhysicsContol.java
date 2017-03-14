package gameController;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import elements.GameElement;
import elements.Item;
import elements.Player;
import elements.StageElement;
import elements.Wall;

public class PhysicsContol {

	public static boolean collision(GameElement gE, LinkedList<StageElement> lSE){
		
		for (int i = 0; i < lSE.size(); i++) {
			if (gE.getBounds(gE.getWidth(),gE.getHeigth()).intersects(lSE.get(i).getBounds(gE.getWidth(),gE.getHeigth()))) {
				//System.out.println("collision");
				//System.out.println(gE.getClass() + " collision with " + lSE.get(i).getClass());
				return true;
			}
		}
		
		return false;
	}
	
	public static Point collisionPlayer(Player p, LinkedList<StageElement> list){
		
		for (int i = 0; i < list.size(); i++) {
			if (isIntersecs(p,list.get(i))) {
				
				if (list.get(i).getClass().equals(Item.class)) {
					list.get(i).setActive(false);
					list.get(i).getStage().ItemTaked((Item) list.get(i));
					list.remove(list.get(i));
				}else{
					return getIntersection(p,list.get(i)).getLocation();
				}
			}
		}		
		return null;
	}
	
	public static Point collisionBullet(GameElement gE, LinkedList<StageElement> lSE){
		
		for (int i = 0; i < lSE.size(); i++) {
			if (gE.getBounds(gE.getWidth(),gE.getHeigth()).intersects(
					lSE.get(i).getBounds(lSE.get(i).getWidth(),lSE.get(i).getHeigth()))) {			
				
				Rectangle r = gE.getBounds(gE.getWidth(),gE.getHeigth()).intersection(
						lSE.get(i).getBounds(lSE.get(i).getWidth(),lSE.get(i).getHeigth()));
				
				Point p = r.getLocation();
				
				return p;
			}
		}
		
		return null;
	}
	
	public static boolean isIntersecs(GameElement gE,  StageElement e){
		 return gE.getBounds(gE.getWidth(),gE.getHeigth()).intersects(
				 		e.getBounds(e.getWidth(),e.getHeigth()));
	}
	
	
	public static Rectangle getIntersection(GameElement gE,  StageElement e2){		
		return  gE.getBounds(gE.getWidth(),gE.getHeigth()).intersection(
						e2.getBounds(e2.getWidth(),e2.getHeigth()));		
	}
}
