package com.forkBombSquad.gameUtilities;


public abstract class AbstractKeyBinder {
	
	/**
	 * Holds all the key-binding information
	 */
	private KeyBindHandler bindings;
	
	
	public AbstractKeyBinder() {
		bindings = new KeyBindHandler();
		addDefaultBindings();
	}
	
	
	/**
	 * Called in the constructor<br>
	 * Here is where you'll add in all the default key bindings.<br>
	 * Just call addBinding() or addBindings();
	 */
	protected abstract void addDefaultBindings();
	
	
	public final void addBinding(KeyBind b) {
		bindings.addBinding(b);
	}
	
	
	public final void addBindings(KeyBind[] b) {
		bindings.addBindings(b);
	}
	
	
	public final void resetToDefaults() {
		bindings.resetToDefaults();
	}
	
	
	public void setBinding(KeyBind b, int key) {
		bindings.setBinding(b, key);
	}
	
	
	public void setBinding(String s, int key) {
		bindings.setBinding(s, key);
	}
	
	
	public void setDefaultBinding(KeyBind b, int key) {
		bindings.setDefaultBinding(b, key);
	}
	
	
	public void setDefaultBinding(String s, int key) {
		bindings.setDefaultBinding(s, key);
	}
	
	
	public int getDefaultKeyBinding(KeyBind b) {
		return bindings.getDefaultBinding(b);
	}
	
	
	public int getDefaultKeyBinding(String s) {
		return bindings.getDefaultBinding(s);
	}
	
	
	public int getKeyBinding(KeyBind b) {
		return bindings.getBinding(b);
	}
	
	
	public int getKeyBinding(String s) {
		return bindings.getBinding(s);
	}
	
	
	public boolean isPressed(KeyBind b) {
		return bindings.isPressed(b);
	}
	
	
	public boolean isPressed(String s) {
		return bindings.isPressed(s);
	}
	
	
	public void setPressed(int keyCode, boolean b) {
		bindings.setPressed(keyCode, b);
	}
	
	
	public void setPressedByBinding(KeyBind bi, boolean b) {
		bindings.setPressed(bi, b);
	}
	
	
	public void setPressedByBinding(String s, boolean b) {
		bindings.setPressed(s, b);
	}
	
	
	public String getKeyName(KeyBind b) {
		return bindings.getKeyName(b);
	}
	
	
	public String getKeyName(String s) {
		return bindings.getKeyName(s);
	}
	
}
