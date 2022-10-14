import java.util.*;

/**
 * This class represents a row of cells at a fixed time. Each Generation
 * encapsulates a boolean array that represents the cell states.
 * 
 * @author Rebekah Lee
 * @version 0.1
 */
public class Generation {

	/** Boolean array, includes the states of each cell. */
	private boolean[] cellStates;
	/** Total number of cells */
	private static final int NUMCELLS = 8;

	/**
	 * Create a Generation with one cell for each element in the given array. The
	 * state of each cell is specified by the value of the corresponding element. If
	 * the array is empty or the method is given a null reference, create a
	 * Generation with one cell in the false state.
	 * 
	 * @param states the status of each cell.
	 */
	public Generation(boolean... states) {
		boolean[] copy;
		if ((states == null) || (states.length == 0)) {
			copy = new boolean[1];
			copy[0] = false;
		} else {
			copy = new boolean[states.length];
			for (int i = 0; i < copy.length; ++i) {
				copy[i] = states[i];
			}
		}
		cellStates = copy;
	}

	/**
	 * Create a Generation with one cell for each character in the given String. If
	 * a character is equal to trueSymbol, the state of the corresponding cell is
	 * true; otherwise, the state is false. If the String is empty ("") or the
	 * method is given a null reference, create a Generation with one cell in the
	 * false state.
	 * 
	 * @param states     represents true or false.
	 * @param trueSymbol represents true.
	 */
	public Generation(String states, char trueSymbol) {
		boolean[] newArray;
		if ((states == null) || (states == "")) {
			newArray = new boolean[1];
			newArray[0] = false;
		} else {
			newArray = new boolean[states.length()];
			for (int i = 0; i < states.length(); ++i) {
				char answer = states.charAt(i);
				if (answer == trueSymbol) {
					newArray[i] = true;
				} else {
					newArray[i] = false;
				}
			}
		}
		cellStates = newArray;
	}

	/**
	 * Return the state of the cell with the given index.
	 * 
	 * @param idx given index.
	 * @return the state of the given cell.
	 */
	public boolean getState(int idx) {
		return this.cellStates[idx];
	}

	/**
	 * Return an array with all of the cell states.
	 * 
	 * @return the copied array.
	 */
	public boolean[] getStates() {
		return Arrays.copyOf(cellStates, cellStates.length);
	}

	/**
	 * Return a String representation of the cell states using falseSymbol and
	 * trueSymbol as the symbols for false and true, respectively.
	 * 
	 * @param falseSymbol represents false.
	 * @param trueSymbol  represents true.
	 * @return a String with the cell states.
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		String finalString = "";
		for (int i = 0; i < cellStates.length; ++i) {
			if (cellStates[i] == false) {
				finalString += falseSymbol;
			} else {
				finalString += trueSymbol;
			}
		}
		return finalString;
	}

	/**
	 * Return the number of cells.
	 * 
	 * @return total number of cells.
	 */
	public int size() {
		return cellStates.length;
	}
}
