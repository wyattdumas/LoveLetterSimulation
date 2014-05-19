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
	
	public Card Play(Deck currentDeck, Player me, Player target, CardType guess) throws Exception {
		
		Card myHand = me.getHand();
		Card targetHand = target.getHand();		
		switch(type) {
			case GUARD:
				if(targetHand.getType() == guess) {
					target.discardHand();
					target.Lose();
				}
				break;
			case PRIEST:
				break;
			case BARON:
				if(targetHand.getValue() > myHand.getValue()) {
					me.discardHand();
					me.Lose();
				} else if(myHand.getValue() > targetHand.getValue()) {
					target.discardHand();
					target.Lose();
				}
				break;
			case HANDMAID:
				me.setDefended();
				break;
			case PRINCE:
				target.discardHand();
				target.draw(currentDeck);
				break;
			case KING:				
				target.setHand(myHand);				
				me.setHand(targetHand);				
				break;
			case COUNTESS:
				break;			
			case PRINCESS:
				me.Lose();
				break;			
		}
		return this;
	}
	
}
