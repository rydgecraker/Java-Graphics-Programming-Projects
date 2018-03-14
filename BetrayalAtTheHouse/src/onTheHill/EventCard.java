package onTheHill;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EventCard {

	public BufferedImage img;
	public String name;

	public int x;
	public int y;
	public int width;
	public int height;
	public int cardNumber;
	public BetrayalGame bg;

	public static final int totalCards = 45;

	// Event Stuff
	public boolean amoh, ab, bv, bm, cd, cc, cp, db, ds, drip, fs, fn, gd, gk, hm, hs, iitm, iimtb, jt, lo, ls, ms = false;
	public boolean mftw, nv, pc, po, rw, rene, ro, sp = false;
	
	
	public EventCard(int x, int y, int width, int height, int cardNumber, BetrayalGame bg) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bg = bg;
		this.cardNumber = cardNumber; // A number 1 - 45;
		init();
	}

	public void draw(Graphics g) {
		// Draw Stuff
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

	public void events(boolean hauntRevealed){
		if(amoh){
			bg.getCurrentPlayerRoom().addBlessingToken();
		}
		if(ab){
			int b = bg.currentPlayerEventSpeedRoll();
			if(b >= 5){
				bg.currentPlayerGainSpeed(1, hauntRevealed);
			}else if(b >= 2 && b < 5){
				bg.currentPlayerTakeDiceMentalDamage(1, hauntRevealed);
			}else{
				bg.currentPlayerTakeDiceMentalDamage(1, hauntRevealed);
				bg.currentPlayerTakeDicePlysicalDamage(1, hauntRevealed);
			}
		}
		if(bv){
			int b = bg.currentPlayerSanityRoll();
			if(b >= 4){
				//Nothing Happens
			}else if(b > 1 && b < 4){
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
			}else{
				bg.explorerAttackAllyOrEnemyInSameRoomOrAdjacent("least might");
			}
		}
		if(bm){
			int b = bg.currentPlayerSanityRoll();
			if(b >= 4){
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else if(b > 1 && b < 4){
				bg.currentPlayerMoveToEntranceHall();
			}else{
				bg.currentPlayerTakeDiceMentalDamage(1, hauntRevealed);
				bg.currentPlayerTakeDicePhysicalDamage(1, hauntRevealed);
			}
		}
		if(cd){
			bg.currentPlayerCurrentRoomAddToken("Closet");
		}
		if(cc){
			int b = bg.currentPlayerSanityRoll();
			if(b >= 5){
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else if(b > 0){
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
			}else{
				bg.currentPlayerChangeSanity(-2, hauntRevealed);
			}
		}
		if(cp){
			bg.currentPlayerAttackedMight(4);
			if(bg.playerOtherThanCurrentHasWeapon("Spear")){
				bg.playerWithWeaponChangeMight("Spear", 2);
			}
		}
		if(db){
			
			int b = bg.currentPlayerSpeedRoll();
			if(b >= 3){
				bg.currentPlayerChangeSpeed(1, hauntRevealed);
			}else if(b >= 1){
				bg.currentPlayerTakePhysicalDiceDamage(1, hauntRevealed);
			}else{
				bg.currentPlayerTakePhysicalDiceDamage(2, hauntRevealed);
				bg.currentPlayerBuriedInDebris(true);
			}
		}
		if(ds){
			int b = bg.currentPlayerRollDice(6);
			if(b >= bg.getCurrentNumberOfOmens()){
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else{
				bg.currentPlayerTakeMentalDiceDamage(1, hauntRevealed);
			}
			
		}
		if(drip){
			bg.currentPlayerCurrentRoomAddToken("Drip");
		}
		if(fs){			
			int b = 0;
			if(bg.currentPlayerCurrentRoomName().equalsIgnoreCase("Chapel")){
				b = bg.currentPlayerRollDice(2);
			}else{
				b = bg.currentPlayerRollDice(1);
			}
			
			if(b == 4){
				bg.currentPlayerChangeMight(1, hauntRevealed);
				bg.nearestExplorerToCurrentPlayerChangeMight(1, hauntRevealed);
			}else if(b == 3){
				bg.currentPlayerChangeMight(1, hauntRevealed);
				bg.nearestExplorerToCurrentPlayerChangeSanity(-1, hauntRevealed);
			}else if(b == 2){
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
			}else if(b == 1){
				bg.currentPlayerChangeSpeed(-1, hauntRevealed);
			}else{
				bg.allExplorersChangeTraitOfChoice(-1, hauntRevealed);
			}
			
		}		
		if(fn){
			int b = bg.currentPlayerSanityRoll();
			if(b >= 4){
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else if(b >= 2){
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
			}else{
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
				bg.moveCurrentPlayerToGraveyardOrCryptIfOnBoard();
			}
		}
		if(gd){
			int b = bg.currentPlayerMightRoll();
			if(b >= 4){
				bg.currentPlayerChangeMight(1, hauntRevealed);
			}else{
				bg.currentPlayerTakesDamageEachTurn(1, "Grave Dirt");
			}
			
		}
		if(gk){
			int b = 0;
			if(bg.currentPlayerCurrentRoomName().equalsIgnoreCase("Gardens")){
				b = currentPlayerKnowledgeRoll(-2);
			}else{
				b = currentPlayerKnowledgeRoll();
			}
			
			if(b >= 4){
				bg.currentPlayerDrawItemCard();
			}else{
				bg.mightAttackAgainstCurrentPlayer(4);
			}
			
		}
		if(hm){
			boolean switcher = false;
			int b = bg.currentPlayerMightRoll();
			if(b < 2){
				switcher = true;
				bg.currentPlayerChangeMight(-1, hauntRevealed);
			}
			b = bg.currentPlayerSpeedRoll();
			if(b < 2){
				switcher = true;
				bg.currentPlayerChangeSpeed(-1, hauntRevealed);
			}
			int b = bg.currentPlayerKnowledgeRoll();
			if(b < 2){
				switcher = true;
				bg.currentPlayerChangeKnowledge(-1, hauntRevealed);
			}
			int b = bg.currentPlayerSanityRoll();
			if(b < 2){
				switcher = true;
				bg.currentPlayerChangeSanity(-1, hauntRevealed);
			}
			
			if(!switcher){
				bg.currentPlayerChangeAnyTrait(1, hauntRevealed);
			}
			
		}
		if(hs){
			
			int rolls[] = bg.allPlayerSanityRoll();
			boolean doIt[] = new boolean[rolls.length];
			
			for(int i = 0; i < rolls.length; i++){
				if(rolls[i] < 4 && rolls[i] > 0){
					bg.allTakeDiceMentalDamage(doIt, 1, hauntRevealed);
				}else{
					doIt[i] = false;
				}
			}
			
			for(int i = 0; i < rolls.length; i++){
				if(rolls[i] == 0){
					bg.allTakeDiceMentalDamage(doIt, 2, hauntRevealed);
				}else{
					doIt[i] = false;
				}
			}
			
		}
		if(iitm){
			
			if(bg.doesAnyPlayerHaveItem()){
				if(bg.currentPlayerHasItem()){
					bg.currentPlayerDiscardItem();
					bg.currentPlayerChangeKnowledge(1, hauntRevealed);
				}else{
					int temp = 1;
					while(!bg.playersLeftOfCurrentPlayerHasItem(temp)){
						temp++;
					}
					bg.playersLeftOfCurrentPlayerDiscardItem(temp);
					bg.playersLeftOfCurrentPlayerChangeKnowledge(1, hauntRevealed);
				}
			}
			
			
		}
		if(iimtb){
			
			int result = bg.currentPlayerChooseOptions("Look At Top 3 Cards", "Roll Dice and Save Result");
			if(result == 0){
				int res = bg.currentPlayerChooseOptions("Floor Tiles", "Items", "Events", "Omens");
				if(res == 0){
					bg.currentPlayerRearrangeFloorTiles(3);
				}else if(res == 1){
					bg.currentPlayerRearrangeItems(3);
				}else if(res == 2){
					bg.currentPlayerRearrangeEvents(3);
				}else if(res == 3){
					bg.currentPlayerRearrangeOmens(3);
				}
			}else{
				bg.currentPlayerRollDiceAndKeepResult(4);
			}
		}
		if(jt){
			if(bg.playerHasPuzzleBox()){
				bg.playerWithPuzzleBoxDrawItem();
				bg.playerWithPuzzleBoxDiscard();
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else{
				bg.currentPlayerTakeDiceMentalDamage(1, hauntRevealed);
			}
		}
		if(lo){			
			if(bg.currentPlayerCurrentRoomName().equalsIgnoreCase("Furnace") || bg.currentPlayerHasCandle()){
				
			}else{
				bg.currentPlayerLightsOut();
			}
		}
		if(ls){		
			bg.currentPlayerCurrentRoomAddSafe();
			
		}
		if(mftw){
			if(bg.anyExplorerInBasement()){
				int rolls[] = bg.allPlayersInBasementSanityRoll();
				boolean doIt[] = new boolean[rolls.length];
				
				for(int i = 0; i < rolls.length; i++){
					if(rolls[i] > 0 && rolls[i] < 4){
						bg.playerInBasementDiceMentalDamage(i, 1, hauntRevealed, "Event", 2);
					}else if(rolls[i] == 0){
						bg.playerInBasementDiceMentalDamage(i, 1, hauntRevealed, "Event", 3);
					}
				}
			}
		}
		if(ms){			
			if(!bg.allPlayersInBasement){
				if(!bg.currentPlayerInBasement){
					bg.currentPlayerCurrentRoomAddMysticSlide();
					bg.currentPlayerUseMysticSlide();
				}else{
					bg.firstPlayerNotInBasementCurrentRoomAddMysticSlide();
					bg.firstPlayerNotInBasementUseMysticSlide();
				}
			}
		}
		if(nv){			
			int b = bg.currentPlayerKnowledgeRoll();
			if(b >= 5){
				bg.currentPlayerChangeKnowledge(1, hauntRevealed);
			}
			
		}
		if(pc){			
			int b = currentPlayerRollDice(2);
			if(b == 4){
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else if(b == 3){
				bg.currentPlayerChangeKnowledge(1, hauntRevealed);
			}else if(b == 2 || b == 1){
				bg.currentPlayerDiceMentalDamage(1, hauntRevealed);
			}else{
				bg.currentPlayerDicePhysicalDamage(2, hauntRevealed);
			}
			
		}
		if(po){			
			String s = bg.currentPlayerChooseTrait();
			if(s.equalsIgnoreCase("Might")){
				int b = bg.currentPlayerMightRoll();
				if(b >= 4){
					bg.currentPlayerChangeMight(1, hauntRevealed);
				}else{
					if(bg.currentPlayerMightAtLowestValue()){
						bg.currentPlayerLowerRandomTraitToLowestValue();
					}else{
						bg.currentPlayerLowerMightToLowestValue();
					}
				}
			}else if(s.equalsIgnoreCase("Speed")){
				int b = bg.currentPlayerSpeedRoll();
				if(b >= 4){
					bg.currentPlayerChangeSpeed(1, hauntRevealed);
				}else{
					if(bg.currentPlayerSpeedAtLowestValue()){
						bg.currentPlayerLowerRandomTraitToLowestValue();
					}else{
						bg.currentPlayerLowerSpeedToLowestValue();
					}
				}
			}else if(s.equalsIgnoreCase("Knowledge")){
				int b = bg.currentPlayerKnowledgeRoll();
				if(b >= 4){
					bg.currentPlayerChangeKnowledge(1, hauntRevealed);
				}else{
					if(bg.currentPlayerKnowledgeAtLowestValue()){
						bg.currentPlayerLowerRandomTraitToLowestValue();
					}else{
						bg.currentPlayerLowerKnowledgeToLowestValue();
					}
				}
			}else if(s.equalsIgnoreCase("Sanity")){
				int b = bg.currentPlayerSanityRoll();
				if(b >= 4){
					bg.currentPlayerChangeSanity(1, hauntRevealed);
				}else{
					if(bg.currentPlayerSanityAtLowestValue()){
						bg.currentPlayerLowerRandomTraitToLowestValue();
					}else{
						bg.currentPlayerLowerSanityToLowestValue();
					}
				}
			}
		}
		if(rw){			
			bg.currentPlayerChooseWallSwitchLocation();
			bg.moveCurrentPlayerToWallSwitchLocation();			
		}
		if(rene){			
			if(bg.doesAnyPlayerHaveItem()){
				if(bg.currentPlayerHasItem()){
					bg.currentPlayerDrawItemCard();
				}else{
					int temp = 1;
					while(!bg.playersLeftOfCurrentPlayerHasItem(temp)){
						temp++;
					}
					bg.playersLeftOfCurrentPlayerDrawItemCard();
				}
			}
			
		}
		if(ro){			
			int b = bg.currentPlayerSanityRoll();
			if(b >= 5){
				bg.currentPlayerChangeSanity(1, hauntRevealed);
			}else if(b >= 2){
				bg.currentPlayerChangeMight(-1, hauntRevealed);
			}else if(b == 1){
				bg.currentPlayerChangeMight(-1, hauntRevealed);
				bg.currentPlayerChangeSpeed(-1, hauntRevealed);
			}else{
				bg.currentPlayerChangeAllTraits(-1, hauntRevealed);
			}			
		}
		if(sp){
			
			bg.currentPlayerCurrentRoomAddSecretPassage();
			int b = bg.currentPlayerRollDice(3);
			if(b >= 6){
				bg.currentPlayerGoToRoomOfChoice("ANY");
			}else if(b >= 4){
				bg.currentPlayerGoToRoomOfChoice("UPPER");
			}else if(b >= 2){
				bg.currentPlayerGoToRoomOfChoice("GROUND");
			}else{
				bg.currentPlayerGoToRoomOfChoice("BASEMENT");
			}
			
		}
		if(ss){
			
		}
		if(sw){
			
		}
		if(sl){
			
		}
		if(sk){
			
		}
		if(sm){
			
		}
		if(sh){
			
		}
		if(ssl){
			
		}
		if(sp){
			
		}
		if(tb){
			
		}
		if(tlo){
			
		}
		if(tv){
			
		}
		if(tw){
			
		}
		if(we){
			
		}
		if(wt){
			
		}
		if(wh){
			
		}		
	}

	public void init() {
		if (cardNumber == 1) {
			// A Moment Of Hope
			// Something feels strangely right about this room. Something is
			// resisting the evil of the house
			// Place the blessing token in this room.
			// Each explorer rolls 1 additional die in this room on all trait
			// rolls (Max of 8)

			amoh = true;
			name = "A Moment Of Hope";
			try {
				img = ImageIO.read(new File("src/Images/events/aMomentOfHope.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 2) {
			// Angry Being
			// It Emerges from the slime on the wall next to you
			// You must attempt a speed roll
			// 5+ - You get away. Gain 1 speed
			// 2-4 - Take one die of mental damage
			// 0-1 - Take one die of mental damage and one die of physical
			// damage

			ab = true;
			name = "Angry Being";
			try {
				img = ImageIO.read(new File("src/Images/events/angryBeing.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 3) {
			// Bloody Vision
			// The walls of this room are damp with blood. The blood drips from
			// teh ceiling, down the walls, over the furniture, and onto your
			// shoes. In a blink, it's gone.
			// You must attempt a Sanity Roll
			// 4+ - You steel yourself
			// 2-3 - Lose 1 Sanity
			// 0-1 - If an explorer or monster is in your room or an adjacent
			// room you must attack it.
			// //If there is more than one, pick the one with the lowest might

			bv = true;
			name = "Bloody Vision";
			try {
				img = ImageIO.read(new File("src/Images/events/bloodyVision.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 4) {
			// Burning Man
			// A man on fire runs through the room. His skin bubbles and cracks,
			// falling away from him and leaving a fiery skull that clatters to
			// the ground, bounces, rolls, and disappears.
			// You must attempt a Sanity roll
			// 4+ - You feel a little hot under the collar but otherwise fine.
			// Gain 1 Sanith.
			// 2-3 - Out, out, you must get out. Put your explorer in the
			// entrace hall
			// 0-1 - You burst into flames! Take 1 die of physical damage. Then
			// take 1 die of mental damage as you put out the flames.

			bm = true;
			name = "Burning Man";
			try {
				img = ImageIO.read(new File("src/Images/events/burningMan.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 5) {
			// Closet Door
			// That closet door is open... Just a crack. There must be something
			// inside.
			// Put the closet token in this room.
			// Once during an explorer's turn, that explorer can roll two dice
			// and open the closet.
			// 4 - draw an item card.
			// 2-3 - draw an event card.
			// 0-1 - draw an event card and remove the closet token.

			cd = true;
			name = "Closet Door";
			try {
				img = ImageIO.read(new File("src/Images/events/closetDoor.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 6) {
			// Creepy Crawlies
			// A thousand bugs spill out on yoru skin, under your clothes, and
			// in your hair.
			// You must attempt a Sanity roll
			// 5+ - You blink and they're gone. Gain 1 Sanity.
			// 1-4 - Lose 1 Sanity
			// 0 - Lose 2 Sanity
			cc = true;
			name = "Creepy Crawlies";
			try {
				img = ImageIO.read(new File("src/Images/events/creepyCrawlies.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 7) {
			// Creepy Puppet
			// You see one of those dolls that give you the willies. It jumps at
			// you with a tiny spear
			// A puppet rolls a might 4 attack against you.
			// If you take any damage and an explorer (other than you) has the
			// spear, they gain 2 might.

			cp = true;
			name = "Creepy Puppet";
			try {
				img = ImageIO.read(new File("src/Images/events/creepyPuppet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 8) {
			// Debris
			// Player falls from the walls and ceiling.
			// You must attempt a Speed roll
			// 3+ - You dodge the plaster. Gain 1 Speed.
			// 1-2 - You're buried in debris. Take 1 die of physical damage
			// 0 - You're buried in debris. Take 2 dice of physical damage
			// If you're buried, keep this card. You cant attack, move, or use
			// items until you're free.
			// Once during an explorer's turn, that explorer can attempt a Might
			// roll to free you.
			// 4+ - You're free
			// 0-3 - You're still stuck
			// If you're still trapped after 3 attempts, you break free
			// automatically on your next turn.
			// Discard when you're free

			db = true;
			name = "Debris";
			try {
				img = ImageIO.read(new File("src/Images/events/debris.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 9) {
			// Desquieting Sounds
			// A baby's cry, lost and abandonded. A scream. The crack of
			// breaking glass. Then silence.
			// Roll 6 dice. If you roll => the number of omens, you gain 1
			// sanity.
			// If you roll < number of omens, take 1 die mental damage

			ds = true;
			name = "Disquieting Sounds";
			try {
				img = ImageIO.read(new File("src/Images/events/desquietingSounds.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 10) {
			// Drip Drip Drip
			// A Rhythmic sound that needles at your brain.
			// Put the drip token in this room
			// Each explorer rolls 1 fewer die on all trait rolls in this room.
			// (Min of 1)

			drip = true;
			name = "Drip";
			try {
				img = ImageIO.read(new File("src/Images/events/dripDripDrip.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 11) {
			// Footsteps
			// The floorboards slowly creak. Dust rises. Footprints appear on
			// the dirty floor. And then, as they reach you, they are gone.
			// Roll 1 Die. (Roll 2 dice if in the chapel)
			// 4 - You and the nearest explorer gain 1 might
			// 3 - You gain 1 Might, and the nearest explorer loses 1 Sanity
			// 2 - Lose 1 Sanity
			// 1 - Lose 1 Speed
			// 0 - Each explorer loses 1 point from a trait of their choice

			fs = true;
			name = "Footsteps";
			try {
				img = ImageIO.read(new File("src/Images/events/footsteps.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 12) {
			// Funeral
			// You see an open coffin. You're inside it.
			// You must attempt a sanity roll.
			// 4+ - You blink and it's gone. Gain 1 Sanity.
			// 2-3 - The vision disturbs you. Lose 1 Sanity.
			// 0-1 - You're really in that coffin. Lose 1 sanity and 1 Might as
			// you dig yourself out. If the graveyard or crypt has been found,
			// put your explorer in one of those rooms (choose)

			fn = true;
			name = "funeral";
			try {
				img = ImageIO.read(new File("src/Images/events/funeral.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 13) {
			// Grave Dirt
			// This room is covered in a thick layer of dirt. You cough as it
			// gets on yoru skin and in your lungs.
			// You must attempt a Might roll:
			// 4+ - You shake it off. Gain 1 Might
			// 0-3 - Something is wrong. Keep this card. Take 1 point of
			// physical damage at the start of each turn.
			// If you kept this card: Discard this card if an item card
			// increases one of your traits or if you end your turn in the
			// Balcony, Gardens, Graveyard, Gymnasium, Larder, Patio, or Tower

			gd = true;
			name = "Grave Dirt";
			try {
				img = ImageIO.read(new File("src/Images/events/graveDirt.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 14) {
			// Groundskeeper
			// You turn to see a man in groundskeeper clothing. He raises his
			// shovel and charges. Inches from your face, he disappears, leaving
			// muddy footprints, and nothing more.
			// You must attempt a knowledge roll. (If you're in the gardens you
			// get 2 fewer dice)
			// 4+ - You find something in the mud. Draw an item card
			// 0-3 - The groundskeeper reappears and strikes you in the face
			// with the shovel. A Might 4 attack against you.

			gk = true;
			name = "Groundskeeper";
			try {
				img = ImageIO.read(new File("src/Images/events/groundskeeper.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 15) {
			// Hanged Men
			// A breeze chills the room. Before you, three men hang form frayed
			// ropes. They stare at you with bold, dead eyes. The trio swing
			// silently, then fade into dust that falls to the ground. You start
			// to choke.
			// You must attempt a roll for EACH trait
			// 2+ - that trait is unaffected.
			// 0-1 + Lose 1 from that trait.
			// If you rolled a 2+ on all four rolls, gain a point in the trait
			// of your choice

			hm = true;
			name = "Hanged Men";
			try {
				img = ImageIO.read(new File("src/Images/events/hangedMen.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 16) {
			// Hideous Shriek
			// It starts like a whisper, but ends in a soul-rendering shriek.
			// Each explorer must attempt a Sanity roll
			// 4+ - you resist the sound.
			// 1-3 - Take one die of mental damage
			// 0 - Take 2 dice of mental damage

			hs = true;
			name = "Hideous Shriek";
			try {
				img = ImageIO.read(new File("src/Images/events/hideousShriek.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 17) {
			// Image In The Mirror
			// If you don't have any item cards, this card effects the next
			// player to the left who does. If no player has an item card, then
			// this is discarded.
			// There is an old mirror in this room. Your frightened reflection
			// moves on it's own. You realize it is you from another time. You
			// need to help your reflection, so you write on the mirror.
			// "This will Help" Then you hand an item through the mirror.
			// Choose one of your item cards and shuffle it into the item stack.
			// Gain 1 Knowledge

			iitm = true;
			name = "Image In The Mirror";
			try {
				img = ImageIO.read(new File("src/Images/events/imageInTheMirror.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 18) {
			// It Is Meant To Be
			// You collapse on the floor, visions of future events pouring
			// through your head.
			// Choose 1 of the following options
			// // 1) Look at the top three tiles or cards of any one stack and
			// rearrange them in any order you want. Do not tell anyone the
			// result
			// // 2) Roll 4 dice and keep the result. You can use that result
			// once on any roll in the future. If that result is higher than the
			// maximum result, use the maximum result
			
			iimtb = true;
			name = "It Is Meant To Be";
			try {
				img = ImageIO.read(new File("src/Images/events/itIsMeantToBe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 19) {
			// Jonah's Turn
			// Two boys are playing with a wooden top.
			// "Would you like a turn Jonah?" one asks. "No." says Jonah.
			// "I want all the turns." Jonah takes the top and hits the other
			// boy in the face. The boy falls. Jonah keeps hitting him as they
			// fade from view.
			// If an explorer has the Puzzle Box, that explorer discards that
			// item and draws a new item.
			// If the puzzle box was discarded, You gain 1 sanity,
			// otherwise you take 1 die of mental damage
			
			jt = true;
			name = "Jonah's Turn";
			try {
				img = ImageIO.read(new File("src/Images/events/jonahsTurn.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 20) {
			// Light's Out
			// Your flashlight goes out. Don't worry. Someone else has
			// batteries.
			// Keep this card
			// You can only move one space per turn until you end yoru turn in
			// the same room as another explorer.
			// At the end of that turn, discard this card. You can move normally
			// again.
			// If you have the candle or end your turn in the furnace room,
			// discard this card.

			lo = true;
			name = "Lights Out";
			try {
				img = ImageIO.read(new File("src/Images/events/lightsOut.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 21) {
			// Locked Safe
			// Behind a portrait is a wall safe. It is trapped, of course.
			// put the safe token in this room.
			// Once during an explorers turn, that explorer can attempt a
			// knowledge roll to open the safe.
			// 5+ - Draw 2 item cards and remove the safe token
			// 2-4 - Take 1 die of physical damage. The safe wont open.
			// 0-1 - Take 2 dice of physical damage. The safe wont open.

			ls = true;
			name = "Locked Safe";
			try {
				img = ImageIO.read(new File("src/Images/events/lockedSafe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 22) {
			// Mists From The Walls
			// Mists pour out form the walls. There are faces in the mist. Human
			// and... inhuman.
			// Each explorer in the basement must attempt a sanity roll.
			// 4+ - The faces are tricks of light and shadow. All is well.
			// 1-3 - Take 1 die of mental damage (2 dice if the room has an
			// event symbol)
			// 0 - Take 1 die of mental damage (3 dice if the room has an event
			// symbol)

			mftw = true;
			name = "Mists From The Walls";
			try {
				img = ImageIO.read(new File("src/Images/events/mistsFromTheWalls.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 23) {
			// Mystic Slide
			// If you're in the basement, this event effects the next explorer
			// to your left that's not in the basement. Discard if all are in
			// the basement.
			// The floor falls from under you
			// Place the slide token in this room, then attempt a Might roll to
			// use the slide.
			// 5+ - You control the slide. Put yourself in any explored room on
			// a floor below the slide.
			// 0-4 - Draw tiles from the room stack until you draw a basement
			// room. Place the room tile. (If no basement tiles are left in the
			// stack, you may choose a room.) You fall to that room and take 1
			// die of physical damage.
			// The slide can be used by anyone after this who attempts a might
			// roll.

			ms = true;
			name = "Mystic Slide";
			try {
				img = ImageIO.read(new File("src/Images/events/mysticSlide.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 24) {
			// Night View
			// You see a vision of a ghostly couple walking the grounds,
			// silently strolling in their wedding best.
			// You must attempt a knowledge roll
			// 5+ - You recognize the ghost as the former inhabitants of the
			// house. You call thier names and they turn to you, whispering dark
			// secrets about the house. Gain 1 Knowledge.
			// 0-4 - You pull back in horror, unable to watch

			nv = true;
			name = "Night View";
			try {
				img = ImageIO.read(new File("src/Images/events/nightView.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (cardNumber == 25) {
			// Phone Call
			// A phone rings in the room. You feel compelled to answer it.
			// Roll 2 dice. A sweet little granny voice says:
			// 4 - "Tea and cakes! Tea and cakes! You always were my favorite!"
			// Gain 1 sanity
			// 3 - "I'm always here for you pattycakes. Watching ... " Gain 1
			// Knowledge
			// 1-2 - "I'm here sweetums! Give us a kiss!" take 1 die of mental
			// damage
			// 0 - "Bad little children must be punished!" Take 2 dice of
			// physical damage.

			pc = true;
			name = "Phone Call";
			try {
				img = ImageIO.read(new File("src/Images/events/phoneCall.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 26) {
			// Posession
			// A shadow seperates from the wall. As you stand in shock, the
			// shadow surrounds you and chills you to the core.
			// You must choose 1 trait and attempt a roll for it.
			// 4+ - you resist the shaow's corruption. Gain 1 in a trait of your
			// choice.
			// 0-3 - The shadow drains your energy. The chosen trait drops to
			// it's lowest value above X. If that trait is already at it's
			// lowest value. Drop a different trait to it's lowest value.

			po = true;
			name = "Posession";
			try {
				img = ImageIO.read(new File("src/Images/events/posession.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 27) {
			// Revolving Wall
			// The wall spins to another place
			// Place the wall switch token on a wall without an exit in this
			// room or a corner of this room.
			// If there isn't a room on the other side of the wall switch, draw
			// for one. if there are no more room tiles for this floor and you
			// must draw, discard this card.
			// Place your explorer in the new room. Icons in that room DO NOT
			// effect them this turn.
			// Once during an explorer's turn, if that explorer is in either
			// room, he or shee can attempt a knowedge roll to use the wall
			// switch.
			// 3+ - That explorer finds the hidden switch and goes through. This
			// doesn't count as moving a space.
			// 0-2 - That explorer can't find the hidden switch and can't go
			// though.

			rw = true;
			name = "Revolving Wall";
			try {
				img = ImageIO.read(new File("src/Images/events/revolvingWall.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 28) {
			// Rorrim Eht Ni Egami
			// If you don't have any item cards, this card effects the next
			// player to the left who does. If no player has an item card, then
			// this is discarded.
			// There is an old mirror in this room. Your frightened reflection
			// moves on it's own. You realize it is you from another time. Your
			// reflection writes on the mirror this will help. Then hands you an
			// item through the mirror.
			// Draw an item card

			rene = true;
			name = "Rorrim Eth Ni Egami";
			try {
				img = ImageIO.read(new File("src/Images/events/rorrimEhtNiEgami.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 29) {
			// Rotten
			// The smell in this room, it's horrible. Smells like death, like
			// blood. A slaughterhouse smell.
			// You must attempt a Sanity roll.
			// 5+ - Troubling odors, nothing more. Gain 1 sanity.
			// 2-4 - Lose 1 Might.
			// 1 - Lose 1 Might and 1 Speed.
			// 0 - You double over with nausea. Lose 1 Might, 1 Speed, 1
			// Knowledge, and 1 Sanity

			ro = true;
			name = "Rotten";
			try {
				img = ImageIO.read(new File("src/Images/events/rotten.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 30) {
			// Secret Passage
			// A section of the wall slides away. Behind it, a moldy tunnel
			// awaits.
			// Put a secret Passage token in this room. Roll 3 Dice and lace teh
			// second Secret Passage token in:
			// 6 - Any Room of your choice
			// 4-5 - Any upper level room
			// 2-3 - Any ground floor room
			// 0-1 - Any basement room.
			// You may then use the secret passage. Moving from one Secret
			// passage token to the other counts as moving one space.
			// On later turns, any explorer may use the secret passage.

			sp = true;
			name = "Secret Passage";
			try {
				img = ImageIO.read(new File("src/Images/events/secretPassage.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 31) {
			// Secret Stairs
			// A horrible creaking sound echoes around you. You've discovered a
			// secret stairwell
			// Put one SecretStairs token in this room and the second Secret
			// Stairs token on another level.
			// You may follow them if you wish. If you do, draw an event card in
			// the new room.
			// Using the secret stairs counts as one space.

			ss = true;
			name = "Secret Stairs";
			try {
				img = ImageIO.read(new File("src/Images/events/secretStairs.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 32) {
			// Shrieking Wind
			// The wind picks up, a slow crescendo to a screeching howl.
			// Each explorer in the Gardens, Graveyard, Patio, Tower, Balcony,
			// or in a room with an outside facing window, must attempt a Might
			// roll
			// 5+ - You Keep your footing.
			// 3-4 - The wind knocks you down. Take 1 die of physical damage
			// 1-2 - The wind chills your soul. Take 1 die of mental damage.
			// 0 - The wind knocks you down hard. Take 1 die of physical damage.
			// Put one of your items (if you have one) in the entrance hall.

			sw = true;
			name = "Shrieking Wind";
			try {
				img = ImageIO.read(new File("src/Images/events/shriekingWind.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 33) {
			// Silence
			// underground, everything goes silent.
			// Each explorer in the basement must attempt a Sanity roll.
			// 4+ - You wait calmly for your hearing to return.
			// 1-3 - You scream a silent scream. Take 1 die of mental damage.
			// 0 - You freak out. Take 2 dice of mental damage.

			sl = true;
			name = "Silence";
			try {
				img = ImageIO.read(new File("src/Images/events/silence.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 34) {
			// Skeletons
			// Mother and child, still embracing.
			// Put the skeletons token in this room.
			// Take one die of mental damage.
			// Once during an explorer's turn, that explorer can attempt a
			// Sanity roll to search the skeletons.
			// 5+ - Draw and item card. Discard the skeletons.
			// 0-4 - You dig around, but find nothing. Take 1 die of mental
			// damage.\

			sk = true;
			name = "Skeletons";
			try {
				img = ImageIO.read(new File("src/Images/events/skeletons.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 35) {
			// Smoke
			// Smoke billows around you. You cough, wiping away tears.
			// Put the smoke token in this room.
			// The smooke blocks line of sight from adjacent rooms.
			// An explorer rolls 2 fewer dice on all trait rolls while in this
			// room. (Min of 1)

			sm = true;
			name = "Smoke";
			try {
				img = ImageIO.read(new File("src/Images/events/smoke.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 36) {
			// Something Hidden
			// There's something odd about this room, but what? It's tickling
			// the back of your mind.
			// If you want to figure out what's odd, attempt a Knowledge roll.
			// 4+ - A section of wall slides away revealing an alcove. Draw an
			// item card
			// 0-3 - You can't figure it out, and that makes you a bit crazy.
			// Lose 1 sanity.

			sh = true;
			name = "Something Hidden";
			try {
				img = ImageIO.read(new File("src/Images/events/somethingHidden.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 37) {
			// Something Slimy
			// What's around your ankle? A bug? A tentacle? A dead hand clawing?
			// You must attempt a Speed roll:
			// 4+ - You break free. Gain 1 speed.
			// 1-3 - Lose 1 Might
			// 0 - Lose 1 Might and 1 Speed

			ssl = true;
			name = "Something Slimy";
			try {
				img = ImageIO.read(new File("src/Images/events/somethingSlimy.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 38) {
			// Spider
			// A spider the size of a fist lands on your shoulder... and crawls
			// into your hair.
			// You must attempt a speed roll to brush it away or a Sanity roll
			// to stand still.
			// 4+ - It's gone. Gain 1 in the trait you used to attempt this
			// roll.
			// 1-3 - It bites you. Take 1 die of physical damage.
			// 0 - It takes a chunk out of you. take 2 dice of physical damgae

			sp = true;
			name = "Spider";
			try {
				img = ImageIO.read(new File("src/Images/events/spider.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 39) {
			// The Beckoning
			// Outside. You must get outside. Fly to freedom!
			// Each explorer in the Gardens, Graveyard, Tower, Balcony, or in a
			// room with an outside-facing window must attempt a Sanity roll
			// 3+ - You back away from the ledge.
			// 0-2 - You jump to the patio. (If it isn't in the house, put it
			// there.) put your explorer in the patio and take 1 die of physical
			// damage

			tb = true;
			name = "The Beckoning";
			try {
				img = ImageIO.read(new File("src/Images/events/theBeckoning.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 40) {
			// The Lost One
			// A woman wearing a Civil war dress beckons to you, you fall into a
			// trance.
			// You must attempt a knowledge roll
			// 5+ - You break out of the trance. Gain 1 Knowledge.
			// 0-4 - Roll three dice to see where The Lost One leads you...
			// // 6 - Put your explorer in the entrance hall
			// // 4-5 - put your explorer on the upper landing
			// // 2-3 - Draw and place an upper level tile and place your
			// explorer there. If none left, place them in entrance hall.
			// // 0-1 - Draw and place a basement tile and place your explorer
			// there. If none are left, place them in entrance hall.

			tlo = true;
			name = "The Lost One";
			try {
				img = ImageIO.read(new File("src/Images/events/theLostOne.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 41) {
			// The Voice
			// I'm under the floor, buried under the floor... The voice whispers
			// once, then is gone.
			// You must attempt a Knowledge roll
			// 4+ - You find something under the floor. Draw an item card.
			// 0-3 - You dig and search for the voice, but to no avail.

			tv = true;
			name = "The Voice";
			try {
				img = ImageIO.read(new File("src/Images/events/theVoice.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 42) {
			// The Walls
			// This room is warm. Flesh like walls pulse with a steady hearbeat.
			// Your own heart beats with the rhythm of the house. You are drawn
			// into the walls... and emerge somewehre else.
			// You must draw the next room tile and put it in the house. Pur
			// your explorer in that room.

			tw = true;
			name = "The Walls";
			try {
				img = ImageIO.read(new File("src/Images/events/theWalls.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 43) {
			// Webs
			// Casually, you reach up to brush some webs aside... but they wont
			// brush away. They cling.
			// You must attempt a Might roll
			// 4+ - You break free. Gain 1 Might and discard this card.
			// 0-3 - You're stuck. Keep this card.
			// If you're stuck, you can't move, attack, or use an item until
			// you're free.
			// You, or any other explorer can attempt to break you free with a
			// Might 4 roll.
			// Anyone who fails an attempt is stuck until their next turn.
			// After 3 unsucessful attempts you may discard this card at the
			// start of your next turn.

			we = true;
			name = "Webs";
			try {
				img = ImageIO.read(new File("src/Images/events/webs.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 44) {
			// What The ...?
			// As you look back the way you came, you see... nothing. Just empty
			// fog and mist where a room used to be.
			// Move your current room somewhere else on this level. If no open
			// doors, move it to a differnt floor.

			wt = true;
			name = "What The";
			try {
				img = ImageIO.read(new File("src/Images/events/whatThe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (cardNumber == 45) {
			// WHOOPS!
			// You feel a body under your foot. Before you can leap away from
			// it, you're knocked over. A giggling voice runs asay from you.
			// Discard a random item card. (Not omen)
			wh = true;
			name = "Whoops!";
			try {
				img = ImageIO.read(new File("src/Images/events/whoops.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
