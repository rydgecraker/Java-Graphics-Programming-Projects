package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Holds the RcsglBindings and does operations with them <br>
 * Used in the RcsglKeyBinder class
 * 
 * @author Rydge
 */
public class RcsglBindingHolder {
	
	private ArrayList<RcsglBinding>	bindings;
	
	public RcsglBindingHolder() {
		bindings = new ArrayList<RcsglBinding>();
	}
	
	public void addBindings(RcsglBinding[] b){
		for (int i = 0; i < b.length; i++) {
			bindings.add(b[i]);
		}
	}
	
	public void addBinding(RcsglBinding b){
		bindings.add(b);
	}
	
	public void setBinding(RcsglBinding b, int key){
		setBinding(b.getName(), key);
	}
	
	public void setBinding(String s, int key){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				bindings.get(i).setBinding(key);
			}
		}
	}
	
	public void setDefaultBinding(RcsglBinding b, int key){
		setDefaultBinding(b.getName(), key);
	}
	
	public void setDefaultBinding(String s, int key){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				bindings.get(i).setDefaultBinding(key);
			}
		}
	}
	
	public int getDefaultBinding(RcsglBinding b){
		return getDefaultBinding(b.getName());
	}
	
	public int getDefaultBinding(String s){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				return bindings.get(i).getDefaultBinding();
			}
		}
		return -1;
	}
	
	public int getBinding(RcsglBinding b){
		return getBinding(b.getName());
	}
	
	public int getBinding(String s){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				return bindings.get(i).getBinding();
			}
		}
		return -1;
	}
	
	public boolean isPressed(RcsglBinding b){
		return isPressed(b.getName());
	}
	
	public boolean isPressed(String s){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				return bindings.get(i).isPressed();
			}
		}
		return false;
	}
	
	public void setPressed(int keyCode, boolean b){
		for (int i = 0; i < bindings.size(); i++) {
			if(keyCode == bindings.get(i).getBinding()) {
				bindings.get(i).setPressed(b);
			}
		}
	}
	
	public void setPressed(RcsglBinding bi, boolean b){
		setPressed(bi.getName(), b);
	}
	
	public void setPressed(String s, boolean b){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				bindings.get(i).setPressed(b);
				;
			}
		}
	}
	
	public void resetToDefaultBinding(RcsglBinding bi){
		resetToDefaultBinding(bi.getName());
	}
	
	public void resetToDefaultBinding(String s){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				bindings.get(i).resetToDefaultBinding();
				;
			}
		}
	}
	
	public void resetToDefaults(){
		for (int i = 0; i < bindings.size(); i++) {
			bindings.get(i).resetToDefaultBinding();
		}
	}
	
	
	public RcsglBinding[] getBindings(){
		RcsglBinding[] rcsglb = (RcsglBinding[]) bindings.toArray();
		return rcsglb;
	}
	
	public String getKeyName(RcsglBinding b){
		return getKeyName(b.getName());
	}
	
	public String getKeyName(String s){
		for (int i = 0; i < bindings.size(); i++) {
			if(bindings.get(i).isKey(s)) {
				return KeyEvent.getKeyText(bindings.get(i).getBinding());
			}
		}
		return "";
	}
}
