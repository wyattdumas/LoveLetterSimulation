package love;

public class Driver {

	private Player[] players;
	
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
	
	public Driver()
	{
		Deck newDeck = new Deck();
		players = new Player[4];
	}

}
