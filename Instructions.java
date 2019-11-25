import java.util.Scanner;
public class Instructions {
	Scanner scanner = new Scanner(System.in);
	String whichInstructions;
	Instructions() {
		while(true) {
			System.out.println("Get instructions for each game! \nExit instructions anytime by typing 0.  \nType 1 for Black Jack Instructions \nType 2 for Five Card Draw Instructions \nType 3 for Seven Card Stud Instructions \nType 4 for Texas Hold\'em Instructions \nType 5 to learn whats comming in version 2.0");
			whichInstructions = scanner.nextLine();
			if(whichInstructions.equals("0")) { break; }
			else if(whichInstructions.equals("1")) { blackJackI(); }
			else if(whichInstructions.equals("2")) { fiveCardI(); }
			else if(whichInstructions.equals("3")) { sevenCardI(); }
			else if(whichInstructions.equals("4")) { texasHoldemI(); }
			else if(whichInstructions.equals("5")) { version2(); }
			else { System.out.println("That was not a valid entry... Try again.\n"); }
		}
	}
	void blackJackI() {
		System.out.println("Black Jack Instructions: \n");
		System.out.println("General Rules: ");
		System.out.println("Black Jack is a poker card game where two cards are dealt to each player, one face up and one face down.");
		System.out.println("The goal of the game is to get the highest hand without going over 21.  \nGoing over 21 points is called a \"Bust\" and that is an automatic loss.");
		System.out.println("Let's go over scoring so you can calculate your hand...");
		System.out.println("\nPoints: ");
		System.out.println("10 points for each King, Queen, and Jack (face cards).");
		System.out.println("2-10 points for each card numbered 2-10. i.e. a 2 is worth 2 points and a 9 is worth 9 points.");
		System.out.println("Either 1 point or 11 points for each Ace (which ever is more beneficial for you.");
		System.out.println("You then add up the value of your cards to get your total points.\n");
		System.out.println("Starting with just two cards, if you have 21 right off the bat, you win automatically!\nThis is called Black Jack! It would be one Ace and one 10 or face card.");
		System.out.println("If you aren't dealt Black Jack, you have the option to hit or to stay.");
		System.out.println("Hit: This means you take an additional card from the deck.\nYou can \"hit\" as many times as you like, but be careful because you might bust.");
		System.out.println("Stay: This means that you do not want any additional cards and wish to end your turn.");
		System.out.println("After you end your turn with a \"stay\", the dealer executes his turn.\n");
		System.out.println("You only know one of the dealer\'s cards when you play your turn.");
		System.out.println("The dealer must automatically hit until he has at least 17 points.  ");
		System.out.println("In the case of a tie, the dealer will automatically win, thus you must always be at least 1 point high than the dealer.\n");
	}
	
	void fiveCardI() {
		System.out.println("5 Card Draw Instructions: \n");
		System.out.println("General Rules: ");
		System.out.println("5 Card Draw is a poker game where 5 cards are dealt to each player, \nand after a discard and redraw (must have 5 cards at all times) The highest hand wins!");
		System.out.println("Let's go over scoring so you can calculate each hand...");
		System.out.println("\nPoints: From best to worst...");
		System.out.println("Royal (Straight) Flush: This is the highest hand. It is a 10, Jack, Queen, King, and Ace all of the same suit!");
		System.out.println("Straight Flush: This like the Royal Flush as all cards must be of the same suit and need to follow sequence, but need not be \"royal\".");
		System.out.println("4 of a Kind: This is the third best hand.  You must get 4 of the same card in one hand.  i.e. 4 kings.");
		System.out.println("Full House: The next best hand is 3 of a kind and a separate pair.  i.e. 3 Kings and 2 Jacks.");
		System.out.println("Flush: A hand of all the same suit (Spades, Diamonds, Hearts, or Clubs). i.e. all 5 cards are Spades.");
		System.out.println("Straight: A hand of a complete sequence.  Aces can be either high or low.  i.e. Ace, 2, 3, 4, and 5.");
		System.out.println("3 of a Kind: Three of the same numbered card.  i.e. 3 Jacks or 3 Twos or 3 Aces, etc.");
		System.out.println("Two pair: Two separate pairs in one hand.  i.e. 2 Jacks and 2 Twos or 2 Threes and 2 Fours.");
		System.out.println("One Pair: Two of the same card.  i.e. 2 Jacks or 2 Aces, or 2 Tens, etc.");
		System.out.println("High Card: None of the above hands, but scored on your highest card first. i.e. Ace high, then King high, etc.\n");
		System.out.println("If two players have the same kind of hand as detailed above, then the value of their hand\'s cards are considered.\ni.e. If one player has a pair of Aces and the other has a pair of Kings, the player with the Aces wins.");
		System.out.println("If two players have the same kind of hand and they are the same value, then the next highest card is considered.\ni.e. If both players have a pair of Aces, look at the next highest card.  \nIf one has a King high and the other a Queen high, the player with the King high wins.");
		System.out.println("In a tie, you keep going down to the next card until the winner is determined. \ni.e. player1 has Ace, Ace, King, Queen, 5 and Dealer has Ace, Ace, King, Queen, 6");
		System.out.println("In the scenario above, we first check the kind of hand. Since they both have one pair we look at the cards in the pair.");
		System.out.println("Since both pairs are Aces, we look at the next highest card.  Since it is a King for both, we look at the next highest...");
		System.out.println("That would be a Queen, which again, they both have... \nNow we look at their last card, a 5 for player1 and a 6 for the dealer... The dealer wins!\n");
		System.out.println("Game flow:");
		System.out.println("After every player is dealt 5 cards and your turn begins you have to option to discard and redraw cards.");
		System.out.println("First choose how many cards you want to discard and redraw.  \ni.e. If you have a pair and the other three card suck, then you want to select 3.");
		System.out.println("Then you want to choose which cards to discard.  Enter the position(s) in your hand separately.");
		System.out.println("i.e. If you have: Ace, Ace, Ace, Two, Four... \nFirst enter 2 to discard two cards, then enter 4 and then 5 to discard the fourth and fifth cards.");
		System.out.println("After discarding and redrawing, your turn is over and the next player or the dealer goes.  \nAt the end, scores are compared and the highest score wins!\n It is possible, but extremely unlikely to have an absolute tie (all cards are exactly the same).\n");
	}
	
	void sevenCardI() {
		System.out.println("7 Card Stud Instructions: \n");
		System.out.println("7 Card Stud is a unique variation of poker that relies on betting.\nFirst 3 cards are dealt with the first two face down and the third face up."
				+ "\nA bet is made and a 4th card is dealt face up. Another bet is made and a 5th card is dealt face up. "
				+ "\nyet another bet is made and a 6th card is dealt face up.  Finally another bet, and the 7th and final card is deal face down."
				+ "\n\nThe goal is to get the best 5-card hand.  \nThis game can be played with 2 to 8 players, but the more players and betting, the more fun."
				+ "\n\nUnfortunately this game isn\'t available in version 1.0, but you can check out our 5 Card Draw and Black Jack games.\n");
	}
	
	void texasHoldemI() {
		System.out.println("Texas Hold\'em Instructions: \n");
		System.out.println("Texas Hold\'em is a variation of poker that deals two cards face down to each player. \nThen burns one card and lays out three community cards fae up called the flop."
				+ "\nCommunity cards mean that all players can use them to make a hand. \nThen betting ensues and a 4th community card layed down face up."
				+ "Another round of betting and a 5th community card is played face up. \nPlayers make the best 5 card hand from their own 2 cards and the 5 community cards."
				+ "\n\nUnfortunately this game isn\'t available in version 1.0, but you can check out our 5 Card Draw and Black Jack games.\n");
	}
	
	void version2() {
		System.out.println("Version 2 will have more games to include Seven Card Stud, Texas Hold\'em, and more!"
				+ "\nThere will be betting and pots and the ability to play with multiple users.  It\'s going to be awesome!\n"
				+ "Look out for it on my GitHub page coming in 2020.\n");
	}
}
