package zombieGame2;

import java.awt.Color;

public class NextFrame extends GameComponent{
	
	int frameNumber;
	int frameSide;

	public NextFrame(int x, int y, int w, int h) {
		super(x, y, w, h, Color.LIGHT_GRAY);
	}
	public NextFrame(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	
	public void decideFrameNumber(int frameNumber, int frameSide){
		this.frameNumber = frameNumber;
		this.frameSide = frameSide;
	}
	
	public int getFrameNumber(){
		return frameNumber;
	}
	
	public int getFrameSide(){
		return frameSide;
	}

}
