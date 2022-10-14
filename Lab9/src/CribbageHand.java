import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CribbageHand {
	public static final Map<Rank, Integer> CARD_VALUES = Map.ofEntries(
			Map.entry(Rank.TWO , 2),
			Map.entry(Rank.THREE , 3),
			Map.entry(Rank.FOUR , 4),
			Map.entry(Rank.FIVE , 5),
			Map.entry(Rank.SIX , 6),
			Map.entry(Rank.SEVEN , 7),
			Map.entry(Rank.EIGHT , 8),
			Map.entry(Rank.NINE , 9),
			Map.entry(Rank.TEN , 10),
			Map.entry(Rank.JACK , 10),
			Map.entry(Rank.QUEEN , 10),
			Map.entry(Rank.KING , 10),
			Map.entry(Rank.ACE , 1));		
	public final List<Card> cards;


	public CribbageHand(Card c1, Card c2, Card c3, Card c4) {
		this.cards = Collections.unmodifiableList(new ArrayList<Card>(List.of(c1, c2, c3, c4)));
		if(cards.contains(null)) {
			throw new NullPointerException();
		}
	}


	//I got help from Jessica Eaburg.
	public Set<Set<Card>> fifteens(Card starter) {
		Set<Set<Card>> subSets = powerSet(cards);
		Set<Set<Card>> fifteen = new HashSet<Set<Card>>();
		//pull a set from the subSets.
		for(Set<Card> set : subSets) {
			//add every card values from the set.
			int value = 0;
			for (Card c : set) {
				value += CARD_VALUES.get(c.getRank());
			}
			//if value has 15 points, add it to the fifteen set.
			if (value == 15) {
				fifteen.add(set);
			}
			//if not 15 points, add the starter's card value to help reach 15 points.
			if (value + CARD_VALUES.get(starter.getRank()) == 15) {
				set.add(starter);
				fifteen.add(set);
			}	
		}
		return fifteen;
	}


	//I got help from Google 
	public static Set<Set<Card>> powerSet(List<Card> cards) {
		Set<Set<Card>> sets = new HashSet<Set<Card>>();

		//base condition--returns a set that contains every subset of cards
		//in the given list, including the empty set.
		if(cards.isEmpty()) {
			sets.add(new HashSet<Card>());
			return sets;
		}

		//take the 1st element from set and start recursion.
		List<Card> list = new ArrayList<Card>(cards);
		Card head = list.get(0);
		//create a list that shows the rest of the list.
		List<Card> rest = new ArrayList<Card>(list.subList(1, list.size()));
		//recursion begins
		for (Set<Card> set : powerSet(rest)) {
			//create temp set
			Set<Card> newSet = new HashSet<Card>();

			//make temp set and add head and the remaining.
			newSet.add(head);
			newSet.addAll(set);

			//sets = temp + set
			//add the temp set on top of the set I already have.
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}
}



