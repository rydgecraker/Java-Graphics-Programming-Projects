package com.rcs.rydge_craker_studios.basic_utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public final class R {
	
	static {
		
	}
	
	
	public final static class valueOf {
		
		public static final float	ONE_MS								= 1.0f;
		public static final float	EARTH_GRAVITY_MS					= 9.81f;
		public static final float	VERY_LIGHT_GRAVITY_MS				= 2.0f;
		public static final float	LIGHT_GRAVITY_MS					= 5.0f;
		public static final float	MED_LIGHT_GRAVITY_MS				= 10.0f;
		public static final float	MEDIUM_GRAVITY_MS					= 20.0f;
		public static final float	MED_HEAVY_GRAVITY_MS				= 35.0f;
		public static final float	HEAVY_GRAVITY_MS					= 50.0f;
		public static final float	VERY_HEAVY_GRAVITY_MS				= 100.0f;
		
		public static final float	EXTREMELY_LIGHT_FRICTION_COEFFICENT	= 0.01f;
		public static final float	VERY_LIGHT_FRICTION_COEFFICENT		= 0.1f;
		public static final float	LIGHT_FRICTION_COEFFICENT			= 0.2f;
		public static final float	MED_LIGHT_FRICTION_COEFFICENT		= 0.4f;
		public static final float	MEDIUM_FRICTION_COEFFICENT			= 0.4f;
		public static final float	MED_HEAVY_FRICTION_COEFFICENT		= 0.4f;
		public static final float	HEAVY_FRICTION_COEFFICENT			= 0.7f;
		public static final float	VERY_HEAVY_FRICTION_COEFFICENT		= 1f;
		public static final float	EXTREMELY_HEAVY_FRICTION_COEFFICENT	= 10f;
		
		
		public static final float	AVERAGE_HUMAN_MAX_JUMP_VELOCITY_MS	= 4.572f;
		public static final float	AVERAGE_HUMAN_MAX_RUN_VELOCITY_MS	= 4.4704f;
		public static final float	AVERAGE_HUMAN_RUN_ACCELERATION_MS2	= 0.89408f;
		public static final float	AVERAGE_HUMAN_JUMP_ACCELERATION_MS2	= 1.524f;
		public static final float	AVERAGE_HUMAN_ROTATION_SPEED		= 0.5f;
		
		
		public static final float	HUMAN_TERMANAL_VELOCITY_MS			= 56.0f;
		
		
		public static float extremelyLightEarthFriction(float mass){
			return EXTREMELY_LIGHT_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float lightEarthFriction(float mass){
			return LIGHT_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float medLightEarthFriction(float mass){
			return MED_LIGHT_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float MedEarthFriction(float mass){
			return MEDIUM_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float MedHeavyEarthFriction(float mass){
			return MED_HEAVY_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float heavyEarthFriction(float mass){
			return HEAVY_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float veryHeavyEarthFriction(float mass){
			return VERY_HEAVY_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float extremelyHeavyEarthFriction(float mass){
			return EXTREMELY_HEAVY_FRICTION_COEFFICENT * (EARTH_GRAVITY_MS * mass);
		}
		
		public static float extremelyLightFriction(float gravity, float mass){
			return EXTREMELY_LIGHT_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float lightFriction(float gravity, float mass){
			return LIGHT_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float medLightFriction(float gravity, float mass){
			return MED_LIGHT_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float MedFriction(float gravity, float mass){
			return MEDIUM_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float MedHeavyFriction(float gravity, float mass){
			return MED_HEAVY_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float heavyFriction(float gravity, float mass){
			return HEAVY_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float veryHeavyFriction(float gravity, float mass){
			return VERY_HEAVY_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		public static float extremelyHeavyFriction(float gravity, float mass){
			return EXTREMELY_HEAVY_FRICTION_COEFFICENT * (gravity * mass);
		}
		
		
		public static float friction(float coefficent, float gravity, float mass){
			return (coefficent * (gravity * mass));
		}
		
		public static float metersPerSecondToPixelsPerSecond(float metersPerSecond, float pixelsPerMeter){
			return metersPerSecond * pixelsPerMeter;
		}
	}
	
	// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓  ▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓
	// ▓▓        ▓▓    ▓▓  ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓    ▓▓     ▓▓   
	// ▓▓▓▓▓▓    ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓ ▓▓ ▓▓  ▓▓▓▓▓▓▓▓     ▓▓   
	// ▓▓        ▓▓    ▓▓  ▓▓  ▓▓    ▓▓ ▓▓ ▓▓  ▓▓    ▓▓     ▓▓   
	// ▓▓        ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  ▓▓    ▓▓  ▓▓    ▓▓     ▓▓   
	// 
	// ▓▓▓   ▓▓  ▓▓    ▓▓  ▓▓▓  ▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓  
	// ▓▓▓▓  ▓▓  ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  ▓▓        ▓▓    ▓▓
	// ▓▓ ▓▓ ▓▓  ▓▓    ▓▓  ▓▓ ▓▓ ▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓
	// ▓▓  ▓▓▓▓  ▓▓    ▓▓  ▓▓ ▓▓ ▓▓  ▓▓    ▓▓  ▓▓        ▓▓  ▓▓
	// ▓▓   ▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓    ▓▓
	
	
	public final static class FormatNumber {
		
		private static DecimalFormat	addCommas;
		
		private static DecimalFormat	trunicateNdecimalPlaces;
		private static DecimalFormat	trunicateNdecimalPlacesCommas;
		
		private static DecimalFormat	roundNdecimalPlaces;
		private static DecimalFormat	roundNdecimalPlacesCommas;
		
		private static DecimalFormat	roundToNearestInt;
		private static DecimalFormat	roundToNearestIntCommas;
		
		private static DecimalFormat	trunicateToNearestInt;
		private static DecimalFormat	trunicateToNearestIntCommas;
		
		
		private static RoundingMode		roundingMode;
		
		
		static {
			
			roundingMode = RoundingMode.HALF_UP;
			
			addCommas = new DecimalFormat("#,###");
			addCommas.setGroupingUsed(true);
			addCommas.setRoundingMode(RoundingMode.UNNECESSARY);
			
			
			//No Commas
			trunicateNdecimalPlaces = new DecimalFormat("#");
			trunicateNdecimalPlaces.setGroupingUsed(false);
			trunicateNdecimalPlaces.setRoundingMode(RoundingMode.DOWN);
			
			roundNdecimalPlaces = new DecimalFormat("#");
			roundNdecimalPlaces.setGroupingUsed(false);
			roundNdecimalPlaces.setRoundingMode(RoundingMode.HALF_UP);
			
			roundToNearestInt = new DecimalFormat("#");
			roundToNearestInt.setGroupingUsed(false);
			roundToNearestInt.setMaximumFractionDigits(0);
			roundToNearestInt.setRoundingMode(RoundingMode.HALF_UP);
			
			trunicateToNearestInt = new DecimalFormat("#");
			trunicateToNearestInt.setGroupingUsed(false);
			trunicateToNearestInt.setMaximumFractionDigits(0);
			trunicateToNearestInt.setRoundingMode(RoundingMode.DOWN);
			
			//Commas
			trunicateNdecimalPlacesCommas = new DecimalFormat("#");
			trunicateNdecimalPlacesCommas.setGroupingUsed(true);
			trunicateNdecimalPlacesCommas.setRoundingMode(RoundingMode.DOWN);
			
			roundNdecimalPlacesCommas = new DecimalFormat("#");
			roundNdecimalPlacesCommas.setGroupingUsed(true);
			roundNdecimalPlacesCommas.setRoundingMode(RoundingMode.HALF_UP);
			
			roundToNearestIntCommas = new DecimalFormat("#");
			roundToNearestIntCommas.setGroupingUsed(true);
			roundToNearestIntCommas.setMaximumFractionDigits(0);
			roundToNearestIntCommas.setRoundingMode(RoundingMode.HALF_UP);
			
			trunicateToNearestIntCommas = new DecimalFormat("#");
			trunicateToNearestIntCommas.setGroupingUsed(true);
			trunicateToNearestIntCommas.setMaximumFractionDigits(0);
			trunicateToNearestIntCommas.setRoundingMode(RoundingMode.DOWN);
			
			
		}
		
		
		public synchronized static void setRoundingMode(RoundingMode rm){
			roundingMode = rm;
			
			addCommas.setRoundingMode(RoundingMode.UNNECESSARY);
			//No Commas
			trunicateNdecimalPlaces.setRoundingMode(RoundingMode.DOWN);
			roundNdecimalPlaces.setRoundingMode(rm);
			roundToNearestInt.setRoundingMode(rm);
			trunicateToNearestInt.setRoundingMode(RoundingMode.DOWN);
			
			//Commas
			trunicateNdecimalPlacesCommas.setRoundingMode(RoundingMode.DOWN);
			roundNdecimalPlacesCommas.setRoundingMode(rm);
			roundToNearestIntCommas.setRoundingMode(rm);
			trunicateToNearestIntCommas.setRoundingMode(RoundingMode.DOWN);
		}
		
		
		
		public synchronized static RoundingMode getRoundingMode(){
			return roundingMode;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		public synchronized static String addCommas(int i){
			return addCommas.format(i);
		}
		
		public synchronized static String addCommas(float f){
			return addCommas.format(f);
		}
		
		public synchronized static String addCommas(double d){
			return addCommas.format(d);
		}
		
		public synchronized static String addCommas(long l){
			return addCommas.format(l);
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		public synchronized static String trunicateToDecimalPlaces(float f, int decimalPlaces){
			trunicateNdecimalPlaces.setMaximumFractionDigits(decimalPlaces);
			return trunicateNdecimalPlaces.format(f);
		}
		
		public synchronized static String trunicateToDecimalPlaces(double d, int decimalPlaces){
			trunicateNdecimalPlaces.setMaximumFractionDigits(decimalPlaces);
			return trunicateNdecimalPlaces.format(d);
		}
		
		public synchronized static String trunicateToDecimalPlacesCommas(float f, int decimalPlaces){
			trunicateNdecimalPlacesCommas.setMaximumFractionDigits(decimalPlaces);
			return trunicateNdecimalPlacesCommas.format(f);
		}
		
		public synchronized static String trunicateToDecimalPlacesCommas(double d, int decimalPlaces){
			trunicateNdecimalPlacesCommas.setMaximumFractionDigits(decimalPlaces);
			return trunicateNdecimalPlacesCommas.format(d);
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		public synchronized static String roundToDecimalPlaces(float f, int decimalPlaces){
			roundNdecimalPlaces.setMaximumFractionDigits(decimalPlaces);
			return roundNdecimalPlaces.format(f);
		}
		
		public synchronized static String roundToDecimalPlaces(double d, int decimalPlaces){
			roundNdecimalPlaces.setMaximumFractionDigits(decimalPlaces);
			return roundNdecimalPlaces.format(d);
		}
		
		public synchronized static String roundToDecimalPlacesCommas(float f, int decimalPlaces){
			roundNdecimalPlacesCommas.setMaximumFractionDigits(decimalPlaces);
			return roundNdecimalPlacesCommas.format(f);
		}
		
		public synchronized static String roundToDecimalPlacesCommas(double d, int decimalPlaces){
			roundNdecimalPlacesCommas.setMaximumFractionDigits(decimalPlaces);
			return roundNdecimalPlacesCommas.format(d);
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		public synchronized static String roundToNearestInt(float f){
			return roundToNearestInt.format(f);
		}
		
		public synchronized static String roundToNearestInt(double d){
			return roundToNearestInt.format(d);
		}
		
		public synchronized static String roundToNearestIntCommas(float f){
			return roundToNearestIntCommas.format(f);
		}
		
		public synchronized static String roundToNearestIntCommas(double d){
			return roundToNearestIntCommas.format(d);
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		public synchronized static String trunicateToNearestInt(float f){
			return trunicateToNearestInt.format(f);
		}
		
		public synchronized static String trunicateToNearestInt(double d){
			return trunicateToNearestInt.format(d);
		}
		
		public synchronized static String trunicateToNearestIntCommas(float f){
			return trunicateToNearestIntCommas.format(f);
		}
		
		public synchronized static String trunicateToNearestIntCommas(double d){
			return trunicateToNearestIntCommas.format(d);
		}
	}
	
	// ▓▓▓  ▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓    ▓▓
	// ▓▓▓▓▓▓▓▓  ▓▓    ▓▓     ▓▓     ▓▓    ▓▓
	// ▓▓ ▓▓ ▓▓  ▓▓▓▓▓▓▓▓     ▓▓     ▓▓▓▓▓▓▓▓
	// ▓▓ ▓▓ ▓▓  ▓▓    ▓▓     ▓▓     ▓▓    ▓▓
	// ▓▓    ▓▓  ▓▓    ▓▓     ▓▓     ▓▓    ▓▓
	
	public final static class Math {
		
		
		public synchronized static float distance2d(float x1, float x2, float y1, float y2){
			return (float) java.lang.Math.sqrt((java.lang.Math.pow((x2 - x1), 2)) + (java.lang.Math.pow((y2 - y1), 2)));
		}
		
		public synchronized static double distance2d(double x1, double x2, double y1, double y2){
			return java.lang.Math.sqrt((java.lang.Math.pow((x2 - x1), 2)) + (java.lang.Math.pow((y2 - y1), 2)));
		}
		
		public synchronized static float distance3d(float x1, float x2, float y1, float y2, float z1, float z2){
			return (float) java.lang.Math.sqrt((java.lang.Math.pow((x2 - x1), 2)) + (java.lang.Math.pow((y2 - y1), 2) + (java.lang.Math.pow((z2 - z1), 2))));
		}
		
		public synchronized static double distance3d(double x1, double x2, double y1, double y2, double z1, double z2){
			return java.lang.Math.sqrt((java.lang.Math.pow((x2 - x1), 2)) + (java.lang.Math.pow((y2 - y1), 2) + (java.lang.Math.pow((z2 - z1), 2))));
		}
		
		public synchronized static boolean doLinesIntersect(Line line1, Line line2){
			return line1.intersectsLine(line2);
		}
		
		public synchronized static boolean useRaycasting(Line ray, Line[] sides){
			int intersections = 0;
			for (int i = 0; i < sides.length; i++) {
				if(doLinesIntersect(ray, sides[i])) {
					intersections++;
				}
			}
			if((intersections & 1) == 1) {
				return true; //Point is inside polygon
			} else {
				return false; //Point is outside polygon
			}
		}
		
		
		public static synchronized Line[] getSidesOfPolygon(float[] polygonLocationsX, float[] polygonLocationsY){
			Line[] sides = new Line[polygonLocationsX.length];
			
			for (int i = 0; i < sides.length; i++) {
				if(i != sides.length - 1) {
					sides[i] = new Line(polygonLocationsX[i], polygonLocationsY[i], polygonLocationsX[i + 1], polygonLocationsY[i + 1]);
				} else {
					sides[i] = new Line(polygonLocationsX[i], polygonLocationsY[i], polygonLocationsX[0], polygonLocationsY[0]);
				}
			}
			
			return sides;
		}
		
		
		public synchronized static boolean polygonContainsPoint(float pointX, float pointY, float[] polygonLocationsX, float[] polygonLocationsY){
			
			float[] maxAndMins = MathHelper.findMaxAndMinPoly(polygonLocationsX, polygonLocationsY);
			
			if(!MathHelper.polyContainsRoughCheck(pointX, pointY, maxAndMins)) {
				return false;
			}
			
			Line ray = MathHelper.getPolyRay(maxAndMins[0], maxAndMins[1], pointX, pointY);
			Line[] sides = getSidesOfPolygon(polygonLocationsX, polygonLocationsY);
			
			return useRaycasting(ray, sides);
		}
		
		public synchronized static boolean polygonContainsPoint(float pointX, float pointY, float[] polygonLocationsX, float[] polygonLocationsY, Line[] polygonSides){
			float[] maxAndMins = MathHelper.findMaxAndMinPoly(polygonLocationsX, polygonLocationsY);
			
			if(!MathHelper.polyContainsRoughCheck(pointX, pointY, maxAndMins)) {
				return false;
			}
			
			Line ray = MathHelper.getPolyRay(maxAndMins[0], maxAndMins[1], pointX, pointY);
			
			return useRaycasting(ray, polygonSides);
		}
		
		
		public synchronized static boolean polygonContainsPoint(float pointX, float pointY, float[] polygonLocationsX, float[] polygonLocationsY, float polygonMaxX, float polygonMinX, float polygonMaxY, float polygonMinY){
			float[] maxAndMins = { polygonMaxX, polygonMinX, polygonMaxY, polygonMinY };
			
			if(!MathHelper.polyContainsRoughCheck(pointX, pointY, maxAndMins)) {
				return false;
			}
			
			Line ray = MathHelper.getPolyRay(maxAndMins[0], maxAndMins[1], pointX, pointY);
			Line[] sides = getSidesOfPolygon(polygonLocationsX, polygonLocationsY);
			
			return useRaycasting(ray, sides);
		}
		
		public synchronized static boolean polygonContainsPoint(float pointX, float pointY, Line[] polygonSides, float polygonMaxX, float polygonMinX, float polygonMaxY, float polygonMinY){
			float[] maxAndMins = { polygonMaxX, polygonMinX, polygonMaxY, polygonMinY };
			
			if(!MathHelper.polyContainsRoughCheck(pointX, pointY, maxAndMins)) {
				return false;
			}
			
			Line ray = MathHelper.getPolyRay(maxAndMins[0], maxAndMins[1], pointX, pointY);
			
			return useRaycasting(ray, polygonSides);
		}
		
		
		public synchronized static boolean polygonIntersectsPolygon(float[] polygon1X, float[] polygon1Y, float[] polygon2X, float[] polygon2Y){
			for (int i = 0; i < polygon1X.length; i++) {
				if(polygonContainsPoint(polygon1X[i], polygon1Y[i], polygon2X, polygon2Y)) {
					return true;
				}
			}
			
			return false;
		}
		
	}
	
	// ▓▓▓  ▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓    ▓▓
	// ▓▓▓▓▓▓▓▓  ▓▓    ▓▓     ▓▓     ▓▓    ▓▓
	// ▓▓ ▓▓ ▓▓  ▓▓▓▓▓▓▓▓     ▓▓     ▓▓▓▓▓▓▓▓
	// ▓▓ ▓▓ ▓▓  ▓▓    ▓▓     ▓▓     ▓▓    ▓▓
	// ▓▓    ▓▓  ▓▓    ▓▓     ▓▓     ▓▓    ▓▓
	// 
	// ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓        ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    
	// ▓▓    ▓▓  ▓▓        ▓▓        ▓▓    ▓▓  ▓▓        ▓▓    ▓▓  
	// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓        ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  
	// ▓▓    ▓▓  ▓▓        ▓▓        ▓▓        ▓▓        ▓▓  ▓▓    
	// ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓        ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  
	
	public final static class MathHelper {
		
		public static synchronized Line getPolyRay(float maxX, float minX, float pointX, float pointY){
			float e = getEpsilon(maxX, minX);
			return new Line(minX - e, pointY, pointX, pointY);
		}
		
		public static synchronized float getEpsilon(float maxX, float minX){
			float e = 0f;
			e = ((maxX - minX) / 100);
			return e;
		}
		
		public static synchronized boolean polyContainsRoughCheck(float x, float y, float[] maxAndMins){
			return polyContainsRoughCheck(x, y, maxAndMins[0], maxAndMins[1], maxAndMins[2], maxAndMins[3]);
		}
		
		
		public static synchronized boolean polyContainsRoughCheck(float x, float y, float maxX, float minX, float maxY, float minY){
			if(x > maxX || x < minX || y > maxY || y < minY) {
				return false;
			} else {
				return true;
			}
		}
		
		public static synchronized float[] findMaxAndMinPoly(float[] polygonLocationsX, float[] polygonLocationsY){
			float[] maxAndMins = new float[4];
			
			maxAndMins[0] = polygonLocationsX[0]; //maxX
			maxAndMins[1] = polygonLocationsX[0]; //minX
			maxAndMins[2] = polygonLocationsY[0]; //maxY
			maxAndMins[3] = polygonLocationsY[0]; //minY
			
			float iterator = 0;
			
			for (int i = 1; i < polygonLocationsX.length; i++) {
				iterator = polygonLocationsX[i];
				if(maxAndMins[0] < iterator) {
					maxAndMins[0] = iterator;
				}
				if(maxAndMins[1] > iterator) {
					maxAndMins[1] = iterator;
				}
			}
			for (int i = 1; i < polygonLocationsY.length; i++) {
				iterator = polygonLocationsY[i];
				if(maxAndMins[2] < iterator) {
					maxAndMins[2] = iterator;
				}
				if(maxAndMins[3] > iterator) {
					maxAndMins[3] = iterator;
				}
			}
			
			return maxAndMins;
			
		}
		
	}
	
	
	
	// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓   ▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓   ▓▓  ▓▓▓▓▓▓▓▓
	// ▓▓    ▓▓  ▓▓    ▓▓     ▓▓     ▓▓▓▓  ▓▓     ▓▓        ▓▓     ▓▓▓▓  ▓▓  ▓▓    
	// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓     ▓▓     ▓▓ ▓▓ ▓▓     ▓▓        ▓▓     ▓▓ ▓▓ ▓▓  ▓▓  ▓▓▓▓
	// ▓▓        ▓▓  ▓▓       ▓▓     ▓▓  ▓▓▓▓     ▓▓        ▓▓     ▓▓  ▓▓▓▓  ▓▓    ▓▓
	// ▓▓        ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓   ▓▓▓     ▓▓     ▓▓▓▓▓▓▓▓  ▓▓   ▓▓▓  ▓▓▓▓▓▓▓▓
	
	
	public static void printf(String s){
		System.out.println(s);
	}
	
	public static void printf(float f){
		System.out.println(f);
	}
	
	public static void printf(int i){
		System.out.println(i);
	}
	
	public static void printf(long l){
		System.out.println(l);
	}
	
	public static void printf(double d){
		System.out.println(d);
	}
	
	public static void printf(char c){
		System.out.println(c);
	}
	
	public static void printf(Object o){
		System.out.println(o.toString());
	}
	
	public static void print(String s){
		System.out.print(s);
	}
	
	
	
	// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓   ▓▓  ▓▓▓▓▓▓▓▓  
	// ▓▓           ▓▓     ▓▓    ▓▓     ▓▓     ▓▓▓▓  ▓▓  ▓▓        
	// ▓▓▓▓▓▓▓▓     ▓▓     ▓▓▓▓▓▓▓▓     ▓▓     ▓▓ ▓▓ ▓▓  ▓▓  ▓▓▓▓  
	//       ▓▓     ▓▓     ▓▓  ▓▓       ▓▓     ▓▓  ▓▓▓▓  ▓▓    ▓▓  
	// ▓▓▓▓▓▓▓▓     ▓▓     ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓   ▓▓▓  ▓▓▓▓▓▓▓▓  
	
	// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓   ▓▓  ▓▓▓▓▓▓▓▓
	// ▓▓    ▓▓  ▓▓    ▓▓  ▓▓        ▓▓    ▓▓  ▓▓    ▓▓     ▓▓        ▓▓     ▓▓    ▓▓  ▓▓▓▓  ▓▓  ▓▓
	// ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓     ▓▓        ▓▓     ▓▓    ▓▓  ▓▓ ▓▓ ▓▓  ▓▓▓▓▓▓▓▓
	// ▓▓    ▓▓  ▓▓        ▓▓        ▓▓  ▓▓    ▓▓    ▓▓     ▓▓        ▓▓     ▓▓    ▓▓  ▓▓  ▓▓▓▓        ▓▓
	// ▓▓▓▓▓▓▓▓  ▓▓        ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  ▓▓    ▓▓     ▓▓     ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓   ▓▓▓  ▓▓▓▓▓▓▓▓
	
	public final static class StringOpp {
		
		private static Resolution	scaleRes;
		
		static {
			scaleRes = new Resolution(1920f, 1080f);
		}
		
		
		// ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓   ▓▓  ▓▓▓▓▓▓▓▓  
		// ▓▓        ▓▓ ▓▓ ▓▓     ▓▓     ▓▓▓▓  ▓▓  ▓▓        
		// ▓▓▓▓▓▓▓▓  ▓▓ ▓▓ ▓▓     ▓▓     ▓▓ ▓▓ ▓▓  ▓▓  ▓▓▓▓  
		//       ▓▓  ▓▓▓▓▓▓▓▓     ▓▓     ▓▓  ▓▓▓▓  ▓▓    ▓▓  
		// ▓▓▓▓▓▓▓▓  ▓▓▓  ▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓   ▓▓▓  ▓▓▓▓▓▓▓▓  
		
		// ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓   ▓▓  ▓▓▓▓▓▓▓▓
		// ▓▓    ▓▓  ▓▓    ▓▓  ▓▓        ▓▓    ▓▓  ▓▓    ▓▓     ▓▓        ▓▓     ▓▓    ▓▓  ▓▓▓▓  ▓▓  ▓▓
		// ▓▓    ▓▓  ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓     ▓▓        ▓▓     ▓▓    ▓▓  ▓▓ ▓▓ ▓▓  ▓▓▓▓▓▓▓▓
		// ▓▓    ▓▓  ▓▓        ▓▓        ▓▓  ▓▓    ▓▓    ▓▓     ▓▓        ▓▓     ▓▓    ▓▓  ▓▓  ▓▓▓▓        ▓▓
		// ▓▓▓▓▓▓▓▓  ▓▓        ▓▓▓▓▓▓▓▓  ▓▓    ▓▓  ▓▓    ▓▓     ▓▓     ▓▓▓▓▓▓▓▓  ▓▓▓▓▓▓▓▓  ▓▓   ▓▓▓  ▓▓▓▓▓▓▓▓
		
		public final static class SwingOpp {
			
			
			//TODO
			
			
		}
		
		
		
		public static StringWithCoordinates scaleStringLoc(StringWithCoordinates text, float screenWidth, float screenHeight){
			text.setX((screenWidth * text.getX()) / scaleRes.getWidth());
			text.setY((screenHeight * text.getY()) / scaleRes.getHeight());
			return text;
		}
		
		public static StringWithCoordinates scaleStringLoc(StringWithCoordinates text, Resolution screenRes){
			return scaleStringLoc(text, screenRes.getWidth(), screenRes.getHeight());
		}
		
		public static StringWithCoordinates scaleStringLoc(StringWithCoordinates text, int screenWidth, int screenHeight){
			text.setX((screenWidth * text.getX()) / scaleRes.getWidth());
			text.setY((screenHeight * text.getY()) / scaleRes.getHeight());
			return text;
		}
		
		
		public static StringWithCoordinates scaleStringLocX(StringWithCoordinates text, int screenWidth){
			text.setX((screenWidth * text.getX()) / scaleRes.getWidth());
			return text;
		}
		
		public static StringWithCoordinates scaleStringLocY(StringWithCoordinates text, int screenHeight){
			text.setY((screenHeight * text.getY()) / scaleRes.getHeight());
			return text;
		}
		
		
		
		public static float[] scaleStringLoc(float stringX, float stringY, float screenWidth, float screenHeight){
			return new float[] { (screenWidth * stringX) / scaleRes.getWidth(), (screenHeight * stringY) / scaleRes.getHeight() };
		}
		
		public static float[] scaleStringLoc(float stringX, float stringY, Resolution screenRes){
			return scaleStringLoc(stringX, stringY, screenRes.getWidth(), screenRes.getHeight());
		}
		
		public static int[] scaleStringLoc(int stringX, int stringY, int screenWidth, int screenHeight){
			return new int[] { (int) ((screenWidth * stringX) / scaleRes.getWidth()), (int) ((screenHeight * stringY) / scaleRes.getHeight()) };
		}
		
		
		public static float scaleFont(float fontSize, float screenWidth){
			return (screenWidth * fontSize) / scaleRes.getWidth();
		}
		
		public static int scaleFont(int fontSize, int screenWidth){
			return (int) ((screenWidth * fontSize) / scaleRes.getWidth());
		}
		
		
		
		public static void setScaleWidth(float width){
			scaleRes.setWidth(width);
		}
		
		public static void setScaleHeight(float height){
			scaleRes.setHeight(height);
		}
		
		public static float getScaleWidth(){
			return scaleRes.getWidth();
		}
		
		public static float setScaleHeight(){
			return scaleRes.getHeight();
		}
		
		public static void setScaleResolution(Resolution r){
			scaleRes = r;
		}
		
		public static Resolution getScaleResolution(){
			return scaleRes;
		}
		
		public static String convertStringToASCII(String s){
			char[] chars = s.toCharArray();
			
			String temp = "";
			for (int i = 0; i < chars.length; i++) {
				temp += (int) chars[i];
			}
			
			
			return temp;
		}
		
		public static boolean isStringNumeric(String s){
			if(s == null || s.isEmpty()) {
				return false;
			}
			int i = 0;
			int stringLength = s.length();
			if(s.charAt(0) == '-') {
				if(stringLength > 1) {
					i++;
				} else {
					return false;
				}
			}
			if(!Character.isDigit(s.charAt(i)) || !Character.isDigit(s.charAt(stringLength - 1))) {
				return false;
			}
			i++;
			stringLength--;
			if(i >= stringLength) {
				return true;
			}
			
			
			
			for (; i < stringLength; i++) {
				if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') {
					return false;
				}
			}
			return true;
		}
		
		
		public static boolean isStringNumericNoSpecialChars(String s){
			if(s == null || s.isEmpty()) {
				return false;
			}
			int i = 0;
			int stringLength = s.length();
			if(!Character.isDigit(s.charAt(i)) || !Character.isDigit(s.charAt(stringLength - 1))) {
				return false;
			}
			i++;
			stringLength--;
			if(i >= stringLength) {
				return true;
			}
			
			
			
			for (; i < stringLength; i++) {
				if(!Character.isDigit(s.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}
	
	
	
	public static int scale(int screenWidth, int number, int scaleWidth){
		return (screenWidth * number) / scaleWidth;
	}
	
}
