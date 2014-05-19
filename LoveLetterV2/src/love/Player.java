package love;

import java.util.*;

public class Player {

	private Vector<Card> hand = new Vector<Card>();
	private Vector<Card> discardPile = new Vector<Card>();
	private HashMap knownCards = new HashMap();
	private int position;
	private Random rng = new Random();
	private boolean active = true; 
	private boolean defended;

	public Player(int order, int numPlayers) {
		position = order;		
	}

	public void draw(Deck drawPile) {
		hand.add(drawPile.drawTopCard());
	}
	
	public void discardHand() {
		discardPile.add(hand.remove(0));
	}

	public Card play(Vector<Player> allPlayers) {
		Card temp = hand.remove(rng.nextInt(2));
		discardPile.add(temp);
		return temp;
		
		/*
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

		} */
	}	
	
	public void Lose() {
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}

	public Card getHand() throws Exception {
		//This should only return 1 active card
		if(hand.size() == 1) {
			return hand.firstElement();
		} else {
			throw new Exception("More then 1 card in Hand");
		}		
	}

	public void setHand(Card activeCard) {
		hand.removeAllElements();
		hand.add(activeCard);
	}

	public int getPosition() {
		return position;
	}
	
	public boolean isDefended() {
		return defended;
	}

	public void setDefended() {
		this.defended = true;
	}

	public String toString() {
		return "Player: " + position + "\n\tHand: " + hand;
	}
	
	
	
}