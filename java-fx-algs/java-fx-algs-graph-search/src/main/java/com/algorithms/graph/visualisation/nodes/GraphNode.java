package com.algorithms.graph.visualisation.nodes;

import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.observers.GraphAlgVisualisationObserver;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * @author slidem
 */
public class GraphNode {

    public static final double DEFAULT_RADIUS = 25f;

    public static final Color DEFAULT_COLOR = Color.GOLD;

    private Node<String> node;

    private String id;

    private Circle shape;

    private Text text;

    private Pane parent;

    private Point point;

    private StackPane circlePane;

    private List<Line> connections;

    private GraphNode() {
    }

    public void draw() {
        parent.getChildren().add(circlePane);
    }

    public void delete() {
        parent.getChildren().remove(circlePane);
        parent.getChildren().removeAll(connections);
        connections.clear();
    }

    public void visit() {
        shape.setFill(Color.CADETBLUE);
    }

    public void select() {
        shape.setFill(Color.GOLDENROD);
        shape.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
    }

    public void unvisit() {
        shape.setFill(DEFAULT_COLOR);
    }

    public void setAsTarget() {
        shape.setFill(Color.INDIANRED);
    }

    public void unselect() {
        shape.setStyle("");
        shape.setFill(DEFAULT_COLOR);
    }

    public Node<String> getNode() {
        return node;
    }

    public String getId() {
        return id;
    }

    public double getNodeRadius() {
        return shape.getRadius();
    }

    public Point getPoint() {
        return point;
    }

    public void addConnectionLine(Line line) {
        connections.add(line);
        circlePane.toFront();
    }


    /**
     * @author slidem
     */
    public static class Builder {

        private Pane parent;

        private Point point;

        private String id;

        private GraphAlgVisualisationObserver observer;

        public Builder withParent(Pane parent) {
            this.parent = parent;
            return this;
        }

        public Builder withPoint(Point point) {
            this.point = point;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withGraphObserver(GraphAlgVisualisationObserver obs) {
            observer = obs;
            return this;
        }

        public GraphNode build() {
            requireNonNull(parent, "Graph node parent cannot be null");
            requireNonNull(point, "Graph coordinates cannot be null");
            requireNonNull(id, "Graph id cannot be null");

            GraphNode n = new GraphNode();
            n.id = id;
            n.point = point;
            n.parent = parent;
            n.text = new Text(id);
            n.shape = new Circle(DEFAULT_RADIUS);
            n.shape.setFill(DEFAULT_COLOR);
            n.shape.setStroke(Color.BLACK);
            n.circlePane = new StackPane(n.shape, n.text);
            n.circlePane.setLayoutX(n.point.getX() - DEFAULT_RADIUS);
            n.circlePane.setLayoutY(n.point.getY() - DEFAULT_RADIUS);

            n.circlePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    GraphAlgVisualisationObserver obs = observer;
                    if (Objects.nonNull(obs)) {
                        obs.graphNodeClicked(n);
                    }
                }
            });

            n.circlePane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    StackPane sp = (StackPane) event.getSource();
                    sp.getScene().setCursor(Cursor.HAND);
                }
            });

            n.circlePane.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    StackPane sp = (StackPane) event.getSource();
                    sp.getScene().setCursor(Cursor.DEFAULT);
                }
            });

            n.connections = new ArrayList<>();

            n.node = new Node.Builder<String>()
                    .withId(id)
                    .withConnection(new Node.UndirectedConnection<String>())
                    .build();
            return n;
        }

    }

}
