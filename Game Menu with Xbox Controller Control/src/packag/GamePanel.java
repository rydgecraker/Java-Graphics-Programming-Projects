package packag;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

public class GamePanel extends JPanel{
	
	public int height;
	public int width;
	public Crosshair cross;
	public boolean drawCross;
	public ArrayList<Button> buttons;
	
	public int startTime;
	public int counter;
	public int fps;

	public boolean dofps;
	
	public GamePanel(Color backgroundColor, int height, int width){
		buttons = new ArrayList<Button>();
		this.setBackground(backgroundColor);
		this.height = height;
		this.width = width;
		drawCross = false;
		dofps = true;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).draw(g);
		}
		
		if(dofps){
			g.setColor(Color.WHITE);
			g.drawString(fps + " Frames Per Second", 300, 300);
		}
		
		if(drawCross){
			cross.draw(g);
			g.setColor(Color.GREEN);
		}
	}
	
	public void addCross(int height, int width, Color color, int x, int y){
		cross = new Crosshair(height, width, color, x, y);
		drawCross = true;
	}
	
	public void addButton(Button b){
		buttons.add(b);
	}
	
	public void fps(){
		if(startTime == 0){
			startTime = (int) (System.currentTimeMillis() / 1000);
		}
		int currentTime = (int) (System.currentTimeMillis() / 1000);
		counter++;
		if(currentTime - startTime >= 1){
			fps = counter;
			counter = 0;
			startTime = (int) (System.currentTimeMillis() / 1000);
		}
	}
	
	public void clearButtons(){
		buttons.clear();
	}
	
	public void createPlayerButtons(boolean p1, boolean p2, boolean p3, boolean p4){
		if(p1){
			buttons.add(new Button(new Color(200, 0, 0), Color.RED, Color.WHITE, "1 Player", 0, 0, width / 4, height));
			if(p2){
				buttons.add(new Button(new Color(200, 0, 0), Color.RED, Color.WHITE, "2 Players", width / 4, 0, width / 4, height));
				if(p3){
					buttons.add(new Button(new Color(200, 0, 0), Color.RED, Color.WHITE, "3 Players", (width / 4) * 2, 0, width / 4, height));
					if(p4){
						buttons.add(new Button(new Color(200, 0, 0), Color.RED, Color.WHITE, "4 Players", (width / 4) * 3, 0, width / 4, height));
					}else{
						buttons.add(new Button(Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, "4 Players", (width / 4) * 3, 0, width / 4, height));
					}
				}else{
					buttons.add(new Button(Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, "3 Players", (width / 4) * 2, 0, width / 4, height));
					buttons.add(new Button(Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, "4 Players", (width / 4) * 3, 0, width / 4, height));
				}
			}else{
				buttons.add(new Button(Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, "2 Players", width / 4, 0, width / 4, height));
				buttons.add(new Button(Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, "3 Players", (width / 4) * 2, 0, width / 4, height));
				buttons.add(new Button(Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, "4 Players", (width / 4) * 3, 0, width / 4, height));
			}
		}
	}
	
	
	public void loadImages(int np){
		if(np == 1){
			
		}else if(np == 2){
			
		}else if(np == 3){
			
		}else{
			
		}
	}
	
}
