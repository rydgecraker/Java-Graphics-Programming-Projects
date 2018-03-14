package com.rcs.rydge_craker_studios.basic_utilities.movement_handling;


public class PlatformerMovementHandler extends MovementHandler {
	
	
	
	public PlatformerMovementHandler(float x, float y, float acelRight, float acelLeft, float acelUp, float acelClockwise, float acelCounterClockwise, float maxVelRight, float maxVelLeft, float maxVelUp, float terminalVelocity, float maxVelClockwise, float maxVelCounterClockwise, float frictionRight, float frictionLeft, float frictionUp, float frictionClockwise, float frictionCounterClockwise) {
		super(x, y, 0, acelRight, acelLeft, acelUp, 0, acelClockwise, acelCounterClockwise, maxVelRight, maxVelLeft, maxVelUp, terminalVelocity,
				maxVelClockwise, maxVelCounterClockwise, frictionRight, frictionLeft, frictionUp, 0, frictionClockwise, frictionCounterClockwise);
	}
	
	public int getType(){
		return MovementHandler.PLATFORMER;
	}
	
	@Override
	public void handleAllMotion(float dt){
		handleRotations(dt);
		handleMovement(dt);
		handleSpeedLimits(dt);
		resetLocationAndDirection(dt);
	}
	
	public void handleAllMotion(float dt, float ground){
		handleRotations(dt);
		handleMovement(dt);
		handleSpeedLimits(dt);
		applyBoundsDown(dt, ground);
		resetLocationAndDirection(dt);
	}
	
	@Override
	public void handleAllMotionWithBounds(float dt, float boundLeft, float boundRight, float boundUp, float boundDown, float boundClockwise, float boundCounterClockwise){
		handleRotations(dt);
		handleMovement(dt);
		handleSpeedLimits(dt);
		handleBounds(dt, boundLeft, boundRight, boundUp, boundDown, boundClockwise, boundCounterClockwise);
		resetLocationAndDirection(dt);
	}
	
	@Override
	public void handleRotations(float dt){
		accelerateClockwise(dt);
		accelerateCounterClockwise(dt);
		decelerateClockwise(dt);
		decelerateCounterClockwise(dt);
		dthetaZero(dt);
		
	}
	
	private void dthetaZero(float dt){
		if(dtheta == 0) {
			return;
		}
		if(dtheta > -(acelClockwise * dt) && dtheta < (acelCounterClockwise * dt)) {
			dtheta = 0.0f;
		}
	}
	
	@Override
	public void handleMovement(float dt){
		accelerateRight(dt);
		decelerateRight(dt);
		accelerateLeft(dt);
		decelerateLeft(dt);
		dxZero(dt);
		
		accelerateUp(dt);
		decelerateUp(dt);
		accelerateDown(dt);
		decelerateDown(dt);
		dyZero(dt);
		
	}
	
	private void dxZero(float dt){
		if(dx == 0) {
			return;
		}
		if(dx > -(acelLeft * dt) && dx < (acelRight * dt)) {
			dx = 0.0f;
		}
	}
	
	private void dyZero(float dt){
		return;
	}
	
	@Override
	public void handleSpeedLimits(float dt){
		limitSpeedClockwise(dt);
		limitSpeedCounterClockwise(dt);
		limitSpeedUp(dt);
		limitSpeedDown(dt);
		limitSpeedLeft(dt);
		limitSpeedRight(dt);
	}
	
	@Override
	public void handleBounds(float dt, float boundLeft, float boundRight, float boundUp, float boundDown, float boundClockwise, float boundCounterClockwise){
		applyBoundsClockwise(dt, boundClockwise);
		applyBoundsCounterClockwise(dt, boundCounterClockwise);
		applyBoundsLeft(dt, boundLeft);
		applyBoundsRight(dt, boundRight);
		applyBoundsUp(dt, boundUp);
		applyBoundsDown(dt, boundDown);
	}
	
	
	@Override
	public void resetLocationAndDirection(float dt){
		theta += dtheta * dt;
		x += dx * dt;
		y += dy * dt;
	}
	
	@Override
	public void accelerateRight(float dt){
		if(accRight) {
			dx += acelRight * dt;
		}
	}
	
	@Override
	public void accelerateLeft(float dt){
		if(accLeft) {
			dx -= acelLeft * dt;
		}
	}
	
	public void accelerateUp(float dt){
		if(accUp) {
			dy += acelUp * dt;
		}
	}
	
	public void accelerateDown(float dt){
		return;
	}
	
	public void accelerateClockwise(float dt){
		if(accClockwise) {
			dtheta -= acelClockwise * dt;
		}
	}
	
	@Override
	public void accelerateCounterClockwise(float dt){
		if(accCounterClockwise) {
			dtheta += acelCounterClockwise * dt;
		}
	}
	
	@Override
	public void decelerateUp(float dt){
		dx -= frictionUp * dt;
	}
	
	@Override
	public void decelerateDown(float dt){
		return;
	}
	
	@Override
	public void decelerateRight(float dt){
		if(dx > 0) {
			dx -= frictionRight * dt;
		}
	}
	
	@Override
	public void decelerateLeft(float dt){
		if(dx < 0) {
			dx += frictionRight * dt;
		}
	}
	
	@Override
	public void decelerateClockwise(float dt){
		if(dtheta < 0) {
			dtheta += frictionClockwise * dt;
		}
	}
	
	@Override
	public void decelerateCounterClockwise(float dt){
		if(dtheta > 0) {
			dtheta -= frictionCounterClockwise * dt;
		}
	}
	
	@Override
	public void limitSpeedRight(float dt){
		if(dx > 0 && dx > maxVelRight) {
			dx = maxVelRight;
		}
	}
	
	@Override
	public void limitSpeedLeft(float dt){
		if(dx < 0 && dx < -maxVelLeft) {
			dx = -maxVelLeft;
		}
	}
	
	@Override
	public void limitSpeedUp(float dt){
		if(dy > 0 && dy > maxVelUp) {
			dy = maxVelUp;
		}
	}
	
	@Override
	public void limitSpeedDown(float dt){
		if(dy < 0 && dy < -maxVelDown) {
			dy = maxVelDown;
		}
	}
	
	@Override
	public void limitSpeedClockwise(float dt){
		if(dtheta < 0 && dtheta < -maxVelClockwise) {
			dtheta = -maxVelClockwise;
		}
	}
	
	@Override
	public void limitSpeedCounterClockwise(float dt){
		if(dtheta > 0 && dtheta > maxVelCounterClockwise) {
			dtheta = maxVelCounterClockwise;
		}
	}
	
	@Override
	public void applyBoundsRight(float dt, float bound){
		if(x > bound) {
			x = bound;
			if(dx > 0) {
				dx = 0.0f;
			}
		}
	}
	
	@Override
	public void applyBoundsLeft(float dt, float bound){
		if(x < bound) {
			x = bound;
			if(dx < 0) {
				dx = 0.0f;
			}
		}
	}
	
	@Override
	public void applyBoundsUp(float dt, float bound){
		if(y > bound) {
			y = bound;
			if(dy > 0) {
				dy = 0.0f;
			}
		}
	}
	
	@Override
	public void applyBoundsDown(float dt, float bound){
		if(y < bound) {
			y = bound;
			if(dy < 0) {
				dy = 0.0f;
			}
		}
	}
	
	@Override
	public void applyBoundsClockwise(float dt, float bound){
		if(theta < bound) {
			theta = bound;
			if(dtheta < 0) {
				dtheta = 0;
			}
		}
	}
	
	@Override
	public void applyBoundsCounterClockwise(float dt, float bound){
		if(theta > bound) {
			theta = bound;
			if(dtheta > 0) {
				dtheta = 0;
			}
		}
	}
	
	
	
}
