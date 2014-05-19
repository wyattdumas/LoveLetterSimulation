package love;

public class Card {
	
	private int number;
	private CardType type;	
	
	public Card(int num, CardType type) {
		number = num;
		this.type = type;
	}
	
	public int getValue() {
		return type.getValue();
	}	
		
	public String getTitle() {
		return type.getTitle();
	}		
	
	public CardType getType() {
		return type;
	}
	
	public String toString() {
		return "Card Value: " + type.getValue() + " Number:" + number + " Text:" + getTitle();
	}
	
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
