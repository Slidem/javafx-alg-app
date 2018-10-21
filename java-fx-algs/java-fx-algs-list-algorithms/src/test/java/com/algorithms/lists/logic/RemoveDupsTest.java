package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class RemoveDupsTest extends NodeTest {

    @ParameterizedTest
    @MethodSource(value = "removeDupsArguments")
    public void testRemoveDups(List<String> duplicates, List<String> expectedValues) {
        testRemoveDups((n) -> RemoveDups.removeDups(n), duplicates, expectedValues);
    }

    @ParameterizedTest
    @MethodSource(value = "removeDupsArguments")
    public void testRemoveDupsUsingNoTemporarryBuffer(List<String> duplicates, List<String> expectedValues) {
        testRemoveDups((n) -> RemoveDups.removeDupsNoBuffer(n), duplicates, expectedValues);
    }

    private void testRemoveDups(Function<Node<String>, Node<String>> resultSupplier, List<String> duplicates, List<String> expectedValues) {
        //given
        Node<String> head = fromStringValues(duplicates);

        //when
        Node<String> result = resultSupplier.apply(head);

        //expect
        Iterator<String> expectedValuesIterator = expectedValues.iterator();
        for (String value : result) {
            assertEquals(value, expectedValuesIterator.next());
        }
    }


    private static Stream<Arguments> removeDupsArguments() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "c"), List.of("a", "b", "c")),
                Arguments.of(List.of("a", "a", "a"), List.of("a")),
                Arguments.of(List.of("a", "b", "b", "c", "d", "d"), List.of("a", "b", "c", "d")));
    }

}
