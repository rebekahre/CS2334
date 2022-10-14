import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.Test;

class TowerOfHanoiTest {

	@Test
	void testConstructor() {
		try {
			new TowerOfHanoi(-1, Peg.LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new TowerOfHanoi(0, Peg.MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new TowerOfHanoi(1, null);
			fail();
		} catch (NullPointerException e) {}

		TowerOfHanoi game = new TowerOfHanoi(1, Peg.LEFT);
		Deque<Integer> leftStack = game.getDiskStack(Peg.LEFT);
		Deque<Integer> middleStack = game.getDiskStack(Peg.MIDDLE);
		Deque<Integer> rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(1), List.copyOf(leftStack));
		assertEquals(List.of(), List.copyOf(middleStack));
		assertEquals(List.of(), List.copyOf(rightStack));
		assertNotSame(middleStack, rightStack);

		game = new TowerOfHanoi(4, Peg.MIDDLE);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(), List.copyOf(leftStack));
		assertEquals(List.of(1, 2, 3, 4), List.copyOf(middleStack));
		assertEquals(List.of(), List.copyOf(rightStack));
		assertNotSame(leftStack, rightStack);

		game = new TowerOfHanoi(7, Peg.RIGHT);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(), List.copyOf(leftStack));
		assertEquals(List.of(), List.copyOf(middleStack));
		assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), List.copyOf(rightStack));
		assertNotSame(leftStack, middleStack);
	}

	@Test
	void testGetDiskStack() {
		TowerOfHanoi game = new TowerOfHanoi(1, Peg.MIDDLE);
		try {
			game.getDiskStack(null);
			fail();
		} catch (NullPointerException e) {}

		// Test encapsulation
		Deque<Integer> leftStack = game.getDiskStack(Peg.LEFT);
		Deque<Integer> middleStack = game.getDiskStack(Peg.MIDDLE);
		Deque<Integer> rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(), List.copyOf(leftStack));
		assertEquals(List.of(1), List.copyOf(middleStack));
		assertEquals(List.of(), List.copyOf(rightStack));

		leftStack.push(-12345);
		middleStack.push(-12345);
		rightStack.push(-12345);

		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(), List.copyOf(leftStack));
		assertEquals(List.of(1), List.copyOf(middleStack));
		assertEquals(List.of(), List.copyOf(rightStack));
	}

	@Test
	void testMoveDisk() {
		TowerOfHanoi game = new TowerOfHanoi(3, Peg.LEFT);
		Deque<Integer> leftStack = game.getDiskStack(Peg.LEFT);
		Deque<Integer> middleStack = game.getDiskStack(Peg.MIDDLE);
		Deque<Integer> rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(1, 2, 3), List.copyOf(leftStack));
		assertEquals(List.of(), List.copyOf(middleStack));
		assertEquals(List.of(), List.copyOf(rightStack));

		try {
			game.moveDisk(null);
			fail();
		} catch (NullPointerException e) {}

		try {
			game.moveDisk(Move.MIDDLE_TO_LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.MIDDLE_TO_RIGHT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.RIGHT_TO_LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.RIGHT_TO_MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.LEFT_TO_RIGHT);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(2, 3), List.copyOf(leftStack));
		assertEquals(List.of(), List.copyOf(middleStack));
		assertEquals(List.of(1), List.copyOf(rightStack));

		try {
			game.moveDisk(Move.LEFT_TO_RIGHT);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.LEFT_TO_MIDDLE);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(3), List.copyOf(leftStack));
		assertEquals(List.of(2), List.copyOf(middleStack));
		assertEquals(List.of(1), List.copyOf(rightStack));

		try {
			game.moveDisk(Move.LEFT_TO_MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.LEFT_TO_RIGHT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.MIDDLE_TO_RIGHT);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.RIGHT_TO_MIDDLE);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(3), List.copyOf(leftStack));
		assertEquals(List.of(1, 2), List.copyOf(middleStack));
		assertEquals(List.of(), List.copyOf(rightStack));

		try {
			game.moveDisk(Move.LEFT_TO_MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.LEFT_TO_RIGHT);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(), List.copyOf(leftStack));
		assertEquals(List.of(1, 2), List.copyOf(middleStack));
		assertEquals(List.of(3), List.copyOf(rightStack));

		try {
			game.moveDisk(Move.RIGHT_TO_MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.MIDDLE_TO_LEFT);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(1), List.copyOf(leftStack));
		assertEquals(List.of(2), List.copyOf(middleStack));
		assertEquals(List.of(3), List.copyOf(rightStack));

		try {
			game.moveDisk(Move.MIDDLE_TO_LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.RIGHT_TO_LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			game.moveDisk(Move.RIGHT_TO_MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.MIDDLE_TO_RIGHT);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(1), List.copyOf(leftStack));
		assertEquals(List.of(), List.copyOf(middleStack));
		assertEquals(List.of(2, 3), List.copyOf(rightStack));

		try {
			game.moveDisk(Move.RIGHT_TO_LEFT);
			fail();
		} catch (IllegalArgumentException e) {}

		game.moveDisk(Move.LEFT_TO_RIGHT);
		leftStack = game.getDiskStack(Peg.LEFT);
		middleStack = game.getDiskStack(Peg.MIDDLE);
		rightStack = game.getDiskStack(Peg.RIGHT);
		assertEquals(List.of(), List.copyOf(leftStack));
		assertEquals(List.of(), List.copyOf(middleStack));
		assertEquals(List.of(1, 2, 3), List.copyOf(rightStack));
	}

	@Test
	void testToString() {
		TowerOfHanoi game = new TowerOfHanoi(3, Peg.LEFT);
		String string = "" +
				"  LEFT: [3, 2, 1]" + System.lineSeparator() +
				"MIDDLE: []" + System.lineSeparator() +
				" RIGHT: []";
		assertEquals(string, game.toString());

		game.moveDisk(Move.LEFT_TO_RIGHT);
		string = "" +
				"  LEFT: [3, 2]" + System.lineSeparator() +
				"MIDDLE: []" + System.lineSeparator() +
				" RIGHT: [1]";
		assertEquals(string, game.toString());

		game.moveDisk(Move.LEFT_TO_MIDDLE);
		string = "" +
				"  LEFT: [3]" + System.lineSeparator() +
				"MIDDLE: [2]" + System.lineSeparator() +
				" RIGHT: [1]";
		assertEquals(string, game.toString());

		game.moveDisk(Move.RIGHT_TO_MIDDLE);
		string = "" +
				"  LEFT: [3]" + System.lineSeparator() +
				"MIDDLE: [2, 1]" + System.lineSeparator() +
				" RIGHT: []";
		assertEquals(string, game.toString());

		game.moveDisk(Move.LEFT_TO_RIGHT);
		string = "" +
				"  LEFT: []" + System.lineSeparator() +
				"MIDDLE: [2, 1]" + System.lineSeparator() +
				" RIGHT: [3]";
		assertEquals(string, game.toString());

		game.moveDisk(Move.MIDDLE_TO_LEFT);
		string = "" +
				"  LEFT: [1]" + System.lineSeparator() +
				"MIDDLE: [2]" + System.lineSeparator() +
				" RIGHT: [3]";
		assertEquals(string, game.toString());

		game.moveDisk(Move.MIDDLE_TO_RIGHT);
		string = "" +
				"  LEFT: [1]" + System.lineSeparator() +
				"MIDDLE: []" + System.lineSeparator() +
				" RIGHT: [3, 2]";
		assertEquals(string, game.toString());

		game.moveDisk(Move.LEFT_TO_RIGHT);
		string = "" +
				"  LEFT: []" + System.lineSeparator() +
				"MIDDLE: []" + System.lineSeparator() +
				" RIGHT: [3, 2, 1]";
		assertEquals(string, game.toString());
	}

	@Test
	void testSolveEdgeAndBaseCases() {
		// Edge cases
		try {
			TowerOfHanoi.solve(-1, Peg.LEFT, Peg.MIDDLE);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			TowerOfHanoi.solve(0, null, Peg.MIDDLE);
			fail();
		} catch (NullPointerException e) {}

		try {
			TowerOfHanoi.solve(0, Peg.LEFT, null);
			fail();
		} catch (NullPointerException e) {}

		assertEquals(List.of(), TowerOfHanoi.solve(0, Peg.LEFT, Peg.MIDDLE));
		assertEquals(List.of(), TowerOfHanoi.solve(0, Peg.LEFT, Peg.RIGHT));
		assertEquals(List.of(), TowerOfHanoi.solve(0, Peg.MIDDLE, Peg.LEFT));
		assertEquals(List.of(), TowerOfHanoi.solve(0, Peg.MIDDLE, Peg.RIGHT));
		assertEquals(List.of(), TowerOfHanoi.solve(0, Peg.RIGHT, Peg.LEFT));
		assertEquals(List.of(), TowerOfHanoi.solve(0, Peg.RIGHT, Peg.MIDDLE));

		assertEquals(List.of(), TowerOfHanoi.solve(2, Peg.LEFT, Peg.LEFT));
		assertEquals(List.of(), TowerOfHanoi.solve(3, Peg.MIDDLE, Peg.MIDDLE));
		assertEquals(List.of(), TowerOfHanoi.solve(4, Peg.RIGHT, Peg.RIGHT));

		// Base case
		assertEquals(List.of(Move.LEFT_TO_MIDDLE),
				TowerOfHanoi.solve(1, Peg.LEFT, Peg.MIDDLE));
		assertEquals(List.of(Move.LEFT_TO_RIGHT),
				TowerOfHanoi.solve(1, Peg.LEFT, Peg.RIGHT));
		assertEquals(List.of(Move.MIDDLE_TO_LEFT),
				TowerOfHanoi.solve(1, Peg.MIDDLE, Peg.LEFT));
		assertEquals(List.of(Move.MIDDLE_TO_RIGHT),
				TowerOfHanoi.solve(1, Peg.MIDDLE, Peg.RIGHT));
		assertEquals(List.of(Move.RIGHT_TO_LEFT),
				TowerOfHanoi.solve(1, Peg.RIGHT, Peg.LEFT));
		assertEquals(List.of(Move.RIGHT_TO_MIDDLE),
				TowerOfHanoi.solve(1, Peg.RIGHT, Peg.MIDDLE));
	}

	@Test
	void testSolveRecursiveCase() {
		// 2 disks
		assertEquals(List.of(
				Move.LEFT_TO_RIGHT,
				Move.LEFT_TO_MIDDLE,
				Move.RIGHT_TO_MIDDLE),
				TowerOfHanoi.solve(2, Peg.LEFT, Peg.MIDDLE));

		assertEquals(List.of(
				Move.LEFT_TO_MIDDLE,
				Move.LEFT_TO_RIGHT,
				Move.MIDDLE_TO_RIGHT),
				TowerOfHanoi.solve(2, Peg.LEFT, Peg.RIGHT));

		assertEquals(List.of(
				Move.MIDDLE_TO_RIGHT,
				Move.MIDDLE_TO_LEFT,
				Move.RIGHT_TO_LEFT),
				TowerOfHanoi.solve(2, Peg.MIDDLE, Peg.LEFT));

		assertEquals(List.of(
				Move.MIDDLE_TO_LEFT,
				Move.MIDDLE_TO_RIGHT,
				Move.LEFT_TO_RIGHT),
				TowerOfHanoi.solve(2, Peg.MIDDLE, Peg.RIGHT));

		assertEquals(List.of(
				Move.RIGHT_TO_MIDDLE,
				Move.RIGHT_TO_LEFT,
				Move.MIDDLE_TO_LEFT),
				TowerOfHanoi.solve(2, Peg.RIGHT, Peg.LEFT));

		assertEquals(List.of(
				Move.RIGHT_TO_LEFT,
				Move.RIGHT_TO_MIDDLE,
				Move.LEFT_TO_MIDDLE),
				TowerOfHanoi.solve(2, Peg.RIGHT, Peg.MIDDLE));

		// 3 disks
		List<Move> expectedMoves = List.of(
				Move.LEFT_TO_RIGHT,   // 2 disks from LEFT to MIDDLE
				Move.LEFT_TO_MIDDLE,
				Move.RIGHT_TO_MIDDLE,
				Move.LEFT_TO_RIGHT,   // 1 disk from LEFT to RIGHT
				Move.MIDDLE_TO_LEFT,  // 2 disks from MIDDLE to RIGHT
				Move.MIDDLE_TO_RIGHT,
				Move.LEFT_TO_RIGHT);
		List<Move> actualMoves = TowerOfHanoi.solve(3, Peg.LEFT, Peg.RIGHT);
		assertEquals(expectedMoves, actualMoves);

		// Check the solution by applying the moves.
		TowerOfHanoi game = new TowerOfHanoi(3, Peg.LEFT);
		String string = "" +
				"  LEFT: [3, 2, 1]" + System.lineSeparator() +
				"MIDDLE: []" + System.lineSeparator() +
				" RIGHT: []";
		assertEquals(string, game.toString());
		for (Move move : actualMoves) {
			game.moveDisk(move);
		}
		string = "" +
				"  LEFT: []" + System.lineSeparator() +
				"MIDDLE: []" + System.lineSeparator() +
				" RIGHT: [3, 2, 1]";
		assertEquals(string, game.toString());

		// Now move the disks from the right to the middle peg.
		expectedMoves = List.of(
				Move.RIGHT_TO_MIDDLE,  // 2 disks from RIGHT to LEFT
				Move.RIGHT_TO_LEFT,
				Move.MIDDLE_TO_LEFT,
				Move.RIGHT_TO_MIDDLE,  // 1 disk from RIGHT to MIDDLE
				Move.LEFT_TO_RIGHT,    // 2 disks from LEFT to MIDDLE
				Move.LEFT_TO_MIDDLE,
				Move.RIGHT_TO_MIDDLE);
		actualMoves = TowerOfHanoi.solve(3, Peg.RIGHT, Peg.MIDDLE);
		assertEquals(expectedMoves, actualMoves);

		for (Move move : actualMoves) {
			game.moveDisk(move);
		}
		string = "" +
				"  LEFT: []" + System.lineSeparator() +
				"MIDDLE: [3, 2, 1]" + System.lineSeparator() +
				" RIGHT: []";
		assertEquals(string, game.toString());
	}
}
