package algorithm.palindromepermutation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromePermutationTest {

    @ParameterizedTest
    @ValueSource(strings = {"aba", "radar", "aarrd", "Tact Coa", "atco cta"})
    public void testIsPalindrome(String value) {
        assertTrue(new PalindromePermutation(value).isPalindromPermutationFirstDraft());
        assertTrue(new PalindromePermutation(value).isPalindromePermutationSolutionA());
        assertTrue(new PalindromePermutation(value).isPalindromePermutationSolutionB());
        assertTrue(new PalindromePermutation(value).isPalindromePermutationSolutionC());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "Not a palindrome"})
    public void testIsNotPalindrome(String value) {
        assertFalse(new PalindromePermutation(value).isPalindromPermutationFirstDraft());
        assertFalse(new PalindromePermutation(value).isPalindromePermutationSolutionA());
        assertFalse(new PalindromePermutation(value).isPalindromePermutationSolutionB());
        assertFalse(new PalindromePermutation(value).isPalindromePermutationSolutionC());
    }

}
