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
   @author Caitlin Scarberry
   @version CS56 Winter 2016
*/

public class PascalTriangleComponent extends JComponent {  

    // Variables
    private int centerX = 375; // x-coordinate that determines where top of Pascal's Triangle is drawn 
    private int centerY = 50; // y-coordinate that determines where top of Pascal's Triangle is drawn
    private int spacing = 10; // Determines how big each square is
    private int totalRows = 8; // Total number of rows to be drawn, if you change this number you need to add the respective numbers into the integer array "numbersInPascalTriangle"
    private int count = 0; // Total number of squares
    private ArrayList<Rectangle> squares = new ArrayList<Rectangle>();

    /*important note: because the max integer size is 2,147,483,647, any
      triangle with more than 33 rows will not be calculated correctly*/
    private int [] numbersInPascalTriangle;

    public PascalTriangleComponent(int rows){
    	totalRows = rows;
    	numbersInPascalTriangle = new int[rows*(rows+1)/2];
    	numbersInPascalTriangle[0]=1;
    	numbersInPascalTriangle[1]=1;
    	numbersInPascalTriangle[2]=1;
    	int currentRow = 2;
    	int currentColumn = 0;
    	for(int i = 3; i < numbersInPascalTriangle.length; i++){
			int startPrevRow = ((currentRow-1)*(currentRow))/2;
			int leftParent = currentColumn>0? numbersInPascalTriangle[startPrevRow + currentColumn-1]:0;
			int rightParent = currentColumn<(currentRow) ? numbersInPascalTriangle[startPrevRow + currentColumn]:0;
			numbersInPascalTriangle[i] = leftParent+rightParent;

    		currentColumn++;
    		if(currentColumn>currentRow){
    			currentColumn = 0;
    			currentRow++;
    		}
    	}
    }

    // Paint Method
    public void paintComponent(Graphics g) {  
		Graphics2D g2 = (Graphics2D) g;

		//Scale the squares so that the triangle takes up the panel
		spacing = (this.getHeight()-centerY-50)/(this.totalRows*6);

		// Adds Rectangles into ArrayList
		squares = new ArrayList<Rectangle>();
		count = 0;
		for (int i = 0; i < totalRows; i++) { // i = rows
		    for (int j = 0; j <= i; j++) { // j = index within row
			squares.add(new Rectangle(centerX + 6*j*spacing - 3*i*spacing, centerY + 6*i*spacing, 6*spacing, 6*spacing)); // clever formula for aligning squares ;)
		        count++; 
		    }
		}

		// Draws the squares and numbers and fills them in appropriately
		for (int i = 0; i < count; i++) {
			//System.out.println("adding square");
		    g2.setColor(Color.RED); 
		    g2.draw(squares.get(i));

		    g2.setColor(Color.BLACK); 
		    if (numbersInPascalTriangle[i] % 2 == 1) // Fill in square only if ODD
			g2.fillRect((int)squares.get(i).getX()+1, (int)squares.get(i).getY()+1, (int)squares.get(i).getWidth()-1, (int)squares.get(i).getHeight()-1); // The adding and subtracting of 1 is to have a "border" show up

		    g2.setColor(new Color(0,50,255)); // Blue
		    
		    String num = ""+numbersInPascalTriangle[i];
		    //make font scale with number length
		    g2.setFont(new Font("default", Font.BOLD, (int)(spacing*(3.1-.3*num.length()) ) ));

		    if (numbersInPascalTriangle[i] < 10) // Single digit numbers alignment
			g2.drawString(Integer.toString(numbersInPascalTriangle[i]), (int)squares.get(i).getX() + 2*spacing + 2, (int)squares.get(i).getY() + 4*spacing - 1); // The constants just fine-tune alignment
		    if (numbersInPascalTriangle[i] >= 10) // Double digit numbers alignment
			g2.drawString(Integer.toString(numbersInPascalTriangle[i]), (int)squares.get(i).getX() + 1*spacing + 2 , (int)squares.get(i).getY() + 4*spacing - 1); // The constants just fine-tune alignment 
		}

    } // End Paint Method


} // End class
