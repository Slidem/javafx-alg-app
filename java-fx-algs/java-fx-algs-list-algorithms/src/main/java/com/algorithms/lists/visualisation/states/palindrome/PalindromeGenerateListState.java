package com.algorithms.lists.visualisation.states.palindrome;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.PalindromeControlTypes;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import javafx.scene.control.TextInputControl;

import java.util.Objects;
import java.util.UUID;

import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.GENERATE_LIST;
import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.LIST_TEXT_FIELD;
import static com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils.*;
import static com.algorithms.utils.StringUtils.isEmpty;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class PalindromeGenerateListState extends ListAbstractState {

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        if (! Objects.equals(control.getType(), GENERATE_LIST.name())) {
            return;
        }
        Toolbar toolbar = context.getToolbar();
        InformationBar informationBar = toolbar.getInformationBar();
        String listStringValue = getListStringValue(toolbar);
        if (isEmpty(listStringValue)) {
            informationBar.changeToErrorIcon();
            informationBar.changeMessage("Unable to generate an empty list");
            return;
        }

        var headNode = drawList(listStringValue, context);
        disableSearch(toolbar, false);
        disableClearList(toolbar, false);
        disableGenerateList(toolbar, true);
        context.changeState(new PalindromeListGeneratedState(headNode));
    }

    // ------------------------------------------------------------------------------------------------

    private String getListStringValue(Toolbar toolbar) {
        ToolbarControl listTextField = toolbar.getControl(LIST_TEXT_FIELD.name());
        return listTextField.getControlAs(TextInputControl.class).getText();
    }

    private Node<String> drawList(String listStringValue, ListNodeAlgorithmContext context) {
        Node<String> head = null;
        for (Character c : listStringValue.toCharArray()) {
            Node<String> n = new Node<>(String.valueOf(c), UUID.randomUUID().toString());
            if (head == null) {
                head = n;
            } else {
                head.appendToTail(n);
            }
        }
        context.getListFactory().drawList(head);
        return head;
    }

}
