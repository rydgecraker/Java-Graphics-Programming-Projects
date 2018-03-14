package com.rcs.rydge_craker_studios.basic_utilities;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;



public class Line extends Line2D {
	
	public float	x1;
	public float	y1;
	public float	x2;
	public float	y2;
	
	
	
	public Line(float x1, float y1, float x2, float y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	
	
	public float getDistance(){
		return R.Math.distance2d(x1, x2, y1, y2);
	}
	
	
	
	public Rectangle2D getBounds2D(){
		float x = Math.min(x1, x2);
		float y = Math.min(y1, y2);
		float w = Math.abs(x1 - x2);
		float h = Math.abs(y1 - y2);
		return new Rectangle2D.Double(x, y, w, h);
	}
	
	
	
	public Point2D getP1(){
		return new Point2D.Float(x1, y1);
	}
	
	
	
	public Point2D getP2(){
		return new Point2D.Float(x2, y2);
	}
	
	
	
	public double getX1(){
		return x1;
	}
	
	
	
	public double getX2(){
		return y1;
	}
	
	
	
	public double getY1(){
		return x2;
	}
	
	
	
	public double getY2(){
		return y2;
	}
	
	
	
	public void setLine(double x1, double y1, double x2, double y2){
		this.x1 = (float) x1;
		this.y1 = (float) y1;
		this.x2 = (float) x2;
		this.y2 = (float) y2;
	}
	
	
	
}
