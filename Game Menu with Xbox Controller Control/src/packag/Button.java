package packag;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Button {
	
	public Color bg;
	public Color fg;
	public Color text;
	public String string;
	public int x;
	public int y;
	public int width;
	public int height;
	
	
	public Button(Color bg, Color fg, Color text, String string, int x, int y, int width, int height){
		this.bg = bg;
		this.fg = fg;
		this.text = text;
		this.string = string;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g){
		g.setColor(bg);
		g.fillRect(x, y, width, height);
		g.setColor(fg);
		g.fillRect(x + 5, y + 5, width - 10, height - 10);
		g.setColor(text);
		FontMetrics f = g.getFontMetrics();
		int i = (int) f.getStringBounds(string, 0, string.length(), g).getWidth();
		g.drawString(string, (x + width/2) - (i / 2), (y + height/2));
	}

}
