package uk.ac.aber.dcs.cs12320.cards.game;

public class Cards {
	private char suit;
	private char value;
	private String fileName;

	public Cards(char suitType, char cardValue, String file) {
		this.suit = suitType;
		this.value = cardValue;
		this.fileName = file;
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Cards [suit=" + suit + ", value=" + value + ", fileName="
				+ fileName + "]";
	}
	

}
