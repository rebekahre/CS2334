import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TowerOfHanoi {
	private Map<Peg, Deque<Integer>> diskStacks = new HashMap<>();

	public TowerOfHanoi(int numDisks, Peg start) {
		if(numDisks <= 0) {
			throw new IllegalArgumentException();
		}
		if(start == null) {
			throw new NullPointerException();
		}

		// map each peg to an empty deque
		diskStacks.put(Peg.LEFT, new ArrayDeque<>(numDisks));
		diskStacks.put(Peg.MIDDLE, new ArrayDeque<>(numDisks));
		diskStacks.put(Peg.RIGHT, new ArrayDeque<>(numDisks));

		// override the start peg
		Deque<Integer> disks = new ArrayDeque<>(numDisks);
		for(int i=0; i<numDisks; ++i) {
			disks.add(i+1);
		}
		diskStacks.put(start, disks);
	}


	public Deque<Integer> getDiskStack(Peg peg) {
		Deque<Integer> copy = new ArrayDeque<Integer>(diskStacks.get(peg));

		if(peg == null) {
			throw new NullPointerException();
		}
		return copy;
	}


	//Baraa Ahmad and Jessica Eaburg helped me with this method.
	public void moveDisk(Move move) {
		if(move.from == null || move.to==null) {
			throw new NullPointerException();
		}

		if(getDiskStack(move.from).isEmpty()) 
			throw new IllegalArgumentException();
	
		if(!(diskStacks.get(move.to).isEmpty())) {
			if(diskStacks.get(move.from).peek() > diskStacks.get(move.to).peek())
				throw new IllegalArgumentException();
		}
		int remove = diskStacks.get(move.from).pop();
		diskStacks.get(move.to).push(remove);			
	}


	public static List<Move> solve(int numDisks, Peg start, Peg end) {
		List<Move> list = new ArrayList<Move>();
		//create middle peg
		if(start != end) {
			Peg middle = Peg.other(start, end);

			if(numDisks < 0) 
				throw new IllegalArgumentException();
			if(start == null || end == null) 
				throw new NullPointerException();
			if(numDisks == 0 )
				list.isEmpty();

			//base case--if there's 1 disk, move it from start to end. 
			if(numDisks == 1) 
				list.add(Move.move(start, end));

			//recursive call
			else if (numDisks>1) {
				//Move smaller disks from the first peg to the middle peg. 
				list.addAll(solve(numDisks-1, start, middle));
				//Move the biggest disk thats left on the first peg to the last peg.
				list.add(Move.move(start, end));
				//then move all the smaller disks from the middle to the end. 
				list.addAll(solve(numDisks-1, middle, end));
			}
		}
		return list;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  LEFT: " + reverse(diskStacks.get(Peg.LEFT)));
		sb.append(System.lineSeparator());
		sb.append("MIDDLE: " + reverse(diskStacks.get(Peg.MIDDLE)));
		sb.append(System.lineSeparator());
		sb.append(" RIGHT: " + reverse(diskStacks.get(Peg.RIGHT)));

		return sb.toString();
	}


	private String reverse(Deque<Integer> value) {
		Object[] arr = value.toArray();
		Collections.reverse(Arrays.asList(arr));
		return Arrays.toString(arr);
	}
}
