package com.forkBombSquad.gameUtilities;


public class Mathf {
	
	public static float PI = 3.1415f;
	
	
	public static float degreesToRadians(float angle) {
		return ((angle * PI) / 180.0f);
	}
	
	
	public static float radiansToDegrees(float angle) {
		return ((angle * 180.0f) / PI);
	}
	
	
	public static float sinDegrees(float angle) {
		return (float) Math.sin(degreesToRadians(angle));
	}
	
	
	public static float cosDegrees(float angle) {
		return (float) Math.cos(degreesToRadians(angle));
	}
	
	
	public static float distance1d(float x1, float x2) {
		return Math.abs(x2 - x1);
	}
	
	
	public static float distance2d(float x1, float x2, float y1, float y2) {
		return (float) Math.sqrt((Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2)));
	}
	
	
	public static float distance3d(float x1, float x2, float y1, float y2, float z1, float z2) {
		return (float) Math.sqrt((Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2) + (Math.pow((z2 - z1), 2))));
	}
	
	
	public static float pythagoreanTheorem(float a, float b) {
		return (float) Math.sqrt((a * a) + (b * b));
	}
	
	
	public static float randRange(float min, float max) {
		return ((float) Math.random()) * (max - min) + min;
	}
	
	
	public static float rotateXaroundPoint(float deltaAngle, float centerOfRotationX, float centerOfRotationY, float xCoord, float yCoord) {
		return ((cosDegrees(deltaAngle) * (xCoord - centerOfRotationX)) - (sinDegrees(deltaAngle) * (yCoord - centerOfRotationY))) + centerOfRotationX;
	}
	
	
	public static float rotateYaroundPoint(float deltaAngle, float centerOfRotationX, float centerOfRotationY, float xCoord, float yCoord) {
		return ((sinDegrees(deltaAngle) * (xCoord - centerOfRotationX)) + (cosDegrees(deltaAngle) * (yCoord - centerOfRotationY))) + centerOfRotationY;
	}
	
	
	public static float midpoint(float coord1, float coord2) {
		return (coord1 + coord2) / 2.0f;
	}
	
	
	public static boolean polygonContainsPoint(float pointX, float pointY, float[] polygonLocationsX, float[] polygonLocationsY) {
		float[] maxAndMins = findMaxAndMinPoly(polygonLocationsX, polygonLocationsY);
		
		if (!polyContainsRoughCheck(pointX, pointY, maxAndMins)) {
			return false;
		}
		
		Line ray = getPolyRay(maxAndMins[0], maxAndMins[1], pointX, pointY);
		Line[] sides = getSidesOfPolygon(polygonLocationsX, polygonLocationsY);
		
		return useRaycasting(ray, sides);
	}
	
	
	public static Line[] getSidesOfPolygon(float[] polygonLocationsX, float[] polygonLocationsY) {
		Line[] sides = new Line[polygonLocationsX.length];
		
		for (int i = 0; i < sides.length; i++) {
			if (i != sides.length - 1) {
				sides[i] = new Line(polygonLocationsX[i], polygonLocationsY[i], polygonLocationsX[i + 1], polygonLocationsY[i + 1]);
			} else {
				sides[i] = new Line(polygonLocationsX[i], polygonLocationsY[i], polygonLocationsX[0], polygonLocationsY[0]);
			}
		}
		
		return sides;
	}
	
	
	public static boolean useRaycasting(Line ray, Line[] sides) {
		int intersections = 0;
		for (int i = 0; i < sides.length; i++) {
			if (doLinesIntersect(ray, sides[i])) {
				intersections++;
			}
		}
		if ((intersections & 1) == 1) {
			return true; // Point is inside polygon
		} else {
			return false; // Point is outside polygon
		}
	}
	
	
	private static boolean polyContainsRoughCheck(float x, float y, float[] maxAndMins) {
		return polyContainsRoughCheck(x, y, maxAndMins[0], maxAndMins[1], maxAndMins[2], maxAndMins[3]);
	}
	
	
	private static boolean polyContainsRoughCheck(float x, float y, float maxX, float minX, float maxY, float minY) {
		if (x > maxX || x < minX || y > maxY || y < minY) {
			return false;
		} else {
			return true;
		}
	}
	
	
	private static float[] findMaxAndMinPoly(float[] polygonLocationsX, float[] polygonLocationsY) {
		float[] maxAndMins = new float[4];
		
		maxAndMins[0] = polygonLocationsX[0]; // maxX
		maxAndMins[1] = polygonLocationsX[0]; // minX
		maxAndMins[2] = polygonLocationsY[0]; // maxY
		maxAndMins[3] = polygonLocationsY[0]; // minY
		
		float iterator = 0;
		
		for (int i = 1; i < polygonLocationsX.length; i++) {
			iterator = polygonLocationsX[i];
			if (maxAndMins[0] < iterator) {
				maxAndMins[0] = iterator;
			}
			if (maxAndMins[1] > iterator) {
				maxAndMins[1] = iterator;
			}
		}
		for (int i = 1; i < polygonLocationsY.length; i++) {
			iterator = polygonLocationsY[i];
			if (maxAndMins[2] < iterator) {
				maxAndMins[2] = iterator;
			}
			if (maxAndMins[3] > iterator) {
				maxAndMins[3] = iterator;
			}
		}
		
		return maxAndMins;
		
	}
	
	
	private static Line getPolyRay(float maxX, float minX, float pointX, float pointY) {
		float e = getEpsilon(maxX, minX);
		return new Line(minX - e, pointY, pointX, pointY);
	}
	
	
	public static float getEpsilon(float maxX, float minX) {
		float e = 0f;
		e = ((maxX - minX) / 100);
		return e;
	}
	
	
	public synchronized static boolean doLinesIntersect(Line line1, Line line2) {
		return line1.intersectsLine(line2);
	}
	
	
	public static boolean polygonIntersectsPolygon(float[] polygon1X, float[] polygon1Y, float[] polygon2X, float[] polygon2Y) {
		for (int i = 0; i < polygon1X.length; i++) {
			if (polygonContainsPoint(polygon1X[i], polygon1Y[i], polygon2X, polygon2Y)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static float abs(float num) {
		if (num >= 0) {
			return num;
		} else {
			return num * -1;
		}
	}
	
	
	public static float getSign(float num) {
		if (num >= 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
	public static float reduceToZero(float num, float rate) {
		rate = abs(rate);
		if (num > 0) {
			return Math.max(0, num - rate);
		} else if (num < 0) {
			return Math.min(0, num + rate);
		} else {
			return 0.0f;
		}
	}
	
}
