package com.algorithms.lists.visualisation.states.kthnode;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.KthNodeControlTypes;
import com.algorithms.lists.visualisation.objects.KthNodeControlUtils;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import javafx.scene.control.ComboBox;

/**
 * State indicates app is waiting for the user to generate the list. This should be the default state.
 *
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public class KthNodeGenerateListState extends ListAbstractState {

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        if (!generateClicked(control)) {
            return;
        }

        Toolbar toolbar = context.getToolbar();

        //draw list
        int listSize = getListSizeFromCombo(toolbar);
        Node<String> headNode = context.getListFactory().drawList(listSize);

        //make sure generate list is disabled; enable search and clear list.
        KthNodeControlUtils.disableSearch(toolbar, false);
        KthNodeControlUtils.disableClearList(toolbar, false);
        KthNodeControlUtils.disableGenerateList(toolbar, true);

        //change info
        InformationBar informationBar = toolbar.getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Start searching for kth node from the end of the list.");

        //change state
        context.changeState(new KthNodeListGeneratedState(headNode));
    }

    private boolean generateClicked(ToolbarControl control) {
        return KthNodeControlTypes.GENERATE_LIST_BUTTON.name().equals(control.getType());
    }

    private int getListSizeFromCombo(Toolbar toolbar) {
        return (int) toolbar.getControl(KthNodeControlTypes.LIST_SIZE_COMBO.name()).getControlAs(ComboBox.class).getValue();
    }

}
