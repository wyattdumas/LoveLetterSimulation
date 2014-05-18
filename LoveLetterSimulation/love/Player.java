package love;
import java.util.*;

public class Player {

	private Vector<Card> hand = new Vector<Card>();
	private Vector<Card> discardPile = new Vector<Card>(); 
	private int position = 0;
	private Random rng = new Random();
	
	public Player (int order){
		position = order;
	}
	
	public void draw(Deck drawPile){
		hand.add(drawPile.drawTopCard());		
	}
	
	public Card play(){
		Card temp = hand.remove(rng.nextInt(2));
		discardPile.add(temp);
		return temp;
	}	
	
	public String toString(){
		return "Player: " + position + "\n\tHand: " + hand;
	}
	
}