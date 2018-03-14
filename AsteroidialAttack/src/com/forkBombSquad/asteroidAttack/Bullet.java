package com.forkBombSquad.asteroidAttack;


import java.awt.Graphics2D;


public class Bullet extends GameObject {
	
	public boolean	alive	= true;
	public float	timeUntilDeath;
	public float	currentTime;
	
	
	public Bullet(float xLocation, float yLocation, float xVel, float yVel, float timeUntilDeath) {
		super(xLocation, yLocation, 0.0f);
		this.xVelocity = xVel;
		this.yVelocty = yVel;
		this.timeUntilDeath = timeUntilDeath;
		this.currentTime = 0.0f;
	}
	
	
	@Override
	public void update(float dt) {
		float dx = xVelocity * dt;
		float dy = yVelocty * dt;
		move(dx, dy);
		currentTime += dt;
		if (currentTime >= timeUntilDeath) {
			alive = false;
		}
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.drawRect((int) xLocation, (int) yLocation, 1, 1);
	}
	
	
	@Override
	public void rotate(float da) {
		
	}
	
	
	@Override
	public void move(float dx, float dy) {
		xLocation += dx;
		yLocation += dy;
	}
	
	
	public void collide() {
		alive = false;
	}
	
}
