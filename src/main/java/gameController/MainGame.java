package gameController;

import javax.swing.JFrame;

public class MainGame {

	public static void main(String[] args) {
		
		JFrame jf = new JFrame ("BattleCity 2017 Unizar");
		GameControl game = new GameControl(jf);
		jf.add(game);
		int kx = 2;
        int ky = 2;
		jf.setSize(1300*kx/4, 30+700*ky/3);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        game.start();

	}

	

}
