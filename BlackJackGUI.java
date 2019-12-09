import java.awt.Color;
//Made by Douglas Dieckgraefe, Jordan Cooper, and Liam Tsamous
public class BlackJackGUI extends GameGUI {

	
	int deckPosition;
	int clickX, clickY;
	int playerHandTotal = 0;
	int dealerHandTotal = 0;
	//int handScore = 0;
	int cardCount = 2;
	boolean escape, soundOn;
	static boolean playAgain = true;
	static EZRectangle playAgainButton;
	static EZImage quitButton, backButton, hitButton, standButton;
	static EZText playerTotal, dealerTotal, winnerT, playAgainT;

	public static Deck deck2 = MainClass.menu.deck1; //deck 2 here
	
	BlackJackGUI() {//starts game
		super();
		//blackJackGUI();
		escape = false;
		quitButton = EZ.addImage("exitFinal.png", -2000, -2000);
		backButton = EZ.addImage("back.png", -2000, -2000);
		
		playerTotal = EZ.addText(-2000, -2000, "Player Total: " + "0", Color.white, 40);
		playerTotal.setFont("Amaranth-Regular.ttf");
		dealerTotal = EZ.addText(-2000, -2000, "Dealer Total: " + "0" , Color.white,40);
		dealerTotal.setFont("Amaranth-Regular.ttf");
		winnerT = EZ.addText(-2000, -2000, "", Color.yellow, 50);
		winnerT.setFont("Amaranth-Regular.ttf");
		
		hitButton = EZ.addImage("hitButton.png", -2000, -2000);
		standButton = EZ.addImage("standButton.png", -2000, -2000);
		
		playAgainButton = EZ.addRectangle(-2000,-2000,170,70,Color.red,true);
		playAgainT = EZ.addText(-2000, -2000, "Play Again", Color.yellow, 35);
		playAgainT.setFont("Amaranth-Regular.ttf");
		
	}
	void blackJackGUI() {
		while(playAgain == true) {
			
			playAgain = false;
			
			boolean hasCharlie = false;
			clickX = 0;
			clickY = 0;
			//Menu menu = new Menu();
			showQuitBack();
			showHitStay();
			showScores();
			
			BlackJack bj = new BlackJack();//makes game
			deck1=bj.deck1; //deck 1 frame of game
			deck2.makeFaceDown(MainClass.menu.cardSel);//makes face down cards
			deck2.moveFaceDownBJ(); //grabs one
			bj.dealBlackJackFirst();//shuffle logic deck 
			
			int deckPosition = 0;
			playerHand.clear();
			dealerHand.clear();
			
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
			
			bj.sort(playerHand); //orders hand
			bj.sort(dealerHand);
			
			System.out.println("\n\nPlayer Hand: "); 
			for(int i=0; i<2; i++) { playerHand.get(i).print(); }
			System.out.println("\nDealer Hand: ");
			for(int i=0; i<2; i++) { dealerHand.get(i).print(); }
		
			for(int i=0; i<2; i++) {//draws cards
				deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650);//moves 2 player cards face up
			}
			deck2.deckImage[dealerHand.get(0).suit-1][dealerHand.get(0).cardNum-1].moveCardImage(175+1*205,300);//moves 1 dealer card face up
			
			if(dealerHand.get(0).cardNum == 1) { dealerTotal.setMsg("Dealer must have at least 12"); } 
			else { dealerTotal.setMsg("Dealer must have at least " + (dealerHand.get(0).cardNum + 1)); }
			
			playerTotal.setMsg("Player Total: " + score(playerHand)); 
			
			System.out.println(score(playerHand));
			
			soundOn = MainClass.menu.soundOn;
			
			while(escape==false) { 
				
				clickX = EZInteraction.getXMouse();
				clickY = EZInteraction.getYMouse();	
				
				boolean playersTurn = true;
				boolean autoWin = false;
				
				if(score(dealerHand) == 21) { System.out.println("Black Jack!\nDealer Wins!"); playersTurn = false; } 
				else if(score(playerHand) == 21) { System.out.println("Black Jack!\nPlayer Wins!"); playersTurn = false; autoWin = true;}
				
				if(EZInteraction.wasMouseLeftButtonPressed()) { 
					if(hitButton.isPointInElement(clickX, clickY)) { 
						if(soundOn == true) { MainClass.menu.coin.play(); }
						if(score(playerHand)<=21) { 
							playerHand.add(deck1.get(deckPosition));
							deckPosition++;
							System.out.println("The player drew: ");
							playerHand.print(playerHand.size()-1);
							System.out.println("\nThe player\'s hand is now: ");
							playerHand.print();
							bj.sort(playerHand); // Sort the new cards
							for(int i=0; i<playerHand.size(); i++) {
								if(playerHand.size() <= 5) {
									deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(175+i*205,650); 	
								}
								else if(playerHand.size() > 5) {
									deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].moveCardImage(150+i*150,650); 	
									deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].cardImage.pullToFront();
									deck2.deckImage[playerHand.get(i).suit-1][playerHand.get(i).cardNum-1].cardNumEZ.pullToFront();
									if((playerHand.size() >= 7) && (score(playerHand) <= 21)) {
										playerTotal.setMsg("" + score(playerHand) + " with 7 card Charlie!");
										hideHitStay();
										autoWin = true;
										hasCharlie = true;
									}
								}
							}
							playerTotal.setMsg("Player Total: " + score(playerHand)); 
							System.out.println(score(playerHand));
							cardCount++;
							if(score(playerHand)>21) {
								hideHitStay();
								playerTotal.setMsg("" + score(playerHand) + "... You BUST!");
								System.out.println("You bust, so the dealer wins!");
								playersTurn = false;
								autoWin = true;
							}
						}
					}
					if(standButton.isPointInElement(clickX, clickY)) {
						if(soundOn == true) { MainClass.menu.floop.play(); }
						hideHitStay();
						playersTurn = false;
					}
					if(playersTurn == false) {
						System.out.println("Dealer\'s turn!\n\nDealer\'s hand: ");
						dealerHand.print();
						
						deck2.hideFaceDown();
						deck2.deckImage[dealerHand.get(0).suit-1][dealerHand.get(0).cardNum-1].moveCardImage(175+0*205,300);
						deck2.deckImage[dealerHand.get(1).suit-1][dealerHand.get(1).cardNum-1].moveCardImage(175+1*205,300);
						if(autoWin == false) {
							if(score(dealerHand) >= 17) { System.out.println("The dealer stays..."); autoWin = true; }
							
							while(score(dealerHand) < 17) { 
								System.out.println("\nDealer hits and gets: ");
								dealerHand.add(deck1.get(deckPosition)); 
								dealerHand.print(dealerHand.size()-1); 
								System.out.println("\nThe dealer\'s hand is now: ");
								dealerHand.print();
								
								bj.sort(dealerHand);
								
								for(int i=0; i<dealerHand.size(); i++) {
									if(dealerHand.size() <= 5) {
										deck2.deckImage[dealerHand.get(i).suit-1][dealerHand.get(i).cardNum-1].moveCardImage(175+i*205,300);
									} else if(dealerHand.size() > 5) {
										deck2.deckImage[dealerHand.get(i).suit-1][dealerHand.get(i).cardNum-1].moveCardImage(150+i*150,300);
									}
								}
								deckPosition++;
							}
						}
						dealerTotal.setMsg("Dealer Total: " + score(dealerHand));
						System.out.println("Total is: " + score(dealerHand));
					}
					if(playersTurn == false) {
						if(hasCharlie == true) {
							dealerTotal.setMsg("Dealer Total: " + score(dealerHand));
							winnerT.translateTo(600, 467);
							winnerT.setMsg("PLAYER WINS!");
						}
						else if(score(dealerHand) > 21) { 
							if(soundOn == true) { MainClass.menu.winning.play(); }
							System.out.println("\nBUST!\nPlayer Wins!"); 
							dealerTotal.setMsg("" + score(dealerHand) + "... Dealer Busts"); 
							winnerT.translateTo(600, 467);
							winnerT.setMsg("PLAYER WINS!");
						}
						else if(score(playerHand) > 21) {
							if(soundOn == true) { MainClass.menu.losing.play(); }
							System.out.println("\nBUST!\nDealer Wins!"); 
							dealerTotal.setMsg("Dealer Total: " + score(dealerHand)); 
							winnerT.translateTo(600, 467);
							winnerT.setMsg("DEALER WINS!");
						}
						else if(score(dealerHand) >= score(playerHand)) { 
							if(soundOn == true) { MainClass.menu.losing.play(); }
							System.out.println("\nDealer Wins!"); 
							dealerTotal.setMsg("Dealer Total: " + score(dealerHand));
							winnerT.translateTo(600, 467);
							winnerT.setMsg("DEALER WINS!");
						} 
						else { 
							if(soundOn == true) { MainClass.menu.winning.play(); }
							System.out.println("\nPlayer Wins!"); 
							dealerTotal.setMsg("Dealer Total: " + score(dealerHand));
							winnerT.translateTo(600, 467);
							winnerT.setMsg("PLAYER WINS!");
						}
						showPlayAgain();
					}
					if(playAgainButton.isPointInElement(clickX, clickY)) {
						if(soundOn == true) { MainClass.menu.floop.play(); }
						System.out.println("Clicked Play Again!");
						playAgain = true;
						escape = true;
						hidePlayAgain();
						deck2.hideDeckImage();
						hideScores();
						winnerT.translateTo(-2000, -2000);
					}
					if(quitButton.isPointInElement(clickX, clickY)) { 
						if(soundOn==true) { MainClass.menu.floop.play(); }
						System.out.println("Should be quiting...");
						System.exit(0);
					}
					if(backButton.isPointInElement(clickX, clickY)) { 
						if(soundOn==true) { MainClass.menu.floop.play(); }
						System.out.println("Take us back...");
						escape = true;
						removeGame();
					}
				}
				EZ.refreshScreen();
			}
			escape = false;
		}
	}
	
	static void removeGame() {
		deck2.hideDeckImage();
		deck2.hideFaceDown();
		hideQuitBack();
		hideHitStay();
		hideScores();
		winnerT.translateTo(-2000, -2000);
		hidePlayAgain();
		Menu.showMenuStuff();
		
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
	
	static void hideHitStay() {
		hitButton.translateTo(-2000, -2000);
		standButton.translateTo(-2000, -2000);
	}
	
	static void showHitStay() {
		hitButton.translateTo(567, 467);
		standButton.translateTo(678, 467);
	}
	
	static void hideQuitBack() {
		quitButton.translateTo(-2000,-2000);
		backButton.translateTo(-2000,-2000);
	}
	
	static void showQuitBack() {
		quitButton.translateTo(1125, 75);
		backButton.translateTo(75, 75);
	}
	
	static void hideScores() {
		playerTotal.translateTo(-2000, -2000);
		dealerTotal.translateTo(-2000, -2000);
	}
	
	static void showScores() {
		playerTotal.translateTo(970, 467);
		dealerTotal.translateTo(250, 467);
	}
	
	static void hidePlayAgain() {
		playAgainButton.translateTo(-2000, -2000);
		playAgainT.translateTo(-2000, -2000);
	}
	
	static void showPlayAgain() {
		playAgainButton.translateTo(250, 75);
		playAgainT.translateTo(250, 75);
	}
}

