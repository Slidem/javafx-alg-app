package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class ReturnKthToLastTest extends NodeTest {

    @ParameterizedTest
    @MethodSource(value = "kthToLast")
    public void testKthToLast(List<String> values, int kth, String expectedValue) {

        //given
        Node<String> head = fromStringValues(values);

        //when
        Node<String> kthNode = ReturnKthToLast.getKthToLast(head, kth);

        //expect
        assertEquals(expectedValue, kthNode.getValue());
    }

    @ParameterizedTest
    @MethodSource(value = "kthToLast")
    public void testKthToLastRecursive(List<String> values, int kth, String expectedValue) {

        //given
        Node<String> head = fromStringValues(values);

        //when
        Node<String> kthNode = ReturnKthToLast.getKthToLastRecursive(head, kth);

        //expect
        assertEquals(expectedValue, kthNode.getValue());
    }


    private static Stream<Arguments> kthToLast() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "c"), 2, "b"),
                Arguments.of(List.of("a"), 1, "a"),
                Arguments.of(List.of("a", "b", "c", "d"), 1, "d"),
                Arguments.of(List.of("a", "b", "c", "d"), 3, "b"),
                Arguments.of(List.of("a", "b", "c", "d"), 4, "a"));

    }
}
