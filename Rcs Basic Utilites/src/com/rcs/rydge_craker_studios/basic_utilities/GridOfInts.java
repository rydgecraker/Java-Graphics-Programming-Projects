package com.rcs.rydge_craker_studios.basic_utilities;

import com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator.SubBiome;
import com.rcs.rydge_craker_studios.basic_utilities.procedural_world_generator.SubBiomeNames;



public class GridOfInts extends Grid {
	
	public static final int	TOP_RIGHT		= 0;
	public static final int	BOTTOM_RIGHT	= 1;
	public static final int	BOTTOM_LEFT		= 2;
	public static final int	TOP_LEFT		= 3;
	
	
	public static final int	TOP				= 0;
	public static final int	BOTTOM			= 1;
	public static final int	LEFT			= 2;
	public static final int	RIGHT			= 3;
	
	private int[][]			intGrid;
	
	public GridOfInts(int width, int height) {
		super(width, height);
	}
	
	protected void init(){
		intGrid = new int[this.width][this.height];
	}
	
	public int getValueAt(int x, int y){
		return intGrid[(int) (originX + x)][(int) (originY + y)];
	}
	
	public int getOrigin(){
		return intGrid[(int) originX][(int) originY];
	}
	
	public void printGrid(){
		for (int i = 0; i < height; i++) {
			printGridXAbsolute(i);
		}
	}
	
	public void setGridAt(int x, int y, int num){
		intGrid[(int) (x + originX)][(int) (y + originY)] = num;
	}
	
	private void printGridXAbsolute(int y){
		printGridX((int) (y - originY));
	}
	
	public void printGridX(int y){
		R.print("\n");
		for (int i = 0; i < width; i++) {
			R.print(" [ ");
			R.print(intGrid[i][(int) (originY + y)] + "");
			R.print(" ]");
		}
	}
	
	public void printGridY(int x){
		R.print("\n");
		for (int i = 0; i < width; i++) {
			R.print(" [ ");
			R.print(intGrid[(int) (x + originX)][i] + "");
			R.print(" ]");
		}
	}
	
	public void printGridAt(int x, int y){
		R.printf(" [ " + intGrid[(int) (originX + x)][(int) (originY + y)] + " ]");
	}
	
	
	public float getAverageValue(){
		float avg = 0;
		float counter = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				avg += intGrid[i][j];
				counter++;
			}
		}
		return (avg / counter);
	}
	
	public boolean isTouching(int num, int xPos, int yPos){
		if(xPos - 1 > -1 * originX && getValueAt(xPos - 1, yPos) == num) {
			return true;
		}
		if(xPos + 1 < originX && getValueAt(xPos + 1, yPos) == num) {
			return true;
		}
		if(yPos + 1 < originY && getValueAt(xPos, yPos + 1) == num) {
			return true;
		}
		if(yPos - 1 > -1 * originY && getValueAt(xPos, yPos - 1) == num) {
			return true;
		}
		return false;
	}
	
	
	public boolean isSurroundedBy(int num, int xPos, int yPos, int maxSize){
		boolean b = false;
		if((xPos - 1 < -1 * maxSize) || (xPos - 1 > -1 * originX && getValueAt(xPos - 1, yPos) == num)) {
			b = true;
		} else {
			b = false;
		}
		if((xPos + 1 > maxSize) || (xPos + 1 < originX && getValueAt(xPos + 1, yPos) == num)) {
			b = true;
		} else {
			b = false;
		}
		if((yPos + 1 > maxSize) || (yPos + 1 < originY && getValueAt(xPos, yPos + 1) == num)) {
			b = true;
		} else {
			b = false;
		}
		if((yPos - 1 < -1 * maxSize) || (yPos - 1 > -1 * originY && getValueAt(xPos, yPos - 1) == num)) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}
	
	public int getModeValue(){
		int num = 0;
		int occurances = getOccurances(num);
		int temp = 0;
		for (int i = 1; i < 10; i++) {
			temp = getOccurances(i);
			if(temp > occurances) {
				occurances = temp;
				num = i;
			}
		}
		
		return num;
	}
	
	private int getOccurances(int x){
		int counter = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(intGrid[i][j] == x) {
					counter++;
				}
			}
		}
		
		return counter;
	}
	
	private int getXFromAbsoluteX(int absX){
		if(absX < originX) {
			return (int) (originX - absX);
		}
		if(absX > originX) {
			return (int) (absX - originX);
		}
		
		return 0;
	}
	
	private int getYFromAbsoluteY(int absY){
		if(absY < originY) {
			return (int) (originY - absY);
		}
		if(absY > originY) {
			return (int) (absY - originY);
		}
		
		return 0;
	}
	
	
	
	public String getOccuranceStats(){
		int[] temp = new int[10];
		
		for (int i = 0; i < 10; i++) {
			temp[i] = getOccurances(i);
		}
		
		float f = 0;
		for (int i = 0; i < 10; i++) {
			f += temp[i];
		}
		
		String s = "Number = " + SubBiomeNames.values()[0] + " | Occurances = " + getOccurances(0) + " | "
				+ R.FormatNumber.roundToDecimalPlaces((temp[0] / f) * 100, 2) + " % of all biomes";
		for (int i = 1; i < 10; i++) {
			s += "\nNumber = " + SubBiomeNames.values()[i] + " | Occurances = " + getOccurances(i) + " | "
					+ R.FormatNumber.roundToDecimalPlaces((temp[i] / f) * 100, 2) + " % of all biomes";
		}
		
		return s;
	}
	
	public void surround(int typeSub, int TypeSurround, int maxSize){
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(getValueAt(getXFromAbsoluteX(i), getYFromAbsoluteY(j)) == typeSub) {
					if(isSurroundedBy(TypeSurround, getXFromAbsoluteX(i), getYFromAbsoluteY(j), maxSize)) {
						setGridAt(getXFromAbsoluteX(i), getYFromAbsoluteY(j), TypeSurround);
					}
				}
			}
		}
	}
	
	
	public void surround(int typeSub, int TypeSurround, int changeType, int maxSize){
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(getValueAt(getXFromAbsoluteX(i), getYFromAbsoluteY(j)) == typeSub) {
					if(isSurroundedBy(TypeSurround, getXFromAbsoluteX(i), getYFromAbsoluteY(j), maxSize)) {
						setGridAt(getXFromAbsoluteX(i), getYFromAbsoluteY(j), changeType);
					}
				}
			}
		}
	}
	
	private void corerBorder(int x, int y, int corner, int overrider){
		if(corner == TOP_RIGHT) {
			if(getValueAt(x, y) != overrider) {
				if(getValueAt(x - 1, y) == overrider || getValueAt(x, y - 1) == overrider) {
					setGridAt(x, y, overrider);
					setGridAt(x, y + 1, overrider);
					setGridAt(x + 1, y, overrider);
					setGridAt(x + 1, y + 1, overrider);
					
				} else {
					setGridAt(x, y + 1, getValueAt(x, y));
					setGridAt(x + 1, y, getValueAt(x, y));
					setGridAt(x + 1, y + 1, overrider);
				}
			} else {
				if(getValueAt(x - 1, y) == overrider && getValueAt(x, y - 1) == overrider) {
					setGridAt(x, y + 1, overrider);
					setGridAt(x + 1, y, overrider);
					setGridAt(x + 1, y + 1, SubBiome.UNKNOWN);
				} else {
					setGridAt(x, y + 1, overrider);
					setGridAt(x + 1, y, overrider);
					setGridAt(x + 1, y + 1, overrider);
				}
			}
		} else if(corner == BOTTOM_RIGHT) {
			if(getValueAt(x, y) != overrider) {
				if(getValueAt(x - 1, y) == overrider || getValueAt(x, y + 1) == overrider) {
					setGridAt(x, y, overrider);
					setGridAt(x, y - 1, overrider);
					setGridAt(x + 1, y, overrider);
					setGridAt(x + 1, y - 1, overrider);
					
				} else {
					setGridAt(x, y - 1, getValueAt(x, y));
					setGridAt(x + 1, y, getValueAt(x, y));
					setGridAt(x + 1, y - 1, overrider);
				}
			} else {
				if(getValueAt(x - 1, y) == overrider && getValueAt(x, y + 1) == overrider) {
					setGridAt(x, y - 1, overrider);
					setGridAt(x + 1, y, overrider);
					setGridAt(x + 1, y - 1, SubBiome.UNKNOWN);
				} else {
					setGridAt(x, y - 1, overrider);
					setGridAt(x + 1, y, overrider);
					setGridAt(x + 1, y - 1, overrider);
				}
			}
		} else if(corner == BOTTOM_LEFT) {
			if(getValueAt(x, y) != overrider) {
				if(getValueAt(x + 1, y) == overrider || getValueAt(x, y + 1) == overrider) {
					setGridAt(x, y, overrider);
					setGridAt(x, y - 1, overrider);
					setGridAt(x - 1, y, overrider);
					setGridAt(x - 1, y - 1, overrider);
					
				} else {
					setGridAt(x, y - 1, getValueAt(x, y));
					setGridAt(x - 1, y, getValueAt(x, y));
					setGridAt(x - 1, y - 1, overrider);
				}
			} else {
				if(getValueAt(x + 1, y) == overrider && getValueAt(x, y + 1) == overrider) {
					setGridAt(x, y - 1, overrider);
					setGridAt(x - 1, y, overrider);
					setGridAt(x - 1, y - 1, SubBiome.UNKNOWN);
				} else {
					setGridAt(x, y - 1, overrider);
					setGridAt(x - 1, y, overrider);
					setGridAt(x - 1, y - 1, overrider);
				}
			}
		} else if(corner == TOP_LEFT) {
			if(getValueAt(x, y) != overrider) {
				if(getValueAt(x + 1, y) == overrider || getValueAt(x, y - 1) == overrider) {
					setGridAt(x, y, overrider);
					setGridAt(x, y + 1, overrider);
					setGridAt(x - 1, y, overrider);
					setGridAt(x - 1, y + 1, overrider);
					
				} else {
					setGridAt(x, y + 1, getValueAt(x, y));
					setGridAt(x - 1, y, getValueAt(x, y));
					setGridAt(x - 1, y + 1, overrider);
				}
			} else {
				if(getValueAt(x + 1, y) == overrider && getValueAt(x, y - 1) == overrider) {
					setGridAt(x, y + 1, overrider);
					setGridAt(x - 1, y, overrider);
					setGridAt(x - 1, y - 1, SubBiome.UNKNOWN);
				} else {
					setGridAt(x, y + 1, overrider);
					setGridAt(x - 1, y, overrider);
					setGridAt(x - 1, y + 1, overrider);
				}
			}
		}
	}
	
	private void border(int x, int y, int side, int override){
		if(side == TOP) {
			if(getValueAt(x, y) != override) {
				if(getValueAt(x - 1, y) != override && getValueAt(x + 1, y) != override) {
					setGridAt(x, y + 1, getValueAt(x, y));
				} else {
					setGridAt(x, y + 1, override);
				}
			} else {
				setGridAt(x, y + 1, override);
			}
		} else if(side == BOTTOM) {
			if(getValueAt(x, y) != override) {
				if(getValueAt(x - 1, y) != override && getValueAt(x + 1, y) != override) {
					setGridAt(x, y - 1, getValueAt(x, y));
				} else {
					setGridAt(x, y - 1, override);
				}
			} else {
				setGridAt(x, y - 1, override);
			}
		} else if(side == LEFT) {
			if(getValueAt(x, y) != override) {
				if(getValueAt(x, y - 1) != override && getValueAt(x, y + 1) != override) {
					setGridAt(x - 1, y, getValueAt(x, y));
				} else {
					setGridAt(x - 1, y, override);
				}
			} else {
				setGridAt(x - 1, y, override);
			}
		} else if(side == RIGHT) {
			if(getValueAt(x, y) != override) {
				if(getValueAt(x, y - 1) != override && getValueAt(x, y + 1) != override) {
					setGridAt(x + 1, y, getValueAt(x, y));
				} else {
					setGridAt(x + 1, y, override);
				}
			} else {
				setGridAt(x + 1, y, override);
			}
		}
	}
	
	public void determineBorders(int x, int y, int size, int overrider){
		if(x == size && y == size) {
			corerBorder(x, y, TOP_RIGHT, overrider);
		} else if(x == size && y == -1 * size) {
			corerBorder(x, y, BOTTOM_RIGHT, overrider);
		} else if(x == -1 * size && y == size) {
			corerBorder(x, y, TOP_LEFT, overrider);
		} else if(x == -1 * size && y == -1 * size) {
			corerBorder(x, y, BOTTOM_LEFT, overrider);
		} else {
			if(x == size) {
				border(x, y, RIGHT, overrider);
			} else if(y == size) {
				border(x, y, TOP, overrider);
			} else if(x == -1 * size) {
				border(x, y, LEFT, overrider);
			} else if(y == -1 * size) {
				border(x, y, BOTTOM, overrider);
			}
		}
		
		
	}
}
