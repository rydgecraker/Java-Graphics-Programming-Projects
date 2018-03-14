package com.eastwesterntribe.rcs.RcsGamingLibrary;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


/**
 * RCSGL Engine<br>
 * Version 0.0015 Indev
 * <p>
 * 
 * Abstract Class that Runs the game.<br>
 * Must be extended in order to be used.<br>
 * <br>
 * Most of the "Under the hood" stuff is done in the game panel
 * <p>
 * The game loop and ticks begin here...<br>
 * although, again, most of their work is done through methods in the game panel
 */
public abstract class AbstractRcsglEngine implements Runnable {
	
	/**
	 * The version of RCSGL engine you're currently running Typically labled as Indev 0.00xx, Alpha
	 * 0.0xx, Beta 0.xx, or Release x.x
	 */
	public static final String	engineVersion			= "Alpha 0.002";
	
	public String				gameTitle;
	public String				gameVersion;
	
	
	/**
	 * The outer shell that holds the game panel
	 */
	public JFrame				frame;
	
	
	/**
	 * The screen size as determined by Graphics Device
	 */
	public int					screenWidth, screenHeight;
	
	/**
	 * The threads that run the game. Defaulted to 2 (Ticking and looping) but can be customized
	 * 
	 * @see #numThreads
	 */
	public Thread[]				threads;
	
	/**
	 * The number of running {@link #threads}. <br>
	 * Defined at the creation of the engine<br>
	 * Defaults to 2 if a number less than 2 is given
	 */
	public int					numThreads;
	
	
	/**
	 * Determines what {@link #threads} are allowed to do during their loop
	 */
	public boolean				gameRunning, ticking, looping;
	
	/**
	 * Limits how many ticks are allowed to occur per second
	 * <p>
	 * Defaulted to 20
	 * 
	 * @see #tickSleepTime
	 */
	public double				alottedTicskPerSecond;
	
	/**
	 * Number of milliseconds for the ticking thread to sleep before looping again<br>
	 * Helps keep CPU cycles down. <br>
	 * Adjust if you aren't getting the desired number of ticks per second
	 * <p>
	 * Also indirectly determines attempted ticks per second
	 * <p>
	 * Defaulted to 25 milliseconds
	 */
	public double				tickSleepTime			= 25;
	
	/**
	 * The tick sleep time is stored here for displaying purposes.
	 */
	public double				displaySleepTime;
	
	/**
	 * How long the game has been running in seconds
	 */
	public long					gameTimeSeconds;
	
	// Variables used to determine Frames per Second
	public int					fpsCounter				= 0;
	
	
	/**
	 * The variable that keeps track of the current Frames Per Second of the game.
	 */
	public int					currentFps				= 0;
	
	/**
	 * Variables used to determine the currentFps
	 */
	public double				averageFps, fpsKeeper, fpsCCounter = 0.0;
	
	
	/**
	 * The number of ticks per second that the game is currently getting
	 */
	public int					ticksPerSecond			= 0;
	
	
	/**
	 * The number of attempted ticks the game is trying to do
	 * 
	 * @see #tickSleepTime
	 */
	public int					suggestedTicksPerSecond	= 0;
	
	
	
	/**
	 * A counting variable used for FPS determination
	 */
	public int					tempCount				= 0;
	
	/**
	 * Boolean that determines whether or not the debug method within the engine is called<br>
	 * Typically used to print values to the console but can be customized<br>
	 * Defaulted to false
	 */
	public boolean				debugging				= false;
	
	/**
	 * Variables used to keep game ticks on time
	 */
	public int					tickCounter, tmpAccCounter;
	
	/**
	 * Variable used to keep game ticks on time
	 */
	public long					baseTime;
	/**
	 * Variable used to keep game ticks on time
	 */
	public double				timeForOneTick;
	/**
	 * Variable used to keep game ticks on time
	 */
	public double				averageTimeForOneTick;
	/**
	 * Variable used to keep game ticks on time
	 */
	public double				tempAverageTimeForOneTick;
	
	
	
	/**
	 * .<br>
	 * <i>-note-</i> the engine does not start running until {@link #start() start()} is called<br>
	 * Calls {@link #init()}
	 * 
	 * @param gameTitle
	 *            - String passed in representing the title of the current game <br>
	 *            Saved as an instance variable
	 * @param gameVersion
	 *            - String passed in representing the version of the current game<br>
	 *            Saved as an instance variable
	 * @param numThreads
	 *            - The number of threads you want the engine to run.<br>
	 *            Defaults to 2 when a number less than 2 is given
	 * 
	 */
	public AbstractRcsglEngine(String gameTitle, String gameVersion, int numThreads, int numTicks) {
		this.gameTitle = gameTitle;
		this.gameVersion = gameVersion;
		this.alottedTicskPerSecond = numTicks;
		if (numThreads < 2) {
			numThreads = 2;
		}
		init(numThreads);
	}
	
	
	/**
	 * Called when you want to start running the game.<br>
	 * Calls:
	 * <p>
	 * {@link #lastMinuteStartupTasks()}<br>
	 * {@link #startThreads()}<br>
	 * {@link #startEverythingElse()}
	 */
	public final void start() {
		lastMinuteStartupTasks();
		startThreads();
		startEverythingElse();
	}
	
	
	/**
	 * Used to start anything that needs to start before the engine threads begin looping
	 */
	protected abstract void lastMinuteStartupTasks();
	
	
	/**
	 * For starting everything that needs to begin after the threads have started looping
	 */
	protected abstract void startEverythingElse();
	
	
	
	private final void startThreads() {
		// Starts the game threads contained in the Thread array
		// Defaulted to the Run method of this class
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	
	/**
	 * Initializes the engine variables that won't change<br>
	 * Also sets the default values of those variables<br>
	 * Calls {@link #initPanel()} and {@link #initialize()}
	 * <p>
	 * Variables include:<br>
	 * {@link #screenWidth}, {@link #screenHeight}<br>
	 * {@link #frame}<br>
	 * {@link #gameRunning}, {@link #looping}, {@link #ticking}<br>
	 * {@link #numThreads}, {@link #threads}, {@link #gameTimeSeconds}
	 * <p>
	 * 
	 * Values set are:<br>
	 * frame size = screenWidth and screenHeight<br>
	 * frame state = MAXIMIZED_BOTH<br>
	 * frame undecorated = true<br>
	 * frame visible = true<br>
	 * 
	 */
	public final void init(int numThreads) {
		// Initializes all necessary engine variables
		
		// Gets Screen Size and sets it to class-level variables
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		// Initializes the frame and sets it's resolution to the screen resolution
		frame = new JFrame();
		// Ensures the frame is at full size and then gets rid of bars on the side
		frame.setState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(screenWidth, screenHeight);
		
		initBeforePanel();
		// initializes all components that go inside the frame (such as the game panel)
		initPanel();
		
		// makes the frame visible and refreshes it, painting it to the screen for the first time
		frame.setVisible(true);
		frame.revalidate();
		
		// Sets looping booleans to true, allowing the loops to run
		gameRunning = true;
		looping = true;
		ticking = true;
		
		// Initializes threads and tells them to run in this Class
		this.numThreads = numThreads;
		threads = new Thread[this.numThreads];
		for (int i = 0; i < this.numThreads; i++) {
			threads[i] = new Thread(this);
		}
		
		// Sets the game time to zero
		gameTimeSeconds = 0;
		timeForOneTick = 0;
		tickCounter = 0;
		baseTime = 0;
		averageTimeForOneTick = 0;
		tempAverageTimeForOneTick = 0;
		tmpAccCounter = 0;
		displaySleepTime = 0;
		// Waits for a class to call start() or initialize()
		initialize();
	}
	
	
	/**
	 * Called just before initPanel() so you can initialize things that it needs to use right away.
	 */
	protected abstract void initBeforePanel();
	
	
	/**
	 * Called after {@link #init()} to allow for initialization by extended classes
	 */
	protected abstract void initialize();
	
	
	
	/**
	 * Initializes the inner parts of the frame, including the {@link #GamePanel}
	 * <p>
	 * It should:<br>
	 * - Create a new panel and store it as an instance variable<br>
	 * - Set panel to focusable = true<br>
	 * - Add mouseListener(panel), mouseMotionListener(panel), and keyListener(panel)<br>
	 * - Set background color to an initial color<br>
	 * - Set the content panel of the frame to be GamePanel<br>
	 * - Revalidate frame
	 */
	protected abstract void initPanel();
	
	
	/**
	 * Allows you to specify anything that you want the engine to print out if debugging is
	 * enabled<br>
	 * {@link #debugging} is false by default
	 */
	protected abstract void debugging();
	
	
	/**
	 * A faster way of printing to the console
	 * 
	 * @param string
	 *            the string that's printed to the console
	 */
	protected void printf(String string) {
		System.out.println(string);
	}
	
	
	/**
	 * This is where all engine threads start
	 */
	public final void run() {
		runEngine();
	}
	
	
	private final void runEngine() {
		// All threads start here
		// set up variables before looping for accurate measurements
		baseTime = System.nanoTime();
		long currentTime = System.nanoTime();
		double temp = 0.0;
		tickCounter = 0;
		
		// Loops while the game is on
		while (gameRunning) {
			// Determines which thread is currently looping this code.
			// Thread 0 is the game loop thread.
			if (Thread.currentThread().getId() == threads[0].getId()) {
				// If looping is turned on, then it does gameLoop operations
				if (looping) {
					gameLoop();
					fpsCounter++;
				}
				// Thread 1 is the ticking thread.
			} else if (Thread.currentThread().getId() == threads[1].getId()) {
				// if ticking is turned on, do ticking operations
				if (ticking) {
					temp = (1.0 / (alottedTicskPerSecond));
					tickSleepTime = (((temp - (timeForOneTick / 1000000000)) * 1000000000) * .9);
					if (tickSleepTime <= 0) {
						tickSleepTime = 1;
					}
					currentTime = System.nanoTime();
					if (timeForOneTick > 0) {
						if ((currentTime - baseTime) < 1000000000) {
							if (tickCounter >= alottedTicskPerSecond) {
								try {
									if (tickSleepTime > 1000000) {
										Thread.sleep((long) (tickSleepTime / 1000000));
									} else {
										Thread.sleep(0, (int) tickSleepTime);
									}
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} else {
								tickNormally(currentTime);
							}
							
						} else {
							
							tockNormally(currentTime);
							
						}
					} else {
						tickWithoutRest(currentTime);
					}
					
				} // if ticking
			} else {
				otherLoop();
			}// if thread[x]
		} // Run method
		
		if (Thread.currentThread().getId() == threads[0].getId()) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		
	}
	
	
	private void tockNormally(long currentTime) {
		// Called when a second has passed since the tickTimer has been reset
		// resets tick counter and starts the loop again at 1 being this counts as a tick
		
		displaySleepTime = tickSleepTime;
		
		// Determine game time in seconds
		ticksPerSecond = tickCounter;
		tickCounter = 1;
		baseTime = System.nanoTime();
		gameTimeSeconds++;
		
		// Calls a tick loop that only happens once per second
		// Also called a tock
		tockLoop();
		
		// prints info to console if debug printing is allowed
		
		// Still does tick operations because it counts as a tick
		tickLoop();
		timeForOneTick = System.nanoTime() - currentTime;
		tempAverageTimeForOneTick += timeForOneTick;
		tmpAccCounter++;
		averageTimeForOneTick = tempAverageTimeForOneTick / tmpAccCounter;
		suggestedTicksPerSecond += ticksPerSecond;
		if (suggestedTicksPerSecond > 0) {
			suggestedTicksPerSecond /= 2;
		}
		// Sleeps for specified amount of time
		try {
			if (tickSleepTime > 1000000) {
				Thread.sleep((long) (tickSleepTime / 1000000));
			} else {
				Thread.sleep(0, (int) tickSleepTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private void tickWithoutRest(long currentTime) {
		
		if (tickCounter < alottedTicskPerSecond) {
			tickCounter++;
			
			// Called to do tick operations
			tickLoop();
			timeForOneTick = System.nanoTime() - currentTime;
			tempAverageTimeForOneTick += timeForOneTick;
			tmpAccCounter++;
		}
		// A normal tick, called when the loop has not reached it's max value of ticks
		// Done ticksPerSecond times per second
	}
	
	
	private void tickNormally(long currentTime) {
		if (tickCounter < alottedTicskPerSecond) {
			tickWithoutRest(currentTime);
			try {
				if (tickSleepTime > 1000000) {
					Thread.sleep((long) (tickSleepTime / 1000000));
				} else {
					Thread.sleep(0, (int) tickSleepTime);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Called via the looping thread (thread 0)<br>
	 * Used to deal with inputs from the user, repaint the panel, and determine FPS
	 * <p>
	 * Must-have functions:<br>
	 * - GamePanel.repaint<br>
	 * - GamePanel.lowLevelUpdates<br>
	 * - GamePanel.requestFocus<br>
	 */
	protected abstract void gameLoop();
	
	
	/**
	 * Determines the current FPS.<br>
	 * Should be called by the engine during the game loop phase
	 */
	protected final void determineFPS() {
		if (gameTimeSeconds >= 1) {
			if (tempCount < gameTimeSeconds) {
				tempCount = (int) gameTimeSeconds;
				currentFps = fpsCounter;
				fpsCCounter++;
				fpsKeeper += fpsCounter;
				averageFps = fpsKeeper / fpsCCounter;
				fpsCounter = 0;
			}
		}
	}
	
	
	
	/**
	 * Called via the ticking thread (Thread 0)<br>
	 * The loop that's called {@link #ticksPerSecond} times per second<br>
	 * Separate from GameLoop and will (usually) be called regardless<br>
	 * <p>
	 * 
	 * Should call<br>
	 * GamePanel.tick()
	 */
	protected abstract void tickLoop();
	
	
	/**
	 * Like the {@link #tickLoop()} but only called once per second.<br>
	 * Still called with the ticking thread (Thread 0)<br>
	 * Should call panel.tock
	 */
	protected abstract void tockLoop();
	
	
	/**
	 * A loop that only gets called if more than two threads are created.<br>
	 * Used to create your own game loop free from the other two.<br>
	 * Could allow for multi-core rendering
	 */
	protected abstract void otherLoop();
	
	
	public boolean isGameRunning() {
		return gameRunning;
	}
	
	
	
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}
	
	
	
	public boolean isLooping() {
		return looping;
	}
	
	
	
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	
	
	public boolean isDebugging() {
		return debugging;
	}
	
	
	
	public void setDebugging(boolean debugging) {
		this.debugging = debugging;
	}
	
	
	
	public static String getEngineversion() {
		return engineVersion;
	}
	
	
	
	public String getGameTitle() {
		return gameTitle;
	}
	
	
	
	public String getGameVersion() {
		return gameVersion;
	}
	
	
	
	public JFrame getFrame() {
		return frame;
	}
	
	
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	
	
	public Thread[] getThreads() {
		return threads;
	}
	
	
	
	public int getNumThreads() {
		return numThreads;
	}
	
	
	
	public boolean isTicking() {
		return ticking;
	}
	
	
	
	public long getGameTimeSeconds() {
		return gameTimeSeconds;
	}
	
	
	
	public int getFpsCounter() {
		return fpsCounter;
	}
	
	
	
	public int getCurrentFps() {
		return currentFps;
	}
	
	
	
	public int getTicksPerSecond() {
		return ticksPerSecond;
	}
	
	
	protected abstract AbstractRcsglGamePanel getGamePanel();
	
	
	
}
