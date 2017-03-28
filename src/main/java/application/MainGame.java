package application;

import java.awt.Color;

import javax.swing.JFrame;
import gameController.GameControl;

public class MainGame {

	public static void main(String[] args) {
		
		JFrame jf = new JFrame ("BattleCity 2017 Unizar");
		GameControl game = new GameControl(jf);
		jf.add(game);
		//jf.setFocusable(true);
		jf.setForeground(new Color(30, 30, 30));
		jf.setSize(Properties.WIDTH+20, Properties.HEIGHT+20);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        game.start();

	}	

}
