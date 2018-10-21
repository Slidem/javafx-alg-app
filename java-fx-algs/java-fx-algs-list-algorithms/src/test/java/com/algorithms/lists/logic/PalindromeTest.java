package com.algorithms.lists.logic;

import com.algorithms.lists.logic.observers.impl.EmptyPalindromeNodeObserverImpl;
import com.algorithms.lists.node.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class PalindromeTest extends NodeTest {

    @ParameterizedTest
    @MethodSource("isPalindrome")
    public void testIsPalindrome(List<String> values) {
        //given
        Node<String> node = fromStringValues(values);
        //when
        boolean result = Palindrome.isPalindromeReverse(node);
        //expect
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("notPalindrome")
    public void testIsNotPalindrome(List<String> values) {
        //given
        Node<String> node = fromStringValues(values);
        //when
        boolean result = Palindrome.isPalindromeReverse(node);
        //expect
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("isPalindrome")
    public void testIsPalindromeIterative(List<String> values) {
        //given
        Node<String> node = fromStringValues(values);
        //when
        boolean result = Palindrome.isPalindromeIterative(node, new EmptyPalindromeNodeObserverImpl());
        //expect
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("notPalindrome")
    public void testIsNotPalindromeIterative(List<String> values) {
        //given
        Node<String> node = fromStringValues(values);
        //when
        boolean result = Palindrome.isPalindromeIterative(node, new EmptyPalindromeNodeObserverImpl());
        //expect
        assertFalse(result);
    }


    private static Stream<Arguments> isPalindrome() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "a")),
                Arguments.of(List.of("a", "a")),
                Arguments.of(List.of("a")),
                Arguments.of(List.of("a", "l", "l", "o", "p", "o", "l", "l", "a")),
                Arguments.of(List.of("a", "b", "b", "a"))
        );
    }

    private static Stream<Arguments> notPalindrome() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "c")),
                Arguments.of(List.of("a", "b")),
                Arguments.of(List.of("a", "c", "l", "o", "p", "o", "l", "a", "a")),
                Arguments.of(List.of("a", "b", "b", "s"))
        );
    }

}
