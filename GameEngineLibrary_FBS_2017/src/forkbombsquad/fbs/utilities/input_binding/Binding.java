package forkbombsquad.fbs.utilities.input_binding;


import java.util.ArrayList;


public class Binding {
	
	private ArrayList<Integer>	bindings;
	private int					defaultBind;
	private String				name;
	private boolean				pressed		= false;
	private boolean				singlePress	= false;
	
	
	public Binding(String name, int defaultBind) {
		bindings = new ArrayList<Integer>();
		this.defaultBind = defaultBind;
		bindings.add(defaultBind);
		this.name = name;
	}
	
	
	public void addBinding(int bind) {
		if (!bindings.contains(bind)) {
			bindings.add(bind);
		}
	}
	
	
	public void removeBinding(int bind) {
		if (bindings.contains(bind)) {
			bindings.remove((Integer) bind);
		}
	}
	
	
	public void addBindings(int[] bindings) {
		for (int i : bindings) {
			addBinding(i);
		}
	}
	
	
	public void removeBindings(int[] bindings) {
		for (int i : bindings) {
			removeBinding(i);
		}
	}
	
	
	public void replaceBinding(int oldBinding, int newBinding) {
		removeBinding(oldBinding);
		addBinding(newBinding);
	}
	
	
	public void resetToDefault() {
		bindings.clear();
		bindings.add(defaultBind);
	}
	
	
	
	public String getName() {
		return name;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
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
	
	
	public boolean isName(String inputName) {
		return (name.equalsIgnoreCase(inputName));
	}
	
	
	public boolean hasBinding(int bind) {
		for (Integer i : bindings) {
			if (i == bind) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
