package com.algorithms.lists.visualisation.objects;

import javafx.scene.control.TextField;

/**
 * @author Mihai Alexandru
 * @date 09.08.2018
 */
public class TextFieldLimited extends TextField {
    private int maxlength;

    public TextFieldLimited() {
        maxlength = 10;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        // Delete or backspace user input.
        if (text.equals("") || getText().length() < maxlength) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxlength) {
            // Add characters, but don't exceed maxlength.
            if (text.length() > maxlength - getText().length()) {
                text = text.substring(0, maxlength - getText().length());
            }
            super.replaceSelection(text);
        }
    }
}
