package crakerrc;

import java.awt.Color;

public class SquareBlock extends TetrisBlock{

	public SquareBlock(Grid grid){
		super(new Color(255, 255, 0), Color.BLACK, grid);
	}
	
	public void init() {
			blocks[0] = new Block(grid.fullGrid[2].getGridPos(4), fg, bg, 2, 4, grid);
			blocks[1] = new Block(grid.fullGrid[2].getGridPos(5), fg, bg, 2, 5, grid);
			blocks[2] = new Block(grid.fullGrid[3].getGridPos(4), fg, bg, 3, 4, grid);
			blocks[3] = new Block(grid.fullGrid[3].getGridPos(5), fg, bg, 3, 5, grid);		
	}

	@Override
	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

}
