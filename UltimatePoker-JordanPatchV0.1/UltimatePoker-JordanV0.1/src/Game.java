
public class Game {
	static Hand playerHand;
	static Hand dealerHand;
	static Deck deck1;
	Game() {
		//System.out.println("How many players do you want?  \nJust kidding you only get 1, unless I have time to make more");
		playerHand = new Hand();
		dealerHand = new Hand();
		deck1 = new Deck();
	}
	void replace() {
		int deckPosition = 0;
		for(int i=0; i<5; i++) { playerHand.hand.set(i, deck1.deck[deckPosition]); deckPosition++; }
	}
	
	
}
