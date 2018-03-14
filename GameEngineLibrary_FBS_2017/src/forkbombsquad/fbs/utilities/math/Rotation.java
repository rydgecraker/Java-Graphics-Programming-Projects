package forkbombsquad.fbs.utilities.math;


public class Rotation {
	
	public float angle;
	
	
	public Rotation(float angle) {
		this.angle = angle;
	}
	
	
	public Vector2 getAsVector(boolean alreadyInRadians) {
		if (alreadyInRadians) {
			return new Vector2((float) (Math.cos(angle)), (float) (Math.sin(angle)));
		} else {
			return new Vector2((float) (Math.cos(Function.degreesToRadians(angle))), (float) (Math.sin(Function.degreesToRadians(angle))));
		}
	}
	
}
