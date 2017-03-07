package gameController;

import javax.swing.JFrame;

public class MainGame {

	public static void main(String[] args) {
		
		JFrame jf = new JFrame ("BattleCity 2017 Unizar");
		GameControl game = new GameControl(jf);
		jf.add(game);
		jf.setSize(Properties.width, Properties.height);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        game.start();

	}

	

}
