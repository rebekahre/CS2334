public abstract class Rule {

	private final int ruleNum;
	private char[] charArr;

	protected Rule(int ruleNum) {
		this.ruleNum=ruleNum;
	}


	public Generation evolve(Generation gen) {
		boolean[] generation = gen.getStates();
		boolean[] nextGen = new boolean[generation.length];

		for (int i = 0; i < generation.length; ++i) {
			nextGen[i] = evolve(getNeighborhood(i, gen));
		}
		return new Generation(nextGen);
	}


	public abstract boolean evolve(boolean[] neighborhood);


	abstract boolean[] getNeighborhood(int idx, Generation gen);


	public int getRuleNum() {
		return ruleNum;
	}

	
	public abstract String getRuleTable(char falseSymbol, char trueSymbol);

}


