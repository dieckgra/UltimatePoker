import java.awt.Color;

public class BlackJackGUI extends GameGUI {

	
	int deckPosition;
	int clickX, clickY;
	int playerHandTotal = 0;
	int dealerHandTotal = 0;
	//int handScore = 0;
	int cardCount = 2;
	boolean escape, soundOn;
	static EZImage quitButton, backButton, hitButton, standButton;
	static EZRectangle discardButton;
	static EZText discardT, discardMsg, playerTotal, dealerTotal, where;

	public static Deck deck2 = MainClass.menu.deck1; //deck 2 here
	static EZRectangle[] discardRect = new EZRectangle[2];
	static EZImage[] indicator = new EZImage[2];
	
	BlackJackGUI() {//starts game
		super();
		//blackJackGUI();
		escape = false;
		quitButton = EZ.addImage("exitFinal.png", -2000, -2000);
		backButton = EZ.addImage("back.png", -2000, -2000);
		discardButton = EZ.addRectangle(-2000,-2000,170,70,Color.darkGray,true);
		discardT = EZ.addText(-2000, -2000, "Discard", Color.red, 40);
		
		where = EZ.addText(600, 600, "---,---", Color.red, 40);
		
		playerTotal = EZ.addText(-2000, -2000, "Player Total: " + "0", Color.white, 40);
		dealerTotal = EZ.addText(-2000, -2000, "Dealer Total: " + "0" , Color.white,40);
		playerTotal.setFont("Amaranth-Regular.ttf");
		dealerTotal.setFont("Amaranth-Regular.ttf");
		
		
		discardT.setFont("Amaranth-Regular.ttf");
		discardMsg = EZ.addText(-2000, -2000, "Please select the cards you wish to discard by clicking on them, then press the discard button...", Color.white, 20);
		discardMsg.setFont("Amaranth-Regular.ttf");
		
		hitButton = EZ.addImage("hitButton.png", -2000, -2000);
		standButton = EZ.addImage("standButton.png", -2000, -2000);
	}
	void blackJackGUI() {
		clickX = 0;
		clickY = 0;
		//Menu menu = new Menu();
		quitButton.translateTo(1125, 75);
		backButton.translateTo(75, 75);
		hitButton.translateTo(567, 466);
		standButton.translateTo(678, 466);
		playerTotal.translateTo(971, 646);
		dealerTotal.translateTo(971, 286);
		
		
	
		
		BlackJack bj = new BlackJack();//makes game
		deck1=bj.deck1; //deck 1 frame of game
		deck2.makeFaceDown(MainClass.menu.cardSel);//makes face down cards
		deck2.moveFaceDownBJ(); //grabs one
		bj.dealBlackJackFirst();//shuff logic deck 
		
	
	deckPosition=0;
	for(int i=0; i<(deckPosition)+4; i++) {//deals
		if(i%2==0) {//deals logic player dealer player dealer
			playerHand.add(deck1.deck[i]);
			//System.out.println("Player Hand: " + playerHand.get(j).cardNum);
		}
		else {
			dealerHand.add(deck1.deck[i]);
			//System.out.println("Dealer Hand: " + dealerHand.get(j).cardNum);
			deckPosition++;
			System.out.println(deckPosition); // testing position and debugging
			}
	}
	deckPosition++;//increase counter for deck, where @
	
	bj.sort(playerHand); //orders deck
	bj.sort(dealerHand);
	
	System.out.println("\n\nPlayer Hand: "); //con
	for(int i=0; i<2; i++) { playerHand.get(i).print(); }
	System.out.println("\nDealer Hand: ");
	for(int i=0; i<2; i++) { dealerHand.get(i).print(); }

	for(int i=0; i<2; i++) {//draws cards
		deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650);//moves 2 player cards face up
	}
	deck2.deckImage[dealerHand.get(0).suit-1][dealerHand.get(0).cardNum-1].moveCardImage(175+1*205,300);//moves one face up
	
	//playerTotal.setMsg("Player Total: " + (score(playerHand) - 0));
	
	
	boolean[] isClicked = new boolean[2];
	for(int i=0; i<2; i++) { isClicked[i] = false; }
	
	while(escape==false) { 
		soundOn = MainClass.menu.soundOn;
	
		
//		for(int i = 0; i < playerHand.size();i++) {
//			playerHandTotal += playerHand.getCardNum(i);
//		}
		
		clickX = EZInteraction.getXMouse();
		clickY = EZInteraction.getYMouse();	
		
		where.setMsg(clickX + ", " + clickY);
		
		if(cardCount == 2) {
			playerTotal.setMsg("Player Total: " + (score(playerHand) - 20)); //!!!!!!!!!!!!!!!
		}
		//playerHandTotal = score(playerHand);
		
		
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
			if(hitButton.isPointInElement(clickX, clickY)) {
			bj.sort(playerHand); // Sort the new cards
			deck2.deckImage[playerHand.get(cardCount).suit-1][playerHand.get(cardCount).cardNum-1].moveCardImage(175+cardCount*205,650); 			
			playerTotal.setMsg("Player Total: " + (score(playerHand) -0)); //!!!!!!!!!!!!!!!!!!!!!!!
			cardCount++;
			}
			
			if(discardButton.isPointInElement(clickX, clickY)) {
				if(soundOn==true) { MainClass.menu.floop.play(); }
				
					}
//					hideDiscardIndicator();
//					deck2.hideDeckImage(); // hide the old cards
//					// handle the discard and redraw in the virtual hand (make the new cards)
//					for(int i=0; i<i+1; i++) { playerHand.set(selection[i], deck1.deck[deckPosition]); deckPosition++; }
//					bj.sort(playerHand); // Sort the new cards
//					for(int i=0; i<2; i++) { // bring forth the new cards graphically
//						deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650); 
//					}
//				}
			}
		EZ.refreshScreen();
		}
	escape = false;
	
	}
	
	
	
	//use BJ class framework for liek hit or stay //
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
	
	public static int score(Hand hand) {
		int handScore = 0;
		for(int i=(hand.size()-1); i>=0; i--) {
			if(hand.get(i).cardNum >= 10) { 
				handScore += 10; 
				}
			else if(hand.get(i).cardNum >= 2) { 
				handScore += hand.get(i).cardNum; 
				}
			else { // Must be an ace...
				if(handScore > 10) { handScore += 1; } 
				else { handScore += 11; } 
			}
		}
		return handScore;
	}
	
	
}

