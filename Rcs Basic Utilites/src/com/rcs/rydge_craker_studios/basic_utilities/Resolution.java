package com.rcs.rydge_craker_studios.basic_utilities;


public class Resolution {
	
	private float	width;
	private float	height;
	
	public Resolution(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	
	protected float getWidth(){
		return width;
	}
	
	
	protected void setWidth(float width){
		this.width = width;
	}
	
	
	protected float getHeight(){
		return height;
	}
	
	
	protected void setHeight(float height){
		this.height = height;
	}
	
	
	
}
