package forkbombsquad.fbs.engine;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import forkbombsquad.fbs.utilities.math.Function;


/**
 * FBS Abstract Engine<br>
 * Version Indev 0.0001
 * <p>
 * 
 * This class should be extended by the core of your game.<br>
 * It runs all of the heavy lifting stuff.<br>
 * If you're unsure what to do with some of the values, try using the default constructor.<br>
 * <br>
 * This is where the basic engine logic is performed, but game logic should not be performed here.
 * <p>
 * The game threads and ticks begin here...<br>
 * although, again, most of their work is done the methods called in the game panel
 * <p>
 * After Engine is initialized, the game will not start until engine.start() is called.
 * 
 * @author ForkBombSquad
 */
public abstract class AbstractEngine implements Runnable {
	
	/**
	 * The version of FBS Abstract Engine Library you're currently running. <br>
	 * Typically labeled as Indev 0.00xx, Alpha 0.0xx, Beta 0.xx, or Release x.x
	 */
	public static final String	engineVersion			= "Indev 0.0001";
	
	
	/**
	 * The title given to your game.<br>
	 * You set this value in the engine's constructor.
	 */
	public String				gameTitle;
	
	/**
	 * The version your game is currently in. <br>
	 * Typically labeled as Indev 0.00xx, Alpha 0.0xx, Beta 0.xx, or Release x.x <br>
	 * You set this value in the engine's constructor.
	 */
	public String				gameVersion;
	
	
	
	/**
	 * The outer shell that holds the game panel. AKA the base for the game.
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
	public boolean				gameRunning, ticking, looping, otherThreads;
	
	/**
	 * Limits how many ticks are allowed to occur per second
	 * <p>
	 * Defaulted to 20
	 * 
	 * @see #tickSleepTime
	 */
	public double				alottedTicskPerSecond	= 20;
	
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
	
	public float				deltaTime				= 0f;
	protected long				lastTime				= 0L;
	
	
	/**
	 * The easy constructor for the FBS Engine.<br>
	 * <i>-note-</i> the engine does not start running until {@link #start() start()} is called<br>
	 * Calls {@link #init()}
	 * 
	 * @param gameTitle
	 *            - String passed in representing the title of the current game <br>
	 *            Saved as an instance variable
	 * @param gameVersion
	 *            - String passed in representing the version of the current game<br>
	 *            Saved as an instance variable
	 * 
	 */
	public AbstractEngine(String gameTitle, String gameVersion, boolean debugMode) {
		this.gameTitle = gameTitle;
		this.gameVersion = gameVersion;
		this.alottedTicskPerSecond = 20.0;
		numThreads = 2;
		tickSleepTime = 25;
		this.debugging = debugMode;
		init();
	}
	
	
	/**
	 * The normal constructor for the FBS Engine.<br>
	 * <i>-note-</i> the engine does not start running until {@link #start() start()} is called<br>
	 * Calls {@link #init()}
	 * 
	 * @param gameTitle
	 *            - String passed in representing the title of the current game <br>
	 *            Saved as an instance variable
	 * @param gameVersion
	 *            - String passed in representing the version of the current game<br>
	 *            Saved as an instance variable
	 * @param ticksPerSecond
	 *            - Limits how many ticks are allowed to occur per second<br>
	 *            Default is 20
	 * @param debugMode
	 *            - Boolean determining whether or not to run the game in debug mode
	 * 
	 */
	public AbstractEngine(String gameTitle, String gameVersion, double ticksPerSecond, boolean debugMode) {
		this.gameTitle = gameTitle;
		this.gameVersion = gameVersion;
		this.alottedTicskPerSecond = ticksPerSecond;
		numThreads = 2;
		tickSleepTime = 25;
		this.debugging = debugMode;
		init();
	}
	
	
	/**
	 * The complex constructor for the FBS Engine.<br>
	 * <i>-note-</i> the engine does not start running until {@link #start() start()} is called<br>
	 * Calls {@link #init()}
	 * 
	 * @param gameTitle
	 *            - String passed in representing the title of the current game <br>
	 *            Saved as an instance variable
	 * @param gameVersion
	 *            - String passed in representing the version of the current game<br>
	 *            Saved as an instance variable
	 * @param ticksPerSecond
	 *            - Limits how many ticks are allowed to occur per second<br>
	 *            Default is 20
	 * @param numThreads
	 *            - Determines the number of game threads to run.<br>
	 *            Default is 2, minimum is also 2.
	 * @param tickSleepTime
	 *            - Determines how long ticks sleep before checking if they can tick again.<br>
	 *            Default is 25.0 milliseconds
	 * @param debugMode
	 *            - Boolean determining whether or not to run the game in debug mode
	 * 
	 */
	public AbstractEngine(String gameTitle, String gameVersion, double ticksPerSecond, int numThreads, double tickSleepTime, boolean debugMode) {
		this.gameTitle = gameTitle;
		this.gameVersion = gameVersion;
		this.alottedTicskPerSecond = ticksPerSecond;
		if (numThreads < 2) {
			this.numThreads = 2;
		} else {
			this.numThreads = numThreads;
		}
		this.tickSleepTime = tickSleepTime;
		this.debugging = debugMode;
		init();
	}
	
	
	/**
	 * Initializes the engine variables. Those variables are:<br>
	 * screenWidth, screenHeight, frame, gameRunning, looping, ticking, otherThreads, threads,<br>
	 * and all variables dealing with tick and FPS timing.
	 * <p>
	 * ORDER OF OPERATION:<br>
	 * - Call {@link #initFirst()}<br>
	 * - Set the screenWidth and Height<br>
	 * - Create the JFrame and set its default look and operations.<br>
	 * - Call {@link #initBeforePanel()} <br>
	 * - Call {@link #initPanel()} <br>
	 * - Call {@link #initAfterPanel()} <br>
	 * - Make the frame visible and revalidate it.<br>
	 * - Set looping booleans to true and create threads<br>
	 * - Set timing and ticking variables to zero. <br>
	 * - Call {@link #finalInit()}
	 */
	protected final void init() {
		initFirst();
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
		
		initAfterPanel();
		
		// makes the frame visible and refreshes it, painting it to the screen for the first time
		frame.setVisible(true);
		frame.revalidate();
		
		// Sets looping booleans to true, allowing the loops to run
		gameRunning = true;
		looping = true;
		ticking = true;
		otherThreads = true;
		
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
		finalInit();
	}
	
	
	/**
	 * A method called before any initialization logic
	 */
	protected abstract void initFirst();
	
	
	/**
	 * A method called after the frame is created but before the game panel.
	 */
	protected abstract void initBeforePanel();
	
	
	/**
	 * Initializes the inner parts of the frame, including the {@link #GamePanel}
	 * <p>
	 * It should:<br>
	 * - Create a new panel and store it as an instance variable<br>
	 * - Set panel to focusable = true<br>
	 * - Add mouseListener(panel), mouseMotionListener(panel), mouseWheelListener(panel), and
	 * keyListener(panel)<br>
	 * - Set background color to an initial color<br>
	 * - Set the content panel of the frame to be GamePanel<br>
	 * - call frame.revalidate();
	 */
	protected abstract void initPanel();
	
	
	/**
	 * A method called after the frame and panel are created but before the frame is visible.
	 */
	protected abstract void initAfterPanel();
	
	
	/**
	 * A method called after all base engine initialization logic is completed.<br>
	 * Unless you have a good reason to do otherwise, your init logic should go here.
	 */
	protected abstract void finalInit();
	
	
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
	
	
	private final void startThreads() {
		// Starts the game threads contained in the Thread array
		// Defaulted to the Run method of this class
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	
	/**
	 * For starting everything that needs to begin after the threads have started looping
	 */
	protected abstract void startEverythingElse();
	
	
	
	
	
	
	/**
	 * Method called every loop when debugging is enabled.
	 */
	protected abstract void debug();
	
	
	/**
	 * This is where all engine threads start
	 */
	public final void run() {
		runEngine();
	}
	
	
	protected final void runEngine() {
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
				if (debugging) {
					debug();
				}
				if (looping) {
					
					gameLoop();
					fpsCounter++;
					this.determineFPS();
				}
				// Thread 1 is the ticking thread.
			} else if (Thread.currentThread().getId() == threads[1].getId()) {
				// if ticking is turned on, do ticking operations
				if (ticking) {
					this.determineDeltaTtime(System.nanoTime());
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
				// Thread is not 0 or 1
				if (otherThreads) {
					int threadnum = 0;
					for (int i = 0; i < threads.length; i++) {
						if (Thread.currentThread().getId() == threads[i].getId()) {
							threadnum = i;
						}
					}
					otherLoop(threadnum);
				}
			}// if other thread
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
		tock();
		
		// prints info to console if debug printing is allowed
		
		// Still does tick operations because it counts as a tick
		tick();
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
			tick();
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
	 * - GamePanel.repaint()<br>
	 * - GamePanel.lowLevelUpdates()<br>
	 * - GamePanel.requestFocus()<br>
	 */
	protected abstract void gameLoop();
	
	
	/**
	 * Determines the current FPS.<br>
	 * Called by the engine during the game loop phase
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
	 * Determines the current change in time since the last frame. <br>
	 * Called by the engine during the loop phase.
	 */
	protected final void determineDeltaTtime(long timeFromThisTick) {
		deltaTime = Function.max(Function.min((float) ((timeFromThisTick - lastTime) / 1000000000.0), 0.05f), 0.05f);
		lastTime = System.nanoTime();
	}
	
	
	/**
	 * Called via the ticking thread (Thread 1)<br>
	 * The loop that's called {@link #ticksPerSecond} times per second<br>
	 * Separate from GameLoop and will (usually) be called regardless<br>
	 * <p>
	 * 
	 * MUST HAVE METHODS:<br>
	 * GamePanel.tick()
	 */
	protected abstract void tick();
	
	
	/**
	 * Similar to the {@link #tickLoop()} but only called once per second.<br>
	 * Still called with the ticking thread (Thread 1)<br>
	 * MUST HAVE METHODS:<br>
	 * GamePanel.tock()
	 */
	protected abstract void tock();
	
	
	/**
	 * A loop that only gets called if more than two threads are created.<br>
	 * Used to create your own game loop free from the other two.<br>
	 * Could allow for multi-core rendering or other useful things.<br>
	 * <br>
	 * 
	 * MUST HAVE METHODS:<br>
	 * GamePanel.otherLoop()
	 */
	protected abstract void otherLoop(int currentThread);
	
	
}
