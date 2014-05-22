package love;

import java.util.Collections;
import java.util.Vector;


/** Deck simulates a standard 16 card deck from Love Letter.
 * 
 * 
 * @author jpolonsky
 *
 */
public class Deck {

	private int discardCards;
	private Vector<Card> available = new Vector<Card>();
	private Vector<Card> discard = new Vector<Card>();
	private Vector<Card> allCards = new Vector<Card>();

	
	/** Discards varied numbers of cards based on <code>numPlayers</code>
	 * 
	 * @param numPlayers Number of people playing in a game of Love Letter
	 */
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
	/**
	 * 
	 * @return A vector with all of the cards in a standard Love Letter deck
	 */
	public Vector<Card> getAllCards() {
		return allCards;
	}
	/**
	 * 
	 * @return A count of the total number of cards left in the deck
	 */
	public int getNumAvailable() {
		return available.size();
	}
	/** 
	 * 
	 * @return The card at index 0 in the vector of available cards
	 */
	public Card drawTopCard() {
		Card topCard;
		if(available.size() != 0)
			topCard = available.remove(0);
		else //Special circumstance forced to draw and no cards left
			topCard = discard.remove(0);
		return topCard;
	}
	/**
	 * 
	 * @return Whether <code>available</code> is empty
	 */
	public boolean hasCards() {
		return !available.isEmpty();
	}

	/**
	 * Format:
	 * <pre>
	 * Available Pile
	 * 	*First available Card*
	 * 	*Second available Card*
	 * 	...
	 * Discard Pile
	 * 	*First discarded Card*
	 * 	*Second discarded Card*
	 * 	...
	 * </pre>
	 */
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
