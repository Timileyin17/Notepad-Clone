import javax.swing.*;
import javax.swing.text.*;

public class Format {

    public void fontType(JTextPane textPane, String fontFamily) {
        // Values used to set font style
        String selectedText = textPane.getSelectedText();
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.FontConstants.setFontFamily(set, fontFamily);

        // Apply the attributes to the selected text
        if (selectedText != null && !selectedText.isEmpty()) {
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            doc.setCharacterAttributes(start, end - start, set, true);
        } else {
            textPane.setCharacterAttributes(set, true);
        }
    }

    public void fontSize(JTextPane textPane, int size) {
        // Values used to set font size
        String selectedText = textPane.getSelectedText();
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setFontSize(set, size);

        // Apply the attributes to the selected text
        if (selectedText != null && !selectedText.isEmpty()) {
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            doc.setCharacterAttributes(start, end - start, set, true);
        } else {
            textPane.setCharacterAttributes(set, true);
        }
    }
}