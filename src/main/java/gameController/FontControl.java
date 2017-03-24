package gameController;

import java.awt.Font;
import java.io.InputStream;

public class FontControl {

	public static Font ARIAL = new Font("Arial", Font.PLAIN, 20);
	private static Font font;

	public FontControl(String fontName) {    	
		try {
			InputStream is =  getClass().getResourceAsStream(fontName);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			System.err.println(fontName + " " + e);
			font = new Font("Arial", Font.PLAIN, 14);
		}
	}

	public Font getFont(int style, float size){
		Font tfont = font.deriveFont(style, size);
		return tfont;
	}
}
