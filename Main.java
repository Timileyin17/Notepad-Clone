import javax.swing.*;
import java.awt.*;

public class Main {

    JFrame frame = new JFrame();
    final static int WINDOW_WIDTH = 500;
    final static int WINDOW_HEIGHT = 500;
    JScrollPane scrollBar;
    GUI gui;

    public Main() {
        gui = new GUI(this);
        structure();
    }

    private void structure() {
        // Sets frame
        frame.setTitle("Untitled");
        frame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setLocation(200, 40);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the text field for typing
        int vertical = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
        int horizontal = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        scrollBar = new JScrollPane(gui.getTextPane(), vertical, horizontal);
        scrollBar.setBorder(BorderFactory.createEmptyBorder());
        gui.getTextPane().setOpaque(true);

        // Applies functions to the menu bar
        new GUI(this);

        // Adds components
        frame.add(scrollBar);
        frame.add(gui.menuBar, BorderLayout.PAGE_START);
        frame.add(gui.getBottomBar(), BorderLayout.PAGE_END);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Sets the look and feel for Swing components to match the look and feel of the current system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        new Main();
    }

    public JFrame getFrame() {
        return frame;
    }
}