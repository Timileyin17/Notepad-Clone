import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {

    JTextPane textPane = new JTextPane();
    JMenuBar menuBar = new JMenuBar();
    JMenuBar bottomBar = new JMenuBar();
    FileClass file;
    Edit edit;
    Format format;
    Style style;
    Colors colors;
    Main main;

    public GUI(Main main) {
        this.main = main;
        file = new FileClass(main);
        edit = new Edit(this);
        format = new Format();
        style = new Style();
        colors = new Colors();

        // Adds shortcuts
        textPane.addKeyListener(new Shortcuts(main, this));

        new Functionalities(this);
        file();
        edit();
        format();
        style();
        color();
    }

    private void file() {
        //File option
        JMenu menuFile = new JMenu("File ");
        menuBar.add(menuFile);

        JMenuItem fileNew = new JMenuItem("New (Ctrl + N)");
        fileNew.addActionListener(this);
        fileNew.setActionCommand("New Tab");
        menuFile.add(fileNew);

        JMenuItem fileOpen = new JMenuItem("Open (Ctrl + O)");
        fileOpen.addActionListener(this);
        fileOpen.setActionCommand("Open");
        menuFile.add(fileOpen);

        JMenuItem fileSave = new JMenuItem("Save (Ctrl + S)");
        fileSave.addActionListener(this);
        fileSave.setActionCommand("Save");
        menuFile.add(fileSave);

        JMenuItem fileSaveAs = new JMenuItem("Save As  (Ctrl + Shift + S)");
        fileSaveAs.addActionListener(this);
        fileSaveAs.setActionCommand("Save As");
        menuFile.add(fileSaveAs);

        JMenuItem filePrint = new JMenuItem("Print");
        filePrint.addActionListener(this);
        filePrint.setActionCommand("Print");
        menuFile.add(filePrint);

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.addActionListener(this);
        fileExit.setActionCommand("Exit");
        menuFile.add(fileExit);
    }

    private void edit() {
        //Edit option
        JMenu menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        JMenuItem editUndo = new JMenuItem("Undo (Ctrl + Z)");
        editUndo.addActionListener(this);
        editUndo.setActionCommand("Undo");
        menuEdit.add(editUndo);

        JMenuItem editRedo = new JMenuItem("Redo (Ctrl + Y)");
        editRedo.addActionListener(this);
        editRedo.setActionCommand("Redo");
        menuEdit.add(editRedo);

        JMenuItem editCut = new JMenuItem("Cut (Ctrl + X)");
        editCut.addActionListener(this);
        editCut.setActionCommand("Cut");
        menuEdit.add(editCut);

        JMenuItem editClear = new JMenuItem("Clear (Ctrl + Shift + Del)");
        editClear.addActionListener(this);
        editClear.setActionCommand("Clear");
        menuEdit.add(editClear);

        JMenuItem editCopy = new JMenuItem("Copy (Ctrl + C)");
        editCopy.addActionListener(this);
        editCopy.setActionCommand("Copy");
        menuEdit.add(editCopy);

        JMenuItem editPaste = new JMenuItem("Paste (Ctrl + V)");
        editPaste.addActionListener(this);
        editPaste.setActionCommand("Paste");
        menuEdit.add(editPaste);

        JMenuItem editFind = new JMenuItem("Find (Ctrl + F)");
        editFind.addActionListener(this);
        editFind.setActionCommand("Find");
        menuEdit.add(editFind);

        JMenuItem editFindNext = new JMenuItem("Find Next (Ctrl + F3)");
        editFindNext.addActionListener(this);
        editFindNext.setActionCommand("Find Next");
        menuEdit.add(editFindNext);

        JMenuItem editFindPre = new JMenuItem("Find Previous (Ctrl + Shift + F3)");
        editFindPre.addActionListener(this);
        editFindPre.setActionCommand("Find Previous");
        menuEdit.add(editFindPre);

        JMenuItem editReplace = new JMenuItem("Replace (Ctrl + R)");
        editReplace.addActionListener(this);
        editReplace.setActionCommand("Replace");
        menuEdit.add(editReplace);

        JMenuItem editGoTo = new JMenuItem("Go To (Ctrl + G)");
        editGoTo.addActionListener(this);
        editGoTo.setActionCommand("Go To");
        menuEdit.add(editGoTo);

        JMenuItem editSelectAll = new JMenuItem("Select All (Ctrl + A)");
        editSelectAll.addActionListener(this);
        editSelectAll.setActionCommand("Select All");
        menuEdit.add(editSelectAll);

        JMenuItem editTime = new JMenuItem("Time/Date (Ctrl + F5)");
        editTime.addActionListener(this);
        editTime.setActionCommand("Time/Date");
        menuEdit.add(editTime);
    }

    private void format() {
        // Format option
        JMenu menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        // Font styles
        JMenuItem fontStyles = new JMenu("Font");
        fontStyles.addActionListener(this);
        fontStyles.setActionCommand("Font");
        menuFormat.add(fontStyles);

        JMenuItem arial = new JMenuItem("Arial");
        arial.addActionListener(this);
        arial.setActionCommand("Arial");
        fontStyles.add(arial);

        JMenuItem TNR = new JMenuItem("Times New Roman");
        TNR.addActionListener(this);
        TNR.setActionCommand("Times New Roman");
        fontStyles.add(TNR);

        JMenuItem calibri = new JMenuItem("Calibri");
        calibri.addActionListener(this);
        calibri.setActionCommand("Calibri");
        fontStyles.add(calibri);

        JMenuItem verdana = new JMenuItem("Verdana");
        verdana.addActionListener(this);
        verdana.setActionCommand("Verdana");
        fontStyles.add(verdana);

        JMenuItem garamond = new JMenuItem("Garamond");
        garamond.addActionListener(this);
        garamond.setActionCommand("Garamond");
        fontStyles.add(garamond);

        JMenuItem helvetica = new JMenuItem("Helvetica");
        helvetica.addActionListener(this);
        helvetica.setActionCommand("Helvetica");
        fontStyles.add(helvetica);

        JMenuItem cambria = new JMenuItem("Cambria");
        cambria.addActionListener(this);
        cambria.setActionCommand("Cambria");
        fontStyles.add(cambria);

        // Font sizes
        JMenuItem fontSize = new JMenu("Font Size");
        fontSize.addActionListener(this);
        fontSize.setActionCommand("Font Size");
        menuFormat.add(fontSize);

        JMenuItem size8 = new JMenuItem("8");
        size8.addActionListener(this);
        size8.setActionCommand("8");
        fontSize.add(size8);

        JMenuItem size9 = new JMenuItem("9");
        size9.addActionListener(this);
        size9.setActionCommand("9");
        fontSize.add(size9);

        JMenuItem size10  = new JMenuItem("10");
        size10.addActionListener(this);
        size10.setActionCommand("10");
        fontSize.add(size10);

        JMenuItem size11 = new JMenuItem("11");
        size11.addActionListener(this);
        size11.setActionCommand("11");
        fontSize.add(size11);

        JMenuItem size12 = new JMenuItem("12");
        size12.addActionListener(this);
        size12.setActionCommand("12");
        fontSize.add(size12);

        JMenuItem size14 = new JMenuItem("14");
        size14.addActionListener(this);
        size14.setActionCommand("14");
        fontSize.add(size14);

        JMenuItem size16 = new JMenuItem("16");
        size16.addActionListener(this);
        size16.setActionCommand("16");
        fontSize.add(size16);

        JMenuItem size18 = new JMenuItem("18");
        size18.addActionListener(this);
        size18.setActionCommand("18");
        fontSize.add(size18);

        JMenuItem customSize = new JMenuItem("Custom");
        customSize.addActionListener(this);
        customSize.setActionCommand("Custom Size");
        fontSize.add(customSize);
    }

    private void style() {
        // Style option
        JMenu menuStyle = new JMenu("Style");
        menuBar.add(menuStyle);

        JMenuItem styleBold = new JMenuItem("Bold");
        styleBold.addActionListener(this);
        styleBold.setActionCommand("Bold");
        menuStyle.add(styleBold);

        JMenuItem stylePlain = new JMenuItem("Plain");
        stylePlain.addActionListener(this);
        stylePlain.setActionCommand("Plain");
        menuStyle.add(stylePlain);

        JMenuItem styleItalic = new JMenuItem("Italic");
        styleItalic.addActionListener(this);
        styleItalic.setActionCommand("Italic");
        menuStyle.add(styleItalic);

        JMenuItem styleUnderline = new JMenuItem("Underline");
        styleUnderline.addActionListener(this);
        styleUnderline.setActionCommand("Underline");
        menuStyle.add(styleUnderline);

        JMenuItem styleUppercase = new JMenuItem("Uppercase");
        styleUppercase.addActionListener(this);
        styleUppercase.setActionCommand("Uppercase");
        menuStyle.add(styleUppercase);

        JMenuItem styleLowercase = new JMenuItem("Lowercase");
        styleLowercase.addActionListener(this);
        styleLowercase.setActionCommand("Lowercase");
        menuStyle.add(styleLowercase);
    }

    private void color() {
        // Color option
        JMenu menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        // Text color option
        JMenuItem textColor = new JMenu("Text Color");
        textColor.addActionListener(this);
        textColor.setActionCommand("Text Color");
        menuColor.add(textColor);

        JMenuItem colorBlack = new JMenuItem("Black");
        colorBlack.addActionListener(this);
        colorBlack.setActionCommand("Black");
        textColor.add(colorBlack);

        JMenuItem colorWhite = new JMenuItem("White");
        colorWhite.addActionListener(this);
        colorWhite.setActionCommand("White");
        textColor.add(colorWhite);

        JMenuItem colorRed = new JMenuItem("Red");
        colorRed.addActionListener(this);
        colorRed.setActionCommand("Red");
        textColor.add(colorRed);

        JMenuItem colorGreen = new JMenuItem("Green");
        colorGreen.addActionListener(this);
        colorGreen.setActionCommand("Green");
        textColor.add(colorGreen);

        JMenuItem colorBlue = new JMenuItem("Blue");
        colorBlue.addActionListener(this);
        colorBlue.setActionCommand("Blue");
        textColor.add(colorBlue);

        JMenuItem colorYellow = new JMenuItem("Yellow");
        colorYellow.addActionListener(this);
        colorYellow.setActionCommand("Yellow");
        textColor.add(colorYellow);

        JMenuItem colorPurple = new JMenuItem("Purple");
        colorPurple.addActionListener(this);
        colorPurple.setActionCommand("Purple");
        textColor.add(colorPurple);

        JMenuItem colorGray = new JMenuItem("Gray");
        colorGray.addActionListener(this);
        colorGray.setActionCommand("Gray");
        textColor.add(colorGray);

        JMenuItem customColor = new JMenuItem("Custom");
        customColor.addActionListener(this);
        customColor.setActionCommand("Custom Text Color");
        textColor.add(customColor);

        // Highlight text color option
        JMenuItem highlightColor = new JMenu("Highlight Color");
        highlightColor.addActionListener(this);
        highlightColor.setActionCommand("Highlight Color");
        menuColor.add(highlightColor);

        JMenuItem hlBlack = new JMenuItem("Black");
        hlBlack.addActionListener(this);
        hlBlack.setActionCommand("Highlight Black");
        highlightColor.add(hlBlack);

        JMenuItem hlWhite = new JMenuItem("White");
        hlWhite.addActionListener(this);
        hlWhite.setActionCommand("Highlight White");
        highlightColor.add(hlWhite);

        JMenuItem hlRed = new JMenuItem("Red");
        hlRed.addActionListener(this);
        hlRed.setActionCommand("Highlight Red");
        highlightColor.add(hlRed);

        JMenuItem hlGreen = new JMenuItem("Green");
        hlGreen.addActionListener(this);
        hlGreen.setActionCommand("Highlight Green");
        highlightColor.add(hlGreen);

        JMenuItem hlBlue = new JMenuItem("Blue");
        hlBlue.addActionListener(this);
        hlBlue.setActionCommand("Highlight Blue");
        highlightColor.add(hlBlue);

        JMenuItem hlYellow = new JMenuItem("Yellow");
        hlYellow.addActionListener(this);
        hlYellow.setActionCommand("Highlight Yellow");
        highlightColor.add(hlYellow);

        JMenuItem hlPurple = new JMenuItem("Purple");
        hlPurple.addActionListener(this);
        hlPurple.setActionCommand("Highlight Purple");
        highlightColor.add(hlPurple);

        JMenuItem hlGray = new JMenuItem("Gray");
        hlGray.addActionListener(this);
        hlGray.setActionCommand("Highlight Gray");
        highlightColor.add(hlGray);

        JMenuItem customHL = new JMenuItem("Custom");
        customHL.addActionListener(this);
        customHL.setActionCommand("Custom Highlight Color");
        highlightColor.add(customHL);

        // Background color option
        JMenu backgroundColor = new JMenu("Background Color");
        menuColor.add(backgroundColor);

        JMenuItem bgBlack = new JMenuItem("Black");
        bgBlack.addActionListener(this);
        bgBlack.setActionCommand("Background Black");
        backgroundColor.add(bgBlack);

        JMenuItem bgWhite = new JMenuItem("White");
        bgWhite.addActionListener(this);
        bgWhite.setActionCommand("Background White");
        backgroundColor.add(bgWhite);

        JMenuItem bgRed = new JMenuItem("Red");
        bgRed.addActionListener(this);
        bgRed.setActionCommand("Background Red");
        backgroundColor.add(bgRed);

        JMenuItem bgGreen = new JMenuItem("Green");
        bgGreen.addActionListener(this);
        bgGreen.setActionCommand("Background Green");
        backgroundColor.add(bgGreen);

        JMenuItem bgBlue = new JMenuItem("Blue");
        bgBlue.addActionListener(this);
        bgBlue.setActionCommand("Background Blue");
        backgroundColor.add(bgBlue);

        JMenuItem bgYellow = new JMenuItem("Yellow");
        bgYellow.addActionListener(this);
        bgYellow.setActionCommand("Background Yellow");
        backgroundColor.add(bgYellow);

        JMenuItem bgPurple = new JMenuItem("Purple");
        bgPurple.addActionListener(this);
        bgPurple.setActionCommand("Background Purple");
        backgroundColor.add(bgPurple);

        JMenuItem bgGray = new JMenuItem("Gray");
        bgGray.addActionListener(this);
        bgGray.setActionCommand("Background Gray");
        backgroundColor.add(bgGray);

        JMenuItem customBG = new JMenuItem("Custom");
        customBG.addActionListener(this);
        customBG.setActionCommand("Custom Background Color");
        backgroundColor.add(customBG);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String command = e.getActionCommand();

        switch (command) {
            // File option
            case "New Tab":
                file.newFile();
                break;
            case "Open":
                file.open(textPane, main.getFrame());
                break;
            case "Save":
                file.save(textPane, main.getFrame());
                break;
            case "Save As":
                file.saveAs(textPane, main.getFrame());
                break;
            case "Print":
                file.print(textPane, main.getFrame());
                break;
            case "Exit":
                file.exit();
                break;
            // Edit option
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            case "Cut":
                edit.cut();
                break;
            case "Clear":
                edit.clear(textPane);
                break;
            case "Copy":
                edit.copy();
                break;
            case "Paste":
                edit.paste();
                break;
            case "Find":
                edit.find(textPane);
                break;
            case "Find Next":
                edit.findNext(textPane);
                break;
            case "Find Previous":
                edit.findPrevious(textPane);
                break;
            case "Replace":
                edit.replace(textPane);
                break;
            case "Go To":
                edit.goTo(textPane);
                break;
            case "Select All":
                edit.selectAll(textPane);
                break;
            case "Time/Date":
                edit.time(textPane);
                break;
            // Format option
            // Font style list
            case "Arial":
                format.fontType(textPane, "Arial");
                break;
            case "Times New Roman":
                format.fontType(textPane, "Times New Roman");
                break;
            case "Calibri":
                format.fontType(textPane, "Calibri");
                break;
            case "Verdana":
                format.fontType(textPane, "Verdana");
                break;
            case "Garamond":
                format.fontType(textPane, "Garamond");
                break;
            case "Helvetica":
                format.fontType(textPane, "Helvetica");
                break;
            case "Cambria":
                format.fontType(textPane, "Cambria");
                break;
            // Font size list
            case "8":
                format.fontSize(textPane, 8);
                break;
            case "9":
                format.fontSize(textPane, 9);
                break;
            case "10":
                format.fontSize(textPane, 10);
                break;
            case "11":
                format.fontSize(textPane, 11);
                break;
            case "12":
                format.fontSize(textPane, 12);
                break;
            case "14":
                format.fontSize(textPane, 14);
                break;
            case "16":
                format.fontSize(textPane, 16);
                break;
            case "18":
                format.fontSize(textPane, 18);
                break;
            case "Custom Size":
                format.fontSize(textPane, Integer.parseInt(JOptionPane.showInputDialog(textPane, "Enter font size", "Font Size", JOptionPane.QUESTION_MESSAGE)));
                break;
            // Style option
            case "Bold":
                style.fontStyles(textPane, "Bold");
                break;
            case "Plain":
                style.fontStyles(textPane, "Plain");
                break;
            case "Italic":
                style.fontStyles(textPane, "Italic");
                break;
            case "Underline":
                style.fontStyles(textPane, "Underline");
                break;
            case "Uppercase":
                style.textCase(textPane, "Upper");
                break;
            case "Lowercase":
                style.textCase(textPane, "Lower");
                break;
            // Color option
            // Text color list
            case "Black":
                colors.textColor(textPane, Color.black);
                break;
            case "White":
                colors.textColor(textPane, Color.white);
                break;
            case "Red":
                colors.textColor(textPane, Color.red);
                break;
            case "Green":
                colors.textColor(textPane, Color.green);
                break;
            case "Blue":
                colors.textColor(textPane, Color.blue);
                break;
            case "Yellow":
                colors.textColor(textPane, Color.yellow);
                break;
            case "Purple":
                colors.textColor(textPane, new Color(160, 32, 240));
                break;
            case "Gray":
                colors.textColor(textPane, Color.gray);
                break;
            // Color highlight list
            case "Highlight Black":
                colors.highlightColor(textPane, Color.black);
                break;
            case "Highlight White":
                colors.highlightColor(textPane, Color.white);
                break;
            case "Highlight Red":
                colors.highlightColor(textPane, Color.red);
                break;
            case "Highlight Green":
                colors.highlightColor(textPane, Color.green);
                break;
            case "Highlight Blue":
                colors.highlightColor(textPane, Color.blue);
                break;
            case "Highlight Yellow":
                colors.highlightColor(textPane, Color.yellow);
                break;
            case "Highlight Purple":
                colors.highlightColor(textPane, new Color(160, 32, 240));
                break;
            case "Highlight Gray":
                colors.highlightColor(textPane, Color.gray);
                break;
            // Background color list
            case "Background Black":
                colors.backgroundColor(textPane, Color.black);
                break;
            case "Background White":
                colors.backgroundColor(textPane, Color.white);
                break;
            case "Background Red":
                colors.backgroundColor(textPane, Color.red);
                break;
            case "Background Green":
                colors.backgroundColor(textPane, Color.green);
                break;
            case "Background Blue":
                colors.backgroundColor(textPane, Color.blue);
                break;
            case "Background Yellow":
                colors.backgroundColor(textPane, Color.yellow);
                break;
            case "Background Purple":
                colors.backgroundColor(textPane, new Color(160, 32, 240));
                break;
            case "Background Gray":
                colors.backgroundColor(textPane, Color.gray);
                break;
            // Custom color options
            case "Custom Text Color":
                colors.textColor(textPane, JColorChooser.showDialog(textPane, "Custom Color Menu", null));
                break;
            case "Custom Highlight Color":
                colors.highlightColor(textPane, JColorChooser.showDialog(textPane, "Custom Color Menu", null));
                break;
            case "Custom Background Color":
                colors.backgroundColor(textPane, JColorChooser.showDialog(textPane, "Custom Color Menu", null));
                break;
            default: JOptionPane.showMessageDialog(textPane, "Option not Available", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public JMenuBar getBottomBar() {
        return bottomBar;
    }
}