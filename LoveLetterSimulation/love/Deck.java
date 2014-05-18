package love;
import java.util.Vector;

public class Deck {

	private int activeCards;
	private int discardCards;
	private Vector<Card> available;
	private Vector<Card> discard;
	
	public Deck(int numPlayers)
	{
		if(numPlayers == 2) {
			activeCards = 12;
			discardCards = 4;
		} else if(numPlayers > 2) {
			activeCards = 15;
			discardCards = 1;
		}
		
		//available = new Vector()
		//discard = new Card[discardCards];
		
	}
	
	private void Shuffle()
	{
		
	}
	
	public Card DrawTopCard()
	{
		Card topCard = available.remove(0);
		return topCard;
	}
	
	public boolean hasCards()
	{
		return !available.isEmpty();
	}
}
