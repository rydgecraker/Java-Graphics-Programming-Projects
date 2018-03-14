package onTheHill;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RedButton extends Button{

	public RedButton(int x, int y, int width, int height, String text, String keyCode) {
		super(x, y, width, height, text, keyCode);
		RedButton.background = new Color(100, 0, 0);
		RedButton.foreground = new Color(150, 0, 0);
	}
	
	public void draw(Graphics g){
		g.setColor(bg);
		g.fillRect(x, y, width, height);
		g.setColor(fg);
		g.fillRect(x + widthChange, y + heightChange, width - (widthChange*2), height - (heightChange*2));
		stringCalculations(g, new Rectangle(x + widthChange, y + heightChange, width - widthChange, height - heightChange));
		stringY -= 5;
		drawStringOutlines(g);
		g.setColor(tc);
		g.drawString(text, stringX, stringY);
	}
	
}
