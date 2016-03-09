package edu.ucsb.cs56.projects.discretemath.towers_sierpinski.PascalTriangle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.awt.FontMetrics;
import java.awt.geom.*;
import java.awt.Shape;
import java.awt.*;

import java.awt.Rectangle;  // squares and rectangles
import java.awt.Color; // class for Colors

/**
   The component that actually draws Pascal's Triangle. Everything is decently scalable besides the numbers in Pascal's Triangle which are hard coded. Just remember that there must be the same number of squares as numbers in Pascal's Triangle.
   @author George Shih
   @author Caitlin Scarberry
   @author Sierra Schwellenbach
   @version CS56 Winter 2016
*/

public class PascalTriangleComponent extends JComponent {  

    // Variables
    private int centerX = 375; // x-coordinate that determines where top of Pascal's Triangle is drawn 
    private int centerY = 50; // y-coordinate that determines where top of Pascal's Triangle is drawn
    private int hexRadius = 10; // Determines how big each hexagon is
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
    	
    	//if there's only one row, we're done
    	if (rows==1) return;

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
		//Center everything
		int centerX = this.getWidth()/2;


		//Scale the hexagons so that the triangle takes up the panel
		hexRadius = (int)((this.getHeight()-centerY-50)/(this.totalRows*1.5));
		hexRadius = Math.min(hexRadius, (this.getWidth()-100)/(this.totalRows*2));

		//make the base hexagon shape
		int xPoints[] = {0, hexRadius,  hexRadius*2,  hexRadius*2, hexRadius, 0,0};
		int yPoints[] = {0, -hexRadius/2, 0, hexRadius, 3*hexRadius/2, hexRadius,0};
		GeneralPath hexagon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xPoints.length);
		hexagon.moveTo(xPoints[0], yPoints[0]);
		for (int index = 1; index < xPoints.length; index++) {
		    hexagon.lineTo(xPoints[index], yPoints[index]);
		};
		hexagon.closePath();

		// Adds Rectangles into ArrayList
		// The rectangles represent the area inside the hexagon
		// that contains the number
		squares = new ArrayList<Rectangle>();
		count = 0;
		for (int i = 0; i < totalRows; i++) { // i = rows
		    for (int j = 0; j <= i; j++) { // j = index within row
			squares.add(new Rectangle(centerX - hexRadius*2/2 + j*hexRadius*2 - i*hexRadius*2/2, 
						  centerY + i*hexRadius*3/2, hexRadius*2, hexRadius));
		        count++; 
		    }
		}

		// Draws the hexagons and numbers and fills them in appropriately
		for (int i = 0; i < count; i++) {
		    AffineTransform transform = new AffineTransform();
		    transform.translate(squares.get(i).getX(),squares.get(i).getY());
		    Shape movedHex = transform.createTransformedShape(hexagon);
		    g2.setColor(Color.BLACK); 
		    g2.setStroke(new BasicStroke((int)(Math.sqrt(hexRadius))));
		    g2.draw(movedHex);

		    g2.setColor(new Color(9,70,9));
		     
		    if (numbersInPascalTriangle[i] % 2 == 1){
			g2.setColor(new Color(129,201,130));
			g2.fill(movedHex);
			g2.setColor(new Color(2,63,2)); 
		    }
		    
		    String num = ""+numbersInPascalTriangle[i];
		    //make font scale with number length
		    g2.setFont(new Font("default", Font.BOLD, (int)(hexRadius*1.5*(1-.8*num.length()/6) ) ));

		    //center the number
		    FontMetrics metrics = g2.getFontMetrics();
		    Rectangle2D r = metrics.getStringBounds(num, g2);
		    g2.drawString(num, (int)(squares.get(i).getX() + squares.get(i).getWidth()/2 -r.getWidth()/2),
				  (int)squares.get(i).getY()+metrics.getAscent() + (int)(squares.get(i).getHeight() - r.getHeight())/2);
		}

    } // End Paint Method

} // End class
