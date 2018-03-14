package onTheHill;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class CharacterHex {

	public String name = "Darrin \"Flash\" Williams";
	
	public String keyCode = "";
	
	public boolean dead;

	public int polyBgChange = 7;
	
	public int x;
	public int y;
	public int width;
	public int height;
	public int charColor;
	public int charNum;

	public String text = "1";

	public int stringX;
	public int stringY;

	public Color fg;
	public Color background;

	public String[] leftTop = new String[] { "X", "2", "2", "3", "3", "4", "4", "6", "7" };
	public String[] rightTop = new String[] { "X", "2", "2", "3", "3", "4", "4", "6", "7" };
	public String[] leftBottom = new String[] { "X", "2", "2", "3", "3", "4", "4", "6", "7" };
	public String[] rightBottom = new String[] { "X", "2", "2", "3", "3", "4", "4", "6", "7" };

	public int baseMight = 4;
	public int baseSpeed = 4;
	public int baseKnowledge = 4;
	public int baseSanity = 4;
	public int currentMight = 3;
	public int currentSpeed = 3;
	public int currentKnowledge = 3;
	public int currentSanity = 3;

	public Color bg = new Color(10, 10, 150);
	public Color bgg = Color.BLACK;
	public Color bggg = Color.GRAY;
	public Color pentBg = new Color(0, 0, 100);

	public Font font = new Font("Arial", Font.BOLD, 20);
	public Font nameFont = new Font("Arial", Font.BOLD, 15);
	public Font infoFont = new Font("Arial", Font.BOLD, 10);
	
	public final int heightScale = 3;
	public final int widthScale = 3;

	public BufferedImage img;
	
	public String age;
	public String birthday;
	public String charHeight;
	public String charWeight;
	public ArrayList<String> hobbies;
	
	public double imageX;
	public double imageY;

	public void changeFonts(int b){
		font = new Font("Arial", Font.BOLD, b + 10);
		nameFont = new Font("Arial", Font.BOLD, b + 5);
		infoFont = new Font("Arial", Font.BOLD, b);
	}
	
	public void resetFonts(){
		font = new Font("Arial", Font.BOLD, 20);
		nameFont = new Font("Arial", Font.BOLD, 15);
		infoFont = new Font("Arial", Font.BOLD, 10);
	}
	
	public CharacterHex(int x, int y, int width, int height, int charColor, int charNum) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.charColor = charColor;
		this.charNum = charNum;
		background = bgg;
		init();
	}
	
	public void changeMight(int change, boolean hauntRevealed){
		if(currentMight + change <= 0 && hauntRevealed){
			currentMight = 0;
			dead = true;
		}else if(currentMight + change <= 0){
			currentMight = 1;
		}else if(currentMight + change >= 8){
			currentMight = 8;
		}else{
			currentMight += change;
		}
	}
	
	public void changeSpeed(int change, boolean hauntRevealed){
		if(currentSpeed + change <= 0 && hauntRevealed){
			currentSpeed = 0;
			dead = true;
		}else if(currentSpeed + change <= 0){
			currentSpeed = 1;
		}else if(currentSpeed + change >= 8){
			currentSpeed = 8;
		}else{
			currentSpeed += change;
		}
	}
	
	public void changeSanity(int change, boolean hauntRevealed){
		if(currentSanity + change <= 0 && hauntRevealed){
			currentSanity = 0;
			dead = true;
		}else if(currentSanity + change <= 0){
			currentSanity = 1;
		}else if(currentSanity + change >= 8){
			currentSanity = 8;
		}else{
			currentSanity += change;
		}
	}
	
	public void changeKnowledge(int change, boolean hauntRevealed){
		if(currentKnowledge + change <= 0 && hauntRevealed){
			currentKnowledge = 0;
			dead = true;
		}else if(currentKnowledge + change <= 0){
			currentKnowledge = 1;
		}else if(currentKnowledge + change >= 8){
			currentKnowledge = 8;
		}else{
			currentKnowledge += change;
		}
	}
	
	
	public int getMight(){
		if(currentMight > 0){
			return Integer.parseInt(leftTop[currentMight]);
		}else{
			return 0;
		}
	}
	
	public int getSpeed(){
		if(currentSpeed > 0){
			return Integer.parseInt(leftBottom[currentSpeed]);
		}else{
			return 0;
		}
	}
	
	public int getSanity(){
		if(currentSanity > 0){
			return Integer.parseInt(rightTop[currentSanity]);
		}else{
			return 0;
		}
	}
	
	public int getKnowledge(){
		if(currentKnowledge > 0){
			return Integer.parseInt(rightBottom[currentKnowledge]);
		}else{
			return 0;
		}
	}
	
	public int getBaseMight(){
		return Integer.parseInt(leftTop[baseMight]);
	}
	
	public int getBaseSpeed(){
		return Integer.parseInt(leftBottom[baseSpeed]);
	}
	
	public int getBaseSanity(){
		return Integer.parseInt(rightTop[baseSanity]);
	}
	
	public int getBaseKnowledge(){
		return Integer.parseInt(rightBottom[baseKnowledge]);
	}

	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	public void init() {
		double hh = getPentagon().ypoints[0] - getPentagon().ypoints[1];
		hh = hh * .75;
		hh = (getPentagon().ypoints[4] + hh) - getPentagon().ypoints[0];
		hh = hh/2;
		double dis = hh;
		
		double ww = getPentagon().xpoints[2] - getPentagon().xpoints[0];
		ww = ww * .40;
		double dis2 = ww;
		
		if (charColor == 0) {
			// Blue
			fg = new Color(10, 175, 250);
			if (charNum == 0) {
				// Madame Zostra
				age = "37";
				charHeight = "5'7\"";
				charWeight = "150 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Astrology");
				hobbies.add("Cooking");
				hobbies.add("Baseball");
				birthday = "December 10th";
				
				name = "Madame Zostra";
				keyCode = "mz";
				baseMight = 4;
				currentMight = 4;
				baseSpeed = 3;
				currentSpeed = 3;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 4;
				currentKnowledge = 4;
				//Might
				leftTop = new String[]{"X", "2", "3", "3", "4", "5", "5", "5", "6"};
				//Speed
				leftBottom = new String[]{"X", "2", "3", "3", "5", "5", "6", "6", "7"};
				//Sanity
				rightTop = new String[]{"X", "4", "4", "4", "5", "6", "7", "8", "8"};
				//Knowledge
				rightBottom = new String[]{"X", "1", "3", "4", "4", "4", "5", "6", "6"};
				try {
					img = ImageIO.read(new File("src/Images/madameZostra.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage(img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			} else if (charNum == 1) {
				// Vivian Lopez
				age = "42";
				charHeight = "5'5\"";
				charWeight = "142 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Old Movies");
				hobbies.add("Horses");
				birthday = "January 11th";
				
				name = "Vivian Lopez";
				keyCode = "vl";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 4;
				currentSpeed = 4;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 4;
				currentKnowledge = 4;
				//Might
				leftTop = new String[]{"X", "2", "2", "2", "4", "4", "5", "6", "6"};
				//Speed
				leftBottom = new String[]{"X", "3", "4", "4", "4", "4", "6", "7", "8"};
				//Sanity
				rightTop = new String[]{"X", "4", "4", "4", "5", "6", "7", "8", "8"};
				//Knowledge
				rightBottom = new String[]{"X", "4", "5", "5", "5", "5", "6", "6", "7"};
				try {
					img = ImageIO.read(new File("src/Images/vivianLopez.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage(img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}
		} else if (charColor == 1) {
			// Green
			fg = new Color(0, 200, 0);
			if (charNum == 0) {
				// Brandon Japsers
				age = "12";
				charHeight = "5'1\"";
				charWeight = "109 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Computers");
				hobbies.add("Camping");
				hobbies.add("Hockey");
				birthday = "May 21st";
				
				name = "Brandon Jaspers";
				keyCode = "bj";
				baseMight = 4;
				currentMight = 4;
				baseSpeed = 3;
				currentSpeed = 3;
				baseSanity = 4;
				currentSanity = 4;
				baseKnowledge = 3;
				currentKnowledge = 3;
				//Might
				leftTop = new String[]{"X", "2", "3", "3", "4", "5", "6", "6", "7"};
				//Speed
				leftBottom = new String[]{"X", "3", "4", "4", "4", "5", "6", "7", "8"};
				//Sanity
				rightTop = new String[]{"X", "3", "3", "3", "4", "5", "6", "7", "8"};
				//Knowledge
				rightBottom = new String[]{"X", "1", "3", "3", "5", "5", "6", "6", "7"};
				try {
					img = ImageIO.read(new File("src/Images/brandonJaspers.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));

			} else if (charNum == 1) {
				// Peter Akimoto
				age = "13";
				charHeight = "4'11\"";
				charWeight = "98 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Bugs");
				hobbies.add("Basketball");
				birthday = "September 3rd";
				
				name = "Peter Akimoto";
				keyCode = "pa";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 4;
				currentSpeed = 4;
				baseSanity = 4;
				currentSanity = 4;
				baseKnowledge = 3;
				currentKnowledge = 3;
				//Might
				leftTop = new String[]{"X", "2", "3", "3", "4", "5", "5", "6", "8"};
				//Speed
				leftBottom = new String[]{"X", "3", "3", "3", "4", "6", "6", "7", "7"};
				//Sanity
				rightTop = new String[]{"X", "3", "4", "4", "4", "5", "6", "6", "7"};
				//Knowledge
				rightBottom = new String[]{"X", "3", "4", "4", "5", "6", "7", "7", "8"};
				try {
					img = ImageIO.read(new File("src/Images/peterAkimoto.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}
		} else if (charColor == 2) {
			// Purple
			fg = new Color(200, 20, 200);
			if (charNum == 0) {
				// Heather Granville
				age = "18";
				charHeight = "5'2\"";
				charWeight = "120 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Television");
				hobbies.add("Shopping");
				birthday = "August 2nd";
				
				name = "Heather Granville";
				keyCode = "hg";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 3;
				currentSpeed = 3;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 5;
				currentKnowledge = 5;
				//Might
				leftTop = new String[]{"X", "3", "3", "3", "4", "5", "6", "7", "8"};
				//Speed
				leftBottom = new String[]{"X", "3", "3", "4", "5", "6", "6", "7", "8"};
				//Sanity
				rightTop = new String[]{"X", "3", "3", "3", "4", "5", "6", "6", "6"};
				//Knowledge
				rightBottom = new String[]{"X", "2", "3", "3", "4", "5", "6", "7", "8"};
				try {
					img = ImageIO.read(new File("src/Images/heatherGranville.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			} else if (charNum == 1) {
				// Jenny LeClerc
				age = "21";
				charHeight = "5'7\"";
				charWeight = "142 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Reading");
				hobbies.add("Soccer");
				birthday = "March 4th";
				
				name = "Jenny LeClerc";
				keyCode = "jl";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 4;
				currentSpeed = 4;
				baseSanity = 5;
				currentSanity = 5;
				baseKnowledge = 3;
				currentKnowledge = 3;
				//Might
				leftTop = new String[]{"X", "3", "4", "4", "4", "4", "5", "6", "8"};
				//Speed
				leftBottom = new String[]{"X", "2", "3", "4", "4", "4", "5", "6", "8"};
				//Sanity
				rightTop = new String[]{"X", "1", "1", "2", "4", "4", "4", "5", "6"};
				//Knowledge
				rightBottom = new String[]{"X", "2", "3", "3", "4", "4", "5", "6", "8"};
				try {
					img = ImageIO.read(new File("src/Images/jennyLeClerc.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}
		} else if (charColor == 3) {
			// Red
			fg = new Color(200, 0, 0);
			if (charNum == 0) {
				// Darrin "Flash" Williams
				age = "20";
				charHeight = "5'11\"";
				charWeight = "188 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Track");
				hobbies.add("Music");
				hobbies.add("Shakespeare");
				birthday = "June 6th";
				
				name = "Darrin \"Flash\" Williams";
				keyCode = "dfw";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 5;
				currentSpeed = 5;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 3;
				currentKnowledge = 3;
				//Might
				leftTop = new String[]{"X", "2", "3", "3", "4", "5", "6", "6", "7"};
				//Speed
				leftBottom = new String[]{"X", "4", "4", "4", "5", "6", "7", "7", "8"};
				//Sanity
				rightTop = new String[]{"X", "1", "2", "3", "4", "5", "5", "5", "7"};
				//Knowledge
				rightBottom = new String[]{"X", "2", "3", "3", "4", "5", "5", "5", "7"};
				try {
					img = ImageIO.read(new File("src/Images/darrinFlashWilliams.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			} else if (charNum == 1) {
				// Ox Bellows
				age = "23";
				charHeight = "6'4\"";
				charWeight = "288 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Football");
				hobbies.add("Shiny Objects");
				birthday = "October 18th";
				
				name = "Ox Bellows";
				keyCode = "ob";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 5;
				currentSpeed = 5;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 3;
				currentKnowledge = 3;
				//Might
				leftTop = new String[]{"X", "4", "5", "5", "6", "6", "7", "8", "8"};
				//Speed
				leftBottom = new String[]{"X", "2", "2", "2", "3", "4", "5", "5", "6"};
				//Sanity
				rightTop = new String[]{"X", "2", "2", "3", "4", "5", "5", "6", "7"};
				//Knowledge
				rightBottom = new String[]{"X", "2", "2", "3", "3", "5", "5", "6", "6"};
				try {
					img = ImageIO.read(new File("src/Images/oxBellows.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}
		} else if (charColor == 4) {
			// White
			fg = Color.WHITE;
			if (charNum == 0) {
				// Father Rhinehardt
				age = "62";
				charHeight = "5'9\"";
				charWeight = "185 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Fencing");
				hobbies.add("Gardening");
				birthday = "April 29th";
				
				name = "Father Rhinehardt";
				keyCode = "fr";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 3;
				currentSpeed = 3;
				baseSanity = 5;
				currentSanity = 5;
				baseKnowledge = 4;
				currentKnowledge = 4;
				//Might
				leftTop = new String[]{"X", "1", "2", "2", "4", "4", "5", "5", "7"};
				//Speed
				leftBottom = new String[]{"X", "2", "3", "3", "4", "5", "6", "7", "7"};
				//Sanity
				rightTop = new String[]{"X", "3", "4", "5", "5", "6", "7", "7", "8"};
				//Knowledge
				rightBottom = new String[]{"X", "1", "3", "3", "4", "5", "6", "6", "8"};
				try {
					img = ImageIO.read(new File("src/Images/fatherRhinehardt.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			} else if (charNum == 1) {
				// Professor Longfellow
				age = "57";
				charHeight = "5'11\"";
				charWeight = "153 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Music");
				hobbies.add("Drama");
				hobbies.add("Fine Wine");
				birthday = "July 27th";
				
				name = "Professor Longfellow";
				keyCode = "pl";
				baseMight = 3;
				currentMight = 3;
				baseSpeed = 4;
				currentSpeed = 4;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 5;
				currentKnowledge = 5;
				//Might
				leftTop = new String[]{"X", "1", "2", "3", "4", "5", "5", "6", "6"};
				//Speed
				leftBottom = new String[]{"X", "2", "2", "4", "4", "5", "5", "6", "6"};
				//Sanity
				rightTop = new String[]{"X", "1", "3", "3", "4", "5", "5", "6", "7"};
				//Knowledge
				rightBottom = new String[]{"X", "4", "5", "5", "5", "5", "6", "7", "8"};
				try {
					img = ImageIO.read(new File("src/Images/professorLongfellow.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}
		} else if (charColor == 5) {
			// Yellow
			fg = new Color(200, 170, 0);
			if (charNum == 0) {
				// Missy Dubourde
				age = "9";
				charHeight = "4'2\"";
				charWeight = "62 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Swimming");
				hobbies.add("Medicine");
				birthday = "February 14th";
				
				name = "Missy Dubourde";
				keyCode = "md";
				baseMight = 4;
				currentMight = 4;
				baseSpeed = 3;
				currentSpeed = 3;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 4;
				currentKnowledge = 4;
				//Might
				leftTop = new String[]{"X", "2", "3", "3", "3", "4", "5", "6", "7"};
				//Speed
				leftBottom = new String[]{"X", "3", "4", "5", "6", "6", "6", "7", "7"};
				//Sanity
				rightTop = new String[]{"X", "1", "2", "3", "4", "5", "5", "6", "7"};
				//Knowledge
				rightBottom = new String[]{"X", "2", "3", "4", "4", "5", "6", "6", "6"};
				try {
					img = ImageIO.read(new File("src/Images/missyDubourde.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			} else if (charNum == 1) {
				// Zoe Ingstrom
				age = "8";
				charHeight = "3'9\"";
				charWeight = "49 lbs";
				hobbies = new ArrayList<String>();
				hobbies.add("Dolls");
				hobbies.add("Music");
				birthday = "November 5th";
				
				name = "Zoe Ingstrom";
				keyCode = "zi";
				baseMight = 4;
				currentMight = 4;
				baseSpeed = 4;
				currentSpeed = 4;
				baseSanity = 3;
				currentSanity = 3;
				baseKnowledge = 3;
				currentKnowledge = 3;
				//Might
				leftTop = new String[]{"X", "2", "2", "3", "3", "4", "4", "6", "7"};
				//Speed
				leftBottom = new String[]{"X", "4", "4", "4", "4", "5", "6", "8", "8"};
				//Sanity
				rightTop = new String[]{"X", "3", "4", "5", "5", "6", "6", "7", "8"};
				//Knowledge
				rightBottom = new String[]{"X", "1", "2", "3", "4", "4", "5", "5", "5"};
				try {
					img = ImageIO.read(new File("src/Images/zoeIngstrom.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}else{
				try {
					img = ImageIO.read(new File("src/Images/zoeIngstrom.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
			}
		}

	}
	
	public void rescaleImage(){
		double hh = getPentagon().ypoints[0] - getPentagon().ypoints[1];
		hh = hh * .75;
		hh = (getPentagon().ypoints[4] + hh) - getPentagon().ypoints[0];
		hh = hh/2;
		double dis = hh;
		
		double ww = getPentagon().xpoints[2] - getPentagon().xpoints[0];
		ww = ww * .40;
		double dis2 = ww;
		
		img = toBufferedImage( img.getScaledInstance((int) dis2,(int) dis, Image.SCALE_DEFAULT));
	}

	public void invert() {
		background = bggg;
	}

	public void revert() {
		background = bgg;
	}

	public Polygon getPentagon() {
		int[] xpoints = new int[5];
		int[] ypoints = new int[5];

		xpoints[0] = x;
		ypoints[0] = (int) (y + (height * (5 / 12.0)));

		xpoints[1] = (int) (x + (width / 2.0));
		ypoints[1] = y;

		xpoints[2] = x + width;
		ypoints[2] = (int) (y + (height * (5 / 12.0)));

		xpoints[3] = (int) (x + ((width * (1 / 6.0)) + (width * (2 / 3.0))));
		ypoints[3] = y + height;

		xpoints[4] = (int) (x + (width * (1 / 6.0)));
		ypoints[4] = y + height;

		return new Polygon(xpoints, ypoints, 5);

	}
	
	public Polygon getBgPentagon(){
		int[] xpoints = new int[5];
		int[] ypoints = new int[5];

		xpoints[0] = x - polyBgChange;
		ypoints[0] = (int) (y + (height * (5 / 12.0)));

		xpoints[1] = (int) (x + (width / 2.0));
		ypoints[1] = y - polyBgChange;

		xpoints[2] = x + width + polyBgChange;
		ypoints[2] = (int) (y + (height * (5 / 12.0)));

		xpoints[3] = (int) (x + ((width * (1 / 6.0)) + (width * (2 / 3.0)))) + polyBgChange;
		ypoints[3] = y + height + polyBgChange;

		xpoints[4] = (int) (x + (width * (1 / 6.0))) - polyBgChange;
		ypoints[4] = y + height + polyBgChange;

		return new Polygon(xpoints, ypoints, 5);
	}

	public void draw(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setColor(background);
		if(background == bggg){
			g.fillRect(x - polyBgChange, y - polyBgChange, width + (polyBgChange*2) + 1, height + (polyBgChange*2) + 1);
		}
		g.setColor(pentBg);
		g.fillPolygon(getBgPentagon());
		g.setColor(bg);
		g.fillPolygon(getPentagon());
		g.setColor(fg);
		//

		//
		drawStringStuff(g);
		double hh = getPentagon().ypoints[0] - getPentagon().ypoints[1];
		hh = hh * .75;
		double ww = getPentagon().xpoints[2] - getPentagon().xpoints[0];
		ww = ww * .30;
		imageX = x + ww;
		imageY = y + hh;
		g.drawImage(img, (int) imageX,(int) imageY, null);
		drawCharacterInfo(g);
	}
	
	public void drawCharacterInfo(Graphics2D g){
		g.setFont(infoFont);
		FontMetrics f = g.getFontMetrics();
		g.setColor(Color.WHITE);
		
		int spaceBetween = 3;
		
		int strX = x + ((width / 2) - (f.stringWidth("-Age: " + age + "   -Height:" + charHeight) / 2));
		int strY = (((int) imageY) + img.getHeight() + f.getHeight());
		g.drawString("-Age: " + age + "   -Height:" + charHeight, strX, strY);
		
		strX = x + ((width / 2) - (f.stringWidth("-Weight: " + charWeight) / 2));
		strY = (((int) imageY) + img.getHeight() + (f.getHeight() * 2) + (spaceBetween*1));
		g.drawString("-Weight: " + charWeight, strX, strY);
		
		String temp = "";
		temp += hobbies.get(0) + ",";
		
		strX = x + ((width / 2) - (f.stringWidth("-Hobbies: " + temp) / 2));
		strY = (((int) imageY) + img.getHeight() + (f.getHeight() * 3) + (spaceBetween*2));
		g.drawString("-Hobbies: " + temp, strX, strY);
		
		temp = "";
		temp += hobbies.get(1);
		
		if(hobbies.size() == 3){
			temp += ", " + hobbies.get(2);
		}
		
		strX = x + ((width / 2) - (f.stringWidth(temp) / 2));
		strY = (((int) imageY) + img.getHeight() + (f.getHeight() * 4) + (spaceBetween*3));
		g.drawString(temp, strX, strY);
		
		strX = x + ((width / 2) - (f.stringWidth("-Birthday: " + birthday) / 2));
		strY = (((int) imageY) + img.getHeight() + (f.getHeight() * 5) + (spaceBetween*4));
		g.drawString("-Birthday: " + birthday, strX, strY);
		
	}

	public void drawStringStuff(Graphics2D g) {

		int currentNumber = 3;

		g.setFont(font);
		FontMetrics f = g.getFontMetrics();
		int rotateX = getPentagon().xpoints[0];
		int rotateY = getPentagon().ypoints[0];
		int numNumbers = 9;
		double disX = (getPentagon().xpoints[1] - getPentagon().xpoints[0]);
		double disY = (getPentagon().ypoints[0] - getPentagon().ypoints[1]);
		double dis = Math.sqrt((Math.pow(disY, 2)) + (Math.pow(disX, 2)));
		double ttp = dis / numNumbers;
		int leftSideDistance = (int) ttp;

		stringY = getPentagon().ypoints[0] + ((int) (f.getHeight() * .9));
		
		double sideB = dis;
		double sideA = dis; 
		double sideC = Math.sqrt((Math.pow((getPentagon().xpoints[0] + dis) - getPentagon().xpoints[1], 2)) + (Math.pow((getPentagon().ypoints[0]) - getPentagon().ypoints[1], 2)));
		double angle = Math.acos((Math.pow(sideC, 2) - (Math.pow(sideA, 2) + Math.pow(sideB, 2)))/((-2)*sideA * sideB));
		angle = Math.toDegrees(angle);
		
		g.rotate(Math.toRadians(-angle), rotateX, rotateY);

		for (int i = 0; i < numNumbers; i++) {
			stringX = getPentagon().xpoints[0] + ((int) ((dis * .09) * (i + 1)) + ((int) (dis * .005)));
			g.setColor(Color.WHITE);
			if (i == baseMight) {
				g.setColor(Color.YELLOW);
			}
			if (i == currentMight) {
				g.setColor(Color.RED);
			}
			g.drawString(leftTop[i], stringX, stringY);

		}

		g.setColor(Color.WHITE);
		stringX = getPentagon().xpoints[0] + (int) (dis / 2);
		stringX -= (f.stringWidth("MIGHT") / 2);
		stringY = getPentagon().ypoints[0] + ((int)(f.getHeight() * 2));

		g.drawString("MIGHT", stringX, stringY);

		g.rotate(Math.toRadians(angle), rotateX, rotateY);
		// //////////////////

		rotateX = getPentagon().xpoints[1];
		rotateY = getPentagon().ypoints[1];
		disX = (getPentagon().xpoints[2] - getPentagon().xpoints[1]);
		disY = (getPentagon().ypoints[2] - getPentagon().ypoints[1]);
		dis = Math.sqrt((Math.pow(disY, 2)) + (Math.pow(disX, 2)));
		ttp = dis / numNumbers;
		int rightSideDistance = (int) ttp;

		stringY = getPentagon().ypoints[1] + ((int) (f.getHeight() * .9));
		
		sideB = dis;
		sideA = dis; 
		sideC = Math.sqrt((Math.pow((getPentagon().xpoints[2] - dis) - getPentagon().xpoints[1], 2)) + (Math.pow((getPentagon().ypoints[2]) - getPentagon().ypoints[1], 2)));
		angle = Math.acos((Math.pow(sideC, 2) - (Math.pow(sideA, 2) + Math.pow(sideB, 2)))/((-2)*sideA * sideB));
		angle = Math.toDegrees(angle);

		g.rotate(Math.toRadians(angle), rotateX, rotateY);

		int j = numNumbers - 1;

		for (int i = 0; i < numNumbers; i++) {
			stringX = getPentagon().xpoints[1] + ((int) ((dis * .09) * (i + 1)) + ((int) (dis * .07)));
			g.setColor(Color.WHITE);
			if (j == baseSanity) {
				g.setColor(Color.YELLOW);
			}
			if (j == currentSanity) {
				g.setColor(Color.RED);
			}
			g.drawString(rightTop[j], stringX, stringY);
			j--;
		}

		g.setColor(Color.WHITE);
		stringX = getPentagon().xpoints[1] + (int) (dis / 2);
		stringX = stringX -  (f.stringWidth("SANITY") / 2);
		stringY = getPentagon().ypoints[1] + ((int)(f.getHeight() * 2));

		g.drawString("SANITY", stringX, stringY);

		g.rotate(Math.toRadians(-angle), rotateX, rotateY);

		// ///////////

		rotateX = getPentagon().xpoints[0];
		rotateY = getPentagon().ypoints[0];
		disX = (getPentagon().xpoints[4] - getPentagon().xpoints[0]);
		disY = (getPentagon().ypoints[4] - getPentagon().ypoints[0]);
		dis = Math.sqrt((Math.pow(disY, 2)) + (Math.pow(disX, 2)));
		ttp = dis / numNumbers;
		leftSideDistance = (int) ttp;
		
		sideB = dis;
		sideA = dis; 
		sideC = Math.sqrt((Math.pow((getPentagon().xpoints[0] + dis) - getPentagon().xpoints[4], 2)) + (Math.pow((getPentagon().ypoints[4]) - getPentagon().ypoints[0], 2)));
		angle = Math.acos((Math.pow(sideC, 2) - (Math.pow(sideA, 2) + Math.pow(sideB, 2)))/((-2)*sideA * sideB));
		angle = Math.toDegrees(angle);

		g.rotate(Math.toRadians(angle), rotateX, rotateY);

		stringY = getPentagon().ypoints[0] - ((int) (f.getHeight() * .2));

		j = numNumbers - 1;
		for (int i = 0; i < numNumbers; i++) {
			stringX = getPentagon().xpoints[0] + ((int) ((dis * .09) * (i + 1)) + ((int) (dis * .07)));
			g.setColor(Color.WHITE);
			if (j == baseSpeed) {
				g.setColor(Color.YELLOW);
			}
			if (j == currentSpeed) {
				g.setColor(Color.RED);
			}
			g.drawString(leftBottom[j], stringX, stringY);
			j--;
		}

		g.setColor(Color.WHITE);
		stringX = getPentagon().xpoints[0] + (int) (dis / 2);
		stringX = stringX - (f.stringWidth("SPEED") / 2);
		stringY = getPentagon().ypoints[0] - ((int)(f.getHeight()*1.5));

		g.drawString("SPEED", stringX, stringY);

		g.rotate(Math.toRadians(-angle), rotateX, rotateY);

		// //////////////////////////////
		rotateX = getPentagon().xpoints[3];
		rotateY = getPentagon().ypoints[3];
		disX = (getPentagon().xpoints[3] - getPentagon().xpoints[2]);
		disY = (getPentagon().ypoints[3] - getPentagon().ypoints[2]);
		dis = Math.sqrt((Math.pow(disY, 2)) + (Math.pow(disX, 2)));
		ttp = dis / numNumbers;
		leftSideDistance = (int) ttp;
		
		double sideB2 = dis;
		double sideA2 = dis;
		double sideC2 = Math.sqrt((Math.pow((getPentagon().xpoints[3] + dis) - getPentagon().xpoints[2], 2)) + (Math.pow((getPentagon().ypoints[3]) - getPentagon().ypoints[2], 2)));
		double angle2 = Math.acos((Math.pow(sideC2, 2) - (Math.pow(sideA2, 2) + Math.pow(sideB2, 2)))/((-2)*sideA2 * sideB2));
		angle2 = Math.toDegrees(angle2);
		
		angle = angle2;
		
		g.rotate(Math.toRadians(-angle), rotateX, rotateY);

		stringY = getPentagon().ypoints[3] - ((int) (f.getHeight() * .1));

		
		for (int i = 0; i < numNumbers; i++) {
			stringX = getPentagon().xpoints[3] + ((int) ((dis * .09) * (i + 1)) + ((int) (dis * .005)));
			g.setColor(Color.WHITE);
			if (i == baseKnowledge) {
				g.setColor(Color.YELLOW);
			}
			if (i == currentKnowledge) {
				g.setColor(Color.RED);
			}
			g.drawString(rightBottom[i], stringX, stringY);
		}

		g.setColor(Color.WHITE);
		stringX = getPentagon().xpoints[3] + (int) (dis / 2);
		stringX = stringX - (f.stringWidth("KNOWLEDGE") / 2);
		stringY = getPentagon().ypoints[3] - ((int)(f.getHeight()*1.5));

		g.drawString("KNOWLEDGE", stringX, stringY);

		g.rotate(Math.toRadians(angle), rotateX, rotateY);
		// /////////////////////////////////////////////////////////////////////////////////////////

		g.setFont(nameFont);
		f = g.getFontMetrics();
		int w = width - f.stringWidth(name);
		w = w / 2;
		stringX = x + w;
		stringY = getPentagon().ypoints[4] - 3;
		text = name;
		drawStringOutlines(g);
		g.setColor(fg);
		g.drawString(name, stringX, stringY);

	}

	public void drawStringOutlines(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(text, stringX - 1, stringY - 1);
		g.drawString(text, stringX - 1, stringY + 1);
		g.drawString(text, stringX + 1, stringY - 1);
		g.drawString(text, stringX + 1, stringY + 1);
		g.drawString(text, stringX - 2, stringY - 2);
		g.drawString(text, stringX - 2, stringY + 2);
		g.drawString(text, stringX + 2, stringY - 2);
		g.drawString(text, stringX + 2, stringY + 2);
		g.setColor(Color.WHITE);
	}

	public void stringCalculations(Graphics g, Rectangle bounds) {
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int stringWidth = fm.stringWidth(text);
		int stringAscent = fm.getAscent();
		int stringDecent = fm.getDescent();
		int stringHeight = stringDecent - stringAscent;

		int leftover = bounds.width - stringWidth;
		leftover = leftover / 2;
		stringX = bounds.x + leftover;

		leftover = bounds.height - stringHeight;
		leftover = leftover / 2;
		stringY = bounds.y + leftover;
	}

}
