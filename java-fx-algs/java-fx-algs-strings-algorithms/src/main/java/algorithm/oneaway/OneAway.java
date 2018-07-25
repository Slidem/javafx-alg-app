package algorithm.oneaway;

public class OneAway {
	
	private String str1;
	
	private String str2;

	public OneAway(String str1, String str2) {
		this.str1 = str1;
		this.str2 = str2;
	}
	
	public boolean isOneWay() {
		
		int[] str1Freq = new int[256];
		int[] str2Freq = new int[256];
		
		computeFreq(str1, str1Freq);
		computeFreq(str2, str2Freq);
		
		int maxDifference = str1.length() == str2.length() ? 2 : 1;
		
		return isWithMaxDifference(str1Freq, str2Freq, maxDifference);		
	}
	
	private void computeFreq(String str, int[] freq) {
		for(int i = 0; i<str.length(); i++) {
			char c = str.charAt(i);
			int charCode = (int)c;
			freq[charCode]++;
		}
	}
	
	private boolean isWithMaxDifference(int[] freq1, int[] freq2, int maxDifference) {
		int diff = 0;
		for(int i=0; i<freq1.length; i++) {
			diff += Math.abs(freq1[i] - freq2[i]);
			if(diff > maxDifference) {
				return false;
			}
		}
		return true;
	}
	

}
