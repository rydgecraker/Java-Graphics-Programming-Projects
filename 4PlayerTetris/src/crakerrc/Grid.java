package crakerrc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
	//10 by 22 with the top two hidden
	
	public int x;
	public int y;
	public int width;
	public int height;
	public Color playerColor;
	public int playerNumber;
	public int lineHeight;
	
	public int level = 1;
	public int score = 0;
	
	public LineInGrid[] fullGrid;
	
	public TetrisBlock currentBlock;
	public ArrayList<TetrisBlock> blocks;
	
	public boolean go;
	
	public Grid(int x, int y, int width, int height, Color playerColor, int playerNumber){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		lineHeight = height / 20;
		this.playerColor = playerColor;
		this.playerNumber = playerNumber;
		blocks = new ArrayList<TetrisBlock>();
		init();
	}
	
	public void init(){
		fullGrid = new LineInGrid[24];
		for(int i = 0; i < 24; i++){
			if(i < 3){
				fullGrid[i] = new LineInGrid(i, x, y, 1, 1);
			}else{
				fullGrid[i] = new LineInGrid(i, x, y + (lineHeight * (i - 3)), width, lineHeight);
			}
		}
		currentBlock = addNewBlock();
	}
	
	public void draw(Graphics2D g, Color col, int offset){
		g.setColor(col);
		g.fillRect(x - offset, y - offset, width + offset + offset, height + offset + offset);
		g.setColor(playerColor);
		for(int i = 0; i < 20; i++){
			g.fillRect(x, y, width, lineHeight * 20);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		for(int i = 0; i < 20; i++){
			fullGrid[i + 3].draw(g);
		}
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).draw(g);
		}
		currentBlock.draw(g);
		if(go){
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("GAME OVER", x + ((int) (width * .05)), y + (height / 2));
		}
	}

	public void doEvents() {
		currentBlock.fall();
		if(!currentBlock.falling){
			blocks.add(currentBlock);
			for(int i = 0; i < 4; i++){
				fullGrid[currentBlock.blocks[i].lineNumber].addBlock(currentBlock.blocks[i].posNumber);
			}
			currentBlock = addNewBlock();
		}
		
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).fall();
		}
		checkForClear();
		checkForDeath();
		
	}
	
	public boolean checkForDeath(){
		int counter = 0;
		for(int i = 0; i < 3; i++){
			if(!fullGrid[i].isEmtpy()){
				counter++;
			}
		}
		if(counter > 0){
			go = true;
			return true;
		}else{
			return false;
		}
		
	}
	
	public TetrisBlock addNewBlock(){
		Random r = new Random();
		int x = r.nextInt(7);
		if(x == 0){
			return new ReverseLBlock(this);
		}else if(x == 1){
			return new Lblock(this);
		}else if(x == 2){
			return new SquareBlock(this);
		}else if(x == 3){
			return new SquigBlock(this);
		}else if(x == 4){
			return new ReverseSquigBlock(this);
		}else if(x == 5){
			return new LineBlock(this);
		}else{
			return new TBlock(this);
		}
	}

	public void shiftCurrentLeft() {
		currentBlock.moveLeft();
	}

	public void shiftCurrentRight() {
		currentBlock.moveRight();
	}

	public void drop() {
		currentBlock.drop();
	}

	public void RotateLeft() {
		currentBlock.rotateLeft();
	}

	public void RotateRight() {
		currentBlock.rotateRight();		
	}
	
	public void checkForClear(){
		boolean b = false;
		int counter = 0;
		for(int i = 0; i < 24; i++){
			b = fullGrid[i].checkForDeleteLine();
			if(b){
				counter++;
				for(int j = 0; j < blocks.size(); j++){
					blocks.get(j).clearBlocksOnLine(i);
					blocks.get(j).moveDown(i);
					for(int t = 0; t < fullGrid.length; t++){
						fullGrid[t].clearAllLines();
					}
					for(int t = 0; t < fullGrid.length; t++){
						fullGrid[t].addInShit(blocks, t);
					}
				}
			}
		}
		int tempScore = counter * 100;
		tempScore *= counter;
		tempScore *= level;
		score += tempScore;
		
	}

	public void setLevel(int i) {
		level = i;
	}

	public int getScore() {
		return score;
	}
	
	

}
