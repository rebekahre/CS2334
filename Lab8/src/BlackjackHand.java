import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackHand {
	private static final Map <Rank, Integer> CARD_VALUES = helper();
	private static final int MAX_VALUE = 21;
	private List<Card> cards;
	
	//I got this idea from Jessica Eaburg.
	private static Map<Rank, Integer> helper() {
		HashMap<Rank, Integer> CARD_VALUES = new HashMap<Rank, Integer>();
		CARD_VALUES.put(Rank.TWO, 2);
		CARD_VALUES.put(Rank.THREE, 3);
		CARD_VALUES.put(Rank.FOUR, 4);
		CARD_VALUES.put(Rank.FIVE, 5);
		CARD_VALUES.put(Rank.SIX, 6);
		CARD_VALUES.put(Rank.SEVEN, 7);
		CARD_VALUES.put(Rank.EIGHT, 8);
		CARD_VALUES.put(Rank.NINE, 9);
		CARD_VALUES.put(Rank.TEN, 10);
		CARD_VALUES.put(Rank.JACK, 10);
		CARD_VALUES.put(Rank.QUEEN, 10);
		CARD_VALUES.put(Rank.KING, 10);
		CARD_VALUES.put(Rank.ACE, 11);
		return CARD_VALUES;
	}

	
	public BlackjackHand(Card c1, Card c2) {
	
	this.cards = new ArrayList<Card>(2);
	this.cards.add(c1);
	this.cards.add(c2);

		}
		
	
	public void addCard(Card card) {
		if(getValue() < MAX_VALUE) {
			cards.add(card);
		}
	}
	
	public static Map <Rank, Integer> getCardValues() {
		return helper();
	}
	
	//return a copy of cards list.
	public List<Card> getCards() {
		return new ArrayList<Card>(cards);
		
	}
	
	//I got help from a tutor in this method.
	public int getValue() {
		int total = 0;
		for(int i=0; i<cards.size(); ++i) {
			//get index of that rank.
			Rank cardRank = cards.get(i).getRank();
			//add integer value of CARD_VALUES to total.
			total += CARD_VALUES.get(cardRank);
		}
		//As long as total is greater than Max value, decrement 10 (ace) from total.
		for(int i=0; i<cards.size()&& total > MAX_VALUE; ++i) {
			// if cards[i] has rank of Aces, then subtract 10.
			if(cards.get(i).getRank() == Rank.ACE)
				total -= 10;
		}
		return total;
		
	}
	
	public int size() {
		return cards.size();
		
	}
	
	public String toString() {
		return cards.toString();
		
	}
}
