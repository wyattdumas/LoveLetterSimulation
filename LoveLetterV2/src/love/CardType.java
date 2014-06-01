package love;
/**
 * enum that treats the storage of data about the different kinds of Cards e.g. Baron, Princess et al
 * @author jpolonsky
 *
 */
public enum CardType {
    GUARD (5,1,"Guard"),
    PRIEST (2,2,"Priest"),
    BARON(2,3,"Baron"),
    HANDMAID(2,4,"HandMaid"),
    PRINCE(2,5,"Prince"),
    KING(1,6,"King"),
    COUNTESS(1,7,"Countess"),
    PRINCESS(1,8,"Princess");
    
    private int occurences;
    private int remaining;
	private int value;
	private String title;	
	/**
	 * 
	 * @param occurrence frequency of this CardType in a standard Deck
	 * @param value point value of this CardType for scoring purposes and use with effects like Baron
	 * @param title text representation of the name of the card
	 */
	private CardType(int occurrence, int value, String title) {
		this.value = value;
		this.occurences = occurrence;
		this.remaining = occurrence;
		this.title = title;
	}
	
	public int getRemaining() {
		return remaining;
	}
	
	public void decreaseRemaining() {
		if(this.remaining > 0) 
			--this.remaining;		
	}
	
	/**
	 * 
	 * @return the frequency with which this Card appears in a standard Deck
	 */
    public int getOccurences() {
		return occurences;
	}
    /**
     * 
     * @return the point value of this Card
     */
	public int getValue() {
		return value;
	}
	/**
	 * 
	 * @return the text representation of this Card's name
	 */
	public String getTitle() {
		return title;
	}
}
