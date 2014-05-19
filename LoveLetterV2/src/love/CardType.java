package love;

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
	private int value;
	private String title;	
	
	private CardType(int occurrence, int value, String title) {
		this.value = value;
		this.occurences = occurrence;
		this.title = title;
	}
	
    public int getOccurences() {
		return occurences;
	}

	public int getValue() {
		return value;
	}

	public String getTitle() {
		return title;
	}
}
