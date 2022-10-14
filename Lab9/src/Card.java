import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import javax.print.attribute.standard.MediaSize.Other;

//@author Rebekah Lee
public class Card implements Comparable<Card> {
	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit) {
		if(rank==null || suit == null) {
			throw new NullPointerException();
		}
		this.rank = rank;
		this.suit = suit;
	}
	
	
	@Override
	public int compareTo(Card otherCard) {
		if(this.suit.compareTo(otherCard.suit) < 0) {
			return -1;
		}
		else if(this.suit.compareTo(otherCard.suit) > 0) {
			return 1;
		}
		else {
			if(this.rank.compareTo(otherCard.rank) < 0) {
				return -1;
			}
			else if(this.rank.compareTo(otherCard.rank) > 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		//checks references
		if(this == obj) { 
			return true;
		}
		//checks null
		if (obj==null) {
			return false;
		}
		//check if the given object is an instance of Card.
		if(!(obj instanceof Card)) {
			return false;	
		}
		//Downcast the Object reference to Card.
		Card cards = (Card) obj;
		//Compare the Ranks and Suits of the Cards. 
		return rank==cards.rank && suit == cards.suit;
	}
	
	
	
	public Rank getRank() {
		return rank;
	}
	
	
	public Suit getSuit() {
		return suit;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(rank,suit);
	}
	
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(rank);
		string.append(suit);
		return string.toString();
		
	}
	
	
	
	
	
}
