package logic;


import com.algorithms.graph.logic.Node;
import com.algorithms.graph.logic.bfs.BFSSearch;
import com.algorithms.graph.logic.dfs.DFSSearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BFSSearchTest {

    private static final Node.Connection<String> undirectionalConnection = new Node.UndirectedConnection<>();

    @Test
    @DisplayName("Test path exists between connected nodes")
    public void testPathExistsBetweenConnectedNodes() {
        // prepare
        Node<String> node1 = buildUndirectionalNode("1");
        Node<String> node2 = buildUndirectionalNode("2");
        Node<String> node3 = buildUndirectionalNode("3");
        Node<String> node4 = buildUndirectionalNode("4");
        Node<String> node5 = buildUndirectionalNode("5");

        node1.addNeighbour(node2);
        node2.addNeighbour(node5);

        node1.addNeighbour(node4);
        node2.addNeighbour(node3);

        // expect
        assertTrue(new BFSSearch<>(node1, node5).beginSearch().pathExists(), "Path should exists between node 1 -> node 5");
        assertTrue(new BFSSearch<>(node1, node3).beginSearch().pathExists(), "Path should exists between node 1 -> node 3");
        assertTrue(new BFSSearch<>(node5, node4).beginSearch().pathExists(), "Path should exists between node 5 -> node 4");
    }

    @Test
    @DisplayName("Test path exists between connected nodes on a large graph")
    public void testPathExistsBetweenConnectedNodesOnLargeGraph() {
        // prepare
        Node<String> node1 = buildUndirectionalNode("1");
        Node<String> node2 = buildUndirectionalNode("2");
        Node<String> node3 = buildUndirectionalNode("3");
        Node<String> node4 = buildUndirectionalNode("4");
        Node<String> node5 = buildUndirectionalNode("5");
        Node<String> node6 = buildUndirectionalNode("6");
        Node<String> node7 = buildUndirectionalNode("7");
        Node<String> node8 = buildUndirectionalNode("8");
        Node<String> node9 = buildUndirectionalNode("9");
        Node<String> node10 = buildUndirectionalNode("10");

        node1.addNeighbour(node2);
        node3.addNeighbour(node8);
        node8.addNeighbour(node10);
        node4.addNeighbour(node7);
        node7.addNeighbour(node9);
        node5.addNeighbour(node6);

        // expect
        assertFalse(new BFSSearch<>(node1, node5).pathExists(),
                "Path should not exists between node 1 -> node 5");
        assertFalse(new BFSSearch<>(node1, node3).pathExists(),
                "Path should not exists between node 1 -> node 3");
        assertFalse(new BFSSearch<>(node5, node4).pathExists(),
                "Path should not exists between node 5 -> node 4");
        assertFalse(new BFSSearch<>(node1, node9).pathExists(),
                "Path should not exists between node 5 -> node 4");

        assertTrue(new BFSSearch<>(node1, node2).beginSearch().pathExists(), "Path should exists between node 1 -> node 2");
        assertTrue(new BFSSearch<>(node3, node10).beginSearch().pathExists(), "Path should exists between node 3 -> node 10");
        assertTrue(new BFSSearch<>(node4, node9).beginSearch().pathExists(), "Path should exists between node 4 -> node 9");
        assertTrue(new BFSSearch<>(node6, node5).beginSearch().pathExists(), "Path should exists between node 5 -> node 6");

    }

    @Test
    @DisplayName("Test if returned path is correct for a given graph")
    public void testPathBetweenNodes() {
        // prepare
        Node<String> node1 = buildUndirectionalNode("1");
        Node<String> node2 = buildUndirectionalNode("2");
        Node<String> node3 = buildUndirectionalNode("3");
        Node<String> node4 = buildUndirectionalNode("4");
        Node<String> node5 = buildUndirectionalNode("5");
        Node<String> node6 = buildUndirectionalNode("6");
        Node<String> node7 = buildUndirectionalNode("7");
        Node<String> node8 = buildUndirectionalNode("8");

        node1.addNeighbour(node2);
        node1.addNeighbour(node3);
        node1.addNeighbour(node4);
        node2.addNeighbour(node7);
        node3.addNeighbour(node7);
        node5.addNeighbour(node3);
        node6.addNeighbour(node4);
        node8.addNeighbour(node5);

        new DFSSearch<>(node1, node6).beginSearch().pathExists();
    }

    private Node<String> buildUndirectionalNode(String id) {
        return new Node.Builder<String>().withId(id).withValue("dummyValue").withConnection(undirectionalConnection)
                .build();
    }
}
