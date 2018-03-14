package forkbombsquad.fbs.utilities.input_binding;


import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class InputHolder {
	
	private MouseButton[]		mouseButtons;
	private ArrayList<Binding>	bindings;
	
	
	public InputHolder() {
		mouseButtons = new MouseButton[3];
		bindings = new ArrayList<Binding>();
		mouseButtons[0] = new MouseButton(MouseEvent.BUTTON1);
		mouseButtons[1] = new MouseButton(MouseEvent.BUTTON2);
		mouseButtons[2] = new MouseButton(MouseEvent.BUTTON3);
	}
	
	
	public void addBindings(Binding[] b) {
		for (Binding binding : b) {
			bindings.add(binding);
		}
	}
	
	
	public void addBinding(Binding b) {
		bindings.add(b);
	}
	
	
	public void addBinding(String name, int defaultBind) {
		addBinding(new Binding(name, defaultBind));
	}
	
	
	public void addBindingTo(String existingKey, int newBinding) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isName(existingKey)) {
				bindings.get(i).addBinding(newBinding);
			}
		}
	}
	
	
	public void addBindingsTo(String existingKey, int[] newBindings) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isName(existingKey)) {
				bindings.get(i).addBindings(newBindings);
			}
		}
	}
	
	
	public void removeBindingFrom(String existingKey, int oldBinding) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isName(existingKey)) {
				bindings.get(i).removeBinding(oldBinding);
			}
		}
	}
	
	
	public void removeBindingsFrom(String existingKey, int[] oldBindings) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isName(existingKey)) {
				bindings.get(i).removeBindings(oldBindings);
			}
		}
	}
	
	
	public void replaceBinding(String existingKey, int oldBinding, int newBinding) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isName(existingKey)) {
				bindings.get(i).replaceBinding(oldBinding, newBinding);
			}
		}
	}
	
	
	public boolean isPressed(String key) {
		for (Binding binding : bindings) {
			if (binding.isName(key)) {
				return binding.isPressed();
			}
		}
		return false;
	}
	
	
	public boolean isSinglePressed(String key) {
		for (Binding binding : bindings) {
			if (binding.isName(key)) {
				return binding.isSinglePress();
			}
		}
		return false;
	}
	
	
	public void setPressed(int keyCode, boolean pressed) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).hasBinding(keyCode)) {
				bindings.get(i).setPressed(pressed);
			}
		}
	}
	
	
	public void setPressed(String key, boolean pressed) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isName(key)) {
				bindings.get(i).setPressed(pressed);
			}
		}
	}
	
	
	public void setMousePressed(int button, boolean pressed) {
		if (button != MouseEvent.NOBUTTON) {
			mouseButtons[getMouseButton(button)].setPressed(pressed);
		}
	}
	
	
	private int getMouseButton(int button) {
		if (button == MouseEvent.BUTTON1) {
			return 0;
		} else if (button == MouseEvent.BUTTON2) {
			return 1;
		} else if (button == MouseEvent.BUTTON3) {
			return 2;
		}
		return 0;
	}
	
	
	private boolean mousePressed(int button) {
		return mouseButtons[button].isPressed();
	}
	
	
	private boolean mouseSinglePressed(int button) {
		return mouseButtons[button].isSinglePress();
	}
	
	
	public boolean leftMousePressed() {
		return mousePressed(0);
	}
	
	
	public boolean middleMousePressed() {
		return mousePressed(2);
	}
	
	
	public boolean rightMousePressed() {
		return mousePressed(1);
	}
	
	
	public boolean leftMouseSinglePressed() {
		return mouseSinglePressed(0);
	}
	
	
	public boolean middleMouseSinglePressed() {
		return mouseSinglePressed(2);
	}
	
	
	public boolean rightMouseSinglePressed() {
		return mouseSinglePressed(1);
	}
	
}
