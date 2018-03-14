package forkbombsquad.fbs.engine;


import java.awt.Graphics2D;

import forkbombsquad.fbs.utilities.math.Distance;
import forkbombsquad.fbs.utilities.math.Rotation;
import forkbombsquad.fbs.utilities.math.Vector2;


public abstract class AbstractGameObject2D {
	
	public Vector2	location;
	public Vector2	velocity;
	public Rotation	angle;
	
	
	public AbstractGameObject2D(float x, float y, float angle) {
		location = new Vector2(x, y);
		velocity = new Vector2();
		this.angle = new Rotation(angle);
	}
	
	
	public abstract void update(float dt);
	
	
	public abstract void draw(Graphics2D g);
	
	
	public void rotate(float amount) {
		angle.angle += amount;
	}
	
	
	public void setRotation(float amount) {
		angle = new Rotation(amount);
	}
	
	
	public void accelerate(Vector2 amount) {
		velocity = velocity.add(amount);
	}
	
	
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	
	public void move(Vector2 amount) {
		location = location.add(amount);
	}
	
	
	public void setLoctaion(Vector2 location) {
		this.location = location;
	}
	
	
	public float distanceFrom(AbstractGameObject2D gameObject) {
		return Distance.distance2d(location.x, location.y, gameObject.location.x, gameObject.location.y);
	}
	
	
	
}
