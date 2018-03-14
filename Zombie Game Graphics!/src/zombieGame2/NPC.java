package zombieGame2;

import java.awt.Color;

import javax.swing.JOptionPane;

public class NPC extends GameComponent{
	
	int npcNumber;
	int talk;

	public NPC(int x, int y, int w, int h) {
		super(x, y, w, h, Color.BLUE);
	}
	
	public void decideNpcNumber(int npc){
		npcNumber = npc;
	}
	
	public int getNpcNumber(){
		return npcNumber;
	}
	
	public void talk(){
		if(npcNumber == 1){
			if(talk == 0){
				JOptionPane.showMessageDialog(null, "Hello, I am an NPC, or Non-Player Character.  My Name Is Bill. You're Pretty Cool");
				talk = 1;
			}else{
				talk = 0;
			}
			
		}else if(npcNumber == 2){
			if(talk == 0){
				JOptionPane.showMessageDialog(null, "The red blocks are buildings,\nThe purple blocks are doors\nThe dark gray blocks are walls\nThe light gray blocks are passages to the next area\nWe are NPC's\nAnd you are the orange block");
				talk = 1;
			}else{
				talk = 0;
			}
		}
	}

}
