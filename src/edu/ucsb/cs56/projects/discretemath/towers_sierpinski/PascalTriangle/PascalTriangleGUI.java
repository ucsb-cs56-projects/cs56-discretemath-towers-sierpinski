package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.PascalTriangle;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.*;

/**
 *  Runs a GUI which shows Pascal's Triangle and it's relations with Sierpinski's Triangle and the Tower of Hanoi.
 *  @author George Shih
 *  @author Caitlin Scarberry
 *  @author Sierra Schwellenbach
 *  @version CS56 Winter 2016
 */

public class PascalTriangleGUI extends JFrame implements ActionListener {

    JButton button;
    JTextField field;
    PascalTriangleComponent component;
    JFrame frame;
        
    public static void main(String[] args) {
	int rows = 8;
	if(args.length>0)
	    try{
		rows = Integer.parseInt(args[0]);
		if(rows<=0)
		    throw new NumberFormatException();
	    }
	    catch(NumberFormatException e){
		System.err.println("Please input a valid number of rows (an integer >= 1)");
		System.exit(0);
	    }

	PascalTriangleGUI gui = new PascalTriangleGUI();
	gui.go(rows);
    }
    
    public void go(int rows){

	frame = new JFrame();
		
	frame.setSize(800,600);
	frame.setTitle("Pascal's Triangle GUI");
	field = new JTextField();
	field.setBounds(5, 10, 50, 20);
	frame.add(field);	
	button = new JButton("Redo with new row amount");
	button.setBounds(55, 10, 230, 20);
	button.addActionListener(this);
	frame.add(button);
	component = new PascalTriangleComponent(rows);
	frame.add(component);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
	String input = field.getText();
	int numRows = 0;
	if(input.equals(""))
	    return;
	try{
	    numRows = Integer.parseInt(input);
	}
	catch(Exception e){
	    //don't do anything
	}
	frame.getContentPane().remove(component);
	component = new PascalTriangleComponent(numRows);
	frame.add(component);
	frame.getContentPane().invalidate();
	frame.getContentPane().validate();
	
    }

}






