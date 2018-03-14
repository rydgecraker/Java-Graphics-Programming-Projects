package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class RcsglMenuBarOption {
	
	protected String			text;
	protected int				x;
	protected int				y;
	protected int				stringVertOffset;
	protected int				stringHorOffset;
	protected Font				font;
	protected Color				textColor;
	protected Color				highlightTextColor;
	protected Color				selectedTextColor;
	protected Color				outlineColor;
	protected Color				highlightBgColor;
	protected Color				selectedBgColor;
	protected Color				disabledTextColor;
	protected boolean			highlighted;
	protected boolean			selected;
	protected boolean			needToUpdateConfig;
	protected boolean			disabled;
	protected int				width;
	protected int				height;
	public RcsglDropDownMenu	dropDownMenu;
	protected boolean			pendingClick;
	
	
	public RcsglMenuBarOption(String text, int stringVertOffset, int stringHorOffset, Font font, Color textColor, Color highlightTextColor, Color selectedTextColor, Color outlineColor, Color highlightBgColor, Color selectedBgColor, RcsglDropDownMenu dropDownMenu, boolean disabled, Color disabledTextColor) {
		super();
		this.text = text;
		this.x = 0;
		this.y = 0;
		this.stringVertOffset = stringVertOffset;
		this.stringHorOffset = stringHorOffset;
		this.font = font;
		this.textColor = textColor;
		this.highlightTextColor = highlightTextColor;
		this.selectedTextColor = selectedTextColor;
		this.outlineColor = outlineColor;
		this.highlightBgColor = highlightBgColor;
		this.selectedBgColor = selectedBgColor;
		this.highlighted = false;
		this.selected = false;
		this.dropDownMenu = dropDownMenu;
		pendingClick = false;
		needToUpdateConfig = true;
		this.disabled = disabled;
		this.disabledTextColor = disabledTextColor;
	}
	
	public void updateConfig(Graphics2D g){
		g.setFont(font);
		RcsglStringApplications sa = new RcsglStringApplications();
		width = sa.getStringWidth(text, g) + (stringHorOffset * 2);
		height = sa.getStringHeight(g) + stringVertOffset;
		dropDownMenu.setX(x);
		dropDownMenu.setY(y + height);
		dropDownMenu.setWidth(width);
		dropDownMenu.updateConfig(g);
		needToUpdateConfig = false;
	}
	
	public void click(){
		if(!disabled) {
			this.selected = true;
		}
	}
	
	public String sendClick(){
		if(!disabled) {
			return text + " - " + dropDownMenu.click();
		}
		return text + " (DISABLED)";
		
	}
	
	public boolean doesContain(int mousePosX, int mousePosY){
		if(!needToUpdateConfig) {
			Point p = new Point(mousePosX, mousePosY);
			if(getRect().contains(p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean doesSubContain(int mousePosX, int mousePosY){
		if(!disabled) {
			if(!dropDownMenu.doesContain(mousePosX, mousePosY)) {
				return dropDownMenu.doesSubContain(mousePosX, mousePosY);
			}
			return dropDownMenu.doesContain(mousePosX, mousePosY);
		}
		return false;
	}
	
	
	
	private void drawMenuBarOption(Graphics2D g){
		if(needToUpdateConfig) {
			updateConfig(g);
		}
		RcsglStringApplications sa = new RcsglStringApplications();
		int strh = sa.getStringHeight(g, font);
		
		
		if(disabled) {
			g.setColor(disabledTextColor);
		} else if(highlighted && selected) {
			g.setColor(selectedBgColor);
			g.fillRect(x, y, width, height);
			g.setColor(selectedTextColor);
		} else if(selected) {
			g.setColor(selectedBgColor);
			g.fillRect(x, y, width, height);
			g.setColor(selectedTextColor);
		} else if(highlighted) {
			g.setColor(highlightBgColor);
			g.fillRect(x, y, width, height);
			g.setColor(highlightTextColor);
		} else {
			g.setColor(textColor);
		}
		g.setFont(font);
		g.drawString(text, x + stringHorOffset, y + strh);
		g.setColor(outlineColor);
		g.drawRect(x, y, width, height - 1);
		g.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
		
	}
	
	public void draw(Graphics2D g){
		if(needToUpdateConfig) {
			updateConfig(g);
		}
		if(selected) {
			dropDownMenu.draw(g);
		}
		drawMenuBarOption(g);
	}
	
	public void closeMenus(){
		dropDownMenu.closeMenus();
	}
	
	public void update(int mousePosX, int mousePosY){
		if(dropDownMenu.isPendingClick() || highlighted || selected) {
			if(selected) {
				dropDownMenu.update(mousePosX, mousePosY);
			}
		} else {
			dropDownMenu.closeMenus();
			highlighted = false;
			selected = false;
		}
	}
	
	
	public String getText(){
		return text;
	}
	
	
	
	public void setText(String text){
		this.text = text;
		needToUpdateConfig = true;
	}
	
	
	
	public int getX(){
		return x;
	}
	
	
	
	public void setX(int x){
		this.x = x;
		needToUpdateConfig = true;
	}
	
	
	
	public int getY(){
		return y;
	}
	
	
	
	public void setY(int y){
		this.y = y;
		needToUpdateConfig = true;
	}
	
	
	
	public int getStringVertOffset(){
		return stringVertOffset;
	}
	
	
	
	public void setStringVertOffset(int stringVertOffset){
		this.stringVertOffset = stringVertOffset;
		needToUpdateConfig = true;
	}
	
	
	
	public int getStringHorOffset(){
		return stringHorOffset;
	}
	
	
	
	public void setStringHorOffset(int stringHorOffset){
		this.stringHorOffset = stringHorOffset;
		needToUpdateConfig = true;
	}
	
	
	
	public Font getFont(){
		return font;
	}
	
	
	
	public void setFont(Font font){
		this.font = font;
		needToUpdateConfig = true;
	}
	
	
	
	public Color getTextColor(){
		return textColor;
	}
	
	
	
	public void setTextColor(Color textColor){
		this.textColor = textColor;
	}
	
	
	
	public Color getHighlightTextColor(){
		return highlightTextColor;
	}
	
	
	
	public void setHighlightTextColor(Color highlightTextColor){
		this.highlightTextColor = highlightTextColor;
	}
	
	
	
	public Color getSelectedTextColor(){
		return selectedTextColor;
	}
	
	
	
	public void setSelectedTextColor(Color selectedTextColor){
		this.selectedTextColor = selectedTextColor;
	}
	
	
	public Color getHighlightBgColor(){
		return highlightBgColor;
	}
	
	
	
	public void setHighlightBgColor(Color highlightBgColor){
		this.highlightBgColor = highlightBgColor;
	}
	
	
	
	public Color getSelectedBgColor(){
		return selectedBgColor;
	}
	
	
	
	public void setSelectedBgColor(Color selectedBgColor){
		this.selectedBgColor = selectedBgColor;
	}
	
	
	
	public boolean isHighlighted(){
		return highlighted;
	}
	
	
	
	public void setHighlighted(boolean highlighted){
		this.highlighted = highlighted;
	}
	
	
	
	public boolean isSelected(){
		return selected;
	}
	
	
	
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
		needToUpdateConfig = true;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
		needToUpdateConfig = true;
	}
	
	
	public Color getOutlineColor(){
		return outlineColor;
	}
	
	
	public void setOutlineColor(Color outlineColor){
		this.outlineColor = outlineColor;
	}
	
	
	public boolean isNeedToUpdateConfig(){
		return needToUpdateConfig;
	}
	
	
	public void setNeedToUpdateConfig(boolean needToUpdateConfig){
		this.needToUpdateConfig = needToUpdateConfig;
	}
	
	
	public RcsglDropDownMenu getDropDownMenu(){
		return dropDownMenu;
	}
	
	
	public void setDropDownMenu(RcsglDropDownMenu dropDownMenu){
		this.dropDownMenu = dropDownMenu;
	}
	
	public boolean isPendingClick(){
		pendingClick = dropDownMenu.isPendingClick();
		return pendingClick;
	}
	
	public void setPendingClick(boolean b){
		pendingClick = b;
		dropDownMenu.setPendingClick(b);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public boolean isName(String s){
		if(text.equalsIgnoreCase(s)) {
			return true;
		}
		return false;
	}
	
	
	
}
