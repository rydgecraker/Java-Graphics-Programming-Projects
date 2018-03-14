package com.eastwesterntribe.rcs.RcsGamingLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class RcsglStringApplications {
	
	private FontMetrics	fm;
	
	/**
	 * Returns a point that, when drawn, will center the screen inside the box given, provided
	 * origin is 0,0
	 * 
	 * @param s
	 *            - The given string
	 * @param g
	 *            - Graphics device
	 * @param width
	 *            - width of the area you want to center the string in
	 * @param height
	 *            - height of the area you want to center the string in
	 * @return
	 */
	public Point centerString(String s, Graphics2D g, int width, int height){
		FontMetrics fm = g.getFontMetrics();
		int sw = fm.stringWidth(s);
		int sh = fm.getHeight();
		
		int stringX = ((width / 2) - (sw / 2));
		int stringY = ((height / 2) + (sh / 3));
		
		
		return new Point(stringX, stringY);
	}
	
	public int centerStringY(Graphics2D g, int height, int offset){
		FontMetrics fm = g.getFontMetrics();
		int h = fm.getHeight();
		
		return (((height - h) / 2) - offset) + h;
	}
	
	public RcsglStringWithCoordinates positionStringInBottomRightCorner(RcsglStringWithCoordinates s, Graphics2D g, Font f, Rectangle area, int xOffset, int yOffset){
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics();
		s.x = (((area.x + area.width) - (fm.stringWidth(s.text))) + xOffset);
		s.y = (area.y + area.height) + yOffset;
		return s;
	}
	
	public int centerStringX(String s, Graphics2D g, int width, int offset){
		FontMetrics fm = g.getFontMetrics();
		int sw = fm.stringWidth(s);
		
		return ((width - sw) / 2) - offset;
	}
	
	public int getStringWidth(String s, Graphics2D g, Font f){
		g.setFont(f);
		return getStringWidth(s, g);
	}
	
	public int getStringWidth(String s, Graphics2D g){
		fm = g.getFontMetrics();
		return fm.stringWidth(s);
	}
	
	public int getStringHeight(Graphics2D g){
		fm = g.getFontMetrics();
		return fm.getHeight();
	}
	
	public int getStringHeight(Graphics2D g, Font f){
		g.setFont(f);
		return g.getFontMetrics(f).getHeight();
	}
	
	public void centerOutlineAndDrawString(String s, Graphics2D g, Font f, int y, int outlineX, int outlineY, Color outlineC, Color textC, int width){
		int x = centerStringX(s, g, width, outlineX);
		outlineAndDrawString(s, g, f, x, y, outlineX, outlineY, outlineC, textC);
	}
	
	public void outlineAndDrawString(String s, Graphics2D g, Font f, int x, int y, int outlineX, int outlineY, Color outline, Color text){
		g.setFont(f);
		g.setColor(outline);
		g.drawString(s, x - outlineX, y - outlineY);
		g.drawString(s, x - outlineX, y + outlineY);
		g.drawString(s, x + outlineX, y - outlineY);
		g.drawString(s, x + outlineX, y + outlineY);
		g.drawString(s, x, y + outlineY);
		g.drawString(s, x, y - outlineY);
		g.drawString(s, x + outlineX, y);
		g.drawString(s, x - outlineX, y);
		
		g.setColor(text);
		g.drawString(s, x, y);
	}
	
	public Point[] getVerticalDropDownOptionPoints(RcsglDropDownOption[] options, Graphics2D g, int verticalSpacing, int horizontalSpacing, int startingX, int startingY){
		Point[] points = new Point[options.length + 1];
		for (int i = 0; i < options.length; i++) {
			g.setFont(options[i].getFont());
			int height = getStringHeight(g);
			points[i] = new Point((startingX + horizontalSpacing), (startingY + (verticalSpacing * i)) + (height * i));
		}
		
		//The last point is how large the rectangle of strings is
		int twi = getStringWidth(options[findLongestString(options, g)].getText(), g);
		int totalWidth = (twi + (horizontalSpacing * 2));
		int totalHeight = findTotalHeight(options, g, verticalSpacing);
		points[options.length] = new Point(totalWidth, totalHeight);
		
		return points;
	}
	
	
	public int findTotalHeight(RcsglDropDownOption[] options, Graphics2D g, int verticalSpacing){
		int height = 0;
		for (int i = 0; i < options.length; i++) {
			height += (getStringHeight(g, options[i].getFont()) + verticalSpacing);
		}
		return height;
	}
	
	public Point[] getVerticalStringPoints(String[] strings, Graphics2D g, int verticalSpacing, int horizontalSpacing, int startingX, int startingY){
		int height = getStringHeight(g);
		Point[] points = new Point[strings.length + 1];
		for (int i = 0; i < strings.length; i++) {
			points[i] = new Point((startingX + (horizontalSpacing * i)), (startingY + (verticalSpacing * i)) + (height * i));
		}
		
		//The last point is how large the rectangle of strings is
		int totalWidth = (findLongestString(strings, g) + (horizontalSpacing * 2));
		int totalHeight = ((height * strings.length) + (verticalSpacing * strings.length));
		points[strings.length] = new Point(totalWidth, totalHeight);
		
		return points;
	}
	
	public Point findAreaLargestDropDownOption(RcsglDropDownOption[] ddo, Graphics2D g){
		int width = 0;
		int height = 0;
		int tempW = 0;
		int tempH = 0;
		for (int i = 0; i < ddo.length; i++) {
			tempW = ddo[i].getAdjWidth();
			if(width < tempW) {
				width = tempW;
			}
			tempH = ddo[i].getHeight();
			if(height < tempH) {
				height = tempH;
			}
		}
		
		height = height * ddo.length;
		
		return new Point(width, height);
	}
	
	public int getCharWidth(char c, Graphics2D g, Font f){
		g.setFont(f);
		fm = g.getFontMetrics(f);
		return fm.charWidth(c);
	}
	
	public Rectangle[] getRectangularPositionsDropDownOption(RcsglDropDownOption[] ddo, Graphics2D g, int xStart, int yStart, int horSpacing, int vertSpacing){
		Rectangle[] recs = new Rectangle[ddo.length];
		for (int i = 0; i < ddo.length; i++) {
			int h = getStringHeight(g, ddo[i].getFont());
			recs[i] = new Rectangle((xStart), (yStart + (h * i) + (vertSpacing * (i))), ddo[i].getWidth(this, g) + (horSpacing * 2), h + (vertSpacing));
		}
		
		return recs;
	}
	
	public int findLongestString(RcsglDropDownOption[] options, Graphics2D g){
		String[] strings = new String[options.length];
		for (int i = 0; i < options.length; i++) {
			strings[i] = options[i].getText();
		}
		return findLongestString(strings, g);
	}
	
	//returns the position of the longest string in an array
	public int findLongestString(String[] strings, Graphics2D g){
		//In the event of a tie, the first string is the one that's returned
		int position = 0;
		int length = 0;
		int tempLength = 0;
		for (int i = 0; i < strings.length; i++) {
			tempLength = getStringWidth(strings[i], g);
			if(tempLength > length) {
				length = tempLength;
				position = i;
			}
		}
		
		return position;
	}
	
	public int alignAndDrawStringsVertically(String[] strings, Color[] colors, int x, int y, int spacing, Graphics2D g){
		int height = getStringHeight(g);
		for (int i = 0; i < strings.length; i++) {
			g.setColor(colors[i]);
			if(strings[i] != null) {
				g.drawString(strings[i], x, y + (height * i) + (spacing * i));
			}
		}
		
		int totalHeight = x + (height * strings.length) + (spacing * strings.length - 1);
		return totalHeight;
	}
	
}
