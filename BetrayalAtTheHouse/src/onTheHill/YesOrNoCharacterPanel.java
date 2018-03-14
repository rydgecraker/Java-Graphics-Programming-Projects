package onTheHill;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class YesOrNoCharacterPanel extends JPanel {

	public int x;
	public int y;
	public int width;
	public int height;
	public CharacterHex ch;
	public Button yes;
	public Button no;

	public int mouseX;
	public int mouseY;

	public YesOrNoCharacterPanel(int x, int y, int width, int height, CharacterHex ch) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.ch = new CharacterHex(x + 7, y + 7, width - 7, ((int) (height * .90)) - 7, ch.charColor, ch.charNum);
		this.ch.changeFonts(23);
		yes = new BlueButton(x, y + ((int) (height * .9)) + 10, (int) ((width * .5) - 5), ((int) (height * .1)), "Yes", "yes");
		no = new BlueButton(x + (int) ((width * .5) + 5), y + ((int) (height * .90)) + 10, (int) ((width * .5) - 5), ((int) (height * .1)), "No", "no");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		ch.draw(g);
		yes.draw(g);
		no.draw(g);
	}

	public void menuEvents() {
		mouseOver(mouseX, mouseY);
	}
	
	public Button getButtonMousedOver(){
		return ggetButtonMousedOver(mouseX, mouseY);
	}

	private void mouseOver(int x, int y) {
		if (x > yes.x && x < yes.width + yes.x && y > yes.y && y < yes.height + yes.y) {
			yes.invertColors();
		} else {
			yes.revertColors();
		}
		if (x > no.x && x < no.width + no.x && y > no.y && y < no.height + no.y) {
			no.invertColors();
		} else {
			no.revertColors();
		}
	}

	private Button ggetButtonMousedOver(int x, int y) {
		if (x > yes.x && x < yes.width + yes.x && y > yes.y && y < yes.height + yes.y) {
			return yes;
		} else {
			yes.revertColors();
		}
		if (x > no.x && x < no.width + no.x && y > no.y && y < no.height + no.y) {
			return no;
		} else {
			no.revertColors();
		}
		return null;
	}
	

}
