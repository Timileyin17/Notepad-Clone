import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Functionalities {
    private final JMenu wordCountMenu = new JMenu();
    private final JMenu col_RowMenu = new JMenu();
    private final JMenu charCountMenu = new JMenu();
    private final JButton spellCheckButton = new JButton();
    private final Set<String> dictionary = new HashSet<>();
    private final Document doc;
    private final GUI gui;

    public Functionalities(GUI gui) {
        this.gui = gui;
        doc = gui.getTextPane().getDocument();
        col_rows();
        wordCounter();
        characterCounter();
        //spellChecker();
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

    private void spellChecker(JTextPane textPane) {
        // Reads text from the intended "dictionary" file
        try (BufferedReader reader = new BufferedReader(new FileReader("/C:/Users/timim/OneDrive/Documents/Dictionary.txt/"))) {
            String word;
            // Reads each line of the file until the end and adds it to hashset "dictionary"
            while ((word = reader.readLine()) != null) {
                dictionary.add(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        spellCheckButton.setText("Spell Check");
        spellCheckButton.setFocusable(false);

        spellCheckButton.addActionListener(e -> {
            String text = textPane.getText();
            String[] words = text.split("\\s+");
        });


        gui.getBottomBar().add(spellCheckButton);


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
}