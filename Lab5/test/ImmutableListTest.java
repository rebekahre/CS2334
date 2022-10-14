import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ImmutableListTest {

	@Test
	void testInheritance() {
		IntegerList list = new ImmutableList();
		assertTrue(list instanceof IntegerList);
		assertTrue(list instanceof ImmutableList);
	}

	@Test
	void testConstructors() {
		IntegerList list = new ImmutableList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		list = new ImmutableList(0);
		assertEquals(1, list.size());
		assertEquals(0, list.get(0));
		assertEquals("[0]", list.toString());
		
		list = new ImmutableList(-1, 1, -1);
		assertEquals(3, list.size());
		assertEquals(-1, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(-1, list.get(2));
		assertEquals("[-1, 1, -1]", list.toString());
	}
	
	@Test
	void testAdd() {
		IntegerList list = new ImmutableList(10, 9, 8);
		assertEquals(3, list.size());
		assertEquals(10, list.get(0));
		assertEquals(9, list.get(1));
		assertEquals(8, list.get(2));
		assertEquals("[10, 9, 8]", list.toString());
		
		try {
			list.add(7);
			fail();
		} catch (UnsupportedOperationException e) {}
		assertEquals(3, list.size());
		assertEquals(10, list.get(0));
		assertEquals(9, list.get(1));
		assertEquals(8, list.get(2));
		assertEquals("[10, 9, 8]", list.toString());
	}
	
	@Test
	void testInsert() {
		IntegerList list = new ImmutableList(10, 8);
		assertEquals(2, list.size());
		assertEquals(10, list.get(0));
		assertEquals(8, list.get(1));
		assertEquals("[10, 8]", list.toString());
		
		try {
			list.insert(1, 9);
			fail();
		} catch (UnsupportedOperationException e) {}
		assertEquals(2, list.size());
		assertEquals(10, list.get(0));
		assertEquals(8, list.get(1));
		assertEquals("[10, 8]", list.toString());
	}
	
	@Test
	void testRemove() {
		IntegerList list = new ImmutableList(10, 9, 8, 7);
		assertEquals(4, list.size());
		assertEquals(10, list.get(0));
		assertEquals(9, list.get(1));
		assertEquals(8, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals("[10, 9, 8, 7]", list.toString());
		
		try {
			list.remove(3);
			fail();
		} catch (UnsupportedOperationException e) {}
		assertEquals(4, list.size());
		assertEquals(10, list.get(0));
		assertEquals(9, list.get(1));
		assertEquals(8, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals("[10, 9, 8, 7]", list.toString());
	}
}
