import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class PrimeListTest {

	@Test
	void testInheritance() {
		IntegerList list = new PrimeList();
		assertTrue(list instanceof IntegerList);
		assertTrue(list instanceof PrimeList);
	}
	
	@Test
	void testConstructors() {
		IntegerList list = new PrimeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list = new PrimeList(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be positive.", e.getMessage());
		}
		
		try {
			list = new PrimeList(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be positive.", e.getMessage());
		}
		
		list = new PrimeList(1);
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		list = new PrimeList(2);
		assertEquals(1, list.size());
		assertEquals(2, list.get(0));
		assertEquals("[2]", list.toString());
		
		list = new PrimeList(3);
		assertEquals(2, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals("[2, 3]", list.toString());
		
		list = new PrimeList(4);
		assertEquals(2, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals("[2, 3]", list.toString());
		
		list = new PrimeList(5);
		assertEquals(3, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals("[2, 3, 5]", list.toString());
	}
	
	@Test
	void testAdd() {
		IntegerList list = new PrimeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list.add(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound cannot be less than 2.", 
					e.getMessage());
		}
		
		list.add(2);
		assertEquals(1, list.size());
		assertEquals("[2]", list.toString());
		
		try {
			list.add(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be greater than the last prime "
					+ "in the list: 2.", e.getMessage());
		}
		
		list.add(8);
		assertEquals(4, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals("[2, 3, 5, 7]", list.toString());
		
		list.add(8);
		assertEquals(4, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals("[2, 3, 5, 7]", list.toString());
		
		try {
			list.add(7);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be greater than the last prime "
					+ "in the list: 7.", e.getMessage());
		}
		assertEquals(4, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals("[2, 3, 5, 7]", list.toString());
		
		list.add(20);
		assertEquals(8, list.size());
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals(11, list.get(4));
		assertEquals(13, list.get(5));
		assertEquals(17, list.get(6));
		assertEquals(19, list.get(7));
		assertEquals("[2, 3, 5, 7, 11, 13, 17, 19]", list.toString());
	}
	
	@Test
	void testInsert() {
		IntegerList list = new PrimeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list.insert(0, 451);
			fail();
		} catch (UnsupportedOperationException e) {}
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
	}
	
	@Test
	void testRemove() {
		IntegerList list = new PrimeList(40);
		assertEquals(12, list.size());
		assertEquals(29, list.get(9));
		assertEquals(31, list.get(10));
		assertEquals(37, list.get(11));
		assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]", 
				list.toString());
		
		try {
			list.remove(12);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 11].", 
					e.getMessage());
		}
		assertEquals(12, list.size());
		assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]", 
				list.toString());
		
		assertEquals(1, list.remove(11));
		assertEquals(11, list.size());
		assertEquals(23, list.get(8));
		assertEquals(29, list.get(9));
		assertEquals(31, list.get(10));
		assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]", 
				list.toString());
		
		assertEquals(4, list.remove(7));
		assertEquals(7, list.size());
		assertEquals(11, list.get(4));
		assertEquals(13, list.get(5));
		assertEquals(17, list.get(6));
		assertEquals("[2, 3, 5, 7, 11, 13, 17]", list.toString());
		
		try {
			list.remove(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 6].", 
					e.getMessage());
		}
		assertEquals(7, list.size());
		assertEquals(11, list.get(4));
		assertEquals(13, list.get(5));
		assertEquals(17, list.get(6));
		assertEquals("[2, 3, 5, 7, 11, 13, 17]", list.toString());
		
		assertEquals(6, list.remove(1));
		assertEquals(1, list.size());
		assertEquals(2, list.get(0));
		assertEquals("[2]", list.toString());
		
		assertEquals(1, list.remove(0));
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
	}
}
