package onTheHill;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OmenCard {
	
	public BufferedImage img;
	
	public int x;
	public int y;
	public int width;
	public int height;
	public int cardNumber;
	
	public BetrayalGame bg;
	
	public static final int totalCards = 13;
	
	
	//Card Traits
	////Basics
	public String name = "";
	public boolean useRightAway = false;
	
	////Logistics
	private boolean traitChange = false;
	private boolean playerAttacked = false;
	private boolean rollForEffects = false;
	private boolean mask = false;
	private boolean ring = false;
	private boolean skull = false;
	private boolean medallion = false;
	private boolean spear = false;
	private boolean spiritboard = false;
	
	////For Use By Game
	public boolean canBeDropped = true;
	public boolean canBeStolen = true;
	public boolean canBeTraded = true;
	
	////Traits
	private int sanityChange = 0;
	private int knowledgeChange = 0;
	private int mightChange = 0;
	private int speedChange = 0;
	
	////Attacking
	private int numAttackDice = 0;
	private String traitAttack = "might";
	
	////Roll For Effects Stuff
	private int effectNumbers = 0;
	
	////Alternate Forms of attack and Damage
	private boolean maskOn = false;
	
	public OmenCard(int x, int y, int width, int height, int cardNumber, BetrayalGame bg){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cardNumber = cardNumber; //A number 1 - 13;
		this.bg = bg;
		init();
	}
	
	public void applyChanges(boolean hauntRevealed){
		if(traitChange){
			bg.currentPlayerChangeKnowledge(knowledgeChange, hauntRevealed);
			bg.currentPlayerChangeSanity(sanityChange, hauntRevealed);
			bg.currentPlayerChangeMight(mightChange, hauntRevealed);
			bg.currentPlayerChangeSpeed(speedChange, hauntRevealed);
		}
		if(playerAttacked){
			bg.attackOnCurrentPlayer(numAttackDice, traitAttack);
		}
		if(rollForEffects){
			if(effectNumbers == 0){
				int x = bg.currentPlayerKnowledgeRoll();
				if(x >= 4){
					bg.currentPlayerSearchStackAndPlaceCardOnTop();
				}else if(x >= 1 && x <= 3){
					bg.currentPlayerChangeSanity(-1, hauntRevealed);
				}else{
					bg.currentPlayerChangeSanity(-2, hauntRevealed);
				}
			}
		}
		if(mask){
			int x = bg.currentPlayerSanityRoll();
			if(x >= 4){
				if(maskOn){
					maskOn = false;
					bg.currentPlayerChangeSanity(-2, hauntRevealed);
					bg.currentPlayerChangeKnowledge(2, hauntRevealed);
				}else{
					maskOn = true;
					bg.currentPlayerChangeSanity(2, hauntRevealed);
					bg.currentPlayerChangeSanity(-2, hauntRevealed);
				}
			}
		}
		if(medallion){
			bg.currentPlayerImmuneToPentagramChamber(true);
			bg.currentPlayerImmuneToCrypt(true);
			bg.currentPlayerImmuneToCrypt(true);
			bg.currentPlayerImmuneToGraveyard(true);
		}
		if(ring){
			bg.currentPlayerCanAttackWithSanity(true);
		}
		if(skull){
			bg.currentPlayerCanTakeMentalAsPhysical(true);
		}
		if(spear){
			bg.currentPlayerAddWeapon("Spear", 2, "might", 0, "");
		}
		if(spiritboard){
			bg.lookAtTopCardOfStack();
			if(hauntRevealed){
				bg.traitorMovesMonstersToward(bg.getCurrentPlayer(), 1);
			}
		}
		
	}
	
	public void resetChanges(boolean hauntRevealed){
		if(traitChange){
			bg.currentPlayerChangeKnowledge(-knowledgeChange, hauntRevealed);
			bg.currentPlayerChangeSanity(-sanityChange, hauntRevealed);
			bg.currentPlayerChangeMight(-mightChange, hauntRevealed);
			bg.currentPlayerChangeSpeed(-speedChange, hauntRevealed);
		}
		if(medallion){
			bg.currentPlayerImmuneToPentagramChamber(false);
			bg.currentPlayerImmuneToCrypt(false);
			bg.currentPlayerImmuneToCrypt(false);
			bg.currentPlayerImmuneToGraveyard(false);
		}
		if(ring){
			bg.currentPlayerCanAttackWithSanity(false);
		}
		if(skull){
			bg.currentPlayerCanTakeMentalAsPhysical(false);
		}
		if(spear){
			bg.currentPlayerRemoveWeapon("Spear");
		}
		
	}
	
	public void draw(Graphics g){
		//Draw Stuff
		g.drawImage(img, x, y, null);
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
	
	public void init(){
		if(cardNumber == 1){
			//Bite
			//A growl. The scent of death. Pain. Darkness. Gone.
			//When you draw this card, something bites you.
			//A might 4 attack is made against you before the creature runs away
			//Can't be dropped, traded or stolen.
			//Make a haunt roll now
			canBeDropped = false;
			canBeTraded = false;
			canBeStolen = false;
			numAttackDice = 4;
			traitAttack = "might";
			name = "Bite";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/bite.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 2){
			//Book
			//A diary or lab notes? Ancient script or modern ravings?
			//Gain 2 knowledge now
			//Lose 2 knowledge if you lose the book
			//Make a haunt roll now
			
			knowledgeChange = 2;
			traitChange = true;
			name = "Book";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/book.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 3){
			//Crystal Ball
			//Hazy images appear in the glass.
			//Once per turn after the haunt is revealed, you can attempt a knowledge roll to peer into the crystal ball
			// 4+ - You see the truth. Search through a stack of your choice and place the card of your choice on top
			// 1-3 - You avert your eyes, lose 1 sanity
			// 0 - You stare into hell. Lose 2 sanity
			//Make a haunt roll now
			
			rollForEffects = true;
			effectNumbers = 0;
			name = "Crystal Ball";
			
			try {
				img = ImageIO.read(new File("src/Images/omens/crystalBall.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 4){
			//Dog
			//This mangy dog seems friendly. At least you hope it is.
			//Gain 1 might and 1 sanity now
			//Lose 1 might and 1 sanity if you lose custody of the dog
			//Once during your turn the dog can move into any explored rooms up to 6 spaces away, and then return.
			//It can pick up, carry, or drop 1 item before it returns.
			//This card cannot be dropped, traded, or stolen
			//Make a haunt roll now
			
			mightChange = 1;
			sanityChange = 1;
			traitChange = true;
			canBeDropped = false;
			canBeTraded = false;
			canBeStolen = false;
			name = "Dog";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/dog.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 5){
			//Girl
			//A girl. Trapped. Alone. You free her.
			//Gain 1 sanity and 1 knowledge now
			//Lose 1 sanity and 1 knowledge if you lose custody of the girl
			//This omen can't be dropped, traded, or stolen
			//Make a haunt roll now
			
			sanityChange = 1;
			knowledgeChange = 1;
			traitChange = true;
			name = "Girl";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/girl.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 6){
			//Holy Symbol
			//A symbol of calm in an unsetteling world.
			//Gain 2 sanity now
			//Lose 2 sanity if you lose the holy symbol
			//Make a haunt roll now
			
			sanityChange = 2;
			traitChange = true;
			name = "Holy Symbol";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/holySymbol.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 7){
			//Madman
			//A raving, frothing madman
			//Gain 2 might and lose 1 sanity now
			//Lose 2 might and gain 1 sanity if you lose custody of the madman
			//This omen cannot be dropped, traded, or stolen
			//Make a haunt roll now
			
			mightChange = 2;
			sanityChange = -1;
			traitChange = true;
			canBeDropped = false;
			canBeTraded = false;
			canBeStolen = false;
			name = "Madman";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/madman.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 8){
			//Mask
			//A somber mask to hide your intentions
			//Once during your turn you can attempt a sanity roll to use the mask
			// 4+ - You can put on or take off the mask
			////If you put it on, Gain 2 knowledge and lose 2 sanity
			////If you take it off, Lose 2 knowledge and gain 2 sanity
			// 0-3 - You can't use the mask this turn
			//Make a haunt roll now
			
			mask = true;
			name = "Mask";
			
			try {
				img = ImageIO.read(new File("src/Images/omens/mask.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 9){
			//Medallion
			//A medallion inscribed with a pentagram
			//You are immune to the effects of pentagram chamber, crypt, and graveyeard.
			//Make a haunt roll now
			
			medallion = true;
			name = "Medallion";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/medallion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 10){
			//Ring
			//A battered ring with an incomprehensible inscription
			//If you attack an opponent that has a sanity trait, you can attack with sanity instead of might
			//Damage is mental instead of physical
			//Make a haunt roll now
			
			ring = true;
			name = "Ring";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/ring.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 11){
			//Skull
			//A skull, craked and missing teeth
			//If you take mental damage, you can take it as physical instead.
			//Make a haunt roll now
			
			skull = true;
			name = "Skull";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/skull.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 12){
			//Spear
			//Weapon
			//A weapon pulsing with power
			//You roll 2 additional dice when making a might attack with this weapon. (max of 8)
			//You can't use another weapon while using this one
			//Make a haunt roll now
			
			spear = true;
			name = "Spear";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/omens/spear.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 13){
			//Spiritboard
			//A board with letters and numbers to call the dead
			//Before you move during your turn, you can look at the top tile of the room stack.
			//If you use this after the haunt is revealed, the traitor can move any number of monsters one space closer to you.
			//If there is no traitor, all mosnters move one step closer to you
			//If you are the traitor you can still do this.
			//Make a haunt roll now.
			
			spiritboard = true;
			name = "Spiritboard";
			
			try {
				img = ImageIO.read(new File("src/Images/omens/spiritboard.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

}
