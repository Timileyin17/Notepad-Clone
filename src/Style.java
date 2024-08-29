import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class Style {

    // Choices under Style option
    public void fontStyles(JTextPane textPane, String style) {
        String selectedText = textPane.getSelectedText();
        // Start and end index
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        // Get the current attributes of the text and creates new bold attribute
        StyledDocument doc = textPane.getStyledDocument();
        AttributeSet current = doc.getCharacterElement(start).getAttributes();
        // Merge the bold attribute with any existing attributes
        MutableAttributeSet attrs = new SimpleAttributeSet(current);

        // Sets the style of the text to the selected case
        switch (style) {
            case "Bold":
                StyleConstants.setBold(attrs, true);
                break;
            case "Plain":
                StyleConstants.setBold(attrs, false);
                break;
            case "Italic":
                StyleConstants.setItalic(attrs, true);
                break;
            case "Underline":
                StyleConstants.setUnderline(attrs, true);
                break;
        }
        // Apply the merged bold attributes to the selected text
        if (selectedText != null && !selectedText.isEmpty()) {
            doc.setCharacterAttributes(start, end - start, attrs, true);
        } else {
            textPane.setCharacterAttributes(attrs, true);
        }
    }

    // Changes casing to lower and upper case
    public void textCase(JTextPane textPane, String casing) {
        // Removes pre-existing key listeners to prevent interference
        KeyListener[] pre = textPane.getKeyListeners();
        for (KeyListener listener : pre) {
           textPane.removeKeyListener(listener);
        }

        String selectedText = textPane.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            // Values used to covert selected text to upper or lowercase
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            String originalText = textPane.getText().substring(start, end);

            // Converts the selected text to upper or lowercase
            switch (casing) {
                case "Upper":
                    String upperCaseText = originalText.toUpperCase();
                    textPane.replaceSelection(upperCaseText);
                    break;
                case "Lower":
                    String lowerCaseText = originalText.toLowerCase();
                    textPane.replaceSelection(lowerCaseText);
                    break;
            }
        } else {
            // Prints text casing from typing point if no text is selected
            textPane.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                    char c = e.getKeyChar();
                    e.consume();
                    switch (casing) {
                        case "Upper":
                            textPane.replaceSelection(String.valueOf(Character.toUpperCase(c)));
                            break;
                        case "Lower":
                            textPane.replaceSelection(String.valueOf(Character.toLowerCase(c)));
                            break;
                    }
                }
            });
        }
    }
}