//@author Rebekah Lee
public enum Suit {
	CLUBS("C"),
	DIAMONDS("D"),
	HEARTS("H"),
	SPADES("S");
	private String symbol;
	
	private Suit(String symbol) {
		this.symbol=symbol;
	}
	
	public String toString() {
		return symbol;	
	}
	

}
