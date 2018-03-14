package forkbombsquad.fbs.utilities.math;


public class Vector2 {
	
	public static Vector2	DOWN				= new Vector2(0, -1);
	public static Vector2	LEFT				= new Vector2(-1, 0);
	public static Vector2	RIGHT				= new Vector2(1, 0);
	public static Vector2	UP					= new Vector2(0, 1);
	public static Vector2	NEGATIVE_INFINITY	= new Vector2(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
	public static Vector2	POSITIVE_INFINITY	= new Vector2(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
	public static Vector2	ZERO				= new Vector2(0, 0);
	
	public float			x;
	public float			y;
	
	
	public Vector2() {
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public boolean equals(Vector2 v2) {
		return (this.x == v2.x && this.y == v2.y);
	}
	
	
	public float distanceTo(Vector2 v2) {
		return Distance.distance2d(x, y, v2.x, v2.y);
	}
	
	
	public double getMagnitude() {
		return Function.SquareRoot((x * x) + (y * y));
	}
	
	
	public Vector2 add(Vector2 vector) {
		return new Vector2(x + vector.x, y + vector.y);
	}
	
	
	public void addApply(Vector2 vector) {
		x += vector.x;
		y += vector.y;
	}
	
	
	/**
	 * Sets the length of the vector to 1
	 */
	public Vector2 normalize() {
		double length = getMagnitude();
		if (length != 0.0) {
			float s = 1.0f / (float) length;
			return new Vector2(x * s, y * s);
		}
		return duplicate();
	}
	
	
	private Vector2 duplicate() {
		return new Vector2(x, y);
	}
	
	
	public Vector2 multiplyBy(float amount) {
		return new Vector2(x * amount, y * amount);
	}
	
}
