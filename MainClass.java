import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class MainClass {
	static Scanner scanner = new Scanner(System.in);
	static Card[] deck = new Card[52];
	
	static Hand player1Hand = new Hand();
	static ArrayList<Card> playerHand = new ArrayList<Card>();
	static ArrayList<Card> dealerHand = new ArrayList<Card>();
	
	public static void dealBlackJackFirst() {
		System.out.println("Dealing cards...");
		for(int i=0;i<4;i++) {
			deck[i].print();
		}
		/*
		System.out.println("Player has: ");
		deck[0].print(); deck[2].print();
		System.out.println("Dealer has: ");
		deck[1].print(); deck[3].print();
		*/
	}
	public static void BlackJack() {
		System.out.println("Let\'s play some Black Jack!"); 
		// The game of black jack
		
		System.out.println("");
		dealBlackJackFirst();
		int j = 0;
		int deckPosition = 0;
		for(int i=deckPosition; i<deckPosition+4; i++) {
			if(i%2==0) {
				playerHand.add(deck[i]);
				//System.out.println("Player Hand: " + playerHand.get(j).cardNum);
			} else {
				dealerHand.add(deck[i]);
				//System.out.println("Dealer Hand: " + dealerHand.get(j).cardNum);
				j++;
				deckPosition++;
				//System.out.println(deckPosition); // testing position and debugging
			}
		}

		System.out.println("\n\nPlayer Hand: ");
		int playerHandSum = 0;
		for(int i=0; i<2; i++) {
			playerHand.get(i).print();
			if(playerHand.get(i).cardNum>=10) { playerHandSum += 10; } 
			else if(playerHand.get(i).cardNum>=2) { playerHandSum += playerHand.get(i).cardNum; } 
			else { if(playerHandSum <= 10) { playerHandSum += 11; } else { playerHandSum += 1; } }
		}
		System.out.println("Total is " + playerHandSum + "\n");
		
		System.out.println("Dealer Hand: ");
		int dealerHandSum = 0;
		for(int i=0; i<2; i++) {
			dealerHand.get(i).print();
			if(dealerHand.get(i).cardNum>=10) { dealerHandSum += 10; } 
			else if(dealerHand.get(i).cardNum>=2) { dealerHandSum += dealerHand.get(i).cardNum; } 
			else { if(dealerHandSum <= 10) { dealerHandSum += 11; } else { dealerHandSum +=1; } }
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
				
				if(hitOrStay.contentEquals("h")) {
					playerHand.add(deck[deckPosition]);
					playerHand.get(deckPosition).print();
					
					if(playerHand.get(deckPosition).cardNum>=10) { playerHandSum += 10; } 
					else if(playerHand.get(deckPosition).cardNum>=2) { playerHandSum += playerHand.get(deckPosition).cardNum; } 
					else { if(playerHandSum <= 10) { playerHandSum += 11; } else { playerHandSum += 1; } }
					System.out.println("Total is: " + playerHandSum);
					
					deckPosition++;
					//System.out.println(deckPosition); // debugging position
					
					if(playerHandSum>21) { System.out.println("\nBUST!\nDealer Wins!"); playersTurn = false; }
					
				} else if(hitOrStay.contentEquals("s")) {
					System.out.println("Dealers turn");
					playersTurn = false;
					if(dealerHandSum>=17) {
						//System.out.println("Total is: " + dealerHandSum);
						//if(dealerHandSum>21) { System.out.println("BUST!\nPlayer Wins!"); }
						//if(dealerHandSum>=playerHandSum) { System.out.println("Dealer Wins!"); } 
						//else { System.out.println("Player Wins!"); }
					}
					
					while(dealerHandSum<17) { //System.out.println("not yet added" + deckPosition);
						dealerHand.add(deck[deckPosition]); //System.out.println("added" + deckPosition);
						dealerHand.get(dealerHand.size()-1).print(); //System.out.println("printed" + deckPosition);
						if(dealerHand.get(dealerHand.size()-1).cardNum>=10) { dealerHandSum += 10; } 
						else if(dealerHand.get(dealerHand.size()-1).cardNum>=2){ dealerHandSum += dealerHand.get(dealerHand.size()-1).cardNum; } 
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
	}
	
	public static void dealFiveCardFirst() {
		System.out.println("Dealing cards...");
		for(int i=0; i<10; i++) {
			deck[i].print();
		}
	}
	
	public static void fiveCardDraw() {
		// The game of 5 Card Draw Poker
		dealFiveCardFirst();
		int j = 0;
		int deckPosition = 0;
		for(int i=deckPosition; i<deckPosition+10; i++) {
			if(i%2==0) {
				playerHand.add(deck[i]);
				//System.out.println("Player Hand: " + playerHand.get(j).cardNum);
			} else {
				dealerHand.add(deck[i]);
				//System.out.println("Dealer Hand: " + dealerHand.get(j).cardNum);
				j++;
				deckPosition++;
				//System.out.println(deckPosition); // testing position and debugging
			}
		}

		System.out.println("\n\nPlayer Hand: ");
		int playerHandSum = 0;
		for(int i=0; i<2; i++) {
			playerHand.get(i).print();
			if(playerHand.get(i).cardNum>=10) { playerHandSum += 10; } 
			else if(playerHand.get(i).cardNum>=2) { playerHandSum += playerHand.get(i).cardNum; } 
			else { if(playerHandSum <= 10) { playerHandSum += 11; } else { playerHandSum += 1; } }
		}
		System.out.println("Total is " + playerHandSum + "\n");
		
		
	}

	public static void dealersHand() {
		
	}
	
	public static void main(String[] args) {
		
		// An array of 52 references to cards
		
		int counter = 0;

		/*
		1a Make 13 cards for Clubs
		1b Make 13 cards for Hearts
		1c Make 13 cards for Diamonds
		1d Make 13 cards for Spades
		 */
		/* Cycle through each suit and within each suit each card
		 * and make a new card with that suit and card.
		 * Number the cards 1-13 for Ace, 2, 3, .... Jack, Queen, King=13
		 * Suits are numbered as follows: 1=Clubs, 2=Hearts, 3=Diamonds, 4=Spades.
		 * Print the card created as its being created.
		 */
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
		
		// Our random number generator
		Random randomGenerator = new Random();
		
		for (int shuffle = 0; shuffle < 300000; shuffle++) {
		// Iterate through each card in the deck
			for (int i = 0; i<52; i++){
			
				// Use the random number generator to choose a card to swap with.
				int j = randomGenerator.nextInt(52);
			
				// Swap card i with card j in the deck
				Card tempCard = deck[j];
				deck[j]=deck[i];
				deck[i]=tempCard;
			}
		}
		
		// Now print out the new shuffled deck of cards.
		System.out.println("======== Deck of Shuffled Cards ===========");
		for (int i = 0; i<52; i++) {
			deck[i].print();
		}
		
		// Currently only offer Black Jack (1) or quit (0)
		System.out.println("\nPlayer 1, would you like to play Black Jack? \nOther games will be available in the future... \nPlease enter \"1\" for Yes or \"0\" for no: ");
		int gameSelection = scanner.nextInt();
		// 0 quits the program, 1 is black jack, 2 is 5-card poker, 3 is 7-card stud, 4 is Texas Hold'em, etc...
		if(gameSelection == 0) { System.exit(0); }
		else if(gameSelection == 1) { BlackJack(); } 
		else if(gameSelection == 2) { System.out.println("5-card poker isn\'t available yet"); }
		else if(gameSelection == 3) { System.out.println("7-card stud isn\'t available yet"); }
		else if(gameSelection == 4) { System.out.println("Texas Hold\'em isn\'t available yet"); }
		else { System.out.println("That is not a valid option."); } // Later put this in a loop to validate and offer a retry
		

	}

}
