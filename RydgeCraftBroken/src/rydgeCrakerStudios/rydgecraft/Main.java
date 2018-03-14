package rydgeCrakerStudios.rydgecraft;

import java.io.IOException;

import org.lwjgl.LWJGLException;

public class Main {

	public static void main(String[] args) {
		try {
			new Main();
		} catch (LWJGLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Main() throws LWJGLException, IOException{
		new GameRunner();
	}

}
