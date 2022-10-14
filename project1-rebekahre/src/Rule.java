//I got help from Cole Hoffman.

/**
 * This class represents any one of the 256 possible rules that govern the
 * evolution of ECAs. The class has methods that take the cell states of an ECA
 * at one time and return the states after the next time step.
 * 
 * @author Rebekah Lee
 * @version 0.1
 */
public class Rule {

	/** number of rules. */
	private final int ruleNum;
	/** char array that holds the numbers. */
	private char[] charArr;
	/** Total number of cells. */
	private static final int NUMCELLS = 8;

	/**
	 * Create a Rule corresponding to the given Wolfram code. If ruleNum is less
	 * than 0 or greater than 255, use the closest valid rule number instead.
	 * 
	 * @param ruleNum is the number of possible rules.
	 */
	public Rule(int ruleNum) {
		if (ruleNum > 255) {
			ruleNum = 255;
		} else if (ruleNum < 0) {
			ruleNum = 0;
		}

		this.ruleNum = ruleNum;
		wolframCode();
	}

	/*
	 * Count the numbers padded and show how the Wolfram Code works.
	 */
	private void wolframCode() {
		char[] binaryArr = Integer.toBinaryString(ruleNum).toCharArray();
		charArr = new char[NUMCELLS];

		int numPadded = charArr.length - binaryArr.length;
		for (int i = 0; i < numPadded; ++i) {
			charArr[i] = '0';
		}

		int count = 0;
		for (int i = numPadded; i < charArr.length; ++i) {
			// start from the beginning of binary array which is 0.
			charArr[i] = binaryArr[count];
			++count;
		}
	}

	/**
	 * Apply the rule to the given Generation and return the next Generation.
	 * 
	 * @param gen the Generation.
	 * @return the next Generation.
	 */
	public Generation evolve(Generation gen) {
		boolean[] generation = gen.getStates();
		boolean[] nextGen = new boolean[generation.length];

		for (int i = 0; i < generation.length; ++i) {
			nextGen[i] = evolve(getNeighborhood(i, gen));
		}
		return new Generation(nextGen);
	}

	/**
	 * Return the next state of a cell with the given neighborhood of states. The
	 * input is an array that contains the states of the left neighbor, the cell
	 * itself, and the right neighbor (in that order).
	 * 
	 * @param neighborhood is the surrounding cell.
	 * @return the next state of a cell.
	 */
	public boolean evolve(boolean[] neighborhood) {
		// black=1=true
		// white=0=false
		String status = "";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < neighborhood.length; ++i) {
			if (neighborhood[i] == false) {
				sb.append(0);
			} else {
				sb.append(1);
			}
		}

		status = sb.toString();
		// Parse the String into an Integer.
		int result = Integer.parseInt(status, 2);
		return (charArr[charArr.length - result - 1] == '1');
	}

	/**
	 * Return the state of the cell with the given index in the given Generation
	 * along with the states of its two nearest neighbors. If the index corresponds
	 * to the first or last cell, use circular boundary conditions to get the state
	 * of its left or right neighbor.
	 * 
	 * @param idx is the given index.
	 * @param gen is the given Generation.
	 * @return boolean array of states.
	 */
	public static boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] genBooleans = gen.getStates();
		boolean[] states = new boolean[3];
		// Use circular boundary conditions
		if (genBooleans.length == 1) {
			states[0] = genBooleans[0];
			states[1] = genBooleans[0];
			states[2] = genBooleans[0];
		} else if (idx - 1 < 0) {
			states[0] = genBooleans[genBooleans.length - 1];
			states[1] = genBooleans[idx];
			states[2] = genBooleans[idx + 1];
		} else if (idx + 1 >= genBooleans.length) {
			states[2] = genBooleans[0];
			states[1] = genBooleans[idx];
			states[0] = genBooleans[idx - 1];
		} else
		// if no issues on circular boundary conditions, then
		// assign in order.
		if ((idx - 1 >= 0) && (idx + 1 < genBooleans.length)) {
			states[0] = genBooleans[idx - 1];
			states[1] = genBooleans[idx];
			states[2] = genBooleans[idx + 1];
		}
		return states;
	}

	/**
	 * Return the rule's Wolfram code.
	 * 
	 * @return the rule number.
	 */
	public int getRuleNum() {
		return ruleNum;
	}
}
