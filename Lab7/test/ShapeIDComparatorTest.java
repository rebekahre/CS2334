import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShapeIDComparatorTest {

	@Test
	void idComparatorTests() {
		Shape square = new Square(10);
		Shape rectangle = new Rectangle(1, 1);
		ShapeIDComparator comparator = new ShapeIDComparator();

		assertTrue(comparator.compare(square, rectangle) < 0);
		assertTrue(comparator.compare(rectangle, square) > 0);
		assertTrue(comparator.compare(square, square) == 0);
		assertTrue(comparator.compare(rectangle, rectangle) == 0);
	}
}
