package com.algorithms.strings.problems;

import com.algorithms.strings.problems.zeromatrix.ZeroMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author Mihai Alexandru
 * @date 23.07.2018
 */
public class ZeroMatrixTest {

    @ParameterizedTest
    @MethodSource(value = "zeroMatrix")
    public void testZeroMatrix(int[][] matrix, int[][] expected) {
        //given
        int rows = matrix.length;
        int cols = matrix[0].length;
        ZeroMatrix unit = new ZeroMatrix(matrix, rows, cols);

        //when
        int[][] result = unit.transform();

        //expect
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> zeroMatrix() {
        //@formatter:off
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 1},
                                {1, 0}
                        }, new int[][]{
                                {1, 0},
                                {0, 0}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1},
                                {1, 0, 1}
                        }, new int[][]{
                                {1, 0, 1},
                                {0, 0, 0}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1, 0},
                                {1, 0, 1, 3},
                                {1, 0, 1, 3}
                        }, new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1, 0},
                                {1, 1, 1, 3},
                                {1, 0, 1, 3}
                        }, new int[][]{
                                {0, 0, 0, 0},
                                {1, 0, 1, 0},
                                {0, 0, 0, 0}
                        }),
                Arguments.of(
                        new int[][]{
                                {0, 1, 1, 0},
                                {1, 1, 1, 3},
                                {1, 1, 1, 3}
                        }, new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {0, 1, 1, 0}
                        }),
                Arguments.of(
                        new int[][]{
                                {0, 1, 1, 0},
                                {1, 1, 1, 3},
                                {1, 1, 1, 0},
                                {1, 1, 1, 3}
                        }, new int[][]{
                                {0, 0, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 1, 1, 0}
                        })
        );
        //@formatter:on
    }

}
