import java.awt.Color;

public class CardImage extends Card {
	CardImage(int c, int s) {
		super(c,s);
		//System.out.println("Hi!!");
		//print();
		makeCardImage(c,s);
	}
	
	public void makeCardImage(int s, int c) {
		
		int posX = -2000;
		int posY = -2000;
		suit = s;
		cardNum = c;
		
		String cardPhoto = "";
		Color textColor = new Color(0,0,0);
		switch(suit) {
			case 0: cardPhoto = ""; break;
			case 1: cardPhoto = "clubs.png"; textColor = new Color(255,0,0); break;
			case 2: cardPhoto = "diamonds.png"; break;
			case 3: cardPhoto = "hearts.png"; break;
			case 4: cardPhoto = "spades.png"; textColor = new Color(255,0,0); break;
		}
		
		cardImage = EZ.addImage(cardPhoto, posX, posY);
		cardNumStr = Integer.toString(cardNum);
		
		if(cardNum==1) { cardNumEZ = EZ.addText(posX, posY-15, "A", textColor, 75); }
		else if(cardNum==11) { cardNumEZ = EZ.addText(posX, posY-15, "J", textColor, 75); }
		else if(cardNum==12) { cardNumEZ = EZ.addText(posX, posY-15, "Q", textColor, 75); }
		else if(cardNum==13) { cardNumEZ = EZ.addText(posX, posY-15, "K", textColor, 75); }
		else { cardNumEZ = EZ.addText(posX, posY-15, cardNumStr, textColor, 75); }
		cardNumEZ.setFont("CinzelDecorative-Regular.ttf");
	}
	public void moveCardImage(int x, int y) {
		cardImage.translateTo(x,y);
		cardNumEZ.translateTo(x,y-15);
	}
	public void hideCardImage() {
		int posX = -2000;
		int posY = -2000;
		cardImage.translateTo(posX,posY);
		cardNumEZ.translateTo(posX,posY-15);
	}

	
}
