package love;

import java.util.Vector;
/**
 * Maintains records of the Player's in the game, main method present here.
 * @author jpolonsky
 *
 */
public class Driver {

	private Vector<Player> players = new Vector<Player>();
	private int numCurrentPlayer;
	/**
	 * Run as many instances of Love Letter as you like. Hardcoded to 1000 presently. TODO Switch to args[]
	 * @param args from the command line
	 */
	public static void main(String[] args) {
		
		for(int i = 0; i < 1000; i++) {
			System.out.println("Start of Game");		
			new Driver(2);
			System.out.println("End of Game");
		}
	}
	/**
	 * Runs one complete simulation of the game of Love Letter
	 * @param numPlayers used determine how many Players should be set up as well as qualities of the Deck and discard pile
	 */
	public Driver(int numPlayers) {
		Deck newDeck = new Deck(numPlayers);
		System.out.println("New Deck: " + newDeck);
		for (int i = 0; i < numPlayers; i++) {
			Player newPlayer = new Player(i, numPlayers);
			players.add(newPlayer);
			newPlayer.draw(newDeck);
		}

		while (newDeck.hasCards() && getActivePlayers() > 1) {

			Player currentPlayer = players.elementAt(numCurrentPlayer);

			if (currentPlayer.isActive()) {
				System.out.println("\n" + currentPlayer);
				Vector<Player> copyOfPlayers = new Vector<Player>();
				copyOfPlayers.addAll(players);
				System.out.println("\tPlays: "
						+ currentPlayer.play(copyOfPlayers, newDeck));
			}

			numCurrentPlayer++;
			if (numCurrentPlayer >= numPlayers)
				numCurrentPlayer = 0;
		}

		// Check if multiple people survived till the end		
		if (getActivePlayers() > 1) {
			System.out.println("Multiple Survived until the End");
			for (Player currentPlayer : players) {
				if (currentPlayer.isActive()) {
					Card currentCard = currentPlayer.getCardInHand();
					for (Player comparePlayer : players) {
						if (comparePlayer.isActive()) {
							Card compareCard = comparePlayer.getCardInHand();
							if (compareCard.getValue() > currentCard.getValue())
								currentPlayer.Lose();
							else if (compareCard.getValue() < currentCard.getValue())
								comparePlayer.Lose();
						}
					}
				}
			}
		}

		// Declare the Winner(s)
		for (Player currentPlayer : players) {
			if (currentPlayer.isActive())
				System.out.println("\n\nPlayer: " + currentPlayer
						+ "\nHAS WON!\n\n");
		}

	}
	/**
	 * 
	 * @return number of players still "in the round."
	 */
	private int getActivePlayers() {
		int activePlayers = 0;
		for (Player activePlayer : players) {
			if (activePlayer.isActive())
				activePlayers++;
		}
		return activePlayers;
	}

}
