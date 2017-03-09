package application;

import javax.swing.JFrame;

import gameController.GameControl;

public class MainGame {

	public static void main(String[] args) {
		
		JFrame jf = new JFrame ("BattleCity 2017 Unizar");
		GameControl game = new GameControl(jf);
		jf.add(game);
		jf.setSize(Properties.WIDTH, Properties.HEIGHT);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        game.start();

	}

	

}
