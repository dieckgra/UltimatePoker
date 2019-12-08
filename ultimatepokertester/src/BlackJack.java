import java.util.*;

public class BlackJack extends Game {
	
	static Scanner scanner = new Scanner(System.in);
	
	BlackJack() {
		super();
		//blackJack();
	}
	
	public static void dealBlackJackFirst() {
		System.out.println("Dealing cards...");
		deck1.shuffle();
		deck1.print();
	}
	
	public static void blackJack() {
		System.out.println("Let\'s play some Black Jack!"); 
		// The game of black jack
		
		System.out.println("");
		dealBlackJackFirst();
		int deckPosition = 0;
		for(int i=0; i<4; i++) {
			if(i%2==0) {
				playerHand.add(deck1.get(i));
				//deckPosition++;
				//System.out.println(deckPosition); // testing position and debugging
			} else {
				dealerHand.add(deck1.get(i));
				//deckPosition++;
				//System.out.println(deckPosition); // testing position and debugging
			}
			deckPosition++;
			System.out.println(deckPosition); // testing position and debugging
		}

		System.out.println("\n\nPlayer Hand: ");
		int playerHandSum = 0;
		int playerAces = 0;
		playerHand.print();
		for(int i=0; i<2; i++) {
			if(playerHand.getCardNum(i)>=10) { playerHandSum += 10; } 
			else if(playerHand.getCardNum(i)>=2) { playerHandSum += playerHand.getCardNum(i); } 
			else { // must be an ace...
				playerAces++;
				if(playerHandSum <= 10) { playerHandSum += 11; } else { playerHandSum += 1; } 
				}
		}
		System.out.println("Total is " + playerHandSum + "\n");
		
		System.out.println("Dealer Hand: ");
		int dealerHandSum = 0;

		int[] cardSum = new int[2];
		for(int i=0; i<2; i++) {
			if(dealerHand.getCardNum(i)>=10) { cardSum[i] = 10; dealerHandSum += cardSum[i]; } 
			else if(dealerHand.getCardNum(i)>=2) { cardSum[i] = dealerHand.getCardNum(i); dealerHandSum += cardSum[i]; } 
			else { if(dealerHandSum <= 10) { cardSum[i] = 11; dealerHandSum += cardSum[i]; } else { cardSum[i] = 1; dealerHandSum += cardSum[i]; } }
		}
		dealerHand.get(0).print();
		if(dealerHandSum==21) { // Display the second card instead of hiding it if has "Black Jack!"
			dealerHand.get(1).print(); 
			System.out.println("Total is: " + dealerHandSum + "\n");
		} else { // The second card... We will add its value to the dealer hand sum, but subtract it from what we print...
			System.out.println("[[[FACE DOWN]]]"); 
			System.out.println("Total is at least " + (dealerHandSum-cardSum[1]) + "\n");
		}
		
		
		boolean playersTurn = true;
		if(dealerHandSum==21) { System.out.println("Black Jack!\nDealer Wins!"); playersTurn = false; } 
		else if(playerHandSum==21) { System.out.println("Black Jack!\nPlayer Wins!"); playersTurn = false; } 
		else { // else, play on...
			String hitOrStay;
			while(playersTurn == true) {//loops until stay
				System.out.println("\nPlayer, Do you want to hit or stay?\nType \"h\" for hit or type \"s\" for stay.");
				hitOrStay = scanner.next();
				
				if(hitOrStay.contentEquals("h") || hitOrStay.contentEquals("hit") || hitOrStay.contentEquals("H")) {
					//System.out.println("The deck position variable is: " + deckPosition + ", but the array is length: " + playerHand.size());
					playerHand.add(deck1.get(deckPosition));
					System.out.println("The player drew: ");
					playerHand.print(playerHand.size()-1);
					System.out.println("\nThe player\'s hand is now: ");
					playerHand.print();
					
					sort(playerHand);
					playerHandSum = score(playerHand);
					System.out.println("Total is: " + playerHandSum);
					
					deckPosition++;
					//System.out.println(deckPosition); // debugging position
					
					if(playerHandSum>21) { System.out.println("\nBUST!\nDealer Wins!"); playersTurn = false; }
					
				} else if(hitOrStay.contentEquals("s") || hitOrStay.contentEquals("stay") || hitOrStay.contentEquals("S")) { //kinda sim frame 
					System.out.println("Dealer\'s turn!\n\nDealer\'s hand: ");
					dealerHand.print();
					playersTurn = false;
					if(dealerHandSum>=17) {
						System.out.println("The dealer stays...");
					}
					
					while(dealerHandSum<17) { 
						System.out.println("\nDealer hits and gets: ");
						dealerHand.add(deck1.get(deckPosition)); 
						dealerHand.print(dealerHand.size()-1); 
						System.out.println("\nThe dealer\'s hand is now: ");
						dealerHand.print();
						
						sort(dealerHand);
						dealerHandSum = score(dealerHand);

						deckPosition++;
					}
					System.out.println("Total is: " + dealerHandSum);
					if(dealerHandSum>21) { System.out.println("\nBUST!\nPlayer Wins!"); break; }
					if(dealerHandSum>=playerHandSum) { System.out.println("\nDealer Wins!"); break; } 
					else { System.out.println("\nPlayer Wins!"); break; }
					
				} else { System.out.println("That was not a valid entry... Try again."); }
			}
		}
		playerHand.clear();
		dealerHand.clear();
	}

	public static void sort(Hand hand) {
		Card tempCard = new Card(1,1);
		for(int j=0; j<hand.size(); j++) {
			for(int i=0; i<hand.size()-1; i++) {
				if(hand.getCardNum(i) > hand.getCardNum(i+1)) {
					tempCard = hand.get(i);
					hand.set(i,hand.get(i+1));
					hand.set(i+1, tempCard);
				}
			} 
		}
	}

	public static int score(Hand hand) {
		int handScore = 0;
		for(int i=(hand.size()-1); i>=0; i--) {
			if(hand.get(i).cardNum >= 10) { 
				handScore += 10; 
				}
			else if(hand.get(i).cardNum >= 2) { 
				handScore += hand.get(i).cardNum; 
				}
			else { // Must be an ace...
				if(handScore > 10) { handScore += 1; } 
				else { handScore += 11; } 
			}
		}
		return handScore;
	}
}
