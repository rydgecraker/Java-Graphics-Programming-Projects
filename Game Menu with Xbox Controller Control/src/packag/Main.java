package packag;

import java.util.ArrayList;

import ch.aplu.xboxcontroller.*;

public class Main {
	
	private XboxController c1;
	private XboxController c2;
	private XboxController c3;
	private XboxController c4;
	private boolean c1connected;
	private boolean c2connected;
	private boolean c3connected;
	private boolean c4connected;
	private int vibratel;
	private int vibrater;

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		c1 = new XboxController(1);
		c2 = new XboxController(2);
		c3 = new XboxController(3);
		c4 = new XboxController(4);
		
		controllerConnections();
		cInitialButtonSetup();
		new Game(c1, c1connected, c2, c2connected, c3, c3connected, c4, c4connected);	
	}
	
	public void cInitialButtonSetup(){
		if(c1connected){
			c1.addXboxControllerListener(new XboxControllerAdapter(){
				public void start(boolean b){
					if(b){
						System.out.println("Controller 1 pressed START");
					}else{
						System.out.println("Controller 1 released START");
					}
				}
				public void back(boolean b){
					System.out.println("Controller 1 ended the session");
					c1.release();
					c2.release();
					c3.release();
					c4.release();
					System.exit(0);
				}
			});
		}
		if(c2connected){
			c2.addXboxControllerListener(new XboxControllerAdapter(){
				public void start(boolean b){
					if(b){
						System.out.println("Controller 2 pressed START");
					}else{
						System.out.println("Controller 2 released START");
					}
				}
				public void back(boolean b){
					System.out.println("Controller 2 ended the session");
					c1.release();
					c2.release();
					c3.release();
					c4.release();
					System.exit(0);
				}
			});
		}
		if(c3connected){
			c3.addXboxControllerListener(new XboxControllerAdapter(){
				public void start(boolean b){
					if(b){
						System.out.println("Controller 3 pressed START");
					}else{
						System.out.println("Controller 3 released START");
					}
				}
				public void back(boolean b){
					System.out.println("Controller 3 ended the session");
					c1.release();
					c2.release();
					c3.release();
					c4.release();
					System.exit(0);
				}
			});
		}
		if(c4connected){
			c4.addXboxControllerListener(new XboxControllerAdapter(){
				public void start(boolean b){
					if(b){
						System.out.println("Controller 4 pressed START");
					}else{
						System.out.println("Controller 4 released START");
					}
				}
				public void back(boolean b){
					System.out.println("Controller 4 ended the session");
					c1.release();
					c2.release();
					c3.release();
					c4.release();
					System.exit(0);
				}
			});
		}
	}
	
	
	public void controllerConnections(){
		if(c1.isConnected()){
			System.out.println("Controller 1 is connected");
			c1connected = true;
		}else{
			System.out.println("No Controller connected... Exiting...");
			c1connected = false;
			System.exit(0);
		}
		if(c2.isConnected()){
			System.out.println("Controller 2 is connected");
			c2connected = true;
		}else{
			System.out.println("Controller 2 is not connected");
			c2connected = false;
		}
		if(c3.isConnected()){
			System.out.println("Controller 3 is connected");
			c3connected = true;
		}else{
			System.out.println("Controller 3 is not connected");
			c3connected = false;
		}
		if(c4.isConnected()){
			System.out.println("Controller 4 is connected");
			c4connected = true;
		}else{
			System.out.println("Controller 4 is not connected");
			c4connected = false;
		}
	}

}
