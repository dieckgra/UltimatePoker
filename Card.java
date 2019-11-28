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
		if (suit == 2) System.out.println("Hearts");
		if (suit == 3) System.out.println("Diamonds");
		if (suit == 4) System.out.println("Spades");
	}
	
	public void makeCardImage(int x, int y) {
		
		int posX = x;
		int posY = y;
		
		String cardPhoto = "";
		switch(suit) {
			case 0: cardPhoto = ""; break;
			case 1: cardPhoto = "clubs.png"; break;
			case 2: cardPhoto = "diamonds.png"; break;
			case 3: cardPhoto = "hearts.png"; break;
			case 4: cardPhoto = "spades.png"; break;
		}
		
		
		cardImage = EZ.addImage(cardPhoto, posX, posY);
		cardNumStr = Integer.toString(cardNum);
		
		if(cardNum==1) { cardNumEZ = EZ.addText(posX, posY-10, "A", new Color(255,0,0), 75); }
		else if(cardNum==11) { cardNumEZ = EZ.addText(posX, posY-10, "J", new Color(255,0,0), 75); }
		else if(cardNum==12) { cardNumEZ = EZ.addText(posX, posY-10, "Q", new Color(255,0,0), 75); }
		else if(cardNum==13) { cardNumEZ = EZ.addText(posX, posY-10, "K", new Color(255,0,0), 75); }
		else { cardNumEZ = EZ.addText(posX, posY-10, cardNumStr, new Color(255,0,0), 75); }
		
	}
	
	public void moveCardImage() {
		
	}
	
	
	
}
