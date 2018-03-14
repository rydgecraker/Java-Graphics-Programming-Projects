package forkbombsquad.fbs.utilities.math;


public class Distance {
	
	public static float distance1d(float x1, float x2) {
		return Function.absoluteValue(x2 - x1);
	}
	
	
	public static double distance1d(double x1, double x2) {
		return Function.absoluteValue(x2 - x1);
	}
	
	
	public static float distance2d(float x1, float y1, float x2, float y2) {
		return Function.SquareRoot(Function.square(x2 - x1) + Function.square(y2 - y1));
	}
	
	
	public static double distance2d(double x1, double y1, double x2, double y2) {
		return Function.SquareRoot(Function.square(x2 - x1) + Function.square(y2 - y1));
	}
	
	
	public static float distance3d(float x1, float y1, float z1, float x2, float y2, float z2) {
		return Function.SquareRoot(Function.square(x2 - x1) + Function.square(y2 - y1) + Function.square(z2 - z1));
	}
	
	
	public static double distance3d(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Function.SquareRoot(Function.square(x2 - x1) + Function.square(y2 - y1) + Function.square(z2 - z1));
	}
	
}
