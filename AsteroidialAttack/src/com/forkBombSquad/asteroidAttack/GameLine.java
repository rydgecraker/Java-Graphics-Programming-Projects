package com.forkBombSquad.asteroidAttack;


import java.awt.Graphics2D;

import com.forkBombSquad.gameUtilities.Mathf;


public class GameLine extends GameObject {
	
	public float	x1, x2, y1, y2;
	public float	nrx1, nrx2, nry1, nry2;
	
	public boolean	alive			= true;
	public float	timeTillDeath	= 0.0f;
	
	public float	currentTime		= 0.0f;
	
	
	public GameLine(float x1, float y1, float x2, float y2, float timeTillDeath) {
		super(Mathf.midpoint(x1, x2), Mathf.midpoint(y1, y2), 0.0f);
		this.x1 = x1;
		this.nrx1 = x1;
		this.x2 = x2;
		this.nry1 = y1;
		this.y1 = y1;
		this.nrx2 = x2;
		this.y2 = y2;
		this.nry2 = y2;
		this.timeTillDeath = timeTillDeath;
	}
	
	
	@Override
	public void update(float dt) {
		nrx1 += xVelocity * dt;
		nrx2 += xVelocity * dt;
		nry1 += yVelocty * dt;
		nry2 += yVelocty * dt;
		angle += angularVelocty * dt;
		recalculateCenterPoint();
		calculateRotation();
		currentTime += dt;
		if (currentTime >= timeTillDeath) {
			alive = false;
		}
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}
	
	
	@Override
	public void rotate(float da) {
		angle += da;
		calculateRotation();
	}
	
	
	private void calculateRotation() {
		x1 = Mathf.rotateXaroundPoint(angle, xLocation, yLocation, nrx1, nry1);
		x2 = Mathf.rotateXaroundPoint(angle, xLocation, yLocation, nrx2, nry2);
		y1 = Mathf.rotateYaroundPoint(angle, xLocation, yLocation, nrx1, nry1);
		y2 = Mathf.rotateYaroundPoint(angle, xLocation, yLocation, nrx2, nry2);
	}
	
	
	public void rotateAround(float x, float y, float da) {
		x1 = Mathf.rotateXaroundPoint(da, x, y, nrx1, nry1);
		y1 = Mathf.rotateYaroundPoint(da, x, y, nrx1, nry1);
		x2 = Mathf.rotateXaroundPoint(da, x, y, nrx2, nry2);
		y2 = Mathf.rotateYaroundPoint(da, x, y, nrx2, nry2);
		
	}
	
	
	private void recalculateCenterPoint() {
		xLocation = Mathf.midpoint(nrx1, nrx2);
		yLocation = Mathf.midpoint(nry1, nry2);
	}
	
	
	@Override
	public void move(float dx, float dy) {
		nrx1 += dx;
		nrx2 += dx;
		nry1 += dy;
		nry2 += dy;
		recalculateCenterPoint();
	}
	
	
	public void explode() {
		float ang = Mathf.randRange(0, 360);
		float explosionSpeed = Mathf.randRange(20, 100);
		xVelocity = Mathf.cosDegrees(ang) * explosionSpeed;
		yVelocty = Mathf.sinDegrees(ang) * explosionSpeed;
		angularVelocty = Mathf.randRange(-720, 720);
	}
	
}
