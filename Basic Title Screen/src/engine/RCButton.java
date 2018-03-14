package engine;

import java.awt.*;

public class RCButton {
	
	public boolean highlighted = false;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int buttonid;
	private Color bg;
	private Color fg;
	private Color bg2;
	private Color fg2;
	private Color fontColor;
	private Color fontColor2;
	private Font font;
	private Font font2;
	private String text;
	private String text2;
	private double percentHeight;
	private	double percentWidth;
	
	public RCButton(int x, int y, int width, int height, int buttonid, Color background, Color foreground, Color background2, Color foreground2, Color fontColor, Color fontColor2, Font font, Font font2, String text, String text2, double percentWidth, double percentHeight){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonid = buttonid;
		this.bg = background;
		this.fg = foreground;
		this.bg2 = background2;
		this.fg2 = foreground2;
		this.fontColor = fontColor;
		this.fontColor2 = fontColor2;
		this.font = font;
		this.font2 = font2;
		this.text = text;
		this.text2 = text2;
		this.percentHeight = percentHeight;
		this.percentWidth = percentWidth;
	}
	
	public RCButton(int x, int y, int width, int height, int buttonid, Color background, Color foreground, Color fontColor, Font font, String text, double percentWidth, double percentHeight){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonid = buttonid;
		this.bg = background;
		this.fg = foreground;
		this.bg2 = foreground;
		this.fg2 = background;
		this.fontColor = fontColor;
		this.fontColor2 = fontColor;
		this.font = font;
		this.font2 = font;
		this.text = text;
		this.text2 = text;
		this.percentHeight = percentHeight;
		this.percentWidth = percentWidth;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g){
		draw((Graphics2D) g);
	}
	
	public int getButtonId(){
		return buttonid;
	}
	
	public void setButtonId(int bid){
		this.buttonid = bid;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int x){
		this.y = x;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int x){
		this.width = x;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int x){
		this.height = x;
	}
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public Color getBgColor(){
		return bg;
	}
	
	public void setBgColor(Color bg){
		this.bg = bg;
	}
	
	public Color getBg2Color(){
		return bg2;
	}
	
	public void setBg2Color(Color bg){
		this.bg2 = bg;
	}
	
	public Color getFgColor(){
		return fg;
	}
	
	public void setFgColor(Color bg){
		this.fg = bg;
	}
	
	public Color getFg2Color(){
		return fg2;
	}
	
	public void setFg2Color(Color bg){
		this.fg2 = bg;
	}
	
	public Color getFontColor(){
		return fontColor;
	}
	
	public void setFontColor(Color bg){
		this.fontColor = bg;
	}
	
	public Color getFont2Color(){
		return fontColor2;
	}
	
	public void setFont2Color(Color bg){
		this.fontColor2 = bg;
	}
	
	public Font getFont(){
		return font;
	}
	
	public void setFont(Font f){
		this.font = f;
	}
	
	public Font getFont2(){
		return font2;
	}
	
	public void setFont2(Font f){
		this.font2 = f;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String s){
		this.text = s;
	}
	
	public String getText2(){
		return text2;
	}
	
	public void setText2(String s){
		this.text = s;
	}
	
	public double getPercentWidth(){
		return percentWidth;
	}
	
	public void setPercentWidth(double x){
		this.percentWidth = x;
	}
	
	public double getPercentHeight(){
		return percentHeight;
	}
	
	public void setPercentHeight(double x){
		this.percentHeight = x;
	}
	
	public String toString(){
		return "ButtonID: " + buttonid;
	}
	
	
	public void draw(Graphics2D g){
		if(highlighted){
			g.setColor(bg2);
			g.fillRect(x, y, width, height);
			double wp = percentWidth / 100.0;
			double hp = percentHeight / 100.0;
			g.setColor(fg2);
			
			int tempx = (int) (x + (width * wp));
			int tempy = (int) (y + (height * hp));
			int tempw = (int) (width - (width * (wp + wp)));
			int temph = (int) (height - (height * (hp + hp)));
			
			g.fillRect(tempx, tempy, tempw, temph);
			g.setFont(font2);
			FontMetrics fm = g.getFontMetrics();
			int sh = fm.getHeight() / 2;
			int sw  = fm.stringWidth(text2);
			
			int stringx = x + ((width / 2) - (sw / 2));
			int stringy = y + ((height / 2) + (sh / 2));
			
			drawStringOutline(stringx, stringy, text2, g);
			g.setColor(fontColor2);
			g.drawString(text, stringx, stringy);
		}else{
			g.setColor(bg);
			g.fillRect(x, y, width, height);
			double wp = percentWidth / 100.0;
			double hp = percentHeight / 100.0;
			g.setColor(fg);
			
			int tempx = (int) (x + (width * wp));
			int tempy = (int) (y + (height * hp));
			int tempw = (int) (width - (width * (wp + wp)));
			int temph = (int) (height - (height * (hp + hp)));
			
			g.fillRect(tempx, tempy, tempw, temph);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			int sh = fm.getHeight() / 2;
			int sw  = fm.stringWidth(text);
			
			int stringx = x + ((width / 2) - (sw / 2));
			int stringy = y + ((height / 2) + (sh / 2));
			
			drawStringOutline(stringx, stringy, text, g);
			g.setColor(fontColor);
			g.drawString(text, stringx, stringy);
			
		}
	}
	
	private void drawStringOutline(int strx, int stry, String s, Graphics2D g){
		g.setColor(Color.BLACK);
		g.drawString(s, strx + 1, stry);
		g.drawString(s, strx, stry + 1);
		g.drawString(s, strx + 1, stry + 1);
		g.drawString(s, strx - 1, stry);
		g.drawString(s, strx, stry - 1);
		g.drawString(s, strx - 1, stry - 1);
		g.drawString(s, strx + 2, stry);
		g.drawString(s, strx, stry + 2);
		g.drawString(s, strx + 2, stry + 2);
		g.drawString(s, strx - 2, stry);
		g.drawString(s, strx, stry - 2);
		g.drawString(s, strx - 2, stry - 2);
	}

}
