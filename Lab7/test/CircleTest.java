import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class CircleTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testInheritance() {
		Circle circle = new Circle(1);
		assertTrue(circle instanceof Shape);
	}

	@Test
	void testOverrides() {
		try {
			Circle.class.getDeclaredMethod("getID");
			fail("Do not override getID in Circle.");
		} catch (NoSuchMethodException e) {}

		try {
			Circle.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in Circle.");
		} catch (NoSuchMethodException e) {}

		try {
			Circle.class.getDeclaredMethod("toString");
			fail("Do not override toString in Circle.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		Circle circle = new Circle(0.5);
		assertEquals(0.5, circle.getRadius(), EPSILON);

		circle = new Circle(4.2);
		assertEquals(4.2, circle.getRadius(), EPSILON);

		try {
			new Circle(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive radius: 0.0", e.getMessage());
		}

		try {
			new Circle(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive radius: -1.0", e.getMessage());
		}
	}

	@Test
	void testPerimeterAndArea() {
		Shape circle = new Circle(1);
		assertEquals(6.2832, circle.getPerimeter(), EPSILON);
		assertEquals(3.1415, circle.getArea(), EPSILON);

		circle = new Circle(2);
		assertEquals(12.5664, circle.getPerimeter(), EPSILON);
		assertEquals(12.5664, circle.getArea(), EPSILON);
	}
}
