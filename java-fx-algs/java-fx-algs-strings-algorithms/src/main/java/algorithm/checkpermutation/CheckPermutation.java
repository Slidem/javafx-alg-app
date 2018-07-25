package algorithm.checkpermutation;

import java.util.Arrays;
import java.util.Objects;


/**
 * Chapter 1. Interview Questions 1.2
 * Given two strings, checks if one is a permutation of the other.
 *
 * @author slidem
 *
 */
public class CheckPermutation {
	
	private String strA;
	
	private String strB;

	public CheckPermutation(String strA, String strB) {
		this.strA = Objects.requireNonNull(strA);
		this.strB = Objects.requireNonNull(strB);
	}
	
	public boolean isPermutation() {
		if(strA.length() != strB.length()) {
			return false;
		}
		
		int[] strAFreq = new int[256];
		int[] strBFreq = new int[256];
		
		computeFreq(strA, strAFreq);
		computeFreq(strB, strBFreq);
		
		return Arrays.equals(strAFreq, strBFreq);
	}
	
	private void computeFreq(String str, int[] freq) {
		for(int i = 0; i<str.length(); i++) {
			char c = str.charAt(i);
			int charCode = (int)c;
			freq[charCode]++;
		}
	}
	
	
}
