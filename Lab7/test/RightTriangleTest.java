import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RightTriangleTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testInheritance() {
		RightTriangle rightTriangle = new RightTriangle(1, 1);
		assertTrue(rightTriangle instanceof Triangle);
		assertTrue(rightTriangle instanceof Polygon);
		assertTrue(rightTriangle instanceof Shape);
	}

	@Test
	void testFields() {
		assertEquals(RightTriangle.class.getDeclaredFields().length, 0,
				"Do not declare any fields in RightTriangle.");
	}

	@Test
	void testOverrides() {
		List<String> inheritedMethods = Arrays.asList("getID", "toString",
				"getPerimeter", "getArea", "getSideA", "getSideB", "getSideC");
		for (String method : inheritedMethods) {
			try {
				RightTriangle.class.getDeclaredMethod(method);
				fail("Do not override " + method + " in RightTriangle.");
			} catch (NoSuchMethodException e) {}
		}

		try {
			RightTriangle.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in RightTriangle.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		RightTriangle triangle = new RightTriangle(3, 4);
		assertEquals(3, triangle.getBase(), EPSILON);
		assertEquals(4, triangle.getHeight(), EPSILON);
		assertEquals(5, triangle.getHypotenuse(), EPSILON);
		assertEquals(triangle.getBase(), triangle.getSideA(), EPSILON);
		assertEquals(triangle.getHeight(), triangle.getSideB(), EPSILON);
		assertEquals(triangle.getHypotenuse(), triangle.getSideC(), EPSILON);

		triangle = new RightTriangle(1, Math.sqrt(3));
		assertEquals(1, triangle.getBase(), EPSILON);
		assertEquals(Math.sqrt(3), triangle.getHeight(), EPSILON);
		assertEquals(2, triangle.getHypotenuse(), EPSILON);
		assertEquals(triangle.getBase(), triangle.getSideA(), EPSILON);
		assertEquals(triangle.getHeight(), triangle.getSideB(), EPSILON);
		assertEquals(triangle.getHypotenuse(), triangle.getSideC(), EPSILON);
	}

	@Test
	void testPerimeterAndArea() {
		Shape triangle = new RightTriangle(3, 4);
		assertEquals(12, triangle.getPerimeter(), EPSILON);
		assertEquals(6, triangle.getArea(), EPSILON);

		triangle = new RightTriangle(1224, 2334);
		assertEquals(6193.4757, triangle.getPerimeter(), EPSILON);
		assertEquals(1428408, triangle.getArea(), EPSILON);
	}
}
