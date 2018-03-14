package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ButtonArea {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color bg;
	private Color fg;
	private Color fc;
	private Font f;
	private double percentWidth;
	private double percentHeight;
	private double percentWidthSpace;
	private double percentHeightSpace;
	
	private ArrayList<RCButton> buttons;
	private int buttonCount = 0;
	
	
	
	public ButtonArea(int x, int y, int width, int height, Color bg, Color fg, Color fc, Font f, double percentWidth, double percentHeight, double percentWidthSpace, double percentHeightSpace) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bg = bg;
		this.fg = fg;
		this.fc = fc;
		this.f = f;
		this.percentWidth = percentWidth;
		this.percentHeight = percentHeight;
		this.percentHeightSpace = percentHeightSpace;
		this.percentWidthSpace = percentWidthSpace;
		buttons = new ArrayList<RCButton>();
	}
	
	public void addButton(String s){
		RCButton b = new RCButton(x, y, width, height, buttonCount, bg, fg, fc, f, s, percentWidth, percentHeight);
		buttons.add(b);
		buttonCount++;
		revalidateButtons();
	}
	
	private void revalidateButtons(){
		int hs = (int) (height * (percentHeightSpace / 100.0));
		int hsp = hs / (buttonCount + 1);
		int ws = (int) (width * (percentWidthSpace / 100.0));
		int wsp = ws / 2;
		int buttonHeight = (height - hs) / buttonCount;
		int buttonWidth = width - ws;
		
		for(int i = 0; i < buttonCount; i++){
			int tempx = x + (wsp);
			int tempy = y + (buttonHeight * i) + (hsp * (i + 1));
			buttons.get(i).setX(tempx);
			buttons.get(i).setY(tempy);
			buttons.get(i).setWidth(buttonWidth);
			buttons.get(i).setHeight(buttonHeight);
		}
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

	public Color getBg() {
		return bg;
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}

	public Color getFg() {
		return fg;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}

	public Color getFc() {
		return fc;
	}

	public void setFc(Color fc) {
		this.fc = fc;
	}

	public Font getF() {
		return f;
	}

	public void setF(Font f) {
		this.f = f;
	}

	public double getPercentWidth() {
		return percentWidth;
	}

	public void setPercentWidth(double percentWidth) {
		this.percentWidth = percentWidth;
	}

	public double getPercentHeight() {
		return percentHeight;
	}

	public void setPercentHeight(double percentHeight) {
		this.percentHeight = percentHeight;
	}

	public ArrayList<RCButton> getButtons() {
		return buttons;
	}

	public int getButtonCount() {
		return buttonCount;
	}

	public void setButtonCount(int buttonCount) {
		this.buttonCount = buttonCount;
	}
	
	public void draw(Graphics2D g){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).draw(g);
		}
	}
	
	public void doSelection(int mouseX, int mouseY){
		for(int i = 0; i < buttons.size(); i++){
			if(mouseX >= buttons.get(i).getX() && mouseX <= buttons.get(i).getX() + buttons.get(i).getWidth() && mouseY >= buttons.get(i).getY() && mouseY <= buttons.get(i).getY() + buttons.get(i).getHeight()){
				buttons.get(i).highlighted = true;
			}else{
				buttons.get(i).highlighted = false;
			}
		}
	}

	public boolean hasButtonSelected() {
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).highlighted){
				return true;
			}
		}
		return false;
	}
	
	public int getSelectedButtonId(){
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).highlighted){
				return buttons.get(i).getButtonId();
			}
		}
		return -1;
	}

}
