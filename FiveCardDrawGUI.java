
public class FiveCardDrawGUI extends GameGUI {
	// make these array lists to grab the suit and card number of any card in the deck and pass it to the cardImage
	
	int dSuit = deck1.get(1).suit;
	int dCardNum = deck1.get(1).cardNum;
	int dhSuit;
	int phSuit;
	int dhCardNum;
	int phCardNum;
	int deckPosition;

	public static Deck deck2 = MainClass.menu.deck1;
	// Then do the same for dealers and players hands
	
	// Now we have variable shortcuts for card suits and numbers to pass through into our CardImage object arraylist seemlessly
	
	
	FiveCardDrawGUI() {
		super();
		fiveCardDrawGUI();

	}
	

	
	void fiveCardDrawGUI() {
		//Menu menu = new Menu();
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
				//j++;
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
		
		
		//deck1.makeDeckImage();
		
		for(int i=0; i<5; i++) {
			deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650);
		}
		//deck1.makeFaceDown();
		//deck1.moveFaceDownFCD();
		
		for(int i=0;i<5;i++) {
			
			//handI[0] = new CardImage(playerHand.get(i).suit,playerHand.get(i).cardNum);
			//175+i*200, 600
			//deck1.get(i).makeCardImage();
		}
	}
	
	static void removeGame() {
		deck2.hideDeckImage();
		//deck1.hideFaceDown();
	}
	
}
