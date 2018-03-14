package rydgeCrakerStudios.rydgecraft;

import java.awt.Point;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GameRunner {
	
	public int screenWidth;
	public int screenHeight;
	
	public boolean running;
	
	public Point centerPoint;
	
	public Crosshair crosshair;
	
	public int fkey, bkey, lkey, rkey, qkey;
	public boolean forward, backward, left, right, quit;
	public boolean lmouse, rmouse;
	
	public Block[][][] blocks;
	public int blockSize = 2;
	
	int blocksX = 10;
	int blocksY = 10;
	int blocksZ = 10;
	
	public float playerLocationX = 0;
	public float playerLocationY = -10;
	public float playerLocationZ = 50;
	
	public float xrotan = 0;
	public float yrotan = 0;
	public float zrotan = 0;
	
	Block testingBlock;

	public GameRunner() throws LWJGLException, IOException{
		Display.setLocation(0, 0);
		Display.setVSyncEnabled(false);
		Display.setTitle("RydgeCraft V.01");
		Display.setFullscreen(true);
		Display.create();
		init();
		initgl();
		gameLoop();
		closingOperations();
	}
	
	public void closingOperations(){
		crosshair.releaseTexture();
		for(int x = 0; x < blocksX; x++){
			for(int y = 0; y < blocksY; y++){
				for(int z = 0; z < blocksZ; z++){
					blocks[x][y][z].releaseTextures();
				}
			}
		}
	}
	
	public void init(){
		screenWidth = Display.getWidth();
		screenHeight = Display.getHeight();
		running = true;
		centerPoint = new Point((int) screenWidth / 2,(int) screenHeight / 2);
		fkey = Keyboard.KEY_A;
		bkey = Keyboard.KEY_S;
		lkey = Keyboard.KEY_A;
		rkey = Keyboard.KEY_D;
		qkey = Keyboard.KEY_Q;
		crosshair = new Crosshair(centerPoint, 1, 20);
		
		
		blocks = new Block[blocksX][blocksY][blocksZ];
		
		for(int x = 0; x < blocksX; x++){
			for(int y = 0; y < blocksY; y++){
				for(int z = 0; z < blocksZ; z++){
					blocks[x][y][z] = new Block(x, y, -z, blockSize);
				}
			}
		}
		testingBlock = new Block(1, 1, 1, 2);
		
	}
	
	public void createBlocks() throws IOException{
		crosshair.setTexture(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Crosshair.image)));
		//loadDirtBlocks 
		Texture dirtTop = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(testingBlock.imageLocationTop));
		Texture dirtSide = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(testingBlock.imageLocationSide));
		Texture dirtBottom = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(testingBlock.imageLocationBottom));
	
		testingBlock.setTextures(dirtSide);
		
		
		for(int x = 0; x < blocksX; x++){
			for(int y = 0; y < blocksY; y++){
				for(int z = 0; z < blocksZ; z++){
					blocks[x][y][z].setTextures(dirtTop, dirtSide, dirtBottom);
				}
			}
		}		
	}
	
	public void initgl() throws IOException{
		GL11.glViewport(0, 0, (int) screenWidth, (int) screenHeight);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45.0f, (screenWidth / screenHeight), 0.1f, 500.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GL11.glEnable(GL11.GL_NORMALIZE);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		createBlocks();
	}
	
	private void gl2D(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, (float) screenWidth, 0, (float) screenHeight, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void gl3D(){
		GL11.glViewport(0, 0, (int) screenWidth, (int) screenHeight);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45.0f, (float) screenWidth / (float) screenHeight, 0.1f, 100.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	private void checkForClose(){
		if(Display.isCloseRequested()){
			running = false;
		}
	}
	
	private void gameLoop(){
		while(running){
			//GL11.glTranslatef((float) playerLocationX, (float) playerLocationY, (float) playerLocationZ);
			checkForClose();
			getInputs();
			handleInputs();
			cleanScreen();
			draw2D();
			rotate();
			draw3D();
			Display.update();
		}
		
	}
	
	public void rotate(){
		GL11.glTranslatef(0,0,0);
		if(Mouse.getY() > centerPoint.y){
			double dis = screenHeight - centerPoint.getY();
			double p = Mouse.getY() - centerPoint.getY();
			yrotan -= (p / dis) * 50;
		}
		
		if(Mouse.getY() < centerPoint.y){
			double dis = screenHeight - centerPoint.getY();
			double p = centerPoint.getY() - Mouse.getY();
			yrotan += (p / dis) * 50;
		}
		GL11.glRotatef(yrotan, 1, 0, 0);
		
		if(Mouse.getX() > centerPoint.x){
			double dis = screenWidth - centerPoint.getX();
			double p = Mouse.getX() - centerPoint.getX();
			xrotan += (p / dis) * 50;
		}
		
		if(Mouse.getX() < centerPoint.x){
			double dis = screenWidth - centerPoint.getX();
			double p = centerPoint.getX() - Mouse.getX();
			xrotan -= (p / dis) * 50;
		}
		
		GL11.glRotatef(xrotan, 0, 1, 0);
		GL11.glTranslatef(playerLocationX, playerLocationY, playerLocationZ);
		
		Mouse.setCursorPosition(centerPoint.x, centerPoint.y);
		
	}
	
	private void cleanScreen() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
	}

	private void draw3D() {
		gl3D();
		GL11.glColor3f(1, 1, 1);
		for(int x = 0; x < blocksX; x++){
			for(int y = 0; y < blocksY; y++){
				for(int z = 0; z < blocksZ; z++){
					blocks[x][y][z].draw();
				}
			}
		}
		
		testingBlock.draw();
	}

	private void draw2D() {
		gl2D();
		crosshair.draw();
	}

	private void handleInputs() {
		if(forward){
			playerLocationZ += .05;
		}
		if(backward){
			playerLocationZ -= .05;
		}
		if(left){
			
		}
		if(right){
			
		}
		if(quit){
			running = false;
		}
		
	}

	private void getInputs() {
		lmouse = Mouse.isButtonDown(0);
		rmouse = Mouse.isButtonDown(1);
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) { //Pressed
				if (Keyboard.getEventKey() == fkey) {
		        	forward = true;
		        }
		        if (Keyboard.getEventKey() == bkey) {
		        	backward = true;
		        }
		        if (Keyboard.getEventKey() == lkey) {
		        	left = true;
		        }
		        if (Keyboard.getEventKey() == rkey) {
		        	right = true;
		        }
		        if(Keyboard.getEventKey() == qkey){
		        	quit = true;
		        }
		    } else { //Released
		    	if (Keyboard.getEventKey() == fkey) {
		        	forward = false;
		        }
		        if (Keyboard.getEventKey() == bkey) {
		        	backward = false;
		        }
		        if (Keyboard.getEventKey() == lkey) {
		        	left = false;
		        }
		        if (Keyboard.getEventKey() == rkey) {
		        	right = false;
		        }
		        if(Keyboard.getEventKey() == qkey){
		        	quit = false;
		        }
		    }
		}
	}

	public void printf(String s){
		System.out.println(s);
	}
	
}
