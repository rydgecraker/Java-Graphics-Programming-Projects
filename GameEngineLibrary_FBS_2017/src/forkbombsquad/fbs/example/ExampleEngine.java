package forkbombsquad.fbs.example;


import java.awt.Color;

import forkbombsquad.fbs.engine.AbstractEngine;


public class ExampleEngine extends AbstractEngine {
	
	public ExampleGamePanel panel;
	
	
	public ExampleEngine(String gameTitle, String gameVersion, boolean debugMode) {
		super(gameTitle, gameVersion, debugMode);
	}
	
	
	public ExampleEngine(String gameTitle, String gameVersion, double ticksPerSecond, boolean debugMode) {
		super(gameTitle, gameVersion, ticksPerSecond, debugMode);
	}
	
	
	public ExampleEngine(String gameTitle, String gameVersion, double ticksPerSecond, int numThreads, double tickSleepTime, boolean debugMode) {
		super(gameTitle, gameVersion, ticksPerSecond, numThreads, tickSleepTime, debugMode);
	}
	
	
	@Override
	protected void initFirst() {
	}
	
	
	@Override
	protected void initBeforePanel() {
	}
	
	
	@Override
	protected void initPanel() {
		panel = new ExampleGamePanel(this);
		panel.setFocusable(true);
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		panel.addMouseWheelListener(panel);
		panel.addKeyListener(panel);
		panel.setBackground(Color.BLACK);
		frame.setContentPane(panel);
		frame.revalidate();
	}
	
	
	@Override
	protected void initAfterPanel() {
	}
	
	
	@Override
	protected void finalInit() {
	}
	
	
	@Override
	protected void lastMinuteStartupTasks() {
	}
	
	
	@Override
	protected void startEverythingElse() {
	}
	
	
	@Override
	protected void debug() {
	}
	
	
	@Override
	protected void gameLoop() {
		panel.repaint();
		panel.lowLevelEvents();
		panel.requestFocus();
	}
	
	
	@Override
	protected void tick() {
		panel.tick();
	}
	
	
	@Override
	protected void tock() {
		panel.tock();
	}
	
	
	@Override
	protected void otherLoop(int currentThread) {
		panel.otherLoop(currentThread);
	}
	
}
