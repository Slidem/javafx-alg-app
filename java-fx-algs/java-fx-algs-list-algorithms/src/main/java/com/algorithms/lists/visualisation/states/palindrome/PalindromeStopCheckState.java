package com.algorithms.lists.visualisation.states.palindrome;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.PalindromeControlTypes;
import com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import javafx.scene.control.Button;

import static com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class PalindromeStopCheckState extends ListAbstractState {

    private Runnable stopRunnable;

    private Node<String> head;

    public PalindromeStopCheckState(Runnable stopRunnable, Node<String> head) {
        this.stopRunnable = stopRunnable;
        this.head = head;
    }

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        stopRunnable.run();
        context.changeState(new PalindromeListGeneratedState(head));
    }
}
