import java.util.*;

public class BlackJack extends Game {
	
	static Scanner scanner = new Scanner(System.in);
	
	BlackJack() {
		super();
		blackJack();
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
				System.out.println(deckPosition); // testing position and debugging
			} else {
				dealerHand.add(deck1.get(i));
				deckPosition++;
				System.out.println(deckPosition); // testing position and debugging
			}
		}

		System.out.println("\n\nPlayer Hand: ");
		int playerHandSum = 0;
		playerHand.print();
		for(int i=0; i<2; i++) {
			if(playerHand.getCardNum(i)>=10) { playerHandSum += 10; } 
			else if(playerHand.getCardNum(i)>=2) { playerHandSum += playerHand.getCardNum(i); } 
			else { if(playerHandSum <= 10) { playerHandSum += 11; } else { playerHandSum += 1; } }
		}
		System.out.println("Total is " + playerHandSum + "\n");
		
		System.out.println("Dealer Hand: ");
		int dealerHandSum = 0;
		dealerHand.print();
		for(int i=0; i<2; i++) {
			if(dealerHand.getCardNum(i)>=10) { dealerHandSum += 10; } 
			else if(dealerHand.getCardNum(i)>=2) { dealerHandSum += dealerHand.getCardNum(i); } 
			else { if(dealerHandSum <= 10) { dealerHandSum += 11; } else { dealerHandSum += 1; } }
		}
		System.out.println("Total is " + dealerHandSum + "\n");
		
		boolean playersTurn = true;
		if(dealerHandSum==21) { System.out.println("Black Jack!\nDealer Wins!"); playersTurn = false; } 
		else if(playerHandSum==21) { System.out.println("Black Jack!\nPlayer Wins!"); playersTurn = false; } 
		else { // else, play on...
			String hitOrStay;
			while(playersTurn == true) {
				System.out.println("\nPlayer, Do you want to hit or stay?\nType \"h\" for hit or type \"s\" for stay.");
				hitOrStay = scanner.next();
				
				if(hitOrStay.contentEquals("h") || hitOrStay.contentEquals("hit") || hitOrStay.contentEquals("H")) {
					System.out.println("The deck position variable is: " + deckPosition + ", but the array is length: " + playerHand.size());
					playerHand.add(deck1.get(deckPosition));
					playerHand.print(deckPosition);
					
					if(playerHand.getCardNum(deckPosition)>=10) { playerHandSum += 10; } 
					else if(playerHand.getCardNum(deckPosition)>=2) { playerHandSum += playerHand.getCardNum(deckPosition); } 
					else { if(playerHandSum <= 10) { playerHandSum += 11; } else { playerHandSum += 1; } }
					System.out.println("Total is: " + playerHandSum);
					
					deckPosition++;
					//System.out.println(deckPosition); // debugging position
					
					if(playerHandSum>21) { System.out.println("\nBUST!\nDealer Wins!"); playersTurn = false; }
					
				} else if(hitOrStay.contentEquals("s") || hitOrStay.contentEquals("stay") || hitOrStay.contentEquals("S")) {
					System.out.println("Dealers turn");
					playersTurn = false;
					if(dealerHandSum>=17) {
						//System.out.println("Total is: " + dealerHandSum);
						//if(dealerHandSum>21) { System.out.println("BUST!\nPlayer Wins!"); }
						//if(dealerHandSum>=playerHandSum) { System.out.println("Dealer Wins!"); } 
						//else { System.out.println("Player Wins!"); }
					}
					
					while(dealerHandSum<17) { //System.out.println("not yet added" + deckPosition);
						dealerHand.add(deck1.deck[deckPosition]); //System.out.println("added" + deckPosition);
						dealerHand.print(dealerHand.size()-1); //System.out.println("printed" + deckPosition);
						if(dealerHand.getCardNum(dealerHand.size()-1)>=10) { dealerHandSum += 10; } 
						else if(dealerHand.getCardNum(dealerHand.size()-1)>=2){ dealerHandSum += dealerHand.getCardNum(dealerHand.size()-1); } 
						else { if(dealerHandSum <= 10) { dealerHandSum += 11; } else { dealerHandSum += 1; } }
						
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
	
}
