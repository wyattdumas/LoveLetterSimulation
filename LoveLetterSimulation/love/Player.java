package love;
import java.util.*;

public class Player {

	private Vector<Card> hand = new Vector<Card>();
	private Vector<Card> discardPile = new Vector<Card>(); 
	private int position = 0;
	
	public Player (){
		
	}
	
	public void draw(Deck drawPile){
		//hand.add(drawPile.drawTopCard());		
	}
	
	public void play(){		
		System.out.println(hand.remove(0));
	}	
	
}