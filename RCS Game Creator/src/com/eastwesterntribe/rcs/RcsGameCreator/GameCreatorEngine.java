package com.eastwesterntribe.rcs.RcsGameCreator;

import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglEngine;
import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglGamePanel;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglAudioHolder;



public class GameCreatorEngine extends AbstractRcsglEngine {
	
	public GameCreatorPanel	panel;
	public RcsglAudioHolder	sounds;
	public float			initialVolume;
	
	
	public GameCreatorEngine(String gameTitle, String gameVersion, int numThreads, int numTicks) {
		super(gameTitle, gameVersion, numThreads, numTicks);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void lastMinuteStartupTasks(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void startEverythingElse(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initBeforePanel(){
		initialVolume = -36.5f;
		sounds = new RcsglAudioHolder(initialVolume);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initPanel(){
		panel = new GameCreatorPanel(this);
		panel.setFocusable(true);
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		panel.addKeyListener(panel);
		panel.addMouseWheelListener(panel);
		frame.setContentPane(panel);
		frame.revalidate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void debugging(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void gameLoop(){
		panel.repaint();
		panel.lowLevelUpdates();
		panel.requestFocus();
		fpsCounter++;
		determineFPS();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tickLoop(){
		panel.tick();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tockLoop(){
		panel.tock();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void otherLoop(){
		
	}
	
	@Override
	protected AbstractRcsglGamePanel getGamePanel(){
		return panel;
	}
	
	
}
