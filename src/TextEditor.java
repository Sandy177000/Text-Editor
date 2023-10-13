import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // declaring properties of the editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut , copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();
        frame.setTitle("Text Editor");
        textArea = new JTextArea();

        menuBar = new JMenuBar();
        //menus
        file = new JMenu("File");
        edit = new JMenu("Edit");


        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        file.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        // add text area to the frame
        frame.add(textArea);
        // set menubar to frame
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane =  new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);
        // set dimensions
        frame.setBounds(500,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut){
            // perform cut operation
            textArea.cut();
        }
        if(e.getSource()==copy){
            // perform copy operation
            textArea.copy();
        }
        if(e.getSource()==paste){
            // perform paste operation
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            // perform selectAll operation
            textArea.selectAll();
        }
        if(e.getSource()==close){
            // perform selectAll operation
            System.exit(0);
        }

        if(e.getSource()==openFile){
            // open file chooser dialog
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if the open button is clicked
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String path = file.getPath();

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // reading the selected file line by line and storing setting the text to the text area
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate+"\n";
                    }
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }

        if(e.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption== JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write the text of the text area to file
                    textArea.write(bufferedWriter);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if(e.getSource()==newFile){
            TextEditor textEditor = new TextEditor();
        }

    }

    public static void main(String[] args)
    {
        TextEditor textEditor = new TextEditor();

    }
}