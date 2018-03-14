package quest;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class POVPanel extends JPanel{
	
	private Player p;
	private QuadTree qt;
	private ArrayList<Spell> spells;
	
	public POVPanel(Player p, QuadTree qt){
		spells = null;
		this.p = p;
		this.qt = qt;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		qt.draw(g, p);
		p.draw(g);
		if(spells != null){
			for(int i = 0; i < spells.size(); i++){
				spells.get(i).draw(g);
			}
		}
	}
	
	public void addSpells(ArrayList<Spell> spells){
		this.spells = spells;
	}
}
