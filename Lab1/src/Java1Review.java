import java.util.ArrayList;
import java.util.Arrays;

//I have Worked with Maria Doan,  Minh Tran, and Chris White.
//They helped me fix some problems when I got stuck in between. 

public class Java1Review {

	public static void main(String[] args) {
		
	}
	public static String main(String args2) {
		return ("Overloaded main method was passed \"" + args2 + "\".");
	}
		
	
		public static double divide(double numerator, double denominator) {
			if(denominator == 0.0) {
				return Double.POSITIVE_INFINITY;
			}
			
			double fraction = numerator / denominator; 
			return fraction;
			
			}
		
		
		public static int divide(int numerator, int denominator) throws ArithmeticException {
			int answer = numerator / denominator;
			return answer;
			
		}
		
		
		public static boolean isDivisibleBy7(int number) {
			return ((number%7) == 0);
			}
		
		
		public static int findMin(int num1, int num2, int num3) {
			return (Math.min(Math.min(num1, num2), num3));
				
			}	
		
		
		public static int findMin(int[] array) {
			int min = array[0];
			
			for(int i=0; i<array.length; ++i) {
				if(array[i] < min) {
					min = array[i];
				}
			}
			return min;
		}
	
		
		public static double average(int[] array) {
			double sum = 0.0;
			
			for(int i=0; i<array.length; ++i) {
				sum = sum + array[i];
			}
			double average = sum / (double)array.length;
			
			return average;
			}
		
		
		public static void toLowerCase(String[] strings) {
			
			for(int i=0; i<strings.length; ++i) {
				String tempWord = strings[i];
				tempWord = tempWord.toLowerCase();
				strings[i] = tempWord;
				}
		}
	
		
		public static String[] toLowerCaseCopy(String[] strings) {
			
			String[] newArray = Arrays.copyOf(strings, strings.length);
			
			for(int i=0; i<newArray.length; ++i) {
				String tempWord = newArray[i];
				tempWord = tempWord.toLowerCase();
				newArray[i] = tempWord;
				}
			
			return newArray;
		}
			
			
		public static void removeDuplicates(int[] array) {
			
			int replacement = 0;
			boolean duplicateFound = false;
			
			for(int outerIndex=0; outerIndex < array.length; ++outerIndex) {
				int firstNum = array[outerIndex];
				
				for(int innerIndex=outerIndex+1; innerIndex < array.length; ++innerIndex) {
					int secondNum = array[innerIndex];
					if(firstNum == secondNum) {
						duplicateFound = true;
						array[innerIndex] = replacement;
					}
				}
				if(duplicateFound) {
					array[outerIndex] = replacement;
					duplicateFound = false;
				}
				}
				}
		}
		
		
		
		
		
		
		
	


