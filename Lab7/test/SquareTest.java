import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SquareTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testInheritance() {
		Square square = new Square(1);
		assertTrue(square instanceof Rectangle);
		assertTrue(square instanceof IsoscelesTrapezoid);
		assertTrue(square instanceof Polygon);
		assertTrue(square instanceof Shape);
	}

	@Test
	void testFields() {
		assertEquals(Square.class.getDeclaredFields().length, 0,
				"Do not declare any fields in Square.");
	}

	@Test
	void testOverrides() {
		List<String> inheritedMethods = Arrays.asList("getID", "toString",
				"getPerimeter", "getArea", "getTop", "getBottom", "getLeg",
				"getCenterRectangle", "getSideTriangle", "getWidth",
				"getHeight");
		for (String method : inheritedMethods) {
			try {
				Square.class.getDeclaredMethod(method);
				fail("Do not override " + method + " in Square.");
			} catch (NoSuchMethodException e) {}
		}

		try {
			String.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in String.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		Square square = new Square(5.678);
		assertEquals(5.678, square.getSide(), EPSILON);
		assertEquals(square.getSide(), square.getWidth(), EPSILON);
		assertEquals(square.getSide(), square.getHeight(), EPSILON);
		assertEquals(square.getSide(), square.getTop(), EPSILON);
		assertEquals(square.getSide(), square.getBase(), EPSILON);
		assertEquals(square.getSide(), square.getLeg(), EPSILON);

		square = new Square(8675309);
		assertEquals(8675309, square.getSide(), EPSILON);
		assertEquals(square.getSide(), square.getWidth(), EPSILON);
		assertEquals(square.getSide(), square.getHeight(), EPSILON);
		assertEquals(square.getSide(), square.getTop(), EPSILON);
		assertEquals(square.getSide(), square.getBase(), EPSILON);
		assertEquals(square.getSide(), square.getLeg(), EPSILON);
	}

	@Test
	void testPerimeterAndArea() {
		Shape square = new Square(2);
		assertEquals(8, square.getPerimeter(), EPSILON);
		assertEquals(4, square.getArea(), EPSILON);

		square = new Square(Math.sqrt(2));
		assertEquals(5.6569, square.getPerimeter(), EPSILON);
		assertEquals(2, square.getArea(), EPSILON);
	}
}
