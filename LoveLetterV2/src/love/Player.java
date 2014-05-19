package love;

import java.util.*;

public class Player {

	private Vector<Card> hand = new Vector<Card>();
	private Vector<Card> discardPile = new Vector<Card>();
	private int position = 0;
	private Random rng = new Random();

	public Player(int order) {
		position = order;
	}

	public void draw(Deck drawPile) {
		hand.add(drawPile.drawTopCard());
	}

	public Card play() {
		Card temp = hand.remove(rng.nextInt(2));
		discardPile.add(temp);
		return temp;
	}

	public Card play(Player[] players) {
		Card card1 = hand.elementAt(0);
		Card card2 = hand.elementAt(1);
		if (card1.getTitle() == "Princess") {
			discardPile.add(card2);
			hand.remove(1);
			return card2;
		} else {
			discardPile.add(card1);
			hand.remove(0);
			return card1;

		}
	}

	public int getPosition() {
		return position;
	}

	public String toString() {
		return "Player: " + position + "\n\tHand: " + hand;
	}

}