package edu.cs56.projects.discretemath.towers_sierpinski;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author Shanen Cross
 * @version CS56, S13, lab04
 *
 * Contains methods which allows you to save an image from a JPanel or other Component
 * in either the .jpg or .png format.
 */

public class SaveImage {

	// These public static methods are the ones which should be called by other classes' methods
	// if they wish to save the component's image.
	
	/**
	 * Saves as .png file
	 * @param comp GUI Component, e.g. JFrame or JPanel
	 */
	public static void savePNG(Component comp) 
	{
		save(comp, "png");
	}
	/**
	 * Saves as .jpg file
	 * @param comp GUI Component, e.g. JFrame or JPanel
	 */
	public static void saveJPG(Component comp) // Saves as .jpg file
	{
		save(comp, "jpg");
	}

	/**
	 * Saves image to file
	 * @param comp GUI Component, e.g. JFrame or JPanel
	 * @param filetype Name of filetype, intended to be either png or jpg
	 */
    private static void save(Component comp, String filetype) {
    	JFrame temp_frame = null; // We won't need this unless comp is a JFrame
    	
    	// If comp *is* a JFrame, we want to get the dimensions from its ContentPane,
    	// not from the frame itself, so we don't get the window header in our image...
    	if (comp instanceof JFrame) {  
    		temp_frame = (JFrame)comp;
    		comp = temp_frame.getContentPane(); //...So we point comp to the JFrame's ContentPane
    	}										// (keeping the original JFrame around as temp_frame)
    	
    	// Then we get the image dimensions,
    	// make a buffered image with those dimensions
    	// and a graphics object from that image.
    	Dimension size = comp.getSize(); 
    	BufferedImage bufImage = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = bufImage.createGraphics();

    	// But getContentPane() doesn't carry over information about the background color,
    	// So we have to get that separately and draw it on
    	// We will use the color of whatever component is passed in
    	
    	if (temp_frame != null){ // If the original comp was a JFrame,
    		g.setColor(temp_frame.getBackground()); // we want the background color from the JFrame,
    	}										    // not the content pane, so we get that from temp_frame
    	else {
    		g.setColor(comp.getBackground()); // Otherwise, we get it from whatever our comp is
    	}
    	g.fillRect(0, 0, size.width, size.height); // Then we draw the background on...
    	comp.printAll(g); // ...and paint our comp itself onto the graphics object
    	g.dispose(); // Then the graphics object is useless to us and disposed of
    	try { // Now we write the buffered image to a file
    	    ImageIO.write(bufImage, filetype, new File("SierpinskiTriangle."+filetype));
    	} catch (IOException ex) {
    	    ex.printStackTrace();
    	}
}
    // The following methods are all hold-overs from my testing phase in case anyone else would want to test
    // these out similarly.
    
    // Called by main() for testing purposes
    public void go() {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	DrawPane pane = new DrawPane();
	frame.getContentPane().add(BorderLayout.CENTER, pane);
	frame.setSize(300,400);
	frame.setVisible(true);
	frame.setBackground(new Color(0,200,0));
	saveJPG(frame.getContentPane());
	savePNG(frame);
   }

    // For testing purposes, used in go()
    public class DrawPane extends JPanel{
    	public void paintComponent(Graphics g){
     		g.setColor(new Color(200,20,10));
    		g.fillRect(20, 20, 100, 200);
    	}
    }
    
    // For testing purposes
    public static void main(String[] args) {
    	SaveImage exp = new SaveImage();
    	exp.go();
    }
}
