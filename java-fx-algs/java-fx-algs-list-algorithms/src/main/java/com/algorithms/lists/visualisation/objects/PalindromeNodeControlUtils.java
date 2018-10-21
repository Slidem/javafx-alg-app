package com.algorithms.lists.visualisation.objects;

import com.algorithms.graphics.toolbar.Toolbar;
import javafx.scene.Node;

import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public final class PalindromeNodeControlUtils {

    private PalindromeNodeControlUtils() {
    }

    public static void disableGenerateList(Toolbar toolbar, boolean disabled) {
        getControl(GENERATE_LIST, toolbar).setDisable(disabled);
        getControl(LIST_TEXT_FIELD, toolbar).setDisable(disabled);
    }

    public static void disableSearch(Toolbar toolbar, boolean disabled) {
        getControl(CHECK_PALINDROME, toolbar).setDisable(disabled);
    }

    public static void disableClearList(Toolbar toolbar, boolean disabled) {
        getControl(CLEAR, toolbar).setDisable(disabled);
    }

    // ---------------------------------------------------------------------------

    private static Node getControl(PalindromeControlTypes controlType, Toolbar toolbar) {
        return toolbar.getControl(controlType.name()).getControl();
    }

}
