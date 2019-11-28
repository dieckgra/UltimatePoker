import java.awt.Color;

public class Menu {
	//set parameters and add the images
		Menu() {
			EZ.initialize(1200, 800);
			EZ.setBackgroundColor(new Color(0, 100, 0));
			EZImage backgroundPicture = EZ.addImage("BG.jpg", 600,400);
			EZText text = EZ.addText(600, 100, "ULTIMATE POKER!", Color.white, 75); 
			text.setFont("Amaranth-Regular.TTF"); //font still up for debate
			
			//icon names
			EZText fivecarddraw = EZ.addText(283, 532, "5 Card Draw", Color.white, 25);
			EZText blackjack = EZ.addText(459, 380, "Black Jack", Color.white, 25);
			EZText texasholdem = EZ.addText(726, 384, "Texas Hold'em", Color.white, 25);
			EZText sevencardstud = EZ.addText(906, 534, "7 Card Stud", Color.white, 25);
			
			//[feel free to fine tune the X and Y if you see anything off]
			EZImage Bj = EZ.addImage("blackjackFinal.png",459,310);
			EZImage FCD = EZ.addImage("5carddrawFinal.png", 283, 464);
			EZImage SCS = EZ.addImage("7cardstudFinal.png", 908,464);
			EZImage TH = EZ.addImage("texasholdemFinal.png",726, 312);
			EZImage setting = EZ.addImage("settingsfinal.png", 459, 528);
			EZImage quit = EZ.addImage("exitFinal.png", 726, 537);
			
			int clickX=0;
			int clickY=0;
			
			while(true) {
				  
				
				
				  // Get the mouseʻs X and Y position
				  clickX = EZInteraction.getXMouse();
				  clickY = EZInteraction.getYMouse();
				  
				  
				  if (EZInteraction.wasMouseLeftButtonPressed()) {

					  // If clickX and clickY is on top of my picture then...
				      if (Bj.isPointInElement(clickX, clickY)) {		    	  
				    	 //gameSelection = 1;
				    	 Bj.translateTo(-2000,-2000);
				    	 FCD.translateTo(-2000, -2000);
				    	 SCS.translateTo(-2000, -2000);
				    	 TH.translateTo(-2000, -2000);
				    	 setting.translateTo(-2000, -2000);
				    	 quit.translateTo(-2000, -2000);
				    	
				    	 //moves title to be title of selected game (POS,size and font still up for debate)
				    	text.setMsg("BlackJack!");	
				    	text.setFontSize(40);
				    	text.translateTo(120, 35);
				    	 
				    	fivecarddraw.translateTo(-2000, -2000);
				    	blackjack.translateTo(-2000, -2000);
				    	texasholdem.translateTo(-2000, -2000);
				    	sevencardstud.translateTo(-2000, -2000);
				    	 
				      }
				      
				      // If click X and clickY is on my second picture....
				      if (FCD.isPointInElement(clickX, clickY)) {			    	  
				    	     //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
					    	 
					    	//moves title to be title of selected game (POS,size and font still up for debate)
					    	 text.setMsg("5 Card Draw!");	
						    	text.setFontSize(40);
						    	text.translateTo(120, 35);
						    	
						    	//moves icon names out of the screen
						    	fivecarddraw.translateTo(-2000, -2000);
						    	blackjack.translateTo(-2000, -2000);
						    	texasholdem.translateTo(-2000, -2000);
						    	sevencardstud.translateTo(-2000, -2000);
						    	
					    	 
					    	 
				      }
				      
				      if (SCS.isPointInElement(clickX,  clickY)) {
				    	  //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
					    	 
					    	//moves icon names out of the screen
					    	 fivecarddraw.translateTo(-2000, -2000);
						    	blackjack.translateTo(-2000, -2000);
						    	texasholdem.translateTo(-2000, -2000);
						    	sevencardstud.translateTo(-2000, -2000);
					    	 
					    	 //comin soon
				      }		
				      if (TH.isPointInElement(clickX,  clickY)) {
				    	  //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
					    	 
					    	//moves icon names out of the screen
					    	 fivecarddraw.translateTo(-2000, -2000);
						    	blackjack.translateTo(-2000, -2000);
						    	texasholdem.translateTo(-2000, -2000);
						    	sevencardstud.translateTo(-2000, -2000);
					    	 
					    	 //comin soon
				      }

				      if (setting.isPointInElement(clickX,  clickY)) {
				    	  //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
					    	 
					    	//moves icon names out of the screen
					    	 fivecarddraw.translateTo(-2000, -2000);
						    	blackjack.translateTo(-2000, -2000);
						    	texasholdem.translateTo(-2000, -2000);
						    	sevencardstud.translateTo(-2000, -2000);
				      }
				      if (quit.isPointInElement(clickX,  clickY)) {
				    	  System.exit(0);
				      }
				  }
				  EZ.refreshScreen();
			}
		}
}
