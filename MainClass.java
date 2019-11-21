import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class MainClass {
	static Scanner scanner = new Scanner(System.in);
	//static Card[] deck = new Card[52];
	static Deck deck1 = new Deck();
	
	static Hand playerHand = new Hand();
	static Hand dealerHand = new Hand();
	
	public static void main(String[] args) {
		
		deck1.shuffle(); 
		
		// Now print out the new shuffled deck of cards.
		System.out.println("======== Deck of Shuffled Cards ===========");
		for (int i = 0; i<52; i++) {
			deck1.deck[i].print();
		}
		
		// Currently only offer Black Jack (1), partial 5-card draw (2) or quit (0)
		while(true) {
			System.out.println("\nPlayer 1...\nType 1 to play Black Jack? \nType 2 to play Five Card Draw\ntype 3 to play Seven Card Stud\nType 4 to play Texas Hold\'em\nType 0 to quit");
			int gameSelection = scanner.nextInt();
			// 0 quits the program, 1 is black jack, 2 is 5-card poker, 3 is 7-card stud, 4 is Texas Hold'em, etc...
			if(gameSelection == 0) { System.exit(0); }
			else if(gameSelection == 1) {
				middleloop:
				while(true) {
					deck1.shuffle(); 
					BlackJack bj = new BlackJack(); 
					while(true) {
						System.out.println("would you like to play again, go to menu or quit? \nType \"p\" for play again, \"m\" for menu, or \"q\" for quit...");
						String playAgain = scanner.next();
						if(playAgain.equals("quit") || playAgain.equals("q") || playAgain.equals("Q")) { System.exit(0); }
						else if(playAgain.equals("menu") || playAgain.equals("m") || playAgain.equals("M")) { break middleloop; }
						else if(playAgain.equals("play") || playAgain.equals("p") || playAgain.equals("P")) { break; }
						else { System.out.println("That was not a vaild entry"); }
					}
				} 
			}
			else if(gameSelection == 2) { 
				System.out.println("5-card draw isn\'t fully ready yet... here\'s what we got so far..."); 
				deck1.shuffle(); 
				FiveCardDraw fcd = new FiveCardDraw(); 
				break; 
			}
			else if(gameSelection == 3) { System.out.println("7-card stud isn\'t available yet"); }
			else if(gameSelection == 4) { System.out.println("Texas Hold\'em isn\'t available yet"); }
			else { System.out.println("That is not a valid option."); } 
		}

	}

}
