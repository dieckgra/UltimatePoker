import java.awt.Color;
import java.awt.event.KeyEvent;
//tabalbar@hawaii.edu
public class Setting {
	EZText textSon, textSoff, textMon, textMoff, cardText; // text sound on and off, text music on and off, explain card backgrounds
	EZImage boxSon, boxSoff, boxMon, boxMoff; // box sound on and off, box music on and off
	String blueBack, redBack, hearthStone, ignite;
	EZImage blueBackI, redBackI, hearthStoneI, igniteI;
	EZImage soundCheck, musicCheck, cardCheck;
	EZRectangle ruleRect;
	EZImage ruleButton, backButton, quitButton;
	boolean soundOn, musicOn, escape;
	int cardSel;
	EZSound guitar = EZ.addSound("bensound-happyrock.wav");
	
	Setting() {
		// Make the sound option
		textSon = EZ.addText(-2000, -2000, "Sound on", Color.white, 50);
		textSon.setFont("amaranth-regular.ttf");
		textSoff = EZ.addText(-2000, -2000, "Sound off", Color.white, 50);
		textSoff.setFont("amaranth-regular.ttf");
		boxSon = EZ.addImage("square.png", -2000, -2000);
		boxSoff = EZ.addImage("square.png", -2000, -2000);
		soundCheck = EZ.addImage("greencheck.png", -2000, -2000);
		// Make the music option
		textMon = EZ.addText(-2000, -2000, "Music on", Color.white, 50);
		textMon.setFont("amaranth-regular.ttf");
		textMoff = EZ.addText(-2000, -2000, "Music off", Color.white, 50);
		textMoff.setFont("amaranth-regular.ttf");
		boxMon = EZ.addImage("square.png", -2000, -2000);
		boxMoff = EZ.addImage("square.png", -2000, -2000);
		musicCheck = EZ.addImage("greencheck.png", -2000, -2000);
		// Make the card background changer option
		blueBack = "facedowncard.png";
		redBack = "tallyho.png";
		hearthStone = "hearthstone.png";
		ignite = "ignite.png";
		cardText = EZ.addText(-2000, -2000, "Select the card background you want to use", Color.white, 50);
		cardText.setFont("amaranth-regular.ttf");
		blueBackI = EZ.addImage(blueBack, -2000, -2000);
		redBackI = EZ.addImage(redBack, -2000, -2000);
		hearthStoneI = EZ.addImage(hearthStone, -2000, -2000);
		igniteI = EZ.addImage(ignite, -2000, -2000);
		cardCheck = EZ.addImage("greencheck2.png", -2000, -2000);
		cardSel = 1;
		// Rules
		ruleRect = EZ.addRectangle(-2000, -2000, 425, 200, Color.black, true);
		ruleButton = EZ.addImage("rules.png", -2000, -2000);
		backButton = EZ.addImage("back.png", -2000, -2000);
		quitButton = EZ.addImage("exitFinal.png", -2000, -2000);
		
	}
	
	public void showSettingStuff() {
		// Move the sound option into place
		textSon.translateTo(130, 260);
		textSoff.translateTo(130, 360);
		boxSon.translateTo(270, 260);
		boxSoff.translateTo(270, 360);
		if(soundOn==true) { soundCheck.translateTo(boxSon.getXCenter(),boxSon.getYCenter()); }
		else if(soundOn==false) { soundCheck.translateTo(boxSoff.getXCenter(),boxSoff.getYCenter()); }
		// Move the music option into place
		textMon.translateTo(1000, 260);
		textMoff.translateTo(1000, 360);
		boxMon.translateTo(1140, 260);
		boxMoff.translateTo(1140, 360);
		if(musicOn==true) { musicCheck.translateTo(boxMon.getXCenter(),boxMon.getYCenter()); }
		else if(musicOn==false) { musicCheck.translateTo(boxMoff.getXCenter(),boxMoff.getYCenter()); }
		// Move the card back option into place
		cardText.translateTo(600, 467); // The text above the cards
		blueBackI.translateTo(200, 650);
		redBackI.translateTo(467, 650);
		hearthStoneI.translateTo(733, 650);
		igniteI.translateTo(1000, 650);
		switch(cardSel) {
			case 1: cardCheck.translateTo(blueBackI.getXCenter(), blueBackI.getYCenter()); break;
			case 2: cardCheck.translateTo(redBackI.getXCenter(), redBackI.getYCenter()); break;
			case 3: cardCheck.translateTo(hearthStoneI.getXCenter(), hearthStoneI.getYCenter()); break;
			case 4: cardCheck.translateTo(igniteI.getXCenter(), igniteI.getYCenter()); break;
			default: cardCheck.translateTo(-2000, -2000);
		}
		ruleRect.translateTo(600, 310);
		ruleButton.translateTo(600, 310);
		backButton.translateTo(75, 75);
		quitButton.translateTo(1125, 75);
		
		escape = false;
		
		int clickX = 0;
		int clickY = 0;
		
		while(escape == false) {
			
			// Get the mouseʻs X and Y position
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();
			  
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				// Clicking the sound
				if ((textSon.isPointInElement(clickX,clickY)) || (boxSon.isPointInElement(clickX,clickY))) {
					soundCheck.translateTo(boxSon.getXCenter(), boxSon.getYCenter());
					soundOn = true;
				}
				else if ((textSoff.isPointInElement(clickX, clickY)) || (boxSoff.isPointInElement(clickX, clickY))) {
					soundCheck.translateTo(boxSoff.getXCenter(), boxSoff.getYCenter());
					soundOn = false;
				}
				// Clicking the music
				else if ((textMon.isPointInElement(clickX,clickY)) || (boxMon.isPointInElement(clickX,clickY))) {
					musicCheck.translateTo(boxMon.getXCenter(), boxMon.getYCenter());
					musicOn = true;
					guitar.loop();
				}
				else if ((textMoff.isPointInElement(clickX, clickY)) || (boxMoff.isPointInElement(clickX, clickY))) {
					musicCheck.translateTo(boxMoff.getXCenter(), boxMoff.getYCenter());
					musicOn = false;
					guitar.stop();
				}
				// Clicking the card image
				else if (blueBackI.isPointInElement(clickX,clickY)) {
					cardCheck.translateTo(blueBackI.getXCenter(), blueBackI.getYCenter());
					cardSel = 1;
				}
				else if (redBackI.isPointInElement(clickX,clickY)) {
					cardCheck.translateTo(redBackI.getXCenter(), redBackI.getYCenter());
					cardSel = 2;
				}
				else if (hearthStoneI.isPointInElement(clickX,clickY)) {
					cardCheck.translateTo(hearthStoneI.getXCenter(), hearthStoneI.getYCenter());
					cardSel = 3;
				}
				else if (igniteI.isPointInElement(clickX,clickY)) {
					cardCheck.translateTo(igniteI.getXCenter(), igniteI.getYCenter());
					cardSel = 4;
				}
				else if((ruleButton.isPointInElement(clickX, clickY)) || (ruleRect.isPointInElement(clickX, clickY))) {
					System.out.println("You have pressed the rule button");
				}
				else if(quitButton.isPointInElement(clickX,  clickY)) {
					System.exit(0);
				}
			}
			//if(EZInteraction.wasKeyPressed(KeyEvent.VK_SPACE)) {
			//	escape = true;
			//}
			if(EZInteraction.wasMouseLeftButtonPressed()) {
				 if (backButton.isPointInElement(clickX, clickY)) {
					 escape = true;
					 hideSettingStuff();
					 Menu.showMenuStuff();
				 }
			}
			EZ.refreshScreen();
		}
	}
	public void hideSettingStuff() {
		// Hide the sound option
		textSon.translateTo(-2000, -2000);
		textSoff.translateTo(-2000, -2000);
		boxSon.translateTo(-2000, -2000);
		boxSoff.translateTo(-2000, -2000);
		soundCheck.translateTo(-2000, -2000);
		// Hide the music option
		textMon.translateTo(-2000, -2000);
		textMoff.translateTo(-2000, -2000);
		boxMon.translateTo(-2000, -2000);
		boxMoff.translateTo(-2000, -2000);
		musicCheck.translateTo(-2000, -2000);
		// Hide the card background option
		cardText.translateTo(-2000, -2000);
		blueBackI.translateTo(-2000, -2000);
		redBackI.translateTo(-2000, -2000);
		hearthStoneI.translateTo(-2000, -2000);
		igniteI.translateTo(-2000, -2000);
		cardCheck.translateTo(-2000, -2000);
		// Hide the buttons
		ruleRect.translateTo(-2000, -2000);
		ruleButton.translateTo(-2000, -2000);
		backButton.translateTo(-2000, -2000);
		quitButton.translateTo(-2000, -2000);
		escape = true;
	}
	//hearthstone, facedowncard, tallyho, ignite
	public String getCardSel() {
		String cardString;
		switch(cardSel) {
			case 1: cardString = "facedowncard.png"; break;
			case 2: cardString = "tallyho.png"; break;
			case 3: cardString = "hearthstone.png"; break;
			case 4: cardString = "ignite.png"; break;
			default: cardString = "discardIcon.png";
		}
		return cardString;
	}

}