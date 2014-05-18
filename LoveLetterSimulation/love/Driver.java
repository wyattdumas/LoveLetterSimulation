package love;

public class Driver {

	private Player[] players;
	
	public static void main(String[] args) {
		System.out.println("Start of Program");
		new Driver(2);
	}
	
	public Driver(int numPlayers)
	{
		Deck newDeck = new Deck(numPlayers);
		System.out.println("New Deck: " + newDeck);
		players = new Player[numPlayers];
		for(int i=0; i<numPlayers; i++){
			players[i] = new Player(i);
			players[i].draw(newDeck);
		}
			 
		for (Player currentPlayer : players){
			while(newDeck.hasCards()){
				currentPlayer.draw(newDeck);
				System.out.println(currentPlayer + "\n\tPlays: " + currentPlayer.play());
			}
		}
	}

}
