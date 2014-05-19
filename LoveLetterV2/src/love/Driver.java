package love;

import java.util.Vector;

public class Driver {

	private Vector<Player> players;

	public static void main(String[] args) {
		System.out.println("Start of Program");
		new Driver(2);
	}

	public Driver(int numPlayers) {
		Deck newDeck = new Deck(numPlayers);
		System.out.println("New Deck: " + newDeck);		
		for (int i = 0; i < numPlayers; i++) {
			Player newPlayer = new Player(i,numPlayers);
			players.add(newPlayer);
			newPlayer.draw(newDeck);
		}

		for (Player currentPlayer : players) {
			while (newDeck.hasCards()) {
				currentPlayer.draw(newDeck);
				System.out.println(currentPlayer + "\n\tPlays: "
						+ currentPlayer.play(players));
			}
		}
	}

}
