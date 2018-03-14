package forkbombsquad.fbs.utilities.math;


public class Function {
	
	public static float		PI_FLOAT	= 3.1415f;
	public static double	PI_DOUBLE	= 3.1415;
	
	
	public static float min(float a, float b) {
		if (a < b) {
			return a;
		} else if (b < a) {
			return b;
		}
		return a;
	}
	
	
	public static float max(float a, float b) {
		if (a > b) {
			return a;
		} else if (b > a) {
			return b;
		}
		return a;
	}
	
	
	public static float absoluteValue(float f) {
		if (f >= 0.0f) {
			return f;
		} else {
			return -f;
		}
	}
	
	
	public static double absoluteValue(double d) {
		if (d >= 0.0) {
			return d;
		} else {
			return -d;
		}
	}
	
	
	public static float SquareRoot(float f) {
		return (float) Math.sqrt(f);
	}
	
	
	public static double SquareRoot(double d) {
		return Math.sqrt(d);
	}
	
	
	public static float square(float f) {
		return f * f;
	}
	
	
	public static double square(double d) {
		return d * d;
	}
	
	
	public static float raiseToPower(float f, float power) {
		return (float) Math.pow(f, power);
	}
	
	
	public static double raiseToPower(double d, double power) {
		return Math.pow(d, power);
	}
	
	
	public static float degreesToRadians(float degrees) {
		return (degrees) * (PI_FLOAT / 180.0f);
	}
	
	
	public static double degreesToRadians(double degrees) {
		return (degrees) * (PI_DOUBLE / 180.0);
	}
	
	
	public static float radiansToDegrees(float radians) {
		return (radians) * (180.0f / PI_FLOAT);
	}
	
	
	public static double radiansToDegrees(double radians) {
		return (radians) * (180.0 / PI_DOUBLE);
	}
	
}
