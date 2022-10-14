import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class PolygonTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testAbstractDeclarations() {
		assertTrue(Modifier.isAbstract(Polygon.class.getModifiers()),
				"Polygon is not abstract.");
	}

	@Test
	void testInheritance() {
		ConcretePolygon polygon = new ConcretePolygon(1, 1, 1);
		assertTrue(polygon instanceof Polygon);
		assertTrue(polygon instanceof Shape);
	}

	@Test
	void testOverrides() {
		try {
			Polygon.class.getDeclaredMethod("getID");
			fail("Do not override getID in Polygon.");
		} catch (NoSuchMethodException e) {}

		try {
			Polygon.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in Polygon.");
		} catch (NoSuchMethodException e) {}

		try {
			Polygon.class.getDeclaredMethod("toString");
			fail("Do not override toString in Polygon.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testInvalidConstructorInput() {
		try {
			new ConcretePolygon(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("null sides", e.getMessage());
		}

		try {
			new ConcretePolygon();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of sides: 0", e.getMessage());
		}

		try {
			new ConcretePolygon(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of sides: 1", e.getMessage());
		}

		try {
			new ConcretePolygon(1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid number of sides: 2", e.getMessage());
		}

		try {
			new ConcretePolygon(1, 1, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive side: 0.0", e.getMessage());
		}

		try {
			new ConcretePolygon(1, 1, 1, -1, 1, 1, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive side: -1.0", e.getMessage());
		}

		try {
			new ConcretePolygon(1, 1, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Polygon inequality violated: 2.0 >= 2.0",
					e.getMessage());
		}

		try {
			new ConcretePolygon(1, 4, 11, 2, 3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Polygon inequality violated: 11.0 >= 10.0",
					e.getMessage());
		}

		try {
			new ConcretePolygon(1324, 2334, 9000, 1323, 2334, 1323);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Polygon inequality violated: 9000.0 >= 8638.0",
					e.getMessage());
		}
	}

	@Test
	void testPerimeter() {
		Shape polygon = new ConcretePolygon(1, 1, 1);
		assertEquals(3, polygon.getPerimeter(), EPSILON);

		polygon = new ConcretePolygon(4.2, 10.1, 4.51, 2.001);
		assertEquals(20.811, polygon.getPerimeter(), EPSILON);
	}

	private static class ConcretePolygon extends Polygon {
		public ConcretePolygon(double... sides) {
			super(sides);
		}

		@Override
		public double getArea() {
			return -1;
		}
	}
}
