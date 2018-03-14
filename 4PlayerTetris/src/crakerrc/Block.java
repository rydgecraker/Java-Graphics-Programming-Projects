package crakerrc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block {
	
	public int x;
	public int y;
	public int width;
	public int height;
	public Color fg;
	public Color bg;
	public int lineNumber;
	public int posNumber;
	public Grid grid;
	public boolean falling = true;
	
	public boolean cleared = false;
	
	public Block(Rectangle r, Color fg, Color bg, int lineNumber, int posNum, Grid grid){
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;
		this.fg = fg;
		this.bg = bg;
		this.grid = grid;
		this.lineNumber = lineNumber;
		posNumber = posNum;
	}
	
	public void setLoc(Rectangle r){
		x = r.x;
		y = r.y;
		width = r.width;
		height = r.height;
	}
	
	public boolean canFall(){
		return (lineNumber + 1 < 23 && !grid.fullGrid[lineNumber + 1].filledAtPos(posNumber)) || cleared;
	}
	
	public void fall(){
		if(canFall()){
			if(!cleared){
				lineNumber++;
				setLoc(grid.fullGrid[lineNumber].getGridPos(posNumber));
			}
		}
	}
	
	public void moveDown(){
		lineNumber++;
		setLoc(grid.fullGrid[lineNumber].getGridPos(posNumber));
	}
	
	public void moveLeft(){
		if(canMoveLeft()){
			posNumber--;
			setLoc(grid.fullGrid[lineNumber].getGridPos(posNumber));
		}
	}
	
	public boolean canMoveLeft(){
		return (posNumber > 0 && !grid.fullGrid[lineNumber].filledAtPos(posNumber - 1));
	}
	
	public void moveRight(){
		if(canMoveRight()){
			posNumber++;
			setLoc(grid.fullGrid[lineNumber].getGridPos(posNumber));
		}
	}
	
	public void moveTo(int line, int pol){
		setLoc(grid.fullGrid[line].getGridPos(pol));
		lineNumber = line;
		posNumber = pol;
	}
	
	public boolean canMoveRight(){
		return (posNumber < 9 && !grid.fullGrid[lineNumber].filledAtPos(posNumber + 1));
	}
	
	public boolean canMoveTo(int line, int pos){
		return ((line < 24) && (pos < 9) && (pos > -1) && !grid.fullGrid[line].filledAtPos(pos));
	}
	
	public void draw(Graphics2D g){
		g.setColor(bg);
		g.fillRect(x, y, width, height);
		g.setColor(fg);
		g.fillRect(x + ((int)(width * .1)), y + ((int)(height * .1)),((int)(width * .8)), ((int)(height * .8)));
	}

}
