package love;

import java.util.*;

public class Player {

	private Card cardInHand;
	private Vector<Card> discardPile = new Vector<Card>();
	private HashMap<Player, CardType> knownCards = new HashMap<Player, CardType>();
	private int position;
	private Random rng = new Random();
	private boolean active = true;
	private boolean defended;

	public Player(int order, int numPlayers) {
		position = order;
	}

	public void draw(Deck drawPile) {
		cardInHand = drawPile.drawTopCard();
	}

	public Card discardHand() {
		Card discardCard = cardInHand;
		cardInHand = null;
		return discardCard;
	}

	public void addToDiscard(Card currentCard) {
		discardPile.add(currentCard);
	}

	public void addKnownCard(Player target, CardType type) {
		if (target == this)
			return;
		knownCards.put(target, type);
	}

	public Card play(Vector<Player> allPlayers, Deck currentDeck) {

		allPlayers.remove(this);

		Vector<Player> toDelete = new Vector<Player>();
		for (Player currentPlayer : allPlayers) {
			if (!currentPlayer.isActive() || currentPlayer.isDefended())
				toDelete.add(currentPlayer);
		}
		for (Player deletePlayer : toDelete) {
			allPlayers.remove(deletePlayer);
		}

		Card drawnCard = currentDeck.drawTopCard();
		System.out.println("\tDrawn Card:" + drawnCard);
		Card playedCard;
		int randInt;
		CardType guess;
		Player target = null;

		// Basic Logic, Don't Play the princess otherwise you lose
		if (drawnCard.getType() == CardType.PRINCESS) {
			playedCard = cardInHand;
			cardInHand = drawnCard;
			guess = CardType.COUNTESS;
			if (allPlayers.size() > 0)
				target = allPlayers.firstElement();
		} else if (cardInHand.getType() == CardType.PRINCESS) {
			playedCard = drawnCard;
			guess = CardType.COUNTESS;
			if (allPlayers.size() > 0)
				target = allPlayers.firstElement();
		} else {
			randInt = rng.nextInt(2);
			if (randInt == 0) {
				playedCard = cardInHand;
				cardInHand = drawnCard;
				guess = CardType.PRINCESS;
				if (allPlayers.size() > 0)
					target = allPlayers.firstElement();
			} else {
				playedCard = drawnCard;
				guess = CardType.PRINCESS;
				if (allPlayers.size() > 0)
					target = allPlayers.firstElement();
			}
		}

		playedCard.Play(currentDeck, this, target, guess);
		return playedCard;
	}

	public void Lose() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public Card getCardInHand() {
		return cardInHand;
	}

	public void setHand(Card activeCard) {
		cardInHand = activeCard;
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
		return "Player: " + position + "\n\tCard In Hand: " + cardInHand;
	}

}