package uk.ac.aber.dcs.cs12320.cards.manage;

import java.util.ArrayList;

import uk.ac.aber.dcs.cs12320.cards.gui.TheFrame;

public class GUIcontrol {
	
	TheFrame frame = new TheFrame();
	
	private ArrayList<String> cardStrings = new ArrayList<String>();
	
	
	public void addCard(String imageName) {

		cardStrings.add(imageName);
		
		frame.cardDisplay(cardStrings);
		
	}


	
	public void replace(int i, int o) {
		
		cardStrings.set(i, cardStrings.get(o));
	
		cardStrings.remove(o);
		
		frame.cardDisplay(cardStrings);
	}



	public void finished() {
		frame.allDone();
		
	}
}
