package quest;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public abstract class GameObject {

	public int x;
	public int y;
	public int width;
	public int height;
	public int relX;
	public int relY;
	public boolean hasBeenMoved;
	
	public GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.relX = x;
		this.relY = y;
		hasBeenMoved = false;
	}
	
	public abstract void draw(Graphics g);
	
	public Rectangle getRelativeLocation(){
		return new Rectangle(relX, relY, width, height);
	}
	
	public Rectangle getAbsoluteLocation(){
		return new Rectangle(x, y, width, height);
	}
	
	public Boolean solid(){
		return false;
	}
	
	public abstract Rectangle getInteractionSpace();
	
	
}
