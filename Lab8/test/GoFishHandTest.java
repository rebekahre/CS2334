import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class GoFishHandTest {

	@Test
	void testConstructor() {
		try {
			new GoFishHand(List.of());
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new GoFishHand(Set.of());
			fail();
		} catch (IllegalArgumentException e) {}

		GoFishHand hand = new GoFishHand(List.of(
				new Card(Rank.FIVE, Suit.DIAMONDS)));
		Map<Rank, Set<Card>> cards = hand.getCards();
		Set<Rank> books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(new Card(Rank.FIVE, Suit.DIAMONDS)),
				cards.get(Rank.FIVE));
		assertEquals(Set.of(), books);

		hand = new GoFishHand(Set.of(
				new Card(Rank.FIVE, Suit.DIAMONDS),
				new Card(Rank.JACK, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.HEARTS)));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(2, cards.size());
		assertEquals(Set.of(
				new Card(Rank.FIVE, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.DIAMONDS)),
				cards.get(Rank.FIVE));
		assertEquals(Set.of(new Card(Rank.JACK, Suit.HEARTS)),
				cards.get(Rank.JACK));
		assertEquals(Set.of(), books);

		hand = new GoFishHand(List.of(
				new Card(Rank.FIVE, Suit.DIAMONDS),
				new Card(Rank.JACK, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.SPADES),
				new Card(Rank.JACK, Suit.CLUBS),
				new Card(Rank.FIVE, Suit.CLUBS),
				new Card(Rank.ACE, Suit.DIAMONDS)));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(2, cards.size());
		assertEquals(Set.of(
				new Card(Rank.JACK, Suit.HEARTS),
				new Card(Rank.JACK, Suit.CLUBS)),
				cards.get(Rank.JACK));
		assertEquals(Set.of(new Card(Rank.ACE, Suit.DIAMONDS)),
				cards.get(Rank.ACE));
		assertEquals(Set.of(Rank.FIVE), books);
	}

	@Test
	void testEncapsulation() {
		GoFishHand hand = new GoFishHand(Set.of(
				new Card(Rank.ACE, Suit.CLUBS),
				new Card(Rank.ACE, Suit.DIAMONDS),
				new Card(Rank.ACE, Suit.HEARTS),
				new Card(Rank.ACE, Suit.SPADES),
				new Card(Rank.TWO, Suit.CLUBS)));
		Map<Rank, Set<Card>> cards = hand.getCards();
		Set<Rank> books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(new Card(Rank.TWO, Suit.CLUBS)),
				cards.get(Rank.TWO));
		assertEquals(Set.of(Rank.ACE), books);

		cards.get(Rank.TWO).clear();
		cards.clear();
		books.clear();
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(new Card(Rank.TWO, Suit.CLUBS)),
				cards.get(Rank.TWO));
		assertEquals(Set.of(Rank.ACE), books);
	}

	@Test
	void testAddCards() {
		GoFishHand hand = new GoFishHand(Set.of(
				new Card(Rank.FOUR, Suit.HEARTS)));
		Map<Rank, Set<Card>> cards = hand.getCards();
		Set<Rank> books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(new Card(Rank.FOUR, Suit.HEARTS)),
				cards.get(Rank.FOUR));
		assertEquals(Set.of(), books);

		hand.addCard(new Card(Rank.SEVEN, Suit.HEARTS));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(2, cards.size());
		assertEquals(Set.of(new Card(Rank.FOUR, Suit.HEARTS)),
				cards.get(Rank.FOUR));
		assertEquals(Set.of(new Card(Rank.SEVEN, Suit.HEARTS)),
				cards.get(Rank.SEVEN));
		assertEquals(Set.of(), books);

		hand.addCards(List.of(
				new Card(Rank.SEVEN, Suit.DIAMONDS),
				new Card(Rank.FIVE, Suit.HEARTS)));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(3, cards.size());
		assertEquals(Set.of(new Card(Rank.FOUR, Suit.HEARTS)),
				cards.get(Rank.FOUR));
		assertEquals(Set.of(new Card(Rank.FIVE, Suit.HEARTS)),
				cards.get(Rank.FIVE));
		assertEquals(Set.of(
				new Card(Rank.SEVEN, Suit.DIAMONDS),
				new Card(Rank.SEVEN, Suit.HEARTS)),
				cards.get(Rank.SEVEN));
		assertEquals(Set.of(), books);

		hand.addCards(Set.of(
				new Card(Rank.FOUR, Suit.CLUBS),
				new Card(Rank.FIVE, Suit.CLUBS),
				new Card(Rank.FOUR, Suit.DIAMONDS),
				new Card(Rank.FIVE, Suit.DIAMONDS),
				new Card(Rank.FOUR, Suit.SPADES),
				new Card(Rank.FIVE, Suit.SPADES)));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(
				new Card(Rank.SEVEN, Suit.DIAMONDS),
				new Card(Rank.SEVEN, Suit.HEARTS)),
				cards.get(Rank.SEVEN));
		assertEquals(Set.of(Rank.FOUR, Rank.FIVE), books);

		hand.addCard(new Card(Rank.SEVEN, Suit.SPADES));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(
				new Card(Rank.SEVEN, Suit.DIAMONDS),
				new Card(Rank.SEVEN, Suit.HEARTS),
				new Card(Rank.SEVEN, Suit.SPADES)),
				cards.get(Rank.SEVEN));
		assertEquals(Set.of(Rank.FOUR, Rank.FIVE), books);

		hand.addCard(new Card(Rank.SEVEN, Suit.CLUBS));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(Map.of(), cards);
		assertEquals(Set.of(Rank.FOUR, Rank.FIVE, Rank.SEVEN), books);
	}

	@Test
	void testRemoveCards() {
		GoFishHand hand = new GoFishHand(List.of(
				new Card(Rank.THREE, Suit.CLUBS),
				new Card(Rank.THREE, Suit.DIAMONDS),
				new Card(Rank.THREE, Suit.HEARTS),
				new Card(Rank.THREE, Suit.SPADES),
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.SIX, Suit.HEARTS),
				new Card(Rank.EIGHT, Suit.CLUBS),
				new Card(Rank.EIGHT, Suit.DIAMONDS),
				new Card(Rank.NINE, Suit.CLUBS)));
		Map<Rank, Set<Card>> cards = hand.getCards();
		Set<Rank> books = hand.getBooks();
		assertEquals(3, cards.size());
		assertEquals(Set.of(
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.SIX, Suit.HEARTS)),
				cards.get(Rank.SIX));
		assertEquals(Set.of(
				new Card(Rank.EIGHT, Suit.CLUBS),
				new Card(Rank.EIGHT, Suit.DIAMONDS)),
				cards.get(Rank.EIGHT));
		assertEquals(Set.of(
				new Card(Rank.NINE, Suit.CLUBS)),
				cards.get(Rank.NINE));
		assertEquals(Set.of(Rank.THREE), books);

		assertEquals(Set.of(), hand.removeCards(Rank.FIVE));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(3, cards.size());
		assertEquals(Set.of(
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.SIX, Suit.HEARTS)),
				cards.get(Rank.SIX));
		assertEquals(Set.of(
				new Card(Rank.EIGHT, Suit.CLUBS),
				new Card(Rank.EIGHT, Suit.DIAMONDS)),
				cards.get(Rank.EIGHT));
		assertEquals(Set.of(
				new Card(Rank.NINE, Suit.CLUBS)),
				cards.get(Rank.NINE));
		assertEquals(Set.of(Rank.THREE), books);

		assertEquals(Set.of(
				new Card(Rank.EIGHT, Suit.CLUBS),
				new Card(Rank.EIGHT, Suit.DIAMONDS)),
				hand.removeCards(Rank.EIGHT));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(2, cards.size());
		assertEquals(Set.of(
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.SIX, Suit.HEARTS)),
				cards.get(Rank.SIX));
		assertEquals(Set.of(
				new Card(Rank.NINE, Suit.CLUBS)),
				cards.get(Rank.NINE));
		assertEquals(Set.of(Rank.THREE), books);

		assertEquals(Set.of(),
				hand.removeCards(Rank.THREE));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(2, cards.size());
		assertEquals(Set.of(
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.SIX, Suit.HEARTS)),
				cards.get(Rank.SIX));
		assertEquals(Set.of(
				new Card(Rank.NINE, Suit.CLUBS)),
				cards.get(Rank.NINE));
		assertEquals(Set.of(Rank.THREE), books);

		assertEquals(Set.of(
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SIX, Suit.DIAMONDS),
				new Card(Rank.SIX, Suit.HEARTS)),
				hand.removeCards(Rank.SIX));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(1, cards.size());
		assertEquals(Set.of(
				new Card(Rank.NINE, Suit.CLUBS)),
				cards.get(Rank.NINE));
		assertEquals(Set.of(Rank.THREE), books);

		assertEquals(Set.of(new Card(Rank.NINE, Suit.CLUBS)),
				hand.removeCards(Rank.NINE));
		cards = hand.getCards();
		books = hand.getBooks();
		assertEquals(Map.of(), cards);
		assertEquals(Set.of(Rank.THREE), books);
	}

	@Test
	void testGetRankCounts() {
		GoFishHand hand = new GoFishHand(Set.of(
				new Card(Rank.FOUR, Suit.CLUBS),
				new Card(Rank.TWO, Suit.DIAMONDS)));
		Map<Rank, Integer> rankCounts = hand.getRankCounts();
		assertEquals(2, rankCounts.size());
		assertEquals(1, rankCounts.get(Rank.TWO));
		assertEquals(1, rankCounts.get(Rank.FOUR));

		hand.addCards(List.of(
				new Card(Rank.TWO, Suit.CLUBS),
				new Card(Rank.FOUR, Suit.DIAMONDS),
				new Card(Rank.FOUR, Suit.HEARTS)));
		rankCounts = hand.getRankCounts();
		assertEquals(2, rankCounts.size());
		assertEquals(2, rankCounts.get(Rank.TWO));
		assertEquals(3, rankCounts.get(Rank.FOUR));

		hand.addCard(new Card(Rank.FOUR, Suit.SPADES));
		rankCounts = hand.getRankCounts();
		assertEquals(2, rankCounts.size());
		assertEquals(2, rankCounts.get(Rank.TWO));
		assertEquals(4, rankCounts.get(Rank.FOUR));

		hand.removeCards(Rank.TWO);
		hand.removeCards(Rank.FOUR);
		rankCounts = hand.getRankCounts();
		assertEquals(1, rankCounts.size());
		assertEquals(4, rankCounts.get(Rank.FOUR));
	}

	@Test
	void testSize() {
		GoFishHand hand = new GoFishHand(List.of(
				new Card(Rank.TWO, Suit.CLUBS)));
		assertEquals(1, hand.size());

		hand.addCard(new Card(Rank.TWO, Suit.SPADES));
		assertEquals(2, hand.size());

		hand.addCards(Set.of(
				new Card(Rank.QUEEN, Suit.CLUBS),
				new Card(Rank.KING, Suit.HEARTS)));
		assertEquals(4, hand.size());

		hand.addCards(Set.of(
				new Card(Rank.TWO, Suit.DIAMONDS),
				new Card(Rank.TWO, Suit.HEARTS)));
		assertEquals(2, hand.size());

		hand.addCards(List.of(
				new Card(Rank.QUEEN, Suit.SPADES),
				new Card(Rank.QUEEN, Suit.HEARTS),
				new Card(Rank.QUEEN, Suit.DIAMONDS)));
		assertEquals(1, hand.size());

		hand.addCards(List.of(
				new Card(Rank.KING, Suit.CLUBS),
				new Card(Rank.KING, Suit.DIAMONDS),
				new Card(Rank.KING, Suit.SPADES)));
		assertEquals(0, hand.size());
	}

	@Test
	void testToString() {
		// The String representations of Sets and Maps can vary between runs.
		// This test produces Sets and Map with a single element so the output
		// is always the same.

		GoFishHand hand = new GoFishHand(Set.of(
				new Card(Rank.TEN, Suit.CLUBS)));
		assertEquals(
				"CARDS: {10=[10C]}" + System.lineSeparator() +
				"BOOKS: []", hand.toString());

		hand.addCards(List.of(
				new Card(Rank.TEN, Suit.DIAMONDS),
				new Card(Rank.TEN, Suit.HEARTS),
				new Card(Rank.TEN, Suit.SPADES)));
		assertEquals(
				"CARDS: {}" + System.lineSeparator() +
				"BOOKS: [10]", hand.toString());

		hand.addCard(new Card(Rank.FOUR, Suit.DIAMONDS));
		assertEquals(
				"CARDS: {4=[4D]}" + System.lineSeparator() +
				"BOOKS: [10]", hand.toString());
	}
}
