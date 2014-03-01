package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.SierpinskiTriangle;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
   Runs a GUI version of the first project. (Originally xml file)
   Needs improvement because JEditorPane cannot display xml, only html
   @author George Shih
   @version CS56 Winter 2014
*/

public class SierpinskiTriangleGUI extends JFrame implements ActionListener {

    // Variables
    private String fstr = "";
    private JButton button;

    // Main Method
    public static void main(String [] args) {
	SierpinskiTriangleGUI gui = new SierpinskiTriangleGUI();
	gui.setString();
	gui.runGUI();
    }

    // Grab the output.html file and put the content into a string
    public void setString() {
	File file = new File("output.html"); // Grab XML file
	FileInputStream input = null;
	try {
	    input = new FileInputStream(file);
	   
	    System.out.println("Total file size to read (in bytes) : "
			       + input.available());
	    
	    int content;
	    while ((content = input.read()) != -1) {
		// Convert to char and append it
		fstr += (char) content;
	    }
	    //System.out.println(fstr);  // Testing purposes
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (input != null)
		    input.close();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}
    }

        /*
	// (If needed) Adds backslashes on all current quotation marks 
	String backslash = "\\";
	String quotation = "\"";
	String fstr2 = fstr.replace("\"", backslash + quotation);
	*/

	// GUI
    public void runGUI() {
	try {
	    // Feel free to append to variable "html"
	    String html = "";
	    html += "<html><head><title>Sierpinski Triangle GUI</title></head>";
	    html += "<body bgcolor='#00FFFF'><hr/><font size=20>";
	    html += fstr;  // String version of the XML file
	    html += "</font><hr/></body></html>";

	    
	    // JFrame and JButton
	    JFrame frame = new JFrame();
	    button = new JButton("Color Picker");
	    button.addActionListener(this);
	    frame.getContentPane().add(BorderLayout.NORTH, button);

	    // JEditorPane
	    JEditorPane editor = new JEditorPane("text/html", html);
	    frame.add(editor);

	    frame.setVisible(true);
	    frame.setSize(600,600);
	    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	catch(Exception e) {
	    e.printStackTrace();
	    System.out.println("Some problem has occured" + e.getMessage());
	}
    }
    
    // What's performed when button is clicked
    public void actionPerformed(ActionEvent event) {
	button.setText("TODO: Launch the Color Picker");
    }


}




/*
// Method 2 with hard coded classpath URL
// JTextPane also cannot display the XML code like in a browser

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class SierpinskiTriangleGUI {

    public static void main(String args[]) {  
	JTextPane textpane = new JTextPane();
	JScrollPane scroll  = new JScrollPane();
	scroll.getViewport().add(textpane);
	JFrame frame = new JFrame();
	frame.getContentPane().add(scroll);
	frame.pack();
	frame.setSize(600,600);
	frame.setVisible(true); 
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	try {
	    URL url = new URL("file:///cs/student/wshih/cs56/cs56-discretemath-towers-sierpinski/output.html");
	    textpane.setPage(url);
	} 
	catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
*/
