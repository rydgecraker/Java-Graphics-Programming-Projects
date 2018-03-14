package com.eastwesterntribe.rcs.RcsGameCreator;

import java.awt.event.KeyEvent;

import com.eastwesterntribe.rcs.RcsGamingLibrary.AbstractRcsglKeyBinder;
import com.eastwesterntribe.rcs.RcsGamingLibrary.RcsglBinding;


public class GameCreatorKeyBinder extends AbstractRcsglKeyBinder {
	
	protected void addDefaultBindings(){
		addBinding(new RcsglBinding("FORWARD", KeyEvent.VK_W));
		addBinding(new RcsglBinding("BACKWARD", KeyEvent.VK_S));
		addBinding(new RcsglBinding("LEFT", KeyEvent.VK_A));
		addBinding(new RcsglBinding("RIGHT", KeyEvent.VK_R));
		addBinding(new RcsglBinding("ROTATELEFT", KeyEvent.VK_Q));
		addBinding(new RcsglBinding("ROTATERIGHT", KeyEvent.VK_E));
		addBinding(new RcsglBinding("QUIT", KeyEvent.VK_ESCAPE));
		addBinding(new RcsglBinding("JUMP", KeyEvent.VK_SPACE));
		addBinding(new RcsglBinding("DEBUG", KeyEvent.VK_F5));
		addBinding(new RcsglBinding("ALTERATION", KeyEvent.VK_SHIFT));
		addBinding(new RcsglBinding("ALTERATION2", KeyEvent.VK_CONTROL));
		addBinding(new RcsglBinding("ALTERATION3", KeyEvent.VK_ALT));
		addBinding(new RcsglBinding("VOLUMEDOWN", KeyEvent.VK_COMMA));
		addBinding(new RcsglBinding("VOLUMEUP", KeyEvent.VK_PERIOD));
		addBinding(new RcsglBinding("DELETE", KeyEvent.VK_DELETE));
		addBinding(new RcsglBinding("BACKSPACE", KeyEvent.VK_BACK_SPACE));
		addBinding(new RcsglBinding("ARROWLEFT", KeyEvent.VK_LEFT));
		addBinding(new RcsglBinding("ARROWRIGHT", KeyEvent.VK_RIGHT));
		addBinding(new RcsglBinding("ARROWUP", KeyEvent.VK_UP));
		addBinding(new RcsglBinding("ARROWDOWN", KeyEvent.VK_DOWN));
	}
	
}
