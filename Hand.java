
public class Hand {
	String name = "";
	/*
	 * I wish to deal a hand to players for various games.
	 * This could include blackjack, 5-card poker and 7-card stud, Texas Hold'em, and more.
	 * 
	 */
	/*
	 * Let's start with Poker and Black Jack.
	 * 
	 * For poker, rules need to be made regarding the value of different hands.
	 * 100 point: high card
	 * 200 points: a pair
	 * 300 points: 3 of a kind
	 * 400 points: 2 pair
	 * 500 points: a flush (having all the same suit)
	 * 600 points: full house (a pair and three of a kind)
	 * 700 points: a straight (having numbers following a consecutive order i.e. 2,3,4,5,6)
	 * 800 points: 4 of a kind
	 * 900 points: a straight flush (having a straight all in the same suit)
	 * 1000 points: a royal straight flush (having a straight in the same suit of specifically 10, J, Q, K, A)
	 * 
	 * If players have the same hand as each other, the next tie breaker will be the highest card.
	 * This will be a fraction of a point.
	 * 2 points for a 2
	 * 3 points for a 3
	 * 4 points for a 4
	 * 5 points for a 5
	 * 6 points for a 6
	 * 7 points for a 7
	 * 8 points for a 8
	 * 9 points for a 9
	 * 10 points for a 10
	 * 11 points for a J
	 * 12 points for a Q
	 * 13 points for a K
	 * 14 points for an A
	 * 
	 * If players still have the same hand and even the same high card, the win will be suit of the highest card
	 * 0.001 for clubs
	 * 0.002 for hearts
	 * 0.003 for diamonds
	 * 0.004 for spades
	 * 
	 * The total points for a hand will be added up to determine a winner.
	 * 
	 * For black Jack, two cards will be dealt to the user and two cards will be dealt to the dealer.
	 * The user will see both of their own cards but only one of the dealer's (1 face up, 1 face down)
	 * The dealer will automatically hit until 17, so doesn't really need to "see" the player's cards
	 * 
	 * Rules:
	 * The player may opt to "hit" (get another card) until the player "busts" (goes over 21) or decides to stop
	 * The dealer will automatically hit until 17 unless the player already busted or has blackjack (21 right off the bat).  
	 * The player (or dealer) with the highest hand (except over 21, a bust) wins.  The dealer wins all draws.
	 * An instant 21 from the first two cards is called "black jack" and wins instantly (even over 21 from hitting).
	 * Deciding not to "hit" is called "stay".  Splitting will not happen in the first version of this game. 
	 * 
	 * Card values must add up to 21 or as close as possible without going over. 
	 * cards 1 through 10 are valued the same as their number.  Cards of Jack, Queen, and King are all valued at 10.
	 * Aces can be valued at either 1 or 11, which ever benefits the player or dealer who holds the ace(s).
	 * 
	 * We will first develop these games for the console, then later implement them in a GUI.
	 * 
	 */
	void method(int game) {
		if(game==1) { // game selection (black jack "1" or other game)
			Card[] hand = new Card[2];
		} else if(game==2) { // game selection (5 card draw "2" or other game)
			Card[] hand = new Card[5];
		} else if(game==3) { // game selection (7 card stud "3" or other game)
			Card[] hand = new Card[7];
		} else if(game==4) { // game selection (texas hold em "4" or other game) Have to double check rules on dealing
			Card[] hand = new Card[3];
		}
	}

	
}
