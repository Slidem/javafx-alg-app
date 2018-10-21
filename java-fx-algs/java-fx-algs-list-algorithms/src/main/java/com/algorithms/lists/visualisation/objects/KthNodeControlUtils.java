package com.algorithms.lists.visualisation.objects;

import com.algorithms.graphics.toolbar.Toolbar;
import javafx.scene.Node;

import static com.algorithms.lists.visualisation.objects.KthNodeControlTypes.*;

/**
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public final class KthNodeControlUtils {

    private KthNodeControlUtils() {
    }

    public static void disableGenerateList(Toolbar toolbar, boolean value) {
        getControl(GENERATE_LIST_BUTTON, toolbar).setDisable(value);
        getControl(LIST_SIZE_COMBO, toolbar).setDisable(value);
    }

    public static void disableSearch(Toolbar toolbar, boolean value) {
        getControl(SEARCH_BUTTON, toolbar).setDisable(value);
        getControl(KTH_ELEMENT_SEARCH, toolbar).setDisable(value);
    }

    public static void disableClearList(Toolbar toolbar, boolean value) {
        getControl(CLEAR_BUTTON, toolbar).setDisable(value);
    }

    // -----------------------------------------------------------------

    private static Node getControl(KthNodeControlTypes controlType, Toolbar toolbar) {
        return toolbar.getControl(controlType.name()).getControl();
    }


}
