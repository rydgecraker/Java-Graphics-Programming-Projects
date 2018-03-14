package com.eastwesterntribe.rcs.RcsGameCreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglEngine;
import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglGamePanel;
import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglKeyBinder;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglDropDownMenu;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglDropDownOption;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglDropDownOptionWithSubmenu;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglMenuBar;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglMenuBarOption;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglStringWithCoordinates;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglTextBox;


/**
 * This class Runs the Game...
 * <p>
 * - Navigation tip: Use Ctrl+O to jump to sections through search.
 * <p>
 * 
 * Table of Contents:
 * <p>
 * 1) Instance Variables<br>
 * 
 * 2) Constructor, Initialize, and Set Default Key Bindings<br>
 * 
 * 3) Init Methods<br>
 * 
 * 4) Low Level Events<br>
 * 
 * 5) Low Level Event Methods<br>
 * 
 * 6) Screen Painter<br>
 * 
 * 7) Drawing Methods<br>
 * 
 * 8) Tick and Tock Loops<br>
 * 
 * 9) Tick Operation Methods<br>
 * 
 * 10) Tock Operation Methods<br>
 * 
 * 11) Mouse Events<br>
 * 
 * 12) Key Events<br>
 * 
 * 13 Helper Methods, Getters, and Setters<br>
 * 
 * @author Rydge
 * 
 */
public class GameCreatorPanel extends AbstractRcsglGamePanel {
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 1) Instance Variables
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * * Using last date it was changed for the version id
	 */
	private static final long		serialVersionUID	= 02112016L;
	
	
	//
	//Engine
	//
	protected GameCreatorEngine		engine;
	
	//
	//Key Bindings
	//
	protected GameCreatorKeyBinder	keyBindings;
	
	//
	//Panel Variables
	//
	protected int					volumeChangeTickAllowanceCounter, volumeCurrentPercent;
	protected double				volumeCurrentValue;
	protected boolean				playIntroMusic, needToRecalibrateStrings;
	
	//
	//Input Variables
	//
	protected boolean				alterationKeyPressed, alteration2KeyPressed, alteration3KeyPressed, leftMouseButton, middleMousebutton, rightMouseButton,
			lmbDragged, rmbDragged, mmbDragged;
	protected int					mouseScrollValue, mouseScrollDirection, lmbClickLocationX, lmbClickLocationY, lmbReleaseLocationX, lmbReleaseLocationY,
			rmbClickLocationX, rmbClickLocationY, rmbReleaseLocationX, rmbReleaseLocationY, mmbClickLocationX, mmbClickLocationY, mmbReleaseLocationX,
			mmbReleaseLocationY;
	protected String				lastMouseScrollDirection;
	
	//
	//Decimal Formatters
	//
	protected DecimalFormat			formatStyleNumberWithCommas;
	protected DecimalFormat			formatStyleTenDecimalPlaces;
	protected DecimalFormat			formatStyleSixDecimalPlaces;
	protected DecimalFormat			formatStyleRoundToNearestInt;
	
	
	//
	//Debugging
	//
	protected Color					debuggingFontColor;
	protected Color[]				debuggingFontColors;
	protected int					debugSpacing, debugMenuLocationX, debugMenuLocationY, debugMenuPreviousSize;
	protected Font					debuggingFont;
	protected boolean[]				debugging;
	protected String[]				debugMenuArryStrings;
	protected double				debugAvgTickTimeSeconds;
	protected String				printThisStringToTheDebugMenu;
	
	//
	//Startup
	//
	protected int					startupCounter, startupTimeLimit, startupSeperation, introMusicTickCounter, startupQuickCounter;
	protected float					fadeSpeed;
	protected RcsglStringWithCoordinates	startupText1, startupText2, startupText3;
	protected boolean						showStartupScreen;
	protected Color							startupTextColor1, startupTextColor2, startupTextColor3, tempStartupTextColor1, tempStartupTextColor2,
			tempStartupTextColor3, startupBackgroundColor;
	protected Font							startupTextFont1, startupTextFont2, startupTextFont3;
	
	//
	//EditorScreen
	//
	protected RcsglMenuBar					editorScreenTitleBar;
	protected Color							editorScreenBackgroundColor;
	protected String						lastClickedEditorScreenTitleBarString;
	protected boolean						showEditorScreen, closeEditorScreenTitleBarMenus;
	//Project Screen (Inside Editor Screen)
	protected boolean						currentlyOpenProject;
	protected RcsglStringWithCoordinates	noOpenProjects;
	protected Font							noOpenProjectsFont;
	protected Color							noOpenProjectsFontColor;
	//Project (Inside Editor Screen)
	protected GameCreatorProject			currentProject;
	protected RcsglTextBox					textBox;
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 2) Constructor, Initialize, and Set Default Key Bindings
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public GameCreatorPanel(GameCreatorEngine engine) {
		super(engine);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setDefaultKeys(){
		keyBindings = new GameCreatorKeyBinder();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize(AbstractRcsglEngine e){
		engine = (GameCreatorEngine) e;
		initDecimalFormatters();
		initPanelVariables();
		initInputVariables();
		intiDebuggingStuff();
		initStartupScreenStuff();
		initEditorScreenStuff();
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 3) Init Methods
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void initDecimalFormatters(){
		formatStyleNumberWithCommas = new DecimalFormat("#,###");
		
		formatStyleTenDecimalPlaces = new DecimalFormat("#");
		formatStyleTenDecimalPlaces.setMaximumFractionDigits(10);
		
		formatStyleSixDecimalPlaces = new DecimalFormat("#");
		formatStyleSixDecimalPlaces.setMaximumFractionDigits(6);
		
		formatStyleRoundToNearestInt = new DecimalFormat("#");
		formatStyleRoundToNearestInt.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	private void initPanelVariables(){
		volumeChangeTickAllowanceCounter = 0;
		volumeCurrentPercent = engine.sounds.getVolumePercentage(formatStyleRoundToNearestInt);
		volumeCurrentValue = engine.sounds.getVolume();
		playIntroMusic = true;
		needToRecalibrateStrings = true;
	}
	
	private void initInputVariables(){
		alterationKeyPressed = false;
		alteration2KeyPressed = false;
		alteration3KeyPressed = false;
		lmbDragged = false;
		rmbDragged = false;
		mmbDragged = false;
		lmbClickLocationX = 0;
		lmbClickLocationY = 0;
		lmbReleaseLocationX = 0;
		lmbReleaseLocationY = 0;
		rmbClickLocationX = 0;
		rmbClickLocationY = 0;
		rmbReleaseLocationX = 0;
		rmbReleaseLocationY = 0;
		mmbClickLocationX = 0;
		mmbClickLocationY = 0;
		mmbReleaseLocationX = 0;
		mmbReleaseLocationY = 0;
		leftMouseButton = false;
		rightMouseButton = false;
		middleMousebutton = false;
		mouseScrollValue = 0;
		mouseScrollDirection = 0;
		lastMouseScrollDirection = "None";
	}
	
	private void intiDebuggingStuff(){
		debugging = new boolean[] { false, false, false, false, false };
		debuggingFont = scaleFont(new Font("arial unicode ms", Font.BOLD, 20));
		debuggingFontColor = Color.WHITE;
		debugSpacing = 2;
		lastClickedEditorScreenTitleBarString = "";
		debugMenuArryStrings = new String[] { " " };
		debuggingFontColors = new Color[] { debuggingFontColor };
		debugMenuLocationX = 0;
		debugMenuLocationY = 0;
		debugMenuPreviousSize = 0;
		printThisStringToTheDebugMenu = "";
	}
	
	private void initStartupScreenStuff(){
		introMusicTickCounter = 0;
		startupQuickCounter = 0;
		showStartupScreen = true;
		startupCounter = 0;
		startupTimeLimit = 5;
		startupSeperation = 5;
		
		fadeSpeed = 500f; // The larger the number, the slower the fade
		
		startupText1 = new RcsglStringWithCoordinates(engine.getGameTitle() + " (Version: " + engine.getGameVersion() + ")");
		startupText2 = new RcsglStringWithCoordinates("Running on RCSGL version: " + AbstractRcsglEngine.getEngineversion());
		if(((char) keyBindings.getKeyBinding("JUMP")) == ' ') {
			startupText3 = new RcsglStringWithCoordinates("Press SPACE!");
		} else {
			startupText3 = new RcsglStringWithCoordinates("Press " + ((char) keyBindings.getKeyBinding("JUMP")) + "!");
		}
		startupTextColor1 = Color.RED;
		startupTextColor2 = Color.ORANGE;
		startupTextColor3 = Color.MAGENTA;
		tempStartupTextColor1 = Color.RED;
		tempStartupTextColor2 = Color.ORANGE;
		tempStartupTextColor3 = Color.MAGENTA;
		startupTextFont1 = scaleFont(new Font("arial unicode ms", Font.BOLD, 70));
		startupTextFont2 = scaleFont(new Font("arial unicode ms", Font.BOLD, 40));
		startupTextFont3 = scaleFont(new Font("arial unicode ms", Font.BOLD, 80));
		startupBackgroundColor = Color.BLACK;
		setBackground(startupBackgroundColor);
	}
	
	private void initEditorScreenStuff(){
		editorScreenBackgroundColor = Color.DARK_GRAY;
		
		String menuName = "Titlebar";
		
		String[] optionNames = new String[] { "File", "Edit", "Resources", "Run", "Help" };
		
		Boolean[] numsWithSubMenus = new Boolean[] { true, true, true, false, true };
		
		String[][] ddOptionNamesWithSubs = new String[][] { { "New" }, { "Debugging" }, { "Import", "Export" }, { "Help Files" } };
		
		String[][][] ddOptionSubNames = new String[][][] { { { "Project", "Topview Map", "Sideview Map", "Topview ProcGen Map" } },
				{ { "Enable All", "Enable Debug Screen", "Enable Engine Stats", "Enable Game Stats", "Enable Input Feedback", "Enable Active Screens" } },
				{ { "Sounds", "Images", "Other Files" }, { "Game Files", "Sounds", "Images" } },
				{ { "Basics", "Procedural Generation", "Exporting", "Playing" } } };
		
		String[][] ddOptionNames = new String[][] { { "Open", "Save", "Save As...", "Export As...", "Quit" },
				{ "Undo", "Redo", "Cut", "Copy", "Paste", "Game Options", "Enable Engine Debugging", "Preferences" }, { "View Resources" },
				{ "Run Game", "Run Tool..." }, { "Tips and tricks", "Version Information" } };
		
		
		editorScreenTitleBar = createDefaultMenuBarWithSubmenus(menuName, optionNames, ddOptionNamesWithSubs, ddOptionNames, ddOptionSubNames, numsWithSubMenus);
		editorScreenTitleBar.disableByName("save");
		editorScreenTitleBar.disableByName("save as...");
		editorScreenTitleBar.disableByName("export as...");
		editorScreenTitleBar.disableByName("undo");
		editorScreenTitleBar.disableByName("redo");
		editorScreenTitleBar.disableByName("paste");
		editorScreenTitleBar.disableByName("topview map");
		editorScreenTitleBar.disableByName("sideview map");
		editorScreenTitleBar.disableByName("topview procgen map");
		editorScreenTitleBar.disableByName("import");
		editorScreenTitleBar.disableByName("export");
		editorScreenTitleBar.disableByName("view resources");
		editorScreenTitleBar.disableByName("game options");
		editorScreenTitleBar.disableByName("cut");
		editorScreenTitleBar.disableByName("copy");
		editorScreenTitleBar.disableByName("run game");
		
		
		initProjectScreen();
	}
	
	private void initProjectScreen(){
		currentlyOpenProject = false;
		noOpenProjects = new RcsglStringWithCoordinates("No Open Projects");
		noOpenProjectsFont = scaleFont(new Font("arial unicode ms", Font.BOLD, 40));
		noOpenProjectsFontColor = new Color(30, 30, 30);
		
		textBox = new RcsglTextBox(300, 300, 300, scaleFont(new Font("arial unicode ms", Font.BOLD, 30)), "Text Box Is Empty", Color.BLACK, Color.GRAY,
				Color.WHITE, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, 1);
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 4) Low Level Events
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void lowLevelEvents(){
		alterationKeyPressed = keyBindings.isPressed("ALTERATION");
		alteration2KeyPressed = keyBindings.isPressed("ALTERATION2");
		alteration3KeyPressed = keyBindings.isPressed("ALTERATION3");
		
		
		
		//
		//Calling update methods...
		//
		doUpdateOperations();
		
		
		//
		//Mouse Stuff
		//
		if(leftMouseButton) {
			doLMBOperations();
		}
		
		
		
		if(middleMousebutton) {
			doMMBOperations();
		}
		
		
		
		if(rightMouseButton) {
			doRMBOperations();
		}
		
		if(lmbDragged) {
			doLmbDraggedOperations();
		}
		
		if(rmbDragged) {
			doRmbDraggedOperations();
		}
		
		if(mmbDragged) {
			doMmbDraggedOperations();
		}
		
		
		
		//
		//Mouse Wheel Stuff
		//
		if(mouseScrollDirection > 0) {
			//Means scrolled DOWN!!! Toward user
			doMouseScrollDownOperations();
		}
		
		
		
		if(mouseScrollDirection < 0) {
			//Means scrolled UP!!!! Away from user
			doMouseScrollUpOperations();
		}
		
		
		
		//
		//Key Stuff
		//
		if(keyBindings.isPressed("ARROWUP")) {
			doArrowUpKeyOperations();
		}
		
		if(keyBindings.isPressed("ARROWDOWN")) {
			doArrowDownKeyOperations();
		}
		
		if(keyBindings.isPressed("ARROWLEFT")) {
			doArrowLeftKeyOperations();
		}
		
		if(keyBindings.isPressed("ARROWRIGHT")) {
			doArrowRightKeyOperations();
		}
		
		if(keyBindings.isPressed("DELETE")) {
			doDeleteKeyOperations();
		}
		
		if(keyBindings.isPressed("BACKSPACE")) {
			doBackspaceKeyOperations();
		}
		
		if(keyBindings.isPressed("FORWARD")) {
			doForwardKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("BACKWARD")) {
			doBackwardKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("LEFT")) {
			doLeftKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("RIGHT")) {
			doRightKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("ROTATELEFT")) {
			doRotateLeftKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("ROTATERIGHT")) {
			doRotateRightKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("QUIT")) {
			doQuitKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("JUMP")) {
			doJumpKeyOperations();
		}
		
		
		
		if(keyBindings.isPressed("DEBUG")) {
			doDebugKeyOperations();
		}
		
		
		if(keyBindings.isPressed("VOLUMEDOWN")) {
			doVolumeDownKeyOperations();
		}
		
		if(keyBindings.isPressed("VOLUMEUP")) {
			doVolumeUpKeyOperations();
		}
		
		
		
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 5) Low Level Event Methods
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void doUpdateOperations(){
		if(showEditorScreen) {
			doEditorScreenUpdates();
		}
	}
	
	private void doEditorScreenUpdates(){
		editorScreenTitleBar.update(mouseX, mouseY);
	}
	
	private void doLMBOperations(){
		if(showEditorScreen) {
			editorScreenTitleBar.click();
			leftMouseButton = false;
			textBox.click(mouseX, mouseY);
		}
	}
	
	private void doMMBOperations(){
		
	}
	
	private void doRMBOperations(){
		
	}
	
	private void doLmbDraggedOperations(){
		if(showEditorScreen) {
			textBox.dragMouse(lmbClickLocationX, lmbClickLocationY, mouseX);
		}
	}
	
	private void doRmbDraggedOperations(){
		
	}
	
	private void doMmbDraggedOperations(){
		
	}
	
	private void doMouseScrollDownOperations(){
		
		mouseScrollDirection = 0;
	}
	
	private void doMouseScrollUpOperations(){
		
		mouseScrollDirection = 0;
	}
	
	private void doArrowUpKeyOperations(){
		
	}
	
	private void doArrowDownKeyOperations(){
		
	}
	
	private void doArrowLeftKeyOperations(){
		if(showEditorScreen) {
			textBox.moveCursorPosition(true);
		}
	}
	
	private void doArrowRightKeyOperations(){
		if(showEditorScreen) {
			textBox.moveCursorPosition(false);
		}
	}
	
	private void doDeleteKeyOperations(){
		if(showEditorScreen) {
			textBox.delete();
		}
	}
	
	private void doBackspaceKeyOperations(){
		if(showEditorScreen) {
			textBox.backspace();
		}
	}
	
	private void doForwardKeyOperations(){
		
	}
	
	private void doBackwardKeyOperations(){
		
	}
	
	private void doLeftKeyOperations(){
		
	}
	
	private void doRightKeyOperations(){
		
	}
	
	private void doRotateLeftKeyOperations(){
		
	}
	
	private void doRotateRightKeyOperations(){
		
	}
	
	private void doJumpKeyOperations(){
		if(showStartupScreen) {
			startupCounter = startupTimeLimit;
		}
	}
	
	private void doDebugKeyOperations(){
		if(alterationKeyPressed) {
			if(engine.debugging) {
				engine.debugging = false;
			} else {
				engine.debugging = true;
			}
		} else {
			if(debugging[0]) {
				debugging[0] = false;
			} else {
				debugging[0] = true;
			}
		}
		keyBindings.setPressedByBinding("DEBUG", false);
	}
	
	private void doVolumeUpKeyOperations(){
		if(engine.sounds.getVolume() < 6 && volumeChangeTickAllowanceCounter > engine.alottedTicskPerSecond / 16) {
			engine.sounds.addVolume(.5f);
			volumeChangeTickAllowanceCounter = 0;
			volumeCurrentValue = engine.sounds.getVolume();
			volumeCurrentPercent = engine.sounds.getVolumePercentage(formatStyleRoundToNearestInt);
		}
	}
	
	private void doVolumeDownKeyOperations(){
		if(engine.sounds.getVolume() > -79 && volumeChangeTickAllowanceCounter > engine.alottedTicskPerSecond / 16) {
			engine.sounds.addVolume(-.5f);
			volumeChangeTickAllowanceCounter = 0;
			volumeCurrentValue = engine.sounds.getVolume();
			volumeCurrentPercent = engine.sounds.getVolumePercentage(formatStyleRoundToNearestInt);
		}
	}
	
	private void doQuitKeyOperations(){
		engine.sounds.stopAllClips();
		engine.gameRunning = false;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 6) Screen Painter
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void screenPainter(Graphics2D g){
		//
		//Sets String Locations For Everything
		//
		if(needToRecalibrateStrings) {
			recalibrateStrings(g);
		}
		
		
		//
		//Startup Screen
		//
		if(showStartupScreen) {
			drawStartupScreen(g);
		}
		
		
		//
		//Editor Screen
		//
		if(showEditorScreen) {
			drawEditorScreen(g);
		}
		
		
		//
		//Debugging Screen
		//
		if(debugging[0]) {
			drawDebugScreen(g);
		}
		
		
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 7) Drawing Methods
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void recalibrateStrings(Graphics2D g){
		needToRecalibrateStrings = false;
		
		g.setFont(startupTextFont1);
		Point p = stringApplication.centerString(startupText1.text, g, engine.screenWidth, engine.screenHeight);
		startupText1.x = (int) p.getX();
		startupText1.y = (int) p.getY();
		
		g.setFont(startupTextFont2);
		int sx = stringApplication.centerStringX(startupText2.text, g, engine.screenWidth, 0);
		int sy = stringApplication.getStringHeight(g) + startupSeperation + (int) p.getY();
		startupText2.x = sx;
		startupText2.y = sy;
		
		g.setFont(startupTextFont3);
		sx = stringApplication.centerStringX(startupText3.text, g, engine.screenWidth, 0);
		sy = stringApplication.getStringHeight(g) + startupSeperation + sy;
		startupText3.x = sx;
		startupText3.y = sy;
		
		g.setFont(debuggingFont);
		debugMenuLocationX = (int) (engine.screenHeight * .01) + 1;
		debugMenuLocationY = ((int) engine.screenHeight - (((debugMenuArryStrings.length) * stringApplication.getStringHeight(g)) + (debugSpacing * (debugMenuArryStrings.length + 1))));
		
		g.setFont(noOpenProjectsFont);
		noOpenProjects.x = stringApplication.centerStringX(noOpenProjects.text, g, engine.screenWidth, 0);
		noOpenProjects.y = (stringApplication.centerStringY(g, engine.screenHeight - editorScreenTitleBar.getHeight(), 0)) + editorScreenTitleBar.getHeight();
		
		if(currentlyOpenProject) {
			currentProject.recalibrateStrings(g, stringApplication, getProjectArea(), getProjectOffsetX(), getProjectOffsetY());
		}
		
		if(showEditorScreen) {
			textBox.setneedToUpdateConfig(true);
		}
	}
	
	private void drawStartupScreen(Graphics2D g){
		if(startupCounter < startupTimeLimit) {
			g.setFont(startupTextFont1);
			g.setColor(tempStartupTextColor1);
			g.drawString(startupText1.text, startupText1.x, startupText1.y);
			
			g.setFont(startupTextFont2);
			g.setColor(tempStartupTextColor2);
			g.drawString(startupText2.text, startupText2.x, startupText2.y);
			
			g.setFont(startupTextFont3);
			g.setColor(tempStartupTextColor3);
			g.drawString(startupText3.text, startupText3.x, startupText3.y);
			
		} else {
			showStartupScreen = false;
			showEditorScreen = true;
			engine.sounds.pauseClip("planb");
			setBackground(editorScreenBackgroundColor);
		}
	}
	
	private void drawEditorScreen(Graphics2D g){
		editorScreenTitleBar.draw(g);
		if(currentlyOpenProject) {
			drawCurrentProject(g);
		} else {
			g.setFont(noOpenProjectsFont);
			g.setColor(noOpenProjectsFontColor);
			g.drawString(noOpenProjects.text, noOpenProjects.x, noOpenProjects.y);
		}
		
		textBox.draw(g, stringApplication);
	}
	
	private void drawCurrentProject(Graphics2D g){
		currentProject.draw(g);
	}
	
	private void drawDebugScreen(Graphics2D g){
		g.setFont(debuggingFont);
		if(debugMenuArryStrings.length == debuggingFontColors.length && debugMenuArryStrings[0] != null) {
			stringApplication.alignAndDrawStringsVertically(debugMenuArryStrings, debuggingFontColors, debugMenuLocationX, debugMenuLocationY, debugSpacing, g);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 8) Tick and Tock Loops
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tick(){
		
		//
		//Intro Music
		//
		if(playIntroMusic && showStartupScreen) {
			introMusicOperations();
		}
		
		
		
		//
		//StartupScreenCalculations
		//
		if(showStartupScreen) {
			startupScreenCalculations();
		}
		
		if(debugging[0]) {
			debugMenuOperations();
		}
		
		//
		//Volume Stuff
		//
		volumeOperations();
		
		
		
		//
		//Main Editor Screen
		//
		if(showEditorScreen) {
			editorScreenOperations();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tock(){
		
		if(showEditorScreen) {
			if(textBox.isFlashing()) {
				textBox.setFlashing(false);
			} else {
				textBox.setFlashing(true);
			}
		}
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 9) Tick Operation Methods
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	private void introMusicOperations(){
		if(introMusicTickCounter < 1) {
			engine.sounds.loopClipContinously("planb", 0, engine.sounds.getClipLength("planb"));
			introMusicTickCounter++;
		}
	}
	
	private void startupScreenCalculations(){
		if(startupCounter < startupTimeLimit) {
			startupQuickCounter++;
			float tempAlpha = (float) (startupQuickCounter / fadeSpeed);
			if(tempAlpha > 1) {
				tempAlpha = 1;
			}
			float[] temCo = startupTextColor1.getRGBColorComponents(null);
			tempStartupTextColor1 = new Color(temCo[0], temCo[1], temCo[2], tempAlpha);
			
			temCo = startupTextColor2.getRGBColorComponents(null);
			tempStartupTextColor2 = new Color(temCo[0], temCo[1], temCo[2], tempAlpha);
			
			temCo = startupTextColor3.getRGBColorComponents(null);
			tempStartupTextColor3 = new Color(temCo[0], temCo[1], temCo[2], tempAlpha);
		}
	}
	
	private synchronized void debugMenuOperations(){
		debugAvgTickTimeSeconds = (double) (engine.averageTimeForOneTick) / 1000000000.0;
		ArrayList<String> debugMenuArrayListStrings = new ArrayList<String>();
		if(debugging[0]) { //Regular Debugging
			debugMenuArrayListStrings.add(" - BASIC DEBUGGING - ");
			debugMenuArrayListStrings.add(engine.gameTitle + " (version: " + engine.gameVersion + ")");
			debugMenuArrayListStrings.add("RCSGL Version: " + GameCreatorEngine.engineVersion);
			debugMenuArrayListStrings.add("Game Resolution " + engine.screenWidth + "x" + engine.screenHeight);
			debugMenuArrayListStrings.add("Printed String: \"" + printThisStringToTheDebugMenu + "\"");
			debugMenuArrayListStrings.add("");
		}
		if(debugging[1]) { //Engine Statistics
			debugMenuArrayListStrings.add(" - ENGINE STATISTICS - ");
			debugMenuArrayListStrings.add(formatStyleNumberWithCommas.format(engine.currentFps) + " FPS " + " - ("
					+ formatStyleNumberWithCommas.format(engine.averageFps) + " Average)");
			debugMenuArrayListStrings.add("Ticks Per Second: " + engine.ticksPerSecond + " out of " + engine.alottedTicskPerSecond
					+ " (Average time per tick = " + formatStyleTenDecimalPlaces.format(debugAvgTickTimeSeconds) + " seconds)");
			debugMenuArrayListStrings.add("");
			
		}
		if(debugging[2]) { //Game Statistics
			debugMenuArrayListStrings.add(" - GAME STATISTICS - ");
			debugMenuArrayListStrings.add("Game Time: " + engine.gameTimeSeconds + " seconds");
			debugMenuArrayListStrings.add("Volume: " + volumeCurrentPercent + "% (" + volumeCurrentValue + "db)");
			debugMenuArrayListStrings.add("Engine Debugging = " + engine.debugging);
			debugMenuArrayListStrings.add("Debugging Types: Normal=" + debugging[0] + " | EngineStats=" + debugging[1] + " | GameStats=" + debugging[2]
					+ " | Inputs=" + debugging[3] + " | Active Screens=" + debugging[4]);
			debugMenuArrayListStrings.add("");
		}
		if(debugging[3]) { //Input Feedback
			debugMenuArrayListStrings.add(" - INPUT FEEDBACK - ");
			debugMenuArrayListStrings.add("Mouse Location: " + mouseX + "x " + mouseY + "y");
			debugMenuArrayListStrings.add("Mouse Scroll: " + mouseScrollValue + " (Last Direction: " + lastMouseScrollDirection + ")");
			debugMenuArrayListStrings.add("Alt1 (" + keyBindings.getKeyName("ALTERATION") + ") = " + alterationKeyPressed + " --- Alt2 ("
					+ keyBindings.getKeyName("ALTERATION2") + ") =  " + alteration2KeyPressed + " --- Alt3 (" + keyBindings.getKeyName("ALTERATION3") + ") =  "
					+ alteration3KeyPressed);
			debugMenuArrayListStrings.add("Last Menu Option Chosen: " + lastClickedEditorScreenTitleBarString);
			debugMenuArrayListStrings.add("");
		}
		if(debugging[4]) { //Active Screens
			debugMenuArrayListStrings.add(" - ACTIVE SCREENS - ");
			debugMenuArrayListStrings.add("Startup Screen = " + showStartupScreen + " | Editor Screen = " + showEditorScreen);
		}
		
		
		debuggingFontColors = new Color[debugMenuArrayListStrings.size()];
		for (int i = 0; i < debuggingFontColors.length; i++) {
			debuggingFontColors[i] = debuggingFontColor;
		}
		
		debugMenuArryStrings = new String[debugMenuArrayListStrings.size()];
		for (int i = 0; i < debugMenuArrayListStrings.size(); i++) {
			debugMenuArryStrings[i] = debugMenuArrayListStrings.get(i);
		}
		
		
		if(debugMenuPreviousSize != debugMenuArryStrings.length) {
			debugMenuPreviousSize = debugMenuArryStrings.length;
			needToRecalibrateStrings = true;
		}
		
		
		
	}
	
	private void volumeOperations(){
		volumeChangeTickAllowanceCounter++;
		if(volumeChangeTickAllowanceCounter < 0) {
			volumeChangeTickAllowanceCounter = (int) engine.alottedTicskPerSecond / 2;
		}
	}
	
	private void editorScreenOperations(){
		if(editorScreenTitleBar.isPendingClick()) {
			editorScreenTitleBarOperations();
		}
	}
	
	private void editorScreenTitleBarOperations(){
		
		leftMouseButton = false;
		editorScreenTitleBar.setPendingClick(false);
		lastClickedEditorScreenTitleBarString = editorScreenTitleBar.getClickedString();
		closeEditorScreenTitleBarMenus = false;
		
		
		if(!closeEditorScreenTitleBarMenus) {
			editorScreenFileMenu();
		}
		
		if(!closeEditorScreenTitleBarMenus) {
			editorScreenEditMenu();
		}
		
		if(!closeEditorScreenTitleBarMenus) {
			editorScreenResourcesMenu();
		}
		
		if(!closeEditorScreenTitleBarMenus) {
			editorScreenRunMenu();
		}
		
		if(!closeEditorScreenTitleBarMenus) {
			editorScreenHelpMenu();
		}
		
		//
		
		if(closeEditorScreenTitleBarMenus) {
			editorScreenTitleBar.closeMenus();
		}
		
	}
	
	
	//
	//File
	//
	private void editorScreenFileMenu(){
		if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - NEW - PROJECT")) {
			
			if(currentlyOpenProject) {
				askToSaveDialog();
			}
			currentProject = newProject();
			currentlyOpenProject = true;
			
			editorScreenTitleBar.enableByName("topview map");
			editorScreenTitleBar.enableByName("sideview map");
			editorScreenTitleBar.enableByName("topview procgen map");
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - NEW - TOPVIEW MAP")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - NEW - SIDEVIEW MAP")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - NEW - TOPVIEW PROCGEN MAP")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - OPEN")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - SAVE")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - SAVE AS...")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - EXPORT AS...")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("FILE - QUIT")) {
			keyBindings.setPressedByBinding("QUIT", true);
			
			closeEditorScreenTitleBarMenus = true;
		}
	}
	
	private void askToSaveDialog(){
		
	}
	
	private GameCreatorProject newProject(){
		
		
		
		return new GameCreatorProject("Default Project", scaleFont(new Font("arial unicode ms", Font.BOLD, 30)), Color.BLACK);
	}
	
	
	//
	//EDIT
	//
	private void editorScreenEditMenu(){
		
		if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - ENABLE ALL")) {
			debugging = new boolean[] { true, true, true, true, true };
			
			editorScreenTitleBar.changeNameByName("enable all", "Disable All");
			editorScreenTitleBar.changeNameByName("enable debug screen", "Disable Debug Screen");
			editorScreenTitleBar.changeNameByName("enable engine stats", "Disable Engine Stats");
			editorScreenTitleBar.changeNameByName("enable game stats", "Disable Game Stats");
			editorScreenTitleBar.changeNameByName("enable input feedback", "Disable Input Feedback");
			editorScreenTitleBar.changeNameByName("enable active screens", "Disable Active Screens");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - ENABLE DEBUG SCREEN")) {
			
			debugging[0] = true;
			editorScreenTitleBar.changeNameByName("enable debug screen", "Disable Debug Screen");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - ENABLE ENGINE STATS")) {
			
			debugging[1] = true;
			editorScreenTitleBar.changeNameByName("enable engine stats", "Disable Engine Stats");
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - ENABLE GAME STATS")) {
			
			debugging[2] = true;
			editorScreenTitleBar.changeNameByName("enable game stats", "Disable Game Stats");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - ENABLE INPUT FEEDBACK")) {
			
			debugging[3] = true;
			editorScreenTitleBar.changeNameByName("enable input feedback", "Disable Input Feedback");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - ENABLE ACTIVE SCREENS")) {
			debugging[4] = true;
			editorScreenTitleBar.changeNameByName("enable active screens", "Disable Active Screens");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - DISABLE ALL")) {
			debugging = new boolean[] { false, false, false, false, false };
			
			
			editorScreenTitleBar.changeNameByName("disable all", "Enable All");
			editorScreenTitleBar.changeNameByName("disable debug screen", "Enable Debug Screen");
			editorScreenTitleBar.changeNameByName("disable engine stats", "Enable Engine Stats");
			editorScreenTitleBar.changeNameByName("disable game stats", "Enable Game Stats");
			editorScreenTitleBar.changeNameByName("disable input feedback", "Enable Input Feedback");
			editorScreenTitleBar.changeNameByName("disable active screens", "Enable Active Screens");
			
			closeEditorScreenTitleBarMenus = true;
			needToRecalibrateStrings = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - DISABLE DEBUG SCREEN")) {
			
			debugging[0] = false;
			editorScreenTitleBar.changeNameByName("disable debug screen", "Enable Debug Screen");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - DISABLE ENGINE STATS")) {
			
			debugging[1] = false;
			editorScreenTitleBar.changeNameByName("disable engine stats", "Enable Engine Stats");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - DISABLE GAME STATS")) {
			
			debugging[2] = false;
			editorScreenTitleBar.changeNameByName("disable game stats", "Enable Game Stats");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - DISABLE INPUT FEEDBACK")) {
			
			debugging[3] = false;
			editorScreenTitleBar.changeNameByName("disable input feedback", "Enable Input Feedback");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DEBUGGING - DISABLE ACTIVE SCREENS")) {
			
			debugging[4] = false;
			editorScreenTitleBar.changeNameByName("disable active screens", "Enable Active Screens");
			
			needToRecalibrateStrings = true;
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - UNDO")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - REDO")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - CUT")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - COPY")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - PASTE")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - ENABLE ENGINE DEBUGGING")) {
			engine.debugging = true;
			editorScreenTitleBar.options[1].dropDownMenu.options[6].setText("Disable Engine Debugging");
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - DISABLE ENGINE DEBUGGING")) {
			engine.debugging = false;
			editorScreenTitleBar.options[1].dropDownMenu.options[6].setText("Enable Engine Debugging");
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("EDIT - PREFERENCES")) {
			
			closeEditorScreenTitleBarMenus = true;
		}
	}
	
	//
	//RESOURCES
	//
	private void editorScreenResourcesMenu(){
		if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - IMPORT - SOUNDS")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - IMPORT - IMAGES")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - IMPORT - OTHER FILES")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - EXPORT - GAME FILES")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - EXPORT - SOUNDS")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - EXPORT - IMAGES")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RESOURCES - VIEW RESOURCES")) {
			
			closeEditorScreenTitleBarMenus = true;
		}
	}
	
	//
	//RUN
	//
	private void editorScreenRunMenu(){
		if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RUN - RUN GAME")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RUN - GAME OPTIONS")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("RUN - RUN TOOL...")) {
			
			closeEditorScreenTitleBarMenus = true;
		}
	}
	
	//
	//HELP
	//
	private void editorScreenHelpMenu(){
		if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("HELP - HELP FILES - BASICS")) {
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("HELP - HELP FILES - PROCEDURAL GENERATION")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("HELP - HELP FILES - EXPORTING")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("HELP - HELP FILES - PLAYING")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("HELP - TIPS AND TRICKS")) {
			
			closeEditorScreenTitleBarMenus = true;
		} else if(lastClickedEditorScreenTitleBarString.equalsIgnoreCase("HELP - VERSION INFORMATION")) {
			
			closeEditorScreenTitleBarMenus = true;
		}
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 10) Tock Operation Methods
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	//
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 11) Mouse Events
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseDragged(MouseEvent e){
		if(leftMouseButton) {
			lmbDragged = true;
		} else if(rightMouseButton) {
			rmbDragged = true;
		} else if(middleMousebutton) {
			mmbDragged = true;
		}
		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseMoved(MouseEvent e){
		
		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent e){
		int button = e.getButton();
		if(button == 1) {
			lmbClickLocationX = mouseX;
			lmbClickLocationY = mouseY;
			leftMouseButton = true;
		}
		if(button == 2) {
			mmbClickLocationX = mouseX;
			mmbClickLocationY = mouseY;
			middleMousebutton = true;
		}
		if(button == 3) {
			rmbClickLocationX = mouseX;
			rmbClickLocationY = mouseY;
			rightMouseButton = true;
		}
		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent e){
		int button = e.getButton();
		if(button == 1) {
			lmbReleaseLocationX = mouseX;
			lmbReleaseLocationY = mouseY;
			leftMouseButton = false;
			lmbDragged = false;
		}
		if(button == 2) {
			mmbReleaseLocationX = mouseX;
			mmbReleaseLocationY = mouseY;
			middleMousebutton = false;
			mmbDragged = false;
		}
		if(button == 3) {
			rmbReleaseLocationX = mouseX;
			rmbReleaseLocationY = mouseY;
			rightMouseButton = false;
			rmbDragged = false;
		}
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		//Negative means scrolled away from user.
		//Positive means towards user.
		int whr = e.getWheelRotation();
		mouseScrollValue += whr;
		
		if(whr > 0) {
			whr = 1;
		} else if(whr < 0) {
			whr = -1;
		}
		mouseScrollDirection = whr;
		if(whr < 0) {
			lastMouseScrollDirection = "Up";
		}
		if(whr > 0) {
			lastMouseScrollDirection = "Down";
		}
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 12) Key Events
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e){
		keyBindings.setPressed(e.getKeyCode(), true);
		if(e.getKeyCode() == 8) {
			printThisStringToTheDebugMenu = "I was here yo";
		}
		if(showEditorScreen) {
			if(isPrintableChar(e.getKeyChar())) {
				textBox.addCharacter(e.getKeyChar());
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e){
		keyBindings.setPressed(e.getKeyCode(), false);
		if(showEditorScreen) {
			
		}
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Section 13) Helper Methods, Getters, and Setters
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	protected boolean isPrintableChar(char c){
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return (!Character.isISOControl(c)) && c != KeyEvent.CHAR_UNDEFINED && block != null && block != Character.UnicodeBlock.SPECIALS;
	}
	
	protected Rectangle getProjectArea(){
		return new Rectangle(findProjectAreaX(), findProjectAreaY(), findProjectAreaWidth(), findProjectAreaHeight());
	}
	
	protected int getProjectOffsetX(){
		return -((int) (findProjectAreaWidth() * .01));
	}
	
	protected int getProjectOffsetY(){
		return -((int) (findProjectAreaHeight() * .01));
	}
	
	protected int findProjectAreaX(){
		int x = 0;
		
		
		return x;
	}
	
	protected int findProjectAreaY(){
		int y = 0;
		
		y = y + editorScreenTitleBar.getHeight();
		
		return y;
	}
	
	protected int findProjectAreaWidth(){
		int width = 0;
		
		width = engine.screenWidth;
		width = width - findProjectAreaX();
		
		return width;
	}
	
	protected int findProjectAreaHeight(){
		int height = 0;
		
		height = engine.screenHeight;
		height = height - findProjectAreaY();
		
		return height;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractRcsglKeyBinder getRcsglKeyBinder(){
		return keyBindings;
	}
	
	
	/**
	 * Adds an RCSMenuBar to the top of the screen with some default values
	 * 
	 * @param menuName
	 *            - Name of the entire bar
	 * @param optionNames
	 *            - Names of the buttons on the menu bar
	 * @param ddOptionNames
	 *            - Names of the drop-down options from each button on the menu bar
	 */
	
	protected RcsglMenuBar createDefaultMenuBar(String menuName, String[] optionNames, String[][] ddOptionNames){
		
		
		Font f = scaleFont(new Font("arial unicode ms", Font.BOLD, 20));
		int vertSpacing = 5;
		int horSpacing = 5;
		int x = 0;
		int y = 0;
		
		
		int numRows = ddOptionNames.length;
		int[] numColumns = new int[numRows];
		for (int i = 0; i < numRows; i++) {
			numColumns[i] = (ddOptionNames[i].length);
		}
		
		
		
		ArrayList<RcsglDropDownMenu> ddms = new ArrayList<RcsglDropDownMenu>();
		for (int i = 0; i < numRows; i++) {
			RcsglDropDownOption[] ddo = new RcsglDropDownOption[numColumns[i]];
			for (int j = 0; j < numColumns[i]; j++) {
				ddo[j] = new RcsglDropDownOption(ddOptionNames[i][j], Color.BLACK, Color.WHITE, f, false, Color.DARK_GRAY);
			}
			ddms.add(new RcsglDropDownMenu(ddo, Color.GRAY, Color.BLUE, vertSpacing, horSpacing, Color.BLACK));
		}
		
		
		RcsglMenuBarOption[] mbOptions = new RcsglMenuBarOption[optionNames.length];
		for (int i = 0; i < mbOptions.length; i++) {
			mbOptions[i] = new RcsglMenuBarOption(optionNames[i], vertSpacing, horSpacing, f, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLUE,
					Color.DARK_GRAY, ddms.get(i), false, Color.DARK_GRAY);
		}
		
		return new RcsglMenuBar(menuName, true, x, y, engine.screenWidth, Color.GRAY, mbOptions);
		
		
		
	}
	
	protected RcsglMenuBar createDefaultMenuBarWithSubmenus(String menuName, String[] optionNames, String[][] ddOptionNamesWithSubs, String[][] ddOptionNames, String[][][] ddOptionSubNames, Boolean[] numsWithSubMenus){
		
		
		Font f = scaleFont(new Font("arial unicode ms", Font.BOLD, 20));
		int vertSpacing = 5;
		int horSpacing = 5;
		int x = 0;
		int y = 0;
		
		
		ArrayList<ArrayList<RcsglDropDownMenu>> subDdms = new ArrayList<ArrayList<RcsglDropDownMenu>>();
		
		for (int i = 0; i < ddOptionSubNames.length; i++) {
			ArrayList<RcsglDropDownMenu> tempSubDdms = new ArrayList<RcsglDropDownMenu>();
			for (int j = 0; j < ddOptionSubNames[i].length; j++) {
				RcsglDropDownOption[] subDdops = new RcsglDropDownOption[ddOptionSubNames[i][j].length];
				for (int j2 = 0; j2 < ddOptionSubNames[i][j].length; j2++) {
					subDdops[j2] = new RcsglDropDownOption(ddOptionSubNames[i][j][j2], Color.BLACK, Color.WHITE, f, false, Color.DARK_GRAY);
				}
				tempSubDdms.add(new RcsglDropDownMenu(subDdops, Color.GRAY, Color.BLUE, vertSpacing, horSpacing, Color.BLACK));
			}
			subDdms.add(tempSubDdms);
		}
		
		
		
		int numRows = ddOptionNames.length;
		int[] numColumns = new int[numRows];
		for (int i = 0; i < numRows; i++) {
			numColumns[i] = (ddOptionNames[i].length);
		}
		
		int numRowsWithSubs = ddOptionNamesWithSubs.length;
		int[] numColumnsWithSubs = new int[numRowsWithSubs];
		for (int i = 0; i < numRowsWithSubs; i++) {
			numColumnsWithSubs[i] = ddOptionNamesWithSubs[i].length;
		}
		
		
		
		ArrayList<RcsglDropDownMenu> ddms = new ArrayList<RcsglDropDownMenu>();
		int falseCounter = 0;
		for (int i = 0; i < numRows; i++) {
			if(i - falseCounter < numRowsWithSubs) {
				if(numsWithSubMenus[i]) {
					RcsglDropDownOption[] ddo = new RcsglDropDownOption[numColumns[i] + numColumnsWithSubs[i - falseCounter]];
					int counter = 0;
					for (int j = 0; j < ddo.length; j++) {
						if(j < numColumnsWithSubs[i - falseCounter]) {
							counter++;
							ddo[j] = new RcsglDropDownOptionWithSubmenu(ddOptionNamesWithSubs[i - falseCounter][j], Color.BLACK, Color.WHITE, f, subDdms.get(
									i - falseCounter).get(j), false, Color.DARK_GRAY);
						} else {
							ddo[j] = new RcsglDropDownOption(ddOptionNames[i][j - counter], Color.BLACK, Color.WHITE, f, false, Color.DARK_GRAY);
						}
						
					}
					ddms.add(new RcsglDropDownMenu(ddo, Color.GRAY, Color.BLUE, vertSpacing, horSpacing, Color.BLACK));
				} else {
					falseCounter++;
					RcsglDropDownOption[] ddo = new RcsglDropDownOption[numColumns[i]];
					for (int j = 0; j < numColumns[i]; j++) {
						ddo[j] = new RcsglDropDownOption(ddOptionNames[i][j], Color.BLACK, Color.WHITE, f, false, Color.DARK_GRAY);
					}
					ddms.add(new RcsglDropDownMenu(ddo, Color.GRAY, Color.BLUE, vertSpacing, horSpacing, Color.BLACK));
				}
			} else {
				RcsglDropDownOption[] ddo = new RcsglDropDownOption[numColumns[i]];
				for (int j = 0; j < numColumns[i]; j++) {
					ddo[j] = new RcsglDropDownOption(ddOptionNames[i][j], Color.BLACK, Color.WHITE, f, true, Color.DARK_GRAY);
				}
				ddms.add(new RcsglDropDownMenu(ddo, Color.GRAY, Color.BLUE, vertSpacing, horSpacing, Color.BLACK));
			}
			
			
			
		}
		
		
		RcsglMenuBarOption[] mbOptions = new RcsglMenuBarOption[optionNames.length];
		for (int i = 0; i < mbOptions.length; i++) {
			mbOptions[i] = new RcsglMenuBarOption(optionNames[i], vertSpacing, horSpacing, f, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLUE,
					Color.DARK_GRAY, ddms.get(i), false, Color.DARK_GRAY);
		}
		
		return new RcsglMenuBar(menuName, true, x, y, engine.screenWidth, Color.GRAY, mbOptions);
	}
	
	
	
	public Font scaleFont(Font f){
		int size = (engine.screenWidth * f.getSize()) / 1920;
		return new Font(f.getName(), f.getStyle(), size);
	}
	
	
	
}
