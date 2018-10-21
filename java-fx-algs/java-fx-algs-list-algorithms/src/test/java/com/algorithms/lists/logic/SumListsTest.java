package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class SumListsTest {

    @ParameterizedTest
    @MethodSource("sumListsForward")
    public void testSumListsForward(int a, int b, int expectedSum) {
        //given
        Node<Integer> nodeA = toLinkedList(a);
        Node<Integer> nodeB = toLinkedList(b);

        //when
        Node<Integer> result = SumLists.sumListsForwardOrder(nodeA, nodeB);
        int integerResult = fromNodeToDigit(result);

        //expect
        assertEquals(expectedSum, integerResult);
    }

    @ParameterizedTest
    @MethodSource("sumListsReverse")
    public void testSumListsReverse(int a, int b, int expectedSum) {
        //given
        Node<Integer> nodeA = toLinkedList(a);
        Node<Integer> nodeB = toLinkedList(b);

        //when
        Node<Integer> result = SumLists.sumListsReverseOrder(nodeA, nodeB);
        int integerResult = fromNodeToDigit(result);

        //expect
        assertEquals(expectedSum, integerResult);
    }

    private Node<Integer> toLinkedList(int number) {
        Deque<Integer> digits = new LinkedList<>();
        do {
            int c = number % 10;
            number /= 10;
            digits.addFirst(c);
        } while (number != 0);
        Node<Integer> head = null;
        for (Integer digit : digits) {
            if (head == null) {
                head = new Node<>(digit);
            } else {
                head.appendToTail(digit);
            }
        }
        return head;
    }

    private int fromNodeToDigit(Node<Integer> head) {
        int number = 0;
        for (Integer digit : head) {
            number = (number * 10) + digit;
        }
        return number;
    }


    private static Stream<Arguments> sumListsForward() {

        return Stream.of(
                Arguments.of(552, 23, 575),
                Arguments.of(123, 123, 246),
                Arguments.of(855, 1, 856),
                Arguments.of(205, 405, 610),
                Arguments.of(555, 222, 777)
        );
    }

    private static Stream<Arguments> sumListsReverse() {

        return Stream.of(
                Arguments.of(716, 592, 219),
                Arguments.of(922, 713, 645),
                Arguments.of(71, 912, 632)
        );
    }


}
