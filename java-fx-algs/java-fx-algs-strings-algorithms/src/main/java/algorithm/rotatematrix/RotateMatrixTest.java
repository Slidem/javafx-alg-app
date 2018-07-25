package algorithm.rotatematrix;

import algorithm.rotatematrix.RotateMatrix.Pixel;
import javafx.scene.transform.Rotate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotateMatrixTest {

    @ParameterizedTest
    @MethodSource(value = "getSuccessfullMatrix")
    public void testRotateMatrix(Pixel[][] initial, Pixel[][] rotated) {
        //given
        RotateMatrix rotateMatrix = new RotateMatrix(initial);

        //when
        rotateMatrix.rotate();

        //expect
        assertArrayEquals(rotated, rotateMatrix.getMatrix(), "Matrix should be rotated.");
    }

    private static Stream<Arguments> getSuccessfullMatrix() {
        return Stream.of(
                Arguments.of(
                        new Pixel[][]{
                                {Pixel.of("a"), Pixel.of("b")},
                                {Pixel.of("c"), Pixel.of("d")},
                        },
                        new Pixel[][]{
                                {Pixel.of("c"), Pixel.of("a")},
                                {Pixel.of("d"), Pixel.of("b")},
                        }
                ),
                Arguments.of(
                        new Pixel[][]{
                                {Pixel.of("a"), Pixel.of("b"), Pixel.of("c")},
                                {Pixel.of("d"), Pixel.of("e"), Pixel.of("f")},
                                {Pixel.of("g"), Pixel.of("h"), Pixel.of("i")}
                        },
                        new Pixel[][]{
                                {Pixel.of("g"), Pixel.of("d"), Pixel.of("a")},
                                {Pixel.of("h"), Pixel.of("e"), Pixel.of("b")},
                                {Pixel.of("i"), Pixel.of("f"), Pixel.of("c")}
                        }
                ),
                Arguments.of(
                        new Pixel[][]{
                                {Pixel.of("a"), Pixel.of("b"), Pixel.of("c"), Pixel.of("d")},
                                {Pixel.of("e"), Pixel.of("f"), Pixel.of("g"), Pixel.of("h")},
                                {Pixel.of("i"), Pixel.of("j"), Pixel.of("k"), Pixel.of("l")},
                                {Pixel.of("m"), Pixel.of("n"), Pixel.of("o"), Pixel.of("p")}
                        },
                        new Pixel[][]{
                                {Pixel.of("m"), Pixel.of("i"), Pixel.of("e"), Pixel.of("a")},
                                {Pixel.of("n"), Pixel.of("j"), Pixel.of("f"), Pixel.of("b")},
                                {Pixel.of("o"), Pixel.of("k"), Pixel.of("g"), Pixel.of("c")},
                                {Pixel.of("p"), Pixel.of("l"), Pixel.of("h"), Pixel.of("d")}
                        }
                )
        );
    }


}
