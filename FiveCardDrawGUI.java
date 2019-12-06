import java.awt.Color;

public class FiveCardDrawGUI extends GameGUI {
	// make these array lists to grab the suit and card number of any card in the deck and pass it to the cardImage
	
//	int dSuit = deck1.get(1).suit;
//	int dCardNum = deck1.get(1).cardNum;
//	int dhSuit;
//	int phSuit;
//	int dhCardNum;
//	int phCardNum;
	boolean escape, soundOn;
	int deckPosition;
	int clickX, clickY;
	static EZImage quitButton, backButton;
	static EZRectangle discardButton;
	static EZText discardT, discardMsg;
	
	static EZRectangle[] discardRect = new EZRectangle[5];
	static EZImage[] indicator = new EZImage[5];
	
	public static Deck deck2 = MainClass.menu.deck1;
	// Then do the same for dealers and players hands
	
	// Now we have variable shortcuts for card suits and numbers to pass through into our CardImage object arraylist seemlessly
	
	
	FiveCardDrawGUI() {
		super();
		escape = false;
		quitButton = EZ.addImage("exitFinal.png", -2000, -2000);
		backButton = EZ.addImage("back.png", -2000, -2000);
		discardButton = EZ.addRectangle(-2000,-2000,170,70,Color.darkGray,true);
		discardT = EZ.addText(-2000, -2000, "Discard", Color.red, 40);
		discardT.setFont("Amaranth-Regular.ttf");
		discardMsg = EZ.addText(-2000, -2000, "Please select the cards you wish to discard by clicking on them, then press the discard button...", Color.white, 20);
		discardMsg.setFont("Amaranth-Regular.ttf");
		for (int i=0; i<5; i++) {
			discardRect[i] = EZ.addRectangle(-2000, -2000, 200, 250, Color.red, false);
		}
		for(int i=0; i<5; i++) {
			indicator[i] = EZ.addImage("discardIndicator.png", -2000, -2000);
		}

		//fiveCardDrawGUI();
		
	}
	

	
	void fiveCardDrawGUI() {
		clickX = 0;
		clickY = 0;
		//Menu menu = new Menu();
		quitButton.translateTo(1125, 75);
		backButton.translateTo(75, 75);
		
		deck2.makeFaceDown(MainClass.menu.cardSel);
		deck2.moveFaceDownFCD();
		
		FiveCardDraw fcd = new FiveCardDraw();
		deck1 = fcd.deck1;
		
		fcd.dealFiveCardFirst(); 
		
		deckPosition = 0;
		for(int i=deckPosition; i<deckPosition+10; i++) {
			if(i%2==0) {
				playerHand.add(deck1.deck[i]);
				//System.out.println("Player Hand: " + playerHand.get(j).cardNum);
			} else {
				dealerHand.add(deck1.deck[i]);
				//System.out.println("Dealer Hand: " + dealerHand.get(j).cardNum);
				deckPosition++;
				System.out.println(deckPosition); // testing position and debugging
			}
		}
		deckPosition++;
		
		fcd.sortFiveCards(playerHand);
		fcd.sortFiveCards(dealerHand);
		
		System.out.println("\n\nPlayer Hand: ");
		for(int i=0; i<5; i++) { playerHand.get(i).print(); }
		System.out.println("\nDealer Hand: ");
		for(int i=0; i<5; i++) { dealerHand.get(i).print(); }
		
		for(int i=0; i<5; i++) {
			deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650);
		}
		
		discardMsg.translateTo(475, 475);
		discardButton.translateTo(1000, 475);
		discardT.translateTo(1000, 475);
		
		boolean[] isClicked = new boolean[5];
		for(int i=0; i<5; i++) { isClicked[i] = false; }
		
		while(escape==false) { 
			soundOn = MainClass.menu.soundOn;
			
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();	
			if(EZInteraction.wasMouseLeftButtonPressed()) {
				if(quitButton.isPointInElement(clickX, clickY)) {
					if(soundOn==true) { MainClass.menu.floop.play(); }
					System.out.println("Should be quiting...");
					System.exit(0);
				}
				if(backButton.isPointInElement(clickX, clickY)) {
					if(soundOn==true) { MainClass.menu.floop.play(); }
					System.out.println("Take us back...");
					escape = true;
					removeGame();
				}
				// This code loops through the 5 possible cards in the hand and "selects" or "de-selects" them for discarding
				for(int i=0; i<5; i++) {
					if(deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].cardImage.isPointInElement(clickX, clickY)) {
						if(soundOn==true) { MainClass.menu.coin.play(); }
						if(isClicked[i]==false) {
							discardRect[i].translateTo(175+i*205,650); 
							discardRect[i].pullToFront(); 
							indicator[i].translateTo(175+i*205, 565); 
							indicator[i].pullToFront(); 
							isClicked[i] = true;
						} else if(isClicked[i]==true) {
							discardRect[i].translateTo(-2000,-2000); 
							indicator[i].translateTo(-2000,-2000);
							isClicked[i] = false;
						}
					}
				}
				if(discardButton.isPointInElement(clickX, clickY)) {
					if(soundOn==true) { MainClass.menu.floop.play(); }
					int selectionSize = 0;
					// determine size of array by how many cards were selected for discarding
					for(int i=0; i<5; i++) { if(isClicked[i]==true) { selectionSize++; } } 
					// stand pat (selected no cards), don't make the array
					if(selectionSize==0) {}
					// make the array because some cards have been selected for discarding
					else if(selectionSize<=5) {
						int[] selection = new int[selectionSize];
						for(int i=0; i<selectionSize; i++) {
							for(int j=0; j<5; j++) {
								if(isClicked[j]==true) {
									selection[i] = j;
									isClicked[j] = false;
									break;
								}
							}
						}
						hideDiscardIndicator();
						deck2.hideDeckImage(); // hide the old cards
						// handle the discard and redraw in the virtual hand (make the new cards)
						for(int i=0; i<selectionSize; i++) { playerHand.set(selection[i], deck1.deck[deckPosition]); deckPosition++; }
						fcd.sortFiveCards(playerHand); // Sort the new cards
						for(int i=0; i<5; i++) { // bring forth the new cards graphically
							deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650); 
						}
					}
				}
				
			}
			
			EZ.refreshScreen();
		}
		escape = false;
	}
	
	static void removeGame() {
		deck2.hideDeckImage();
		deck2.hideFaceDown();
		quitButton.translateTo(-2000,-2000);
		backButton.translateTo(-2000,-2000);
		discardButton.translateTo(-2000,-2000);
		discardT.translateTo(-2000,-2000);
		discardMsg.translateTo(-2000,-2000);
		hideDiscardIndicator();
		Menu.showMenuStuff();
		
	}
	static void hideDiscardIndicator() {
		for (int i=0; i<5; i++) { 
			discardRect[i].translateTo(-2000,-2000);
			indicator[i].translateTo(-2000,-2000);
		}
	}
	
}
