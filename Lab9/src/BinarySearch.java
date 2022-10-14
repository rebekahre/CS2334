import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearch {

	/**
	 * Search a sorted list of strings using binary search. Return a list of
	 * the indices of the strings checked during the search in the order they
	 * are checked. If the target string is not found, append -1 to the end of
	 * the list. Otherwise, the last element is the index of the target.
	 *
	 * @param strings  the list to be searched
	 * @param target  the string to be searched for
	 * @param fromIdx  the index of the first string in the range of strings to
	 *                 be searched (inclusive)
	 * @param toIdx  the index of the last string in the range of strings to be
	 *               searched (inclusive)
	 * @return a list of the indices of the strings checked during the search.
	 *         If the target is not in the list, the last element is -1.
	 */
	//Chris White helped me with this method.
	public static List<Integer> binarySearch(List<String> strings,
			String target, int fromIdx, int toIdx) {
		//Create the list of indices in the base case. 
		List<Integer> indices = new ArrayList<Integer>();
		int midIdx = (fromIdx + toIdx) / 2;

		//if the target is NOT found, the last element is -1. 
		if(fromIdx > toIdx) {
			indices.add(-1);
			return indices;
		}
		//if midIdx equals target, add to the list and return it.
		if(strings.get(midIdx).equals(target)) {
			indices.add(midIdx);
			return indices;
		}
		
		//Use Recursion
		else {
			//if midIdx is lower than the target, search the upper half of the range.
			if(strings.get(midIdx).compareTo(target) < 0) {
				indices= binarySearch(strings, target, midIdx+1, toIdx);
				//then add the midIdx
				indices.add(0, midIdx);
			}
			else {
				//else, check the lower half.
				indices=binarySearch(strings, target, fromIdx, midIdx-1);
				//add the midIdx.
				indices.add(0, midIdx);
			}
		}
		return indices;
	}
}
