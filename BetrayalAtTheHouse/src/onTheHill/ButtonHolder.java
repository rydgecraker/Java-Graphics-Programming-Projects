package onTheHill;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class ButtonHolder {

	public int x;
	public int y;
	public int width;
	public int height;
	public int percentSpace;
	private ArrayList<Button> buttons;

	public ButtonHolder(int x, int y, int width, int height, int percentSpace) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.percentSpace = percentSpace;
		buttons = new ArrayList<Button>();
	}

	public void addButton(String text, String keyCode) {
		double buttonPer = 100.0 - percentSpace;
		buttonPer = buttonPer / (buttons.size() + 1);
		buttonPer = buttonPer / 100.0;
		int buttonHeight = (int) (height * buttonPer);
		double bSpace = percentSpace / (buttons.size() + 2);
		bSpace = bSpace / 100.0;
		int buttonSpace = (int) (height * bSpace);
		int buttonLoc = y + buttonSpace;
		int i = 0;
		while (i < buttons.size()) {
			buttons.get(i).height = buttonHeight;
			buttons.get(i).y = buttonLoc;
			buttonLoc = buttonLoc + buttonSpace + buttonHeight;
			i++;
		}
		buttons.add(new RedButton(x, buttonLoc, width, buttonHeight, text, keyCode));

	}

	public void draw(Graphics g) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
	}

	public void mouseOver(int x, int y) {
		for (int i = 0; i < buttons.size(); i++) {
			if (x > buttons.get(i).x && x < buttons.get(i).width + buttons.get(i).x && y > buttons.get(i).y && y < buttons.get(i).height + buttons.get(i).y) {
				buttons.get(i).invertColors();
			} else {
				buttons.get(i).revertColors();
			}
		}
	}

	public Button getButtonMousedOver(int x, int y) {
		for (int i = 0; i < buttons.size(); i++) {
			if (x > buttons.get(i).x && x < buttons.get(i).width + buttons.get(i).x && y > buttons.get(i).y && y < buttons.get(i).height + buttons.get(i).y) {
				return buttons.get(i);
			} else {
				buttons.get(i).revertColors();
			}
		}
		return null;
	}

	public void darkColors(int x, int y) {
		for (int i = 0; i < buttons.size(); i++) {
			if (x > buttons.get(i).x && x < buttons.get(i).width + buttons.get(i).x && y > buttons.get(i).y && y < buttons.get(i).height + buttons.get(i).y) {
				buttons.get(i).darkColors();
			}
		}
	}

}
