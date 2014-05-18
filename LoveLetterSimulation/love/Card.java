package love;

public class Card {

	int value;
	int number;
	String text;
	
	public Card(int num) {
		number = num;
	}
	public String toString() {
		return "Card Value: " + value + " Number:" + number + " Text:" + text;
	}
}
