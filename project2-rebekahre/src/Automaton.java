import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public abstract class Automaton {
	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	public char falseSymbol;
	public char trueSymbol;
	private static final int LINES = 3;

	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
		falseSymbol = '0';
		trueSymbol = '1';
		rule = createRule(ruleNum);
		generations.add(initial);
	}


	protected Automaton(String filename) throws FileNotFoundException, RuleNumException {
		// reading first line
		File file = new File(filename);
		Scanner read = new Scanner(file);
		String[] array = new String[LINES];

		for (int i = 0; i < LINES; ++i) {
			array[i] = read.nextLine();
		}

		// reading second line
		rule = createRule(Integer.parseInt(array[0]));
		String tempString = array[1];
		falseSymbol = (tempString.substring(0, 1)).charAt(0);
		trueSymbol = (tempString.substring(2, 3)).charAt(0);

		// reading third line into answers
		tempString = array[2];
		boolean[] answers = new boolean[tempString.length()];
		Generation temp = new Generation(array[2], trueSymbol);
		generations.add(temp);
	}


	abstract protected Rule createRule(int ruleNum) throws RuleNumException;


	public void evolve(int numSteps) {
		if (numSteps <= 0) {
			return;
		}
		for (int i = 0; i < numSteps; ++i) {
			Generation lastIndex = generations.get(generations.size() - 1);
			generations.add(rule.evolve(lastIndex));
		}
	}


	public Generation getGeneration(int stepNum) {
		if (generations.size() - 1 <= stepNum) {
			evolve(stepNum - getTotalSteps());
		}
		return generations.get(stepNum);
	}


	public int getRuleNum() {
		return rule.getRuleNum();
	}


	public String getRuleTable() {
		return rule.getRuleTable(falseSymbol, trueSymbol);
	}


	public int getTotalSteps() {
		return generations.size() - 1;
	}


	public void saveEvolution(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(toString());
			writer.close();
		} catch (IOException exception) {
		}
	}


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



