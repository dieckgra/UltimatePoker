import java.awt.Color;

public class Setting {

	Setting() {
		EZText text = EZ.addText(600, 100, "Sound on", Color.white, 75);
		EZText text2 = EZ.addText(600, 300, "Sound off", Color.white, 75);
		EZImage box = EZ.addImage("square.png", 650, 100);
		EZImage box2 = EZ.addImage("square.png", 650, 300);
		EZImage greencheck = EZ.addImage("greencheck.png", -2000, -2000);
		
		
		int clickX=0;
		int clickY=0;
		
		while(true) {
			
			 // Get the mouseʻs X and Y position
			  clickX = EZInteraction.getXMouse();
			  clickY = EZInteraction.getYMouse();
			  
			  if (EZInteraction.wasMouseLeftButtonPressed()) {
				  
				  if (text.isPointInElement(clickX,clickY)) {
					  greencheck.translateTo(650,100);
				  }
				  if (text2.isPointInElement(clickX, clickY)) {
					  greencheck.translateTo(650,300);
				  }
			  }
		}
	}

}
