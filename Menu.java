import java.awt.Color;

public class Menu {
	//set parameters and add the images
		Menu() {
			EZ.initialize(1200, 800);
			EZ.setBackgroundColor(new Color(0, 100, 0));
			EZText text = EZ.addText(600, 100, "ULTIMATE POKER", Color.white, 75);
			text.setFont("8-BIT WONDER.TTF");
			EZImage Bj = EZ.addImage("Blackjack.png",100,300);
			EZImage FCD = EZ.addImage("5CardDraw.png", 100, 550);
			EZImage SCS = EZ.addImage("7CardStud.png", 500,300);
			EZImage TH = EZ.addImage("TexasHoldem.png",500 , 550);
			EZImage setting = EZ.addImage("settings.png", 1000, 300);
			EZImage quit = EZ.addImage("quit.png", 1000, 550);
			
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
				      }
				      
				      if (SCS.isPointInElement(clickX,  clickY)) {
				    	  //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
				      }		
				      if (TH.isPointInElement(clickX,  clickY)) {
				    	  //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
				      }

				      if (setting.isPointInElement(clickX,  clickY)) {
				    	  //gameSelection = 1;
				    	     Bj.translateTo(-2000,-2000);
					    	 FCD.translateTo(-2000, -2000);
					    	 SCS.translateTo(-2000, -2000);
					    	 TH.translateTo(-2000, -2000);
					    	 setting.translateTo(-2000, -2000);
					    	 quit.translateTo(-2000, -2000);
				      }
				      if (quit.isPointInElement(clickX,  clickY)) {
				    	  System.exit(0);
				      }
				  }
				  EZ.refreshScreen();
			}
		}
}
