import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class MainClass {
	static Scanner scanner = new Scanner(System.in);
	static Card[] deck = new Card[52];
	
	static Hand player1Hand = new Hand();
	static ArrayList<Card> playerHand = new ArrayList<Card>();
	static ArrayList<Card> dealerHand = new ArrayList<Card>();
	
	public static void cardShuffler() {
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
	}

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
				
				if(hitOrStay.contentEquals("h") || hitOrStay.contentEquals("hit") || hitOrStay.contentEquals("H")) {
					playerHand.add(deck[deckPosition]);
					playerHand.get(deckPosition).print();
					
					if(playerHand.get(deckPosition).cardNum>=10) { playerHandSum += 10; } 
					else if(playerHand.get(deckPosition).cardNum>=2) { playerHandSum += playerHand.get(deckPosition).cardNum; } 
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
		for(int i=0; i<2; i++) {
			playerHand.remove(i);
			dealerHand.remove(i);
		}
	}
	
	public static void dealFiveCardFirst() {
		System.out.println("Dealing cards...");
		for(int i=0; i<10; i++) {
			deck[i].print();
		}
	}
	
	public static void sortFiveCards() {
		Card tempCard = new Card(1,1);
		for(int j=0; j<5; j++) {
			for(int i=0; i<4; i++) {
				if(playerHand.get(i).cardNum > playerHand.get(i+1).cardNum) {
					tempCard = playerHand.get(i);
					playerHand.set(i,playerHand.get(i+1));
					playerHand.set(i+1, tempCard);
				}
			} 
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
				System.out.println(deckPosition); // testing position and debugging
			}
		}
		deckPosition++;
		
		sortFiveCards();
		
		System.out.println("\n\nPlayer Hand: ");
		for(int i=0; i<5; i++) { playerHand.get(i).print(); }
		System.out.println("\nDealer Hand: ");
		for(int i=0; i<5; i++) { dealerHand.get(i).print(); }
		

		
		boolean playersTurn = true;
		boolean wrongInput;
		do {
			System.out.println("\nPlayer 1... \nHow many cards would you like to discard and draw? (Type a number from 0 to 5)");
			int selectionSize = scanner.nextInt();
			int[] selection = new int[selectionSize];
			if((selectionSize>0)&&(selectionSize<5)) {
				System.out.println("Please type the cards you want replaced by position in your hand, one at a time, pressing enter in between each...\ni.e. 1 [enter] 2 [enter] 4 [enter] etc.");
				for(int i=0; i<selectionSize; i++) { 
					do {
						selection[i] = scanner.nextInt()-1; 
						if((selection[i]>4)||(selection[i]<0)) { 
							wrongInput = true;
							System.out.println("you must select a number in the range of 1 to 5! Try again...");
						} else { wrongInput = false; }
					} while (wrongInput == true);
				}
				for(int i=0; i<selectionSize; i++) { playerHand.set(selection[i], deck[deckPosition]); deckPosition++; }
				System.out.println("Your hand is now: ");
				wrongInput = false;
			} else if(selectionSize==0) { 
				System.out.println("You have chosen to stand pat (to keep all your cards)...\nYour cards remain:"); 
				wrongInput = false;
			} else if(selectionSize==5) { 
				System.out.println("You have chosen to replace all your cards..."); 
				for(int i=0; i<5; i++) { playerHand.set(i, deck[deckPosition]); deckPosition++; }
				System.out.println("Your hand is now: "); 
				wrongInput = false;
			} else { 
				System.out.println("That was not a valid entry..."); 
				wrongInput = true;
			} 
		} while(wrongInput == true);
		
		sortFiveCards();
		
		for(int i=0; i<5; i++) { playerHand.get(i).print(); }
		// NOW WE NEED TO ADD IN RULES TO CALCULATE THE USER'S SCORE BASED OFF OF THE CARDS HE/SHE HOLDS

		// if royal flush all the points... etc.
		// Let's create a rule for flushes that checks if all suits in a hand are equal
		boolean isFlush = false;
		if((playerHand.get(0).suit==playerHand.get(1).suit) && (playerHand.get(1).suit==playerHand.get(2).suit) && (playerHand.get(2).suit==playerHand.get(3).suit) && (playerHand.get(3).suit==playerHand.get(4).suit)) 
		{ isFlush = true; }
		// Let's create a rule for royals that checks if a hand is royal (ten, jack, queen, king, ace)
		boolean hasTen = false;
		if((playerHand.get(0).cardNum == 10 || playerHand.get(1).cardNum == 10 || playerHand.get(2).cardNum == 10 || playerHand.get(3).cardNum == 10 || playerHand.get(4).cardNum == 10)) { hasTen = true; }
		boolean hasJack = false;
		if((playerHand.get(0).cardNum == 11 || playerHand.get(1).cardNum == 11 || playerHand.get(2).cardNum == 11 || playerHand.get(3).cardNum == 11 || playerHand.get(4).cardNum == 11)) { hasJack = true; }
		boolean hasQueen = false;
		if((playerHand.get(0).cardNum == 12 || playerHand.get(1).cardNum == 12 || playerHand.get(2).cardNum == 12 || playerHand.get(3).cardNum == 12 || playerHand.get(4).cardNum == 12)) { hasQueen = true; }
		boolean hasKing = false;
		if((playerHand.get(0).cardNum == 13 || playerHand.get(1).cardNum == 13 || playerHand.get(2).cardNum == 13 || playerHand.get(3).cardNum == 13 || playerHand.get(4).cardNum == 13)) { hasKing = true; }
		boolean hasAce = false;
		if((playerHand.get(0).cardNum == 1 || playerHand.get(1).cardNum == 1 || playerHand.get(2).cardNum == 1 || playerHand.get(3).cardNum == 1 || playerHand.get(4).cardNum == 1)) { hasAce = true; }
		
		boolean isRoyal = false;
		if((hasTen == true) && (hasJack == true) && (hasQueen == true) && (hasKing == true) && (hasAce == true)) { isRoyal = true; }
		
		boolean hasRoyalFlush = false;
		if((isRoyal == true) && (isFlush == true)) { hasRoyalFlush = true; }
		
		// To determine if we have a straight flush or even just a straight, we need some logic to determine if cards are sequential
		boolean isStraight = false;
		if((playerHand.get(0).cardNum+1==playerHand.get(1).cardNum) && (playerHand.get(1).cardNum+1==playerHand.get(2).cardNum) && (playerHand.get(2).cardNum+1==playerHand.get(3).cardNum) && (playerHand.get(3).cardNum+1==playerHand.get(4).cardNum)) 
		{ isStraight = true; }
		
		boolean hasStraightFlush = false;
		if((isStraight == true) && (isFlush == true)) { hasStraightFlush = true; }
		
		// This will identify any pairs (as cards are sorted)
		boolean match01 = false; if(playerHand.get(0).cardNum == playerHand.get(1).cardNum) { match01 = true; }
		boolean match12 = false; if(playerHand.get(1).cardNum == playerHand.get(2).cardNum) { match12 = true; }
		boolean match23 = false; if(playerHand.get(2).cardNum == playerHand.get(3).cardNum) { match23 = true; }
		boolean match34 = false; if(playerHand.get(3).cardNum == playerHand.get(4).cardNum) { match34 = true; }
		// This will identify any three of a kinds (as cards are sorted)
		boolean match02 = false; if(playerHand.get(0).cardNum == playerHand.get(2).cardNum) { match02 = true; }
		boolean match13 = false; if(playerHand.get(1).cardNum == playerHand.get(3).cardNum) { match13 = true; }
		boolean match24 = false; if(playerHand.get(2).cardNum == playerHand.get(4).cardNum) { match13 = true; }
		// This will identify any four of a kinds (as cards are sorted)
		boolean match03 = false; if(playerHand.get(0).cardNum == playerHand.get(3).cardNum) { match03 = true; }
		boolean match14 = false; if(playerHand.get(1).cardNum == playerHand.get(4).cardNum) { match14 = true; }
		// This will identify any five of a kinds (as cards are sorted, but impossible without a joker/wild card or multiple decks)
		boolean match05 = false; if(playerHand.get(0).cardNum == playerHand.get(5).cardNum) { match05 = true; }
		
		// This is IMPOSSIBLE without a joker/wild card or multiple decks... 5 of a kind
		boolean hasFiveKind = false;
		if(match05 == true) { hasFiveKind = true; }
		
		// Now to get our 4 of a kind
		boolean hasFourKind = false;
		if((match03 == true) || (match14 == true)) { hasFourKind = true; }
		
		// Now to get a regular straight
		boolean hasStraight = false;
		if(isStraight == true) { hasStraight = true; }
		
		// Now to get a full house (a three of a kind and a separate pair in one hand)
		boolean hasFullHouse = false;
		if(((match02 == true) && (match34 == true)) || ((match01 == true) && (match24 == true))) { hasFullHouse = true; }
		
		// Now to get a flush
		boolean hasFlush = false;
		if(isFlush == true) { hasFlush = true; }
		
		// now to get two pair
		boolean hasTwoPair = false;
		if (((match01 == true) && (match23 == true)) || ((match01 == true) && (match34 == true)) || ((match12 == true) && (match34 == true))) { hasTwoPair = true; }
		
		// now to get three of a kind
		boolean hasThreeKind = false;
		if((match02 == true) || (match13 == true) || (match24 == true)) { hasThreeKind = true; }
		
		// now to get a pair
		boolean hasPair = false;
		if((match01 == true) || (match12 == true) || (match23 == true) || (match34 == true)) { hasPair = true; }
		
		// Let's give the player 0 points to start and add points based on their hand
		int handPoints = 0;
		
		// A sum of card values can come in handy for tie breakers
		int handSum = 0; 
		for(int i=0; i<5; i++) { handSum += playerHand.get(i).cardNum; }
		
		// Now we order the different possible winning hands in if/else if/else statements and add points to the hands score
		if(hasRoyalFlush == true) { 
			handPoints += 1000000; // Highest hand gets 1 million points!
		}
		else if(hasStraightFlush == true) { 
			handPoints += 900000;
			handPoints += handSum; // This will cause the highest straight flush to win if more than one has a straight flush
		}
		else if(hasFourKind == true) { 
			handPoints += 800000; 
			// by getting the middle card we can be sure that whether the first 4 or last 4 cards are the 4 of a kind we grab the value for all in the 4 of a kind
			// value of 1 is ace, which is high so we give it a value of 15 and times by 4 it has a value of 60
			if(playerHand.get(2).cardNum == 1) { handPoints += 60000; } 
			else { handPoints += playerHand.get(2).cardNum * 4000; }
		}
		else if(hasFullHouse == true) { 
			handPoints += 700000; 
			if(match02 == true) { // if the three of a kind is the first three cards then use the 1st card to represent the three of a kind values
				if(playerHand.get(1).cardNum == 1) { handPoints += 45000; }
				else { handPoints += playerHand.get(1).cardNum * 3000; }
			} else { // otherwise use the 3rd card (we are counting from zero, so really its the 4th card)
				if(playerHand.get(3).cardNum == 1) { handPoints += 45000; }
				else { handPoints += playerHand.get(3).cardNum * 3000; }
			}
		}
		else if(hasFlush == true) { 
			handPoints += 600000; 
			if(playerHand.get(0).cardNum == 1) {
				handPoints += 45000;
				handPoints += playerHand.get(4).cardNum * 3000;
				handPoints += playerHand.get(3).cardNum * 200;
				handPoints += playerHand.get(2).cardNum * 100;
				handPoints += playerHand.get(1).cardNum * 10;
			} else {
				handPoints += playerHand.get(4).cardNum * 3000;
				handPoints += playerHand.get(3).cardNum * 300;
				handPoints += playerHand.get(2).cardNum * 100;
				handPoints += playerHand.get(1).cardNum * 10;
				handPoints += playerHand.get(0).cardNum * 1;
			}
			
		}
		else if(hasStraight == true) { 
			handPoints += 500000; 
			handPoints += handSum; // This will cause the highest straight to win if more than one has a straight
		}
		else if(hasThreeKind == true) { 
			handPoints += 400000; 
			if(match02 == true) { // if the three of a kind is the first three cards then use the 1st card to represent the three of a kind values
				if(playerHand.get(1).cardNum == 1) { handPoints += 45000; }
				else { handPoints += playerHand.get(1).cardNum * 3000; }
			} else { // otherwise use the 3rd card (we are counting from zero, so really its the 4th card)
				if(playerHand.get(3).cardNum == 1) { handPoints += 45000; }
				else { handPoints += playerHand.get(3).cardNum * 3000; }
			}
		}
		else if(hasTwoPair == true) { 
			handPoints += 300000; 
			if(match01 == true) {
				if(match23 == true) {
					if(playerHand.get(0).cardNum == 1) { 
						handPoints += 45000; 
						handPoints += playerHand.get(2).cardNum * 200;
						handPoints += playerHand.get(4).cardNum * 10;
					} else {
						handPoints += playerHand.get(2).cardNum * 3000;
						handPoints += playerHand.get(0).cardNum * 200;
						handPoints += playerHand.get(4).cardNum * 10;
					}
				} else if(match34 == true) {
					if(playerHand.get(0).cardNum == 1) {
						handPoints += 45000;
						handPoints += playerHand.get(4).cardNum * 200;
						handPoints += playerHand.get(2).cardNum * 10;
					} else {
						handPoints += playerHand.get(4).cardNum * 3000;
						handPoints += playerHand.get(0).cardNum * 200;
						handPoints += playerHand.get(2).cardNum * 10;
					}
				}
			} else if(match12 == true) {
				if(match34 == true) {
					handPoints += playerHand.get(4).cardNum * 3000;
					handPoints += playerHand.get(2).cardNum * 200;
					// Ace needs to be worth 15 otherwise it would be treated as low card
					if(playerHand.get(0).cardNum == 1) { handPoints += 150; } 
					else { handPoints += playerHand.get(0).cardNum * 10; }
				}
			}
		}
		
		else if(hasPair == true) { 
			handPoints += 200000;
			if(match01 == true) {
				if(playerHand.get(0).cardNum == 1) {
					handPoints += 45000;
					handPoints += playerHand.get(4).cardNum * 3000;
					handPoints += playerHand.get(3).cardNum * 200;
					handPoints += playerHand.get(2).cardNum * 10;
				} else {
					handPoints += playerHand.get(0).cardNum * 3000;
					handPoints += playerHand.get(4).cardNum * 200;
					handPoints += playerHand.get(3).cardNum * 100;
					handPoints += playerHand.get(2).cardNum * 10;
				}
			} else if(match12 == true) {
				handPoints += playerHand.get(1).cardNum * 3000;
				handPoints += playerHand.get(4).cardNum * 200;
				handPoints += playerHand.get(3).cardNum * 100;
				// Things get complicated when an Ace is the high card so we need to give it higher points than any other combination of high cards.
				if(playerHand.get(0).cardNum == 1) { handPoints += 4500; }
				else { handPoints += playerHand.get(0).cardNum * 10; }
				
			} else if(match23 == true) {
				handPoints += playerHand.get(2).cardNum * 3000;
				handPoints += playerHand.get(4).cardNum * 200;
				handPoints += playerHand.get(1).cardNum * 100;
				if(playerHand.get(0).cardNum == 1) { handPoints += 4500; }
				else { handPoints += playerHand.get(0).cardNum * 10; }
				
			} else if(match34 == true) {
				handPoints += playerHand.get(4).cardNum * 3000;
				handPoints += playerHand.get(2).cardNum * 200;
				handPoints += playerHand.get(1).cardNum * 100;
				if(playerHand.get(0).cardNum == 1) { handPoints += 4500; }
				else { handPoints += playerHand.get(0).cardNum * 10; }
				
			}
		}
		
		else {  // Only has a high card, no pair or better hand
			handPoints += 100000; 
			if(playerHand.get(0).cardNum == 1) {
				handPoints += 45000;
				handPoints += playerHand.get(4).cardNum * 3000;
				handPoints += playerHand.get(3).cardNum * 200;
				handPoints += playerHand.get(2).cardNum * 100;
				handPoints += playerHand.get(1).cardNum * 10;
			} else {
				handPoints += playerHand.get(4).cardNum * 3000;
				handPoints += playerHand.get(3).cardNum * 300;
				handPoints += playerHand.get(2).cardNum * 100;
				handPoints += playerHand.get(1).cardNum * 10;
				handPoints += playerHand.get(0).cardNum * 1;
			}
		} // a generous 100,000 points for having a high card (basically points for participating)
		
		System.out.println(handPoints);
		
		// Now we must handle the dealer's cards and make it smart
		/* Some suggested rules are:
		 * 1. First check if the computer player already has a hand of one pair or better. If so discard all other cards.
		 * 2. If the next card evaluates to "high Card", determine if the user has 4 cards of the same suit.  If so discard the card of the different suit.
		 * 3. Next determine if the user has 4 cards in sequence. If so, discard the card that is out of sequence.
		 * 4. Next if the user has an Ace, discard the other four cards.
		 * 5. Otherwise keep the two highest cards and discard the other 3.
		 * 
		 * */
		
	}

	void fiveCardRules() {
		boolean hasRoyalFlush; // 100 points
		boolean hasStraightFlush; // 90 points
		boolean hasFourKind; // 80 points
		boolean hasStraight; // 70 points
		boolean hasFullHouse; // 60 points
		boolean hasFlush; // 50 points
		boolean hasTwoPair; // 40 points
		boolean hasThreeKind; // 30 points
		boolean hasPair; // 20 points
	}
	
	
	

	
	public static void main(String[] args) {
		
		cardShuffler();
		
		// Now print out the new shuffled deck of cards.
		System.out.println("======== Deck of Shuffled Cards ===========");
		for (int i = 0; i<52; i++) {
			deck[i].print();
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
					cardShuffler();
					BlackJack(); 
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
				cardShuffler(); 
				fiveCardDraw(); 
				break; 
			}
			else if(gameSelection == 3) { System.out.println("7-card stud isn\'t available yet"); }
			else if(gameSelection == 4) { System.out.println("Texas Hold\'em isn\'t available yet"); }
			else { System.out.println("That is not a valid option."); } 
		}

	}

}
