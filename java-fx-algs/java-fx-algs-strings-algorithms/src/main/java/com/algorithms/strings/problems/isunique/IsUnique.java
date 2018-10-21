package com.algorithms.strings.problems.isunique;

/**
 * Chapter 1. Interview question 1.1
 * Checks if for a given string, all the characters are unique;
 * <p>
 * - Used an int vector instead of a boolean vector (this would reduce my space complexity to 256 bits (32 bytes) instead of (4 x 256) bytes )
 * - Could have used a bit vector.
 * - Should have checked for details -> is the string ecnoded in ASCII or UNICODE. In case of unicode, the memory space should be increased.
 *
 * @author slidem
 */
public class IsUnique {

    private final String str;

    public IsUnique(String str) {
        this.str = str;
    }

    /**
     * This works only for lowercase characters from a - z . We are using an int as a bit vector, thus, an int should have 32 bits.
     *
     * @return
     */
    public boolean isUniqueUsingBitVector() {

        int check = 0;

        for (int i = 0; i < str.length(); i++) {
            int value = str.charAt(i) - 'a';
            int bitValue = 1 << value;
            if ((check & (bitValue)) > 0) {
                return false;
            }
            check |= bitValue;
        }

        return true;

    }

    public boolean isUnique() {
        boolean check[] = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (check[val]) {
                return false;
            }
            check[val] = true;
        }
        return true;
    }

    private int charToAsciiCode(char c) {
        return (int) c;
    }


}
