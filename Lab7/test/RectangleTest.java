import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RectangleTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testInheritance() {
		Rectangle rectangle = new Rectangle(1, 1);
		assertTrue(rectangle instanceof IsoscelesTrapezoid);
		assertTrue(rectangle instanceof Polygon);
		assertTrue(rectangle instanceof Shape);
	}

	@Test
	void testFields() {
		assertEquals(Rectangle.class.getDeclaredFields().length, 0,
				"Do not declare any fields in Rectangle.");
	}

	@Test
	void testOverrides() {
		List<String> inheritedMethods = Arrays.asList("getID", "toString",
				"getPerimeter", "getArea", "getTop", "getBottom", "getLeg",
				"getCenterRectangle", "getSideTriangle");
		for (String method : inheritedMethods) {
			try {
				Rectangle.class.getDeclaredMethod(method);
				fail("Do not override " + method + " in Rectangle.");
			} catch (NoSuchMethodException e) {}
		}

		try {
			Rectangle.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in Rectangle.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		Rectangle rectangle = new Rectangle(1, 2);
		assertEquals(1, rectangle.getWidth(), EPSILON);
		assertEquals(2, rectangle.getHeight(), EPSILON);
		assertEquals(rectangle.getWidth(), rectangle.getTop(), EPSILON);
		assertEquals(rectangle.getWidth(), rectangle.getBase(), EPSILON);
		assertEquals(rectangle.getHeight(), rectangle.getLeg(), EPSILON);

		rectangle = new Rectangle(2, 1);
		assertEquals(2, rectangle.getWidth(), EPSILON);
		assertEquals(1, rectangle.getHeight(), EPSILON);
		assertEquals(rectangle.getWidth(), rectangle.getTop(), EPSILON);
		assertEquals(rectangle.getWidth(), rectangle.getBase(), EPSILON);
		assertEquals(rectangle.getHeight(), rectangle.getLeg(), EPSILON);
	}

	@Test
	void testPerimeterAndArea() {
		Shape rectangle = new Rectangle(3, 4);
		assertEquals(14, rectangle.getPerimeter(), EPSILON);
		assertEquals(12, rectangle.getArea(), EPSILON);

		rectangle = new Rectangle(1224, 2334);
		assertEquals(7116, rectangle.getPerimeter(), EPSILON);
		assertEquals(2856816, rectangle.getArea(), EPSILON);
	}

	@Test
	void testCenterRectangle() {
		Rectangle r1 = new Rectangle(42, 451);
		Rectangle r2 = r1.getCenterRectangle();
		assertEquals(r1.getWidth(), r2.getWidth(), EPSILON);
		assertEquals(r1.getHeight(), r2.getHeight(), EPSILON);

		r1 = new Rectangle(2010, 2001);
		r2 = r1.getCenterRectangle();
		assertEquals(r1.getWidth(), r2.getWidth(), EPSILON);
		assertEquals(r1.getHeight(), r2.getHeight(), EPSILON);
	}

	@Test
	void testSideTriangle() {
		Rectangle rectangle = new Rectangle(1, 2);
		try {
			rectangle.getSideTriangle();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive side: 0.0", e.getMessage());
		}
	}
}
