package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class RcsglMenuBar {
	
	public String				title;
	public boolean				display;
	
	protected int				x;
	protected int				y;
	protected int				width;
	protected int				height;
	public RcsglMenuBarOption[]	options;
	protected int				hoverPosition;
	protected boolean			needToUpdateConfig;
	protected Color				color;
	
	protected boolean			pendingClick;
	protected String			clickedString;
	
	//A bar designed to hold buttons/drop-menus. Typically across the top of the screen.
	
	public RcsglMenuBar(String title, boolean display, int x, int y, int width, Color color, RcsglMenuBarOption[] options) {
		this.title = title;
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = 0;
		this.options = options;
		this.hoverPosition = -1;
		this.pendingClick = false;
		this.clickedString = "";
		this.needToUpdateConfig = true;
		this.color = color;
	}
	
	public int getTotalButtonWidth(){
		int w = 0;
		for (int i = 0; i < options.length; i++) {
			w += options[i].getWidth();
		}
		return w;
	}
	
	private void updateConfig(Graphics2D g){
		int tempXPos = x;
		
		
		for (int i = 0; i < options.length; i++) {
			options[i].setX(tempXPos);
			options[i].updateConfig(g);
			tempXPos += options[i].getWidth();
		}
		int tempHeight = 0;
		for (int i = 0; i < options.length; i++) {
			tempHeight = options[i].getHeight();
			if(tempHeight > this.height) {
				this.height = tempHeight;
			}
		}
		needToUpdateConfig = false;
	}
	
	public void draw(Graphics2D g){
		
		if(display) {
			if(needToUpdateConfig) {
				updateConfig(g);
			}
			
			g.setColor(color);
			g.fillRect(x, y, width, height);
			
			for (int i = 0; i < options.length; i++) {
				options[i].draw(g);
			}
			
			g.setColor(options[0].getOutlineColor());
			g.drawRect(x, y, width, height);
			g.drawLine(x, y + height - 1, x + width, y + height - 1);
			
		}
	}
	
	public boolean doesContain(int mousePosX, int mousePosY){
		Point p = new Point(mousePosX, mousePosY);
		if(getRect().contains(p)) {
			return true;
		}
		return false;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	private void determineHoverPosition(int mousePosX, int mousePosY){
		for (int i = 0; i < options.length; i++) {
			if(options[i].doesContain(mousePosX, mousePosY)) {
				hoverPosition = i;
				return;
			}
		}
		hoverPosition = -1;
	}
	
	public void doHighlighting(int mousePosX, int mousePosY){
		if(hoverPosition >= 0 && !options[hoverPosition].isHighlighted() && !options[hoverPosition].disabled) {
			options[hoverPosition].setHighlighted(true);
			for (int i = 0; i < options.length; i++) {
				if(i != hoverPosition && options[i].isHighlighted()) {
					options[i].setHighlighted(false);
				}
				if(i != hoverPosition && options[i].isSelected() && !options[i].doesSubContain(mousePosX, mousePosY)) {
					options[i].setSelected(false);
					options[i].closeMenus();
				}
			}
		}
		
		if(hoverPosition < 0) {
			for (int i = 0; i < options.length; i++) {
				if(options[i].isHighlighted()) {
					options[i].setHighlighted(false);
				}
				if(options[i].isSelected() && !options[i].doesSubContain(mousePosX, mousePosY)) {
					options[i].setSelected(false);
					options[i].closeMenus();
				}
			}
		}
		
	}
	
	//Called when panel updates mouse position
	public void update(int mousePosX, int mousePosY){
		determineHoverPosition(mousePosX, mousePosY);
		doHighlighting(mousePosX, mousePosY);
		boolean timeToCloseMenus = !this.doesContain(mousePosX, mousePosY);
		
		int subContainCounter = -1;
		
		if(hoverPosition < 0) {
			for (int i = 0; i < options.length; i++) {
				if(options[i].doesSubContain(mousePosX, mousePosY) && options[i].isSelected()) {
					subContainCounter = i;
					timeToCloseMenus = false;
					break;
				} else {
					timeToCloseMenus = true;
				}
			}
		}
		
		
		if(subContainCounter >= 0 && options[subContainCounter].isSelected()) {
			options[subContainCounter].dropDownMenu.update(mousePosX, mousePosY);
			timeToCloseMenus = false;
		} else if(subContainCounter >= 0 && !options[subContainCounter].isSelected()) {
			timeToCloseMenus = true;
		}
		
		
		if(timeToCloseMenus) {
			closeMenus();
		}
		
	}
	
	//Happens regardless of whether or not the mouse is on the menubar!!!!!!!!!!!!!!
	public void click(){
		if(hoverPosition >= 0) {
			options[hoverPosition].click();
		} else {
			for (int i = 0; i < options.length; i++) {
				if(options[i].isSelected() && options[i].isPendingClick()) {
					clickedString = options[i].sendClick();
					pendingClick = true;
				}
			}
			
			if(!pendingClick) {
				closeMenus();
			}
		}
	}
	
	
	public void closeMenus(){
		for (int i = 0; i < options.length; i++) {
			options[i].closeMenus();
			options[i].setHighlighted(false);
			options[i].setSelected(false);
		}
		hoverPosition = -1;
	}
	
	
	
	public int getX(){
		return x;
	}
	
	
	
	public void setX(int x){
		this.x = x;
		this.needToUpdateConfig = true;
	}
	
	
	
	public int getY(){
		return y;
	}
	
	
	
	public void setY(int y){
		this.y = y;
		this.needToUpdateConfig = true;
	}
	
	
	
	public int getWidth(){
		return width;
	}
	
	
	
	public void setWidth(int width){
		this.width = width;
		this.needToUpdateConfig = true;
	}
	
	
	
	public int getHeight(){
		return height;
	}
	
	
	
	public void setHeight(int height){
		this.height = height;
		this.needToUpdateConfig = true;
	}
	
	
	
	public RcsglMenuBarOption[] getOptions(){
		return options;
	}
	
	
	
	public void setOptions(RcsglMenuBarOption[] options){
		this.options = options;
		this.needToUpdateConfig = true;
	}
	
	
	
	public int getHoverPosition(){
		return hoverPosition;
	}
	
	
	
	public void setHoverPosition(int hoverPosition){
		this.hoverPosition = hoverPosition;
	}
	
	
	
	public boolean isPendingClick(){
		return pendingClick;
	}
	
	
	
	public String getTitle(){
		return title;
	}
	
	
	public void setTitle(String title){
		this.title = title;
	}
	
	
	public boolean isDisplay(){
		return display;
	}
	
	
	public void setDisplay(boolean display){
		this.display = display;
	}
	
	public void setPendingClick(boolean pendingClick){
		this.pendingClick = pendingClick;
		for (int i = 0; i < options.length; i++) {
			options[i].setPendingClick(pendingClick);
		}
	}
	
	
	public String getClickedString(){
		return clickedString;
	}
	
	
	
	public void setClickedString(String clickedString){
		this.clickedString = clickedString;
	}
	
	
	public boolean isNeedToUpdateConfig(){
		return needToUpdateConfig;
	}
	
	
	public void setNeedToUpdateConfig(boolean needToUpdateConfig){
		this.needToUpdateConfig = needToUpdateConfig;
	}
	
	
	public Color getColor(){
		return color;
	}
	
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public boolean changeNameByName(String oldName, String newName){
		for (int i = 0; i < options.length; i++) {
			if(options[i].isName(oldName)) {
				options[i].text = newName;
				return true;
			} else {
				for (int j = 0; j < options[i].dropDownMenu.options.length; j++) {
					if(options[i].dropDownMenu.options[j].isName(oldName)) {
						options[i].dropDownMenu.options[j].text = newName;
						return true;
					} else {
						if(options[i].dropDownMenu.options[j] instanceof RcsglDropDownOptionWithSubmenu) {
							RcsglDropDownOptionWithSubmenu ddows = (RcsglDropDownOptionWithSubmenu) options[i].dropDownMenu.options[j];
							for (int k = 0; k < ddows.subMenu.options.length; k++) {
								if(ddows.subMenu.options[k].isName(oldName)) {
									ddows.subMenu.options[k].text = newName;
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean enableByName(String s){
		for (int i = 0; i < options.length; i++) {
			if(options[i].isName(s)) {
				options[i].disabled = false;
				return true;
			} else {
				for (int j = 0; j < options[i].dropDownMenu.options.length; j++) {
					if(options[i].dropDownMenu.options[j].isName(s)) {
						options[i].dropDownMenu.options[j].disabled = false;
						return true;
					} else {
						if(options[i].dropDownMenu.options[j] instanceof RcsglDropDownOptionWithSubmenu) {
							RcsglDropDownOptionWithSubmenu ddows = (RcsglDropDownOptionWithSubmenu) options[i].dropDownMenu.options[j];
							for (int k = 0; k < ddows.subMenu.options.length; k++) {
								if(ddows.subMenu.options[k].isName(s)) {
									ddows.subMenu.options[k].disabled = false;
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean disableByName(String s){
		for (int i = 0; i < options.length; i++) {
			if(options[i].isName(s)) {
				options[i].disabled = true;
				return true;
			} else {
				for (int j = 0; j < options[i].dropDownMenu.options.length; j++) {
					if(options[i].dropDownMenu.options[j].isName(s)) {
						options[i].dropDownMenu.options[j].disabled = true;
						return true;
					} else {
						if(options[i].dropDownMenu.options[j] instanceof RcsglDropDownOptionWithSubmenu) {
							RcsglDropDownOptionWithSubmenu ddows = (RcsglDropDownOptionWithSubmenu) options[i].dropDownMenu.options[j];
							for (int k = 0; k < ddows.subMenu.options.length; k++) {
								if(ddows.subMenu.options[k].isName(s)) {
									ddows.subMenu.options[k].disabled = true;
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
}
