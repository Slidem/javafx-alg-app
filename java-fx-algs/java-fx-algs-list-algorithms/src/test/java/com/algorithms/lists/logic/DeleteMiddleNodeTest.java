package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class DeleteMiddleNodeTest extends NodeTest {

    @ParameterizedTest
    @MethodSource("deleteMiddleNodeArguments")
    public void deleteMiddleNodeTest(List<String> values, List<String> expectedValues, int nodePos) {

        //given
        Node<String> head = fromStringValues(values);
        Node<String> toDelete = getNode(head, nodePos);

        //when
        DeleteMiddleNode.deleteMiddleNode(toDelete);

        //expect
        Iterator<String> expectedValuesIterator = expectedValues.iterator();
        for (String nodeValue : head) {
            assertEquals(expectedValuesIterator.next(), nodeValue);
        }
    }

    private static Stream<Arguments> deleteMiddleNodeArguments() {
        return Stream.of(
                Arguments.of(List.of("a", "b", "c"), List.of("a", "c"), 1),
                Arguments.of(List.of("a", "b", "c", "d", "e"), List.of("a", "c", "d", "e"), 1),
                Arguments.of(List.of("a", "b", "c", "d", "e"), List.of("a", "b", "d", "e"), 2),
                Arguments.of(List.of("a", "b", "c", "d"), List.of("a", "c", "d"), 1)
        );
    }


}
