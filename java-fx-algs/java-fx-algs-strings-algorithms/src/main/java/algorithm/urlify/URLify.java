package algorithm.urlify;

import java.util.Objects;

public class URLify {

    private char[] str;

    private int strRealLength;

    private URLify(char[] str, int strRealLength) {
        this.str = Objects.requireNonNull(str);
        this.strRealLength = validatedRealLength(strRealLength);
    }

    public static URLify from(String str, int strRealLength) {
        return new URLify(str.toCharArray(), strRealLength);
    }

    private int validatedRealLength(int strRealLength) {
        int end = str.length - 1;
        char endChar = str[end];
        while (endChar == ' ') {
            endChar = str[--end];
        }
        if (strRealLength - 1 != end) {
            throw new IllegalArgumentException("Invalid string and real length.");
        }
        return strRealLength;
    }

    public String urlify() {
        for (int i = 0; i < this.strRealLength; i++) {
            char c = str[i];
            if (c == ' ') {
                moveString(i + 1, 2, str);
                addReplacement(i, "%20", str);
            }
        }
        return new String(str);
    }

    private void addReplacement(int start, String replacement, char[] str) {
        for (int i = 0; i < replacement.length(); i++) {
            str[start + i] = replacement.charAt(i);
        }
    }

    private char[] moveString(int start, int nbOfPos, char[] str) {
        int end = this.strRealLength - 1;
        while (end >= start) {
            str[end + nbOfPos] = str[end];
            str[end] = ' ';
            end--;
        }
        this.strRealLength += nbOfPos;
        return str;
    }

}
