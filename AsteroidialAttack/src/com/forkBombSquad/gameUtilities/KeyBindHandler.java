package com.forkBombSquad.gameUtilities;


import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class KeyBindHandler {
	
	
	private ArrayList<KeyBind> bindings;
	
	
	public KeyBindHandler() {
		bindings = new ArrayList<KeyBind>();
	}
	
	
	public void addBindings(KeyBind[] b) {
		for (int i = 0; i < b.length; i++) {
			bindings.add(b[i]);
		}
	}
	
	
	public void addBinding(KeyBind b) {
		bindings.add(b);
	}
	
	
	public void setBinding(KeyBind b, int key) {
		setBinding(b.getName(), key);
	}
	
	
	public void setBinding(String s, int key) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				bindings.get(i).setBinding(key);
			}
		}
	}
	
	
	public void setDefaultBinding(KeyBind b, int key) {
		setDefaultBinding(b.getName(), key);
	}
	
	
	public void setDefaultBinding(String s, int key) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				bindings.get(i).setDefaultBinding(key);
			}
		}
	}
	
	
	public int getDefaultBinding(KeyBind b) {
		return getDefaultBinding(b.getName());
	}
	
	
	public int getDefaultBinding(String s) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				return bindings.get(i).getDefaultBinding();
			}
		}
		return -1;
	}
	
	
	public int getBinding(KeyBind b) {
		return getBinding(b.getName());
	}
	
	
	public int getBinding(String s) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				return bindings.get(i).getBinding();
			}
		}
		return -1;
	}
	
	
	public boolean isPressed(KeyBind b) {
		return isPressed(b.getName());
	}
	
	
	public boolean isPressed(String s) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				return bindings.get(i).isPressed();
			}
		}
		return false;
	}
	
	
	public void setPressed(int keyCode, boolean b) {
		for (int i = 0; i < bindings.size(); i++) {
			if (keyCode == bindings.get(i).getBinding()) {
				bindings.get(i).setPressed(b);
			}
		}
	}
	
	
	public void setPressed(KeyBind bi, boolean b) {
		setPressed(bi.getName(), b);
	}
	
	
	public void setPressed(String s, boolean b) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				bindings.get(i).setPressed(b);
				;
			}
		}
	}
	
	
	public void resetToDefaultBinding(KeyBind bi) {
		resetToDefaultBinding(bi.getName());
	}
	
	
	public void resetToDefaultBinding(String s) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				bindings.get(i).resetToDefaultBinding();
				;
			}
		}
	}
	
	
	public void resetToDefaults() {
		for (int i = 0; i < bindings.size(); i++) {
			bindings.get(i).resetToDefaultBinding();
		}
	}
	
	
	public KeyBind[] getBindings() {
		KeyBind[] rcsglb = (KeyBind[]) bindings.toArray();
		return rcsglb;
	}
	
	
	public String getKeyName(KeyBind b) {
		return getKeyName(b.getName());
	}
	
	
	public String getKeyName(String s) {
		for (int i = 0; i < bindings.size(); i++) {
			if (bindings.get(i).isKey(s)) {
				return KeyEvent.getKeyText(bindings.get(i).getBinding());
			}
		}
		return "";
	}
	
}
