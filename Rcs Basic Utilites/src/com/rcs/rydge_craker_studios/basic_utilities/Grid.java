package com.rcs.rydge_craker_studios.basic_utilities;



public abstract class Grid {
	
	protected int	width;
	protected int	height;
	protected float	originX;
	protected float	originY;
	
	public Grid(int width, int height) {
		if(width % 2 == 0) {
			width += 1;
		}
		
		if(height % 2 == 0) {
			height += 1;
		}
		this.width = width;
		this.height = height;
		originX = (float) (width / 2.0);
		originY = (float) (height / 2.0);
		init();
	}
	
	protected abstract void init();
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}
