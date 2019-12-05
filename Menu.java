import java.awt.Color;
import java.awt.event.KeyEvent;

public class Menu extends GameGUI {
	public Deck deck1 = new Deck();
	//set parameters and add the images
	static EZText title, subtitle, fivecarddraw, blackjack, texasholdem, sevencardstud, settingT, quitT;
	//text.setFont("8-BIT WONDER.TTF");
	static EZImage Bj, FCD, SCS, TH, setting, quit, backgroundPicture;
	Color gold, white;
	boolean playingFCD, playingBJ, playingSCS, playingTH;
	static boolean settingsOpenned = false;
	Setting settings;
	int clickX;
	int clickY;
	
		Menu() {
			
		}
		
		void callMenu() {
			// Set up GUI screen and menu
			EZ.initialize(1200, 800);
			EZ.setBackgroundColor(new Color(0, 100, 0));
			backgroundPicture = EZ.addImage("BG.jpg", 600,400);
			gold = new Color(212,175,55);
			white = new Color(255, 255, 255);
			title = EZ.addText(600, 100, "ULTIMATE POKER", white, 100);
			title.setFont("CinzelDecorative-Regular.ttf");
			subtitle = EZ.addText(600, 125, "", gold, 75);
			subtitle.setFont("Amaranth-Regular.ttf");
			
			settings = new Setting();
			
			//icon names for the buttons images below
			blackjack = EZ.addText(460, 355, "Black Jack", Color.white, 25);
			fivecarddraw = EZ.addText(280, 560, "5 Card Draw", Color.white, 25);
			sevencardstud = EZ.addText(910, 560, "7 Card Stud", Color.white, 25);
			texasholdem = EZ.addText(730, 355, "Texas Hold'em", Color.white, 25);
			settingT = EZ.addText(460, 730, "Settings/Rules", Color.white, 25);
			quitT = EZ.addText(730, 730, "Quit Game", Color.white, 25);
			
			// Button Images
			Bj = EZ.addImage("blackjackFinal.png",460,280);
			FCD = EZ.addImage("5carddrawFinal.png", 280, 485);
			SCS = EZ.addImage("7cardstudFinal.png", 910,485);
			TH = EZ.addImage("texasholdemFinal.png",730, 280);
			setting = EZ.addImage("settingsfinal.png", 460, 660);
			quit = EZ.addImage("exitFinal.png", 730, 660);
			
			
			deck1.makeDeckImage();
			deck1.makeFaceDown();
			
			//clickX=0;
			//clickY=0;
			
			while(true) {
				
				// Get the mouseʻs X and Y position
				clickX = EZInteraction.getXMouse();
				clickY = EZInteraction.getYMouse();
				
				if (EZInteraction.wasMouseLeftButtonReleased()) {
					// If clickX and clickY is on top of my picture then...
					if ((Bj.isPointInElement(clickX, clickY)) || (blackjack.isPointInElement(clickX, clickY))) {		    	  
						//MainClass.gameSelection.equals("1");
						hideMenuStuff();
						setGame("Black Jack");
						System.out.println("Black Jack button was pressed...");
						
						playingBJ=true;
						deck1.moveFaceDownBJ();
						BlackJackGUI bjGUI = new BlackJackGUI();
					}
					// If click X and clickY is on my second picture....
					if ((FCD.isPointInElement(clickX, clickY)) || (fivecarddraw.isPointInElement(clickX, clickY))) {			    	  
						//MainClass.gameSelection = 2;
						hideMenuStuff();
						setGame("5 Card Draw");
						
						// Launch the game
						playingFCD = true;
						deck1.moveFaceDownFCD();
						FiveCardDrawGUI fcdGUI = new FiveCardDrawGUI();
					}
					if ((SCS.isPointInElement(clickX,  clickY)) || (sevencardstud.isPointInElement(clickX, clickY))) {
						//MainClass.gameSelection = 3;
						hideMenuStuff();
						setGame("7 Card Stud");
						System.out.println("Seven Card Stud button was pressed...");
					}		
					if ((TH.isPointInElement(clickX,  clickY)) || (texasholdem.isPointInElement(clickX, clickY))) {
						//MainClass.gameSelection = 4;
						hideMenuStuff();
						setGame("Texas Hold\'em");
						System.out.println("Texas Hold\'em button was pressed...");
					}
					if ((setting.isPointInElement(clickX,  clickY)) || (settingT.isPointInElement(clickX, clickY))) {
						//MainClass.gameSelection = 6;
						hideMenuStuff();
						setGame("Settings");
						System.out.println("Setting button was pressed...");
						settingsOpenned = true;
						settings.showSettingStuff();  	
					}
					if ((quit.isPointInElement(clickX,  clickY)) || (quitT.isPointInElement(clickX, clickY))) {
						//MainClass.gameSelection = 0;
						System.out.println("The Quit Button was pressed... Quiting Now!");
						System.exit(0);
					}
				}
				if(EZInteraction.wasKeyReleased(KeyEvent.VK_SPACE)) { 
					if(playingFCD == true) {
						playingFCD = false;
						deck1.hideFaceDown();
						FiveCardDrawGUI.removeGame(); 
					}
					if(settingsOpenned==true) {
						settingsOpenned = false;
						settings.hideSettingStuff();
					}
					showMenuStuff(); 
				}
				EZ.refreshScreen();
			}
		}
		
		void setGame(String name) {
	    	title.setFontSize(50);
	    	title.translateTo(600, 50);
	    	subtitle.setMsg(name);
		}
		
		public static void showMenuStuff() {
			title.setFontSize(75);
			title.translateTo(600, 100);
			subtitle.setMsg("");
			
			// Move menu items back in place
			blackjack.translateTo(460, 355);
			fivecarddraw.translateTo(280, 560);
			sevencardstud.translateTo(910, 560);
			texasholdem.translateTo(730, 355);
			settingT.translateTo(460, 730);
			quitT.translateTo(730, 730);
			
			Bj.translateTo(460,280);
			FCD.translateTo(280, 485);
			SCS.translateTo(910,485);
			TH.translateTo(730, 280);
			setting.translateTo(460, 660);
			quit.translateTo(730, 660);
			
			System.out.println("Bringing menu back...");
		}
		
		void hideMenuStuff() {
   	     	Bj.translateTo(-2000,-2000);
   	     	blackjack.translateTo(-2000,-2000);
	    	FCD.translateTo(-2000, -2000);
	    	fivecarddraw.translateTo(-2000,-2000);
	    	SCS.translateTo(-2000, -2000);
	    	sevencardstud.translateTo(-2000,-2000);
	    	TH.translateTo(-2000, -2000);
	    	texasholdem.translateTo(-2000,-2000);
	    	setting.translateTo(-2000, -2000);
	    	settingT.translateTo(-2000, -2000);
	    	quit.translateTo(-2000, -2000);
	    	quitT.translateTo(-2000, -2000);
	    	
	    	System.out.println("Hiding the menu...");
		}
		
}