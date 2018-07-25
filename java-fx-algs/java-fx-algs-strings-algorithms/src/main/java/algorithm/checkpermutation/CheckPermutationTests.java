package algorithm.checkpermutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author slidem
 *
 */
public class CheckPermutationTests {
	
	@Test
	public void testValidStrings() {
		
		assertTrue(new CheckPermutation("aaabb", "abbaa").isPermutation());
		assertTrue(new CheckPermutation("aaabb", "bbaaa").isPermutation());
		assertTrue(new CheckPermutation("aaabb", "babaa").isPermutation());
		assertTrue(new CheckPermutation("aaabb", "aabab").isPermutation());
		assertTrue(new CheckPermutation("aaabb", "abaab").isPermutation());
		
	}

}
