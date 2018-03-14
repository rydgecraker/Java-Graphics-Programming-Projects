package onTheHill;

import java.awt.Color;

public class BlueButton extends Button{

	public BlueButton(int x, int y, int width, int height, String text, String keyCode) {
		super(x, y, width, height, text, keyCode);
		BlueButton.background = new Color(0, 0, 75);
		BlueButton.foreground = new Color(0, 0, 125);
	}
	
}
