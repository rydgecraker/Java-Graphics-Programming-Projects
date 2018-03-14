package com.rcs.rydge_craker_studios.basic_utilities;


public class GridOfStrings extends Grid {
	
	private String[][]	stringGrid;
	
	public GridOfStrings(int width, int height) {
		super(width, height);
	}
	
	protected void init(){
		stringGrid = new String[this.width][this.height];
	}
	
	public String getStringAt(int x, int y){
		return stringGrid[(int) (originX + x)][(int) (originY + y)];
	}
	
	public String getOrigin(){
		return stringGrid[(int) originX][(int) originY];
	}
	
	public void printGrid(){
		for (int i = 0; i < height; i++) {
			printGridXAbsolute(i);
		}
	}
	
	public void setGridAt(int x, int y, String s){
		stringGrid[(int) (x + originX)][(int) (y + originY)] = s;
	}
	
	private void printGridXAbsolute(int y){
		printGridX((int) (y - originY));
	}
	
	public void printGridX(int y){
		R.print("\n");
		for (int i = 0; i < width; i++) {
			R.print(" [ ");
			R.print(stringGrid[i][(int) (originY + y)]);
			R.print(" ]");
		}
	}
	
	public void printGridY(int x){
		R.print("\n");
		for (int i = 0; i < width; i++) {
			R.print(" [ ");
			R.print(stringGrid[(int) (x + originX)][i]);
			R.print(" ]");
		}
	}
	
	public void printGridAt(int x, int y){
		R.printf(" [ " + stringGrid[(int) (originX + x)][(int) (originY + y)] + " ]");
	}
	
}
