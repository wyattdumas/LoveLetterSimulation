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
	 * @param currentDeck allows cards to interact with the deck and the discard pile
	 * @param me the player whose turn it is
	 * @param target if the card type requires a target, this is that player
	 * @param guess for use with Guards
	 * @return this card
	 */
	public Card Play(Deck currentDeck, Player me, Player target, CardType guess) {
		
		Card myHand;
		Card targetHand;
		
		me.addToDiscard(this);
				
		myHand = me.getCardInHand();
		
		if(target == null)
			target = me;
		
		targetHand = target.getCardInHand();
		
		switch(type) {
			case GUARD:
				if(targetHand.getType() == guess && guess != CardType.GUARD) {
					System.out.println("Guard Guessed Correctly against:" + target);
					target.discardHand();
					target.Lose();					
				}
				break;
			case PRIEST:
				me.addKnownCard(target, targetHand.getType());
				System.out.println("Priest against:" + target);
				break;
			case BARON:
				if(targetHand.getValue() > myHand.getValue()) {
					System.out.println("Baron Lost against:" + target);
					me.discardHand();
					me.Lose();					
				} else if(myHand.getValue() > targetHand.getValue()) {
					System.out.println("Baron Won against:" + target);
					target.discardHand();
					target.Lose();					
				}
				break;
			case HANDMAID:
				me.setDefended();
				System.out.println("Handmaid on:" + target);
				break;
			case PRINCE:
				System.out.println("Prince on:" + target);
				Card discard = target.discardHand();
				if(discard.getType() == CardType.PRINCESS)
					target.Lose();
				else					
					target.draw(currentDeck);				
				break;
			case KING:
				System.out.println("King on:" + target);
				target.setHand(myHand);				
				me.setHand(targetHand);				
				break;
			case COUNTESS:
				System.out.println("Countess does nothing");
				break;			
			case PRINCESS:				
				me.Lose();
				System.out.println("Princess on myself");
				break;			
		}
		
		return this;
	}
	
}
