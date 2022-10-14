import java.util.*;

public class Generation {

	private boolean[] cellStates;

	// Create a Generation with one cell for each element in the given array. 
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


	//Create a Generation with one cell for each character in the given String. 
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


	public boolean getState(int idx) {
		return this.cellStates[idx];
	}


	public boolean[] getStates() {
		return Arrays.copyOf(cellStates, cellStates.length);
	}


	// Return a String representation of the cell states using falseSymbol and
	// trueSymbol as the symbols for false and true, respectively.   
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


	public int size() {
		return cellStates.length;
	}
}
