package rydge.craker.computer.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PaddleTwo {
	public int x;
	public int y;
	public int width;
	public int height;
	public Color c;
	public int maxWidth;
	
	public int counter;
	public int move;
	
	public PaddleTwo(int x, int y, int width, int height, Color c, int maxWidth){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;
		this.maxWidth = maxWidth;
		counter = 200;
		move = 0;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	public void move(int y){
		if(y > 0){
			if(this.width + this.x + y > maxWidth){
				this.x = maxWidth - width;
			}else{
				this.x += y;
			}
		}else{
			if(this.x + y < 0){
				this.x = 0;
			}else{
				this.x += y;
			}
		}
	}
	
	public void moveRandom(){
		Random r = new Random();
		int b = r.nextInt(2);
		counter++;
		if(counter >= 200){
			
			if(b == 0){
				move = 1;
			}else{
				move = -1;
			}
			counter = 0;
		}
		this.move(move);
	}
	
	public void intersect(Ball b){
		Rectangle r = new Rectangle(x, y, width, height);
		Rectangle r2 = new Rectangle(b.x, b.y, b.width, b.height);
		if(r.intersects(r2)){
			b.bounceUpDown();
		}
	}
}
