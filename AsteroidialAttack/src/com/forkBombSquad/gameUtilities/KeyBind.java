package com.forkBombSquad.gameUtilities;


public class KeyBind {
	
	private int		bind;
	private int		defaultBind;
	private String	name;
	private boolean	pressed;
	
	
	/**
	 * @param name
	 *            - The name to the type of binding. <br>
	 *            Examples:<br>
	 *            - FORWARD<br>
	 *            - LEFT<br>
	 *            - PAUSE<br>
	 *            - QUIT<br>
	 *            - DEBUG<br>
	 * 
	 * @param defaultBind
	 *            - The keycode that you want to be the default for this type.<br>
	 *            Use KeyEvent.VK_
	 */
	public KeyBind(String name, int defaultBind) {
		this.name = name.toUpperCase();
		this.defaultBind = defaultBind;
		this.bind = defaultBind;
		pressed = false;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getDefaultBinding() {
		return defaultBind;
	}
	
	
	public int getBinding() {
		return bind;
	}
	
	
	public void resetToDefaultBinding() {
		bind = defaultBind;
	}
	
	
	public boolean isKey(String inputName) {
		return (name.equalsIgnoreCase(inputName));
	}
	
	
	public boolean isPressed() {
		return pressed;
	}
	
	
	public void setPressed(boolean b) {
		this.pressed = b;
	}
	
	
	public void setDefaultBinding(int b) {
		defaultBind = b;
		bind = defaultBind;
	}
	
	
	public void setBinding(int b) {
		bind = b;
	}
	
}
