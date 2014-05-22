package love;

import java.util.*;
/**
 * Encapsulates the detail of an individual playing the game of Love Letter. This includes 
 * whether the player is still in the round, the card(s) in their hand, location
 * in the play order, and what this player knows about what has been played and other player's
 * hands as well as whether this player is under the protection of the Handmaid.
 * @author wdumas
 *
 */
public class Player {

	private Card cardInHand;
	private Vector<Card> discardPile = new Vector<Card>();
	private HashMap<Player, CardType> knownCards = new HashMap<Player, CardType>();
	private int position;
	private Random rng = new Random();
	private boolean active = true;
	private boolean defended;

	/**
	 * 
	 * @param order location around the table
	 * @param numPlayers total number of players at the table
	 */
	public Player(int order, int numPlayers) {
		position = order;
	}
	/**
	 * Removes the card at index 0 in the available vector for this <code>drawPile</code> and puts it into 
	 * this Player's hand
	 * @param drawPile the deck for this game
	 */
	public void draw(Deck drawPile) {
		cardInHand = drawPile.drawTopCard();
	}
	/**
	 * plays this Player's card
	 * @return the Card that was in this player's hand
	 */
	public Card discardHand() {
		Card discardCard = cardInHand;
		cardInHand = null;
		return discardCard;
	}
	/** 
	 * 
	 * @param currentCard the Card to be added to the discard pile for the current Player.
	 */
	public void addToDiscard(Card currentCard) {
		discardPile.add(currentCard);
	}
	/**
	 * 
	 * @param target a PLayer about whom this Player has learned something (for use with Priest)
	 * @param type the Card type that player has in hand
	 */
	public void addKnownCard(Player target, CardType type) {
		if (target == this)
			return;
		knownCards.put(target, type);
	}
	/**
	 * Handles Player AI
	 * @param allPlayers the Players in this game
	 * @param currentDeck the Deck in this game
	 * @return the Card that the Player decides to discard
	 */
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
	/**
	 * Sets this Player to inactive 
	 */
	public void Lose() {
		active = false;
	}
	/**
	 * 
	 * @return the value of active for this Player
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * 
	 * @return the Card in this Player's hand
	 */
	public Card getCardInHand() {
		return cardInHand;
	}
	/**
	 * Used by effects that require trading of cards etc. (e.g. King)
	 * @param activeCard the card to put into this Player's hand
	 */
	public void setHand(Card activeCard) {
		cardInHand = activeCard;
	}
	/**
	 * 
	 * @return position at the table
	 */
	public int getPosition() {
		return position;
	}
	/** 
	 * 
	 * @return whether the current Player is immune to effects
	 */
	public boolean isDefended() {
		return defended;
	}
	/**
	 * For use when a defensive Card e.g. Handmaid is played
	 */
	public void setDefended() {
		this.defended = true;
	}
	/**
	 * Format:
	 * <pre>
	 * Player: *position at table* Card In Hand: *card in this Player's hand*
	 * </pre>
	 */
	public String toString() {
		return "Player: " + position + "\n\tCard In Hand: " + cardInHand;
	}

}