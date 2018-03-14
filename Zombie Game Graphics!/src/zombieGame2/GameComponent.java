package zombieGame2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public abstract class GameComponent extends JPanel {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color c;

	public GameComponent(int x, int y, int w, int h, Color c){
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
		this.c = c;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public Rectangle storeAsRectangle(){
		Rectangle r = new Rectangle(this.x, this.y, this.width, this.height);	
		return r;
	}
	
	public void reSize(int height, int width){
		this.height = this.height + height;
		this.width = this.width + width;
	}
	
	public void changeColor(Color color){
		c = color;
	}
	public Color getColor(){
		return c;
	}
	
	public void update(int x, int y){
		this.x = this.x + x;
		this.y = this.y + y;
	}

	public void updatePosition(int i, int j) {
		
	}
	
	
}
