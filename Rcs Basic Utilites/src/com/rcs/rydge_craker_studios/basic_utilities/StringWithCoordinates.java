package com.rcs.rydge_craker_studios.basic_utilities;


public class StringWithCoordinates {
	
	private String	text;
	private float	x;
	private float	y;
	
	
	public StringWithCoordinates(String text, float x, float y) {
		super();
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	
	public String getText(){
		return text;
	}
	
	
	
	public void setText(String text){
		this.text = text;
	}
	
	
	
	public float getX(){
		return x;
	}
	
	
	
	public void setX(float x){
		this.x = x;
	}
	
	
	
	public float getY(){
		return y;
	}
	
	
	
	public void setY(float y){
		this.y = y;
	}
	
	
	
}
