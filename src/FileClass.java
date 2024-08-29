import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;

public class FileClass {
    String fileName;
    String fileAddress;
    Main main;

    public FileClass(Main main) {
        this.main = main;
    }

    // Opens new file
    public void newFile() {
        new Main();
    }

    // opens File Explorer
    public void open(JTextPane textPane, JFrame frame) {
        FileDialog fileDialog = new FileDialog(frame, "Open", FileDialog.LOAD);
        fileDialog.setLocation(50, 10);
        fileDialog.setVisible(true);

        // Initializes fileName and fileAddress
        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
        }

        // The name of the file and directory must be initialized
        if (fileName != null && fileAddress != null) {
            try {
                // load file contents
                BufferedReader buffReader = new BufferedReader(new FileReader(fileAddress + fileName));
                textPane.read(buffReader, null);
                buffReader.close();
                textPane.requestFocus();
                frame.setTitle(fileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(frame, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // File does not exist
        } else {
            JOptionPane.showMessageDialog(frame, "No file opened.", "Canceled", JOptionPane.WARNING_MESSAGE);
        }
        //System.out.println(fileAddress + fileName);
    }

    public void save(JTextPane textPane, JFrame frame) {
        if (fileName != null && fileAddress != null) {
            // Updates the current file with new textField content when fileName is specified
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAddress + File.separator + fileName))) {
                writer.write(textPane.getText());
                JOptionPane.showMessageDialog(frame, "File Saved Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "File has not been created.", "Error", JOptionPane.WARNING_MESSAGE);
            saveAs(textPane, frame);
        }
    }

    public void saveAs(JTextPane textPane, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();

        // Show the save dialog for file explorer
        int selection = fileChooser.showSaveDialog(frame);

        // If the "Yes" button has been selected, saving the new file
        if (selection == JFileChooser.APPROVE_OPTION) {
            File fileSave = fileChooser.getSelectedFile();
            // Ensure the file has an extension as a full path
            fileSave = new File(fileSave.getAbsolutePath() + ".txt");

            // Writes to the chosen file and writes the content of the text area to the selected output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSave))) {
                writer.write(textPane.getText());
                JOptionPane.showMessageDialog(frame, "File Created Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error creating file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Sets name and address of the selected path
            fileName = fileSave.getName();
            fileAddress = fileSave.getParent();

            frame.setTitle(fileName);
        }
    }

    public void print(JTextPane textPane, JFrame frame) {
        if (fileAddress != null && fileName != null) {
            // Values used to access file transfer
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(textPane.getPrintable(null, null));
            boolean print = job.printDialog();

            // If the user confirms the print dialog
            if (print) {
                try {
                    job.print();
                    JOptionPane.showMessageDialog(frame, "File successfully saved as Adobe File.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (PrinterException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error saving print file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // If a file hasn't been saved or opened
        } else {
            JOptionPane.showMessageDialog(frame, "File has not been saved.", "Error", JOptionPane.WARNING_MESSAGE);
            saveAs(textPane, frame); // Opens save as
        }
    }

    // Closes application
    public void exit() {
        System.exit(1);
    }
}