package com.algorithms.graphics.toolbar.control;

import com.algorithms.graphics.exception.IncompatibleControlTypeException;
import javafx.scene.Node;

/**
 * @author Mihai Alexandru
 * @date 12.10.2018
 */
public class ToolbarControl {

    private Integer order;

    private String type;

    private Node control;

    private ToolbarControl(String type, Node control, Integer order) {
        this.type = type;
        this.control = control;
        this.order = order;
    }

    public static ToolbarControl of(String type, Node control, Integer order) {
        return new ToolbarControl(type, control, order);
    }

    public String getType() {
        return type;
    }

    public Node getControl() {
        return control;
    }

    public Integer getOrder() {
        return order;
    }

    @SuppressWarnings("unchecked")
    public <T extends Node> T getControlAs(Class<T> expectedControlClass) {
        try {
            return expectedControlClass.cast(control);
        } catch (ClassCastException e) {
            throw new IncompatibleControlTypeException(e);
        }
    }

}
