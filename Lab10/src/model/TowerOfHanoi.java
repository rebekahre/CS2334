package model;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class TowerOfHanoi {

	private Map<Peg, Deque<Integer>> diskStacks = new EnumMap<>(Peg.class);

	public TowerOfHanoi(int numDisks, Peg start) {
		if (numDisks <= 0) {
			throw new IllegalArgumentException();
		}
		if (start == null) {
			throw new NullPointerException();
		}

		for (Peg peg : Peg.values()) {
			diskStacks.put(peg, new LinkedList<>());
		}
		Deque<Integer> startStack = diskStacks.get(start);
		for (int disk = numDisks; disk > 0; --disk) {
			startStack.push(disk);
		}
	}

	public Deque<Integer> getDiskStack(Peg peg) {
		if (peg == null) {
			throw new NullPointerException();
		}
		return new LinkedList<>(diskStacks.get(peg));
	}

	public void moveDisk(Move move) {
		if (move == null) {
			throw new NullPointerException();
		}

		Deque<Integer> fromStack = diskStacks.get(move.from);
		if (fromStack.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Deque<Integer> toStack = diskStacks.get(move.to);
		if (!toStack.isEmpty() && fromStack.peek() > toStack.peek()) {
			throw new IllegalArgumentException();
		}

		toStack.push(fromStack.pop());
	}

	public static List<Move> solve(int numDisks, Peg start, Peg end) {
		// Edge cases
		if (numDisks < 0) {
			throw new IllegalArgumentException();
		}
		if (start == null || end == null) {
			throw new NullPointerException();
		}
		if (numDisks == 0 || start == end) {
			return new LinkedList<>();
		}

		// Base case
		if (numDisks == 1) {
			List<Move> moves = new LinkedList<>();
			moves.add(Move.move(start, end));
			return moves;
		}

		// Recursive case
		Peg otherPeg = Peg.other(start, end);
		List<Move> moves = solve(numDisks - 1, start, otherPeg);
		moves.add(Move.move(start, end));
		moves.addAll(solve(numDisks - 1, otherPeg, end));
		return moves;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(System.lineSeparator());
		joiner.add("  LEFT: " + reverseToString(diskStacks.get(Peg.LEFT)));
		joiner.add("MIDDLE: " + reverseToString(diskStacks.get(Peg.MIDDLE)));
		joiner.add(" RIGHT: " + reverseToString(diskStacks.get(Peg.RIGHT)));
		return joiner.toString();
	}

	private static String reverseToString(Deque<Integer> deque) {
		StringJoiner joiner = new StringJoiner(", ", "[", "]");
		Iterator<Integer> iterator = deque.descendingIterator();
		while (iterator.hasNext()) {
			joiner.add(Integer.toString(iterator.next()));
		}
		return joiner.toString();
	}
}
