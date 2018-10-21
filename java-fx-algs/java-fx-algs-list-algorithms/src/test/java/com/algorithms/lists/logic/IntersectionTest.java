package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author Mihai Alexandru
 * @date 30.07.2018
 */
public class IntersectionTest {

    @DisplayName("Intersection test")
    @Test
    public void testIntersection() {

        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");

        a.appendToTail(b);
        b.appendToTail(c);

        d.appendToTail(e);
        e.appendToTail(c);
        c.appendToTail(f);

        Optional<Node<String>> resultA = Intersection.intersection(a, d);
        Optional<Node<String>> resultB = Intersection.intersectionBruteForce(a, d);
        Optional<Node<String>> resultC = Intersection.intersectionOptimized(a, d);

        Assertions.assertTrue(resultA.isPresent());
        Assertions.assertTrue(resultB.isPresent());
        Assertions.assertTrue(resultC.isPresent());

        Assertions.assertSame(c, resultA.get());
        Assertions.assertSame(c, resultB.get());
        Assertions.assertSame(c, resultC.get());
    }

}
