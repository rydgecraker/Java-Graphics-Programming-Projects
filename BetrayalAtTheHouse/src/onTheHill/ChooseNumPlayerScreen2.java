package onTheHill;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChooseNumPlayerScreen2 extends ChooseNumPlayerScreen{

	public int startingComNum;
	private int numComPos;
	
	public ChooseNumPlayerScreen2(String text, int x, int y, int width, int height, int startingComNum, int numComPos) {
		super(text, x, y, width, height);
		this.startingComNum = startingComNum;
		this.numComPos = numComPos;
		init();
	}
	
	public void initButtons(){
		
	}
	
	public void init(){
		buttons = new Button[numComPos - (startingComNum - 1)];
		int spaceBetween = 20;
		int numButtons = numComPos - (startingComNum - 1);
		if(numButtons == 4){
			int startingY =(int) (height * .1);
			int buttonHeight =(int) (((height * .9) - (spaceBetween * 3))/ 2);
			int buttonWidth = (int) ((width - (spaceBetween * 3))/2);
			if(startingComNum == 1){
				//Starts with 1
				//2 Humans
				buttons[0] = new BlueButton(x + spaceBetween, y + startingY + spaceBetween, buttonWidth, buttonHeight, "1", "1");
				buttons[1] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + spaceBetween, buttonWidth, buttonHeight, "2", "2");
				buttons[2] = new BlueButton(x + spaceBetween, y + startingY + (spaceBetween*2) + buttonHeight, buttonWidth, buttonHeight, "3", "3");
				buttons[3] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + (spaceBetween*2)+ buttonHeight, buttonWidth, buttonHeight, "4", "4");
			}else{
				//Starts with 2
				// 1 Human
				buttons[0] = new BlueButton(x + spaceBetween, y + startingY + spaceBetween, buttonWidth, buttonHeight, "2", "2");
				buttons[1] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + spaceBetween, buttonWidth, buttonHeight, "3", "3");
				buttons[2] = new BlueButton(x + spaceBetween, y + startingY + (spaceBetween*2) + buttonHeight, buttonWidth, buttonHeight, "4", "4");
				buttons[3] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + (spaceBetween*2)+ buttonHeight, buttonWidth, buttonHeight, "5", "5");
			}
		}else if(numButtons == 3){
			int startingY =(int) (height * .1);
			int buttonHeight =(int) (((height * .9) - (spaceBetween * 3))/ 2);
			int buttonWidth = (int) ((width - (spaceBetween * 3))/2);
			//Starts with 1
			//3 Humans 
			buttons = new Button[4];
			//Add a zero button
			numButtons = 4;
			
			buttons[0] = new BlueButton(x + spaceBetween, y + startingY + spaceBetween, buttonWidth, buttonHeight, "0", "0");
			buttons[1] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + spaceBetween, buttonWidth, buttonHeight, "1", "1");
			buttons[2] = new BlueButton(x + spaceBetween, y + startingY + (spaceBetween*2) + buttonHeight, buttonWidth, buttonHeight, "2", "2");
			buttons[3] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + (spaceBetween*2)+ buttonHeight, buttonWidth, buttonHeight, "3", "3");
			
		}else if(numButtons == 2){
			int startingY =(int) (height * .1);
			int buttonHeight =(int) (((height * .9) - (spaceBetween * 2)));
			int buttonWidth = (int) ((width - (spaceBetween * 4))/3);
			//Starts with 1
			//4 Humans
			buttons = new Button[3];
			numButtons = 3;
			
			buttons[0] = new BlueButton(x + spaceBetween, y + startingY + spaceBetween, buttonWidth, buttonHeight, "0", "0");
			buttons[1] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + spaceBetween, buttonWidth, buttonHeight, "1", "1");
			buttons[2] = new BlueButton(x + (buttonWidth * 2) + (spaceBetween*3), y + startingY + spaceBetween, buttonWidth, buttonHeight, "2", "2");
			
		}else if(numButtons == 1){
			int startingY =(int) (height * .1);
			int buttonHeight =(int) (((height * .9) - (spaceBetween * 2)));
			int buttonWidth = (int) ((width - (spaceBetween * 3))/2);
			//Starts with 1
			//4 Humans
			buttons = new Button[2];
			numButtons = 2;
			
			buttons[0] = new BlueButton(x + spaceBetween, y + startingY + spaceBetween, buttonWidth, buttonHeight, "0", "0");
			buttons[1] = new BlueButton(x + buttonWidth + (spaceBetween*2), y + startingY + spaceBetween, buttonWidth, buttonHeight, "1", "1");
			
		}
		
		try {
			img = ImageIO.read(new File("src/Images/trees2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		initButtons();
		
	}

}
