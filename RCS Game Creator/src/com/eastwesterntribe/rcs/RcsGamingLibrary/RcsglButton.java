package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class RcsglButton {
	
	/* A clickable button that highlights when hovered over*/
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private String textNormal;
	private String textHighlighted;
	private String textClicked;
	
	private Color bgNormal;
	private Color fgNormal;
	private Color bgHighlighted;
	private Color fgHighlighted;
	private Color bgClicked;
	private Color fgClicked;
	
	private int widthOffset;
	private int heightOffset;
	
	private Font font;
	private Color fontFg;
	private Color fontBg;
	
	private int stringX;
	private int stringY;
	
	private boolean clicked, highlighted;
	
	
	public RcsglButton(int x, int y, int width, int height, String textNormal, Color bgNormal, Color fgNormal, Font font, Color fontFg, Color fontBg, int widthOffset, int heightOffset) {
		//Basic Button (For when you want to just make a quick button)
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textNormal = textNormal;
		this.textClicked = textNormal;
		this.textHighlighted = textNormal;
		this.bgNormal = bgNormal;
		this.fgNormal = fgNormal;
		this.font = font;
		this.widthOffset = widthOffset;
		this.heightOffset = heightOffset;
		this.bgHighlighted = fgNormal;
		this.fgHighlighted = bgNormal;
		this.bgClicked = bgNormal;
		this.fgClicked = bgNormal;
		this.fontFg = fontFg;
		this.fontBg = fontBg;
		init();
	}


	public RcsglButton(int x, int y, int width, int height, String textNormal, String textHighlighted, String textClicked, Color bgNormal, Color fgNormal, Color bgHighlighted, Color fgHighlighted, Color bgClicked, Color fgClicked, Font font, int widthOffset, int heightOffset) {
		//Advanced Button (for when you want to customize everything
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textNormal = textNormal;
		this.textHighlighted = textHighlighted;
		this.textClicked = textClicked;
		this.bgNormal = bgNormal;
		this.fgNormal = fgNormal;
		this.bgHighlighted = bgHighlighted;
		this.fgHighlighted = fgHighlighted;
		this.bgClicked = bgClicked;
		this.fgClicked = fgClicked;
		this.font = font;
		this.widthOffset = widthOffset;
		this.heightOffset = heightOffset;
		this.fontFg = Color.WHITE;
		this.fontBg = Color.BLACK;
		init();
	}
	
	public void init(){
		//Initilizes all necessairy variables
		stringX = 0;
		stringY = 0;
		highlighted = false;
		clicked = false;
	}
	
	public void buttonEvents(int mouseX, int mouseY, boolean click){
		//Changes whether button is highlighted, clicked, or normal.
		if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height){
			if(click){
				clicked = true;
				highlighted = false;
			}else{
				clicked = false;
				highlighted = true;
			}
		}else{
			highlighted = false;
			clicked = false;
		}
	}
	
	public void draw(Graphics2D g){
		//Draws the button to the screen.
		g.setFont(font);
		if(highlighted){
			g.setColor(bgHighlighted);
			g.fillRect(x, y, width, height);
			g.setColor(fgHighlighted);
			g.fillRect(x + widthOffset, y + heightOffset, width - (widthOffset * 2), height - (heightOffset * 2));
			findStringCenter(g, textHighlighted);
			drawStringOperations(g, textHighlighted);
			g.drawString(textHighlighted, stringX, stringY);
		}else if(clicked){
			g.setColor(bgClicked);
			g.fillRect(x, y, width, height);
			g.setColor(fgClicked);
			g.fillRect(x + widthOffset, y + heightOffset, width - (widthOffset * 2), height - (heightOffset * 2));
			findStringCenter(g, textClicked);
			drawStringOperations(g, textClicked);
			g.drawString(textClicked, stringX, stringY);
		}else{
			g.setColor(bgNormal);
			g.fillRect(x, y, width, height);
			g.setColor(fgNormal);
			g.fillRect(x + widthOffset, y + heightOffset, width - (widthOffset * 2), height - (heightOffset * 2));
			findStringCenter(g, textNormal);
			drawStringOperations(g, textNormal);
			g.drawString(textNormal, stringX, stringY);
		}
	}
	
	public void findStringCenter(Graphics2D g, String s){
		//Centers String
		FontMetrics fm = g.getFontMetrics();
		int sw = fm.stringWidth(s);
		int sh = fm.getHeight();
		
		stringX = ((width - sw)/2) + x;
		stringY = y + ((height / 2) + (sh/3));
	}
	
	public void drawStringOperations(Graphics2D g, String text){
		//Gives String Outlines For Easier Reading
		g.setColor(fontBg);
		g.drawString(text, stringX - 1, stringY -1);
		g.drawString(text, stringX - 1, stringY + 1);
		g.drawString(text, stringX + 1, stringY - 1);
		g.drawString(text, stringX + 1, stringY + 1);
		g.drawString(text, stringX - 2, stringY -2);
		g.drawString(text, stringX - 2, stringY + 2);
		g.drawString(text, stringX + 2, stringY - 2);
		g.drawString(text, stringX + 2, stringY + 2);
		g.setColor(fontFg);
	}
	
	
	
	//Getters and Setters
	public Color getFontFg() {
		return fontFg;
	}


	public void setFontFg(Color fontFg) {
		this.fontFg = fontFg;
	}


	public Color getFontBg() {
		return fontBg;
	}


	public void setFontBg(Color fontBg) {
		this.fontBg = fontBg;
	}


	public int getWidthOffset() {
		return widthOffset;
	}


	public void setWidthOffset(int widthOffset) {
		this.widthOffset = widthOffset;
	}


	public int getHeightOffset() {
		return heightOffset;
	}


	public void setHeightOffset(int heightOffset) {
		this.heightOffset = heightOffset;
	}


	public boolean isClicked() {
		return clicked;
	}


	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}


	public boolean isHighlighted() {
		return highlighted;
	}


	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}


	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getTextNormal() {
		return textNormal;
	}
	public void setTextNormal(String textNormal) {
		this.textNormal = textNormal;
	}
	public String getTextHighlighted() {
		return textHighlighted;
	}
	public void setTextHighlighted(String textHighlighted) {
		this.textHighlighted = textHighlighted;
	}
	public String getTextClicked() {
		return textClicked;
	}
	public void setTextClicked(String textClicked) {
		this.textClicked = textClicked;
	}
	public Color getBgNormal() {
		return bgNormal;
	}
	public void setBgNormal(Color bgNormal) {
		this.bgNormal = bgNormal;
	}
	public Color getFgNormal() {
		return fgNormal;
	}
	public void setFgNormal(Color fgNormal) {
		this.fgNormal = fgNormal;
	}
	public Color getBgHighlighted() {
		return bgHighlighted;
	}
	public void setBgHighlighted(Color bgHighlighted) {
		this.bgHighlighted = bgHighlighted;
	}
	public Color getFgHighlighted() {
		return fgHighlighted;
	}
	public void setFgHighlighted(Color fgHighlighted) {
		this.fgHighlighted = fgHighlighted;
	}
	public Color getBgClicked() {
		return bgClicked;
	}
	public void setBgClicked(Color bgClicked) {
		this.bgClicked = bgClicked;
	}
	public Color getFgClicked() {
		return fgClicked;
	}
	public void setFgClicked(Color fgClicked) {
		this.fgClicked = fgClicked;
	}

}
