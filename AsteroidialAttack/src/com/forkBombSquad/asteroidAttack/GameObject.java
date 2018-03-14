package com.forkBombSquad.asteroidAttack;


import java.awt.Graphics2D;


public abstract class GameObject {
	
	public float	xLocation;
	public float	yLocation;
	public float	xVelocity;
	public float	yVelocty;
	public float	angularVelocty;
	public float	angle;
	
	
	public GameObject(float xLocation, float yLocation, float angle) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.angle = angle;
	}
	
	
	public abstract void update(float dt);
	
	
	public abstract void draw(Graphics2D g);
	
	
	public abstract void rotate(float da);
	
	
	public abstract void move(float dx, float dy);
	
}
