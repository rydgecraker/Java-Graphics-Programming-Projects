package quest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class SavedDisplayInLoadMenu extends JPanel{

	private Boolean loadedGame;
	private int outsideX;
	private int outsideY;
	private int outsideWidth;
	private int outsideHeight;
	public Color bgColor;
	private int fileNumber;
	
	
	public SavedDisplayInLoadMenu(int outsideX, int outsideY, int fileNumber){
		this.outsideX = outsideX;
		this.outsideY = outsideY;
		outsideWidth = 794;
		outsideHeight = 260;
		this.fileNumber = fileNumber;
		bgColor = Color.GREEN;
		this.setSize(outsideWidth, outsideHeight);
		
		loadedGame = false;
		
		//loadInGame();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(bgColor);
		g.fillRect(outsideX, outsideY, outsideWidth, outsideHeight);
		g.setColor(Color.BLACK);
		g.fillRect(outsideX, outsideY, 5, outsideHeight);
		g.fillRect(outsideX, outsideY, outsideWidth, 5);
		g.fillRect(outsideX + (outsideWidth - 5), outsideY, 5, outsideHeight);
		g.fillRect(outsideX, outsideY + (outsideHeight - 5), outsideWidth, 5);
		if(loadedGame){
			
		}else{
			g.setFont(new Font("Arial", Font.BOLD, 25));
			if(bgColor.equals(Color.RED)){
				g.setColor(Color.WHITE);
			}else{
				g.setColor(Color.RED);
			}
			g.drawString("New Game", 15, 40);
		}
	}
	
	public void loadInGame(){
		//Check for save files;
		loadedGame = true;
	}
	
	
	public String loadOrCreateGame(){
		if(loadedGame){
			return null;
		}else{
			return null;
		}
	}
	
	
}
