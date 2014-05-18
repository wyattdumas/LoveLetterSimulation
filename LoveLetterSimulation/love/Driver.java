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
		System.out.println("New Deck: " + newDeck);
		players = new Player[numPlayers];
		for(int i=0; i<numPlayers; i++){
			Player player = new Player();
			player.draw(newDeck);
			players[i] = player;
		}
			 
		for (Player currentPlayer : players){
			while(newDeck.hasCards()){
				currentPlayer.draw(newDeck);
				System.out.println(currentPlayer + "\n-plays-");
				System.out.println(currentPlayer.play());
			}
		}
	}

}
