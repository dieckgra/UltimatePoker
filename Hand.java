import java.util.ArrayList;
//Made by Douglas Dieckgraefe
public class Hand {
	String name = "";
	public ArrayList<Card> hand;
	Hand() {
		hand = new ArrayList<Card>();
		
	}
	
	void print() {
		for(int i=0; i<hand.size(); i++) {
			hand.get(i).print();
		}
	}
	void print(int i) {
		hand.get(i).print();
	}
	int size() {
		return hand.size();
	}
	void add(Card j) {
		hand.add(j);
	}
	void set(int i, Card j) {
		hand.set(i, j);
	}
	Card get(int i) {
		return hand.get(i);
	}
	int getCardNum(int i) {
		int cardNum = hand.get(i).cardNum;
		return cardNum;
	}
	int getCardSuit(int i) {
		int cardSuit = hand.get(i).suit;
		return cardSuit;
	}
	void remove(int i) {
		hand.remove(i);
	}
	void remove() {
		for(int i=0; i<hand.size(); i++) {
			hand.remove(i);
		}
	}
	void clear() {
		hand.clear();
	}
	/*
	 * I wish to deal a hand to players for various games.
	 * This could include blackjack, 5-card poker and 7-card stud, Texas Hold'em, and more.
	 * 
	 */
	
	/*
	 * Let's start with Poker and Black Jack.
	 * 
	 * The total points for a hand will be added up to determine a winner.
	 * 
	 * For black Jack, two cards will be dealt to the user and two cards will be dealt to the dealer.
	 * The user will see both of their own cards but only one of the dealer's (1 face up, 1 face down)
	 * The dealer will automatically hit until 17, so doesn't really need to "see" the player's cards
	 * 
	 * Rules:
	 * The player may opt to "hit" (get another card) until the player "busts" (goes over 21) or decides to stop
	 * The dealer will automatically hit until 17 unless the player already busted or has blackjack (21 right off the bat).  
	 * The player (or dealer) with the highest hand (except over 21, a bust) wins.  The dealer wins all draws.
	 * An instant 21 from the first two cards is called "black jack" and wins instantly (even over 21 from hitting).
	 * Deciding not to "hit" is called "stay".  Splitting will not happen in the first version of this game. 
	 * 
	 * Card values must add up to 21 or as close as possible without going over. 
	 * cards 1 through 10 are valued the same as their number.  Cards of Jack, Queen, and King are all valued at 10.
	 * Aces can be valued at either 1 or 11, which ever benefits the player or dealer who holds the ace(s).
	 * 
	 * We will first develop these games for the console, then later implement them in a GUI.
	 * 
	 *
	void method(int game) {
		if(game==1) { // game selection (black jack "1")
			Card[] hand = new Card[2];
			blackJackHand();
		} else if(game==2) { // game selection (5 card draw "2")
			Card[] hand = new Card[5];
			fiveCardHand();
		} else if(game==3) { // game selection (7 card stud "3" or other game)
			Card[] hand = new Card[7];
		} else if(game==4) { // game selection (texas hold em "4" or other game) Have to double check rules on dealing
			Card[] hand = new Card[3];
		}
	}
	*/
	
	void blackJackHand() {
		
	}
	
	
	
	void fiveCardHand() {
		
	}

	
}
