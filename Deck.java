import java.awt.Color;
import java.util.Random;

public class Deck {
	Card[] deck; // Make an array of cards called deck
	
	EZImage[][] cardImage;
	String suit;
	EZText[][] cardNumI;
	CardImage[][] deckImage;
	String num;
	Card tempCard;
	EZImage[] facedown = new EZImage[5];
	
	Deck() {
		deck = new Card[52];  // Assign 52 cards to deck

		int counter = 0;
		
		for (int s = 1; s<=4; s++) { // Make 4 suits
			for(int c=1; c<=13; c++){ // Make 13 cards per suit
				num = Integer.toString(c);
				// Make a card
				Card aCard = new Card(c,s); // Make a new card with the suit and card number parameters
				//System.out.println(c+" "+s);
				aCard.print();
				// Store the card in the deck.

				deck[counter] = aCard;
				counter++;
			}
		}
	}
	
	
	
	
	/*
	 * Do NOT use this joker deck constructor!!! Not yet, at least.
	 */
	Deck(int j) { // Special constructor to make decks with jokers... We will only pursue this if time permits
		int jokers = j;
		deck = new Card[52+jokers];
		int counter = 0;
		for (int s = 1; s<=4; s++) {
			for(int c=1; c<=13; c++){
				// Make a card
				Card aCard = new Card(c,s);
				//System.out.println(c+" "+s);
				//aCard.print();
				// Store the card in the deck.
				deck[counter] = aCard;
				counter++;
			}
		}
		for(int i=52; i<52+jokers; i++) {
			Card aCard = new Card(true);
			deck[i] = aCard;
		}
		// Add in most of the same code from the regular constructor
	}
	/*
	 * Do NOT use this joker deck constructor!!! Not yet, at least.
	 */
	
	
	// This prints the deck
	public void print(){
		for(int i=0; i<deck.length; i++) {
			// Set whether it is a face card or a number card and display it
			if (deck[i].cardNum == 1) System.out.print("Ace of ");
			else if (deck[i].cardNum == 11) System.out.print("Jack of ");
			else if (deck[i].cardNum == 12) System.out.print("Queen of ");
			else if (deck[i].cardNum == 13) System.out.print("King of ");
			else System.out.print(deck[i].cardNum + " of ");
			// then in the same line, display the suit of the card
			if (deck[i].suit == 1) System.out.println("Clubs");
			if (deck[i].suit == 2) System.out.println("Hearts");
			if (deck[i].suit == 3) System.out.println("Diamonds");
			if (deck[i].suit == 4) System.out.println("Spades");
		}
	}
	
	
	
	// A class function that will shuffle our deck(s)
	void shuffle() { 
		// Our random number generator
		Random randomGenerator = new Random();
		// Shuffle 300,000 times to ensure a good shuffle!
		for (int shuffle=0; shuffle < 300000; shuffle++) {
		// Iterate through each card in the deck
			for (int i=0; i<52; i++){
				// Use the random number generator to choose a card to swap with.
				int j = randomGenerator.nextInt(52);
				// Swap card i with card j in the deck
				tempCard = deck[j];
				deck[j]=deck[i];
				deck[i]=tempCard;
			}
		}
	}
	
	Card get(int i) {
		return deck[i];
	}
	
	/*
	void makeCardImage() {
		cardImage = new EZImage[4][13];
		switch(suit) {
			
		}
			
		suit = "clubs.png";
		suit = "diamonds.png";
		suit = "hearts.png";
		suit = "spades.png";
		cardNumI = new EZText[4][13];
		
		for(int i=0; i<1; i++) {
			for(int j=0; j<13; j++) {
				cardImage[i][j] = EZ.addImage(suit, 100, 100);
				cardNumI[i][j] = EZ.addText(100, 100, num, Color.black, 50);
			}
		}
	}
	*/
	
	
	void makeDeckImage() {
		deckImage = new CardImage[4][13];
		for(int s=0; s<4; s++) {
			for(int c=0; c<13; c++) {
				deckImage[s][c] = new CardImage(s+1,c+1);
			}
		}
	}
	void hideDeckImage() {
		for(int s=0; s<4; s++) {
			for(int c=0; c<13; c++) {
				deckImage[s][c].hideCardImage();
			}
		}
	}
	public void makeFaceDown(String image) {
		for(int i=0; i<5; i++) {
			if(true) {
				facedown[i] = EZ.addImage(image,-2000,-2000);
			} //else if(true) {
				//facedown[i] = setting.redBackI;
			//}
		}
	}
	public void moveFaceDownFCD() {
		for(int i=0; i<5; i++) {
			facedown[i].translateTo(175+i*205,300);
		}
	}
	public void hideFaceDown() {
		for(int i=0; i<5; i++) {
			facedown[i].translateTo(-2000,-2000);
		}
	}
	
	
}
