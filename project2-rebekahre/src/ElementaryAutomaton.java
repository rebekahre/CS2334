import java.io.FileNotFoundException;

public class ElementaryAutomaton extends Automaton {

	public ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		super(ruleNum, initial);
	}


	public ElementaryAutomaton(String filename) throws FileNotFoundException, RuleNumException {
		super(filename);
	}


	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		Rule rule = new ElementaryRule(ruleNum);
		return rule;


	}

}
