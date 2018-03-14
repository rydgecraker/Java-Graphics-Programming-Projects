package onTheHill;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemCard {
	
	public BufferedImage img;
	
	public String name;
	public int x;
	public int y;
	public int width;
	public int height;
	public int cardNumber;
	
	public boolean usableAgain = false;
	public boolean useRightAway = false;
	
	public static final int totalCards = 22;
	
	public boolean discardAfterUse = false;
	
	public BetrayalGame bg;
	
	//Trait Stuff
	public int addToTrait = 0;
	public boolean att = false;
	public int addDiceToTrait = 0;
	public boolean adtt = false;
	
	//Gain and lose
	public int gainMight = 0;
	public int gainSpeed = 0;
	public int gainSanity = 0;
	public int gainKnowledge = 0;
	public int loseMight = 0;
	public int loseSpeed = 0;
	public int loseSanity = 0;
	public int loseKnowledge = 0;
	
	public boolean changeTraits = false;
	public boolean callOutNumber = false;
	public boolean pa = false;
	public boolean dontDo = false;
	
	public int physicalArmor = 0;
	
	public int weaponPower = 0;
	public String weaponSkill = "";
	public int weaponNeg = 0;
	public String weaponNegSkill = "";
	public boolean weapon = false;
	public String range = "none";
	
	public boolean bell = false;
	public boolean bottle = false;
	public boolean candle = false;
	public boolean dyn = false;
	public boolean healSal = false;
	public boolean lst = false;
	public boolean medkit = false;
	public boolean puz = false;
	public boolean rab = false;
	public boolean sms = false;
	public boolean monkey = false;
	public boolean sk = false;
	public boolean rp = false;
	public boolean mc = false;
	public boolean druidicCharm = false;
	
	public ItemCard(int x, int y, int width, int height, int cardNumber, BetrayalGame bg){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bg = bg;
		this.cardNumber = cardNumber; //A number 1 - 22;
		init();
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
	
	public boolean use(boolean hauntRevealed){
		//Returns true if card is discarded after use.
		if(att){
			bg.currentPlayerAddToNextResult(addToTrait);
		}
		if(changeTraits && !dontDo){
			bg.currentPlayerChangeKnowledge(gainKnowledge, hauntRevealed);
			bg.currentPlayerChangeSanity(gainSanity, hauntRevealed);
			bg.currentPlayerChangeMight(gainMight, hauntRevealed);
			bg.currentPlayerChangeSpeed(gainSpeed, hauntRevealed);
			if(bell){
				dontDo = true;
			}
		}
		if(callOutNumber){
			bg.currentPlayerCallOutNumber(0, 8);
		}
		if(pa){
			bg.currentPlayerAddPhysicalArmor(physicalArmor);
		}
		if(weapon){
			bg.currentPlayerAddWeapon(name, weaponPower, weaponSkill, weaponNeg, weaponNegSkill, range);
		}
		if(bell && hauntRevealed){
			int b = bg.currentPlayerSanityRoll();
			if(b >= 5){
				bg.currentPlayerMovesPlayersToward(bg.getCurrentPlayer(), 1);
			}else{
				bg.traitorMovesMonstersToward(bg.getCurrentPlayer(), 1);
			}
		}
		if(bottle){
			int b = bg.currentPlayerRollDice(3);
			if(b == 6){
				bg.currentPlayerMoveToRoom("ANY");
			}else if(b == 5){
				bg.currentPlayerChangeMight(2, hauntRevealed);
				bg.currentPlayerChangeSpeed(2, hauntRevealed);
			}else if(b == 4){
				bg.currentPlayerChangeKnowledge(2, hauntRevealed);
				bg.currentPlayerChangeSanity(2, hauntRevealed);
			}else if(b == 3){
				bg.currentPlayerChangeKnowledge(1, hauntRevealed);
				bg.currentPlayerChangeMight(-1, hauntRevealed);
			}else if(b == 2){
				bg.currentPlayerChangeKnowledge(-2, hauntRevealed);
				bg.currentPlayerChangeSanity(-2, hauntRevealed);
			}else if(b == 1){
				bg.currentPlayerChangeMight(-2, hauntRevealed);
				bg.currentPlayerChangeSpeed(-2, hauntRevealed);
			}else if(b == 0){
				bg.currentPlayerChangeKnowledge(-2, hauntRevealed);
				bg.currentPlayerChangeSanity(-2, hauntRevealed);
				bg.currentPlayerChangeMight(-2, hauntRevealed);
				bg.currentPlayerChangeSpeed(-2, hauntRevealed);
			}
		}
		if(candle){
			bg.currentPlayerRollExtraDiceOnEvents(true);
		}
		if(dyn){
			bg.currentPlayerUseDynamite();
		}
		if(healSal){
			bg.currentPlayerMightToStartingValue();
			bg.currentPlayerSanityToStartingValue();
		}
		if(lst){
			bg.currentPlayerCanRerollDice(true, "All");
		}
		if(medkit){
			int b = bg.currentPlayerKnowledgeRoll();
			if(b >= 8){
				bg.targetPlayerHealPhysicalMaxStartingValue(3);
			}else if(b == 6 || b == 7){
				bg.targetPlayerHealUpToPhysicalMaxStartingValue(2);
			}else if(b == 4 || b == 5){
				bg.targetPlayerHealUpToPhysicalMaxStartingValue(1);
			}else if(b < 4){
				//Nothing Happens
			}
		}
		if(puz){
			int b = bg.currentPlayerKnowledgeRoll();
			if(b >= 6){
				bg.currentPlayerDrawItemCard();
				bg.currentPlayerDrawItemCard();
				discardAfterUse = true;
			}else{
				//Nothing Happens
			}
			
		}
		if(rab){
			bg.currentPlayerCanRerollDice(true, "One");
		}
		if(sms){
			bg.targetPlayerHealKnowledgeUpToStartingValue();
		}
		if(sk){
			bg.currentPlayerCanOpenLocks(true);
		}
		if(rp){
			bg.currentPlayerCanFallDownCollapsedRoomWithoutDamage(true);
			bg.currentPlayerCanUseMysticSlideWithoutDamage(true);
			bg.currentPlayerCanMoveFromBasementLandingToCoalChute(true);
		}
		if(mc){
			int b = currentPlayerRollDice(1);
			if(b == 2){
				bg.currentPlayerAddToPhysicalTrait(1);
			}else if(b == 1){
				bg.currentPlayerAddToMentalTrait(1);
			}else{
				bg.currentPlayerAddToPhysicalTrait(-1);
				bg.currentPlayerAddToMentalTrait(-1);
			}
		}
		
		
		if(discardAfterUse){
			discard(hauntRevealed);
		}
		return discardAfterUse;
	}
	
	public void discard(boolean hauntRevealed){
		if(changeTraits){
			bg.currentPlayerChangeKnowledge(-loseKnowledge, hauntRevealed);
			bg.currentPlayerChangeSanity(-loseSanity, hauntRevealed);
			bg.currentPlayerChangeMight(-loseMight, hauntRevealed);
			bg.currentPlayerChangeSpeed(-loseSpeed, hauntRevealed);
		}
		if(pa){
			bg.currentPlayerLosePhysicalArmor(-physicalArmor);
		}
		if(weapon){
			bg.currentPlayerRemoveWeapon(name);
		}
		if(candle){
			bg.currentPlayerRollExtraDiceOnEvents(false);
		}
		if(lst){
			bg.currentPlayerCanRerollDice(false, "All");
		}
		if(rab){
			bg.currentPlayerCanRerollDice(false, "One");
		}
		if(sk){
			bg.currentPlayerCanOpenLocks(false);
		}
		if(rp){
			bg.currentPlayerCanFallDownCollapsedRoomWithoutDamage(false);
			bg.currentPlayerCanUseMysticSlideWithoutDamage(false);
			bg.currentPlayerCanMoveFromBasementLandingToCoalChute(false);
		}
	}
	
	public void init(){
		if(cardNumber == 1){
			//Adrenaline Shot
			//A suringe containing a strange florescent liquid
			//Before you attempt a trait roll, use this card to add 4 to the result
			//Discard after use
			att = true;
			discardAfterUse = true;
			addToTrait = 4;
			name = "Adrenaline Shot";
			
			try {
				img = ImageIO.read(new File("src/Images/items/adrenalineShot.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 2){
			//Amulet of the Ages
			//Ancient Silver and Inlaid Gems Inscribed With blessings
			//Gain 1 Might, 1 Speed, 1 Sanity, and 1 Knowledge Now
			//Lose 3 Might, 3 Speed, 3 Sanity, and 3 Knowledge if you lose the amulet
			
			changeTraits = true;
			
			gainMight = 1;
			gainSpeed = 1;
			gainSanity = 1;
			gainKnowledge = 1;
			loseMight = 3;
			loseSpeed = 3;
			loseSanity = 3;
			loseKnowledge = 3;
			name = "Amulet of the Ages";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/amuletOfTheAges.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 3){
			//Angel Feather
			//A perfect feather fluttering in your hand
			//When making a roll of any kind, you can call out a number 0-8. Use that number instead of rolling.
			//Discard after use
			
			callOutNumber = true;
			discardAfterUse = true;
			name = "Angel Feather";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/angelFeather.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 4){
			//Armor
			//It's just prop armor from a renaissance fair, but it's still metal
			//Any time you take physical damage, take one less.
			//This cannot be stolen.
			
			physicalArmor = 1;
			pa = true;
			name = "Armor";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/armor.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 5){
			//Axe
			//Weapon
			//Very Sharp
			//You roll 1 additional die (max of 8) when making a might attack with this weapon.
			//You can't use another weapon while using this one.
			
			
			weaponPower = 1;
			weaponSkill = "might";
			weaponNeg = 0;
			weaponNegSkill = "";
			weapon = true;
			name = "Axe";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/axe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 6){
			//Bell
			//A brass bell that makes a resonant clang
			//Gain one sanity now
			//Lose one sanity if you lose the bell
			//After the haunt is revealed, once per turn you can attempt a sanity roll...
			// 5+ - Move any number of unimpeded heroes one space closer to you
			// 0-4 - The traitor can move any number of monsters one space closer to you.
			////If you are the traitor this has no effect.
			////If no traitor, ALL monsters move one space closer to you.
			
			changeTraits = true;
			useRightAway = true;
			
			gainSanity = 1;
			loseSanity = 1;
			
			dontDo = false;
			bell = true;
			name = "Bell";
			
			try {
				img = ImageIO.read(new File("src/Images/items/bell.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 7){
			//Blood Dagger
			//Weapon
			//A Nasty Weapon. Needles and tubes extend from the handle... and plunge right into your veins.
			//You roll 3 additional dice when making a might attack with this weapon. (Max of 8)
			//If you do, lose one speed.
			//You can't use another weapon while using this one.
			//This item can't be traded or dropped.
			//If it is stolen take 2 dice of physical damage.
			
			weaponPower = 3;
			weaponSkill = "might";
			weaponNeg = 1;
			weaponNegSkill = "speed";
			weapon = true;
			name = "Blood Dagger";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/bloodDagger.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 8){
			//Bottle
			//An Opaque vial containing a black liquid
			//After the haunt is revealed, and during your turn you can roll 3 dice and drink from the bottle
			// 6 - Choose a room. Put your explorer there.
			// 5 - Gain 2 might and 2 speed
			// 4 - Gain 2 knowledge and 2 sanity
			// 3 - Gain 1 knowledge and lose 1 might
			// 2 - Lose 2 knowledge and 2 sanity
			// 1 - Lose 2 might and 2 speed
			// 0 - Lose 2 from each trait
			//Discard After Use
			
			discardAfterUse = true;
			bottle = true;
			name = "Bottle";
			
			try {
				img = ImageIO.read(new File("src/Images/items/bottle.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 9){
			//Candle
			//It makes the shadows move-at least, you hope it's doing that.
			//If you draw an event card, roll one additional die for that event's trait rolls (Max of 8)
			//If you have the bell, book, and candle gain 2 in each trait
			//If you lose one of those three items, lose 2 in each trait
			
			candle = true;
			name = "candle";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/candle.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 10){
			//Dynamite
			//The fuse isn't lit... yet.
			//Instead of attacking, you can throw dynamite into an adjacent room through a connecting door.
			//Each explorer and monster in that room with might and speed traits must attempt a speed roll
			// 5+ - Take no damage from Dynamite
			// 0-4 - Take 4 points of physical damage
			//Discard after use
			
			discardAfterUse = true;
			dyn = true;
			name = "Dynamite";
			
			try {
				img = ImageIO.read(new File("src/Images/items/dynamite.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 11){
			//Healing Salve
			//A sticky paste in a shallow bowl
			//You can apply the healing salve to yourself or to another explorer in the same room.
			//If that explorer's might or speed is below it's starting value, then raise one or both to their starting values
			//Discard after use
			
			discardAfterUse = true;
			healSal = true;
			name = "Healing Salve";
			
			try {
				img = ImageIO.read(new File("src/Images/items/healingSalve.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 12){
			//Lucky Stone
			//A smooth, ordinary looking rock. you sense it will bring you good fortune.
			//After you attempt a roll of any kind, you can rub the stone to reroll any number of those dice
			//Discard after use.
			
			discardAfterUse = true;
			lst = true;
			name = "Lucky Stone";
			
			try {
				img = ImageIO.read(new File("src/Images/items/luckyStone.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 13){
			//Medical Kit
			//A doctor's bag, depleted in some critical resources
			//Once per turn you can attempt a knowledge roll to attempt to heal yourself or another explorer in the same room
			// 8+ - Gain 3 points in physical traits
			// 6-7 - Gain UP TO 2 points in physical traits
			// 4-5 - Gain one point in a physical trait
			// 0-3 - Nothing happens
			//Traits cannot go above their starting value when using medical kit
			
			medkit = true;
			name = "Medical Kit";
			
			try {
				img = ImageIO.read(new File("src/Images/items/medicalKit.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 14){
			//Puzzle Box
			//There must be a way to open it.
			//One during your turn you can attempt a knowledge roll to open the box:
			// 6+ - You open the box. Draw two item cards and discard the puzzle box
			// 0-5 - You just can't get it open.
			
			puz = true;
			name = "Puzzle Box";
			
			try {
				img = ImageIO.read(new File("src/Images/items/puzzleBox.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 15){
			//Rabbit's Foot
			//Not so lucky for the rabbit
			//Once during your turn you can reroll 1 die. You must keep the second roll
			
			rab = true;
			name = "Rabbit's Foot";
			
			try {
				img = ImageIO.read(new File("src/Images/items/rabbitsFoot.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 16){
			//Revolver
			//Weapon
			//An Old potent looking weapon.
			//You can use the revolver to attack with speed instead of might.
			//The opponent then defends with speed and takes physical damage
			//Roll 1 additional die on your speed roll, max of 8
			//With the revolver, you can attack anyone in the same room, or within line of sight.
			//If you attack someone in another room and lose, you don't take damage.
			//You can't use this weapon while you're using another one.
			
			weaponPower = 1;
			weaponSkill = "speed";
			weaponNeg = 0;
			weaponNegSkill = "";
			weapon = true;
			range = "lineOfSight";
			name = "Revolver";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/revolver.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 17){
			//Smelling Salts
			//Whew, that's a lungful
			//If you or another explorer's knowledge is below it's starting value and you're in the same room
			//You can use smelling salts to raise it back to starting value
			//Discard after use
			
			discardAfterUse = true;
			sms = true;
			name = "Smelling Salts";
			
			try {
				img = ImageIO.read(new File("src/Images/items/smellingSalts.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 18){
			//Toy Monkey
			//Clap, Clap, Clap, Clap
			//Gain 1 knowledge and 1 speed now
			//Lose it if you lose the monkey
			//When you enter a room with an explorer or monster, roll 2 dice
			// 3-4 - The monkey chatters happily
			// 2-1 - The monkey attacks an explorer or monster in the same room with a speed attack of 3
			// 0 - The monkey attacks you with a speed attack of 3
			//The toy monkey is not destroyed if defeated.
			
			changeTraits = true;
			gainKnowledge = 1;
			gainSpeed = 1;
			loseKnowledge = 1;
			loseSpeed = 1;
			useRightAway = true;
			monkey = true;
			name = "Toy Monkey";
			
			try {
				img = ImageIO.read(new File("src/Images/items/toyMonkey.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 19){
			//Skeleton Key
			//You can open the vault or locked safe without rolling dice.
			//If a haunt states that you can attempt to open the front door, open it without rolling dice
			
			sk = true;
			name = "Skeleton Key";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/skeletonKey.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 20){
			//Rope
			//Good solid rope, should come in handy.
			//When you can use rope:
			//// Fall down gallery or collapsed room without taking damage
			//// Use mystic slide without taking damage, but you still must roll the might roll
			//// Move from basement landing to coal chute
			//using rope doesn't count as moving a space.
			
			rp = true;
			name = "Rope";
			useRightAway = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/rope.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 21){
			//Mystic Coin
			//Once during your turn you can roll 1 die to flip the coin
			// 2 - It's heads. Gain 1 point in a physical trait.
			// 1 - It's tails. Gain 1 point in a physical trait.
			// 0 - It landed on it's edge. Lose 1 in mental and 1 in physical
			
			mc = true;
			name = "Mystic Coin";
			
			try {
				img = ImageIO.read(new File("src/Images/items/mysticCoin.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(cardNumber == 22){
			//Druidic Charm
			//Gain 1 Speed.
			//Lose 1 Speed if you lose it.
			//If you drop it, all monsters in the same room are stunned.
			//After it's dropped, discard it.
			
			changeTraits = true;
			gainSpeed = 1;
			loseSpeed = 1;
			name = "Druidic Charm";
			useRightAway = true;
			druidicCharm = true;
			
			try {
				img = ImageIO.read(new File("src/Images/items/druidicCharm.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		img = toBufferedImage(img.getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

}
