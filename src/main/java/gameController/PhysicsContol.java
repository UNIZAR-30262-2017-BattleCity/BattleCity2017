package gameController;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import elements.Bullet;
import elements.Eagle;
import elements.Enemy;
import elements.GameElement;
import elements.Item;
import elements.Player;
import elements.StageElement;
import elements.Tank;
import elements.Wall;
import elements.Water;

public class PhysicsContol {

	public static Point collisionEnemy(Enemy e, LinkedList<StageElement> list){
		StageElement s;
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (!s.equals(e)) {
				if (isIntersecs(e,s)) {		
					return getIntersection(e,s).getLocation();
				}
			}		
		}
		return null;
	}
	
	public static Point collisionPlayer(Player p, LinkedList<StageElement> list){
		StageElement s;
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (!s.equals(p)) {
				if (isIntersecs(p,s)) {				
					if (s.getClass().equals(Item.class)) {
						s.setActive(false);
						s.getStage().ItemTaked(p,(Item) s);
					}else{
						return getIntersection(p,s).getLocation();
					}
				}
			}		
		}
		return null;
	}
	
	public static void collisionBullet(Bullet b, Tank t, StageControl stageControl){
		LinkedList<StageElement> list;
		StageElement s;

		if (t.getClass().equals(Player.class)) {
			list = stageControl.getMaze_Enemies();
			for (int i = 0; i < list.size(); i++) {
				s = list.get(i);
				if (!s.equals(t) && !s.equals(b)) {
					if (isIntersecs(b,s)) {
						t.setBulletsInProgres(t.getBulletsInProgres()-1);
						actionBullet(t,b,true, s, stageControl);						
					}
				}
			}
		}else{
			list = stageControl.getMaze_Players();
			for (int i = 0; i < list.size(); i++) {
				s = list.get(i);
				if (isIntersecs(b,s)) {					
					t.setBulletsInProgres(t.getBulletsInProgres()-1);
					actionBullet(t,b,false, s, stageControl);									
				}

			}
		}
	}
	
	public static void actionBullet(Tank t, Bullet b, boolean player, StageElement s,StageControl stageControl){

		if (s.getClass().equals(Water.class)) {
			stageControl.setInitDraw(true);
		}else{
			b.setActive(false);
			if (s.getClass().equals(Wall.class)) {
				int w = s.getType();
				if (w !=1) return;
				else {
					stageControl.setUpdteBricks(true);
					s.setActive(false);
				}
			}else if (s.getClass().equals(Eagle.class)) {				
				s.setActive(false);
				stageControl.getgC().resultStage(3);
			}

			if (player) {
				if (s.getClass().equals(Enemy.class)) {	
					stageControl.deleteEnemy((Player) t,(Enemy) s);
				}
				if (s.getClass().equals(Player.class)) {
					//TODO
				}
			}else{			
				if (s.getClass().equals(Player.class)) {				
					if (s.equals(StageControl.getPlayers()[0])) {
						StageControl.getPlayers()[0].reduceLifes();
					}else{
						StageControl.getPlayers()[1].reduceLifes();
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
