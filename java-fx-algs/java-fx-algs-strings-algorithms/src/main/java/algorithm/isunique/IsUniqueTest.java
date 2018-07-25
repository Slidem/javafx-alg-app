package algorithm.isunique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsUniqueTest {

    @ParameterizedTest
    @MethodSource(value = "isUniqueBitVector")
    public void testIsUniqueUsingBitVector(String str) {
        assertTrue(new IsUnique(str).isUniqueUsingBitVector());
    }

    @ParameterizedTest
    @MethodSource(value = "isUniqueBooleanVector")
    public void testIsUnqiueUsingBooleanVector(String str) {
        assertTrue(new IsUnique(str).isUnique());
    }

    @ParameterizedTest
    @MethodSource(value = "notUniqueBitVector")
    public void testNotUniqueUsingBitVector(String str) {
        assertFalse(new IsUnique(str).isUniqueUsingBitVector());
    }

    @ParameterizedTest
    @MethodSource(value = "notUniqueBooleanVector")
    public void testNotUnqiueUsingBooleanVector(String str) {
        assertFalse(new IsUnique(str).isUnique());
    }


    private static Stream<Arguments> isUniqueBitVector() {
        return Stream.of(
                Arguments.of("abc"),
                Arguments.of("koiljmnhb"),
                Arguments.of("this"),
                Arguments.of("a"),
                Arguments.of("awerl"),
                Arguments.of("loi"));
    }

    private static Stream<Arguments> isUniqueBooleanVector() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("sda "),
                Arguments.of("!@#%^Aab"),
                Arguments.of("*91"),
                Arguments.of("sd"));
    }

    private static Stream<Arguments> notUniqueBitVector() {
        return Stream.of(
                Arguments.of("aa"),
                Arguments.of("bbb"),
                Arguments.of("aba"),
                Arguments.of("lol"),
                Arguments.of("juju"),
                Arguments.of("qoplsdo"));
    }

    private static Stream<Arguments> notUniqueBooleanVector() {
        return Stream.of(
                Arguments.of("asdasd"),
                Arguments.of("aa aaa"),
                Arguments.of("!!@#$$"),
                Arguments.of(" S  SSdd"),
                Arguments.of("asdasd"),
                Arguments.of("ggg"));
    }

}
