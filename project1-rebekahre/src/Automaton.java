import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

//I got help from Cole Hoffman. 

/**
 * This class represents an ECA. Each Automaton encapsulates a Rule and an
 * ArrayList of Generations. The ArrayList is ordered by time, with the first
 * element being the initial Generation and the last being the current
 * Generation. Each Automaton also contains two public fields, falseSymbol and
 * trueSymbol, that store the characters used to represent the cell states in
 * the output of toString.
 * 
 * @author Rebekah Lee
 * @version 0.1
 */
public class Automaton {

	/** rule from Rule class */
	private Rule rule;
	/** generations array list */
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	/** character false symbol */
	public char falseSymbol;
	/** character true symbol */
	public char trueSymbol;
	/** displayed lines */
	private static final int LINES = 3;

	/**
	 * Create an Automaton from a rule number and an initial Generation. Initialize
	 * falseSymbol and trueSymbol to '0' and '1', respectively.
	 * 
	 * @param ruleNum the rule number.
	 * @param initial the initial Generation.
	 */
	public Automaton(int ruleNum, Generation initial) {
		falseSymbol = '0';
		trueSymbol = '1';
		rule = new Rule(ruleNum);
		generations.add(initial);
	}

	/**
	 * Create an Automaton from the information in a text file.
	 * 
	 * @param filename the name of the file.
	 * @throws FileNotFoundException in case the file is not found.
	 */
	public Automaton(String filename) throws FileNotFoundException {
		// reading first line
		File file = new File(filename);
		Scanner read = new Scanner(file);
		String[] array = new String[LINES];

		for (int i = 0; i < LINES; ++i) {
			array[i] = read.nextLine();
		}

		// reading second line
		rule = new Rule(Integer.parseInt(array[0]));
		String tempString = array[1];
		falseSymbol = (tempString.substring(0, 1)).charAt(0);
		trueSymbol = (tempString.substring(2, 3)).charAt(0);

		// reading third line into answers
		tempString = array[2];
		boolean[] answers = new boolean[tempString.length()];
		Generation temp = new Generation(array[2], trueSymbol);
		generations.add(temp);

	}

	/**
	 * Evolve the Automaton a given number of steps, appending each successive
	 * Generation to the ArrayList. If the number of steps is less than or equal to
	 * 0, leave the Automaton unchanged.
	 * 
	 * @param numSteps the number of steps.
	 */
	public void evolve(int numSteps) {
		if (numSteps <= 0) {
			return;
		}
		for (int i = 0; i < numSteps; ++i) {
			Generation lastIndex = generations.get(generations.size() - 1);
			generations.add(rule.evolve(lastIndex));
		}
	}

	/**
	 * Return the Generation produced during the given time step. If the Automaton
	 * has not evolved this far, first call evolve with the necessary number of
	 * additional steps.
	 * 
	 * @param stepNum number of steps.
	 * @return the Generation in the given time step.
	 */
	public Generation getGeneration(int stepNum) {
		if (generations.size() - 1 <= stepNum) {
			evolve(stepNum - getTotalSteps());
		}
		return generations.get(stepNum);
	}

	/**
	 * Return the Wolfram code for the rule that governs the ECA.
	 * 
	 * @return getRuleNum from rule's class.
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
	}

	/**
	 * Return the total number of steps that the ECA has evolved
	 * 
	 * @return the number of generations minus 1.
	 */
	public int getTotalSteps() {
		return generations.size() - 1;
	}

	/**
	 * Save the output of toString to a file with the given name. Overwrite the
	 * content of the file if it already exists.
	 * 
	 * @param filename the name of the file.
	 * @throws IOException when IO operation fails.
	 */
	public void saveEvolution(String filename) throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(toString());
			writer.close();
		} catch (IOException exception) {
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments.
	 * @throws IOException when IO operation fails.
	 */
	public static void main(String[] args) throws IOException {
		Automaton eca = new Automaton(110, new Generation("00000000000000000000100000000000000000000", '1'));
		eca.evolve(20);
		eca.saveEvolution("rule 110.txt");

	}

	/**
	 * Return a String representation of the full evolution of the Automaton. The
	 * String consists of the representations of all the Generations joined together
	 * by line separators.
	 */
	public String toString() {
		String returnMe = "";
		String[] storage = new String[generations.size()];
		for (int i = 0; i < storage.length; ++i) {
			// get true and false from each generations.
			Generation tempGen = generations.get(i);
			boolean[] states = tempGen.getStates();
			// convert each true and false to true and false symbol.
			char[] temp = new char[states.length];
			for (int j = 0; j < temp.length; ++j) {
				if (states[j]) {
					temp[j] = trueSymbol;
				} else {
					temp[j] = falseSymbol;
				}
				// convert each symbol into one string.
				storage[i] = new String(temp);
			}
		}
		// add all generations strings into one large string.
		StringJoiner joiner = new StringJoiner(System.lineSeparator());
		for (int i = 0; i < storage.length; ++i) {
			joiner.add(storage[i]);
		}
		// return large string.
		return joiner.toString();
	}
}
