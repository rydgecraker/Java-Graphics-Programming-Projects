package crakerrc;

import java.awt.Color;

public class SquigBlock extends TetrisBlock{

	public SquigBlock(Grid grid) {
		super(new Color(0, 255, 0), Color.BLACK, grid);
	}

	public void init() {
		blocks[0] = new Block(grid.fullGrid[2].getGridPos(4), fg, bg, 2, 4, grid);
		blocks[1] = new Block(grid.fullGrid[2].getGridPos(5), fg, bg, 2, 5, grid);
		blocks[2] = new Block(grid.fullGrid[3].getGridPos(4), fg, bg, 3, 4, grid);
		blocks[3] = new Block(grid.fullGrid[3].getGridPos(3), fg, bg, 3, 3, grid);	
	}

	public void rotateRight() {
		if(currentOrentation == 0){
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber + 1, blocks[0].posNumber - 1)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber + 0, blocks[1].posNumber - 2)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 1;
				blocks[0].moveTo(blocks[0].lineNumber + 1, blocks[0].posNumber - 1);
				blocks[1].moveTo(blocks[1].lineNumber + 0, blocks[1].posNumber - 2);
				blocks[3].moveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1);
			}
			
		}else{
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber - 1, blocks[0].posNumber + 1)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber + 0, blocks[1].posNumber + 2)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 0;
				blocks[0].moveTo(blocks[0].lineNumber - 1, blocks[0].posNumber + 1);
				blocks[1].moveTo(blocks[1].lineNumber + 0, blocks[1].posNumber + 2);
				blocks[3].moveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1);
			}
		}
		
	}

	public void rotateLeft() {
		if(currentOrentation == 0){
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber + 1, blocks[0].posNumber - 1)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber + 0, blocks[1].posNumber - 2)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 1;
				blocks[0].moveTo(blocks[0].lineNumber + 1, blocks[0].posNumber - 1);
				blocks[1].moveTo(blocks[1].lineNumber + 0, blocks[1].posNumber - 2);
				blocks[3].moveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1);
			}
			
		}else{
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber - 1, blocks[0].posNumber + 1)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber + 0, blocks[1].posNumber + 2)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 0;
				blocks[0].moveTo(blocks[0].lineNumber - 1, blocks[0].posNumber + 1);
				blocks[1].moveTo(blocks[1].lineNumber + 0, blocks[1].posNumber + 2);
				blocks[3].moveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1);
			}
		}
		
	}

}
