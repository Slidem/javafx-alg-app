package com.algorithms.lists.visualisation.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public final class ListUtils {

    private ListUtils() {
    }

    public static List<Integer> getMinMaxList(int minSize, int maxSize) {
        var listSizeItems = new ArrayList<Integer>();
        for (int i = minSize; i < maxSize; i++) {
            listSizeItems.add(i);
        }
        return listSizeItems;
    }
}
