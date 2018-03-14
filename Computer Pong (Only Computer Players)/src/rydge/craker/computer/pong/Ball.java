package rydge.craker.computer.pong;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ball {
	
	public int		x;
	public int		y;
	public int		width;
	public int		height;
	public Color	c;
	public int		maxWidth;
	public int		maxHeight;
	
	public boolean	left;
	public boolean	up;
	
	public int		speed;
	
	public boolean	reset;
	
	
	public Ball(int x, int y, int width, int height, Color c, int maxWidth, int maxHeight) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		
		reset = false;
		
		left = true;
		up = true;
		
		speed = 1;
		
		Random r = new Random();
		int b = r.nextInt(6);
		boolean one = false;
		boolean two = false;
		if (b == 1 || b == 3 || b == 5) {
			one = true;
		} else {
			one = false;
		}
		b = r.nextInt(6);
		if (b == 1 || b == 3 || b == 5) {
			two = true;
		} else {
			two = false;
		}
		
		setDirection(one, two);
		
	}
	
	
	public void setSpeed(int x) {
		this.speed = x;
	}
	
	
	public void bounceLeftRight() {
		if (this.left) {
			this.left = false;
		} else {
			this.left = true;
		}
	}
	
	
	public void bounceUpDown() {
		if (this.up) {
			this.up = false;
		} else {
			this.up = true;
		}
	}
	
	
	public void setDirection(boolean one, boolean two) {
		left = one;
		up = two;
	}
	
	
	public void move() {
		if (left) {
			if (x - speed <= 18) {
				reset = true;
			} else {
				x -= speed;
			}
		} else {
			if (x + width + speed >= maxWidth - 18) {
				reset = true;
			} else {
				x += speed;
			}
		}
		if (up) {
			if (y - speed <= 10) {
				reset = true;
			} else {
				y -= speed;
			}
		} else {
			if (y + height + speed >= maxHeight - 10) {
				reset = true;
			} else {
				y += speed;
			}
		}
	}
	
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
}
