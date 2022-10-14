import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class BinarySearchTest {

	@Test
	void testBaseCases() {
		List<String> strings = List.of("a", "b", "c", "d", "e");
		List<Integer> indices = List.of(-1);

		// Base case 1: target not found
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 0, -1));
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 1, 0));
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 2, 1));
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 3, 2));
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 4, 3));
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 5, 4));
		assertEquals(indices, BinarySearch.binarySearch(strings, "", 7, -7));

		// Base case 2: target found
		indices = List.of(0);
		assertEquals(indices, BinarySearch.binarySearch(strings, "a", 0, 0));
		assertEquals(indices, BinarySearch.binarySearch(strings, "a", 0, 1));
		indices = List.of(1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 1, 1));
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 1, 2));
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 0, 2));
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 0, 3));
		indices = List.of(2);
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 2, 2));
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 2, 3));
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 1, 3));
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 1, 4));
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 0, 4));
		indices = List.of(3);
		assertEquals(indices, BinarySearch.binarySearch(strings, "d", 3, 3));
		assertEquals(indices, BinarySearch.binarySearch(strings, "d", 3, 4));
		assertEquals(indices, BinarySearch.binarySearch(strings, "d", 2, 4));
		indices = List.of(4);
		assertEquals(indices, BinarySearch.binarySearch(strings, "e", 4, 4));
	}

	@Test
	void testRecursiveCase() {
		List<String> strings = List.of("b");
		List<Integer> indices;

		indices = List.of(0, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "a", 0, 0));
		indices = List.of(0);
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 0, 0));
		indices = List.of(0, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 0, 0));

		strings = List.of("b", "d");

		indices = List.of(0, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "a", 0, 1));
		indices = List.of(0);
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 0, 1));
		indices = List.of(0, 1, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 0, 1));
		indices = List.of(0, 1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "d", 0, 1));
		indices = List.of(0, 1, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "e", 0, 1));

		strings = List.of("b", "d", "f", "h", "j", "l", "n");

		indices = List.of(3, 1, 0, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "a", 0, 6));
		indices = List.of(3, 1, 0);
		assertEquals(indices, BinarySearch.binarySearch(strings, "b", 0, 6));
		indices = List.of(3, 1, 0, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "c", 0, 6));
		indices = List.of(3, 1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "d", 0, 6));
		indices = List.of(3, 1, 2, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "e", 0, 6));
		indices = List.of(3, 1, 2);
		assertEquals(indices, BinarySearch.binarySearch(strings, "f", 0, 6));
		indices = List.of(3, 1, 2, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "g", 0, 6));
		indices = List.of(3);
		assertEquals(indices, BinarySearch.binarySearch(strings, "h", 0, 6));
		indices = List.of(3, 5, 4, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "i", 0, 6));
		indices = List.of(3, 5, 4);
		assertEquals(indices, BinarySearch.binarySearch(strings, "j", 0, 6));
		indices = List.of(3, 5, 4, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "k", 0, 6));
		indices = List.of(3, 5);
		assertEquals(indices, BinarySearch.binarySearch(strings, "l", 0, 6));
		indices = List.of(3, 5, 6, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "m", 0, 6));
		indices = List.of(3, 5, 6);
		assertEquals(indices, BinarySearch.binarySearch(strings, "n", 0, 6));
		indices = List.of(3, 5, 6, -1);
		assertEquals(indices, BinarySearch.binarySearch(strings, "o", 0, 6));
	}
}
