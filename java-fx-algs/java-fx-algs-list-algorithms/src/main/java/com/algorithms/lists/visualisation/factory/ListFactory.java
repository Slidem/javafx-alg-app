package com.algorithms.lists.visualisation.factory;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.exceptions.InvalidListSizeException;
import com.algorithms.lists.visualisation.objects.Constants;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;

import java.util.UUID;

import static com.algorithms.lists.visualisation.objects.Constants.Canvas.MAX_LIST_SIZE;
import static com.algorithms.lists.visualisation.objects.Constants.Canvas.MIN_LIST_SIZE;
import static com.algorithms.lists.visualisation.objects.Constants.Node.DEFAULT_SQUARE_SIDE;
import static com.algorithms.lists.visualisation.objects.Constants.Node.NODE_SPACING;
import static java.util.Objects.requireNonNull;

/**
 * @author Mihai Alexandru
 * @date 16.10.2018
 */
public class ListFactory {

    private Canvas<Node<String>> canvas;

    private ListFactory(Canvas<Node<String>> canvas) {
        this.canvas = canvas;
    }

    public static ListFactory of(Canvas<Node<String>> canvas) {
        return new ListFactory(canvas);
    }

    /**
     * Draws random node list (random values ranging from a - z) on the given canvas
     *
     * @param size
     */
    public Node<String> drawList(int size) {
        validateSize(size);
        Node<String> head = generateRandomNodes(size);
        drawList(head);
        return head;
    }

    /**
     * Draws list for the given  head node.
     *
     * @param headNode the head node of the list.
     */
    public void drawList(Node<String> headNode) {
        requireNonNull(headNode, "Cannot draw a null list of nodes");
        Point2D p = calculateStartPoint(headNode, canvas);
        Node<String> n = headNode;
        canvas.getAllNodes().forEach(canvas::deleteNode);
        while (n != null) {
            canvas.drawNode(p, new ListNodeFactory(n));
            n = n.getNext();
            p = new Point2D(p.getX() + DEFAULT_SQUARE_SIDE + NODE_SPACING, p.getY());
        }
    }

    // ---------------------------------------------------------------------------

    private void validateSize(int size) {
        if (size < MIN_LIST_SIZE) {
            throw new InvalidListSizeException("List size cannot be smaller than " + MIN_LIST_SIZE);
        }
        if (size > MAX_LIST_SIZE) {
            throw new InvalidListSizeException("List size cannot be bigger than" + MAX_LIST_SIZE);
        }
    }

    private Node<String> generateRandomNodes(int size) {

        int startCharCode = 'a';
        int endCharCode = 'z';
        int max = endCharCode - startCharCode;
        int i = 0;

        Node<String> head = null;
        while (i++ < size) {
            int randCode = (int) (Math.random() * max);
            char c = (char) (randCode + startCharCode);
            Node<String> n = new Node<>(String.valueOf(c), UUID.randomUUID().toString());
            if (head == null) {
                head = n;
            } else {
                head.appendToTail(n);
            }
        }

        return head;
    }

    private Point2D calculateStartPoint(Node<String> head, Canvas<Node<String>> canvas) {

        int listLength = getListLength(head);

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double startY = canvasHeight / 2;
        double startX = (canvasWidth - DEFAULT_SQUARE_SIDE * listLength - Constants.Node.NODE_SPACING * (listLength - 1)) / 2 + DEFAULT_SQUARE_SIDE / 2;

        return new Point2D(startX, startY);
    }

    private <T> int getListLength(Node<T> headNode) {
        Node<T> n = headNode;
        int length = 0;
        while (n != null) {
            length++;
            n = n.getNext();
        }
        return length;
    }

}
