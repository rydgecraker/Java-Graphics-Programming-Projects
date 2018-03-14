package forkbombsquad.fbs.example;


import java.awt.Color;
import java.awt.Graphics2D;

import forkbombsquad.fbs.engine.AbstractGameObject2D;


public class ExamplePlayer extends AbstractGameObject2D {
	
	public int width, height;
	
	
	public ExamplePlayer(float x, float y, float angle, int width, int height) {
		super(x, y, angle);
		this.width = width;
		this.height = height;
	}
	
	
	@Override
	public void update(float dt) {
		applyVelocity(dt);
	}
	
	
	public void applyVelocity(float dt) {
		location.addApply(velocity.multiplyBy(dt));
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.drawRect((int) (location.x), (int) (location.y), width, height);
	}
	
}
