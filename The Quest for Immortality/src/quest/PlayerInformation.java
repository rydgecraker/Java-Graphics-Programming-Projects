package quest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlayerInformation extends JPanel {

	private Player p;

	public PlayerInformation(Player p) {
		this.p = p;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255, 250, 205, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(p.getName(), 0, 20);
		
		//Make Health Bar
		g.setColor(Color.BLACK);
		g.drawString("$" + p.getMoney(), 700, 20);
		g.drawString("Level: " + p.getLevel(), 200, 20);
		g.drawString("Experience: " + p.getLevel() + " / 100", 400, 20);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Heath:     " + p.getHp() + " / " + p.getMaxHp(), 10, 40);
		g.drawRect(10, 50, 301, 20);
		g.setColor(Color.GRAY);
		g.fillRect(11, 51, 300, 19);
		g.setColor(Color.GREEN);
		double hpPerSection = 300.0 / p.getMaxHp();
		double number = hpPerSection * p.getHp();
		number = number * 10;
		int modNumber = (int) (number % 10);
		if (modNumber < 5) {
			number = number / 10;
		} else {
			number = number / 10;
			number++;
		}
		g.fillRect(11, 51, (int) (number), 19);
		
		//Make Magic Bar
		g.setColor(Color.BLACK);
		g.drawString("Magic:     " + p.getMp() + " / " + p.getMaxMp(), 330, 40);
		g.drawRect(330, 50, 301, 20);
		g.setColor(Color.GRAY);
		g.fillRect(331, 51, 300, 19);
		g.setColor(new Color(0, 191, 255, 255));
		double mpPerSection = 300.0 / p.getMaxMp();
		number = mpPerSection * p.getMp();
		number = number * 10;
		modNumber = (int) (number % 10);
		if (modNumber < 5) {
			number = number / 10;
		} else {
			number = number / 10;
			number++;
		}
		g.fillRect(331, 51, (int) (number), 19);
		g.setColor(Color.BLACK);
		g.drawString("J", 660, 66);
		g.drawString("K", 718, 66);
		g.setColor(Color.GRAY);
		g.fillRect(671, 50, 20, 20);
		g.fillRect(731, 50, 20, 20);
		if(p.selectedSpell != null){
			g.drawImage(p.selectedSpell.img, 731, 50, null);
		}
	}

}
