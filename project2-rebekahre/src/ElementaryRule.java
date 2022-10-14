
public class ElementaryRule extends Rule{

	private final int ruleNum;
	private char[] charArr;
	private static final int NUMCELLS = 8;
	private int elements = 3;

	public ElementaryRule(int ruleNum) throws RuleNumException{
		super(ruleNum); 

		if (ruleNum > 255 || ruleNum <0) {
			throw new RuleNumException(0, 255);  
		}

		this.ruleNum = ruleNum;
		wolframCode();
	}


	//Count the numbers padded and show how the Wolfram Code works.
	private void wolframCode() {
		char[] binaryArr = Integer.toBinaryString(getRuleNum()).toCharArray();
		charArr = new char[NUMCELLS];

		int numPadded = charArr.length - binaryArr.length;
		for (int i = 0; i < numPadded; ++i) {
			charArr[i] = '0';
		}

		int count = 0; 
		for (int i = numPadded; i < charArr.length; ++i) {
			// start from the beginning of binary array which is 0.
			System.out.println(charArr[i] + " " + binaryArr[count]);
			charArr[i] = binaryArr[count]; 
			++count;
		}
	}


	@Override
	public boolean evolve(boolean[] neighborhood) {
		//		 black=1=true
		//       white=0=false
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


	@Override
	boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] genBooleans = gen.getStates();
		boolean[] states = new boolean[3];
		//Use circular boundary conditions
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


	@Override
	public String getRuleTable(char falseSymbol, char trueSymbol) {
		StringBuilder builder = new StringBuilder();
		char[] temp = new char[elements];
		//generate 111, 110, 101 ...
		for(int i=0; i<charArr.length; ++i) {
			temp=convertBinary(7-i); //count down from 7 then convert to binary.

			for(int j=0; j<temp.length; ++j) { // compare binary to see if T or F. 
				if(temp[j]==('0')) {
					builder.append(falseSymbol);
				}
				else {
					builder.append(trueSymbol);
				}
			} 
			builder.append(" "); //space between 111, 110 and so on.
		}

		String topLine = builder.toString();
		topLine = topLine.substring(0, topLine.length()-1); // remove last one space.

		StringBuilder sb = new StringBuilder();
		sb.append(topLine);
		sb.append(System.lineSeparator()); 
		sb.append(" ");

		for(int i=0; i<charArr.length; ++i) {
			if(charArr[i]==('0')) {
				sb.append(falseSymbol);
			} 
			else {
				sb.append(trueSymbol);
			}
			sb.append("   ");
		} 
		String answer = sb.toString();
		answer = answer.substring(0, answer.length()-2); // remove last two spaces

		return answer;
	}


	public char[] convertBinary(int num) {
		char[] binaryArr = Integer.toBinaryString(num).toCharArray();
		char[] charArr1 = new char[elements];

		int numPadded = charArr1.length - binaryArr.length;
		for (int i = 0; i < numPadded; ++i) {
			charArr1[i] = '0';
		}

		int count = 0;
		for (int i = numPadded; i < charArr1.length; ++i) {
			// start from the beginning of binary array which is 0.
			charArr1[i] = binaryArr[count];
			++count;
		}
		return charArr1;
	}
}
