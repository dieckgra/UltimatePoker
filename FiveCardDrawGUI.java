
public class FiveCardDrawGUI extends GameGUI {
	// make these array lists to grab the suit and card number of any card in the deck and pass it to the cardImage
	
	int dSuit = deck1.get(1).suit;
	int dCardNum = deck1.get(1).cardNum;
	int dhSuit;
	int phSuit;
	int dhCardNum;
	int phCardNum;
	// Then do the same for dealers and players hands
	
	// Now we have variable shortcuts for card suits and numbers to pass through into our CardImage object arraylist seemlessly
	
	
	FiveCardDrawGUI() {
		super();
		fiveCardDrawGUI();
	}
	
	void fiveCardDrawGUI() {
		for(int i=0; i<52; i++) {
			
		}
		
		for(int i=0;i<5;i++) {
			CardImage[] cardI = new CardImage[5];
			
			cardI[0] = new CardImage(deck1.get(1).suit,deck1.get(1).cardNum);
			
			deck1.get(i).makeCardImage(175+i*200, 600);
		}
	}
	
	static void removeGame() {
		//for(int i=0; i<5; i++) {
			//EZ.removeEZElement(Card.cardImage);
		//}
	}
	
}
