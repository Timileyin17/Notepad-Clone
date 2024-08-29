import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;

public class Colors {

    // Choice under Color option
    public void textColor(JTextPane textPane, Color color) {
        String selectedText = textPane.getSelectedText();
        // Colors the text
        StyleContext styleContext = new StyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        if (selectedText != null && !selectedText.isEmpty()) {
            // Gets highlighted text from start to finished
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();
            textPane.getStyledDocument().setCharacterAttributes(start, end - start, attributeSet, true);
        } else {
            // Prints text with color from typing point
            textPane.setCharacterAttributes(attributeSet, true);
        }
    }

    // Highlights text
    public void highlightColor(JTextPane textPane, Color color) {
        String selectedText = textPane.getSelectedText();

        if (selectedText != null && !selectedText.isEmpty()) {
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(color);
            Highlighter highlighter = textPane.getHighlighter();
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            // Removes pre-existing highlights of the selected text to prevent overlapping
            Highlighter.Highlight[] highlights = highlighter.getHighlights();
            for (Highlighter.Highlight pre_highlight : highlights) {
                int pre_start = pre_highlight.getStartOffset();
                int pre_end = pre_highlight.getEndOffset();

                // Check if the highlight is within the selected text range
                if ((pre_start >= start && pre_start < end) ||
                    (pre_end > start && pre_end <= end)) {
                    highlighter.removeHighlight(pre_highlight);
                }
            }

            // Adds highlight
            try {
                highlighter.addHighlight(start, end, painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(textPane, "Select text to highlight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void backgroundColor(JTextPane textPane, Color color) {
        textPane.setBackground(color);

        StyleContext styleContext = new StyleContext();
        AttributeSet attributeSet;

        // Sets the text to white when the background is turned black
        if (color.equals(Color.black)) {
            attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.white);
            textSelection(textPane, attributeSet);
        } else {
            attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
            textSelection(textPane, attributeSet);
        }
    }

    // Selects all text
    private void textSelection(JTextPane textPane, AttributeSet set) {
        textPane.selectAll();
        textPane.setCharacterAttributes(set, true);
        textPane.select(textPane.getCaretPosition(), textPane.getCaretPosition());
    }
}