package com.forkBombSquad.asteroidAttack;


import java.awt.Graphics2D;

import com.forkBombSquad.gameUtilities.Mathf;


public class Player extends GameObject {
	
	public float[]	xLocations;
	public float[]	yLocations;
	public float[]	nrXLocations;
	public float[]	nrYLocations;
	
	public float	maxVelocity		= 999999.0f;
	public float	bulletVelocity	= 5000.0f;
	
	public boolean	alive			= true;
	
	public float	playerScale		= 5f;
	
	
	public Player(float xLocation, float yLocation) {
		super(xLocation, yLocation, 0.0f);
		xLocations = new float[4];
		yLocations = new float[4];
		nrXLocations = new float[4];
		nrYLocations = new float[4];
		generate();
	}
	
	
	public void generate() {
		xLocations[0] = xLocation + 3.0f * playerScale;
		yLocations[0] = yLocation;
		
		xLocations[1] = xLocation - 3.0f * playerScale;
		yLocations[1] = yLocation + 2.0f * playerScale;
		
		xLocations[2] = xLocation - 2.0f * playerScale;
		yLocations[2] = yLocation;
		
		xLocations[3] = xLocation - 3.0f * playerScale;
		yLocations[3] = yLocation - 2.0f * playerScale;
		
		nrXLocations[0] = xLocation + 3.0f * playerScale;
		nrYLocations[0] = yLocation;
		
		nrXLocations[1] = xLocation - 3.0f * playerScale;
		nrYLocations[1] = yLocation + 2.0f * playerScale;
		
		nrXLocations[2] = xLocation - 2.0f * playerScale;
		nrYLocations[2] = yLocation;
		
		nrXLocations[3] = xLocation - 3.0f * playerScale;
		nrYLocations[3] = yLocation - 2.0f * playerScale;
	}
	
	
	@Override
	public void update(float dt) {
		if (alive) {
			float dx = xVelocity * dt;
			float dy = yVelocty * dt;
			move(dx, dy);
			float da = angularVelocty * dt;
			rotate(da);
		}
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		if (alive) {
			recalculateRotation();
			for (int i = 0; i < xLocations.length; i++) {
				if (i == xLocations.length - 1) {
					g.drawLine((int) xLocations[i], (int) yLocations[i], (int) xLocations[0], (int) yLocations[0]);
				} else {
					g.drawLine((int) xLocations[i], (int) yLocations[i], (int) xLocations[i + 1], (int) yLocations[i + 1]);
				}
			}
		}
	}
	
	
	@Override
	public void rotate(float da) {
		angle += da;
		if (Mathf.abs(angle) >= 360.0f) {
			angle = (Mathf.abs(angle) - 360.0f) * Mathf.getSign(angle);
		}
		recalculateRotation();
		
	}
	
	
	public void recalculateRotation() {
		for (int i = 0; i < xLocations.length; i++) {
			xLocations[i] = Mathf.rotateXaroundPoint(angle, xLocation, yLocation, nrXLocations[i], nrYLocations[i]);
			yLocations[i] = Mathf.rotateYaroundPoint(angle, xLocation, yLocation, nrXLocations[i], nrYLocations[i]);
		}
	}
	
	
	@Override
	public void move(float dx, float dy) {
		xLocation += dx;
		yLocation += dy;
		for (int i = 0; i < xLocations.length; i++) {
			xLocations[i] += dx;
			yLocations[i] += dy;
			nrXLocations[i] += dx;
			nrYLocations[i] += dy;
		}
		recalculateRotation();
	}
	
	
	public Bullet shootBullet() {
		float bulX = xLocations[0];
		float bulY = yLocations[0];
		float bulSpd = Math.min(bulletVelocity, Math.max((Mathf.pythagoreanTheorem(xVelocity, yVelocty) * 2.0f), 1000.0f));
		float bulVx = bulSpd * Mathf.cosDegrees(angle);
		float bulVy = bulSpd * Mathf.sinDegrees(angle);
		
		return new Bullet(bulX, bulY, bulVx, bulVy, 1.5f);
	}
	
	
	public void accelerateVelocity(float acc) {
		xVelocity += acc * Mathf.cosDegrees(angle);
		yVelocty += acc * Mathf.sinDegrees(angle);
		
		if (Mathf.abs(xVelocity) > maxVelocity) {
			xVelocity = maxVelocity * Mathf.cosDegrees(angle);
			yVelocty = maxVelocity * Mathf.sinDegrees(angle);
		}
		
		if (Mathf.abs(yVelocty) > maxVelocity) {
			xVelocity = maxVelocity * Mathf.cosDegrees(angle);
			yVelocty = maxVelocity * Mathf.sinDegrees(angle);
		}
	}
	
	
	public void decelerateVelocity(float acc) {
		xVelocity = Mathf.reduceToZero(xVelocity, acc);
		yVelocty = Mathf.reduceToZero(yVelocty, acc);
	}
	
	
	public void collide() {
		alive = false;
	}
	
}
