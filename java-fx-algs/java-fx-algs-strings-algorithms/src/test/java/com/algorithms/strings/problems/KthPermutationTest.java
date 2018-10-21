package com.algorithms.strings.problems;

import com.algorithms.strings.problems.kthpermutation.KthPermutation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Mihai Alexandru
 * @date 11.08.2018
 */
public class KthPermutationTest {

    @Test
    public void testKthPermutation() {

        //given
        KthPermutation kp = KthPermutation.createWithNoObserver(new int[]{2, 3, 4, 5, 6, 1});

        //expect
        Assertions.assertEquals(5, kp.getKthPermutation());
    }

}
