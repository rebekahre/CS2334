
import java.util.Arrays;

public class TotalisticRule extends Rule {
	private boolean[] ruleStates;
	private int ruleNum;
	private char[] charArr;
	private int numElements = 5;

	protected TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);

		final int max = 63; 
		final int min = 0;

		if(ruleNum<min || ruleNum>max) { 
			throw new RuleNumException(min, max);
		}
		this.ruleNum=ruleNum;
		wolframCode();
		toBool();
	}


	//got this idea from Tina Nguyen.
	private void wolframCode() {
		charArr = String.format("%06d", Integer.parseInt(Integer.toBinaryString(ruleNum))).toCharArray();
	}


	//change '1','0' to 'T','F'
	//got help from Chris White.
	private void toBool() {
		ruleStates=new boolean[charArr.length];
		for(int i=0; i<this.charArr.length; ++i) {
			if(this.charArr[i] == '1') {
				ruleStates[i] = true;
			}
			else {
				ruleStates[i] = false;
			}
		}
	}


	@Override
	public boolean evolve(boolean[] neighborhood) {
		//		 black=1=true
		//       white=0=false

		int trues=0;
		for (int i = 0; i < neighborhood.length; ++i) {
			if (neighborhood[i] == true) {
				++trues;
			}
		}
		//the first number added will eventually be located in the end.
		if(trues == 0) {
			return ruleStates[5]; 
		}
		else if (trues == 1) {
			return ruleStates[4];
		}
		else if(trues == 2) {
			return ruleStates[3];
		}
		else if(trues == 3) {
			return ruleStates[2];
		}
		else if(trues==4) {
			return ruleStates[1];
		}
		else if (trues == 5){
			return ruleStates[0];
		}
		return false;
	}


	//Got help from Chris White. Maria Doan also participated shortening the code. 
	@Override
	boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] genBooleans = new boolean[gen.getStates().length*3];
		boolean[] states = new boolean[numElements]; //There are 5 elements
		boolean [] original = gen.getStates();
		if(gen.getStates().length == 1)
		{
			Arrays.fill(states, gen.getStates()[0]); //all the states will be 0 since the length is 1.
			return states;
		}  
		else { 
			//change the first two and the last two indexes according to 
			//the circular boundary conditions.
			System.arraycopy(original, 0, genBooleans, 0, original.length);
			System.arraycopy(original, 0, genBooleans, original.length, original.length);
			System.arraycopy(original, 0, genBooleans, original.length *2, original.length);
			int count = 0;

			for(int index = idx-2; count<states.length; ++index, ++count) {
				states[count] = genBooleans[original.length+index];
			}
			return states;
		}
	}


	@Override
	public String getRuleTable(char falseSymbol, char trueSymbol)  {
		//top line
		StringBuilder sb = new StringBuilder();
		sb.append("5 4 3 2 1 0");
		sb.append(System.lineSeparator());  
		String topLine = sb.toString();
		
		//bottom line
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<charArr.length; ++i) {
			if(charArr[i] == '0') {
				builder.append(falseSymbol);
			}
			else {
				builder.append(trueSymbol);
			}
			builder.append(" ");
		}
		String bottomLine = builder.toString();

		return (topLine + bottomLine.trim());
	} 
}

