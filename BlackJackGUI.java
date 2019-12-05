
public class BlackJackGUI extends GameGUI {

	
	int deckPosition;

	public static Deck deck2 = MainClass.menu.deck1;
	
	BlackJackGUI() {
		super();
		blackJackGUI();
	}
	void blackJackGUI() {
		BlackJack bj = new BlackJack();
		deck1=bj.deck1;
		
		bj.dealBlackJackFirst();
	
	deckPosition=0;
	for(int i=0; i<(deckPosition)+4; i++) {
		if(i%2==0) {
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
	deckPosition++;
	
	bj.sort(playerHand);
	bj.sort(dealerHand);
	
	System.out.println("\n\nPlayer Hand: ");
	for(int i=0; i<2; i++) { playerHand.get(i).print(); }
	System.out.println("\nDealer Hand: ");
	for(int i=0; i<2; i++) { dealerHand.get(i).print(); }

	for(int i=0; i<2; i++) {
		deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650);
	}
	deck2.deckImage[dealerHand.get(0).suit-1][dealerHand.get(0).cardNum-1].moveCardImage(175+1*205,300);
	
	
	
	}
	
	
	
}
	


