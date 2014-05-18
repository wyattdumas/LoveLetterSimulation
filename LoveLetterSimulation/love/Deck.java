package love;
import java.util.Collections;
import java.util.Vector;

public class Deck {

	private	int totalCards = 16;
	private int discardCards;
	private Vector<Card> available = new Vector<Card>();
	private Vector<Card> discard = new Vector<Card>();
	
	public Deck(int numPlayers)
	{
		for(int i = 0; i < totalCards; i++) {
			available.add(new Card(i));
		}
		
		shuffle();
		
		if(numPlayers == 2) {
			discardCards = 4;
		} else if(numPlayers > 2) {
			discardCards = 1;
		}
		
		for(int i = 0; i < discardCards; i++) {
			discard.add(drawTopCard());
		}
		
	}
	
	private void shuffle()
	{
		Collections.shuffle(available);
	}
	
	public Card drawTopCard()
	{
		Card topCard = available.remove(0);
		return topCard;
	}
	
	public boolean hasCards()
	{
		return !available.isEmpty();
	}
	
	public String ToString() {
		String output;
		
		output = "Available Pile \n";
		for(int i = 0; i < available.size(); i++) {
			output += available.elementAt(i);
		}
		output = "Discard Pile \n";
		for(int i = 0; i < discard.size(); i++) {
			output += discard.elementAt(i);
		}
		return output;
	}
}
