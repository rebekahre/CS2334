import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class MoveTest {

	@Test
	void testFields() {
		try {
			Field field = Move.class.getDeclaredField("from");
			int modifiers = field.getModifiers();
			assertTrue(Modifier.isFinal(modifiers));
		} catch (NoSuchFieldException e) {
			fail();
		}

		try {
			Field field = Move.class.getDeclaredField("to");
			int modifiers = field.getModifiers();
			assertTrue(Modifier.isFinal(modifiers));
		} catch (NoSuchFieldException e) {
			fail();
		}

		assertEquals(Peg.LEFT, Move.LEFT_TO_MIDDLE.from);
		assertEquals(Peg.MIDDLE, Move.LEFT_TO_MIDDLE.to);
		assertEquals(Peg.LEFT, Move.LEFT_TO_RIGHT.from);
		assertEquals(Peg.RIGHT, Move.LEFT_TO_RIGHT.to);
		assertEquals(Peg.MIDDLE, Move.MIDDLE_TO_LEFT.from);
		assertEquals(Peg.LEFT, Move.MIDDLE_TO_LEFT.to);
		assertEquals(Peg.MIDDLE, Move.MIDDLE_TO_RIGHT.from);
		assertEquals(Peg.RIGHT, Move.MIDDLE_TO_RIGHT.to);
		assertEquals(Peg.RIGHT, Move.RIGHT_TO_MIDDLE.from);
		assertEquals(Peg.MIDDLE, Move.RIGHT_TO_MIDDLE.to);
		assertEquals(Peg.RIGHT, Move.RIGHT_TO_LEFT.from);
		assertEquals(Peg.LEFT, Move.RIGHT_TO_LEFT.to);
	}

	@Test
	void testGetMove() {
		try {
			Move.move(null, Peg.RIGHT);
			fail();
		} catch (NullPointerException e) {}

		try {
			Move.move(Peg.MIDDLE, null);
			fail();
		} catch (NullPointerException e) {}

		try {
			Move.move(Peg.LEFT, Peg.LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		assertEquals(Move.LEFT_TO_MIDDLE, Move.move(Peg.LEFT, Peg.MIDDLE));
		assertEquals(Move.LEFT_TO_RIGHT, Move.move(Peg.LEFT, Peg.RIGHT));
		assertEquals(Move.MIDDLE_TO_LEFT, Move.move(Peg.MIDDLE, Peg.LEFT));
		assertEquals(Move.MIDDLE_TO_RIGHT, Move.move(Peg.MIDDLE, Peg.RIGHT));
		assertEquals(Move.RIGHT_TO_MIDDLE, Move.move(Peg.RIGHT, Peg.MIDDLE));
		assertEquals(Move.RIGHT_TO_LEFT, Move.move(Peg.RIGHT, Peg.LEFT));
	}
}
