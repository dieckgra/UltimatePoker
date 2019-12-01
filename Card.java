import java.awt.Color;

public class Card {
	
	// Member variables
	public int cardNum;
	public int suit;
	public EZImage cardImage;
	public String cardNumStr;
	public EZText cardNumEZ;
	
	
	// This is a constructor for the card.
	public Card(int c,int s) {
		cardNum = c;
		suit = s;
	}
	public Card(boolean joker) {
		cardNum = 0;
		suit = 0;
	}
	
	// This prints the card
	public void print(){
		if (cardNum == 1) System.out.print("Ace of ");
		else if (cardNum == 11) System.out.print("Jack of ");
		else if (cardNum == 12) System.out.print("Queen of ");
		else if (cardNum == 13) System.out.print("King of ");
		else System.out.print(cardNum + " of ");
		
		if (suit == 1) System.out.println("Clubs");
		if (suit == 2) System.out.println("Diamonds");
		if (suit == 3) System.out.println("Hearts");
		if (suit == 4) System.out.println("Spades");
	}

	public void moveCardImage() {
		
	}
	
	
	
}
