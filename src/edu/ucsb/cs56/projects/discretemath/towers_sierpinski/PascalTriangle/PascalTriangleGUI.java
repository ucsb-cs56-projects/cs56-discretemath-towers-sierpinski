package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.PascalTriangle;

import javax.swing.JFrame;

/**
 *  Runs a GUI which shows Pascal's Triangle and it's relations with Sierpinski's Triangle and the Tower of Hanoi.
 *  @author George Shih
 *	@author	Caitlin Scarberry
 *  @version CS56 Winter 2016
 */

public class PascalTriangleGUI extends JFrame {

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

		JFrame frame = new JFrame();
		frame.setSize(800,600);
		frame.setTitle("Pascal's Triangle GUI");
		PascalTriangleComponent component = new PascalTriangleComponent(rows);
		frame.add(component);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
}





