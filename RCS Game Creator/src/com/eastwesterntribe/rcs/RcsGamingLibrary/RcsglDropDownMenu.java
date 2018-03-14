package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


// A class that creates a rectangular menu for holding menu options
public class RcsglDropDownMenu {
	
	protected int					x;
	protected int					y;
	protected int					width;
	protected int					height;
	public RcsglDropDownOption[]	options;
	protected Color					background;
	protected Color					highlight;
	protected int					vertSpacing;
	protected int					horSpacing;
	protected Rectangle[]			strRecs;
	protected int					hoverPosition;
	public Rectangle				totalArea;
	protected boolean				needToUpdateConfig;
	protected Color					outline;
	protected boolean				pendingClick;
	
	
	public RcsglDropDownMenu(RcsglDropDownOption[] options, Color background, Color highlight, int vertSpacing, int horSpacing, Color outline) {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.background = background;
		this.highlight = highlight;
		this.vertSpacing = vertSpacing;
		this.horSpacing = horSpacing;
		this.hoverPosition = -1;
		this.options = options;
		this.totalArea = new Rectangle(0, 0, 0, 0);
		needToUpdateConfig = true;
		this.outline = outline;
		for (int i = 0; i < options.length; i++) {
			options[i].setParentMenu(this);
			options[i].setNeedToUpdateConfig(true);
		}
	}
	
	public void updateConfig(Graphics2D g){
		RcsglStringApplications strApp = new RcsglStringApplications();
		strRecs = strApp.getRectangularPositionsDropDownOption(options, g, x, y, horSpacing, vertSpacing);
		for (int i = 0; i < options.length; i++) {
			options[i].setX((int) strRecs[i].getX());
			options[i].setY((int) strRecs[i].getY());
			options[i].setAdjWidth((int) strRecs[i].getWidth());
			options[i].setHeight((int) strRecs[i].getHeight());
		}
		Point p = strApp.findAreaLargestDropDownOption(options, g);
		totalArea = new Rectangle(x, y, (int) p.getX(), (int) p.getY());
		this.width = (int) p.getX();
		this.height = (int) p.getY();
		for (int i = 0; i < options.length; i++) {
			options[i].updateConfig(g, strApp);
		}
		needToUpdateConfig = false;
	}
	
	public void closeMenus(){
		for (int i = 0; i < options.length; i++) {
			options[i].setHighlighted(false);
			options[i].closeMenus();
		}
		hoverPosition = -1;
	}
	
	
	public void draw(Graphics2D g){
		
		if(needToUpdateConfig) {
			updateConfig(g);
		}
		
		g.setColor(background);
		g.fillRect(totalArea.x, totalArea.y, totalArea.width, totalArea.height);
		if(hoverPosition > -1) {
			Rectangle r = strRecs[hoverPosition];
			g.setColor(highlight);
			g.fillRect(x, r.y, width, r.height);
		}
		
		for (int i = 0; i < options.length; i++) {
			options[i].draw(g);
		}
		
		g.setColor(outline);
		g.drawRect(totalArea.x, totalArea.y, totalArea.width, totalArea.height);
		g.drawRect(totalArea.x - 1, totalArea.y - 1, totalArea.width + 2, totalArea.height + 2);
		
	}
	
	
	public String click(){
		if(hoverPosition >= 0) {
			return options[hoverPosition].click();
		} else {
			for (int i = 0; i < options.length; i++) {
				if(options[i].isSelected() && options[i].isPendingClick()) {
					return options[i].click();
				}
			}
			return "";
		}
		
	}
	
	//Updated in low level updates
	public void update(int mousePositionX, int mousePositionY){
		
		determineHoverPosition(mousePositionX, mousePositionY);
		doHighlighting(mousePositionX, mousePositionY);
		boolean timeToCloseMenus = !this.doesContain(mousePositionX, mousePositionY);
		
		int subContainCounter = -1;
		if(hoverPosition < 0) {
			for (int i = 0; i < options.length; i++) {
				if(options[i].doesSubContain(mousePositionX, mousePositionY) && options[i].isSelected()) {
					subContainCounter = i;
					timeToCloseMenus = false;
					break;
				} else {
					timeToCloseMenus = true;
				}
			}
		}
		
		if(subContainCounter >= 0 && options[subContainCounter].isSelected()) {
			options[subContainCounter].update(mousePositionX, mousePositionY);
			timeToCloseMenus = false;
		} else if(subContainCounter >= 0 && !options[subContainCounter].isSelected()) {
			timeToCloseMenus = true;
		}
		
		
		if(timeToCloseMenus) {
			closeMenus();
		}
		
		
	}
	
	public void doHighlighting(int mousePosX, int mousePosY){
		if(hoverPosition >= 0 && !options[hoverPosition].isHighlighted()) {
			options[hoverPosition].setHighlighted(true);
			options[hoverPosition].setSelected(true);
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
	
	public void determineHoverPosition(int mousePositionX, int mousePositionY){
		
		if(!needToUpdateConfig) {
			for (int i = 0; i < options.length; i++) {
				Rectangle r = new Rectangle(options[i].x, options[i].y, width, options[i].height);
				if(r.contains(new Point(mousePositionX, mousePositionY))) {
					hoverPosition = i;
					return;
				}
			}
		}
		hoverPosition = -1;
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
	
	
	public Color getBackground(){
		return background;
	}
	
	
	
	public void setBackground(Color background){
		this.background = background;
	}
	
	
	
	public Color getHighlight(){
		return highlight;
	}
	
	
	
	public void setHighlight(Color highlight){
		this.highlight = highlight;
	}
	
	public int getVertSpacing(){
		return vertSpacing;
	}
	
	public void setVertSpacing(int vertSpacing){
		this.vertSpacing = vertSpacing;
		needToUpdateConfig = true;
	}
	
	public int getHorSpacing(){
		return horSpacing;
	}
	
	public void setHorSpacing(int horSpacing){
		this.horSpacing = horSpacing;
		needToUpdateConfig = true;
	}
	
	public int getHoverPosition(){
		return hoverPosition;
	}
	
	public void setHoverPosition(int hoverPosition){
		this.hoverPosition = hoverPosition;
		needToUpdateConfig = true;
	}
	
	public Rectangle[] getStrRecs(){
		return strRecs;
	}
	
	
	public void setStrRecs(Rectangle[] strRecs){
		this.strRecs = strRecs;
		needToUpdateConfig = true;
	}
	
	
	public void setOptions(RcsglDropDownOption[] options){
		this.options = options;
		needToUpdateConfig = true;
	}
	
	public RcsglDropDownOption[] getOptions(){
		return this.options;
	}
	
	
	public Rectangle getTotalArea(){
		return totalArea;
	}
	
	
	public void setTotalArea(Rectangle totalArea){
		this.totalArea = totalArea;
		needToUpdateConfig = true;
	}
	
	
	public boolean isNeedToUpdateConfig(){
		return needToUpdateConfig;
	}
	
	
	public void setNeedToUpdateConfig(boolean needToUpdateConfig){
		this.needToUpdateConfig = needToUpdateConfig;
	}
	
	public boolean isPendingClick(){
		pendingClick = false;
		if(hoverPosition >= 0) {
			pendingClick = true;
		} else {
			for (int i = 0; i < options.length; i++) {
				if(options[i].isPendingClick()) {
					pendingClick = true;
				}
			}
		}
		return pendingClick;
	}
	
	public void setPendingClick(boolean b){
		pendingClick = b;
		for (int i = 0; i < options.length; i++) {
			options[i].setPendingClick(b);
		}
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
		for (int i = 0; i < options.length; i++) {
			if(options[i].doesSubContain(mousePosX, mousePosY)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Rectangle getRect(){
		return new Rectangle(totalArea);
	}
	
}
