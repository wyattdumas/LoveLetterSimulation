package love;
/**
 * Card maintains all data and functionality relevant to an individual card in the game of Love Letter.
 * 
 * @author jpolonsky
 *
 */
public class Card {
	
	private int number;
	private CardType type;	
	/**
	 * 
	 * @param num The number of this kind of card in a deck
	 * @param type The kinf of card, e.g. Baron, or Princess
	 */
	public Card(int num, CardType type) {
		number = num;
		this.type = type;
	}
	/**
	 * 
	 * @return the point value of the card for determining the winner or result of other card effects
	 */
	public int getValue() {
		return type.getValue();
	}	
	/**
	 * 
	 * @return the name of the card, for use in determining player strategies
	 */
	public String getTitle() {
		return type.getTitle();
	}		
	/**
	 * 
	 * @return an object encapsulating the information about this paticular type of card
	 */
	public CardType getType() {
		return type;
	}
	/**
	 * Format:
	 * <pre>
	 * Card Value: *point value* Number: *number in deck* Text: *name of card*
	 * </pre>
	 */
	public String toString() {
		return "Card Value: " + type.getValue() + " Number:" + number + " Text:" + getTitle();
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @param target if the card type requires a target, this is that player
	 * @param guess for use with Guards
	 * @return this card
	 * @throws Exception 
	 */
	public Card playGuard(Player me, Player target, CardType guess) throws Exception {
				
		if(this.type != CardType.GUARD) 
			throw new Exception("Wrong Type");
		
		Card targetHand;
		
		me.addToDiscard(this);	
		
		targetHand = target.getCardInHand();
		
		if(targetHand.getType() == guess && guess != CardType.GUARD) {
			System.out.println("Guard Guessed Correctly against:" + target);
			target.discardHand();
			target.Lose();					
		}
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @param target if the card type requires a target, this is that player
	 * @return this card
	 * @throws Exception 
	 */
	public Card playPriest(Player me, Player target) throws Exception {
				
		if(this.type != CardType.PRIEST) 
			throw new Exception("Wrong Type");
		
		Card targetHand;
		
		me.addToDiscard(this);	
		
		targetHand = target.getCardInHand();
		
		me.addKnownCard(target, targetHand.getType());
		System.out.println("Priest against:" + target);
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @param target if the card type requires a target, this is that player
	 * @return this card
	 * @throws Exception 
	 */
	public Card playBaron(Player me, Player target) throws Exception {
				
		if(this.type != CardType.BARON) 
			throw new Exception("Wrong Type");
		
		Card myHand;
		Card targetHand;
		
		me.addToDiscard(this);
		
		myHand = me.getCardInHand();		
		targetHand = target.getCardInHand();
		
		if(targetHand.getValue() > myHand.getValue()) {
			System.out.println("Baron Lost against:" + target);
			me.discardHand();
			me.Lose();					
		} else if(myHand.getValue() > targetHand.getValue()) {
			System.out.println("Baron Won against:" + target);
			target.discardHand();
			target.Lose();					
		}
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @return this card
	 * @throws Exception 
	 */
	public Card playHandmaid(Player me) throws Exception {
				
		if(this.type != CardType.HANDMAID) 
			throw new Exception("Wrong Type");
							
		me.addToDiscard(this);		
		
		me.setDefended();
		System.out.println("Handmaid on:" + me);
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param currentDeck allows cards to interact with the deck and the discard pile
	 * @param me the player whose turn it is
	 * @param target if the card type requires a target, this is that player
	 * @param guess for use with Guards
	 * @return this card
	 * @throws Exception 
	 */
	public Card playPrince(Deck currentDeck, Player me, Player target) throws Exception {				
		
		if(this.type != CardType.PRINCE) 
			throw new Exception("Wrong Type");
		
		me.addToDiscard(this);				
					
		System.out.println("Prince on:" + target);
		Card discard = target.discardHand();
		if(discard.getType() == CardType.PRINCESS)
			target.Lose();
		else					
			target.draw(currentDeck);						
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @param target if the card type requires a target, this is that player
	 * @return this card
	 * @throws Exception 
	 */
	public Card playKing(Player me, Player target) throws Exception {
				
		if(this.type != CardType.KING) 
			throw new Exception("Wrong Type");
		
		Card myHand;
		Card targetHand;
		
		me.addToDiscard(this);
		
		myHand = me.getCardInHand();		
		targetHand = target.getCardInHand();
		
		System.out.println("King on:" + target);
		target.setHand(myHand);				
		me.setHand(targetHand);	
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @return this card
	 * @throws Exception 
	 */
	public Card playCountess(Player me) throws Exception {
				
		if(this.type != CardType.COUNTESS) 
			throw new Exception("Wrong Type");
		
		me.addToDiscard(this);
		
		System.out.println("Countess does nothing");
		
		return this;
	}
	
	/**
	 * Handles the effects of the various cards
	 * @param me the player whose turn it is
	 * @return this card
	 * @throws Exception 
	 */
	public Card playPrincess(Player me) throws Exception {
				
		if(this.type != CardType.PRINCESS) 
			throw new Exception("Wrong Type");
		
		me.addToDiscard(this);
		
		me.Lose();
		System.out.println("Princess on myself");
		
		return this;
	}
	
}
