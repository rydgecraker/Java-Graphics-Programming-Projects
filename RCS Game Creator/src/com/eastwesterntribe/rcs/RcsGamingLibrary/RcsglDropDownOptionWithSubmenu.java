package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class RcsglDropDownOptionWithSubmenu extends RcsglDropDownOption {
	
	public RcsglDropDownMenu	subMenu;
	public int					width;
	public boolean				updateConfig;
	
	public RcsglDropDownOptionWithSubmenu(String text, Color color, Color hightlightColor, Font font, RcsglDropDownMenu subMenu, boolean disabled, Color disabledTextColor) {
		super(text, color, hightlightColor, font, disabled, disabledTextColor);
		this.text = text;
		this.subMenu = subMenu;
		this.width = 0;
		needToUpdateConfig = true;
		selected = false;
	}
	
	public String click(){
		if(disabled) {
			return text + " (DISABLED)";
		} else if(selected) {
			return text + " - " + subMenu.click();
		} else {
			return text;
		}
		
	}
	
	public int getWidth(RcsglStringApplications sa, Graphics2D g){
		return sa.getStringWidth(text + " \u25b6 " + parentMenu.horSpacing, g, font);
	}
	
	public void draw(Graphics2D g){
		RcsglStringApplications sa = new RcsglStringApplications();
		if(needToUpdateConfig) {
			updateConfig(g, sa);
		}
		int strh = sa.getStringHeight(g, font);
		if(disabled) {
			g.setColor(disabledTextColor);
		} else if(highlighted) {
			g.setColor(hightlightColor);
		} else {
			g.setColor(color);
		}
		g.setFont(font);
		if(parentMenu != null) {
			int debugger = getWidth(sa, g);
			if(debugger == parentMenu.getWidth()) {
				g.drawString(text + " \u25b6 ", x + parentMenu.horSpacing, y + strh);
			} else {
				g.drawString(text, x + parentMenu.horSpacing, y + strh);
				g.drawString(" \u25b6 ", x + parentMenu.getWidth() - (sa.getStringWidth("\u25b6 ", g, font) + parentMenu.horSpacing), y + strh);
			}
		}
		
		if(selected && !disabled) {
			subMenu.draw(g);
		}
	}
	
	public void updateConfig(Graphics2D g, RcsglStringApplications sa){
		if(parentMenu != null) {
			subMenu.setX(x + parentMenu.getWidth());
			subMenu.setY(y);
		}
		subMenu.updateConfig(g);
		needToUpdateConfig = false;
	}
	
	
	
	public void closeMenus(){
		subMenu.closeMenus();
	}
	
	public void update(int mousePositionX, int mousePositionY){
		if(selected && !disabled) {
			subMenu.update(mousePositionX, mousePositionY);
		}
	}
	
	public boolean isPendingClick(){
		pendingClick = false;
		if(selected) {
			pendingClick = subMenu.isPendingClick();
		}
		return pendingClick;
	}
	
	public void setPendingClick(boolean b){
		pendingClick = b;
		subMenu.setPendingClick(b);
	}
	
	public void setParentMenu(RcsglDropDownMenu menu){
		parentMenu = menu;
	}
	
	public boolean doesSubContain(int mousePosX, int mousePosY){
		if(subMenu.doesContain(mousePosX, mousePosY)) {
			return true;
		}
		return subMenu.doesSubContain(mousePosX, mousePosY);
	}
	
	public boolean doesSubMenuContain(int mousePosX, int mousePosY){
		return false;
	}
	
	
	
}
