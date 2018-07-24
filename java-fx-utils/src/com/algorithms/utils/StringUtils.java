package com.algorithms.utils;

import java.io.File;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String toFolderUrl(String packageName) {
        return packageName.replace(".", File.separator);
    }

    public static String toPackageName(String url) {
        return url.replace(File.separator, ".");
    }

}
