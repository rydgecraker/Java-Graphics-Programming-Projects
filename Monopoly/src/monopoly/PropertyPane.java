package monopoly;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PropertyPane extends JPanel{

	Property p;
	
	public PropertyPane(Property p){
		this.p = p;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), 10);
		g.fillRect(this.getWidth() - 10, 0, 10, this.getHeight());
		g.fillRect(0, 0, 10, this.getHeight());
		g.fillRect(0, this.getHeight() - 10, this.getWidth(), 10);
		g.fillRect(10, 50, this.getWidth() - 10, 10);
		g.setColor(p.getColor());
		g.fillRect(10, 10, this.getWidth() - 20, 40);
		
		g.setColor(Color.BLACK);
		Font f = new Font("Attempt", Font.BOLD, 20);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics();
		int titleWidth = fm.stringWidth(p.getName());
		g.drawString(p.getName(), (this.getWidth() / 2) - (titleWidth / 2), 40);
		
		f = new Font("Smaller Stuff", Font.BOLD, 14);
		g.setFont(f);
		fm = g.getFontMetrics();
		int rentWidth = fm.stringWidth("Rent $" + p.getRent());
		g.drawString("Rent $" + p.getRent(), (this.getWidth() / 2) - (rentWidth / 2), 90);
		String s = "With 1 House $" + p.getOneHouseRent();
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 120);
		s = "With 2 Houses $" + p.getTwoHouseRent();
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 140);
		s = "With 3 Houses $" + p.getThreeHouseRent();
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 160);
		s = "With 4 Houses $" + p.getFourHouseRent();
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 180);
		s = "With Hotel $" + p.getHotelRent();
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 200);
		s = "Mortgage Value $" + p.getMortgageValue();
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 230);
		s = "Houses Cost $" + p.getHouseCost() + " Each";
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 250);
		s = "Hotels Cost $" + p.getHouseCost() + " Each, Plus 4 Houses";
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 270);
		
		f = new Font("Smaller Stuff", Font.BOLD, 10);
		g.setFont(f);
		fm = g.getFontMetrics();
		s = "If a player owns ALL the Lots of any Color-Group,";
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 340);
		s = "The rent is doubled on un-improved Lots of that Group.";
		rentWidth = fm.stringWidth(s);
		g.drawString(s, (this.getWidth() / 2) - (rentWidth / 2), 360);
	}
	
	
}
