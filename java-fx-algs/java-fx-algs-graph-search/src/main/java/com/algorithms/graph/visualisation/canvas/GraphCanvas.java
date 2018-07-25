package com.algorithms.graph.visualisation.canvas;

import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.observers.GraphAlgVisualisationObserver;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.Map;

/**
 * @author slidem
 */
public class GraphCanvas extends Pane {

    private int nodeCount;

    private int nodeIdHolder;

    private final Map<String, GraphNode> graphNodes = new HashMap<>();

    private GraphAlgVisualisationObserver graphObserver;

    public GraphCanvas() {
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        setOnMouseClicked(getCanvasClickEventHandler());
        setMinHeight(610);
        setMaxHeight(640);
        setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void drawNode(Point point) {
        double x0 = point.getX();
        double y0 = point.getY();
        for (GraphNode n : graphNodes.values()) {
            double x1 = n.getPoint().getX();
            double y1 = n.getPoint().getY();
            if (Math.hypot(x0 - x1, y0 - y1) <= (n.getNodeRadius() + n.getNodeRadius())) {
                return;
            }
        }

        GraphNode node = new GraphNode.Builder().withGraphObserver(graphObserver).withId(String.valueOf(nodeIdHolder++))
                .withParent(this).withPoint(point).build();
        graphNodes.put(node.getId(), node);
        nodeCount++;
        node.draw();
    }

    public void deleteNode(GraphNode graphNode) {
        if (nodeCount == 0) {
            throw new IllegalStateException("Cannot delete anymore nodes. Canvas contains 0 nodes");
        }

        String nodeId = graphNode.getId();
        graphNode.delete();
        graphNodes.remove(nodeId);
        nodeCount--;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void connectNode(GraphNode connectFrom, GraphNode connectTo) {
        drawConnectLine(connectFrom, connectTo);
        connectFrom.getNode().addNeighbour(connectTo.getNode());
    }

    public void unselectAllNodes() {
        graphNodes.values().stream().forEach(GraphNode::unselect);
    }

    public void unvisitAllNodes() {
        graphNodes.values().stream().forEach(GraphNode::unvisit);
    }

    public GraphNode getFromAlgNode(Node<String> algNode) {
        return graphNodes.values().stream().filter(gn -> gn.getNode() == algNode).findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find Graph node for node : " + algNode));
    }

    public void setGraphObserver(GraphAlgVisualisationObserver observer) {
        graphObserver = observer;
        setOnMouseClicked(getCanvasClickEventHandler());
    }

    private EventHandler<MouseEvent> getCanvasClickEventHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object source = event.getSource();

                if (source instanceof Circle) {
                    Circle c = (Circle) source;
                    Point circlePoint = new Point(c.getCenterX(), c.getCenterY());
                    graphNodes.values().stream().filter(gn -> gn.getPoint().equals(circlePoint)).findFirst()
                            .ifPresent(gn -> graphObserver.graphNodeClicked(gn));
                } else {
                    Point clickedPoint = new Point(event.getX(), event.getY());
                    graphObserver.graphCanvasClicked(clickedPoint);
                }
            }
        };
    }

    private void drawConnectLine(GraphNode connectFrom, GraphNode connectTo) {
        Line line = new Line();
        line.setStartX(connectFrom.getPoint().getX());
        line.setStartY(connectFrom.getPoint().getY());
        line.setEndX(connectTo.getPoint().getX());
        line.setEndY(connectTo.getPoint().getY());
        connectFrom.addConnectionLine(line);
        connectTo.addConnectionLine(line);
        line.toBack();
        getChildren().add(line);
    }

}
