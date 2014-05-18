package love;

public class Driver {

	private Player[] players;
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		new Driver(4);
	}
	
	public Driver(int numPlayers)
	{
		Deck newDeck = new Deck();
		players = new Player[numPlayers];
	}

}
