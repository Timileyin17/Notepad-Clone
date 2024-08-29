import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Shortcuts extends KeyAdapter {

    boolean key1Pressed = false;
    boolean key2Pressed = false;
    boolean shiftPressed = false;
    String secondKey;
    FileClass file;
    Edit edit;
    GUI gui;
    Main main;

    public Shortcuts(Main main, GUI gui) {
        this.main = main;
        this.gui = gui;
        edit = new Edit(gui);
        file = new FileClass(main);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Control key
        if (keyCode == KeyEvent.VK_CONTROL) {
            key1Pressed = true;
        }
        // Shift key
        if (keyCode == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }

        // Syncs both key presses
        switch (keyCode) {
            // File shortcuts
            case KeyEvent.VK_N:
                key2Pressed = true;
                secondKey = "N";
                break;
            case KeyEvent.VK_O:
                key2Pressed = true;
                secondKey = "O";
                break;
            case KeyEvent.VK_S:
                key2Pressed = true;
                if (shiftPressed) {
                    secondKey = "Shift + S";
                } else {
                    secondKey = "S";
                }
                break;
            case KeyEvent.VK_P:
                key2Pressed = true;
                secondKey = "P";
                break;
            // Edit shortcuts
            case KeyEvent.VK_Z:
                key2Pressed = true;
                secondKey = "Z";
                break;
            case KeyEvent.VK_Y:
                key2Pressed = true;
                secondKey = "Y";
                break;
            case KeyEvent.VK_X:
                key2Pressed = true;
                secondKey = "X";
                break;
            case KeyEvent.VK_DELETE:
                key2Pressed = true;
                secondKey = "Shift + Del";
                break;
            case KeyEvent.VK_F:
                key2Pressed = true;
                secondKey = "F";
                break;
            case KeyEvent.VK_F3:
                key2Pressed = true;
                secondKey = "F3";
                break;
            case KeyEvent.VK_R:
                key2Pressed = true;
                secondKey = "R";
                break;
            case KeyEvent.VK_G:
                key2Pressed = true;
                secondKey = "G";
                break;
            case KeyEvent.VK_A:
                key2Pressed = true;
                secondKey = "A";
                break;
            case KeyEvent.VK_F5:
                key2Pressed = true;
                secondKey = "F5";
                break;
        }

        // Performs action when both key presses are true for the specified key
        if (key1Pressed && key2Pressed && shiftPressed || key1Pressed && key2Pressed) {
            switch (secondKey) {
                // File shortcuts
                case "N":
                    file.newFile();
                    setKeysFalse();
                    break;
                case "O":
                    file.open(gui.getTextPane(), main.getFrame());
                    setKeysFalse();
                    break;
                case "S":
                    file.save(gui.getTextPane(), main.getFrame());
                    setKeysFalse();
                    break;
                case "Shift + S":
                    file.saveAs(gui.getTextPane(), main.getFrame());
                    setKeysFalse();
                    break;
                case "P":
                    file.print(gui.getTextPane(), main.getFrame());
                    setKeysFalse();
                    break;
                // Edit shortcuts
                case "Z":
                    edit.undo();
                    setKeysFalse();
                    break;
                case "Y":
                    edit.redo();
                    setKeysFalse();
                    break;
                case "X":
                    edit.cut();
                    setKeysFalse();
                    break;
                case "Shift + Del":
                    edit.clear(gui.getTextPane());
                    setKeysFalse();
                    break;
                case "F":
                    edit.find(gui.getTextPane());
                    setKeysFalse();
                    break;
                case "F3":
                    edit.findNext(gui.getTextPane());
                    break;
                case "R":
                    edit.replace(gui.getTextPane());
                    setKeysFalse();
                    break;
                case "G":
                    edit.goTo(gui.getTextPane());
                    setKeysFalse();
                    break;
                case "A":
                    edit.selectAll(gui.getTextPane());
                    setKeysFalse();
                    break;
                case "F5":
                    edit.time(gui.getTextPane());
                    setKeysFalse();
                    break;
            }
        }
    }

    private void setKeysFalse() {
        key1Pressed = false;
        key2Pressed = false;
        shiftPressed = false;
    }

    @Override
    // Sets press values back to false when the keys are released
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_CONTROL) {
            key1Pressed = false;
        }
        if (keyCode == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }

        // Key code release for "secondKey"
        switch (keyCode) {
            case KeyEvent.VK_N, KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_X, KeyEvent.VK_P,
                 KeyEvent.VK_Z, KeyEvent.VK_Y, KeyEvent.VK_F, KeyEvent.VK_R, KeyEvent.VK_G,
                 KeyEvent.VK_A, KeyEvent.VK_F3, KeyEvent.VK_F5, KeyEvent.VK_DELETE:
                key2Pressed = false;
                secondKey = "";
                break;
        }
    }
}