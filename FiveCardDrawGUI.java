import java.awt.Color;
//Made by Douglas Dieckgraefe
public class FiveCardDrawGUI extends GameGUI {

	boolean escape, soundOn;
	int deckPosition;
	int clickX, clickY;
	static EZImage quitButton, backButton;
	static EZRectangle discardButton, endTurnButton, ddsButton, playAgainButton;
	static EZText discardT, discardMsg, endTurnT, endTurnMsg, ddsT, ddsMsg, scoreMsg, playAgainT;
	static int playerPoints, dealerPoints;
	static boolean alreadyDiscarded = false; // to check if cards have been discarded yet so it doesn't loop and moves on...
	static boolean isPlayersTurn = true; // to check whose turn we are on so dealer doesn't go before player is finished
	static boolean alreadyDDS = false; // to check of the dealer has done it's discard yet so it doesn't get to do it more than once
	static boolean playAgain = true; // Allow the user to play again, reseting everything once they are done with a round
	static EZRectangle[] discardRect = new EZRectangle[5];
	static EZImage[] indicator = new EZImage[5];
	static FiveCardDraw fcd = new FiveCardDraw();
	
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
		
		for (int i=0; i<5; i++) { discardRect[i] = EZ.addRectangle(-2000, -2000, 200, 250, Color.red, false); }
		for (int i=0; i<5; i++) { indicator[i] = EZ.addImage("discardIndicator.png", -2000, -2000); }
		
		endTurnButton = EZ.addRectangle(-2000, -2000, 170, 70, Color.orange, true);
		endTurnT = EZ.addText(-2000, -2000, "End Turn", Color.black, 40);
		endTurnT.setFont("Amaranth-Regular.ttf");
		endTurnMsg = EZ.addText(-2000, -2000, "Click the button to end your turn and start the dealer\'s turn...", Color.white, 30);
		endTurnMsg.setFont("Amaranth-Regular.ttf");
		
		ddsButton = EZ.addRectangle(-2000,-2000,170,70,Color.black,true);
		ddsT = EZ.addText(-2000, -2000, "AI Discard", Color.yellow, 35);
		ddsT.setFont("Amaranth-Regular.ttf");
		ddsMsg = EZ.addText(-2000, -2000, "Ready to let the dealer discard and draw?  Press the AI discard button...", Color.yellow, 25);
		ddsMsg.setFont("Amaranth-Regular.ttf");
		
		playAgainButton = EZ.addRectangle(-2000,-2000,170,70,Color.red,true);
		playAgainT = EZ.addText(-2000, -2000, "Play Again", Color.yellow, 35);
		playAgainT.setFont("Amaranth-Regular.ttf");
		scoreMsg = EZ.addText(-2000, -2000, "", Color.yellow, 25);
		scoreMsg.setFont("Amaranth-Regular.ttf");
	}
	

	
	void fiveCardDrawGUI() {
		while(playAgain == true) {
			
			playAgain = false;
			clickX = 0;
			clickY = 0;
			//Menu menu = new Menu();
			quitButton.translateTo(1125, 75);
			backButton.translateTo(75, 75);
			
			deck2.makeFaceDown(MainClass.menu.cardSel);
			deck2.moveFaceDownFCD();
			
			
			deck1 = fcd.deck1;
			
			fcd.dealFiveCardFirst(); 
			
			deckPosition = 0;
			for(int i=0; i<10; i++) {
				if(i%2==0) {
					playerHand.add(deck1.deck[i]);
					//System.out.println("Player Hand: " + playerHand.get(j).cardNum);
					//System.out.println("player gets 1");
					deckPosition++;
				} else {
					dealerHand.add(deck1.deck[i]);
					//System.out.println("Dealer Hand: " + dealerHand.get(j).cardNum);
					//System.out.println("dealer gets 1");
					deckPosition++;
					//System.out.println(deckPosition); // testing position and debugging
				}
			}
			//deckPosition--;
			
			fcd.sortFiveCards(playerHand);
			fcd.sortFiveCards(dealerHand);
			
			System.out.println("\n\nPlayer Hand: ");
			for(int i=0; i<5; i++) { playerHand.get(i).print(); }
			System.out.println("\nDealer Hand: ");
			for(int i=0; i<5; i++) { dealerHand.get(i).print(); }
			
			for(int i=0; i<5; i++) {
				deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650);
			}
			
			if(alreadyDiscarded == false) { showDiscardTandB(); }
			
			boolean[] isClicked = new boolean[5];
			for (int i=0; i<5; i++) { isClicked[i] = false; }
			
			
			
			while (escape == false) { 
				soundOn = MainClass.menu.soundOn;
				
				clickX = EZInteraction.getXMouse();
				clickY = EZInteraction.getYMouse();	
				if(EZInteraction.wasMouseLeftButtonPressed()) {
					if((alreadyDiscarded == false) && (isPlayersTurn == true)) {
						// This code loops through the 5 possible cards in the hand and "selects" or "de-selects" them for discarding
						for(int i=0; i<5; i++) {
							if(deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].cardImage.isPointInElement(clickX, clickY)) {
								if(soundOn == true) { MainClass.menu.coin.play(); }
								if(isClicked[i] == false) {
									discardRect[i].translateTo(175+i*205,650); 
									discardRect[i].pullToFront(); 
									indicator[i].translateTo(175+i*205, 565); 
									indicator[i].pullToFront(); 
									isClicked[i] = true;
								} else if(isClicked[i] == true) {
									discardRect[i].translateTo(-2000,-2000); 
									indicator[i].translateTo(-2000,-2000);
									isClicked[i] = false;
								}
							}
						}
						if(discardButton.isPointInElement(clickX, clickY)) {
							if (soundOn == true) { MainClass.menu.floop.play(); }
							int selectionSize = 0;
							// determine size of array by how many cards were selected for discarding
							for(int i=0; i<5; i++) { if (isClicked[i]==true) { selectionSize++; } } 
							// stand pat (selected no cards), don't make the array
							if (selectionSize==0) { System.out.println("You have chosen to stand pat"); }
							// make the array because some cards have been selected for discarding
							else if (selectionSize<=5) {
								System.out.println("You have chosen to discard " + selectionSize + " cards.");
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
								System.out.println("\n-------------------------\n\nDiscarding and drawing...\n\nPlayer hand: ");
								for(int i=0; i<5; i++) { // bring forth the new cards graphically
									deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650); 
									playerHand.get(i).print(); // and print them in console for debugging
								}
							}
							alreadyDiscarded = true;
							
						}
					}
					if((alreadyDiscarded == true) && (isPlayersTurn == true)) {
						hideDiscardTandB();
						// NOW WE NEED TO ADD IN RULES TO CALCULATE THE USER'S SCORE BASED OFF OF THE CARDS HE/SHE HOLDS
						fcd.rules(playerHand);
						playerPoints = fcd.scoring(playerHand);
						System.out.println("Player has " + playerPoints + " points.");
						showEndTurnTandB(); // let the user end their turn
						if(endTurnButton.isPointInElement(clickX, clickY)) {
							if (soundOn == true) { MainClass.menu.floop.play(); }
							System.out.println("\nYou have ended your turn...\n\n-------------------------\n\nDealer\'s turn is now starting...\n");
							hideEndTurnTandB();
							isPlayersTurn = false;
						}
					}
					if(isPlayersTurn == false) {
						if(alreadyDDS == false) {
							System.out.println("Dealer hand: ");
							for(int i=0; i<5; i++) { dealerHand.get(i).print(); }
							deck2.hideFaceDown();
							for(int i=0; i<5; i++) {
								deck2.deckImage[dealerHand.get(i).suit-1][dealerHand.get(i).cardNum-1].moveCardImage(175+i*205,300);
							}
							showDDSTandB();
							if(ddsButton.isPointInElement(clickX, clickY)) {
								if (soundOn == true) { MainClass.menu.floop.play(); }
								for(int i=0; i<5; i++) { // move the whole hand away so there is no overlap (the hand will be back in a flash)
									deck2.deckImage[dealerHand.get(i).suit-1][dealerHand.get(i).cardNum-1].moveCardImage(-2000,-2000);
								}
								deckPosition++;
								fcd.rules(dealerHand);
								fcd.DDS(dealerHand, deckPosition);
								fcd.sortFiveCards(dealerHand);
								fcd.rules(dealerHand);
								System.out.println("\nDealer Hand: ");
								dealerHand.print();
								dealerPoints = fcd.scoring(dealerHand);
								System.out.println("Dealer has " + dealerPoints + " points.");
								for(int i=0; i<5; i++) { // bring that new hand back instantly
									deck2.deckImage[dealerHand.get(i).suit-1][dealerHand.get(i).cardNum-1].moveCardImage(175+i*205,300);
								}
								hideDDSTandB();
								deck2.hideFaceDown();
								alreadyDDS = true;
							}
						}
						if(alreadyDDS == true) {
							showScorePlay();
							deck2.hideFaceDown();
							if(playerPoints > dealerPoints) { 
								if (soundOn == true) { MainClass.menu.winning.play(); }
								System.out.println("Player Wins!"); 
								scoreMsg.setMsg("Player Wins!!! Would you like to play again?");
							} else if(playerPoints == dealerPoints) { 
								if (soundOn == true) { MainClass.menu.winning.play(); }
								System.out.println("Player and Dealer have Tied!"); 
								scoreMsg.setMsg("It\'s a Tie!!! Would you like to play again?");
							} else if(playerPoints < dealerPoints) { 
								if (soundOn == true) { MainClass.menu.losing.play(); }
								System.out.println("Dealer Wins!"); 
								scoreMsg.setMsg("Dealer Wins!!! Would you like to play again?");
							}
							if(playAgainButton.isPointInElement(clickX, clickY)) {
								if (soundOn == true) { MainClass.menu.floop.play(); }
								hideScorePlay();
								deck2.hideDeckImage();
								escape = true;
								playAgain = true;
								alreadyDiscarded = false;
								isPlayersTurn = true;
								alreadyDDS = false;
								playerHand.clear();
								dealerHand.clear();
							}
						}
						
						
						
					}
				
					if(quitButton.isPointInElement(clickX, clickY)) {
						if(soundOn == true) { MainClass.menu.floop.play(); }
						System.out.println("Should be quiting...");
						System.exit(0);
					}
					if(backButton.isPointInElement(clickX, clickY)) {
						if(soundOn == true) { MainClass.menu.floop.play(); }
						System.out.println("Take us back...");
						escape = true;
						removeGame();
					}
				}
				EZ.refreshScreen();
			}
			escape = false;
		}
	}
	
	static void removeGame() {
		deck2.hideDeckImage();
		deck2.hideFaceDown();
		quitButton.translateTo(-2000,-2000);
		backButton.translateTo(-2000,-2000);
		hideDiscardIndicator();
		hideDiscardTandB();
		hideEndTurnTandB();
		hideDDSTandB();
		hideScorePlay();
		Menu.showMenuStuff();
		
	}
	static void hideDiscardIndicator() {
		for (int i=0; i<5; i++) { 
			discardRect[i].translateTo(-2000,-2000);
			indicator[i].translateTo(-2000,-2000);
		}
	}
	static void hideDiscardTandB() {
		discardMsg.translateTo(-2000,-2000);
		discardButton.translateTo(-2000,-2000);
		discardT.translateTo(-2000,-2000);
	}
	static void showDiscardTandB() {
		discardMsg.translateTo(475, 475);
		discardButton.translateTo(1000, 475);
		discardT.translateTo(1000, 475);
	}
	static void hideEndTurnTandB() {
		endTurnMsg.translateTo(-2000,-2000);
		endTurnButton.translateTo(-2000,-2000);
		endTurnT.translateTo(-2000,-2000);
	}
	static void showEndTurnTandB() {
		endTurnMsg.translateTo(700, 475);
		endTurnButton.translateTo(175, 475);
		endTurnT.translateTo(175, 475);
	}
	static void hideDDSTandB() {
		ddsMsg.translateTo(-2000,-2000);
		ddsButton.translateTo(-2000,-2000);
		ddsT.translateTo(-2000,-2000);
	}
	static void showDDSTandB() {
		ddsMsg.translateTo(475, 475);
		ddsButton.translateTo(1000, 475);
		ddsT.translateTo(1000, 475);
	}
	static void hideScorePlay() {
		scoreMsg.translateTo(-2000,-2000);
		playAgainButton.translateTo(-2000,-2000);
		playAgainT.translateTo(-2000,-2000);
	}
	static void showScorePlay() {
		scoreMsg.translateTo(700, 475);
		playAgainButton.translateTo(175, 475);
		playAgainT.translateTo(175, 475);
	}
}
