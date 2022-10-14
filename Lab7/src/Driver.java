import java.util.ArrayList;
import java.util.Collections;


public class Driver {
	public static void main(String[] args) {
	ArrayList<String> rebekah = new ArrayList<String>();
	
	rebekah.add("a");
	rebekah.add("bb");
	rebekah.add("cc");
	rebekah.add("ddd");
	rebekah.add("eeee");
	
	Collections.sort(rebekah, new StringLengthComparator());
	System.out.println(rebekah);
	}

}
