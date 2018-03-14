package crakerrc;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class TetrisBlock {
	
	public int currentOrentation;
	
	public Color fg;
	public Color bg;
	
	public Block[] blocks;
	public Grid grid;
	public boolean[] cleared;
	
	public boolean falling = true;
	
	public TetrisBlock(Color fg, Color bg, Grid grid){
		this.fg = fg;
		this.bg = bg;
		blocks = new Block[4];
		cleared = new boolean[4];
		this.grid = grid;
		currentOrentation = 0;
		init();
	}
	
	public void draw(Graphics2D g){
		for(int i = 0; i < 4; i++){
			if(!cleared[i]){
				blocks[i].draw(g);
			}
		}
	}
	
	public void clearBlocksOnLine(int line){
		if(blocks[0].lineNumber == line){
			cleared[0] = true;
			blocks[0].cleared = true;
		}
		if(blocks[1].lineNumber == line){
			cleared[1] = true;
			blocks[1].cleared = true;
		}
		if(blocks[2].lineNumber == line){
			cleared[2] = true;
			blocks[2].cleared = true;
		}
		if(blocks[3].lineNumber == line){
			cleared[3] = true;
			blocks[3].cleared = true;
		}
	}
	
	public void drop(){
		while(canFall()){
			fall();
		}
	}
	
	public boolean canFall(){
		boolean b = true;
		int counter = 0;
		for(int i = 0; i < 4; i++){
			if(!blocks[i].canFall()){
				counter++;
				if(cleared[i]){
					counter--;
				}
			}
		}
		if(counter != 0){
			b = false;
		}
		return b;
	}
	
	public void fall(){
		if(canFall()){
			for(int i = 0; i < 4; i++){
				blocks[i].fall();
			}
		}else{
			falling = false;
		}
	}
	
	public void moveDown(int line){
		for(int i = 0; i < 4; i++){
			if(blocks[i].lineNumber <= line){
				blocks[i].moveDown();
			}
		}
	}
	
	public boolean canRight(){
		return ((blocks[0].canMoveRight() || cleared[0] == true) && (blocks[1].canMoveRight() || cleared[1] == true) && (blocks[2].canMoveRight() || cleared[2] == true) && (blocks[3].canMoveRight() || cleared[3] == true));
	}
	
	public void moveRight(){
		if(canRight()){
			for(int i = 0; i < 4; i++){
				if(!cleared[0]){
					blocks[i].moveRight();
				}
			}
		}
	}
	
	public boolean canLeft(){
		return (blocks[0].canMoveLeft() && blocks[1].canMoveLeft() && blocks[2].canMoveLeft() && blocks[3].canMoveLeft());
	}
	
	public void moveLeft(){
		if(canLeft()){
			for(int i = 0; i < 4; i++){
				blocks[i].moveLeft();
			}
		}
	}
	
	public abstract void init();
	
	public abstract void rotateRight();
	
	public abstract void rotateLeft();

}
