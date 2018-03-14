package com.eastwesterntribe.rcs.RcsGamingLibrary;


/**
 * @author RCS<br>
 *         An abstract class meant to help setup the RCS Gaming Library<br>
 *         Should be extended in the Main Class
 * 
 */
public abstract class AbstractRcsglEngineStarter {
	
	
	/**
	 * The constructor. When Super() is called,<br>
	 * it calls these methods in order:
	 * <p>
	 * {@link #createNewEngine()}<br>
	 * {@link #addPartsToEngine()}<br>
	 * {@link #startEngine()}
	 */
	public AbstractRcsglEngineStarter() {
		createNewEngine();
		addPartsToEngine();
		startEngine();
	}
	
	/**
	 * @return A string representing the name of the game the engine will be running.
	 */
	public abstract String getGameName();
	
	
	/**
	 * Typically labled as Indev 0.00xx, Alpha 0.0xx, Beta 0.xx, or Release x.x
	 * 
	 * @return Returns the version of the game labeled as above
	 */
	public abstract String getGameVersion();
	
	/**
	 * @see AbstractRcsglEngine
	 * @return The engine that is running the game.
	 */
	public abstract AbstractRcsglEngine getEngine();
	
	
	//Where a new extension of the Engine is created
	//Called in the constructor
	/**
	 * Called first in the constructor.
	 * <p>
	 * Where a new version of Engine should be created and stored within the class.
	 * 
	 * @see AbstractRcsglEngine
	 */
	protected abstract void createNewEngine();
	
	/**
	 * Called second in the constructor.
	 * <p>
	 * Where any parts (such as textures and sound effects) can be loaded into the engine,<br>
	 * before it beings to run
	 * 
	 * @see AbstractRcsglEngine
	 * @see RCSMenuBar
	 */
	protected abstract void addPartsToEngine();
	
	//Where Engine.start() is called
	//Last thing the constructor does
	/**
	 * Called third (and lastly) by constructor.
	 * <p>
	 * Where Engine.start() should be called.
	 * 
	 * @see AbstractRcsglEngine
	 */
	protected abstract void startEngine();
	
	
	
}
