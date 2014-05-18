package love;

public class Driver {

	private Player[] players;
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		new Driver(2);
	}
	
	public Driver(int numPlayers)
	{
		Deck newDeck = new Deck(numPlayers);
		players = new Player[numPlayers];
		 for(int i=0; i<numPlayers; i++)
			 
		while(newDeck.hasCards())
		{
			
		}
	}

}
