package algorithm.palindromepermutation;

import java.util.Optional;
import java.util.Set;

/**
 * Palindrome permutation. Chapter 1 , problem 1.4
 * <p>
 * Although the method worked I could have:
 * - Checked only if I have only one odd number of characters. (I checked wheter the length is odd or not, which doesn't matter.)
 * - Can get my response as I go through the string. Although I still have O(n) efficiency, I go through the string only once and get my response at the end.
 * - Can also use a bit vector to reduce space complexity. No need for a table of frequency.
 * - Problem cleary states that the permutations should not be case sensitivy. for the freq vector, i can use a length of 26 characters maximum.
 *
 * @author Mihai Alexandru
 */
public class PalindromePermutation {

    private String str;

    private int length;

    public PalindromePermutation(String str) {
        super();
        this.str = str;
    }

    public boolean isPalindromPermutationFirstDraft() {
        int[] freq = computeFreq(Set.of(' '));
        if (this.length % 2 == 0) {
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] % 2 != 0) {
                    return false;
                }
            }
        } else {
            boolean oddFound = false;
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] % 2 != 0 && oddFound) {
                    return false;
                } else if (freq[i] % 2 != 0 && !oddFound) {
                    oddFound = true;
                }
            }
        }
        return true;
    }

    /**
     * Here, I check only for odd frequency. Only one odd frequency is allowed (at most a single character in the middle).
     *
     * @return
     */
    public boolean isPalindromePermutationSolutionA() {
        int[] freq = computeFreq(Set.of(' '));
        boolean oddFound = false;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] % 2 == 1) {
                if (oddFound) {
                    return false;
                }
                oddFound = true;
            }
        }
        return true;
    }

    /**
     * Here I check as I go.
     *
     * @return
     */
    public boolean isPalindromePermutationSolutionB() {
        int oddCount = 0;

        int freqTableSize = Character.getNumericValue('z') - Character.getNumericValue('a');
        int[] freq = new int[freqTableSize];

        for (Character c : str.toCharArray()) {
            int value = getNumericValue(c).orElse(-1);
            if (value > -1) {
                freq[value]++;
                if (freq[value] % 2 == 1) {
                    oddCount++;
                } else {
                    oddCount--;
                }
            }
        }

        return oddCount <= 1;
    }

    /**
     * @return
     */
    public boolean isPalindromePermutationSolutionC() {
        int bitVector = 0;
        for (Character c : str.toCharArray()) {
            int value = getNumericValue(c).orElse(-1);
            if (value > -1) {
                bitVector ^= (1 << value);
            }
        }
        return checkBitVector(bitVector);
    }

    // ----------------------------------------------------------

    private int[] computeFreq(Set<Character> ignoredCharacters) {
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            char c = Character.toLowerCase(str.charAt(i));
            if (ignoredCharacters.contains(c)) {
                continue;
            }
            int charCode = (int) c;
            freq[charCode]++;
            this.length++;
        }
        return freq;
    }

    private int[] computeIgnoreCaseFreq() {
        int freqTableSize = Character.getNumericValue('z') - Character.getNumericValue('a');
        int[] freq = new int[freqTableSize];
        for (Character c : str.toCharArray()) {
            getNumericValue(c).ifPresent(value -> freq[value]++);
        }
        return freq;
    }

    private Optional<Integer> getNumericValue(char c) {
        Character toLowerCase = Character.toLowerCase(c);
        int value = Character.getNumericValue(toLowerCase) - Character.getNumericValue('a');
        int max = Character.getNumericValue('z') - Character.getNumericValue('a');
        return value < 0 || value > max ? Optional.empty() : Optional.of(value);
    }

    private boolean checkBitVector(int bitVector) {
        int mask = bitVector - 1;
        return (bitVector & mask) == 0;
    }

}
