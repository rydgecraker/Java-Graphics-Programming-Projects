package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class RcsglDropDownOption {
	
	//For the options put into a DropDownMenu
	
	protected String			text;
	protected int				x;
	protected int				y;
	protected Color				color;
	protected Color				hightlightColor;
	protected Color				disabledTextColor;
	protected Font				font;
	protected boolean			highlighted;
	protected boolean			needToUpdateConfig;
	protected boolean			pendingClick;
	protected boolean			selected;
	protected boolean			disabled;
	
	protected int				width;
	protected int				height;
	protected RcsglDropDownMenu	parentMenu;
	
	
	
	public RcsglDropDownOption(String text, Color color, Color hightlightColor, Font font, boolean disabled, Color disabledTextColor) {
		this.text = text;
		this.x = 0;
		this.y = 0;
		this.color = color;
		this.hightlightColor = hightlightColor;
		this.font = font;
		this.pendingClick = false;
		this.disabled = disabled;
		this.disabledTextColor = disabledTextColor;
	}
	
	public void updateConfig(Graphics2D g, RcsglStringApplications sa){
		
	}
	
	public String click(){
		if(disabled) {
			return text + " (DISABLED)";
		}
		return text;
	}
	
	public void draw(Graphics2D g){
		RcsglStringApplications sa = new RcsglStringApplications();
		int strh = sa.getStringHeight(g, font);
		if(disabled) {
			g.setColor(disabledTextColor);
		} else if(highlighted) {
			g.setColor(hightlightColor);
		} else {
			g.setColor(color);
		}
		g.setFont(font);
		g.drawString(text, x + parentMenu.horSpacing, y + strh);
	}
	
	public void resetLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void resetLocation(Point p){
		this.x = (int) p.getX();
		this.y = (int) p.getY();
	}
	
	public boolean isHighlighted(){
		return highlighted;
	}
	
	
	public void setHighlighted(boolean highlighted){
		this.highlighted = highlighted;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getHightlightColor(){
		return hightlightColor;
	}
	
	public void setHightlightColor(Color hightlightColor){
		this.hightlightColor = hightlightColor;
	}
	
	public Font getFont(){
		return font;
	}
	
	public void setFont(Font font){
		this.font = font;
	}
	
	public void closeMenus(){
		
	}
	
	public boolean getNeedToUpdateConfig(){
		return needToUpdateConfig;
	}
	
	public void setNeedToUpdateConfig(boolean b){
		needToUpdateConfig = b;
	}
	
	public int getWidth(RcsglStringApplications sa, Graphics2D g){
		return sa.getStringWidth(text, g, font);
	}
	
	public void update(int mousePositionX, int mousePositionY){
		
	}
	
	public boolean isPendingClick(){
		return pendingClick;
	}
	
	public void setPendingClick(boolean b){
		pendingClick = b;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void setSelected(boolean b){
		selected = b;
	}
	
	public void setParentMenu(RcsglDropDownMenu menu){
		parentMenu = menu;
	}
	
	public boolean doesSubContain(int mousePosX, int mousePosY){
		return false;
	}
	
	public boolean doesSubMenuContain(int mousePosX, int mousePosY){
		return false;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public int getAdjWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setAdjWidth(int x){
		width = x;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public boolean isName(String s){
		if(text.equalsIgnoreCase(s)) {
			return true;
		}
		return false;
	}
	
	
	
}
