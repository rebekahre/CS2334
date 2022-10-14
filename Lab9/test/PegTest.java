import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class PegTest {

	@Test
	void testOther() {
		try {
			Peg.other(null, Peg.LEFT);
			fail();
		} catch (NullPointerException e) {}

		try {
			Peg.other(Peg.MIDDLE, null);
			fail();
		} catch (NullPointerException e) {}

		try {
			Peg.other(Peg.RIGHT, Peg.RIGHT);
			fail();
		} catch (IllegalArgumentException e) {}

		assertEquals(Peg.LEFT, Peg.other(Peg.MIDDLE, Peg.RIGHT));
		assertEquals(Peg.LEFT, Peg.other(Peg.RIGHT, Peg.MIDDLE));
		assertEquals(Peg.MIDDLE, Peg.other(Peg.LEFT, Peg.RIGHT));
		assertEquals(Peg.MIDDLE, Peg.other(Peg.RIGHT, Peg.LEFT));
		assertEquals(Peg.RIGHT, Peg.other(Peg.LEFT, Peg.MIDDLE));
		assertEquals(Peg.RIGHT, Peg.other(Peg.MIDDLE, Peg.LEFT));
	}
}
