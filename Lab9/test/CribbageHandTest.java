import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class CribbageHandTest {

	@Test
	@Order(1)  // Run first to ensure Map is created before object creation.
	void testCardValues() {
		assertEquals(13, CribbageHand.CARD_VALUES.size());
		assertEquals(1, CribbageHand.CARD_VALUES.get(Rank.ACE));
		assertEquals(2, CribbageHand.CARD_VALUES.get(Rank.TWO));
		assertEquals(3, CribbageHand.CARD_VALUES.get(Rank.THREE));
		assertEquals(4, CribbageHand.CARD_VALUES.get(Rank.FOUR));
		assertEquals(5, CribbageHand.CARD_VALUES.get(Rank.FIVE));
		assertEquals(6, CribbageHand.CARD_VALUES.get(Rank.SIX));
		assertEquals(7, CribbageHand.CARD_VALUES.get(Rank.SEVEN));
		assertEquals(8, CribbageHand.CARD_VALUES.get(Rank.EIGHT));
		assertEquals(9, CribbageHand.CARD_VALUES.get(Rank.NINE));
		assertEquals(10, CribbageHand.CARD_VALUES.get(Rank.TEN));
		assertEquals(10, CribbageHand.CARD_VALUES.get(Rank.JACK));
		assertEquals(10, CribbageHand.CARD_VALUES.get(Rank.QUEEN));
		assertEquals(10, CribbageHand.CARD_VALUES.get(Rank.KING));
	}

	@Test
	void testFinalDeclarations() {
		try {
			Field field = CribbageHand.class.getDeclaredField("CARD_VALUES");
			int modifiers = field.getModifiers();
			assertTrue(Modifier.isFinal(modifiers));
		} catch (NoSuchFieldException e) {
			fail();
		}

		try {
			Field field = CribbageHand.class.getDeclaredField("cards");
			int modifiers = field.getModifiers();
			assertTrue(Modifier.isFinal(modifiers));
		} catch (NoSuchFieldException e) {
			fail();
		}
	}

	@Test
	void testConstructor() {
		Card c1 = new Card(Rank.ACE, Suit.CLUBS);
		Card c2 = new Card(Rank.TWO, Suit.DIAMONDS);
		Card c3 = new Card(Rank.THREE, Suit.HEARTS);
		Card c4 = new Card(Rank.FOUR, Suit.SPADES);

		try {
			new CribbageHand(null, c2, c3, c4);
			fail();
		} catch (NullPointerException e) {}

		try {
			new CribbageHand(c1, null, c3, c4);
			fail();
		} catch (NullPointerException e) {}

		try {
			new CribbageHand(c1, c2, null, c4);
			fail();
		} catch (NullPointerException e) {}

		try {
			new CribbageHand(c1, c2, c3, null);
			fail();
		} catch (NullPointerException e) {}

		CribbageHand hand = new CribbageHand(c1, c2, c3, c4);
		assertEquals(List.of(c1, c2, c3, c4), hand.cards);
	}

	@Test
	void testImmutability() {
		try {
			CribbageHand.CARD_VALUES.clear();
			fail();
		} catch (UnsupportedOperationException e) {}

		try {
			CribbageHand.CARD_VALUES.put(Rank.KING, -54321);
			fail();
		} catch (UnsupportedOperationException e) {}

		try {
			CribbageHand.CARD_VALUES.remove(Rank.ACE);
			fail();
		} catch (UnsupportedOperationException e) {}

		CribbageHand hand = new CribbageHand(
				new Card(Rank.FIVE, Suit.SPADES),
				new Card(Rank.SIX, Suit.HEARTS),
				new Card(Rank.SEVEN, Suit.DIAMONDS),
				new Card(Rank.EIGHT, Suit.CLUBS));

		try {
			hand.cards.add(new Card(Rank.NINE, Suit.SPADES));
			fail();
		} catch (UnsupportedOperationException e) {}

		try {
			hand.cards.clear();
			fail();
		} catch (UnsupportedOperationException e) {}

		try {
			hand.cards.remove(0);
			fail();
		} catch (UnsupportedOperationException e) {}
	}

	@Test
	void testPowerSet() {
		// Base case
		assertEquals(Set.of(Set.of()), CribbageHand.powerSet(List.of()));

		// Recursive case
		Card c1 = new Card(Rank.NINE, Suit.CLUBS);
		Card c2 = new Card(Rank.TEN, Suit.DIAMONDS);
		Card c3 = new Card(Rank.JACK, Suit.HEARTS);
		Card c4 = new Card(Rank.QUEEN, Suit.SPADES);

		assertEquals(Set.of(Set.of(), Set.of(c1)),
				CribbageHand.powerSet(List.of(c1)));
		assertEquals(Set.of(Set.of(), Set.of(c2)),
				CribbageHand.powerSet(List.of(c2)));

		assertEquals(Set.of(Set.of(), Set.of(c1), Set.of(c2), Set.of(c1, c2)),
				CribbageHand.powerSet(List.of(c1, c2)));
		assertEquals(Set.of(Set.of(), Set.of(c3), Set.of(c4), Set.of(c3, c4)),
				CribbageHand.powerSet(List.of(c3, c4)));

		assertEquals(Set.of(Set.of(), Set.of(c1), Set.of(c2), Set.of(c3),
				Set.of(c1, c2), Set.of(c1, c3), Set.of(c2, c3),
				Set.of(c1, c2, c3)),
				CribbageHand.powerSet(List.of(c1, c2, c3)));

		assertEquals(Set.of(
				Set.of(),
				Set.of(c1), Set.of(c2), Set.of(c3), Set.of(c4),
				Set.of(c1, c2), Set.of(c1, c3), Set.of(c1, c4), Set.of(c2, c3),
				Set.of(c2, c4), Set.of(c3, c4),
				Set.of(c1, c2, c3), Set.of(c1, c2, c4), Set.of(c1, c3, c4),
				Set.of(c2, c3, c4),
				Set.of(c1, c2, c3, c4)),
				CribbageHand.powerSet(List.of(c1, c2, c3, c4)));
	}

	@Test
	void testFifteens() {
		Card c1 = new Card(Rank.TWO, Suit.CLUBS);
		Card c2 = new Card(Rank.FOUR, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SIX, Suit.HEARTS);
		Card c4 = new Card(Rank.EIGHT, Suit.SPADES);
		Card starter = new Card(Rank.JACK, Suit.DIAMONDS);
		CribbageHand hand = new CribbageHand(c1, c2, c3, c4);
		assertEquals(Set.of(), hand.fifteens(starter));

		starter = new Card(Rank.NINE, Suit.HEARTS);
		assertEquals(Set.of(Set.of(c3, starter), Set.of(c1, c2, starter)),
				hand.fifteens(starter));

		starter = new Card(Rank.SEVEN, Suit.DIAMONDS);
		assertEquals(Set.of(Set.of(c4, starter), Set.of(c1, c3, starter)),
				hand.fifteens(starter));

		starter = new Card(Rank.FIVE, Suit.SPADES);
		assertEquals(Set.of(Set.of(c1, c4, starter),
				Set.of(c2, c3, starter)), hand.fifteens(starter));

		starter = new Card(Rank.THREE, Suit.CLUBS);
		assertEquals(Set.of(Set.of(c2, c4, starter),
				Set.of(c1, c2, c3, starter)), hand.fifteens(starter));

		starter = new Card(Rank.ACE, Suit.HEARTS);
		assertEquals(Set.of(Set.of(c3, c4, starter),
				Set.of(c1, c2, c4, starter)), hand.fifteens(starter));

		c1 = new Card(Rank.TWO, Suit.SPADES);
		c2 = new Card(Rank.THREE, Suit.SPADES);
		c3 = new Card(Rank.FIVE, Suit.CLUBS);
		c4 = new Card(Rank.TEN, Suit.CLUBS);
		starter = new Card(Rank.TEN, Suit.SPADES);
		hand = new CribbageHand(c1, c2, c3, c4);
		assertEquals(Set.of(Set.of(c3, c4), Set.of(c3, starter),
				Set.of(c1, c2, c4), Set.of(c1, c2, starter)),
				hand.fifteens(starter));

		starter = new Card(Rank.FIVE, Suit.HEARTS);
		assertEquals(Set.of(Set.of(c3, c4), Set.of(c4, starter),
				Set.of(c1, c2, c4), Set.of(c1, c2, c3, starter)),
				hand.fifteens(starter));

		c1 = new Card(Rank.FIVE, Suit.CLUBS);
		c2 = new Card(Rank.FIVE, Suit.DIAMONDS);
		c3 = new Card(Rank.FIVE, Suit.HEARTS);
		c4 = new Card(Rank.FIVE, Suit.SPADES);
		starter = new Card(Rank.ACE, Suit.SPADES);
		hand = new CribbageHand(c1, c2, c3, c4);
		assertEquals(Set.of(Set.of(c1, c2, c3), Set.of(c1, c2, c4),
				Set.of(c1, c3, c4), Set.of(c2, c3, c4)),
				hand.fifteens(starter));

		starter = new Card(Rank.TEN, Suit.CLUBS);
		assertEquals(Set.of(
				Set.of(c1, starter), Set.of(c2, starter), Set.of(c3, starter),
				Set.of(c4, starter), Set.of(c1, c2, c3), Set.of(c1, c2, c4),
				Set.of(c1, c3, c4), Set.of(c2, c3, c4)),
				hand.fifteens(starter));
	}
}
