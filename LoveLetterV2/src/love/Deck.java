package love;

import java.util.Collections;
import java.util.Vector;
import love.CardTypes.*;

public class Deck {

	private int totalCards = 16;
	private int discardCards;
	private Vector<Card> available = new Vector<Card>();
	private Vector<Card> discard = new Vector<Card>();

	public Deck(int numPlayers) {
		for (int i = 0; i < totalCards; i++) {
			if (i <= 4)
				available.add(new Guard(i));
			else if (i > 4 && i <= 6)
				available.add(new Priest(i));
			else if (i > 6 && i <= 8)
				available.add(new Baron(i));
			else if (i > 8 && i <= 10)
				available.add(new Handmaid(i));
			else if (i > 10 && i <= 12)
				available.add(new Prince(i));
			else if (i == 13)
				available.add(new King(i));
			else if (i == 14)
				available.add(new Countess(i));
			else if (i == 15)
				available.add(new Princess(i));

		}

		shuffle();

		if (numPlayers == 2) {
			discardCards = 4;
		} else if (numPlayers > 2) {
			discardCards = 1;
		}

		for (int i = 0; i < discardCards; i++) {
			discard.add(drawTopCard());
		}

	}

	private void shuffle() {
		Collections.shuffle(available);
	}

	public Card drawTopCard() {
		Card topCard = available.remove(0);
		return topCard;
	}

	public boolean hasCards() {
		return !available.isEmpty();
	}

	public String toString() {
		String output;

		output = "\nAvailable Pile \n";
		for (int i = 0; i < available.size(); i++) {
			output += "\t" + available.elementAt(i) + "\n";
		}
		output += "Discard Pile \n";
		for (int i = 0; i < discard.size(); i++) {
			output += "\t" + discard.elementAt(i) + "\n";
		}
		return output;
	}
}