package com.eastwesterntribe.rcs.RcsGameCreator;

import java.io.File;

import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglEngine;
import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglEngineStarter;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglAudio;


/**
 * Welcome to RCSGE! (RCS Game Editor)<br>
 * Version 0.0010 Indev
 * <p>
 * Using the RCSGL<br>
 * Version 0.0015 Indev
 * <p>
 * This is where the game beings it's journey.
 */
public class RcsgcStarter extends AbstractRcsglEngineStarter {
	
	private static final String	GAME_NAME			= "RCS Game Creator";
	private static final String	GAME_VERSION		= "Indev. 0.0050";
	private static final int	NUMBER_OF_THREADS	= 2;
	private static final int	NUMBER_OF_TICKS		= 100;
	
	
	private GameCreatorEngine	engine;
	
	
	public static void main(String[] args){
		new RcsgcStarter();
	}
	
	public RcsgcStarter() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getGameName(){
		return GAME_NAME;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getGameVersion(){
		return GAME_VERSION;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractRcsglEngine getEngine(){
		return engine;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createNewEngine(){
		this.engine = new GameCreatorEngine(GAME_NAME, GAME_VERSION, NUMBER_OF_THREADS, NUMBER_OF_TICKS);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addPartsToEngine(){
		String resourcesFolder = new File("").getAbsolutePath() + "\\Resources\\";
		engine.sounds.addAudio(new RcsglAudio("planb", resourcesFolder + "testOne.wav"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void startEngine(){
		engine.start();
	}
	
	
	
}
