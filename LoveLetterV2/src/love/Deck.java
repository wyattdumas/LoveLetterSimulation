package love;

import java.util.Collections;
import java.util.Vector;

public class Deck {
	
	private int discardCards;
	private Vector<Card> available = new Vector<Card>();
	private Vector<Card> discard = new Vector<Card>();
	private Vector<Card> allCards = new Vector<Card>();

	public Vector<Card> getAllCards() {
		return allCards;
	}

	public Deck(int numPlayers) {
		int cardNumber = 0;
		for(CardType type: CardType.values()) {
			for(int i = 0; i < type.getOccurences();i++) {
				available.add(new Card(cardNumber,type));
				cardNumber++;
			}			
		}
		allCards.addAll(available);
		
		Collections.shuffle(available);

		if (numPlayers == 2) {
			discardCards = 4;
		} else if (numPlayers > 2) {
			discardCards = 1;
		}

		for (int i = 0; i < discardCards; i++) {
			discard.add(drawTopCard());
		}

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
