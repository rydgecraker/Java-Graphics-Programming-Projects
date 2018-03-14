package onTheHill;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {
	
	public int x;
	public int y;
	public int width;
	public int height;
	public String text;
	public Color bg;
	public Color fg;
	public Color tc;
	public Color tbc;
	public int stringX;
	public int stringY;
	public String keyCode;
	
	public static Color background = new Color(100, 0, 0);
	public static Color foreground = new Color(150, 0, 0);
	public static Color textColor = Color.WHITE;
	public static Color textBackgroundColor = Color.BLACK;
	public static Font font = new Font("Arial", Font.BOLD, 40);
	public static int widthChange = 10;
	public static int heightChange = 10;
	
	public Button(int x, int y, int width, int height, String text, String keyCode){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.bg = background;
		this.fg = foreground;
		this.tc = textColor;
		this.tbc = textBackgroundColor;
		this.keyCode = keyCode;
	}
	
	public void invertColors(){
		bg = foreground;
		fg = background;
	}
	
	public void revertColors(){
		bg = background;
		fg = foreground;
	}
	
	public void darkColors(){
		bg = background;
		fg = background;
	}
	
	public void draw(Graphics g){
		g.setColor(bg);
		g.fillRect(x, y, width, height);
		g.setColor(fg);
		g.fillRect(x + widthChange, y + heightChange, width - (widthChange*2), height - (heightChange*2));
		stringCalculations(g, new Rectangle(x + widthChange, y + heightChange, width - widthChange, height - heightChange));
		drawStringOutlines(g);
		g.setColor(tc);
		g.drawString(text, stringX, stringY);
	}
	
	public void drawStringOutlines(Graphics g){
		g.setColor(tbc);
		g.drawString(text, stringX - 1, stringY -1);
		g.drawString(text, stringX - 1, stringY + 1);
		g.drawString(text, stringX + 1, stringY - 1);
		g.drawString(text, stringX + 1, stringY + 1);
		g.drawString(text, stringX - 2, stringY -2);
		g.drawString(text, stringX - 2, stringY + 2);
		g.drawString(text, stringX + 2, stringY - 2);
		g.drawString(text, stringX + 2, stringY + 2);
	}
	
	public void stringCalculations(Graphics g, Rectangle bounds){
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int stringWidth = fm.stringWidth(text);
		int stringAscent = fm.getAscent();
		int stringDecent = fm.getDescent();
		int stringHeight = stringDecent - stringAscent;
		
		int leftover = bounds.width - stringWidth;
		leftover = leftover / 2;
		stringX = bounds.x + leftover;
		
		leftover = bounds.height - stringHeight;
		leftover = leftover / 2;
		stringY = bounds.y + leftover;
	}

}
