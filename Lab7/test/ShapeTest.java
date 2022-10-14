import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class ShapeTest {

	@Test
	void testAbstractDeclarations() {
		assertTrue(Modifier.isAbstract(Shape.class.getModifiers()),
				"Shape is not abstract.");

		try {
			Method method = Shape.class.getDeclaredMethod("getPerimeter");
			int modifiers = method.getModifiers();
			assertTrue(Modifier.isAbstract(modifiers),
					"getPerimeter is not abstract.");
		} catch (NoSuchMethodException e) {
			fail();
		}

		try {
			Method method = Shape.class.getDeclaredMethod("getArea");
			int modifiers = method.getModifiers();
			assertTrue(Modifier.isAbstract(modifiers),
					"getArea is not abstract.");
		} catch (NoSuchMethodException e) {
			fail();
		}
	}

	@Test
	void testIDs() {
		Shape s1 = new ShapeA(0, 0);
		Shape s2 = new ShapeB(0, 0);
		Shape s3 = new ShapeA(0, 0);
		Shape s4 = new ShapeB(0, 0);

		assertTrue(s1.getID() < s2.getID());
		assertTrue(s2.getID() < s3.getID());
		assertTrue(s3.getID() < s4.getID());
	}

	@Test
	void testCompareTo() {
		// Sort shapes alphabetically by the object name.
		Shape s1 = new ShapeA(10, 10);
		Shape s2 = new ShapeB(1, 1);
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s1) > 0);

		// If the names are the same, sort by perimeter (smallest to largest).
		s1 = new ShapeA(1, 10);
		s2 = new ShapeA(10, 1);
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s1) > 0);

		// If the names and perimeters are the same, sort by area (smallest to
		// largest).
		s1 = new ShapeB(1, 1);
		s2 = new ShapeB(1, 10);
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s1) > 0);

		// If the object names, perimeters, and areas are the same, leave the
		// shapes in their current order.
		s1 = new ShapeA(1, 1);
		s2 = new ShapeA(1, 1);
		assertTrue(s1.compareTo(s2) == 0);
		assertTrue(s2.compareTo(s1) == 0);
	}

	private static class ShapeA extends Shape {
		private double perimeter;
		private double area;

		public ShapeA(double perimeter, double area) {
			this.perimeter = perimeter;
			this.area = area;
		}

		@Override
		public double getPerimeter() {
			return perimeter;
		}

		@Override
		public double getArea() {
			return area;
		}
	}

	private static class ShapeB extends Shape {
		private double perimeter;
		private double area;

		public ShapeB(double perimeter, double area) {
			this.perimeter = perimeter;
			this.area = area;
		}

		@Override
		public double getPerimeter() {
			return perimeter;
		}

		@Override
		public double getArea() {
			return area;
		}
	}
}
