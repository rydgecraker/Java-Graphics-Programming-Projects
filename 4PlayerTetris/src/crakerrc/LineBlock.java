package crakerrc;

import java.awt.Color;

public class LineBlock extends TetrisBlock{

	public LineBlock(Grid grid) {
		super(new Color(0, 245, 255),Color.BLACK, grid);
	}

	public void init() {
		blocks[0] = new Block(grid.fullGrid[0].getGridPos(5), fg, bg, 0, 5, grid);
		blocks[1] = new Block(grid.fullGrid[1].getGridPos(5), fg, bg, 1, 5, grid);
		blocks[2] = new Block(grid.fullGrid[2].getGridPos(5), fg, bg, 2, 5, grid);
		blocks[3] = new Block(grid.fullGrid[3].getGridPos(5), fg, bg, 3, 5, grid);	
	}

	public void rotateRight() {
		if(currentOrentation == 0){
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber + 2, blocks[0].posNumber + 2)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber + 1, blocks[1].posNumber + 1)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 1;
				blocks[0].moveTo(blocks[0].lineNumber + 2, blocks[0].posNumber + 2);
				blocks[1].moveTo(blocks[1].lineNumber + 1, blocks[1].posNumber + 1);
				blocks[3].moveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1);
			}
			
		}else{
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber - 2, blocks[0].posNumber - 2)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber - 1, blocks[1].posNumber - 1)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 0;
				blocks[0].moveTo(blocks[0].lineNumber - 2, blocks[0].posNumber - 2);
				blocks[1].moveTo(blocks[1].lineNumber - 1, blocks[1].posNumber - 1);
				blocks[3].moveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1);
			}
		}
		
	}

	public void rotateLeft() {
		if(currentOrentation == 0){
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber + 2, blocks[0].posNumber + 2)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber + 1, blocks[1].posNumber + 1)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 1;
				blocks[0].moveTo(blocks[0].lineNumber + 2, blocks[0].posNumber + 2);
				blocks[1].moveTo(blocks[1].lineNumber + 1, blocks[1].posNumber + 1);
				blocks[3].moveTo(blocks[3].lineNumber - 1, blocks[3].posNumber - 1);
			}
			
		}else{
			int counter = 0;
			if(!blocks[0].canMoveTo(blocks[0].lineNumber - 2, blocks[0].posNumber - 2)){
				counter++;
			}
			if(!blocks[1].canMoveTo(blocks[1].lineNumber - 1, blocks[1].posNumber - 1)){
				counter++;
			}
			if(!blocks[3].canMoveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1)){
				counter++;
			}
			
			if(counter == 0){
				currentOrentation = 0;
				blocks[0].moveTo(blocks[0].lineNumber - 2, blocks[0].posNumber - 2);
				blocks[1].moveTo(blocks[1].lineNumber - 1, blocks[1].posNumber - 1);
				blocks[3].moveTo(blocks[3].lineNumber + 1, blocks[3].posNumber + 1);
			}
		}
		
	}
	
}
