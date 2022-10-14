import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();

		for(Rank rank: Rank.values()) {
			for(Suit suit : Suit.values()) {
				//sort the cards then add to the list.
				Collections.sort(cards);
				cards.add(new Card(rank, suit));
			}
		}
	}

	
	public Card draw() {
		//return null if empty.
		if (cards.isEmpty()) {
			return null;
		}
		else {
			//remove first card in the list.
			return cards.remove(0);
		}
	}

	
	//Remove and return the given number of Cards from the front of the List. 
	//If the number is larger than the size, remove and return all the Cards.
	//If the number is less than zero, return an empty List.
	public List<Card> draw(int count) {
		ArrayList<Card> removed = new ArrayList<Card>();

		for(int i=0; i<count && cards.size()>0; ++i) {
			if(count < 0) {
				return Collections.emptyList();
			}	
			else {
				removed.add(cards.remove(0));
			}	
		}
		return removed;
	}

	
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public int size() {
		return cards.size();

	}

	public String toString() {
		return cards.toString();

	}
}
