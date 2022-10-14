import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//I got help from a tutor and Jessica Eaburg in this class.

public class GoFishHand {
	private static final int BOOK_SIZE = 4;
	private Set<Rank> books = new HashSet<Rank>();
	//EnumSet.noneOf(Rank.class);
	private Map<Rank, Set<Card>> cards = new HashMap<Rank, Set<Card>>();
	//new EnumMap<>(Rank.class);


	public GoFishHand(Collection<Card> cards) {
		if(cards.isEmpty()) {
			throw new IllegalArgumentException();
		}
		addCards(cards);
	}


	// Add the given Card to the Map. 
	//If a book is formed, remove the mapping 
	//and add the Rank to the books Set.
	public void addCard (Card card) {
		Rank rank = card.getRank();
		Set<Card> hand = cards.get(rank); 

		if(hand==null) {
			//make a new hand, then store that rank into cards.
			hand = new HashSet<>();
			cards.put(rank, hand);	
		}
		//add that card into the hand Set.
		hand.add(card);

		//if a book is formed, remove the mapping
		//and add the Rank to the books Set.
		if (hand.size() == BOOK_SIZE) {
			cards.remove(rank);
			books.add(rank);
		}
	}

	

	//Add each Card in the given Collection to the Map. 
	//If any books are formed, update the Map and Set as described above.
	public void addCards(Collection<Card> cards) {
		for(Card c: cards) {
			addCard(c);		
		}
	}


	public Set<Rank> getBooks() {
		return new HashSet<Rank>(books);
	}


	public Map<Rank, Set<Card>> getCards() {
		Map<Rank, Set<Card>> copyMap = new HashMap<Rank, Set<Card>>();
		Set<Card> copySet = new HashSet<Card>();
		for(Rank rank: cards.keySet()) {
			//make deep copy of each different sets and ranks stored in the map.
			copyMap.put(rank, new HashSet<Card>(cards.get(rank)));
		}
		return copyMap;
	}
	

	//Create and return a Map with the number of Cards in each partial 
	//and complete book. Each key is a Rank, and the associated value 
	//is the number of Cards with that Rank.
	public Map<Rank, Integer> getRankCounts() {
		//numCards will only store ranks.
		Map<Rank, Integer> numCards = new HashMap<Rank, Integer>();
		//new EnumMap<>(Rank.class);

		//loop over every possible rank.
		for(Rank rank: Rank.values()) {
			//if we have 4 cards of same rank, automatically add 4 if the book contains rank.
			if(books.contains(rank)) {
				//if rank 4 exists, then add 4 to numCards map.
				numCards.put(rank, numCards.getOrDefault(rank, 0)+4);
			}

			if(cards.containsKey(rank)) {
				for(Card suit: cards.get(rank)) {
					//if it already exists, increment the value by 1.
					numCards.put(rank, numCards.getOrDefault(rank, 0)+1);
				}
			}
		}
		return numCards;	
	}


	//Remove the mapping for the given Rank and return the associated Set of Cards. 
	//If the mapping does not exist, return an empty Set.
	public Set<Card> removeCards(Rank rank) {	
		Set<Card> set = cards.remove(rank);
		if(set==null) {
			//return empty Set
			return new HashSet<Card>();
		}
		return set;
	}


	//Return the total number of Cards in the Map.
	public int size() {
		int total=0;
		for(Rank rank: cards.keySet()) {
			//get rank and size for cards map then add it to total.
			Set<Card> set = cards.get(rank);
			total += set.size();
		}
		return total;
	}


	//Return a two-line string representation of the hand. 
	//The first line consists of the string representation of the Map 
	//with the prefix "CARDS: ". The second line consists of the string 
	//representation of the Set with the prefix "BOOKS: ".
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CARDS: " + cards);
		sb.append(System.lineSeparator());
		sb.append("BOOKS: " + books);

		return sb.toString();
	}

}
