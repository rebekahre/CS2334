import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class IsoscelesTrapezoidTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;

	@Test
	void testInheritance() {
		IsoscelesTrapezoid trapezoid = new IsoscelesTrapezoid(1, 1, 1);
		assertTrue(trapezoid instanceof Polygon);
		assertTrue(trapezoid instanceof Shape);
	}

	@Test
	void testOverrides() {
		List<String> inheritedMethods = Arrays.asList("getID", "toString",
				"getPerimeter");
		for (String method : inheritedMethods) {
			try {
				IsoscelesTrapezoid.class.getDeclaredMethod(method);
				fail("Do not override " + method + " in IsoscelesTrapezoid.");
			} catch (NoSuchMethodException e) {}
		}

		try {
			IsoscelesTrapezoid.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in IsoscelesTrapezoid.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		IsoscelesTrapezoid trapezoid = new IsoscelesTrapezoid(1, 2, 3);
		assertEquals(1, trapezoid.getTop(), EPSILON);
		assertEquals(2, trapezoid.getBase(), EPSILON);
		assertEquals(3, trapezoid.getLeg(), EPSILON);

		trapezoid = new IsoscelesTrapezoid(451, 42, 321);
		assertEquals(451, trapezoid.getTop(), EPSILON);
		assertEquals(42, trapezoid.getBase(), EPSILON);
		assertEquals(321, trapezoid.getLeg(), EPSILON);
	}

	@Test
	void testPerimeterAndArea() {
		Shape trapezoid = new IsoscelesTrapezoid(1, 3, Math.sqrt(2));
		assertEquals(6.8284, trapezoid.getPerimeter(), EPSILON);
		assertEquals(2, trapezoid.getArea(), EPSILON);

		trapezoid = new IsoscelesTrapezoid(19, 17, 3);
		assertEquals(42, trapezoid.getPerimeter(), EPSILON);
		assertEquals(50.9117, trapezoid.getArea(), EPSILON);
	}

	@Test
	void testCenterRectangle() {
		IsoscelesTrapezoid trapezoid = new IsoscelesTrapezoid(1, 4.4641, 2);
		Rectangle rectangle = trapezoid.getCenterRectangle();
		assertEquals(1, rectangle.getWidth(), EPSILON);
		assertEquals(1, rectangle.getHeight(), EPSILON);

		trapezoid = new IsoscelesTrapezoid(4.4641, 1, 2);
		rectangle = trapezoid.getCenterRectangle();
		assertEquals(1, rectangle.getWidth(), EPSILON);
		assertEquals(1, rectangle.getHeight(), EPSILON);

		trapezoid = new IsoscelesTrapezoid(42, 50, 5);
		rectangle = trapezoid.getCenterRectangle();
		assertEquals(42, rectangle.getWidth(), EPSILON);
		assertEquals(3, rectangle.getHeight(), EPSILON);
	}

	@Test
	void testSideTriangle() {
		IsoscelesTrapezoid trapezoid = new IsoscelesTrapezoid(1, 4.4641, 2);
		RightTriangle triangle = trapezoid.getSideTriangle();
		assertEquals(Math.sqrt(3), triangle.getBase(), EPSILON);
		assertEquals(1, triangle.getHeight(), EPSILON);
		assertEquals(2, triangle.getHypotenuse(), EPSILON);

		trapezoid = new IsoscelesTrapezoid(4.4641, 1, 2);
		triangle = trapezoid.getSideTriangle();
		assertEquals(Math.sqrt(3), triangle.getBase(), EPSILON);
		assertEquals(1, triangle.getHeight(), EPSILON);
		assertEquals(2, triangle.getHypotenuse(), EPSILON);

		trapezoid = new IsoscelesTrapezoid(50, 42, 5);
		triangle = trapezoid.getSideTriangle();
		assertEquals(4, triangle.getBase(), EPSILON);
		assertEquals(3, triangle.getHeight(), EPSILON);
		assertEquals(5, triangle.getHypotenuse(), EPSILON);
	}
}
