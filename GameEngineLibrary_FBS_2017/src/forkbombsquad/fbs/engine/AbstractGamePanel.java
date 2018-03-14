package forkbombsquad.fbs.engine;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import forkbombsquad.fbs.utilities.input_binding.InputHolder;


/**
 * This class is the panel that contains all things painted to the screen and probably most of your
 * game logic.<br>
 * Being an abstract class, you'll have to extend it in order for it to work.<br>
 * It works closely with the AbstractEngine, which it requires to work
 * 
 * @author ForkBombSquad
 * 
 */
public abstract class AbstractGamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {
	
	/**
	 * Using last date it was changed for the version id
	 */
	private static final long	serialVersionUID	= 12182017L;
	
	
	/**
	 * Mouse location on screen
	 */
	protected int				mouseX, mouseY;
	
	/**
	 * Stores key and mouse button information
	 */
	protected InputHolder		input;
	
	
	/**
	 * The AbstractEngine should be passed to this GamePanel<br>
	 * Calls Super(), {@link #init()}, then {@link #initialize()}
	 */
	public AbstractGamePanel(AbstractEngine e) {
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
		createInputHolder();
		setDefaultInputs();
	}
	
	
	/**
	 * Create a new inputHolder class and store it as an instance variable<br>
	 * Called by {@link #init()}
	 */
	protected void createInputHolder() {
		input = new InputHolder();
	}
	
	
	/**
	 * Used to set the default bindings you'd like for your game<br>
	 * 
	 * Called by {@link #init()}
	 */
	protected abstract void setDefaultInputs();
	
	
	/**
	 * For initialization of all other variables.<br>
	 * Called after {@link #init()}
	 */
	protected abstract void initialize(AbstractEngine e);
	
	
	/**
	 * Called by {@link #lowLevelUpdates()}<br>
	 * Should be used to deal with mouse and keyboard inputs.
	 */
	protected abstract void lowLevelEvents();
	
	
	/**
	 * stores mouse location, <br>
	 * calls {@link #lowLevelEvents()}
	 */
	public final void lowLevelUpdates() {
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
	 * <p>
	 * Remember, the order the code is called is the order it's drawn to the screen
	 * 
	 * @param g
	 *            - The instance of Graphics2D
	 */
	protected abstract void screenPainter(Graphics2D g);
	
	
	/**
	 * Define what to do every single tick. Called by engine
	 */
	protected abstract void tick();
	
	
	/**
	 * Define what to do during the 1 tick per second (tock)
	 */
	protected abstract void tock();
	
	
	/**
	 * Called whenever one of the other loops runs.
	 */
	protected abstract void otherLoop(int currentThread);
	
	
	// Input Methods Below/////////////////////////////////////////////////
	public void mouseDragged(MouseEvent e) {
		
	}
	
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		input.setMousePressed(e.getButton(), true);
	}
	
	
	public void mouseReleased(MouseEvent e) {
		input.setMousePressed(e.getButton(), false);
	}
	
	
	public void keyPressed(KeyEvent e) {
		input.setPressed(e.getKeyCode(), true);
	}
	
	
	public void keyReleased(KeyEvent e) {
		input.setPressed(e.getKeyCode(), false);
	}
	
}
