package testingPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Button extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color bg;
	private Color fg;
	private Color textColor;
	private String text;
	private Font font;
	private int sizePercentage;
	private int stringX;
	private int stringY;
	
	
	public Button(int x, int y, int width, int height, Color bg, Color fg, Color textColor, String text, Font font){
		this.x = x;
		this.y = y;
		this.setLocation(this.x, this.y);
		this.width = width;
		this.height = height;
		this.setSize(this.width, this.height);
		this.bg = bg;
		this.fg = fg;
		this.textColor = textColor;
		this.setBackground(Color.BLACK);
		this.text = text;
		this.font = font;
		this.setFocusable(true);
		this.requestFocus();
		
		
	}
	
	public void setX(int x){
		this.x = x;
		this.setLocation(x, y);
		this.revalidate();
	}
	
	public void setY(int y){
		this.y = y;
		this.setLocation(x,  y);
		this.revalidate();
	}
	
	public void setWidth(int width){
		this.width = width;
		this.setSize(width, height);
		this.revalidate();
	}
	
	public void setHeight(int height){
		this.height = height;
		this.setSize(width, height);
		this.revalidate();
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public String toString(){
		return text;
	}
	
	public void setBackgroundColor(Color bg){
		this.bg = bg;
		this.setBackground(this.bg);
		this.revalidate();
	}
	
	public Color getBackgroundColor(){
		return bg;
	}
	
	public void setForegroundColor(Color fg){
		this.fg = fg;
	}
	
	public Color getForegroundColor(){
		return fg;
	}
	
	public void textColor(Color textColor){
		this.textColor = textColor;
	}
	
	public Color getTextColor(){
		return textColor;
	}
	
	public void setTextFont(Font font){
		this.font = font;
	}
	
	public Font getFont(){
		return font;
	}
	
	public int getSizePercentage(){
		//Currently useless
		return sizePercentage;
	}
	
	public void setSizePercentageCentered(int percent){
		//Unused method, works by centering button and sizing it percentage wise.
		this.sizePercentage = percent;
		double temp = sizePercentage / 100;
		double tempW = temp * width;
		double change = (width - tempW) / 2;
		tempW = tempW + change;
		double tempX = x + change;
		
		double tempH = temp * height;
		change = (height - tempH) / 2;
		tempH = tempH + change;
		double tempY = y + change;
		
		y = (int) tempY;
		height = (int) tempH;
		width = (int) tempW;
		x = (int) tempX;
		
		this.setSize(width, height);
		this.setLocation(x, y);
		
		this.revalidate();
		
	}
	
	public void setSizePercentageFixedPoint(int percent){
		//Unused method, works by aligning the button to the left and sizing it percentage wise.
		this.sizePercentage = percent;
		double temp = sizePercentage / 100;
		double tempW = temp * width;
		double tempH = temp * height;
		
		width = (int) tempW;
		height = (int) tempH;
		
		this.setSize(width, height);
		this.revalidate();
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Paints everything to the button
		g.setColor(bg);
		g.fillRect(x, y, width, height);
		g.setColor(fg);
		g.fillRect((int)(x + (width / 200)),(int) (y + (height / 25)),(int) (width - (width / 100)),(int) (height - (height / 12)));
		g.setFont(font);
		stringCalculations(g);
		g.setColor(Color.BLACK);
		drawOutlines(g);
		g.setColor(textColor);
		g.drawString(text, stringX, stringY);	
	}
	
	private void drawOutlines(Graphics g){
		//Oulines text in black
		g.drawString(text, stringX - 1, stringY -1);
		g.drawString(text, stringX - 1, stringY + 1);
		g.drawString(text, stringX + 1, stringY - 1);
		g.drawString(text, stringX + 1, stringY + 1);
		g.drawString(text, stringX - 2, stringY -2);
		g.drawString(text, stringX - 2, stringY + 2);
		g.drawString(text, stringX + 2, stringY - 2);
		g.drawString(text, stringX + 2, stringY + 2);
	}
	
	private void stringCalculations(Graphics g){
		//Determines how to center the strings
		FontMetrics fm = g.getFontMetrics(font);
		int stringWidth = fm.stringWidth(text);
		int stringAscent = fm.getAscent();
		int stringDecent = fm.getDescent();
		int stringHeight = stringDecent - stringAscent;
		
		int leftover = width - stringWidth;
		leftover = leftover / 2;
		stringX = x + leftover;
		
		leftover = height - stringHeight;
		leftover = leftover / 2;
		stringY = y + leftover;
		
	}
}
