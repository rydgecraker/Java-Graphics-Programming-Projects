package crakerrc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class LineInGrid {
	
	public int cnum;
	private boolean[] squares;
	public int x;
	public int y;
	public int width;
	public int height;
	public int squareWidth;
	
	public LineInGrid(int cnum, int x, int y, int width, int height){
		this.cnum = cnum;
		squares = new boolean[10];
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		squareWidth = width / 10;
	}
	
	public boolean checkForDeleteLine(){
		boolean b = false;
		int counter = 0;
		for(int i = 0; i < 10; i++){
			if(!squares[i]){
				counter++;
			}
		}	
		if(counter == 0){
			for(int i = 0; i < 10; i++){
				squares[i] = false;
			}
			b = true;
		}
		return b;
	}
	
	public Rectangle getGridPos(int index){
		return new Rectangle(x + (squareWidth * index), y, squareWidth, height);
	}
	
	public void addBlock(int position){
		squares[position] = true;
	}
	
	public void removeBlock(int position){
		squares[position] = false;
	}
	
	public boolean filledAtPos(int index){
		return squares[index];
	}
	
	public boolean isEmtpy(){
		int counter = 0;
		for(int i = 0; i < squares.length; i++){
			if(squares[i]){
				counter++;
			}
		}
		if(counter == 0){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		for(int i = 0; i < 9; i++){
			g.drawRect(x + (squareWidth * i), y, squareWidth, height);
		}
	}

	public void clearAllLines() {
		for(int i = 0; i < 10; i++){
			squares[i] = false;
		}
	}

	public void addInShit(ArrayList<TetrisBlock> blocks, int t) {
		for(int i = 0; i < blocks.size(); i++){
			for(int j = 0; j < 4; j++){
				if(blocks.get(i).blocks[j].lineNumber == t && !blocks.get(i).blocks[j].cleared){
					squares[blocks.get(i).blocks[j].posNumber] = true;
				}
			}
		}
		
	}
	
}
