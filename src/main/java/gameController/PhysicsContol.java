package gameController;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import elements.Bullet;
import elements.Eagle;
import elements.Enemy;
import elements.GameElement;
import elements.Item;
import elements.Obstacle;
import elements.Player;
import elements.StageElement;
import elements.Wall;

public class PhysicsContol {

	public static Point collisionEnemy(Enemy e, LinkedList<StageElement> list){
		StageElement s;
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (!s.equals(e)&& !s.getClass().equals(Bullet.class)) {
				if (isIntersecs(e,s)) {
					if (s.getClass().equals(Item.class)) {
						return null;
					}else{
						if (s.getClass().equals(Obstacle.class)) {
							int o = list.get(i).getType();
							if (o != 1) return null;
						}
						return getIntersection(e,s).getLocation();
					}
				}
			}		
		}
		return null;
	}
	
	public static Point collisionPlayer(Player p, LinkedList<StageElement> list){
		StageElement s;
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if (!s.equals(p)&& !s.getClass().equals(Bullet.class)) {
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
		}
		return null;
	}
	
	public static void collisionBullet(Bullet b, GameElement gE, StageControl stageControl){
		LinkedList<StageElement> list;
		StageElement s;

		if (gE.getClass().equals(Player.class)) {
			list = stageControl.getMaze_Enemies();
			for (int i = 0; i < list.size(); i++) {
				s = list.get(i);
				if (!s.equals(gE) && !s.equals(b)) {
					if (isIntersecs(b,s)) {
						b.setActive(false);
						Player pl = (Player) gE;
						pl.setBulletsInProgres(pl.getBulletsInProgres()-1);
						actionBullet(true, s, stageControl);						
					}
				}
			}
		}else{
			list = stageControl.getMaze_Players();
			for (int i = 0; i < list.size(); i++) {
				s = list.get(i);
				if (isIntersecs(b,s)) {
					b.setActive(false);
					Enemy en = (Enemy) gE;
					en.setBulletsInProgres(en.getBulletsInProgres()-1);
					actionBullet(false, s, stageControl);									
				}

			}
		}
	}
	
	public static void actionBullet(boolean player, StageElement s,StageControl stageControl){
		if (s.getClass().equals(Wall.class)) {
			int w = s.getType();
			if (w !=1) return;
			else {
				stageControl.setUpdteBricks(true);
				s.setActive(false);
			}
		}else if (s.getClass().equals(Eagle.class)) {
			s.setActive(false);
		}
		
		if (player) {
			if (s.getClass().equals(Enemy.class)) {
				stageControl.deleteEnemy((Enemy) s);			
			}
			if (s.getClass().equals(Player.class)) {
				//TODO
			}
		}else{
			if (s.getClass().equals(Player.class)) {
				stageControl.getPlayer().reduceLifes();			
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
