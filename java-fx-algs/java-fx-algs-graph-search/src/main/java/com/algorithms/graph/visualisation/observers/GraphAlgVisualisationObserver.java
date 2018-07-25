package com.algorithms.graph.visualisation.observers;

import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

/**
 * @author slidem
 */
public interface GraphAlgVisualisationObserver {

    void graphNodeClicked(GraphNode graphNode);

    void buttonClicked(GraphToolbar.ButtonType type);

    void graphCanvasClicked(Point point);

}
