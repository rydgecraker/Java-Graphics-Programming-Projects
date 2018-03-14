package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;


public class RcsglTextBox {
	
	private int										x, y, width, height, cursorXLocation, cursorWidth, cursorLocationOffset, numCharactersOffset,
			cursorInputLocation, textWidth;
	private Font									font;
	private ArrayList<RcsglStringWithCoordinates>	characters;
	private RcsglStringWithCoordinates				text, emptyText;
	private Color									textColor, emptyTextColor, bgColor, highlightBgColor, highlightTextColor, cursorColor, cursorColorFlash;
	private boolean									flashing, empty, focused, needToUpdateConfig, showCursor, currentlyUpdatingConfig,
			pendingNeedToUpdateConfig;
	private boolean[]								charHighlighted;
	private Rectangle								highlightedArea;
	
	public RcsglTextBox(int x, int y, int width, Font font, String emptyText, Color textColor, Color emptyTextColor, Color bgColor, Color highlightBgColor, Color highlightTextColor, Color cursorColor, Color cursorColorFlash, int cursorWidth) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.font = font;
		setCursorWidth(cursorWidth);
		this.emptyText = new RcsglStringWithCoordinates(emptyText);
		this.textColor = textColor;
		this.emptyTextColor = emptyTextColor;
		this.bgColor = bgColor;
		this.highlightBgColor = highlightBgColor;
		this.highlightTextColor = highlightTextColor;
		this.cursorColor = cursorColor;
		this.cursorColorFlash = cursorColorFlash;
		init();
	}
	
	private void init(){
		needToUpdateConfig = true;
		height = 0;
		cursorXLocation = 0;
		cursorLocationOffset = 0;
		cursorInputLocation = 0;
		textWidth = 0;
		characters = new ArrayList<RcsglStringWithCoordinates>();
		charHighlighted = new boolean[] { true };
		text = new RcsglStringWithCoordinates("");
		highlightedArea = new Rectangle(0, 0, 0, 0);
		flashing = false;
		empty = true;
		focused = false;
		showCursor = false;
		currentlyUpdatingConfig = false;
		pendingNeedToUpdateConfig = false;
	}
	
	private synchronized void updateConfig(Graphics2D g, RcsglStringApplications stringApplication){
		currentlyUpdatingConfig = true;
		needToUpdateConfig = false;
		height = stringApplication.getStringHeight(g, font);
		textWidth = stringApplication.getStringWidth(text.text, g, font);
		
		characters.clear();
		numCharactersOffset = 0;
		for (int i = 0; i < text.text.length(); i++) {
			characters.add(new RcsglStringWithCoordinates(text.text.substring(i, i + 1)));
			characters.get(i).x = stringApplication.getStringWidth(characters.get(i).text, g, font);
		}
		if(charHighlighted.length != characters.size()) {
			charHighlighted = new boolean[characters.size()];
		}
		
		emptyText.x = stringApplication.centerStringX(emptyText.text, g, width, 0) + x;
		emptyText.y = y + height;
		text.x = stringApplication.centerStringX(text.text, g, width, 0) + x;
		text.y = y + height;
		cursorXLocation = getCursorCharLoc(g, stringApplication);
		
		currentlyUpdatingConfig = false;
	}
	
	private int getCursorCharLoc(Graphics2D g, RcsglStringApplications stringApplication){
		if(cursorLocationOffset > 0) {
			cursorLocationOffset = 0;
			cursorInputLocation = characters.size();
			return textWidth + text.x;
		} else if((cursorLocationOffset * -1) > characters.size()) {
			cursorLocationOffset = -1 * characters.size();
			cursorInputLocation = 0;
		}
		
		if(cursorLocationOffset == -1 * characters.size()) {
			return text.x;
		} else {
			int widthBack = 0;
			for (int i = characters.size() - 1; i > (cursorLocationOffset * -1); i--) {
				widthBack += characters.get(i).x;
			}
			cursorInputLocation = characters.size() + cursorLocationOffset;
			return text.x + textWidth - widthBack;
			
		}
	}
	
	public Rectangle getCursorRect(){
		return new Rectangle(cursorXLocation - ((cursorWidth - 1) / 2), y, 1 + ((cursorWidth - 1) / 2), height);
	}
	
	
	public void draw(Graphics2D g, RcsglStringApplications stringApplication){
		if(needToUpdateConfig) {
			updateConfig(g, stringApplication);
		}
		
		g.setColor(bgColor);
		g.fill(getRect());
		g.setColor(Color.BLACK);
		g.drawRect(x - 1, y - 1, width + 2, height + 2);
		if(empty && !focused) {
			g.setFont(font);
			g.setColor(emptyTextColor);
			g.drawString(emptyText.text, emptyText.x, emptyText.y);
		}
		
		if(!characters.isEmpty() && !currentlyUpdatingConfig && !needToUpdateConfig && !pendingNeedToUpdateConfig) {
			int previousWidth = 0;
			for (int i = 0; i < characters.size(); i++) {
				if(!currentlyUpdatingConfig && !needToUpdateConfig && !pendingNeedToUpdateConfig) {
					if(charHighlighted[i]) {
						g.setColor(highlightBgColor);
						g.fillRect(text.x + previousWidth, y + height, text.x + previousWidth + characters.get(i).x, height);
						g.setColor(highlightTextColor);
						g.drawString(characters.get(i).text, text.x + previousWidth + characters.get(i).x, y + height);
					} else {
						g.setColor(textColor);
						g.drawString(characters.get(i).text, text.x + previousWidth + characters.get(i).x, y + height);
					}
					previousWidth += characters.get(i).x;
				}
			}
		}
		
		
		
		if(showCursor) {
			if(flashing) {
				g.setColor(cursorColorFlash);
				
			} else {
				g.setColor(cursorColor);
			}
			g.fill(getCursorRect());
		}
		
		
		
	}
	
	/**
	 * @param left
	 *            A boolean that determines which direction to move the cursor<br>
	 *            True is left, false is right.
	 */
	public void moveCursorPosition(boolean left){
		if(focused && !empty) {
			pendingNeedToUpdateConfig = true;
			if(left) {
				if((cursorLocationOffset * -1) + 1 >= characters.size()) {
					return;
				}
				cursorLocationOffset -= 1;
			} else {
				if((cursorLocationOffset * -1) <= 0) {
					return;
				}
				cursorLocationOffset += 1;
			}
		}
		needToUpdateConfig = true;
		pendingNeedToUpdateConfig = false;
	}
	
	public void addCharacter(char c){
		if(focused) {
			pendingNeedToUpdateConfig = true;
			if(cursorInputLocation == characters.size()) {
				text.text = text.text + c;
				characters.add(new RcsglStringWithCoordinates(c + ""));
				cursorLocationOffset++;
			} else {
				ArrayList<RcsglStringWithCoordinates> chs = new ArrayList<RcsglStringWithCoordinates>();
				chs.addAll(characters);
				chs.add(cursorInputLocation, new RcsglStringWithCoordinates(c + ""));
				String s = "";
				for (int i = 0; i < chs.size(); i++) {
					s += chs.get(i).text;
				}
				text.text = s;
			}
			needToUpdateConfig = true;
			pendingNeedToUpdateConfig = false;
		}
	}
	
	public void delete(){
		if(focused && !empty) {
			pendingNeedToUpdateConfig = true;
			ArrayList<RcsglStringWithCoordinates> chs = new ArrayList<RcsglStringWithCoordinates>();
			chs.addAll(characters);
			if(cursorInputLocation < (chs.size() - 1) && showCursor) {
				chs.remove(cursorInputLocation + 1);
				String s = "";
				if(chs.isEmpty()) {
					empty = true;
				} else {
					for (int i = 0; i < chs.size(); i++) {
						s += chs.get(i).text;
					}
				}
				text.text = s;
				needToUpdateConfig = true;
				pendingNeedToUpdateConfig = false;
			} else if(!showCursor) {
				boolean firstSwitch = true;
				int offsetter = 0;
				for (int i = 0; i < charHighlighted.length; i++) {
					if(charHighlighted[i + offsetter]) {
						if(firstSwitch) {
							cursorLocationOffset = (chs.size() - i) * -1;
							firstSwitch = false;
						}
						chs.remove(i);
						charHighlighted[i + offsetter] = false;
						i--;
						offsetter++;
					}
				}
				
				String s = "";
				if(chs.isEmpty()) {
					empty = true;
				} else {
					for (int i = 0; i < chs.size(); i++) {
						s += chs.get(i).text;
					}
				}
				text.text = s;
				needToUpdateConfig = true;
				pendingNeedToUpdateConfig = false;
			}
		}
	}
	
	public void backspace(){
		if(focused && !empty) {
			pendingNeedToUpdateConfig = true;
			ArrayList<RcsglStringWithCoordinates> chs = new ArrayList<RcsglStringWithCoordinates>();
			chs.addAll(characters);
			if(cursorInputLocation != 0 && showCursor) {
				chs.remove(cursorInputLocation);
				cursorLocationOffset++;
				String s = "";
				if(chs.isEmpty()) {
					empty = true;
				} else {
					for (int i = 0; i < chs.size(); i++) {
						s += chs.get(i).text;
					}
				}
				text.text = s;
				needToUpdateConfig = true;
				pendingNeedToUpdateConfig = false;
			} else if(!showCursor) {
				boolean firstSwitch = true;
				int offsetter = 0;
				for (int i = 0; i < charHighlighted.length; i++) {
					if(charHighlighted[i + offsetter]) {
						if(firstSwitch) {
							cursorLocationOffset = (chs.size() - i) * -1;
							firstSwitch = false;
						}
						chs.remove(i);
						charHighlighted[i + offsetter] = false;
						i--;
						offsetter++;
					}
				}
				
				String s = "";
				if(chs.isEmpty()) {
					empty = true;
				} else {
					for (int i = 0; i < chs.size(); i++) {
						s += chs.get(i).text;
					}
				}
				text.text = s;
				needToUpdateConfig = true;
				pendingNeedToUpdateConfig = false;
				
			}
		}
	}
	
	public void click(int mouseX, int mouseY){
		pendingNeedToUpdateConfig = true;
		Point p = new Point(mouseX, mouseY);
		setAllHighlighted(false);
		highlightedArea = new Rectangle(0, 0, 0, 0);
		cursorLocationOffset = 0;
		if(getRect().contains(p)) {
			showCursor = true;
			focused = true;
			if(!empty) {
				if(mouseX <= text.x + (characters.get(0).x / 2)) {
					//Less than text but still in box
					cursorLocationOffset = characters.size() * -1;
				} else if(mouseX >= text.x + textWidth - (characters.get(characters.size() - 1).x / 2)) {
					//Greater than text but still in box
					cursorLocationOffset = 0;
				} else {
					//Inside Text
					int xDisFromTextStrt = mouseX - text.x;
					int charFPosCounter = 0;
					int tempWidthFCoutner = 0;
					for (int i = 0; i < characters.size(); i++) {
						if(xDisFromTextStrt <= tempWidthFCoutner + (characters.get(i).x / 2)) {
							break;
						}
						tempWidthFCoutner += characters.get(i).x;
						charFPosCounter++;
					}
					
					cursorLocationOffset = (characters.size() - charFPosCounter) * -1;
				}
			}
		} else {
			focused = false;
			showCursor = false;
		}
		
		needToUpdateConfig = true;
		pendingNeedToUpdateConfig = false;
	}
	
	
	
	public void dragMouse(int xStart, int yStart, int xFin){
		
		if(!currentlyUpdatingConfig) {
			Point start = new Point(xStart, yStart);
			setAllHighlighted(false);
			showCursor = true;
			highlightedArea = new Rectangle(0, 0, 0, 0);
			if(focused && !empty) {
				if(getRect().contains(start)) {
					pendingNeedToUpdateConfig = true;
					if(xStart < text.x + textWidth - (characters.get(characters.size() - 1).x / 2)) {
						if(xStart <= text.x + (characters.get(0).x / 2)) {
							//Before text but still in box
							if(xFin <= text.x + (characters.get(0).x / 2)) {
								needToUpdateConfig = true;
								pendingNeedToUpdateConfig = false;
								return;
							}
							showCursor = false;
							int tempWidth = text.x;
							int highlightPosition = 0;
							for (int i = 0; i < characters.size(); i++) {
								if(xFin <= tempWidth + (characters.get(i).x / 2)) {
									break;
								}
								highlightPosition++;
								tempWidth += characters.get(i).x;
							}
							
							for (int i = 0; i <= highlightPosition; i++) {
								charHighlighted[i] = true;
							}
							highlightedArea = new Rectangle(text.x, y, tempWidth, height);
							cursorLocationOffset = characters.size() * -1;
							needToUpdateConfig = true;
							pendingNeedToUpdateConfig = false;
							return;
						} else {
							//Somwhere inside text
							int xDisFromTextStrt = xStart - text.x;
							int charSPosCounter = 0;
							int tempWidthSCoutner = 0;
							for (int i = 0; i < characters.size(); i++) {
								if(xDisFromTextStrt <= tempWidthSCoutner + (characters.get(i).x / 2)) {
									break;
								}
								tempWidthSCoutner += characters.get(i).x;
								charSPosCounter++;
							}
							
							if(xFin < xStart) {
								int tempLoc = xStart - (characters.get(charSPosCounter - 1).x / 2);
								if(xFin >= tempLoc) {
									needToUpdateConfig = true;
									pendingNeedToUpdateConfig = false;
									return;
								}
								
								xDisFromTextStrt = xFin - text.x;
								int charFPosCounter = 0;
								int tempWidthFCoutner = 0;
								for (int i = 0; i < characters.size(); i++) {
									if(xDisFromTextStrt <= tempWidthFCoutner + (characters.get(i).x / 2)) {
										break;
									}
									tempWidthFCoutner += characters.get(i).x;
									charFPosCounter++;
								}
								
								for (int i = charFPosCounter; i < charSPosCounter; i++) {
									charHighlighted[i] = true;
								}
								highlightedArea = new Rectangle(text.x + tempWidthFCoutner, y, tempWidthSCoutner - tempWidthFCoutner, height);
								cursorLocationOffset = (characters.size() - charFPosCounter) * -1;
								needToUpdateConfig = true;
								pendingNeedToUpdateConfig = false;
								return;
								
							} else if(xFin == xStart) {
								return;
							} else if(xFin > xStart) {
								int tempLoc = xStart + (characters.get(charSPosCounter).x / 2);
								if(xFin < tempLoc) {
									needToUpdateConfig = true;
									pendingNeedToUpdateConfig = false;
									return;
								}
								
								xDisFromTextStrt = xFin - text.x;
								int charFPosCounter = 0;
								int tempWidthFCoutner = 0;
								for (int i = 0; i < characters.size(); i++) {
									if(xDisFromTextStrt <= tempWidthFCoutner + (characters.get(i).x / 2)) {
										break;
									}
									tempWidthFCoutner += characters.get(i).x;
									charFPosCounter++;
								}
								
								for (int i = charSPosCounter; i < charFPosCounter; i++) {
									charHighlighted[i] = true;
								}
								highlightedArea = new Rectangle(text.x + tempWidthSCoutner, y, tempWidthFCoutner - tempWidthSCoutner, height);
								cursorLocationOffset = (characters.size() - charSPosCounter) * -1;
								needToUpdateConfig = true;
								pendingNeedToUpdateConfig = false;
								return;
							}
						}
					} else {
						//Greater than text but still in box
						if(xFin >= xStart) {
							needToUpdateConfig = true;
							pendingNeedToUpdateConfig = false;
							return;
						} else if(xFin >= text.x + textWidth) {
							needToUpdateConfig = true;
							pendingNeedToUpdateConfig = false;
							return;
						} else {
							int xDisFromTextStart = xFin - text.x;
							int charPosCounter = 0;
							int tempWidthCounter = 0;
							for (int i = 0; i < characters.size(); i++) {
								if(xDisFromTextStart <= tempWidthCounter + (characters.get(i).x / 2)) {
									break;
								}
								tempWidthCounter += characters.get(i).x;
								charPosCounter++;
							}
							
							
							for (int i = charPosCounter; i <= characters.size(); i++) {
								charHighlighted[i] = true;
							}
							highlightedArea = new Rectangle(text.x + tempWidthCounter, y, textWidth - tempWidthCounter, height);
							cursorLocationOffset = (characters.size() - charPosCounter) * -1;
							needToUpdateConfig = true;
							pendingNeedToUpdateConfig = false;
							return;
						}
					}
				} else {
					return;
				}
			} else {
				return;
			}
		}
	}
	
	private void setAllHighlighted(boolean b){
		for (int i = 0; i < charHighlighted.length; i++) {
			charHighlighted[i] = b;
		}
	}
	
	public Rectangle getStringRect(){
		return new Rectangle(text.x, text.y, textWidth, height);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
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
	
	
	public int getWidth(){
		return width;
	}
	
	
	public void setWidth(int width){
		this.width = width;
	}
	
	
	public int getHeight(){
		return height;
	}
	
	
	public void setHeight(int height){
		this.height = height;
	}
	
	
	public int getCursorXLocation(){
		return cursorXLocation;
	}
	
	
	public void setCursorXLocation(int cursorXLocation){
		this.cursorXLocation = cursorXLocation;
	}
	
	
	public Font getFont(){
		return font;
	}
	
	
	public void setFont(Font font){
		this.font = font;
	}
	
	
	public ArrayList<RcsglStringWithCoordinates> getCharacters(){
		return characters;
	}
	
	
	public void setCharacters(ArrayList<RcsglStringWithCoordinates> characters){
		this.characters = characters;
	}
	
	
	public RcsglStringWithCoordinates getText(){
		return text;
	}
	
	
	public void setText(RcsglStringWithCoordinates text){
		this.text = text;
	}
	
	
	public RcsglStringWithCoordinates getEmptyText(){
		return emptyText;
	}
	
	
	public void setEmptyText(RcsglStringWithCoordinates emptyText){
		this.emptyText = emptyText;
	}
	
	
	public Color getTextColor(){
		return textColor;
	}
	
	
	public void setTextColor(Color textColor){
		this.textColor = textColor;
	}
	
	
	public Color getBgColor(){
		return bgColor;
	}
	
	
	public void setBgColor(Color bgColor){
		this.bgColor = bgColor;
	}
	
	
	public Color getHighlightBgColor(){
		return highlightBgColor;
	}
	
	
	public void setHighlightBgColor(Color highlightBgColor){
		this.highlightBgColor = highlightBgColor;
	}
	
	
	public Color getHighlightTextColor(){
		return highlightTextColor;
	}
	
	
	public void setHighlightTextColor(Color highlightTextColor){
		this.highlightTextColor = highlightTextColor;
	}
	
	
	public Color getCursorColor(){
		return cursorColor;
	}
	
	
	public void setCursorColor(Color cursorColor){
		this.cursorColor = cursorColor;
	}
	
	
	public Color getCursorColorFlash(){
		return cursorColorFlash;
	}
	
	
	public void setCursorColorFlash(Color cursorColorFlash){
		this.cursorColorFlash = cursorColorFlash;
	}
	
	
	public boolean isFlashing(){
		return flashing;
	}
	
	
	public void setFlashing(boolean flashing){
		this.flashing = flashing;
	}
	
	
	public boolean isEmpty(){
		return empty;
	}
	
	
	public void setEmpty(boolean empty){
		this.empty = empty;
	}
	
	
	public boolean isFocused(){
		return focused;
	}
	
	
	public void setFocused(boolean focused){
		this.focused = focused;
	}
	
	public void setneedToUpdateConfig(boolean needToUpdateConfig){
		this.needToUpdateConfig = needToUpdateConfig;
	}
	
	public boolean needToUpdateConfig(boolean focused){
		return needToUpdateConfig;
	}
	
	public void setCursorWidth(int w){
		if(w % 2 == 0 && w > 1) {
			cursorWidth = w - 1;
			return;
		} else if(w > 1) {
			cursorWidth = w;
			return;
		}
		cursorWidth = 1;
	}
	
	public int getCursorWidth(){
		return cursorWidth;
	}
	
	public boolean[] getCharHighlighted(){
		return charHighlighted;
	}
}
