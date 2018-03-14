package com.eastwesterntribe.rcs.RcsGamingLibrary;



/**
 * This is where all the button presses and key bindings are stored.<br>
 * Interaction is made easy and simple.
 * 
 * @author RCS
 */
public abstract class AbstractRcsglKeyBinder {
	
	/**
	 * Holds all the key-binding information
	 */
	private RcsglBindingHolder	bindings;
	
	public AbstractRcsglKeyBinder() {
		bindings = new RcsglBindingHolder();
		addDefaultBindings();
	}
	
	/**
	 * Called in the constructor<br>
	 * Here is where you'll add in all the default key bindings.<br>
	 * Just call addBinding() or addBindings();
	 */
	protected abstract void addDefaultBindings();
	
	public final void addBinding(RcsglBinding b){
		bindings.addBinding(b);
	}
	
	public final void addBindings(RcsglBinding[] b){
		bindings.addBindings(b);
	}
	
	
	public final void resetToDefaults(){
		bindings.resetToDefaults();
	}
	
	public void setBinding(RcsglBinding b, int key){
		bindings.setBinding(b, key);
	}
	
	public void setBinding(String s, int key){
		bindings.setBinding(s, key);
	}
	
	public void setDefaultBinding(RcsglBinding b, int key){
		bindings.setDefaultBinding(b, key);
	}
	
	public void setDefaultBinding(String s, int key){
		bindings.setDefaultBinding(s, key);
	}
	
	public int getDefaultKeyBinding(RcsglBinding b){
		return bindings.getDefaultBinding(b);
	}
	
	public int getDefaultKeyBinding(String s){
		return bindings.getDefaultBinding(s);
	}
	
	public int getKeyBinding(RcsglBinding b){
		return bindings.getBinding(b);
	}
	
	public int getKeyBinding(String s){
		return bindings.getBinding(s);
	}
	
	public boolean isPressed(RcsglBinding b){
		return bindings.isPressed(b);
	}
	
	public boolean isPressed(String s){
		return bindings.isPressed(s);
	}
	
	public void setPressed(int keyCode, boolean b){
		bindings.setPressed(keyCode, b);
	}
	
	public void setPressedByBinding(RcsglBinding bi, boolean b){
		bindings.setPressed(bi, b);
	}
	
	public void setPressedByBinding(String s, boolean b){
		bindings.setPressed(s, b);
	}
	
	public String getKeyName(RcsglBinding b){
		return bindings.getKeyName(b);
	}
	
	public String getKeyName(String s){
		return bindings.getKeyName(s);
	}
}