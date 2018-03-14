package packag;

import java.awt.Color;
import java.awt.Graphics;

public class Crosshair {

	public int height;
	public int width;
	public Color color;
	public int x;
	public int y;
	private String s;
	
	public Crosshair(int height, int width, Color color, int x, int y){
		this.height = height;
		this.width = width;
		this.color = color;
		this.x = x;
		this.y = y;
		s = "";
	}
	
	public void setString(String s){
		this.s = s;
	}
	
	public void changeColor(Color c){
		color = c;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.drawOval((x - (width/2)), (y - (height / 2)), width, height);
		g.drawLine(x,  (y - (height / 2)), x,  (y + (height / 2)));
		g.drawLine((x - (width/2)), y, (x + (width/2)), y);
	}
	
}
