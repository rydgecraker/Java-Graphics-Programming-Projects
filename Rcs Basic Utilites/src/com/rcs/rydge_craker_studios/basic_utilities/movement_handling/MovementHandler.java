package com.rcs.rydge_craker_studios.basic_utilities.movement_handling;



public abstract class MovementHandler {
	
	public static final int	PLATFORMER	= 0;
	public static final int	SIDE_VIEW	= 1;
	public static final int	TOP_VIEW	= 2;
	
	//Location
	protected float			x, y, theta;
	
	//change in location
	protected float			dx, dy, dtheta;
	
	//is accelerating in this direction
	protected boolean		accRight, accLeft, accUp, accDown, accClockwise, accCounterClockwise;
	
	//Accelerations
	protected float			acelRight, acelLeft, acelUp, acelDown, acelClockwise, acelCounterClockwise;
	
	//Maximum Velocities
	protected float			maxVelRight, maxVelLeft, maxVelUp, maxVelDown, maxVelClockwise, maxVelCounterClockwise;
	
	//Opposing Force Values
	protected float			frictionRight, frictionLeft, frictionUp, frictionDown, frictionClockwise, frictionCounterClockwise;
	
	
	
	public MovementHandler(float x, float y, float theta, float acelRight, float acelLeft, float acelUp, float acelDown, float acelClockwise, float acelCounterClockwise, float maxVelRight, float maxVelLeft, float maxVelUp, float maxVelDown, float maxVelClockwise, float maxVelCounterClockwise, float frictionRight, float frictionLeft, float frictionUp, float frictionDown, float frictionClockwise, float frictionCounterClockwise) {
		this.x = x;
		this.y = y;
		this.theta = theta;
		this.acelRight = acelRight;
		this.acelLeft = acelLeft;
		this.acelUp = acelUp;
		this.acelDown = acelDown;
		this.acelClockwise = acelClockwise;
		this.acelCounterClockwise = acelCounterClockwise;
		this.maxVelRight = maxVelRight;
		this.maxVelLeft = maxVelLeft;
		this.maxVelUp = maxVelUp;
		this.maxVelDown = maxVelDown;
		this.maxVelClockwise = maxVelClockwise;
		this.maxVelCounterClockwise = maxVelCounterClockwise;
		this.frictionRight = frictionRight;
		this.frictionLeft = frictionLeft;
		this.frictionUp = frictionUp;
		this.frictionDown = frictionDown;
		this.frictionClockwise = frictionClockwise;
		this.frictionCounterClockwise = frictionCounterClockwise;
		initSuper();
	}
	
	
	private void initSuper(){
		accRight = false;
		accLeft = false;
		accUp = false;
		accDown = false;
		accClockwise = false;
		accCounterClockwise = false;
		dx = 0.0f;
		dy = 0.0f;
		dtheta = 0.0f;
	}
	
	
	public abstract int getType();
	
	public abstract void handleAllMotion(float dt);
	
	public abstract void handleAllMotionWithBounds(float dt, float boundLeft, float boundRight, float boundUp, float boundDown, float boundClockwise, float boundCounterClockwise);
	
	public abstract void handleRotations(float dt);
	
	public abstract void handleMovement(float dt);
	
	public abstract void handleSpeedLimits(float dt);
	
	public abstract void handleBounds(float dt, float boundLeft, float boundRight, float boundUp, float boundDown, float boundClockwise, float boundCounterClockwise);
	
	public abstract void resetLocationAndDirection(float dt);
	
	
	public abstract void accelerateRight(float dt);
	
	public abstract void accelerateLeft(float dt);
	
	public abstract void accelerateUp(float dt);
	
	public abstract void accelerateDown(float dt);
	
	public abstract void accelerateClockwise(float dt);
	
	public abstract void accelerateCounterClockwise(float dt);
	
	public abstract void decelerateUp(float dt);
	
	public abstract void decelerateDown(float dt);
	
	public abstract void decelerateRight(float dt);
	
	public abstract void decelerateLeft(float dt);
	
	public abstract void decelerateClockwise(float dt);
	
	public abstract void decelerateCounterClockwise(float dt);
	
	public abstract void limitSpeedRight(float dt);
	
	public abstract void limitSpeedLeft(float dt);
	
	public abstract void limitSpeedUp(float dt);
	
	public abstract void limitSpeedDown(float dt);
	
	public abstract void limitSpeedClockwise(float dt);
	
	public abstract void limitSpeedCounterClockwise(float dt);
	
	public abstract void applyBoundsRight(float dt, float bound);
	
	public abstract void applyBoundsLeft(float dt, float bound);
	
	public abstract void applyBoundsUp(float dt, float bound);
	
	public abstract void applyBoundsDown(float dt, float bound);
	
	public abstract void applyBoundsClockwise(float dt, float bound);
	
	public abstract void applyBoundsCounterClockwise(float dt, float bound);
	
	
	
	public void moveLeft(float l){
		x -= l;
	}
	
	
	public void moveRight(float r){
		x += r;
	}
	
	
	public void moveUp(float u){
		y += u;
	}
	
	
	public void moveDown(float d){
		y -= d;
	}
	
	
	public void rotateClockwise(float radians){
		theta -= radians;
	}
	
	
	public void rotateCounterClockwise(float radians){
		theta += radians;
	}
	
	
	
	//////////////////////////
	//////////////////////////
	//////////////////////////
	
	
	
	//////////////////////////
	//Getters and Setters
	//////////////////////////
	
	
	
	public float getCenterX(){
		return x;
	}
	
	
	
	public void setCenterX(float centerX){
		this.x = centerX;
	}
	
	
	
	public float getCenterY(){
		return y;
	}
	
	
	
	public void setCenterY(float centerY){
		this.y = centerY;
	}
	
	
	
	public float getTheta(){
		return theta;
	}
	
	
	
	public void setTheta(float theta){
		this.theta = theta;
	}
	
	
	
	public float getDx(){
		return dx;
	}
	
	
	
	public void setDx(float dx){
		this.dx = dx;
	}
	
	
	
	public float getDy(){
		return dy;
	}
	
	
	
	public void setDy(float dy){
		this.dy = dy;
	}
	
	
	
	public float getDtheta(){
		return dtheta;
	}
	
	
	
	public void setDtheta(float dtheta){
		this.dtheta = dtheta;
	}
	
	
	
	public boolean isAccRight(){
		return accRight;
	}
	
	
	
	public void setAccRight(boolean accRight){
		this.accRight = accRight;
	}
	
	
	
	public boolean isAccLeft(){
		return accLeft;
	}
	
	
	
	public void setAccLeft(boolean accLeft){
		this.accLeft = accLeft;
	}
	
	
	
	public boolean isAccUp(){
		return accUp;
	}
	
	
	
	public void setAccUp(boolean accUp){
		this.accUp = accUp;
	}
	
	
	
	public boolean isAccDown(){
		return accDown;
	}
	
	
	
	public void setAccDown(boolean accDown){
		this.accDown = accDown;
	}
	
	
	
	public boolean isAccClockwise(){
		return accClockwise;
	}
	
	
	
	public void setAccClockwise(boolean accClockwise){
		this.accClockwise = accClockwise;
	}
	
	
	
	public boolean isAccCounterClockwise(){
		return accCounterClockwise;
	}
	
	
	
	public void setAccCounterClockwise(boolean accCounterClockwise){
		this.accCounterClockwise = accCounterClockwise;
	}
	
	
	
	public float getAcelRight(){
		return acelRight;
	}
	
	
	
	public void setAcelRight(float acelRight){
		this.acelRight = acelRight;
	}
	
	
	
	public float getAcelLeft(){
		return acelLeft;
	}
	
	
	
	public void setAcelLeft(float acelLeft){
		this.acelLeft = acelLeft;
	}
	
	
	
	public float getAcelUp(){
		return acelUp;
	}
	
	
	
	public void setAcelUp(float acelUp){
		this.acelUp = acelUp;
	}
	
	
	
	public float getAcelDown(){
		return acelDown;
	}
	
	
	
	public void setAcelDown(float acelDown){
		this.acelDown = acelDown;
	}
	
	
	
	public float getAcelClockwise(){
		return acelClockwise;
	}
	
	
	
	public void setAcelClockwise(float acelClockwise){
		this.acelClockwise = acelClockwise;
	}
	
	
	
	public float getAcelCounterClockwise(){
		return acelCounterClockwise;
	}
	
	
	
	public void setAcelCounterClockwise(float acelCounterClockwise){
		this.acelCounterClockwise = acelCounterClockwise;
	}
	
	
	
	public float getMaxVelRight(){
		return maxVelRight;
	}
	
	
	
	public void setMaxVelRight(float maxVelRight){
		this.maxVelRight = maxVelRight;
	}
	
	
	
	public float getMaxVelLeft(){
		return maxVelLeft;
	}
	
	
	
	public void setMaxVelLeft(float maxVelLeft){
		this.maxVelLeft = maxVelLeft;
	}
	
	
	
	public float getMaxVelUp(){
		return maxVelUp;
	}
	
	
	
	public void setMaxVelUp(float maxVelUp){
		this.maxVelUp = maxVelUp;
	}
	
	
	
	public float getMaxVelDown(){
		return maxVelDown;
	}
	
	
	
	public void setMaxVelDown(float maxVelDown){
		this.maxVelDown = maxVelDown;
	}
	
	
	
	public float getMaxVelClockwise(){
		return maxVelClockwise;
	}
	
	
	
	public void setMaxVelClockwise(float maxVelClockwise){
		this.maxVelClockwise = maxVelClockwise;
	}
	
	
	
	public float getMaxVelCounterClockwise(){
		return maxVelCounterClockwise;
	}
	
	
	
	public void setMaxVelCounterClockwise(float maxVelCounterClockwise){
		this.maxVelCounterClockwise = maxVelCounterClockwise;
	}
	
	
	
	public float getFrictionRight(){
		return frictionRight;
	}
	
	
	
	public void setFrictionRight(float frictionRight){
		this.frictionRight = frictionRight;
	}
	
	
	
	public float getFrictionLeft(){
		return frictionLeft;
	}
	
	
	
	public void setFrictionLeft(float frictionLeft){
		this.frictionLeft = frictionLeft;
	}
	
	
	
	public float getFrictionUp(){
		return frictionUp;
	}
	
	
	
	public void setFrictionUp(float frictionUp){
		this.frictionUp = frictionUp;
	}
	
	
	
	public float getFrictionDown(){
		return frictionDown;
	}
	
	
	
	public void setFrictionDown(float frictionDown){
		this.frictionDown = frictionDown;
	}
	
	
	
	public float getFrictionClockwise(){
		return frictionClockwise;
	}
	
	
	
	public void setFrictionClockwise(float frictionClockwise){
		this.frictionClockwise = frictionClockwise;
	}
	
	
	
	public float getFrictionCounterClockwise(){
		return frictionCounterClockwise;
	}
	
	
	
	public void setFrictionCounterClockwise(float frictionCounterClockwise){
		this.frictionCounterClockwise = frictionCounterClockwise;
	}
	
	
	
}
