package love;

import java.util.*;

/**
 * Encapsulates the detail of an individual playing the game of Love Letter.
 * This includes whether the player is still in the round, the card(s) in their
 * hand, location in the play order, and what this player knows about what has
 * been played and other player's hands as well as whether this player is under
 * the protection of the Handmaid.
 * 
 * @author wdumas
 * 
 */
public class Player {

	//Go back to vector to make sure this is handled properly
	private Vector<Card> cardsInHand = new Vector<Card>();	
	private Deck gameDeck;
	private HashMap<Player, CardType> knownCards = new HashMap<Player, CardType>(); // Clever
																					// implementation
																					// JP!
	private int position;
	private Random rng = new Random();
	private boolean active = true;
	private boolean defended;

	/**
	 * 
	 * @param order
	 *            location around the table
	 * @param numPlayers
	 *            total number of players at the table
	 */
	public Player(int order, int numPlayers, Deck currentDeck) {
		position = order;
		gameDeck = currentDeck;
	}

	/**
	 * plays this Player's card
	 * 
	 * @return the Card that was in this player's hand
	 */
	public Card discardHand() {
		if(cardsInHand.size() != 1) {
			System.out.println("Error discardHand");
			System.exit(-1);
		}
		Card discard = cardsInHand.remove(0);
		addToDiscard(discard);
		return discard;
	}

	/**
	 * 
	 * @param currentCard
	 *            the Card to be added to the discard pile for the current
	 *            Player.
	 */
	public void addToDiscard(Card currentCard) {
		cardsInHand.remove(currentCard);
		gameDeck.discard(currentCard);
	}

	/**
	 * 
	 * @param target
	 *            a Player about whom this Player has learned something (for use
	 *            with Priest)
	 * @param type
	 *            the Card type that player has in hand
	 */
	public void addKnownCard(Player target, CardType type) {
		if (target == this)
			return;
		knownCards.put(target, type);
	}

	/**
	 * For use with Priest and other deductive methods
	 * 
	 * @param target
	 *            the pPlayer whose hand it is that this method checks against
	 * @param type
	 *            the CardType that <code>target</code> is suspected to have
	 * @return whether this Player knows that Player has that CardType
	 */
	public boolean iKNowWhatCardYouHave(Player target, CardType type) {
		if (!knownCards.isEmpty()) {
			if (knownCards.get(target).compareTo(type) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Used when a Player can no longer reasonably deduce what another Player
	 * has in his/her hand
	 * 
	 * @param target
	 *            the player whom this Player no longer knows about
	 */
	public void removeKnownCard(Player target) {
		knownCards.remove(target);
	}

	/**
	 * Handles Player AI
	 * 
	 * @param allPlayers
	 *            the Players in this game
	 * @return the Card that the Player decides to discard
	 */
	public Card play(Vector<Player> allPlayers) {

		//Take the list of players and remove ones that are no longer in play
		allPlayers.remove(this);

		Vector<Player> toDelete = new Vector<Player>(); // Yo JP should this be
														// done at then end of
														// this method instead?
		for (Player currentPlayer : allPlayers) {
			if (!currentPlayer.isActive() || currentPlayer.isDefended())
				toDelete.add(currentPlayer);
		}
		for (Player deletePlayer : toDelete) {
			allPlayers.remove(deletePlayer);
		}

		//Start your turn
		Card drawnCard = gameDeck.drawTopCard();
		Card inHand = this.getCardInHand();
		cardsInHand.add(drawnCard);
		System.out.println("\tDrawn Card:" + drawnCard);
		
		Card playedCard;
		
		CardType guess;
		Player target = null;

		// Basic Logic, Don't Play the princess otherwise you lose
		if (drawnCard.getType() == CardType.PRINCESS) {
			playedCard = inHand;			
			guess = CardType.COUNTESS;
			if (allPlayers.size() > 0)
				target = allPlayers.firstElement();
		} else if (inHand.getType() == CardType.PRINCESS) {
			playedCard = drawnCard;
			guess = CardType.COUNTESS;
			if (allPlayers.size() > 0)
				target = allPlayers.firstElement();
		} else {
			int randInt;
			randInt = rng.nextInt(2);
			if (randInt == 0) {
				playedCard = inHand;				
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
		this.updatePlayerKnowledgePerDiscard(allPlayers, playedCard);

		if (target == null)
			target = this;

		try {
			switch (playedCard.getType()) {
			case GUARD:
				playedCard.playGuard(this, target, guess);
				break;
			case PRIEST:
				playedCard.playPriest(this, target);
				break;
			case BARON:
				playedCard.playBaron(this, target);
				break;
			case HANDMAID:
				playedCard.playHandmaid(this);
				break;
			case PRINCE:
				playedCard.playPrince(gameDeck, this, target);
				break;
			case KING:
				playedCard.playKing(this, target);
				break;
			case COUNTESS:
				playedCard.playCountess(this);
				break;
			case PRINCESS:
				playedCard.playPrincess(this);
				break;
			}
		} catch (Exception e) {
			System.out.print(e);
			System.exit(1);
		}
		// playedCard.play(currentDeck, this, target, guess);
		return playedCard;
	}

	/**
	 * Sets this Player to inactive
	 */
	public void lose() {
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
		if(cardsInHand.size() != 1) {
			System.out.println("Error getCardInHand");
			System.exit(-1);
		}
		
		return cardsInHand.elementAt(0);
	}

	/**
	 * Used by effects that require trading of cards etc. (e.g. King)
	 * 
	 * @param activeCard
	 *            the card to put into this Player's hand
	 */
	public void setHand(Card activeCard) {
		cardsInHand.removeAllElements();
		cardsInHand.add(activeCard);		
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
	public void setDefended(boolean isDefended) {
		this.defended = isDefended;
	}

	/**
	 * Format:
	 * 
	 * <pre>
	 * Player: *position at table* Card In Hand: *card in this Player's hand*
	 * </pre>
	 */
	public String toString() {
		return "Player: " + position + "\n\tCard In Hand: " + cardsInHand.elementAt(0);
	}

	/**
	 * When a player plays a card, other players update their knownCards.
	 * 
	 * @param allPlayers
	 *            the Players still in the round
	 * @param playedCard
	 *            the Card that this Player played
	 */
	private void updatePlayerKnowledgePerDiscard(Vector<Player> allPlayers,
			Card playedCard) {
		for (Player opponent : allPlayers) {
			if (opponent.getPosition() != this.getPosition()) { // Make sure
																// opponent
																// isn't the
																// current
																// player
				if (opponent.iKNowWhatCardYouHave(this, playedCard.getType())) {
					opponent.removeKnownCard(this);
				}
			}
		}
	}

}