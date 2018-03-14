package quest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PauseMenuPanel extends JPanel implements ActionListener{

	private Player p;
	private QuadTree qt;
	private ArrayList<Spell> spellsOnMap;
	private JPanel savePanel;
	private JButton save;
	
	
	public PauseMenuPanel(Player p, QuadTree qt, ArrayList<Spell> spellsOnMap){
		this.setLayout(null);
		this.p = p;
		this.qt = qt;
		this.spellsOnMap = spellsOnMap;
		savePanel = new JPanel();
//		savePanel.setLocation(0, this.getHeight() - 100);
//		savePanel.setSize(this.getWidth(), 100);
		this.add(savePanel);
		savePanel.setBackground(Color.RED);
		save = new JButton("Save");
		save.addActionListener(this);
		savePanel.add(save);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		save.setLocation((this.getWidth() - 50), (this.getHeight() - 50));
		savePanel.setBounds(0, this.getHeight() - 100, this.getWidth(), 100);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 300);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Strength: " + p.getStrength(), 20, 20);
		g.drawString("Defense: " + p.getDefense(), 20, 60);
		g.drawString("Speed: " + p.getSpeed(), 20, 100);
		g.drawString("Charisma: ", 20, 140);
		g.drawString("Magic: " + p.getMagic(), 20, 180);
		g.drawString("Luck: " + p.getLuck(), 20, 220);
		g.drawString("Constitution: " + p.getConstitution(), 20, 260);
		//Speed char magic luck con 
		g.setColor(Color.GRAY);
		g.fillRect(0, this.getHeight() - 100, this.getWidth(), 100);
		savePanel.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		save.setEnabled(false);
		//Save game
		save.setEnabled(true);
	}
	
}
