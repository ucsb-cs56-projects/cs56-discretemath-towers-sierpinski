package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.PascalTriangle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.JComponent;
import java.util.ArrayList;

import java.awt.Rectangle;  // squares and rectangles
import java.awt.Color; // class for Colors

/**
   The component that actually draws Pascal's Triangle. Everything is decently scalable besides the numbers in Pascal's Triangle which are hard coded. Just remember that there must be the same number of squares as numbers in Pascal's Triangle.
   @author George Shih 
   @version CS56 Winter 2014
*/

public class PascalTriangleComponent extends JComponent {  

    // Variables
    private int centerX = 375; // x-coordinate that determines where top of Pascal's Triangle is drawn 
    private int centerY = 50; // y-coordinate that determines where top of Pascal's Triangle is drawn
    private int spacing = 10; // Determines how big each square is
    private int totalRows = 8; // Total number of rows to be drawn, if you change this number you need to add the respective numbers into the integer array "numbersInPascalTriangle"
    private int count = 0; // Total number of squares
    private ArrayList<Rectangle> squares = new ArrayList<Rectangle>();

    private int [] numbersInPascalTriangle = {1,1,1,1,2,1,1,3,3,1,1,4,6,4,1,1,5,10,10,5,1,1,6,15,20,15,6,1,1,7,21,35,35,21,7,1}; // HARD CODED, make sure to have the same number of elements as squares or you'll get an Array Index Out Of Bounds Exception


    // Paint Method
    public void paintComponent(Graphics g) {  

	Graphics2D g2 = (Graphics2D) g;
	g2.setFont(new Font("default", Font.BOLD, 24)); // Text is bold and size 24

	// Adds Rectangles into ArrayList
	for (int i = 0; i < totalRows; i++) { // i = rows
	    for (int j = 0; j <= i; j++) { // j = index within row
		squares.add(new Rectangle(centerX + 6*j*spacing - 3*i*spacing, centerY + 6*i*spacing, 6*spacing, 6*spacing)); // clever formula for aligning squares ;)
	        count++; 
	    }
	}

	// Draws the squares and numbers and fills them in appropriately
	for (int i = 0; i < count; i++) {
	    g2.setColor(Color.RED); 
	    g2.draw(squares.get(i));

	    g2.setColor(Color.BLACK); 
	    if (numbersInPascalTriangle[i] % 2 == 1) // Fill in square only if ODD
		g2.fillRect((int)squares.get(i).getX()+1, (int)squares.get(i).getY()+1, (int)squares.get(i).getWidth()-1, (int)squares.get(i).getHeight()-1); // The adding and subtracting of 1 is to have a "border" show up

	    g2.setColor(new Color(0,50,255)); // Blue
	    if (numbersInPascalTriangle[i] < 10) // Single digit numbers alignment
		g2.drawString(Integer.toString(numbersInPascalTriangle[i]), (int)squares.get(i).getX() + 2*spacing + 2, (int)squares.get(i).getY() + 4*spacing - 1); // The constants just fine-tune alignment
	    if (numbersInPascalTriangle[i] >= 10) // Double digit numbers alignment
		g2.drawString(Integer.toString(numbersInPascalTriangle[i]), (int)squares.get(i).getX() + 1*spacing + 2 , (int)squares.get(i).getY() + 4*spacing - 1); // The constants just fine-tune alignment 
	}

    } // End Paint Method


} // End class
