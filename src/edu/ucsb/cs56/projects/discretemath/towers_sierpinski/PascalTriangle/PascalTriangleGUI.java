package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.PascalTriangle;

import javax.swing.JFrame;

/**
   Runs a GUI which shows Pascal's Triangle and it's relations with Sierpinski's Triangle and the Tower of Hanoi.
   @author George Shih
   @version CS56 Winter 2014
 */

public class PascalTriangleGUI extends JFrame {

    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setSize(800,600);
	frame.setTitle("Pascal's Triangle GUI");
	frame.setVisible(true);
	PascalTriangleComponent component = new PascalTriangleComponent();
	frame.add(component);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}





