package gameController;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import elements.Bullet;
import elements.Enemy;
import elements.GameElement;
import elements.Item;
import elements.Obstacle;
import elements.Player;
import elements.StageElement;
import elements.Wall;

public class PhysicsContol {

	public static boolean collisionEnemy(Enemy e, LinkedList<StageElement> list){
		StageElement s;
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (isIntersecs(e,s)) {
				if (s.getClass().equals(Item.class)) {
					return false;
				}
				if (s.getClass().equals(Obstacle.class)) {
					int o = list.get(i).getType();
					if (o != 1) return true;
				}
				return true;
			}
		}		
		return false;
	}
	
	public static Point collisionPlayer(Player p, LinkedList<StageElement> list){
		StageElement s;
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (isIntersecs(p,s)) {				
				if (s.getClass().equals(Item.class)) {
					s.setActive(false);
					s.getStage().ItemTaked((Item) s);
				}else{
					if (s.getClass().equals(Obstacle.class)) {
						int o = list.get(i).getType();
						if (o == 1) return null;
					}
					return getIntersection(p,s).getLocation();
				}
			}
		}		
		return null;
	}
	
	public static void collisionBullet(Bullet b, GameElement gE, LinkedList<StageElement> list){

		StageElement s;		
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (!s.equals(gE)) {
				if (isIntersecs(b,s)) {
					if (!(s.getClass().equals(Obstacle.class)) && !(s.getClass().equals(Item.class))) {
						b.setActive(false);
						if (s.getClass().equals(Wall.class)) {
							int w = list.get(i).getType();
							if (w !=1) return;
							else s.setActive(false);
						}if (s.getClass().equals(Enemy.class)) {
							if (gE.getClass().equals(Player.class)) {
								s.getStage().deleteEnemy((Enemy) s);
							}							
						}if (s.getClass().equals(Player.class)) {
							if (gE.getClass().equals(Enemy.class)) {
								//TODO kil player
							}		
						}else{
							s.setActive(false);
						}
					}
				}
			}
		}
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
