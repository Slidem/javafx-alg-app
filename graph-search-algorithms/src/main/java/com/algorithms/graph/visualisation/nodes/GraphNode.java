package com.algorithms.graph.visualisation.nodes;

import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.observers.GraphAlgVisualisationObserver;
import com.algorithms.graph.logic.Node;
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
 *
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
		this.parent.getChildren().add(circlePane);
	}

	public void delete() {
		this.parent.getChildren().remove(circlePane);
		this.parent.getChildren().removeAll(connections);
		this.connections.clear();
	}

	public void visit() {
		this.shape.setFill(Color.CADETBLUE);
	}
	
	public void select() {
		this.shape.setFill(Color.GOLDENROD);
		this.shape.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
	}
	
	public void unvisit() {
		this.shape.setFill(DEFAULT_COLOR);
	}
	
	public void setAsTarget() {
		this.shape.setFill(Color.INDIANRED);
	}
	
	public void unselect() {
		this.shape.setStyle("");
		this.shape.setFill(DEFAULT_COLOR);
	}
	
	public Node<String> getNode(){
		return this.node;
	}
	
	public String getId() {
		return this.id;
	}
	
	public double getNodeRadius() {
		return this.shape.getRadius();
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public void addConnectionLine(Line line) {
		this.connections.add(line);
		this.circlePane.toFront();
	}
	

	/**
	 * @author slidem
	 *
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
			this.observer = obs;
			return this;
		}
		
		public GraphNode build() {
			requireNonNull(parent, "Graph node parent cannot be null");
			requireNonNull(point, "Graph coordinates cannot be null");
			requireNonNull(id, "Graph id cannot be null");
			
			GraphNode n = new GraphNode();
			n.id = this.id;
			n.point = this.point;
			n.parent = this.parent;
			n.text = new Text(this.id);
			n.shape = new Circle(DEFAULT_RADIUS);
			n.shape.setFill(DEFAULT_COLOR);
			n.shape.setStroke(Color.BLACK);
			n.circlePane = new StackPane(n.shape, n.text);
			n.circlePane.setLayoutX(n.point.getX() - DEFAULT_RADIUS);
			n.circlePane.setLayoutY(n.point.getY() - DEFAULT_RADIUS);
			
			n.circlePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GraphAlgVisualisationObserver obs = GraphNode.Builder.this.observer;
					if(Objects.nonNull(obs)) {
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
					.withId(this.id)
					.withConnection(new Node.UndirectedConnection<String>())
					.build();
			return n;
		}

	}

}
