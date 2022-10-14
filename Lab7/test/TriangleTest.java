import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TriangleTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testInheritance() {
		Triangle triangle = new Triangle(1, 1, 1);
		assertTrue(triangle instanceof Polygon);
		assertTrue(triangle instanceof Shape);
	}

	@Test
	void testOverrides() {
		List<String> inheritedMethods = Arrays.asList("getID", "toString",
				"getPerimeter");
		for (String method : inheritedMethods) {
			try {
				Triangle.class.getDeclaredMethod(method);
				fail("Do not override " + method + " in Triangle.");
			} catch (NoSuchMethodException e) {}
		}

		try {
			Triangle.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in Triangle.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		Triangle triangle = new Triangle(2, 3, 4);
		assertEquals(2, triangle.getSideA(), EPSILON);
		assertEquals(3, triangle.getSideB(), EPSILON);
		assertEquals(4, triangle.getSideC(), EPSILON);

		triangle = new Triangle(1, 2, Math.sqrt(3));
		assertEquals(1, triangle.getSideA(), EPSILON);
		assertEquals(2, triangle.getSideB(), EPSILON);
		assertEquals(Math.sqrt(3), triangle.getSideC(), EPSILON);
	}

	@Test
	void testPerimeterAndArea() {
		Shape triangle = new Triangle(7, 7, 7);
		assertEquals(21, triangle.getPerimeter(), EPSILON);
		assertEquals(21.2176, triangle.getArea(), EPSILON);

		triangle = new Triangle(17, 11, 13);
		assertEquals(41, triangle.getPerimeter(), EPSILON);
		assertEquals(71.4996, triangle.getArea(), EPSILON);
	}
}
