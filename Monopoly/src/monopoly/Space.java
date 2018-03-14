package monopoly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Space extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int spaceNumber;
	private Color color;
	private int blackLineWidth;
	private int blackLineHeight;
	private int blackLineWidth2;
	private int blackLineHeight2;
	private String name;
	
	public Space(int x, int y, int width, int height, int spaceNumber){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.spaceNumber = spaceNumber;
		
		blackLineWidth = (int) (width * .03);
		blackLineHeight = height;
		
		blackLineWidth2 = width;
		blackLineHeight2 = (int) (height * .03);
		
		setName();
		setColor();
	}
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setColor(Color.BLACK);
		g.fillRect(this.x, this.y, blackLineWidth, blackLineHeight);
		g.fillRect((this.x + width) - blackLineWidth, y, blackLineWidth, blackLineHeight);
		g.fillRect(x, y, blackLineWidth2, blackLineHeight2);
		g.fillRect(x, (this.y + height) - blackLineHeight2, blackLineWidth2, blackLineHeight2);
		
		if(color != color.WHITE){
			if(spaceNumber == 21 || spaceNumber == 23 || spaceNumber == 24 || spaceNumber == 26 || spaceNumber == 27 || spaceNumber == 29){
				g.fillRect(x, (int) (height * .8), blackLineWidth2, blackLineHeight2);
				g.setColor(color);
				g.fillRect(blackLineWidth + x, (int) ((height * .8) + blackLineHeight2), width - (2 * blackLineWidth), (int) (height * .2));
				g.setColor(Color.BLACK);
				g.drawString(name, x + (2 * blackLineWidth), (int) (y + (height * .5)));
			}else if(spaceNumber == 31 || spaceNumber == 32 || spaceNumber == 34 || spaceNumber == 37 || spaceNumber == 39){
				g.fillRect((int) ((10 * width) + (width * .2)), y, blackLineWidth, blackLineHeight);
				g.setColor(color);
				g.fillRect((int) ((10 * width) + blackLineWidth), y + blackLineHeight2, (int) ((width * .2) - blackLineWidth), blackLineHeight);
				g.setColor(Color.BLACK);
				g.drawString(name, (int) (x + (.2 * width) + (2 * blackLineWidth)), (int) (y + (height * .5)));
			}else if(spaceNumber == 9 || spaceNumber == 8 || spaceNumber == 6 || spaceNumber == 3 || spaceNumber == 1){
				g.fillRect(x, (int) ((8 * height) + (height * .8)), blackLineWidth2, blackLineHeight2);
				g.setColor(color);
				g.fillRect((x + blackLineWidth), y + blackLineHeight2, width - (2 * blackLineWidth), (int) (height * .2));
				g.setColor(Color.BLACK);
				g.drawString(name, x + (2 * blackLineWidth), (int) (y + (height * .5)));
			}else{
				g.fillRect((int) (width * .8), y, blackLineWidth, blackLineHeight);
				g.setColor(color);
				g.fillRect((int) ((width * .8) + blackLineWidth), y + blackLineHeight2,(int) ((width * .2) - (1.5 * blackLineWidth)), (int) (height - (blackLineHeight2)));
				g.setColor(Color.BLACK);
				g.drawString(name, (int) (x + (2 * blackLineWidth)), (int) (y + (height * .5)));
			}
		}
		if(spaceNumber == 22 || spaceNumber == 25 || spaceNumber == 28){
			g.setColor(Color.BLACK);
			g.drawString(name, x + (2 * blackLineWidth), (int) (y + (height * .5)));
		}else if(spaceNumber == 33 || spaceNumber == 35 || spaceNumber == 36 || spaceNumber == 38){
			g.setColor(Color.BLACK);
			g.drawString(name, (int) (x + (2 * blackLineWidth)), (int) (y + (height * .5)));
		}else if(spaceNumber == 2 || spaceNumber == 4 || spaceNumber == 5 || spaceNumber == 7){
			g.setColor(Color.BLACK);
			g.drawString(name, x + (2 * blackLineWidth), (int) (y + (height * .5)));
		}else if(spaceNumber == 12 || spaceNumber == 15 || spaceNumber == 17){
			g.setColor(Color.BLACK);
			g.drawString(name, (int) (x + (2 * blackLineWidth)), (int) (y + (height * .5)));
		}else if(spaceNumber == 20){
			Font font = new Font("Rydge", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString(name, (int) (x + (2 * blackLineWidth)), (int) (y + (height * .5)));
			g.setFont(font);
			font = new Font("Dialog", Font.PLAIN, 12);
			g.setFont(font);
		}else if(spaceNumber == 30){
			Font font = new Font("Rydge", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString(name, (int) (x + (2 * blackLineWidth)), (int) (y + (height * .5)));
			g.setFont(font);
			font = new Font("Dialog", Font.PLAIN, 12);
			g.setFont(font);
		}else if(spaceNumber == 10){
			Font font = new Font("Rydge", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString(name, (int) (x + (10 * blackLineWidth)), (int) (y + (height * .5)));
			g.setFont(font);
			font = new Font("Dialog", Font.PLAIN, 12);
			g.setFont(font);
		}else if(spaceNumber == 0){
			Font font = new Font("Rydge", Font.BOLD, 40);
			g.setFont(font);
			g.setColor(new Color(175, 0, 0, 255));
			g.drawString(name, (int) (x + (6 * blackLineWidth)), (int) (y + (height * .7)));
			g.setFont(font);
			font = new Font("Dialog", Font.PLAIN, 12);
			g.setFont(font);
			g.setColor(Color.BLACK);
		}
		
		
	}
	
	public void setColor(){
		if(spaceNumber == 1 || spaceNumber == 3){
			color = new Color(104, 34, 139, 255);
		}else if(spaceNumber == 6 || spaceNumber == 8 || spaceNumber == 9){
			color = new Color(0, 245, 255, 255);
		}else if(spaceNumber == 11 || spaceNumber == 13 || spaceNumber == 14){
			color = new Color(255, 20, 147, 255);
		}else if(spaceNumber == 16 || spaceNumber == 18 || spaceNumber == 19){
			color = new Color(238, 118, 0, 255);
		}else if(spaceNumber == 21 || spaceNumber == 23 || spaceNumber == 24){
			color = new Color(255, 0, 0, 255);
		}else if(spaceNumber == 26 || spaceNumber == 27 || spaceNumber == 29){
			color = new Color(255, 255, 0, 255);
		}else if(spaceNumber == 31 || spaceNumber == 32 || spaceNumber == 34){
			color = new Color(0, 100, 0, 255);
		}else if(spaceNumber == 37 || spaceNumber == 39){
			color = new Color(61, 89, 171, 255);
		}else{
			color = Color.WHITE;
		}
	}
	
	public void setName(){
		if(spaceNumber == 0){
			name = "Free Parking";
			spaceNumber = 20;
	    }else if(spaceNumber == 1){
	    	name = "Kentucky Avenue";
	    	spaceNumber = 21;
		}else if(spaceNumber == 2){
			name = "New York Avenue";
			spaceNumber = 19;
		}else if(spaceNumber == 3){
			spaceNumber = 31;
			name = "Pacific Avenue";
		}else if(spaceNumber == 4){
			name = "Connecticut Avenue";
			spaceNumber = 9;
		}else if(spaceNumber == 5){
			name = "Chance";
			spaceNumber = 22;
		}else if(spaceNumber == 6){
			name = "Tennesse Avenue";
			spaceNumber = 18;
		}else if(spaceNumber == 7){
			spaceNumber = 32;
			name = "N.C. Avenue";
		}else if(spaceNumber == 8){
			name = "Vermont Avenue";
			spaceNumber = 8;
		}else if(spaceNumber == 9){
			name = "Indiana Avenue";
			spaceNumber = 23;
		}else if(spaceNumber == 10){
			spaceNumber = 17;
			name = "Community Chest";
		}else if(spaceNumber == 11){
			name = "Community Chest";
			spaceNumber = 33;
		}else if(spaceNumber == 12){
			name = "Chance";
			spaceNumber = 7;
		}else if(spaceNumber == 13){
			name = "Illinois Avenue";
			spaceNumber = 24;
		}else if(spaceNumber == 14){
			name = "St. James Place";
			spaceNumber = 16;
		}else if(spaceNumber == 15){
			name = "Penn. Avenue";
			spaceNumber = 34;
		}else if(spaceNumber == 16){
			name = "Oriental Avenue";
			spaceNumber = 6;
		}else if(spaceNumber == 17){
			name = "B.&O. Railroad";
			spaceNumber = 25;
		}else if(spaceNumber == 18){
			name = "Penn. Railroad";
			spaceNumber = 15;
		}else if(spaceNumber == 19){
			name = "Short Line";
			spaceNumber = 35;
		}else if(spaceNumber == 20){
			name = "Reading Railroad";
			spaceNumber = 5;
		}else if(spaceNumber == 21){
			name = "Atlantic Avenue";
			spaceNumber = 26;
		}else if(spaceNumber == 22){
			name = "Virginia Avenue";
			spaceNumber = 14;
		}else if(spaceNumber == 23){
			name = "Chance";
			spaceNumber = 36;
		}else if(spaceNumber == 24){
			name = "Income Tax";
			spaceNumber = 4;
		}else if(spaceNumber == 25){
			name = "Ventnor Avenue";
			spaceNumber = 27;
		}else if(spaceNumber == 26){
			name = "States Avenue";
			spaceNumber = 13;
		}else if(spaceNumber == 27){
			name = "Park Place";
			spaceNumber = 37;
		}else if(spaceNumber == 28){
			name = "Baltic Avenue";
			spaceNumber = 3;
		}else if(spaceNumber == 29){
			name = "Water Works";
			spaceNumber = 28;
		}else if(spaceNumber == 30){
			name = "Electric Company";
			spaceNumber = 12;
		}else if(spaceNumber == 31){
			name = "Luxury Tax";
			spaceNumber = 38;
		}else if(spaceNumber == 32){
			name = "Community Chest";
			spaceNumber = 2;
		}else if(spaceNumber == 33){
			name = "Marvin Gardens";
			spaceNumber = 29;
		}else if(spaceNumber == 34){
			name = "St. Charles Place";
			spaceNumber = 11;
		}else if(spaceNumber == 35){
			name = "Boarkwalk";
			spaceNumber = 39;
		}else if(spaceNumber == 36){
			name = "Medit. Avenue";
			spaceNumber = 1;
		}else if(spaceNumber == 37){
			name = "Go To Jail";
			spaceNumber = 30;
		}else if(spaceNumber == 38){
			name = "Jail";
			spaceNumber = 10;
		}else if(spaceNumber == 39){
			name = "GO!";
			spaceNumber = 0;
		}
	}
	
	public Color getColor(){
		return color;
	}

}
