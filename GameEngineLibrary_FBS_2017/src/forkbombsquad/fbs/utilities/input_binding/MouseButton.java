package forkbombsquad.fbs.utilities.input_binding;


public class MouseButton {
	
	private int		mouseButton;
	private boolean	pressed		= false;
	private boolean	singlePress	= false;
	
	
	public MouseButton(int mouseButton) {
		this.mouseButton = mouseButton;
	}
	
	
	
	public int getMouseButton() {
		return mouseButton;
	}
	
	
	public boolean isPressed() {
		return pressed;
	}
	
	
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
		singlePress = pressed;
	}
	
	
	
	public boolean isSinglePress() {
		if (singlePress) {
			singlePress = false;
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
}
