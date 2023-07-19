
/*
 This is My Submission: Saksham Srivastava 2110110452
 This is entirely my project and no part of it is copied or plagerised .

    READ-ME!!
    Open, Save and Exit = Used to Open a new File,Save the current Document and Exit from editor.
    Cut,Copy,Paste = Used to Cut , Copy and Paste Selected Text
    Find = Used to find a particular word
    Replace = Used to replace a given word with some other word
    Count= tells the word count and character count of the document
    Find All= Finds all instances of a given word
    Replace All = Replaces all instances of a given word.

    B- Bolds the document
    I-Italics the document.

*/

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.BadLocationException;
/*
Text Editor in Java using Swing and Event Handling
*/
public class TextProEditor implements ActionListener
{
    JComboBox fonts;
    JTextArea textArea;JMenuItem Exit,Cut,Copy,Paste,Save,Open,Find,Replace,AboutUs;
    JToggleButton B,U,I; JComboBox fontBox;
    JFrame f;JPanel p1,p2,p3;
    boolean IsBold;
    int select_start=-1;int startIndex=0,select_end;
    JLabel FindLabel,ReplaceLabel;
    JTextField FindField,ReplaceField;
    JButton FindNext,ReplaceNext,FindAll,ReplaceAll,LA,RA,CA,JA,Count;int currentPosition;

    TextProEditor()
    {

        IsBold=false;

        f=new JFrame("TextProEditor");
        p1=new JPanel();
        p1.setBorder(BorderFactory.createLineBorder(Color.black));
        p1.setSize(2080,1190);
        //p1.setBackground(Color.lightGray);

       
        JMenuBar jm=new JMenuBar();
        JMenu File=new JMenu("File");
        JMenu Edit=new JMenu("Edit");
        JMenu Review=new JMenu("Review");
        JMenu Help=new JMenu("Help");

        Open=new JMenuItem("Open");
        Save=new JMenuItem("Save");
        Exit=new JMenuItem("Exit");
        Exit.addActionListener(this);
        Save.addActionListener(this);
        Open.addActionListener(this);

        Cut=new JMenuItem("Cut");
        Copy=new JMenuItem("Copy");
        Paste=new JMenuItem("Paste");
       
        Find=new JMenuItem("Find");
        Replace=new JMenuItem("Replace");

        AboutUs=new JMenuItem("About Us");
        AboutUs.addActionListener(this);

        File.add(Open);
        File.add(Save);
        File.add(Exit);

        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        Review.add(Find);
        Review.add(Replace);

        Help.add(AboutUs);

        jm.add(File);
        jm.add(Edit);
        jm.add(Review);
        jm.add(Help);

        f.setJMenuBar(jm);
        GridLayout gl=new GridLayout();gl.setHgap(10);
        p1.setLayout(gl);
        JPanel p2,p3;


        p2=new JPanel();
        p3=new JPanel();
        p2.setBorder(BorderFactory.createLineBorder(Color.black));
        p3.setBorder(BorderFactory.createLineBorder(Color.black));

        p2.setVisible(true);
        p3.setVisible(true);

        p1.add(p2);
        p1.add(p3);

        p1.setVisible(true);
        f.add(p1);

        f.setDefaultCloseOperation(3);
        f.setMinimumSize(new Dimension(950, 700));

        f.setSize(2080,1200);
        
        //Designing sub-panels for Font, Alignment etc.

        JPanel TextOptions=new JPanel();
        B=new JToggleButton("B");//Bold
        U=new JToggleButton("U");//Underlined
        I=new JToggleButton("I");//Italics

        B.addActionListener(this);
        U.addActionListener(this);
        I.addActionListener(this);
        Find.addActionListener(this);
        Replace.addActionListener(this);
        

        TextOptions.add(B);TextOptions.add(I);TextOptions.add(U);
        TextOptions.setBounds(0,0,10,10);
        p2.add(TextOptions);

        JPanel TextAlignment=new JPanel();
        LA=new JButton("LA");//Bold
        RA=new JButton("RA");//Underlined
        CA=new JButton("CA");//Italics
        JA=new JButton("JA");//Italics
        TextAlignment.add(LA);TextAlignment.add(RA);TextAlignment.add(CA);TextAlignment.add(JA);
        RA.addActionListener(this);
        LA.addActionListener(this);
        p2.add(TextAlignment);

        JPanel FontNames=new JPanel();


        p2.add(FontNames);
        JSpinner spinner=new JSpinner();
        spinner.setPreferredSize(new Dimension(50,20));
        spinner.setValue(20);
        p2.add(spinner); 
        
        String[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.US);
        
        fontBox=new JComboBox<>(fonts);
        fontBox.addActionListener(this);
        p2.add(fontBox);

        JPanel test=new JPanel();
        test.setVisible(true);
        test.setPreferredSize(new Dimension(900,1000));
        
        p2.add(test);
        FindLabel=new JLabel("Find");
        FindLabel.setPreferredSize(new Dimension(900,20));
        FindField=new JTextField();
        FindField.setPreferredSize(new Dimension(900,30));

        ReplaceLabel=new JLabel("Replace");
        ReplaceLabel.setPreferredSize(new Dimension(900,20));
        ReplaceField=new JTextField();
        ReplaceField.setPreferredSize(new Dimension(900,30)); 

        Count=new JButton("Count");
        Count.setPreferredSize(new Dimension(90,25));
        Count.addActionListener(this);

        test.add(FindLabel);
        test.add(FindField);
        test.add(ReplaceLabel);
        test.add(ReplaceField);
        test.add(Count);

        FindAll =new JButton("Find All");
        test.add(FindAll);
        FindNext =new JButton("Find Next");
        test.add(FindNext);
        ReplaceNext =new JButton("Replace");
        test.add(ReplaceNext);
        ReplaceAll =new JButton("Replace All");
        test.add(ReplaceAll);

        FindNext.addActionListener(this);
        ReplaceAll.addActionListener(this);
        ReplaceNext.addActionListener(this);
        FindAll.addActionListener(this);

        textArea=new JTextArea();
        JScrollPane pane=new JScrollPane(textArea);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(900,750));
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea.setLineWrap(true);
        textArea.getWrapStyleWord();

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)spinner.getValue()));
            }

        });

        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);



        test.add(pane);
        
        JLabel SketchPad=new JLabel("                                                                                                                           SketchPad");
        SketchPad.setPreferredSize(new Dimension(900,20));
        p3.setAlignmentX(Component.CENTER_ALIGNMENT);
        p3.add(SketchPad);

        JButton Rectangle =new JButton("Rectangle");
        p3.add(Rectangle);
        JButton Oval =new JButton("Oval");
        p3.add(Oval);
        JButton Line =new JButton("Line");
        p3.add(Line);
        JButton Triangle =new JButton("Triangle");
        p3.add(Triangle);
        JButton Pentagon =new JButton("Pentagon");
        p3.add(Pentagon);
        JButton Clear =new JButton("Clear");
        p3.add(Clear);

        JPanel drawPanel=new JPanel();
        drawPanel.setBackground(new Color(0xD3D3D3));
        drawPanel.setPreferredSize(new Dimension(700,700));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        p3.setAlignmentY(50);
        p3.add(drawPanel);
        f.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Exit)
        {
            System.exit(0);
        }
        if(e.getSource()==Cut)
        {
            textArea.cut();
        }
        if(e.getSource()==Copy)
        {
            textArea.copy();
        }
        if(e.getSource()==Paste)
        {
            textArea.paste();
        }
        if(e.getSource()==B)
        {
            if(B.isSelected()==true)
            textArea.setFont(textArea.getFont().deriveFont(Font.BOLD, textArea.getFont().getSize()));
            if(B.isSelected()==false)
            textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN, textArea.getFont().getSize()));
        }
        if(e.getSource()==I)
        {
            if(I.isSelected()==true)
            textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC, textArea.getFont().getSize()));
            if(I.isSelected()==false)
            textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN, textArea.getFont().getSize()));
        }
        if(e.getSource()==Save)
        {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showSaveDialog(null);
            if(response== JFileChooser.APPROVE_OPTION)
            {
                File file;
                PrintWriter fileOut=null;
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                fileOut=new PrintWriter(file);
                fileOut.println(textArea.getText());
                }
                catch (FileNotFoundException e1)
                {
                    e1.printStackTrace();

                }
                finally{
                    fileOut.close();
                }
            }
        }
        if(e.getSource()==AboutUs)
        {
            JOptionPane.showMessageDialog(null, "This is a text editor made by Saksham Srivastava :) \n Currently Second Year CSE at SNU, 211011045","About Me",JOptionPane.INFORMATION_MESSAGE);


        }
        if(e.getSource()==fontBox)
        {
            textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
        }
        if(e.getSource()==Open)
        {

            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter=new FileNameExtensionFilter("Text files","txt");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null);
            if(response== JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn=null;
                try{
                fileIn=new Scanner(file);
                if(file.isFile())
                    {
                    while(fileIn.hasNextLine())
                        {
                        String line=fileIn.nextLine()+"\n";
                        textArea.append(line);
                        }
                    }
                }
                catch (FileNotFoundException e1)
                {
                    e1.printStackTrace();

                }
                finally{
                    fileIn.close();
                }
            }
            
        }
        if(e.getSource()==FindNext)
        {
            if(find())
            JOptionPane.showMessageDialog(null, "Found \"" + FindField.getText() + "\"!");
            
            
        }
        if(e.getSource()==RA)
        {
            textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(e.getSource()==LA)
        {
            textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
        if(e.getSource()==ReplaceNext)
        {
            try
            {
                find();
                if (select_start != -1)
                textArea.replaceSelection(ReplaceField.getText());
            }
            catch(NullPointerException ee)
            {
                System.out.print("Null Pointer Exception: "+ee);
        
            }
        }
        if(e.getSource()==ReplaceAll)
        {
            textArea.setText(textArea.getText().replaceAll(FindField.getText() , ReplaceField.getText()));
        }
        if(e.getSource()==FindAll)
        {
        
                String textAreaText = textArea.getText().toLowerCase();
                String textFieldText = FindField.getText().toLowerCase();
                Highlighter highlight = textArea.getHighlighter();
                highlight.removeAllHighlights();
                while(textAreaText.indexOf(textFieldText,currentPosition)!= -1)
                {
                    int index = textAreaText.indexOf(textFieldText,currentPosition);
                    int length = textFieldText.length();
                    try
                    {
                        highlight.addHighlight(index, index+length, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                    }
                    catch(BadLocationException ex){
                        ex.printStackTrace();
                    }
                    currentPosition = index+length;
                }
                if(currentPosition >= textAreaText.length())
                {
                    currentPosition = 0;
                }
                if(textAreaText.indexOf(textFieldText,currentPosition) == -1)
                {
                    currentPosition = 0;
                }
            
        }
        if(e.getSource()==Count)
        {
            int charCount=textArea.getText().length();
            String wordCount[]=textArea.getText().split("\\s");
            JOptionPane.showMessageDialog(null,"Character Count : "+charCount+"\n"+"Word Count is "+wordCount.length,"Word Count",JOptionPane.INFORMATION_MESSAGE);

        }
        if(e.getSource()==Find)
        {
            if(find())
            JOptionPane.showMessageDialog(null, "Found !!  " + FindField.getText() + "!");

        }
        if(e.getSource()==Replace)
        {
            try
            {
                find();
                if (select_start != -1)
                textArea.replaceSelection(ReplaceField.getText());
            }
            catch(NullPointerException ee)
            {
                System.out.print("Null Pointer Exception: "+ee);
        
            }
        }
    }
    boolean find()
    {
        select_start = textArea.getText().indexOf(FindField.getText().toLowerCase());
        if(select_start == -1)
        {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find \"" + FindField.getText() + "\"!");
            return false;
        }
        if(select_start == textArea.getText().lastIndexOf(FindField.getText().toLowerCase()))
        {
            startIndex = 0;
        }
        currentPosition = select_start + FindField.getText().length();
        textArea.select(select_start, currentPosition);
        return true;
       
        
    }
    public static void main(String args[])
    {
        new TextProEditor();
    }

}
