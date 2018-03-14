package forkbombsquad.fbs.example;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import forkbombsquad.fbs.engine.AbstractEngine;
import forkbombsquad.fbs.engine.AbstractGamePanel;
import forkbombsquad.fbs.utilities.input_binding.Binding;
import forkbombsquad.fbs.utilities.math.Vector2;


public class ExampleGamePanel extends AbstractGamePanel {
	
	/**
	 * Using last date it was changed for the version id
	 */
	private static final long	serialVersionUID	= 12182017L;
	
	private ExamplePlayer		player;
	private AbstractEngine		engine;
	
	
	public ExampleGamePanel(AbstractEngine e) {
		super(e);
	}
	
	
	@Override
	protected void setDefaultInputs() {
		Binding[] bindings = new Binding[5];
		bindings[0] = new Binding("QUIT", KeyEvent.VK_ESCAPE);
		bindings[1] = new Binding("JUMP", KeyEvent.VK_SPACE);
		bindings[2] = new Binding("RIGHT", KeyEvent.VK_D);
		bindings[3] = new Binding("LEFT", KeyEvent.VK_A);
		bindings[4] = new Binding("DOWN", KeyEvent.VK_S);
		
		input.addBindings(bindings);
	}
	
	
	@Override
	protected void initialize(AbstractEngine e) {
		player = new ExamplePlayer(100, 100, 0, 10, 20);
		player.setVelocity(new Vector2(50f, 50f));
		this.engine = e;
	}
	
	
	@Override
	protected void lowLevelEvents() {
		if (input.isPressed("QUIT")) {
			System.exit(0);
		}
		
		if (input.isSinglePressed("JUMP")) {
			
		}
		if (input.isPressed("JUMP")) {
			
		}
		
		if (input.isSinglePressed("RIGHT")) {
			
		}
		if (input.isPressed("RIGHT")) {
			
		}
		
		if (input.isSinglePressed("LEFT")) {
			
		}
		if (input.isPressed("LEFT")) {
			
		}
		
		if (input.isSinglePressed("DOWN")) {
			
		}
		if (input.isPressed("DOWN")) {
			
		}
		
	}
	
	
	@Override
	protected void screenPainter(Graphics2D g) {
		player.draw(g);
	}
	
	
	@Override
	protected void tick() {
		player.update(engine.deltaTime);
	}
	
	
	@Override
	protected void tock() {
		
	}
	
	
	protected void otherLoop(int currentThread) {
		
	}
	
}
