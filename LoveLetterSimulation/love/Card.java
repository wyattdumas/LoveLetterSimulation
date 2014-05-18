package love;

public abstract class Card {

	int value;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	int number;
	private String title;
	
	public Card(int num) {
		number = num;
	}
	public String toString() {
		return "Card Value: " + value + " Number:" + number + " Text:" + getTitle();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String text) {
		this.title = text;
	}
}
