package quest;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Spell{
	
	public enum SpellType{DAMAGE, HEAL, NONMOVING, OTHER}

	public enum Element{FIRE, WATER, ICE, AIR, ELECTRIC, ROCK}
	
	public String name;
	public int x;
	public int y;
	public int relX;
	public int relY;
	public int width;
	public int height;
	public SpellType t;
	public boolean isFinished;
	public int mpCost;
	public boolean cast;
	public File filepath;
	public BufferedImage img;
	
	public Spell(String name, int x, int y, int width, int height, SpellType t, int mpCost, File f){
		this.filepath = f;
		this.name = name;
		this.x = x;
		this.y = y;
		this.relX = x;
		this.relY = y;
		this.width = width;
		this.height = height;
		this.t = t;
		this.mpCost = mpCost;
		getImage();
		
	}
	
	public void draw(Graphics g){
		g.drawImage(img, this.relX, this.relY, null);
	}
	
	public void getImage(){
		try {
			img = ImageIO.read(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return name;
	}
	
	public boolean equals(Object o){
		Spell s = (Spell) o;
		if(s.toString().equals(this.toString())){
			return true;
		}
		return false;
	}
	
	public Rectangle getArea(){
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getRelArea(){
		return new Rectangle(relX, relY, width, height);
	}
	
	public abstract void move();
	
	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void moveUp();
	public abstract void moveDown();
	public abstract void moveDUR();
	public abstract void moveDUL();
	public abstract void moveDDR();
	public abstract void moveDDL();
	
	public abstract void moveLeft(int x);
	public abstract void moveRight(int x);
	public abstract void moveUp(int x);
	public abstract void moveDown(int x);
	public abstract void moveDUR(int x);
	public abstract void moveDUL(int x);
	public abstract void moveDDR(int x);
	public abstract void moveDDL(int x);
	
	public abstract void moveRelLeft(int x);
	public abstract void moveRelRight(int x);
	public abstract void moveRelUp(int x);
	public abstract void moveRelDown(int x);
	public abstract void moveRelDUR(int x);
	public abstract void moveRelDUL(int x);
	public abstract void moveRelDDR(int x);
	public abstract void moveRelDDL(int x);
	
	
}
