import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class BottomRow {
    private final JMenu wordCountMenu = new JMenu();
    private final JMenu col_RowMenu = new JMenu();
    private final JMenu charCountMenu = new JMenu();
    private final JButton spellCheckButton = new JButton();
    private final Set<String> dictionary = new HashSet<>();
    private final Document doc;
    private final GUI gui;

    public BottomRow(GUI gui, JTextPane textPane) {
        this.gui = gui;
        doc = gui.getTextPane().getDocument();

        col_rows();
        wordCounter();
        characterCounter();
        spellChecker(textPane);
    }



    private void col_rows() {
        col_RowMenu.setText("Ln 1, Col 1");

        // Updates the position of the mouse caret
        gui.getTextPane().addCaretListener(e -> {
            int caret = gui.getTextPane().getCaretPosition();
            int row = 0;
            int column = 0;

            // Gets the line and column of the text
            row = doc.getDefaultRootElement().getElementIndex(caret) + 1;
            column = caret - doc.getDefaultRootElement().getElement(row - 1).getStartOffset() + 1;

            col_RowMenu.setText("Ln " + row + " Col " + column);
        });
        gui.getBottomBar().add(col_RowMenu);
    }

    private void wordCounter() {
        wordCountMenu.setText("0 Words");

        // Ensures the word count is updated
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                wordCount();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                wordCount();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                wordCount();
            }
        });
        gui.getBottomBar().add(wordCountMenu);
    }

    // Keeps track of the words
    private void wordCount() {
        String text = gui.getTextPane().getText();
        int count = counter(text);

        if (count == 1) {
            wordCountMenu.setText(count + " Word");
        } else {
            wordCountMenu.setText(count + " Words");
        }
    }

    private int counter(String str) {
        // If there are no words
        if (str == null || str.isEmpty()) {
            return 0;
        }
        // Counts words
        String[] text = str.split("\\s+");
        return text.length;
    }

    private void characterCounter() {
        charCountMenu.setText("0 Characters");

        // Ensures the character count is updated
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                charCount();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                charCount();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                charCount();
            }
        });
        gui.getBottomBar().add(charCountMenu);
    }

    private void charCount() {
        // Counts the characters on the pad
        int chars = gui.getTextPane().getText().length();
        if (chars == 1) {
            charCountMenu.setText(chars + " Character");
        } else {
            charCountMenu.setText(chars + " Characters");
        }
    }

    private void spellChecker(JTextPane textPane) {
        loadDictionary(); // Loads words of the dictionary

        spellCheckButton.setText("Spell Check");
        spellCheckButton.setFocusable(false);
        gui.getBottomBar().add(spellCheckButton);

        spellCheckButton.addActionListener(e -> {
            // Values used to check and mark an incorrectly spelled word
            String text = textPane.getText();
            String[] words = text.split("\\s+");
            Highlighter highlighter = textPane.getHighlighter();

            // Removes any pre-existing highlights of words
            Highlighter.Highlight[] highlights = highlighter.getHighlights();
            for (Highlighter.Highlight highlight : highlights) {
                highlighter.removeHighlight(highlight);
            }

            // Loops through all words on the text pane
            for (String word : words) {
                String plainWord = word.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", "").toLowerCase(); // variable to exclude connected symbols

                // If the word isn't empty and dictionary does not contain the word
                if ((!plainWord.isEmpty()) && (!dictionary.contains(plainWord))) {
                    int start = text.indexOf(word);
                    int end = start + word.length();

                    // Highlights incorrectly spelled word
                    try {
                        highlighter.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void loadDictionary() {
        // Reads text from the intended "dictionary" file
        try (BufferedReader reader = new BufferedReader(new FileReader("/C:/IdeaProjects/Notepad Clone/src/Dictionary.txt/"))) {
            String word;
            // Reads each line of the file until the end and adds it to hashset "dictionary"
            while ((word = reader.readLine()) != null) {
                dictionary.add(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}