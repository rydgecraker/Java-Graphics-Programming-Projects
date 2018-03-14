package com.rcs.rydge_craker_studios.basic_utilities.movement_handling;


public class TopViewMovementHandler extends MovementHandler {
	
	
	public TopViewMovementHandler(float x, float y, float theta, float acelRight, float acelLeft, float acelUp, float acelDown, float acelClockwise, float acelCounterClockwise, float maxVelRight, float maxVelLeft, float maxVelUp, float maxVelDown, float maxVelClockwise, float maxVelCounterClockwise, float frictionRight, float frictionLeft, float frictionUp, float frictionDown, float frictionClockwise, float frictionCounterClockwise) {
		super(x, y, theta, acelRight, acelLeft, acelUp, acelDown, acelClockwise, acelCounterClockwise, maxVelRight, maxVelLeft, maxVelUp, maxVelDown,
				maxVelClockwise, maxVelCounterClockwise, frictionRight, frictionLeft, frictionUp, frictionDown, frictionClockwise, frictionCounterClockwise);
	}
	
	public int getType(){
		return MovementHandler.TOP_VIEW;
	}
	
	@Override
	public void handleAllMotion(float dt){
	}
	
	@Override
	public void handleAllMotionWithBounds(float dt, float boundLeft, float boundRight, float boundUp, float boundDown, float boundClockwise, float boundCounterClockwise){
	}
	
	@Override
	public void handleRotations(float dt){
	}
	
	@Override
	public void handleMovement(float dt){
	}
	
	@Override
	public void handleSpeedLimits(float dt){
	}
	
	@Override
	public void handleBounds(float dt, float boundLeft, float boundRight, float boundUp, float boundDown, float boundClockwise, float boundCounterClockwise){
	}
	
	@Override
	public void resetLocationAndDirection(float dt){
	}
	
	@Override
	public void accelerateRight(float dt){
	}
	
	@Override
	public void accelerateLeft(float dt){
	}
	
	@Override
	public void accelerateUp(float dt){
	}
	
	@Override
	public void accelerateDown(float dt){
	}
	
	@Override
	public void accelerateClockwise(float dt){
	}
	
	@Override
	public void accelerateCounterClockwise(float dt){
	}
	
	@Override
	public void decelerateUp(float dt){
	}
	
	@Override
	public void decelerateDown(float dt){
	}
	
	@Override
	public void decelerateRight(float dt){
	}
	
	@Override
	public void decelerateLeft(float dt){
	}
	
	@Override
	public void decelerateClockwise(float dt){
	}
	
	@Override
	public void decelerateCounterClockwise(float dt){
	}
	
	@Override
	public void limitSpeedRight(float dt){
	}
	
	@Override
	public void limitSpeedLeft(float dt){
	}
	
	@Override
	public void limitSpeedUp(float dt){
	}
	
	@Override
	public void limitSpeedDown(float dt){
	}
	
	@Override
	public void limitSpeedClockwise(float dt){
	}
	
	@Override
	public void limitSpeedCounterClockwise(float dt){
	}
	
	@Override
	public void applyBoundsRight(float dt, float bound){
	}
	
	@Override
	public void applyBoundsLeft(float dt, float bound){
	}
	
	@Override
	public void applyBoundsUp(float dt, float bound){
	}
	
	@Override
	public void applyBoundsDown(float dt, float bound){
	}
	
	@Override
	public void applyBoundsClockwise(float dt, float bound){
	}
	
	@Override
	public void applyBoundsCounterClockwise(float dt, float bound){
	}
	
	
}
