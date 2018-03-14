package onTheHill;

import java.awt.Color;

public class GreenButton extends Button{

	public GreenButton(int x, int y, int width, int height, String text, String keyCode) {
		super(x, y, width, height, text, keyCode);
		GreenButton.background = new Color(0, 50, 0);
		GreenButton.foreground = new Color(0, 100, 0);
	}

}
