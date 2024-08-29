import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.*;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Edit {

    Highlighter highlighter;
    UndoManager undoManager;
    String wordToFind;
    String direction;
    int lastFoundPosition;
    boolean occurrence;
    GUI gui;

    public Edit(GUI gui) {
        this.gui = gui;

        undoManager = new UndoManager();
        highlighter = gui.textPane.getHighlighter();
        gui.textPane.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit())); // Undo Listener
        lastFoundPosition = 0;
        occurrence = false;
    }

    // choices under Edit option
    public void undo() {
        // Undoes previous character typed
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException e) {
            e.printStackTrace();
        }
    }

    public void redo() {
        // Redoes what has been undone
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } catch (CannotRedoException e) {
            e.printStackTrace();
        }
    }

    public void cut() {
        // Removes the selected text
        DefaultEditorKit.CutAction cutAction = new DefaultEditorKit.CutAction();
        cutAction.actionPerformed(null);
    }

    //clears notepad
    public void clear(JTextPane text) {
        text.setText("");
    }

    public void copy() {
        // Copies the selected text
        DefaultEditorKit.CopyAction copyAction = new DefaultEditorKit.CopyAction();
        copyAction.actionPerformed(null);
    }

    public void paste() {
        // Paste the copied text
        DefaultEditorKit.PasteAction pasteAction = new DefaultEditorKit.PasteAction();
        pasteAction.actionPerformed(null);
    }

    public void find(JTextPane textPane) {
        // Prompt to enter the word to find and store it in the string
        wordToFind = JOptionPane.showInputDialog(textPane, "", "Find", JOptionPane.QUESTION_MESSAGE);

        if (wordToFind != null && !wordToFind.isEmpty()) {
            // Values used to iterate through document
            StyledDocument doc = textPane.getStyledDocument();
            Element root = doc.getDefaultRootElement();
            boolean wordFound = false;

            // Iterates through each line of the document
            for (int i = 0; i < root.getElementCount(); i++) {
                Element element = root.getElement(i);
                int startOffset = element.getStartOffset();
                int endOffset = element.getEndOffset();

                try {
                    // Values used to search for specified text in text space
                    String lineText = doc.getText(startOffset, endOffset - startOffset);
                    int index = lineText.indexOf(wordToFind);
                    int length = wordToFind.length();

                    if (index != -1) {
                        int startIndex = startOffset + index;
                        int endIndex = startIndex + length;

                        // Removes any pre-existing highlights of words
                        removePreHighlight();
                        // Highlights the selected text and sets typing point to replaced word
                        highlighter.addHighlight(startIndex, endIndex, DefaultHighlighter.DefaultPainter);
                        textPane.setCaretPosition(startIndex + length);
                        lastFoundPosition = startIndex + length;
                        occurrence = true;
                        wordFound = true;
                        break;
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
            // If the searched word isn't found in the text space
            if (!wordFound) {
                JOptionPane.showMessageDialog(textPane, "Searched word not found.", "Missing", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Removes highlight after next mouse or key press
                listeners(textPane);
            }
        } else {
            JOptionPane.showMessageDialog(textPane, "No word entered.", "Error", JOptionPane.ERROR_MESSAGE);
            occurrence = false;
        }
    }

    public void findNext(JTextPane textPane) {
        // Values used to iterate through document
        StyledDocument doc = textPane.getStyledDocument();
        Element root = doc.getDefaultRootElement();
        boolean nextInstance = false;

        if (occurrence) {
            // Iterates through each line of the document
            for (int i = 0; i < root.getElementCount(); i++) {
                Element element = root.getElement(i);
                int startOffset = element.getStartOffset();
                int endOffset = element.getEndOffset();

                try {
                    // Values to find the next instance of the found word
                    String lineText = doc.getText(startOffset, endOffset - startOffset);
                    int startSearchIndex = (lastFoundPosition > startOffset) ? lastFoundPosition - startOffset + 1 : 0;
                    int index = lineText.indexOf(wordToFind, startSearchIndex);
                    int length = wordToFind.length();

                    // Iterates through all instances of the found word
                    if (index != -1) {
                        int startIndex = startOffset + index;
                        int endIndex = startIndex + length;

                        // Removes pre-existing highlights before adding the new one
                        removePreHighlight();
                        highlighter.addHighlight(startIndex, endIndex, DefaultHighlighter.DefaultPainter);
                        // Move to the next occurrence of the found word
                        textPane.setCaretPosition(startIndex + length);
                        lastFoundPosition = startIndex + length;
                        nextInstance = true;
                        break;
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }

            // When the last instance of the word is found, jumps back to the first instance
            if (!nextInstance) {
                lastFoundPosition = 0;
                findNext(textPane);
            } else {
                listeners(textPane);
            }
        } else {
            // If no word has been initially found
            JOptionPane.showMessageDialog(textPane, "No word to find.", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findPrevious(JTextPane textPane) {
        // Values used to iterate through document
        StyledDocument doc = textPane.getStyledDocument();
        Element root = doc.getDefaultRootElement();
        boolean pastInstance = false;

        if (occurrence) {
            // Iterates through each line of the document
            for (int i = root.getElementCount() - 1; i >= 0; i--) {
                Element element = root.getElement(i);
                int startOffset = element.getStartOffset();
                int endOffset = element.getEndOffset();

                try {
                    // Values to find the previous instance of the found word
                    String lineText = doc.getText(startOffset, endOffset - startOffset);
                    int length = wordToFind.length();
                    int index;

                    // If the last found position is at the start, set to the end of the doc
                    if (lastFoundPosition == 0) {
                        lastFoundPosition = doc.getLength();
                    }

                    // If the last found position goes over the end offset, search the entire line
                    if (lastFoundPosition > endOffset) {
                        index = lineText.lastIndexOf(wordToFind);
                    } else {
                        // Search for the word up to just before the last found position in the current line
                        int endSearchIndex = lastFoundPosition - startOffset - 1;
                        index = lineText.lastIndexOf(wordToFind, endSearchIndex - 1);
                    }

                    // Iterates through all instances of the found word
                    if (index != 1) {
                        int startIndex = startOffset + index;
                        int endIndex = startIndex + length;

                        if (startIndex < lastFoundPosition) {
                            // Removes any potential pre-existing highlights
                            removePreHighlight();
                            // Highlights the found word
                            highlighter.addHighlight(startIndex, endIndex, DefaultHighlighter.DefaultPainter);
                            // Sets the caret position for the found word
                            textPane.setCaretPosition(startIndex + length);
                            lastFoundPosition = startIndex;
                            pastInstance = true;
                            break;
                        }
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }

            // When the last previous instance of the word is found, jumps to the last instance
            if (!pastInstance) {
                lastFoundPosition = doc.getLength();
                findPrevious(textPane);
            } else {
                listeners(textPane);
            }
        } else {
            // If no word has been initially found
            JOptionPane.showMessageDialog(textPane, "No word to find.", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void replace(JTextPane textPane) {
        // Prompt to enter the word to find and store it in the variable
        String wordToReplace = JOptionPane.showInputDialog(textPane, "", "Find", JOptionPane.QUESTION_MESSAGE);

        if (wordToReplace != null && !wordToReplace.isEmpty()) {
            // Values used to iterate through document
            StyledDocument doc = textPane.getStyledDocument();
            Element root = doc.getDefaultRootElement();
            boolean wordFound = false;
            boolean cancel = true;

            // Iterates through each line of the document
            for (int i = 0; i < root.getElementCount(); i++) {
                Element element = root.getElement(i);
                int startOffset = element.getStartOffset();
                int endOffset = element.getEndOffset();

                try {
                    // Values used to search for specified text in text space
                    String lineText = doc.getText(startOffset, endOffset - startOffset);
                    int index = lineText.indexOf(wordToReplace);
                    int length = wordToReplace.length();

                    if (index != -1) {
                        int startIndex = startOffset + index;
                        int endIndex = startIndex + length;

                        // Removes any pre-existing highlights of words
                        removePreHighlight();

                        highlighter.addHighlight(startIndex, endIndex, DefaultHighlighter.DefaultPainter);

                        // Prompt to enter the replacement word and store it in the variable
                        String replacement = JOptionPane.showInputDialog(textPane, "Replace");
                        if (replacement != null) {
                            // Replace the found word with the replacement word
                            doc.remove(startIndex, length);
                            doc.insertString(startIndex, replacement, null);
                            int replacedIndex = startIndex;
                            int replacedLength = replacement.length();
                            // Sets typing point to replaced word
                            highlighter.addHighlight(replacedIndex, replacedLength + replacedIndex, DefaultHighlighter.DefaultPainter);
                            textPane.setCaretPosition(replacedIndex + replacedLength);
                            wordFound = true;
                        } else {
                            JOptionPane.showMessageDialog(textPane, "Insertion Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                            wordFound = cancel;
                        }
                        break;
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
            // If the searched word isn't found in the textSpace
            if (!wordFound) {
                JOptionPane.showMessageDialog(textPane, "Searched word not found.", "Missing", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Removes highlight after next mouse or key press
                listeners(textPane);
            }
        } else {
            JOptionPane.showMessageDialog(textPane, "No word entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removePreHighlight() {
        // Removes any pre-existing highlights of words
        Highlighter.Highlight[] highlights = highlighter.getHighlights();
        for (Highlighter.Highlight pre_highlight : highlights) {
            highlighter.removeHighlight(pre_highlight);
        }
    }

    private void listeners(JTextPane textPane) {
        // Removes highlight after the next mouse click
        textPane.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                highlighter.removeAllHighlights();
            }
        });
        // Removes highlight after the next key type
        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                highlighter.removeAllHighlights();
            }
        });
    }

    public void goTo(JTextPane textPane) {
        Document doc = textPane.getDocument();
        int maxLines = doc.getDefaultRootElement().getElementCount(); // Gets total amounts of lines on document
        boolean validInput = false;

        // Loop until a valid number is inputted
        while (!validInput) {
            try {
                // Prompt for selecting which line to go to
                String lineChoice = JOptionPane.showInputDialog(textPane, "Go To Line", "Go To", JOptionPane.QUESTION_MESSAGE);

                // Closes window if nothing is inputted into "lineChoice"
                if (lineChoice == null || lineChoice.trim().isEmpty()) {
                    break;
                }

                int line = Integer.parseInt(lineChoice);
                // Sets the caret position to the selected line
                if (line > 0 && line <= maxLines) {
                    int placeLine = doc.getDefaultRootElement().getElement(line - 1).getStartOffset();
                    textPane.setCaretPosition(placeLine);
                    validInput = true;
                    // If a number greater than the total lines on the doc is inputted
                } else if (line > maxLines) {
                    try {
                        JOptionPane.showMessageDialog(textPane, "The line number is beyond the total number of lines.", "Go To", JOptionPane.ERROR_MESSAGE);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    // If a negative number is inputted
                } else {
                    JOptionPane.showMessageDialog(textPane, "The line number is below the total number of lines.", "Go To", JOptionPane.ERROR_MESSAGE);
                }
                // If a string is inputted instead of an integer
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(textPane, "Please enter a valid number.", "Go To", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void selectAll(JTextPane textPane) {
        // Selects all text on the window
        if (!textPane.getText().isEmpty()) {
            textPane.selectAll();
        } else {
            JOptionPane.showMessageDialog(textPane, "File is already empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void time(JTextPane textPane) {
        // Gets time and date of the current system and sets it in the following format
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String time = timeFormat.format(Calendar.getInstance().getTime());
        String date = dateFormat.format(Calendar.getInstance().getTime());
        Document doc = textPane.getDocument();

        // Inserts the string of content into the document
        try {
            doc.insertString(doc.getLength(), time + " " + date, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}