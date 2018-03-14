package com.forkBombSquad.asteroidAttack;


import java.awt.Graphics2D;

import com.forkBombSquad.gameUtilities.Mathf;


public class Asteroid extends GameObject {
	
	public int		numSides;
	public float	minRadius;
	public float	maxRadius;
	public float[]	xLocations;
	public float[]	yLocations;
	
	public boolean	alive	= true;
	
	public int		sizeMod;
	
	
	public Asteroid(float xLocation, float yLocation, float veloctyX, float velocityY, int numSides, float minRadius, float maxRadius, int sizeMod) {
		super(xLocation, yLocation, 0.0f);
		this.xVelocity = veloctyX;
		this.yVelocty = velocityY;
		this.numSides = numSides;
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
		xLocations = new float[numSides];
		yLocations = new float[numSides];
		this.sizeMod = sizeMod;
		generate();
	}
	
	
	private void generate() {
		float anglePerPoint = 360.0f / numSides;
		float currentAngle = 0;
		for (int i = 0; i < numSides; i++) {
			currentAngle = i * anglePerPoint;
			float coord = getRandomCoord();
			float xLoc = xLocation + (coord * Mathf.cosDegrees(currentAngle));
			float yLoc = yLocation + (coord * Mathf.sinDegrees(currentAngle));
			xLocations[i] = xLoc;
			yLocations[i] = yLoc;
		}
	}
	
	
	private float getRandomCoord() {
		return Mathf.randRange(minRadius * sizeMod, maxRadius * sizeMod);
	}
	
	
	@Override
	public void update(float dt) {
		float dx = xVelocity * dt;
		float dy = yVelocty * dt;
		move(dx, dy);
		float da = angularVelocty * dt;
		rotate(da);
		
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < xLocations.length; i++) {
			if (i == xLocations.length - 1) {
				g.drawLine((int) xLocations[i], (int) yLocations[i], (int) xLocations[0], (int) yLocations[0]);
			} else {
				g.drawLine((int) xLocations[i], (int) yLocations[i], (int) xLocations[i + 1], (int) yLocations[i + 1]);
			}
		}
	}
	
	
	@Override
	public void rotate(float da) {
		angle += da;
		for (int i = 0; i < xLocations.length; i++) {
			xLocations[i] = Mathf.rotateXaroundPoint(da, xLocation, yLocation, xLocations[i], yLocations[i]);
			yLocations[i] = Mathf.rotateYaroundPoint(da, xLocation, yLocation, xLocations[i], yLocations[i]);
		}
	}
	
	
	@Override
	public void move(float dx, float dy) {
		xLocation += dx;
		yLocation += dy;
		for (int i = 0; i < xLocations.length; i++) {
			xLocations[i] += dx;
			yLocations[i] += dy;
		}
	}
	
	
	public void collide() {
		if (alive) {
			alive = false;
		}
	}
	
}
