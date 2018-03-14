package com.eastwesterntribe.rcs.RcsGamingLibrary;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;


/**
 * This class is the panel that contains all things painted to the screen.<br>
 * Being an abstract class, you'll have to extend it in order for it to work.<br>
 * It works closely with the RCSGL engine to bring the game to life
 * 
 * @author RCS
 * 
 */
public abstract class AbstractRcsglGamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {
	
	/**
	 * Using last date it was changed for the version id
	 */
	private static final long		serialVersionUID	= 02112016L;
	
	
	/**
	 * Mouse location on screen
	 */
	protected int					mouseX, mouseY;
	
	public RcsglStringApplications	stringApplication;
	
	
	/**
	 * Calls Super(), {@link #init()}, then {@link #initialize()}
	 */
	public AbstractRcsglGamePanel(AbstractRcsglEngine e) {
		super();
		init();
		initialize(e);
	}
	
	
	/**
	 * Initializes default variables<br>
	 * <br>
	 * Order:<br>
	 * - {@link #setDefaultKeys()}<br>
	 * - create a new {@link #stringApplication}
	 */
	private void init() {
		setDefaultKeys();
		stringApplication = new RcsglStringApplications();
	}
	
	
	/**
	 * Create a new RcsglKeyBinder class and store it as an instance variable<br>
	 * Called by {@link #init()}
	 */
	protected abstract void setDefaultKeys();
	
	
	/**
	 * For initialization of all other variables.<br>
	 * Called after {@link #init()}
	 */
	protected abstract void initialize(AbstractRcsglEngine e);
	
	
	
	/**
	 * Called by {@link #lowLevelUpdates()}<br>
	 * Should be used to give mouse position to any windows that need to detect it
	 * <p>
	 * 
	 * Requires numerous if() statements
	 * <p>
	 * checks if keys are pressed or not and tells the game what to do if they are
	 * <p>
	 * Typically there are nested if statements that further differentiate what to do based on what
	 * the game is currently showing on the screen
	 * <p>
	 * When looking for a keyPress, use {@link #getKeyBindingData()}.getPressed(KeyBindings b)
	 * 
	 */
	protected abstract void lowLevelEvents();
	
	
	/**
	 * Checks for mouse location, <br>
	 * calls {@link #lowLevelEvents()}
	 */
	public final void lowLevelUpdates() {
		// Checks for mouse and keyboard inputs
		// Called by engine
		mouseX = MouseInfo.getPointerInfo().getLocation().x;
		mouseY = MouseInfo.getPointerInfo().getLocation().y;
		lowLevelEvents();
	}
	
	
	/**
	 * Called from {@link #repaint()} method<br>
	 * it's what paints the screen.
	 * <p>
	 * calls {@link #screenPainter(Graphics2D g)} and turns the Graphics gr into a Graphics2D g
	 * function
	 * 
	 * @param gr
	 *            - Graphics instance
	 */
	public final void paintComponent(Graphics gr) {
		// Repaints the panel
		super.paintComponent(gr);
		// Gets a graphics2d object from graphics
		Graphics2D g = (Graphics2D) gr;
		screenPainter(g);
	}
	
	
	/**
	 * Called in paintComponent<br>
	 * Where all the game painting is done.<br>
	 * use the Graphics2D functions to draw what you need to<br>
	 * It is advised to use booleans to determine which parts of the game the program is at<br>
	 * and to help it decide what to paint.
	 * <p>
	 * Remember, the order the code is called is the order it's drawn to the screen
	 * 
	 * @param g
	 *            - The instance of Graphics2D
	 */
	protected abstract void screenPainter(Graphics2D g);
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Define what to do every single tick. Called by engine
	 */
	protected abstract void tick();
	
	
	/**
	 * Define what to do during the 1 tick per second (tock)
	 */
	protected abstract void tock();
	
	
	/**
	 * Print to the console
	 * 
	 * @param s
	 */
	protected final void printf(String s) {
		System.out.println(s);
	}
	
	
	// Input Methods Below/////////////////////////////////////////////////
	public abstract void mouseDragged(MouseEvent e);
	
	
	public abstract void mouseMoved(MouseEvent e);
	
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	
	/**
	 * Set mouse buttons that are pressed to true
	 */
	public abstract void mousePressed(MouseEvent e);
	
	
	/**
	 * Set mouse buttons that are not pressed to false
	 */
	public abstract void mouseReleased(MouseEvent e);
	
	
	/**
	 * Call KeyBindingData.setPressed(keyCode, true);
	 */
	public abstract void keyPressed(KeyEvent e);
	
	
	/**
	 * Call KeyBindingData.setPressed(keyCode, false);
	 */
	public abstract void keyReleased(KeyEvent e);
	
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	protected abstract AbstractRcsglKeyBinder getRcsglKeyBinder();
	
	
	protected int getMouseX() {
		return mouseX;
	}
	
	
	protected void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	
	
	protected int getMouseY() {
		return mouseY;
	}
	
	
	protected void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	
	protected static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	protected RcsglStringApplications getStringApplication() {
		return stringApplication;
	}
	
}
