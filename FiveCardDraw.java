import java.util.Scanner;

public class FiveCardDraw extends Game {
	
	static Scanner scanner = new Scanner(System.in);
	static int deckPosition;
	static boolean hasRoyalFlush,hasStraightFlush,hasFiveKind,hasFourKind,hasStraight,hasFullHouse,hasFlush,hasTwoPair,hasThreeKind,hasPair;
	static boolean match01,match12,match23,match34,match02,match13,match24,match03,match14,match05;
	static int handPoints, dealerPoints, playerPoints;
	
	
	
	FiveCardDraw() {
		super();
		fiveCardDraw();
	}
	
	
	
	public static void dealFiveCardFirst() {
		System.out.println("Dealing cards...");
		deck1.shuffle();
		deck1.print();
		//for(int i=0; i<10; i++) {
		//	deck1.deck[i].print();
		//}
	}
	
	public static void sortFiveCards(Hand hand) {
		Card tempCard = new Card(1,1);
		for(int j=0; j<5; j++) {
			for(int i=0; i<4; i++) {
				if(hand.getCardNum(i) > hand.getCardNum(i+1)) {
					tempCard = hand.get(i);
					hand.set(i,hand.get(i+1));
					hand.set(i+1, tempCard);
				}
			} 
		}
	}
	
	public static void rules(Hand hand) { // Determines the kind of hand that is held

		// Let's create a rule for flushes that checks if all suits in a hand are equal
		boolean isFlush = false;
		if((hand.get(0).suit==hand.get(1).suit) && (hand.get(1).suit==hand.get(2).suit) && (hand.get(2).suit==hand.get(3).suit) && (hand.get(3).suit==hand.get(4).suit)) 
		{ isFlush = true; }
		// Let's create a rule for royals that checks if a hand is royal (ten, jack, queen, king, ace)
		boolean hasTen = false;
		if((hand.get(0).cardNum == 10 || hand.get(1).cardNum == 10 || hand.get(2).cardNum == 10 || hand.get(3).cardNum == 10 || hand.get(4).cardNum == 10)) { hasTen = true; }
		boolean hasJack = false;
		if((hand.get(0).cardNum == 11 || hand.get(1).cardNum == 11 || hand.get(2).cardNum == 11 || hand.get(3).cardNum == 11 || hand.get(4).cardNum == 11)) { hasJack = true; }
		boolean hasQueen = false;
		if((hand.get(0).cardNum == 12 || hand.get(1).cardNum == 12 || hand.get(2).cardNum == 12 || hand.get(3).cardNum == 12 || hand.get(4).cardNum == 12)) { hasQueen = true; }
		boolean hasKing = false;
		if((hand.get(0).cardNum == 13 || hand.get(1).cardNum == 13 || hand.get(2).cardNum == 13 || hand.get(3).cardNum == 13 || hand.get(4).cardNum == 13)) { hasKing = true; }
		boolean hasAce = false;
		if((hand.get(0).cardNum == 1 || hand.get(1).cardNum == 1 || hand.get(2).cardNum == 1 || hand.get(3).cardNum == 1 || hand.get(4).cardNum == 1)) { hasAce = true; }
		
		boolean isRoyal = false;
		if((hasTen == true) && (hasJack == true) && (hasQueen == true) && (hasKing == true) && (hasAce == true)) { isRoyal = true; }
		
		hasRoyalFlush = false;
		if((isRoyal == true) && (isFlush == true)) { hasRoyalFlush = true; }
		
		// To determine if we have a straight flush or even just a straight, we need some logic to determine if cards are sequential
		boolean isStraight = false;
		if((hand.get(0).cardNum+1==hand.get(1).cardNum) && (hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum) && (hand.get(3).cardNum+1==hand.get(4).cardNum)) 
		{ isStraight = true; }
		if(isRoyal == true) { isStraight = true; }
		
		hasStraightFlush = false;
		if((isStraight == true) && (isFlush == true)) { hasStraightFlush = true; }
		
		//match01,match12,match23,match34,match02,match13,match24,match03,match14,match05
		
		// This will identify any pairs (as cards are sorted)
		match01 = false; if(hand.get(0).cardNum == hand.get(1).cardNum) { match01 = true; }
		match12 = false; if(hand.get(1).cardNum == hand.get(2).cardNum) { match12 = true; }
		match23 = false; if(hand.get(2).cardNum == hand.get(3).cardNum) { match23 = true; }
		match34 = false; if(hand.get(3).cardNum == hand.get(4).cardNum) { match34 = true; }
		// This will identify any three of a kinds (as cards are sorted)
		match02 = false; if(hand.get(0).cardNum == hand.get(2).cardNum) { match02 = true; }
		match13 = false; if(hand.get(1).cardNum == hand.get(3).cardNum) { match13 = true; }
		match24 = false; if(hand.get(2).cardNum == hand.get(4).cardNum) { match13 = true; }
		// This will identify any four of a kinds (as cards are sorted)
		match03 = false; if(hand.get(0).cardNum == hand.get(3).cardNum) { match03 = true; }
		match14 = false; if(hand.get(1).cardNum == hand.get(4).cardNum) { match14 = true; }
		// This will identify any five of a kinds (as cards are sorted, but impossible without a joker/wild card or multiple decks)
		match05 = false; if(hand.get(0).cardNum == hand.get(5).cardNum) { match05 = true; }
		
		// This is IMPOSSIBLE without a joker/wild card or multiple decks... 5 of a kind
		hasFiveKind = false;
		if(match05 == true) { hasFiveKind = true; }
		
		// Now to get our 4 of a kind
		hasFourKind = false;
		if((match03 == true) || (match14 == true)) { hasFourKind = true; }
		
		// Now to get a regular straight
		hasStraight = false;
		if(isStraight == true) { hasStraight = true; }
		
		// Now to get a full house (a three of a kind and a separate pair in one hand)
		hasFullHouse = false;
		if(((match02 == true) && (match34 == true)) || ((match01 == true) && (match24 == true))) { hasFullHouse = true; }
		
		// Now to get a flush
		hasFlush = false;
		if(isFlush == true) { hasFlush = true; }
		
		// now to get two pair
		hasTwoPair = false;
		if (((match01 == true) && (match23 == true)) || ((match01 == true) && (match34 == true)) || ((match12 == true) && (match34 == true))) { hasTwoPair = true; }
		
		// now to get three of a kind
		hasThreeKind = false;
		if((match02 == true) || (match13 == true) || (match24 == true)) { hasThreeKind = true; }
		
		// now to get a pair
		hasPair = false;
		if((match01 == true) || (match12 == true) || (match23 == true) || (match34 == true)) { hasPair = true; }
		

		
		
	}

	public static void DDS(Hand hand) { // DSS stands for Dealer Decision System

		/* Now we must handle the dealer's cards and make it smart
		 * 
		 * Some suggested rules are:
		 * 
		 * 1. First check if the computer player already has a hand of one pair or better. If so discard all other cards.
		 * 2. If the next card evaluates to "high Card", determine if the user has 4 cards of the same suit.  If so discard the card of the different suit.
		 * 3. Next determine if the user has 4 cards in sequence. If so, discard the card that is out of sequence.
		 * 4. Next if the user has an Ace, discard the other four cards.
		 * 5. Otherwise keep the two highest cards and discard the other 3.
		 * 
		 */
		
		// DEALER DECISION SYSTEM
		if(hand == dealerHand) {
			// If the dealer already has a good to great hand
			if(hasRoyalFlush==true || hasStraightFlush==true || hasFlush==true || hasStraight==true || hasFiveKind==true || hasFullHouse==true) {
				// Do not discard any cards.  These hands are all desirable and it would be stupid to give up.
				//hand.set(selection[i], deck1.deck[deckPosition]); deckPosition++;
			}
			else if(hasFourKind == true) {
				// discard the one card that isn't part of the four of a kind if its value is in the lower half of card values
				if((match03 == true) && (hand.get(4).cardNum < 6)) {
					//discard 1 card, discard card#5 (position 4)
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
				else if((match14 == true) && (hand.get(0).cardNum != 1) && (hand.get(0).cardNum < 6)) {
					//discard 1 card, discard card#1 (not an ace) (position 0)
					hand.set(0, deck1.deck[deckPosition]); deckPosition++;
				}
			}
			else if(hasTwoPair == true) {
				// Discard the one card that isn't part of either pair regardless of its value to try for full house
				if((match01==true) && (match23==true)) {
					// Discard 1 card, discard card#5 (position 4)
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
				if((match01==true) && (match34==true)) {
					// Discard 1 card, discard card#3 (position 2)
					hand.set(2, deck1.deck[deckPosition]); deckPosition++;
				}
				if((match12==true) && (match34==true)) {
					// Discard 1 card, discard card#1 (position 0)
					hand.set(0, deck1.deck[deckPosition]); deckPosition++;
				}
			}
			else if(hasThreeKind == true) {
				// Discard two cards if both are lower half, discard one card (the lower value) if at least one is upper half in value
				if(match02==true) {
					// if at least one card is upper half, then discard 1 card, discard card#4
					if(hand.get(4).cardNum>6) {
						hand.set(3, deck1.deck[deckPosition]); deckPosition++;
					}
					// if both cards are lower half then discard 2 cards, discard card#4 and card#5
					else {
						hand.set(3, deck1.deck[deckPosition]); deckPosition++;
						hand.set(4, deck1.deck[deckPosition]); deckPosition++;
					}
				}
				if(match13==true) {
					// if at least one card is upper half, then discard 1 card, discard card#1 if it is not an ace or card#5 if card#1 is ace
					if(hand.get(0).cardNum==1) { // if an ace is the first card, then discard the fifth card
						hand.set(4, deck1.deck[deckPosition]); deckPosition++;
					}
					else if(hand.get(4).cardNum>6) { // if no ace as first card and the fifth card is high, then discard the first card
						hand.set(0, deck1.deck[deckPosition]); deckPosition++;
					}
					// if both cards are lower half then discard 2 cards, discard 2 cards, discard card#1 and card#5 (not an ace)
					else {
						hand.set(0, deck1.deck[deckPosition]); deckPosition++;
						hand.set(4, deck1.deck[deckPosition]); deckPosition++;
					}
				}
				if(match24==true) {
					// if at least one card is upper half, then discard 1 card, discard card#1 if it is not an ace or card#2 if card#1 is ace
					if(hand.get(0).cardNum==1) { // if an ace is the first card, then discard the second card
						hand.set(1, deck1.deck[deckPosition]); deckPosition++;
					}
					else if(hand.get(4).cardNum>6) { // if no ace as first card and the second card is high, then discard the first card
						hand.set(0, deck1.deck[deckPosition]); deckPosition++;
					}
					// if both cards are lower half then discard 2 cards, discard card#1 and card#2 (not an ace)
					else {
						hand.set(0, deck1.deck[deckPosition]); deckPosition++;
						hand.set(1, deck1.deck[deckPosition]); deckPosition++;
					}
				}
			}
			else if(hasPair == true) {
				// Discard three cards
				if(match01==true) {
					// Discard 3 cards #3, #4, and #5 (positions 2,3,4)
					hand.set(2, deck1.deck[deckPosition]); deckPosition++;
					hand.set(3, deck1.deck[deckPosition]); deckPosition++;
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
				if(match12==true) {
					// Discard 3 cards #1, #4, and #5 (positions 0,3,4)
					hand.set(0, deck1.deck[deckPosition]); deckPosition++;
					hand.set(3, deck1.deck[deckPosition]); deckPosition++;
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
				if(match23==true) {
					// Discard 3 cards #1, #2, and #5 (positions 0,1,4)
					hand.set(0, deck1.deck[deckPosition]); deckPosition++;
					hand.set(1, deck1.deck[deckPosition]); deckPosition++;
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
				if(match34==true) {
					// Discard 3 cards #1, #2, and #3 (positions 0,1,2)
					hand.set(0, deck1.deck[deckPosition]); deckPosition++;
					hand.set(1, deck1.deck[deckPosition]); deckPosition++;
					hand.set(2, deck1.deck[deckPosition]); deckPosition++;
				}
			}
			
			// If the dealer has nothing good in his hand (not even a pair) but close to something good
			
			// One card off from a flush
			else if((hand.get(0).suit==hand.get(1).suit) && (hand.get(1).suit==hand.get(2).suit) && (hand.get(2).suit==hand.get(3).suit)) {
				// discard one card, card#5 (position 4)
				hand.set(4, deck1.deck[deckPosition]); deckPosition++;
			}
			else if((hand.get(0).suit==hand.get(1).suit) && (hand.get(1).suit==hand.get(2).suit) && (hand.get(2).suit==hand.get(4).suit)) {
				// discard one card, card#4 (position 3)
				hand.set(3, deck1.deck[deckPosition]); deckPosition++;
			}
			else if((hand.get(0).suit==hand.get(1).suit) && (hand.get(1).suit==hand.get(3).suit) && (hand.get(3).suit==hand.get(4).suit)) {
				// discard one card, card#3 (position 2)
				hand.set(2, deck1.deck[deckPosition]); deckPosition++;
			}
			else if((hand.get(0).suit==hand.get(2).suit) && (hand.get(2).suit==hand.get(3).suit) && (hand.get(3).suit==hand.get(4).suit)) {
				// discard one card, card#2 (position 1)
				hand.set(1, deck1.deck[deckPosition]); deckPosition++;
			}
			else if((hand.get(1).suit==hand.get(2).suit) && (hand.get(2).suit==hand.get(3).suit) && (hand.get(3).suit==hand.get(4).suit)) {
				// discard one card, card#1 (position 0)
				hand.set(0, deck1.deck[deckPosition]); deckPosition++;
			}
			
			// One card off from a straight (we must analyze the sequence)
			
			// first card in sequence is off (replace lowest card) low possibility #1 and #5
			else if((hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum) && (hand.get(3).cardNum+1==hand.get(4).cardNum)) {
				// replace card#1 (position 0)
				hand.set(0, deck1.deck[deckPosition]); deckPosition++;
			}
			// last card in sequence is off (replace highest card) high possibility #1 and #5
			else if((hand.get(0).cardNum+1==hand.get(1).cardNum) && (hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum)) {
				// replace card#5 (position 4)
				hand.set(4, deck1.deck[deckPosition]); deckPosition++;
			}
			// low possibility #2
			else if((hand.get(1).cardNum+2==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum) && (hand.get(3).cardNum+1==hand.get(4).cardNum)) {
				// replace card#1 (position 0)
				hand.set(0, deck1.deck[deckPosition]); deckPosition++;
			}
			// high possibility #2
			else if((hand.get(0).cardNum+2==hand.get(1).cardNum) && (hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum)) {
				// replace card#5 (position 4)
				hand.set(4, deck1.deck[deckPosition]); deckPosition++;
			}
			// low possibility #3
			else if((hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+2==hand.get(3).cardNum) && (hand.get(3).cardNum+1==hand.get(4).cardNum)) {
				// replace card#1 (position 0)
				hand.set(0, deck1.deck[deckPosition]); deckPosition++;
			}
			// high possibility #3
			else if((hand.get(0).cardNum+1==hand.get(1).cardNum) && (hand.get(1).cardNum+2==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum)) {
				// replace card#5 (position 4)
				hand.set(4, deck1.deck[deckPosition]); deckPosition++;
			}
			// low possibility #4
			else if((hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+1==hand.get(3).cardNum) && (hand.get(3).cardNum+2==hand.get(4).cardNum)) {
				// replace card#1 (position 0)
				hand.set(0, deck1.deck[deckPosition]); deckPosition++;
			}
			// high possibility #4
			else if((hand.get(0).cardNum+1==hand.get(1).cardNum) && (hand.get(1).cardNum+1==hand.get(2).cardNum) && (hand.get(2).cardNum+2==hand.get(3).cardNum)) {
				// replace card#5 (position 4)
				hand.set(4, deck1.deck[deckPosition]); deckPosition++;
			}
			// But what if we are going for a royal straight (an ace is treated as low so we need some extra rule to fix this
			else if(hand.get(0).cardNum == 1) {
				if((hand.get(2).cardNum==11) && (hand.get(3).cardNum==12) && (hand.get(4).cardNum==13)) {
					// discard card#2 (position 1)
					hand.set(1, deck1.deck[deckPosition]); deckPosition++;
				}
				else if((hand.get(1).cardNum==10) && (hand.get(3).cardNum==12) && (hand.get(4).cardNum==13)) {
					// discard card#3 (position 2)
					hand.set(2, deck1.deck[deckPosition]); deckPosition++;
				}
				else if((hand.get(1).cardNum==10) && (hand.get(2).cardNum==11) && (hand.get(4).cardNum==13)) {
					// discard card#4 (position 3)
					hand.set(3, deck1.deck[deckPosition]); deckPosition++;
				}
				else if((hand.get(1).cardNum==10) && (hand.get(2).cardNum==11) && (hand.get(3).cardNum==12)) {
					// discard card#5 (position 4)
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
				// If the dealer has a poor hand (not even a pair) and is NOT close to anything good (2 or more cards away from a high hand excl. pairs)
				// But the dealer has an ace high (sorted in the lowest position)
				// Keep the ace if you have it and discard the other 4 cards
				else {
					// Discard cards 2,3,4,5 (positions 1,2,3,4)
					hand.set(1, deck1.deck[deckPosition]); deckPosition++;
					hand.set(2, deck1.deck[deckPosition]); deckPosition++;
					hand.set(3, deck1.deck[deckPosition]); deckPosition++;
					hand.set(4, deck1.deck[deckPosition]); deckPosition++;
				}
			}
			// Among the worst of possible hands and no ace:
			// Keep the 2 highest cards and discard the other 3 cards
			else {
				// Discard cards 1,2,3 (positions 0,1,2)
				hand.set(0, deck1.deck[deckPosition]); deckPosition++;
				hand.set(1, deck1.deck[deckPosition]); deckPosition++;
				hand.set(2, deck1.deck[deckPosition]); deckPosition++;
			}
		}
		
	}
	
	public static void scoring(Hand hand) { // Score based on value of the hand plus high cards

		/*
		 * NOW WE SET UP OUR SCORING SYSTEM
		 */
		
		// Let's give the player 0 points to start and add points based on their hand
		handPoints = 0;
		
		// A sum of card values can come in handy for tie breakers
		int handSum = 0; 
		for(int i=0; i<5; i++) { handSum += hand.get(i).cardNum; }
		
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
			if(hand.get(2).cardNum == 1) { handPoints += 60000; } 
			else { handPoints += hand.get(2).cardNum * 4000; }
		}
		else if(hasFullHouse == true) { 
			handPoints += 700000; 
			if(match02 == true) { // if the three of a kind is the first three cards then use the 1st card to represent the three of a kind values
				if(hand.get(1).cardNum == 1) { handPoints += 45000; }
				else { handPoints += hand.get(1).cardNum * 3000; }
			} else { // otherwise use the 3rd card (we are counting from zero, so really its the 4th card)
				if(hand.get(3).cardNum == 1) { handPoints += 45000; }
				else { handPoints += hand.get(3).cardNum * 3000; }
			}
		}
		else if(hasFlush == true) { 
			handPoints += 600000; 
			if(hand.get(0).cardNum == 1) {
				handPoints += 45000;
				handPoints += hand.get(4).cardNum * 3000;
				handPoints += hand.get(3).cardNum * 200;
				handPoints += hand.get(2).cardNum * 100;
				handPoints += hand.get(1).cardNum * 10;
			} else {
				handPoints += hand.get(4).cardNum * 3000;
				handPoints += hand.get(3).cardNum * 300;
				handPoints += hand.get(2).cardNum * 100;
				handPoints += hand.get(1).cardNum * 10;
				handPoints += hand.get(0).cardNum * 1;
			}
			
		}
		else if(hasStraight == true) { 
			handPoints += 500000; 
			handPoints += handSum; // This will cause the highest straight to win if more than one has a straight
		}
		else if(hasThreeKind == true) { 
			handPoints += 400000; 
			if(match02 == true) { // if the three of a kind is the first three cards then use the 1st card to represent the three of a kind values
				if(hand.get(1).cardNum == 1) { handPoints += 45000; }
				else { handPoints += hand.get(1).cardNum * 3000; }
			} else { // otherwise use the 3rd card (we are counting from zero, so really its the 4th card)
				if(hand.get(3).cardNum == 1) { handPoints += 45000; }
				else { handPoints += hand.get(3).cardNum * 3000; }
			}
		}
		else if(hasTwoPair == true) { 
			handPoints += 300000; 
			if(match01 == true) {
				if(match23 == true) {
					if(hand.get(0).cardNum == 1) { 
						handPoints += 45000; 
						handPoints += hand.get(2).cardNum * 200;
						handPoints += hand.get(4).cardNum * 10;
					} else {
						handPoints += hand.get(2).cardNum * 3000;
						handPoints += hand.get(0).cardNum * 200;
						handPoints += hand.get(4).cardNum * 10;
					}
				} else if(match34 == true) {
					if(hand.get(0).cardNum == 1) {
						handPoints += 45000;
						handPoints += hand.get(4).cardNum * 200;
						handPoints += hand.get(2).cardNum * 10;
					} else {
						handPoints += hand.get(4).cardNum * 3000;
						handPoints += hand.get(0).cardNum * 200;
						handPoints += hand.get(2).cardNum * 10;
					}
				}
			} else if(match12 == true) {
				if(match34 == true) {
					handPoints += hand.get(4).cardNum * 3000;
					handPoints += hand.get(2).cardNum * 200;
					// Ace needs to be worth 15 otherwise it would be treated as low card
					if(hand.get(0).cardNum == 1) { handPoints += 150; } 
					else { handPoints += hand.get(0).cardNum * 10; }
				}
			}
		}
		
		else if(hasPair == true) { 
			handPoints += 200000;
			if(match01 == true) {
				if(hand.get(0).cardNum == 1) {
					handPoints += 45000;
					handPoints += hand.get(4).cardNum * 3000;
					handPoints += hand.get(3).cardNum * 200;
					handPoints += hand.get(2).cardNum * 10;
				} else {
					handPoints += hand.get(0).cardNum * 3000;
					handPoints += hand.get(4).cardNum * 200;
					handPoints += hand.get(3).cardNum * 100;
					handPoints += hand.get(2).cardNum * 10;
				}
			} else if(match12 == true) {
				handPoints += hand.get(1).cardNum * 3000;
				handPoints += hand.get(4).cardNum * 200;
				handPoints += hand.get(3).cardNum * 100;
				// Things get complicated when an Ace is the high card so we need to give it higher points than any other combination of high cards.
				if(hand.get(0).cardNum == 1) { handPoints += 4500; }
				else { handPoints += hand.get(0).cardNum * 10; }
				
			} else if(match23 == true) {
				handPoints += hand.get(2).cardNum * 3000;
				handPoints += hand.get(4).cardNum * 200;
				handPoints += hand.get(1).cardNum * 100;
				if(hand.get(0).cardNum == 1) { handPoints += 4500; }
				else { handPoints += hand.get(0).cardNum * 10; }
				
			} else if(match34 == true) {
				handPoints += hand.get(4).cardNum * 3000;
				handPoints += hand.get(2).cardNum * 200;
				handPoints += hand.get(1).cardNum * 100;
				if(hand.get(0).cardNum == 1) { handPoints += 4500; }
				else { handPoints += hand.get(0).cardNum * 10; }
				
			}
		}
		
		else {  // Only has a high card, no pair or better hand
			handPoints += 100000; 
			if(hand.get(0).cardNum == 1) {
				handPoints += 45000;
				handPoints += hand.get(4).cardNum * 3000;
				handPoints += hand.get(3).cardNum * 200;
				handPoints += hand.get(2).cardNum * 100;
				handPoints += hand.get(1).cardNum * 10;
			} else {
				handPoints += hand.get(4).cardNum * 3000;
				handPoints += hand.get(3).cardNum * 300;
				handPoints += hand.get(2).cardNum * 100;
				handPoints += hand.get(1).cardNum * 10;
				handPoints += hand.get(0).cardNum * 1;
			}
		} // a generous 100,000 points for having a high card (basically points for participating)
		
		if(hand==dealerHand) { dealerPoints = handPoints; }
		if(hand==playerHand) { playerPoints = handPoints; }
		
		System.out.println("Total points: " + handPoints);
		
	}
	
	public static void fiveCardDraw() {
		// The game of 5 Card Draw Poker
		dealFiveCardFirst();
		//int j = 0;
		deckPosition = 0;
		for(int i=deckPosition; i<deckPosition+10; i++) {
			if(i%2==0) {
				playerHand.add(deck1.deck[i]);
				//System.out.println("Player Hand: " + playerHand.get(j).cardNum);
			} else {
				dealerHand.add(deck1.deck[i]);
				//System.out.println("Dealer Hand: " + dealerHand.get(j).cardNum);
				//j++;
				deckPosition++;
				System.out.println(deckPosition); // testing position and debugging
			}
		}
		deckPosition++;
		
		sortFiveCards(playerHand);
		sortFiveCards(dealerHand);
		
		System.out.println("\n\nPlayer Hand: ");
		for(int i=0; i<5; i++) { playerHand.get(i).print(); }
		System.out.println("\nDealer Hand: ");
		for(int i=0; i<5; i++) { dealerHand.get(i).print(); }
		

		
		//boolean playersTurn = true;
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
				for(int i=0; i<selectionSize; i++) { playerHand.set(selection[i], deck1.deck[deckPosition]); deckPosition++; }
				System.out.println("Your hand is now: ");
				wrongInput = false;
			} else if(selectionSize==0) { 
				System.out.println("You have chosen to stand pat (to keep all your cards)...\nYour cards remain:"); 
				wrongInput = false;
			} else if(selectionSize==5) { 
				System.out.println("You have chosen to replace all your cards..."); 
				for(int i=0; i<5; i++) { playerHand.set(i, deck1.deck[deckPosition]); deckPosition++; }
				System.out.println("Your hand is now: "); 
				wrongInput = false;
			} else { 
				System.out.println("That was not a valid entry..."); 
				wrongInput = true;
			} 
		} while(wrongInput == true);
		
		sortFiveCards(playerHand);
		
		for(int i=0; i<5; i++) { playerHand.get(i).print(); }
		// NOW WE NEED TO ADD IN RULES TO CALCULATE THE USER'S SCORE BASED OFF OF THE CARDS HE/SHE HOLDS
		rules(playerHand);
		scoring(playerHand);
		// After the dealer has his hand, we check the rules so the dealer can back up its decision
		rules(dealerHand);
		// Based on the rules above and the dealer Decision System run below, the dealer will make smart decisions
		DDS(dealerHand);
		// Sort the dealers cards after its decision
		sortFiveCards(dealerHand);
		
		// Print the dealers new hand
		System.out.println("\nDealer\'s hand is now: ");
		for(int i=0; i<5; i++) { dealerHand.get(i).print(); }
		// We need to check the rules one more time to see what hand the dealer has before scoring
		rules(dealerHand);
		scoring(dealerHand);
		
		if(dealerPoints>playerPoints) { System.out.println("Dealer Wins!"); }
		else if(dealerPoints==playerPoints) { System.out.println("Player1 and Dealer have Tied!"); }
		else if(dealerPoints<playerPoints) { System.out.println("Player1 Wins!"); }
		
	}
	
}
