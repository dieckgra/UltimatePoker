
public class FiveCardDrawGUI extends GameGUI {
	FiveCardDrawGUI() {
		super();
		fiveCardDrawGUI();
	}
	
	void fiveCardDrawGUI() {
		for(int i=0;i<5;i++) {
			deck1.get(i).makeCardImage(175+i*200, 600);
		}
	}
	
	static void removeGame() {
		//for(int i=0; i<5; i++) {
			//EZ.removeEZElement(Card.cardImage);
		//}
	}
	
}
